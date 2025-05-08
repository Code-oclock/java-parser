package expression;

public class Log extends UnOperation{

    public Log(GlobalExpression expression) {
        super(expression);
    }

    @Override
    protected int makeEvaluation(int a) {
        return (int) (Math.log10(a) / Math.log10(2));
    }

    @Override
    protected String getSymbol() {
        return "log2";
    }
}
