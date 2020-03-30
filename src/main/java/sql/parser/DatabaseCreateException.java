package sql.parser;

/**
 *
 * @author zyy
 */
public class DatabaseCreateException extends RuntimeException {

    public DatabaseCreateException() {
    }

    public DatabaseCreateException(String message) {
        super(message);
    }

    public DatabaseCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseCreateException(Throwable cause) {
        super(cause);
    }

    public DatabaseCreateException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
