package expression.generic.operation;

import expression.generic.calculator.Calculator;

import java.util.Objects;


public class Variable<T> extends AbstractExpression<T> {

    private final String name;

    public Variable(Calculator<T> calculator, String name) {
        super(calculator);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Variable<T> variable = (Variable<T>) object;
        return Objects.equals(name, variable.name);
    }

    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toMiniString() {
        return name;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return switch (name) {
            case "x" -> x;
            case "y" -> y;
            default -> z;
        };
    }
}