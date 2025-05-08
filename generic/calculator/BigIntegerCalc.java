package expression.generic.calculator;

import java.math.BigInteger;

public class BigIntegerCalc implements Calculator<BigInteger> {
    public BigInteger add(BigInteger left, BigInteger right) {
        return left.add(right);
    }

    public BigInteger subtract(BigInteger left, BigInteger right) {
        return left.subtract(right);
    }

    public BigInteger multiply(BigInteger left, BigInteger right) {
        return left.multiply(right);
    }

    public BigInteger divide(BigInteger left, BigInteger right) {
        return left.divide(right);
    }

    public BigInteger negate(BigInteger left) {
        return left.negate();
    }
    public BigInteger parseConst(String number) {
        return new BigInteger(number);
    }
}