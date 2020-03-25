package sql.parser.exception;

/**
 * @author liwenhe
 * @date 2020-03-25 17:49
 */
public class ReferenceDefinitionException extends RuntimeException {

    public ReferenceDefinitionException() {
    }

    public ReferenceDefinitionException(String message) {
        super(message);
    }

    public ReferenceDefinitionException(String message, Throwable cause) {
        super(message, cause);
    }

}
