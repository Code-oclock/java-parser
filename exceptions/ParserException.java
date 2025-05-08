package expression.exceptions;

public class ParserException extends Exception {
    public ParserException(String str, int position) {
        super(str + " at position " + position);
    }
}
