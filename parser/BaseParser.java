package expression.parser;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class BaseParser {
    private static final char END = '\0';
    private CharSource source;
    private char ch = 0xffff;
    private int position = 0;

    protected BaseParser() {
    }

    protected void Source(final CharSource source) {
        this.source = source;
        take();
        position = 0;
    }

    protected char take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        position++;
        return result;
    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    protected void expect(final char expected) {
        if (!take(expected)) {
            throw error("Expected '" + expected + "', found '" + ch + "'");
        }
    }

    protected void expect(final String value) {
        for (final char c : value.toCharArray()) {
            expect(c);
        }
    }

    protected boolean eof() {
        return take(END);
    }

    protected IllegalArgumentException error(final String message) {
        return source.error(message);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }
    protected void skipWhitespace() {
        while (Character.isWhitespace(ch)) {
            take();
        }
    }

    protected int getPosition() {
        return position;
    }

    protected char currentChar() {
        return ch;
    }
}
