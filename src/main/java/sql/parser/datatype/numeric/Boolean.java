package sql.parser.datatype.numeric;

import sql.parser.datatype.NumericTypeDefinition;

/**
 * @author liwenhe
 * @date 2020-03-26 11:22
 */
public class Boolean extends Bool {

    private Boolean(Builder builder) {
        super(builder);
    }

    private static class Builder extends Bool.Builder {

        @Override
        public NumericTypeDefinition build() {
            return new Boolean(this);
        }

    }

}
