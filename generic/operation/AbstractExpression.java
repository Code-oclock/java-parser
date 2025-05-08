package expression.generic.operation;

import expression.generic.calculator.Calculator;

public abstract class AbstractExpression<T> implements GlobalExpression<T> {

    protected final Calculator<T> calculator;

    protected AbstractExpression(Calculator<T> calculator) {
        this.calculator = calculator;
    }
}
