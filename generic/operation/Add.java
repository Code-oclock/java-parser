package expression.generic.operation;


import expression.generic.calculator.Calculator;

public class Add<T> extends Operation<T> {
    public Add(GlobalExpression<T> l, GlobalExpression<T> r, Calculator<T> calculator) {
        super(l, r, calculator);
    }

    @Override
    protected T makeEvaluation(T left, T right) {
        return calculator.add(left, right);
    }

    @Override
    protected int getPriority() {
        return 3000;
    }

    @Override
    protected String getSymbol() {
        return "+";
    }
}
