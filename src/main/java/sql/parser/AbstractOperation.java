package sql.parser;

import java.util.StringJoiner;

/**
 * @author liwenhe
 * @date 2020-03-24 17:06
 */
public abstract class AbstractOperation implements Operation {

    protected final static String EMPTY_STRING      = "";

    protected final static String BLANK_STRING      = " ";

    protected final static String LT_BRACKET_STRING = "(";

    protected final static String RT_BRACKET_STRING = ")";

    protected final static String COMMA_STRING      = ",";

    protected final static String BACK_QUOTE_STRING = "`";

    private final StringJoiner ddlJoiner = new StringJoiner(BLANK_STRING);

    protected StringJoiner getDdlJoiner() {
        return ddlJoiner;
    }

    protected String backQuoteString(String str) {
        return new StringJoiner(EMPTY_STRING, BACK_QUOTE_STRING, BACK_QUOTE_STRING).add(str).toString();
    }

    @Override
    public String convertDDL() {
        return this.getDdlJoiner().toString();
    }

}
