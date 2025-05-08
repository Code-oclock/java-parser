package expression;


public class Subtract extends Operation {
    public Subtract(GlobalExpression l, GlobalExpression r) {
        super(l, r);
    }

    @Override
    public int makeEvaluation(int left, int right) {
        return left - right;
    }

    @Override
    protected int getPriority() {
        return 3000;
    }

    @Override
    protected String getSymbol() {
        return "-";
    }
}
