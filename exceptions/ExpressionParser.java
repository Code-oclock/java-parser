package expression.exceptions;

import expression.*;
import expression.parser.BaseParser;
import expression.parser.StringSource;

import javax.swing.plaf.synth.SynthLookAndFeel;
import java.util.List;
import java.util.Map;


public class ExpressionParser extends BaseParser implements TripleParser, ListParser {
    private final static List<Character> specialSymbols = List.of('x', 'y', 'z', '+', '-', '*', '/', ')', ' ', ']', '}');
    private static List<String> variables;
    // :NOTE: Operation
    private final static Map<Character, String> opereation = Map.of(
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

    protected ExpressionParser() {
    }

    @Override
    public TripleExpression parse(String expression) throws ParserException {
        countBrackets = 0;
        variables = List.of("");
        Source(new StringSource(expression));
        return parseAddOrSubtract();
    }

    @Override
    public ListExpression parse(String expression, List<String> variables) throws ParserException {
        countBrackets = 0;
        ExpressionParser.variables = variables;
        Source(new StringSource(expression));
        return parseAddOrSubtract();
    }

    private GlobalExpression parseAddOrSubtract() throws ParserException {
        GlobalExpression left = parseMultiplyOrDivide("first argument");
        skipWhitespace();
        while (test('+') || test('-')) {
            char operation = take();
            GlobalExpression right = parseMultiplyOrDivide("second argument for " + opereation.get(operation));
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

    private GlobalExpression parseMultiplyOrDivide(String name) throws ParserException {
        GlobalExpression left = parseConstOrVariable(name);
        skipWhitespace();
        while (test('/') || test('*')) {
            char operation = take();
            GlobalExpression right = parseConstOrVariable("second argument for " + opereation.get(operation));
            left =  parseBinaryOperations(operation, left, right);
            skipWhitespace();
        }
        return left;
    }

    private GlobalExpression parseConstOrVariable(String argument) throws ParserException {
        skipWhitespace();
        if (test('(') || test('[') || test('{')) {
            char bracket = take();
            countBrackets++;
            GlobalExpression left = parseAddOrSubtract();
            if (!take(brackets.get(bracket))) {
                throw new MissingBracketsException("Expected close brackets for \"" + bracket + "\"", getPosition());
            }
            countBrackets--;
            return left;
        } else if (take('-')) {
            if (between('0', '9')) {
                return new Const(parseConst(true));
            } else {
                GlobalExpression left = parseConstOrVariable("argument for NEGATE");
                return new CheckedNegate(left);
            }
        } else if (test('l')) {
            try {
                expect("log2");
            } catch (Exception e) {
                throw new ParserException("No correct expression", getPosition());
            }
            GlobalExpression left = parseLogPowArgument("LOG");
            return new CheckedLog(left);
        } else if (test('p')) {
            try {
                expect("pow2");
            } catch (Exception e) {
                throw new ParserException("No correct expression", getPosition());
            }
            GlobalExpression left = parseLogPowArgument("POW");
            return new CheckedPow(left);
        } else {
            if (between('0', '9')) {
                return new Const(parseConst(false));
            } else if (between('x', 'z')){
                return new Variable(parseVariable());
            } else if (Character.isJavaIdentifierStart(currentChar())) {
                String name = parseJavaVariable();
                if (!variables.contains(name)) {
                    throw new InvalidArgumentException("No such variable: " + name, getPosition());
                }
                return new Variable(name, variables.indexOf(name));
            } else {
                throw new MissingArgumentException("Unexpected symbol: \"" + currentChar() + "\"" + ". Expected: " + argument, getPosition());
            }
        }
    }

    private GlobalExpression parseLogPowArgument(String expression) throws ParserException {
        if (!test(' ') && !test('(')) {
            if (!between('x', 'z') && !between('0', '9')) {
                throw new MissingArgumentException("Expect argument for " + expression, getPosition());
            }
            throw new InvalidArgumentException("Expect whitespace", getPosition());
        }
        return parseConstOrVariable("argument for " + expression);
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
            return Integer.parseInt(stringBuilder.toString());
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
        return (!between('0', '9') && !specialSymbols.contains(tmp) && tmp != '\0') && !variables.contains(String.valueOf(tmp));
    }

    private GlobalExpression parseBinaryOperations(char operation, GlobalExpression left, GlobalExpression right) throws ParserException {
        return switch (operation) {
            case '*' -> new CheckedMultiply(left, right);
            case '/' -> new CheckedDivide(left, right);
            case '+' -> new CheckedAdd(left, right);
            case '-' -> new CheckedSubtract(left, right);
            default -> throw new InvalidArgumentException("Unexpected value: \"" + take() + "\"", getPosition());
        };
    }
}
