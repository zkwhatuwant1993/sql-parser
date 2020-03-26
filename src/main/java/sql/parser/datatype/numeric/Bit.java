package sql.parser.datatype.numeric;

import sql.parser.datatype.NumericTypeDefinition;

/**
 * @author liwenhe
 * @date 2020-03-26 10:18
 */
public class Bit extends NumericTypeDefinition {

    private final static Number DEFAULT_LENGTH = 10;

    private final static Number MIN_LENGTH = 1;

    private final static Number MAX_LENGTH = 64;

    private Bit(Builder builder) {
        super(builder);
        this.precision = builder.precision;
    }

    public static class Builder extends NumericTypeDefinition.Builder {

        private Number precision;

        @Override
        public Builder precision(Number precision) {
            if (precision.doubleValue() < MIN_LENGTH.doubleValue()
                    || precision.doubleValue() > MAX_LENGTH.doubleValue()) {
                this.precision = DEFAULT_LENGTH;
            } else {
                this.precision = precision;
            }
            return this;
        }

        @Override
        public NumericTypeDefinition build() {
            return new Bit(this);
        }

    }

    @Override
    public String convertDDL() {
        if (null == this.precision) {
            this.getDdlJoiner().add("BIT");
        } else {
            this.getDdlJoiner().add(String.format("BIT(%d)", this.precision));
        }
        return this.getDdlJoiner().toString();
    }

}
