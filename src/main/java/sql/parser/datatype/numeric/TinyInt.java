package sql.parser.datatype.numeric;

import sql.parser.datatype.NumericTypeDefinition;

/**
 * @author liwenhe
 * @date 2020-03-26 10:26
 */
public class TinyInt extends NumericTypeDefinition {

    private final static Integer SIGNED_MIN_LENGTH = -128;

    private final static Integer SIGNED_MAX_LENGTH = 127;

    private final static Integer UNSIGNED_MIN_LENGTH = 0;

    private final static Integer UNSIGNED_MAX_LENGTH = 255;

    private boolean unsigned;

    private boolean zerofill;

    private TinyInt(Builder builder) {
        super(builder);
        this.precision = builder.precision;
        this.unsigned = builder.unsigned;
        this.zerofill = builder.zerofill;
    }

    public static class Builder extends NumericTypeDefinition.Builder {

        private Integer precision;

        private boolean unsigned;

        private boolean zerofill;

        @Override
        public Builder precision(Integer precision) {
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
        if ((this.unsigned || this.zerofill)
                && (this.precision < UNSIGNED_MIN_LENGTH
                        || this.precision > UNSIGNED_MAX_LENGTH)) {
            this.precision = UNSIGNED_MAX_LENGTH;
        }

        if (!this.unsigned
                && (this.precision < SIGNED_MIN_LENGTH
                        || this.precision > SIGNED_MAX_LENGTH)) {
            this.precision = SIGNED_MAX_LENGTH;
        }

        if (null == this.precision) {
            this.getDdlJoiner().add("TINYINT");
        } else {
            this.getDdlJoiner().add(String.format("TINYINT(%d)", this.precision));
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
