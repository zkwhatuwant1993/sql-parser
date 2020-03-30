package sql.parser;

/**
 *
 *
 * @author zyy
 */
public class DatabaseAlterException extends RuntimeException{

    public DatabaseAlterException() {
    }

    public DatabaseAlterException(String message) {
        super(message);
    }

    public DatabaseAlterException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseAlterException(Throwable cause) {
        super(cause);
    }

    public DatabaseAlterException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
