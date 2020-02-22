package by.kiselevich.parsingtask.exception;

public class OperatorConstantException extends Exception {

    public OperatorConstantException() {
    }

    public OperatorConstantException(String message) {
        super(message);
    }

    public OperatorConstantException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperatorConstantException(Throwable cause) {
        super(cause);
    }

    public OperatorConstantException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
