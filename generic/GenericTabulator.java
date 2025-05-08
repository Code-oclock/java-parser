package expression.generic;

import expression.generic.calculator.*;

import java.util.Map;

public class GenericTabulator implements Tabulator {

    Map<String, Calculator<?>> calculator = Map.of("i", new IntegerCalc(true), "d", new DoubleCalc(),
            "bi", new BigIntegerCalc(), "u", new IntegerCalc(false), "b", new ByteCalc(),
            "bool", new BooleanCalc());


    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        Object[][][] result = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        return makeEvaluate(result, calculator.get(mode), expression, x1, x2, y1, y2, z1, z2);

    }

    private <T> Object[][][] makeEvaluate(Object[][][] result, Calculator<T> calculator, String expression, int x1, int x2, int y1, int y2,
                                          int z1, int z2) throws Exception {
        TripleExpression<T> newExpression = new ExpressionParser<T>().parse(expression, calculator);
        for (int i = x1; i < x2 + 1; ++i) {
            for (int j = y1; j < y2 + 1; ++j) {
                for (int k = z1; k < z2 + 1; ++k) {
                    try {
                        result[i - x1][j - y1][k - z1] = newExpression.evaluate(
                                calculator.parseConst(String.valueOf(i)),
                                calculator.parseConst(String.valueOf(j)),
                                calculator.parseConst(String.valueOf(k))
                        );
                    } catch (Exception e) {
                        result[i - x1][j - y1][k - z1] = null;
                    }
                }
            }
        }
        return result;
    }

}
