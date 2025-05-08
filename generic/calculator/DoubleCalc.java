package expression.generic.calculator;

public class DoubleCalc implements Calculator<Double> {
    public Double add(Double left, Double right) {
        return left + right;
    }

    public Double subtract(Double left, Double right) {
        return left - right;
    }

    public Double multiply(Double left, Double right) {
        return left * right;
    }

    public Double divide(Double left, Double right) {
        return left / right;
    }

    public Double negate(Double left) {
        return -left;
    }
    public Double parseConst(String number) {
        return Double.parseDouble(number);
    }

}
