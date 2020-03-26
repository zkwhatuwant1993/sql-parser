package sql.parser.datatype.numeric;

import sql.parser.datatype.NumericTypeDefinition;

/**
 * @author liwenhe
 * @date 2020-03-26 10:26
 */
public class TinyInt extends NumericTypeDefinition {

    private final static String KEYWORD = "TINYINT";

    private final static Number DEFAULT_LENGTH = 10;

    private final static Number SIGNED_MIN_LENGTH = -128;

    private final static Number SIGNED_MAX_LENGTH = 127;

    private final static Number UNSIGNED_MIN_LENGTH = 0;

    private final static Number UNSIGNED_MAX_LENGTH = 255;

    protected boolean unsigned;

    protected boolean zerofill;

    protected TinyInt(Builder builder) {
        super(builder);
        this.precision = builder.precision;
        this.unsigned = builder.unsigned;
        this.zerofill = builder.zerofill;
    }

    protected String getKeyword() {
        return KEYWORD;
    }

    protected Number getSignedMinLength() {
        return SIGNED_MIN_LENGTH;
    }

    protected Number getSignedMaxLength() {
        return SIGNED_MAX_LENGTH;
    }

    protected Number getUnsignedMinLength() {
        return UNSIGNED_MIN_LENGTH;
    }

    protected Number getUnsignedMaxLength() {
        return UNSIGNED_MAX_LENGTH;
    }

    public static class Builder extends NumericTypeDefinition.Builder {

        protected Number precision;

        protected boolean unsigned;

        protected boolean zerofill;

        @Override
        public Builder precision(Number precision) {
            this.precision = precision;
            return this;
        }

        public Builder unsigned(boolean unsigned) {
            this.unsigned = unsigned;
            return this;
        }

        public Builder zerofill(boolean zerofill) {
            this.zerofill = zerofill;
            return this;
        }

        @Override
        public NumericTypeDefinition build() {
            return new TinyInt(this);
        }

    }

    @Override
    public String convertDDL() {
        if (null != this.precision) {
            if ((this.unsigned || this.zerofill)
                    && (this.precision.doubleValue() < this.getUnsignedMinLength().doubleValue()
                    || this.precision.doubleValue() > this.getUnsignedMaxLength().doubleValue())) {
                this.precision = DEFAULT_LENGTH;
            }

            if (!this.unsigned
                    && (this.precision.doubleValue() < getSignedMinLength().doubleValue()
                    || this.precision.doubleValue() > getSignedMaxLength().doubleValue())) {
                this.precision = DEFAULT_LENGTH;
            }

            this.getDdlJoiner().add(String.format("%s(%d)", this.getKeyword(), this.precision));
        } else {
            this.getDdlJoiner().add(this.getKeyword());
        }

        if (this.zerofill) {
            this.getDdlJoiner().add(UNSIGNED);
            this.getDdlJoiner().add(ZEROFILL);
        } else if (this.unsigned) {
            this.getDdlJoiner().add(UNSIGNED);
        }

        return this.getDdlJoiner().toString();
    }


}
