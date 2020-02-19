package by.kiselevich.parsingtask.exception;

public class WrongExpressionException extends Exception {

    public WrongExpressionException() {
    }

    public WrongExpressionException(String message) {
        super(message);
    }

    public WrongExpressionException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongExpressionException(Throwable cause) {
        super(cause);
    }

    public WrongExpressionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
