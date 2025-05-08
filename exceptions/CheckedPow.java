package expression.exceptions;

import expression.GlobalExpression;
import expression.Pow;

public class CheckedPow extends Pow {
    public CheckedPow(GlobalExpression expression) {
        super(expression);
    }

    @Override
    protected int makeEvaluation(int a) {
        if (a > 30) {
            throw new ParserArithmeticException("Overflow");
        } else if (a < 0) {
            throw new ParserArithmeticException("Pow must be > 0");
        }
        return 1 << a;
    }
}
