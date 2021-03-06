package sql.parser.datatype;

/**
 * @author liwenhe
 * @date 2020-03-26 09:44
 */
public class NumericTypeDefinition extends DataTypeDefinition {

    protected final static String UNSIGNED = "UNSIGNED";

    protected final static String ZEROFILL = "ZEROFILL";

    protected Number precision;

    protected NumericTypeDefinition(Builder builder) {}

    protected abstract static class Builder {

        public abstract Builder precision(Number precision);

        public abstract NumericTypeDefinition build();

    }

}
