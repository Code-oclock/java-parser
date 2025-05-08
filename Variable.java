package expression;

import java.util.List;
import java.util.Objects;

public class Variable implements GlobalExpression {

    private final String name;
    private final int number;

    public Variable(String name) {
        this.name = name;
        this.number = -1;
    }

    public Variable(int number) {
        this.number = 0;
        this.name = "";
    }

    public Variable(String name, int number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return switch (name) {
            case "x" -> x;
            case "y" -> y;
            default -> z;
        };
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
        Variable variable = (Variable) object;
        return Objects.equals(name, variable.name);
    }

    @Override
    public String toMiniString() {
        return name;
    }

    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int evaluate(List<Integer> variables) {
        return variables.get(number);
    }
}