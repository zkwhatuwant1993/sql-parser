package sql.parser.table.primary;

import sql.parser.AbstractOperation;
import sql.parser.table.KeyPart;
import sql.parser.table.index.IndexDefinitionException;
import sql.parser.table.index.IndexType;

import java.util.LinkedHashMap;
import java.util.StringJoiner;

public class PrimaryKeyDefinition extends AbstractOperation {

    private final static String PRIMARY_KEY = "PRIMARY KEY";

    protected IndexType type;

    protected LinkedHashMap<String, KeyPart> keyParts;

    protected PrimaryKeyDefinition(PrimaryKeyDefinition.Builder builder) {
        this.type = builder.type;
        this.keyParts = builder.keyParts;
    }

    public static class Builder {

        private IndexType type;

        private LinkedHashMap<String, KeyPart> keyParts;

        public Builder() {
            this.type = IndexType.HASH;
            this.keyParts = new LinkedHashMap<>();
        }

        public PrimaryKeyDefinition.Builder type(IndexType type) {
            this.type = type;
            return this;
        }

        public PrimaryKeyDefinition.Builder keyPart(KeyPart keyPart) {
            this.keyParts.put(keyPart.getColumn(), keyPart);
            return this;
        }

        public PrimaryKeyDefinition build() {
            return new PrimaryKeyDefinition(this);
        }

    }

    @Override
    public String convertDDL() {
        if (null == keyParts || keyParts.isEmpty()) {
            throw new IndexDefinitionException("KeyPart must be not null or empty.");
        }

        StringJoiner keyPartJoiner = new StringJoiner(", ");
        for (KeyPart keyPart : keyParts.values()) {
            keyPartJoiner.add(keyPart.convertDDL());
        }

        this.getDdlJoiner().add(PRIMARY_KEY);
        this.getDdlJoiner().add(this.type.name());
        this.getDdlJoiner().add(LT_BRACKET_STRING);
        this.getDdlJoiner().merge(keyPartJoiner);
        this.getDdlJoiner().add(RT_BRACKET_STRING);

        return this.getDdlJoiner().toString();
    }

    public static void main(String[] args) {
        System.out.println(
                new PrimaryKeyDefinition
                        .Builder()
                        .keyPart(
                                new KeyPart
                                        .Builder()
                                        .column("id")
                                        .build()
                        ).build()
                        .convertDDL()
        );
    }

}
