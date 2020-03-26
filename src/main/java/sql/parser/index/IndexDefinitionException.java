package sql.parser.index;

/**
 * @author liwenhe
 * @date 2020-03-25 13:44
 */
public class IndexDefinitionException extends RuntimeException {

    public IndexDefinitionException() {
        super();
    }

    public IndexDefinitionException(String message) {
        super(message);
    }

    public IndexDefinitionException(String message, Throwable cause) {
        super(message, cause);
    }

}
