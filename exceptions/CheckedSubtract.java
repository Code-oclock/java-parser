package expression.exceptions;

import expression.*;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(GlobalExpression l, GlobalExpression r) {
        super(l, r);
    }

    @Override
    public int makeEvaluation(int left, int right) {
        if (left >= 0 && right < 0 && (Integer.MAX_VALUE + right < left) ||
                left < 0 && right > 0 && (Integer.MIN_VALUE + right > left)) {
            throw new ParserArithmeticException("Overflow");
        }
        return left - right;

    }
}
