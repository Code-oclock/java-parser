package expression;

import java.util.List;
import java.util.Objects;

public abstract class Operation implements GlobalExpression {

    protected final GlobalExpression left, right;

    abstract protected int makeEvaluation(int left, int right);

    abstract protected int getPriority();
    abstract protected String getSymbol();

    protected Operation(GlobalExpression left, GlobalExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " " + getSymbol() + " " + right.toString() + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Operation operation = (Operation) object;
        return Objects.equals(left, operation.left) && Objects.equals(right, operation.right) && Objects.equals(getSymbol(), operation.getSymbol());
    }

    public int hashCode() {
        return (2 * left.hashCode() - 3 * right.hashCode() + 5 * getSymbol().hashCode()) * 23 + getSymbol().hashCode() * 7;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return makeEvaluation(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return makeEvaluation(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public int evaluate(List<Integer> variables) {
        return makeEvaluation(left.evaluate(variables), right.evaluate(variables));
    }

    @Override
    public String toMiniString() {
        StringBuilder stringBuilder = new StringBuilder();
        boolean need = left instanceof Operation && ((Operation) left).getPriority() < getPriority();

        if (need) {
            stringBuilder.append("(");
        }
        stringBuilder.append(left.toMiniString());
        if (need) {
            stringBuilder.append(")");
        }

        stringBuilder.append(" ");
        stringBuilder.append(getSymbol());
        stringBuilder.append(" ");

        need = right instanceof Operation && ((Operation) right).getPriority() < getPriority();
        if (need || isNeedBracket()) {
            stringBuilder.append("(");
        }
        stringBuilder.append(right.toMiniString());
        if (need || isNeedBracket()) {
            stringBuilder.append(")");
        }

        return stringBuilder.toString();
    }

    private boolean isNeedBracket() {
        if (this instanceof Subtract) {
            if (right instanceof Add || right instanceof Subtract) {
                return true;
            }
        } else if (right instanceof Divide) {
            if (this instanceof Divide || this instanceof Multiply) {
                return true;
            }
        } else if (right instanceof Multiply && this instanceof Divide) {
            return true;
        }
        return false;
    }
}
