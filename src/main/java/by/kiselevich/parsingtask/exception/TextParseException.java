package by.kiselevich.parsingtask.exception;

public class TextParseException extends Exception {

    public TextParseException() {
    }

    public TextParseException(String message) {
        super(message);
    }

    public TextParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextParseException(Throwable cause) {
        super(cause);
    }

    public TextParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
