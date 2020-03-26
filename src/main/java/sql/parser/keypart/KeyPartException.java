package sql.parser.keypart;

/**
 * @author liwenhe
 * @date 2020-03-25 17:48
 */
public class KeyPartException extends RuntimeException {

    public KeyPartException() {
    }

    public KeyPartException(String message) {
        super(message);
    }

    public KeyPartException(String message, Throwable cause) {
        super(message, cause);
    }

}
