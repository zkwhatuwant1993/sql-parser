package sql.parser.datatype;

/**
 * @author liwenhe
 * @date 2020-03-26 10:16
 */
public class DataTypeException extends RuntimeException {

    public DataTypeException() {
        super();
    }

    public DataTypeException(String message) {
        super(message);
    }

    public DataTypeException(String message, Throwable cause) {
        super(message, cause);
    }

}
