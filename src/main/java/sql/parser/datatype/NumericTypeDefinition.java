package sql.parser.datatype;

import sql.parser.AbstractOperation;

/**
 * @author liwenhe
 * @date 2020-03-26 09:44
 */
public class NumericTypeDefinition extends AbstractOperation {

    protected final static String UNSIGNED = "UNSIGNED";

    protected final static String ZEROFILL = "ZEROFILL";

    protected Integer precision;

    protected NumericTypeDefinition(Builder builder) {}

    protected abstract static class Builder {

        public abstract Builder precision(Integer precision);

        public abstract NumericTypeDefinition build();

    }

    @Override
    public String convertDDL() {
        return super.convertDDL();
    }

}
