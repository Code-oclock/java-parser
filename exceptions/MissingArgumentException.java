package expression.exceptions;

public class MissingArgumentException extends ParserException{

    public MissingArgumentException(String str, int position) {
        super(str, position);
    }

}
