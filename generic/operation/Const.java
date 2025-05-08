package expression.generic.operation;

import expression.generic.calculator.Calculator;

import java.util.Objects;

public class Const<T> extends AbstractExpression<T> {

    private final String value;

    public Const(Calculator<T> calculator, String value) {
        super(calculator);
        this.value = value;
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Const<T> aConst = (Const<T>) object;
        return Objects.equals(value, aConst.value);
    }

    @Override
    public String toMiniString() {
        return value;
    }

    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return calculator.parseConst(value);
    }
}
