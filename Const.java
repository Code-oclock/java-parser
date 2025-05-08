package expression;

import java.util.List;
import java.util.Objects;

public class Const implements GlobalExpression {

    private final Number value;

    public Const(int value) {
        this.value = value;}

    @Override
    public int evaluate(int x) {
        return value.intValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Const aConst = (Const) object;
        return Objects.equals(value, aConst.value);
    }

    @Override
    public String toMiniString() {
        return String.valueOf(value);
    }

    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int evaluate(List<Integer> variables) {
        return value.intValue();
    }
}
