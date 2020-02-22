package by.kiselevich.parsingtask.exception;

public class SourceTextReaderException extends Exception {

    public SourceTextReaderException() {
    }

    public SourceTextReaderException(String message) {
        super(message);
    }

    public SourceTextReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public SourceTextReaderException(Throwable cause) {
        super(cause);
    }

    public SourceTextReaderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
