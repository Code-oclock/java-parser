package expression.generic.operation;


import expression.generic.calculator.Calculator;

public class Subtract<T> extends Operation<T> {
    public Subtract(GlobalExpression<T> l, GlobalExpression<T> r, Calculator<T> calculator) {
        super(l, r, calculator);
    }

    @Override
    public T makeEvaluation(T left, T right) {
        return calculator.subtract(left, right);
    }

    @Override
    protected int getPriority() {
        return 3000;
    }

    @Override
    protected String getSymbol() {
        return "-";
    }
}
