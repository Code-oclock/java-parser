package expression.exceptions;

public class InvalidArgumentException extends ParserException{
    public InvalidArgumentException(String str, int position) {
        super(str, position);
    }
}
