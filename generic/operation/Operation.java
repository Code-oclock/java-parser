package expression.generic.operation;


import expression.generic.calculator.*;

import java.util.Objects;

public abstract class Operation<T> extends AbstractExpression<T> {

    protected final GlobalExpression<T> left, right;

    abstract protected T makeEvaluation(T left, T right);

    abstract protected int getPriority();
    abstract protected String getSymbol();

    protected Operation(GlobalExpression<T> left, GlobalExpression<T> right, Calculator<T> calculator) {
        super(calculator);
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
        Operation<?> operation = (Operation<?>) object;
        return Objects.equals(left, operation.left) && Objects.equals(right, operation.right) && Objects.equals(getSymbol(), operation.getSymbol());
    }

    public int hashCode() {
        return (2 * left.hashCode() - 3 * right.hashCode() + 5 * getSymbol().hashCode()) * 23 + getSymbol().hashCode() * 7;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return makeEvaluation(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }


    public String toMiniString() {
        StringBuilder stringBuilder = new StringBuilder();
        boolean need = left instanceof Operation<T> && ((Operation<T>) left).getPriority() < getPriority();

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

        need = right instanceof Operation<T> && ((Operation<T>) right).getPriority() < getPriority();
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
            return right instanceof Add || right instanceof Subtract;
        } else if (right instanceof Divide) {
            return this instanceof Divide || this instanceof Multiply;
        } else return right instanceof Multiply && this instanceof Divide;
    }
}
