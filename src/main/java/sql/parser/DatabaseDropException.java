package sql.parser;

/**
 *
 *
 * @author zyy
 */
public class DatabaseDropException extends RuntimeException{

    public DatabaseDropException() {
    }

    public DatabaseDropException(String message) {
        super(message);
    }

    public DatabaseDropException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseDropException(Throwable cause) {
        super(cause);
    }

    public DatabaseDropException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
