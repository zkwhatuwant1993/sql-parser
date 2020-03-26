package sql.parser.reference;

import sql.parser.AbstractOperation;
import sql.parser.OperationKeyword;
import sql.parser.keypart.KeyPart;

import java.util.LinkedHashMap;
import java.util.StringJoiner;

/**
 * @author liwenhe
 * @date 2020-03-25 16:46
 */
public class ReferenceDefinition extends AbstractOperation {

    private final static String REFERENCES = "REFERENCES";

    private String table;

    private LinkedHashMap<String, KeyPart> keyParts;

    private MatchType match;

    private ReferenceOption onDelete;

    private ReferenceOption onUpdate;

    private ReferenceDefinition(Builder builder) {
        this.table = builder.table;
        this.keyParts = builder.keyParts;
        this.match = builder.match;
        this.onDelete = builder.onDelete;
        this.onUpdate = builder.onUpdate;
    }

    public String getTable() {
        return table;
    }

    public LinkedHashMap<String, KeyPart> getKeyParts() {
        return keyParts;
    }

    public MatchType getMatch() {
        return match;
    }

    public ReferenceOption getOnDelete() {
        return onDelete;
    }

    public ReferenceOption getOnUpdate() {
        return onUpdate;
    }

    public static class Builder {

        private String table;

        private LinkedHashMap<String, KeyPart> keyParts;

        private MatchType match;

        private ReferenceOption onDelete;

        private ReferenceOption onUpdate;

        public Builder() {
            this.keyParts = new LinkedHashMap<>();
            this.match = null;
            this.onDelete = ReferenceOption.RESTRICT;
            this.onUpdate = ReferenceOption.RESTRICT;
        }

        public Builder table(String table) {
            this.table = table;
            return this;
        }

        public Builder keyParts(KeyPart keyPart) {
            this.keyParts.put(keyPart.getColumn(), keyPart);
            return this;
        }

        public Builder onDelete(ReferenceOption onDelete) {
            this.onDelete = onDelete;
            return this;
        }

        public Builder onUpdate(ReferenceOption onUpdate) {
            this.onUpdate = onUpdate;
            return this;
        }

        public ReferenceDefinition build() {
            return new ReferenceDefinition(this);
        }

    }

    @Override
    public String convertDDL() {
        if (null == keyParts || keyParts.isEmpty()) {
            throw new ReferenceDefinitionException("KeyPart must be not null or empty.");
        }

        StringJoiner keyPartJoiner = new StringJoiner(COMMA_STRING + BLANK_STRING);
        for (KeyPart keyPart : keyParts.values()) {
            keyPartJoiner.add(keyPart.convertDDL());
        }

        this.getDdlJoiner().add(REFERENCES);
        this.getDdlJoiner().add(this.backQuoteString(this.table));
        this.getDdlJoiner().add(LT_BRACKET_STRING);
        this.getDdlJoiner().merge(keyPartJoiner);
        this.getDdlJoiner().add(RT_BRACKET_STRING);

        if (null != this.onDelete) {
            this.getDdlJoiner().add(OperationKeyword.ON.name());
            this.getDdlJoiner().add(OperationKeyword.DELETE.name());
            this.getDdlJoiner().add(this.onDelete.getValue());
        }

        if (null != this.onUpdate) {
            this.getDdlJoiner().add(OperationKeyword.ON.name());
            this.getDdlJoiner().add(OperationKeyword.UPDATE.name());
            this.getDdlJoiner().add(this.onUpdate.getValue());
        }

        return this.getDdlJoiner().toString();
    }

}
