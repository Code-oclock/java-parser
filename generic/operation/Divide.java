package expression.generic.operation;


import expression.generic.calculator.Calculator;

public class Divide<T> extends Operation<T> {
    public Divide(GlobalExpression<T> left, GlobalExpression<T> right, Calculator<T> calculator) {
        super(left, right, calculator);
    }

    @Override
    protected T makeEvaluation(T left, T right) {
        return calculator.divide(left, right);
    }

    @Override
    protected int getPriority() {
        return 4000;
    }

    @Override
    protected String getSymbol() {
        return "/";
    }
}
