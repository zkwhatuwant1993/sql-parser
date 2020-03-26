package sql.parser.datatype.numeric;

import sql.parser.datatype.DataTypeException;
import sql.parser.datatype.NumericTypeDefinition;

/**
 * @author liwenhe
 * @date 2020-03-26 10:18
 */
public class Bit extends NumericTypeDefinition {

    private final static Integer MIN_LENGTH = 1;

    private final static Integer MAX_LENGTH = 64;

    private Bit(Builder builder) {
        super(builder);
        this.precision = builder.precision;
    }

    public static class Builder extends NumericTypeDefinition.Builder {

        private Integer precision;

        @Override
        public Builder precision(Integer precision) {
            if (precision < MIN_LENGTH || precision > MAX_LENGTH) {
                throw new DataTypeException(
                        String.format("DataType 'Bit' precision must be >= %d and <= %d", MIN_LENGTH, MAX_LENGTH));
            }
            this.precision = precision;
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
