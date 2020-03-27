package sql.parser.reference;

/**
 *
 *
 * @author liwenhe
 */
public enum MatchType {

    MATCH_FULL("MATCH FULL"),
    MATCH_PARTIAL("MATCH PARTIAL"),
    MATCH_SIMPLE("MATCH SIMPLE");

    MatchType(String value) {
        this.value = value;
    }

    private final String value;

    public String getValue() {
        return value;
    }

}
