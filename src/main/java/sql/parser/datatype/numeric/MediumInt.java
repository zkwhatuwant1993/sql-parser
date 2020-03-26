package sql.parser.datatype.numeric;

import sql.parser.datatype.NumericTypeDefinition;

/**
 * @author liwenhe
 * @date 2020-03-26 10:26
 */
public class MediumInt extends TinyInt {

    private final static String KEYWORD = "MEDIUMINT";

    private final static Number SIGNED_MIN_LENGTH = -8388608;

    private final static Number SIGNED_MAX_LENGTH = 8388607;

    private final static Number UNSIGNED_MIN_LENGTH = 0;

    private final static Number UNSIGNED_MAX_LENGTH = 16777215;

    private MediumInt(Builder builder) {
        super(builder);
        this.precision = builder.precision;
        this.unsigned = builder.unsigned;
        this.zerofill = builder.zerofill;
    }

    @Override
    protected String getKeyword() {
        return KEYWORD;
    }

    @Override
    protected Number getSignedMinLength() {
        return SIGNED_MIN_LENGTH;
    }

    @Override
    protected Number getSignedMaxLength() {
        return SIGNED_MAX_LENGTH;
    }

    @Override
    protected Number getUnsignedMinLength() {
        return UNSIGNED_MIN_LENGTH;
    }

    @Override
    protected Number getUnsignedMaxLength() {
        return UNSIGNED_MAX_LENGTH;
    }

    public static class Builder extends TinyInt.Builder {

        @Override
        public NumericTypeDefinition build() {
            return new MediumInt(this);
        }

    }

}
