package sql.parser.reference;

import org.apache.commons.lang3.StringUtils;
import sql.parser.AbstractOperation;
import sql.parser.OperationKeyword;
import sql.parser.keypart.KeyPart;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringJoiner;

/**
 * @author liwenhe
 */
public class ReferenceDefinition extends AbstractOperation {

    private final static String REFERENCES = "REFERENCES";

    private String name;

    private Set<String> columns;

    private String table;

    private LinkedHashMap<String, KeyPart> keyParts;

    private MatchType match;

    private ReferenceOption onDelete;

    private ReferenceOption onUpdate;

    private ReferenceDefinition(Builder builder) {
        this.name = builder.name;
        this.columns = builder.columns;
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

        private String name;

        private Set<String> columns;

        private String table;

        private LinkedHashMap<String, KeyPart> keyParts;

        private MatchType match;

        private ReferenceOption onDelete;

        private ReferenceOption onUpdate;

        public Builder() {
            this.columns = new LinkedHashSet<>();
            this.keyParts = new LinkedHashMap<>();
            this.match = null;
            this.onDelete = ReferenceOption.RESTRICT;
            this.onUpdate = ReferenceOption.RESTRICT;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder column(String column) {
            this.columns.add(column);
            return this;
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
        if (null == this.columns || this.columns.isEmpty()) {
            throw new ReferenceDefinitionException("Reference [columns] must be not null or empty.");
        }

        if (null == this.keyParts || this.keyParts.isEmpty()) {
            throw new ReferenceDefinitionException("Reference [KeyPart] must be not null or empty.");
        }

        StringJoiner columnsJoiner = new StringJoiner(COMMA_STRING + BLANK_STRING);
        for (String column : this.columns) {
            columnsJoiner.add(this.backQuoteString(column));
        }

        StringJoiner keyPartJoiner = new StringJoiner(COMMA_STRING + BLANK_STRING);
        for (KeyPart keyPart : this.keyParts.values()) {
            keyPartJoiner.add(keyPart.convertDDL());
        }

        if (!StringUtils.isEmpty(this.name) && !StringUtils.isBlank(this.name)) {
            this.getDdlJoiner().add(this.name);
        }

        this.getDdlJoiner().add(LT_BRACKET_STRING);
        this.getDdlJoiner().merge(columnsJoiner);
        this.getDdlJoiner().add(RT_BRACKET_STRING);

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
