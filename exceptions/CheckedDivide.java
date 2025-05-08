package expression.exceptions;

import expression.*;

public class CheckedDivide extends Divide {
    public CheckedDivide(GlobalExpression left, GlobalExpression right) {
        super(left, right);
    }

    @Override
    protected int makeEvaluation(int left, int right) {
        if (right == 0) {
            throw new ParserArithmeticException("Division by 0");
        } else if (left == Integer.MIN_VALUE && right == -1) {
            throw new ParserArithmeticException("Overflow");
        }
        return left / right;
    }
}
