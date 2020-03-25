package sql.parser.table.index;

public class FullTextIndexDefinition extends IndexDefinition {

    private final static String TYPE = "FULLTEXT";

    protected FullTextIndexDefinition(Builder builder) {
        super(builder);
    }

    public static class Builder extends IndexDefinition.Builder {

        @Override
        public IndexDefinition build() {
            return new FullTextIndexDefinition(this);
        }

    }

    @Override
    protected String type() {
        return TYPE;
    }

}
