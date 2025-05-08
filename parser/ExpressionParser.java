package expression.parser;

import expression.*;



public class ExpressionParser extends BaseParser implements TripleParser {
    protected ExpressionParser() {
    }

    @Override
    public TripleExpression parse(String expression) {
        Source(new StringSource(expression));
        return parseAddOrSubtract();
    }

    private GlobalExpression parseAddOrSubtract() {
        GlobalExpression left = parseMultiplyOrDivide();
        skipWhitespace();
        while (test('+') || test('-')) {
            char operation = take();
            GlobalExpression right = parseMultiplyOrDivide();
            left =  parseBinaryOperations(operation, left, right);
            skipWhitespace();
        }
        return left;
    }

    private GlobalExpression parseMultiplyOrDivide() {
        GlobalExpression left = parseConstOrVariable();
        skipWhitespace();
        while (test('/') || test('*')) {
            char operation = take();
            GlobalExpression right = parseConstOrVariable();
            left =  parseBinaryOperations(operation, left, right);
            skipWhitespace();
        }
        return left;
    }

    private GlobalExpression parseConstOrVariable() {
        skipWhitespace();
        if (take('(')) {
            GlobalExpression left = parseAddOrSubtract();
            take();
            return left;
        }
        else if (take('-')) {
            if (between('0', '9')) {
                return new Const(parseConst(true));
            } else {
                GlobalExpression left = parseConstOrVariable();
                return new Negative(left);
            }
        } else {
            if (between('0', '9')) {
                return new Const(parseConst(false));
            } else {
                return new Variable(parseVariable());
            }
        }
    }

    private int parseConst(boolean negative) {
        StringBuilder stringBuilder = new StringBuilder();
        if (negative) {
            stringBuilder.append('-');
        }
        while (between('0', '9')) {
            stringBuilder.append(take());
        }
        try {
            int result = Integer.parseInt(stringBuilder.toString());
        } catch (NumberFormatException e) {
            throw new ArithmeticException("Overflow");
        }
        return Integer.parseInt(stringBuilder.toString());
    }

    private String parseVariable() {
        return String.valueOf(take());
    }

    private GlobalExpression parseBinaryOperations(char operation, GlobalExpression left, GlobalExpression right) {
        return switch (operation) {
            case '*' -> new Multiply(left, right);
            case '/' -> new Divide(left, right);
            case '+' -> new Add(left, right);
            case '-' -> new Subtract(left, right);
            default -> throw new IllegalStateException("Unexpected value: " + take());
        };
    }
}
