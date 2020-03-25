package sql.parser.exception;

/**
 * @author liwenhe
 * @date 2020-03-25 11:44
 */
public class DefaultValueException extends RuntimeException {

    public DefaultValueException() {
    }

    public DefaultValueException(String message) {
        super(message);
    }

    public DefaultValueException(String message, Throwable cause) {
        super(message, cause);
    }

}
