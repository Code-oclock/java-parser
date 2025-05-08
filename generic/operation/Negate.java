package expression.generic.operation;


import expression.generic.calculator.Calculator;

public class Negate<T> extends UnOperation<T> {

    public Negate(GlobalExpression<T> name, Calculator<T> calculator) {
        super(name, calculator);
    }


    @Override
    protected T makeEvaluation(T a) {
        return calculator.negate(a);
    }

    @Override
    protected String getSymbol() {
        return "-";
    }
}