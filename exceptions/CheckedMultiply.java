package expression.exceptions;


import expression.*;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(GlobalExpression left, GlobalExpression right) {
        super(left, right);
    }

    @Override
    protected int makeEvaluation(int left, int right) {
        if ((right > 0 && (left > Integer.MAX_VALUE / right || left < Integer.MIN_VALUE / right)) ||
                (right < -1 && (left < Integer.MAX_VALUE / right || left > Integer.MIN_VALUE / right)) ||
                (right == -1 && left == Integer.MIN_VALUE)) {
            throw new ParserArithmeticException("Overflow");
        }
        return left * right;
    }
}
