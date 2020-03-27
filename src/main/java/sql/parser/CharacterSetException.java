package sql.parser;

/**
 * @author liwenhe
 * @date 2020-03-27 13:24
 */
public class CharacterSetException extends RuntimeException {

    public CharacterSetException() {
    }

    public CharacterSetException(String message) {
        super(message);
    }

    public CharacterSetException(String message, Throwable cause) {
        super(message, cause);
    }

}
