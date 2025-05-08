package expression.generic.operation;

import expression.generic.calculator.Calculator;

import java.util.Objects;

public abstract class UnOperation<T> extends AbstractExpression<T> {

    protected final GlobalExpression<T> expression;

    abstract protected T makeEvaluation(T a);
    abstract protected String getSymbol();

    protected UnOperation(GlobalExpression<T> expression, Calculator<T> calculator) {
        super(calculator);
        this.expression = expression;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return makeEvaluation(expression.evaluate(x, y, z));
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
        UnOperation<?> operation = (UnOperation<?>) object;
        return Objects.equals(expression, operation.expression) && Objects.equals(getSymbol(), operation.getSymbol());
    }

    public int hashCode() {
        return (2 * expression.hashCode() - 3) * 23;
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
