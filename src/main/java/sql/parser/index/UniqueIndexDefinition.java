package sql.parser.index;

public class UniqueIndexDefinition extends IndexDefinition {

    private final static String TYPE = "UNIQUE";

    protected UniqueIndexDefinition(Builder builder) {
        super(builder);
    }

    public static class Builder extends IndexDefinition.Builder {

        @Override
        public IndexDefinition build() {
            return new UniqueIndexDefinition(this);
        }

    }

    @Override
    protected String type() {
        return TYPE;
    }

}
