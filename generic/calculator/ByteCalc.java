package expression.generic.calculator;

public class ByteCalc implements Calculator<Byte>{
    @Override
    public Byte parseConst(String value) {
        return (byte) Integer.parseInt(value);
    }

    @Override
    public Byte add(Byte left, Byte right) {
        return (byte) (left + right);
    }

    @Override
    public Byte divide(Byte left, Byte right) {
        return (byte) (left / right);
    }

    @Override
    public Byte multiply(Byte left, Byte right) {
        return (byte) (left * right);
    }

    @Override
    public Byte subtract(Byte left, Byte right) {
        return (byte) (left - right);
    }

    @Override
    public Byte negate(Byte a) {
        return (byte) -a;
    }
}
