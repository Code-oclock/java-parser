package expression.generic.calculator;

import expression.exceptions.ParserArithmeticException;

public class IntegerCalc implements Calculator<Integer> {
    private final boolean overflow;

    public IntegerCalc(boolean overflow) {
        this.overflow = overflow;
    }

    public Integer add(Integer left, Integer right) {
        if (overflow) {
            if (left > 0 && right > 0 && (Integer.MAX_VALUE - right < left) ||
                    left < 0 && right < 0 && (Integer.MIN_VALUE - right > left)) {
                throw new ParserArithmeticException("Overflow");
            }
        }
        return left + right;
    }

    public Integer subtract(Integer left, Integer right) {
        if (overflow) {
            if (left >= 0 && right < 0 && (Integer.MAX_VALUE + right < left) ||
                    left < 0 && right > 0 && (Integer.MIN_VALUE + right > left)) {
                throw new ParserArithmeticException("Overflow");
            }
        }
        return left - right;
    }

    public Integer multiply(Integer left, Integer right) {
        if (overflow) {
            if ((right > 0 && (left > Integer.MAX_VALUE / right || left < Integer.MIN_VALUE / right)) ||
                    (right < -1 && (left < Integer.MAX_VALUE / right || left > Integer.MIN_VALUE / right)) ||
                    (right == -1 && left == Integer.MIN_VALUE)) {
                throw new ParserArithmeticException("Overflow");
            }
        }
        return left * right;
    }

    public Integer divide(Integer left, Integer right) {
        if (overflow) {
            if (right == 0) {
                throw new ParserArithmeticException("Division by 0");
            } else if (left == Integer.MIN_VALUE && right == -1) {
                throw new ParserArithmeticException("Overflow");
            }
        }
        return left / right;
    }

    public Integer negate(Integer left) {
        if (overflow) {
            if (left == Integer.MIN_VALUE) {
                throw new ParserArithmeticException("Overflow");
            }
        }
        return -left;
    }

    public Integer parseConst(String number) {
        return Integer.parseInt(number);
    }

}
