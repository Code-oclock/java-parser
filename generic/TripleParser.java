package expression.generic;

import expression.generic.calculator.Calculator;

public interface TripleParser<T> {
    TripleExpression<T> parse(String expression, Calculator<T> calculator) throws Exception;
}
