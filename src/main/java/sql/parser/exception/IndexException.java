package sql.parser.exception;

/**
 * @author liwenhe
 * @date 2020-03-25 13:44
 */
public class IndexException extends RuntimeException {

    public IndexException() {
        super();
    }

    public IndexException(String message) {
        super(message);
    }

    public IndexException(String message, Throwable cause) {
        super(message, cause);
    }

}
