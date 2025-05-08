package expression.exceptions;

public class MissingBracketsException extends ParserException{
    public MissingBracketsException(String str, int position) {

        super(str, position);
    }
}
