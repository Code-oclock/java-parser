package expression.generic;

import expression.exceptions.*;
import expression.generic.calculator.*;
import expression.generic.operation.*;
import expression.parser.BaseParser;
import expression.parser.StringSource;

import java.util.List;
import java.util.Map;

public class ExpressionParser<T> extends BaseParser implements TripleParser<T> {
    private final static List<Character> specialSymbols = List.of('x', 'y', 'z', '+', '-', '*', '/', ')', ' ', ']', '}');
    // :NOTE: Operation
    private final static Map<Character, String> operation = Map.of(
            '*', "MULTIPLY",
            '/', "DIVIDE",
            '-', "SUBTRACT",
            '+', "ADD"
    );
    private final static Map<Character, Character> brackets = Map.of(
            '(',')',
            '{','}',
            '[', ']'
    );
    private int countBrackets;
    private Calculator<T> calculator;



    @Override
    public TripleExpression<T> parse(String expression, Calculator<T> calculator) throws Exception {
        countBrackets = 0;
        Source(new StringSource(expression));
        this.calculator = calculator;
        return parseAddOrSubtract();
    }

    private GlobalExpression<T> parseAddOrSubtract() throws ParserException {
        GlobalExpression<T> left = parseMultiplyOrDivide("first argument");
        skipWhitespace();
        while (test('+') || test('-')) {
            char operation = take();
            GlobalExpression<T> right = parseMultiplyOrDivide("second argument for " + ExpressionParser.operation.get(operation));
            left =  parseBinaryOperations(operation, left, right);
            skipWhitespace();
        }
        if (strangeSymbol(currentChar())) {
            throw new InvalidArgumentException("Unsupported character: \"" + currentChar() + "\"", getPosition());
        } else if (brackets.containsValue(currentChar()) && countBrackets <= 0) {
            throw new MissingBracketsException("No opening brackets for \"" + currentChar() + "\"", getPosition());
        } else if (between('0', '9')) {
            throw new InvalidArgumentException("Whitespace in number", getPosition());
        }
        return left;
    }

    private GlobalExpression<T> parseMultiplyOrDivide(String name) throws ParserException {
        GlobalExpression<T> left = parseConstOrVariable(name);
        skipWhitespace();
        while (test('/') || test('*')) {
            char operation = take();
            GlobalExpression<T> right = parseConstOrVariable("second argument for " + ExpressionParser.operation.get(operation));
            left =  parseBinaryOperations(operation, left, right);
            skipWhitespace();
        }
        return left;
    }

    private GlobalExpression<T> parseConstOrVariable(String argument) throws ParserException {
        skipWhitespace();
        if (test('(') || test('[') || test('{')) {
            char bracket = take();
            countBrackets++;
            GlobalExpression<T> left = parseAddOrSubtract();
            if (!take(brackets.get(bracket))) {
                throw new MissingBracketsException("Expected close brackets for \"" + bracket + "\"", getPosition());
            }
            countBrackets--;
            return left;
        } else if (take('-')) {
            if (between('0', '9')) {
                return new Const<>(calculator, parseConst(true));
            } else {
                GlobalExpression<T> left = parseConstOrVariable("argument for NEGATE");
                return new Negate<>(left, calculator);
            }
        } else {
            if (between('0', '9')) {
                return new Const<>(calculator, parseConst(false));
            } else if (between('x', 'z')){
                return new Variable<>(calculator, parseVariable());
            } else {
                throw new MissingArgumentException("Unexpected symbol: \"" + currentChar() + "\"" + ". Expected: " + argument, getPosition());
            }
        }
    }

    private String parseConst(boolean negative) {
        StringBuilder stringBuilder = new StringBuilder();
        if (negative) {
            stringBuilder.append('-');
        }
        while (between('0', '9')) {
            stringBuilder.append(take());
        }
        try {
            return stringBuilder.toString();
        } catch (NumberFormatException e) {
            throw new ParserArithmeticException("Overflow");
        }
    }

    private String parseVariable() {
        return String.valueOf(take());
    }

    private String parseJavaVariable() {
        StringBuilder name = new StringBuilder();
        while (Character.isJavaIdentifierPart(currentChar()) && currentChar() != '\0') {
            name.append(take());
        }
        return name.toString();
    }

    private boolean strangeSymbol(char tmp) {
        return (!between('0', '9') && !specialSymbols.contains(tmp) && tmp != '\0');
    }

    private GlobalExpression<T> parseBinaryOperations(char operation, GlobalExpression<T> left, GlobalExpression<T> right) throws ParserException {
        return switch (operation) {
            case '*' -> new Multiply<>(left, right, calculator);
            case '/' -> new Divide<>(left, right, calculator);
            case '+' -> new Add<>(left, right, calculator);
            case '-' -> new Subtract<>(left, right, calculator);
            default -> throw new InvalidArgumentException("Unexpected value: \"" + take() + "\"", getPosition());
        };
    }
}
