package expression;

public class Pow extends UnOperation{
    public Pow(GlobalExpression expression) {
        super(expression);
    }

    @Override
    protected int makeEvaluation(int a) {
        return 1 << a;
    }

    @Override
    protected String getSymbol() {
        return "pow2";
    }
}
