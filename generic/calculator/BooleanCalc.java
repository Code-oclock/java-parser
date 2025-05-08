package expression.generic.calculator;

public class BooleanCalc implements Calculator<Boolean>{
    @Override
    public Boolean parseConst(String value) {
        return Integer.parseInt(value) != 0;
    }

    @Override
    public Boolean add(Boolean left, Boolean right) {
        return left | right;
    }

    @Override
    public Boolean divide(Boolean left, Boolean right) {
        return (left ? 1 : 0) / (right ? 1 : 0) != 0;
    }

    @Override
    public Boolean multiply(Boolean left, Boolean right) {
        return left & right;
    }

    @Override
    public Boolean subtract(Boolean left, Boolean right) {
        return left ^ right;
    }

    @Override
    public Boolean negate(Boolean a) {
        return a;
    }
}
