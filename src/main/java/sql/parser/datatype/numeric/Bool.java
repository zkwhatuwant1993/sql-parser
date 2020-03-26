package sql.parser.datatype.numeric;

import sql.parser.datatype.NumericTypeDefinition;

/**
 * @author liwenhe
 * @date 2020-03-26 11:22
 */
public class Bool extends NumericTypeDefinition {

    protected Bool(Builder builder) {
        super(builder);
    }

    protected static class Builder extends NumericTypeDefinition.Builder {

        @Override
        public Builder precision(Number precision) {
            return this;
        }

        @Override
        public NumericTypeDefinition build() {
            return new Bool(this);
        }

    }

    @Override
    public String convertDDL() {
        this.getDdlJoiner().add("TINYINT(1)");
        return this.getDdlJoiner().toString();
    }

}
