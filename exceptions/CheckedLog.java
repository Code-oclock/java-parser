package expression.exceptions;
import expression.*;
public class CheckedLog extends Log {
    public CheckedLog(GlobalExpression expression) {
        super(expression);
    }

    @Override
    protected int makeEvaluation(int a) {
        if (a <= 0) {
            throw new ParserArithmeticException("Log int must be positive");
        }
        return (int) (Math.log10(a) / Math.log10(2));
    }
}
