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

    private final StringJoiner ddlJoiner = new StringJoiner(BLANK_STRING);

    private String collate = "UTF8_GENERAL_CI";

    private String characterSet = "UTF8";

    public AbstractOperation collate(String collate) {
        return this;
    }

    public AbstractOperation characterSet(String characterSet) {
        return this;
    }

    protected String getCollate() {
        return collate;
    }

    protected String getCharacterSet() {
        return characterSet;
    }

    protected void setCollate(String collate) {
        this.collate = collate;
    }


    protected void setCharacterSet(String characterSet) {
        this.characterSet = characterSet;
    }

    protected StringJoiner getDdlJoiner() {
        return ddlJoiner;
    }

    protected String backQuoteString(String str) {
        return new StringJoiner(EMPTY_STRING, "`", "`")
                .add(str)
                .toString();
    }

    @Override
    public String convertDDL() {
        return this.getDdlJoiner().toString();
    }

}
