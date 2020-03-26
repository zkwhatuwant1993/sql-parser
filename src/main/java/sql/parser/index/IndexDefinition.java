package sql.parser.index;

import org.apache.commons.lang3.StringUtils;
import sql.parser.keypart.KeyPart;

import java.util.LinkedHashMap;
import java.util.StringJoiner;

public class IndexDefinition extends AbstractIndexDefinition {

    protected String name;

    protected IndexType type;

    protected LinkedHashMap<String, KeyPart> keyParts;

    protected IndexDefinition(Builder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.keyParts = builder.keyParts;
    }

    public static class Builder {

        private String name;

        private IndexType type;

        private LinkedHashMap<String, KeyPart> keyParts;

        public Builder() {
            this.type = IndexType.BTREE;
            this.keyParts = new LinkedHashMap<>();
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder type(IndexType type) {
            this.type = type;
            return this;
        }

        public Builder keyPart(KeyPart keyPart) {
            this.keyParts.put(keyPart.getColumn(), keyPart);
            return this;
        }

        public IndexDefinition build() {
            return new IndexDefinition(this);
        }

    }

    @Override
    protected String type() {
        return null;
    }

    @Override
    protected String ddl() {
        if (StringUtils.isEmpty(this.name) || StringUtils.isBlank(this.name)) {
            throw new IndexDefinitionException("Index's name must be not empty or blank.");
        }

        if (null == keyParts || keyParts.isEmpty()) {
            throw new IndexDefinitionException("KeyPart must be not null or empty.");
        }

        StringJoiner keyPartJoiner = new StringJoiner(COMMA_STRING + BLANK_STRING);
        for (KeyPart keyPart : keyParts.values()) {
            keyPartJoiner.add(keyPart.convertDDL());
        }

        this.getDdlJoiner().add(this.name);
        this.getDdlJoiner().add(this.type.name());
        this.getDdlJoiner().add(LT_BRACKET_STRING);
        this.getDdlJoiner().merge(keyPartJoiner);
        this.getDdlJoiner().add(RT_BRACKET_STRING);

        return this.getDdlJoiner().toString();
    }

}
