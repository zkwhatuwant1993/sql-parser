package sql.parser.datatype.numeric;

import sql.parser.datatype.NumericTypeDefinition;

/**
 * @author liwenhe
 * @date 2020-03-26 10:26
 */
public class Integer extends Int {

    private final static String KEYWORD = "INTEGER";

    private Integer(Builder builder) {
        super(builder);
        this.precision = builder.precision;
        this.unsigned = builder.unsigned;
        this.zerofill = builder.zerofill;
    }

    @Override
    protected String getKeyword() {
        return KEYWORD;
    }

    public static class Builder extends Int.Builder {

        @Override
        public NumericTypeDefinition build() {
            return new Integer(this);
        }

    }

}
