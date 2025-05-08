package expression;


public class Divide extends Operation {
    public Divide(GlobalExpression left, GlobalExpression right) {
        super(left, right);
    }

    @Override
    protected int makeEvaluation(int left, int right) {
        return left / right;
    }

    @Override
    protected int getPriority() {
        return 4000;
    }

    @Override
    protected String getSymbol() {
        return "/";
    }
}
