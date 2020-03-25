package sql.parser.exception;

/**
 * @author liwenhe
 * @date 2020-03-24 18:32
 */
public class NumericException extends RuntimeException {

    public NumericException() {
    }

    public NumericException(String message) {
        super(message);
    }

    public NumericException(String message, Throwable cause) {
        super(message, cause);
    }

}
