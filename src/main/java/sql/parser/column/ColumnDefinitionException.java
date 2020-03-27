package sql.parser.column;

/**
 * When parsing column definition failed, throw this exception.
 *
 * @author liwenhe
 */
public class ColumnDefinitionException extends RuntimeException {

    public ColumnDefinitionException() {
    }

    public ColumnDefinitionException(String message) {
        super(message);
    }

    public ColumnDefinitionException(String message, Throwable cause) {
        super(message, cause);
    }

}
