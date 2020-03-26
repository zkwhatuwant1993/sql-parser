package sql.parser.datatype.numeric;

import sql.parser.datatype.DataTypeException;
import sql.parser.datatype.NumericTypeDefinition;

/**
 * @author liwenhe
 * @date 2020-03-26 10:26
 */
public class TinyInt extends NumericTypeDefinition {

    private final static Integer MIN_LENGTH = 1;

    private final static Integer MAX_LENGTH = 64;

    protected TinyInt(Builder builder) {
        super(builder);
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
            return new TinyInt(this);
        }

    }

}
