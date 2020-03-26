package sql.parser.index;

public class SpatialIndexDefinition extends IndexDefinition {

    private final static String TYPE = "SPATIAL";

    private SpatialIndexDefinition(Builder builder) {
        super(builder);
    }

    public static class Builder extends IndexDefinition.Builder {

        @Override
        public IndexDefinition build() {
            return new SpatialIndexDefinition(this);
        }

    }

    @Override
    protected String type() {
        return TYPE;
    }

}
