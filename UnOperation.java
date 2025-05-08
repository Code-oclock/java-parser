package expression;

import java.util.List;
import java.util.Objects;

public abstract class UnOperation implements GlobalExpression {

    protected final GlobalExpression expression;

    abstract protected int makeEvaluation(int a);
    abstract protected String getSymbol();

    protected UnOperation(GlobalExpression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return getSymbol() + "(" + expression.toString() + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        UnOperation operation = (UnOperation) object;
        return Objects.equals(expression, operation.expression) && Objects.equals(getSymbol(), operation.getSymbol());
    }

    public int hashCode() {
        return (2 * expression.hashCode() - 3) * 23;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return makeEvaluation(expression.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return makeEvaluation(expression.evaluate(x));
    }

    @Override
    public int evaluate(List<Integer> variables) {
        return makeEvaluation(expression.evaluate(variables));
    }

    @Override
    public String toMiniString() {
        StringBuilder stringBuilder = new StringBuilder();
        boolean need = (expression instanceof Operation);
        stringBuilder.append(getSymbol());

        if (need) {
            stringBuilder.append("(");
        } else {
            stringBuilder.append(" ");
        }
        stringBuilder.append(expression.toMiniString());
        if (need) {
            stringBuilder.append(")");
        }
        return stringBuilder.toString();
    }
}
