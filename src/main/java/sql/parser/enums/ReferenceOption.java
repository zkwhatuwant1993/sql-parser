package sql.parser.enums;

/**
 * @author liwenhe
 * @date 2020-03-25 17:00
 */
public enum ReferenceOption {

    RESTRICT("RESTRICT"),
    CASCADE("CASCADE"),
    SET_NULL("SET NULL"),
    NO_ACTION("NO ACTION"),
    SET_DEFAULT("SET DEFAULT");

    ReferenceOption(String value) {
        this.value = value;
    }

    private final String value;

    public String getValue() {
        return value;
    }

}
