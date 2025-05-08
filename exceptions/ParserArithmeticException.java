package expression.exceptions;

public class ParserArithmeticException extends ArithmeticException {
    public ParserArithmeticException(String str) {
        super(str);
    }
}