package expression;

import java.util.List;

public class Add extends Operation {
    public Add(GlobalExpression l, GlobalExpression r) {
        super(l, r);
    }

    @Override
    protected int makeEvaluation(int left, int right) {
        return left + right;
    }

    @Override
    protected int getPriority() {
        return 3000;
    }

    @Override
    protected String getSymbol() {
        return "+";
    }
}
