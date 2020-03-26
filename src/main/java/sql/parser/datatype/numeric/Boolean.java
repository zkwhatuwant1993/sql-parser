package sql.parser.datatype.numeric;

import sql.parser.datatype.NumericTypeDefinition;

/**
 * @author liwenhe
 * @date 2020-03-26 11:22
 */
public class Boolean extends NumericTypeDefinition {

    protected Boolean(Builder builder) {
        super(builder);
    }

    private static class Builder extends NumericTypeDefinition.Builder {

        @Override
        public Builder precision(Integer precision) {
            return this;
        }

        @Override
        public NumericTypeDefinition build() {
            return new Boolean(this);
        }

    }

    @Override
    public String convertDDL() {
        this.getDdlJoiner().add("TINYINT(1)");
        return this.getDdlJoiner().toString();
    }

}
