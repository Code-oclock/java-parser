package expression;

public class Negative extends UnOperation {

    public Negative(GlobalExpression name) {
        super(name);
    }


    @Override
    protected int makeEvaluation(int a) {
        return -a;
    }

    @Override
    protected String getSymbol() {
        return "-";
    }
}