package expression.exceptions;
import expression.*;

public class CheckedNegate extends Negative {

    public CheckedNegate(GlobalExpression name) {
        super(name);
    }


    @Override
    protected int makeEvaluation(int a) {
        if (a == Integer.MIN_VALUE) {
            throw new ParserArithmeticException("Overflow");
        }
        return -a;
    }
}