package sql.parser.column;

import sql.parser.AbstractOperation;
import sql.parser.datatype.DataTypeDefinition;

/**
 * @author liwenhe
 * @date 2020-03-26 18:06
 */
public class ColumnDefinition extends AbstractOperation {

    private String name;

    private DataTypeDefinition dataType;

    private Boolean isNullable;

    private Boolean isAutoIncrementable;

    private Boolean isUnique;

    private Boolean isPrimary;

    private String comment;

    private String collate;

    private ColumnFormatType formatType;

    private ColumnStorageType storageType;

    private ColumnDefinition(Builder builder) {
        this.name = builder.name;
        this.dataType = builder.dataType;
        this.isNullable = builder.isNullable;
        this.isAutoIncrementable = builder.isAutoIncrementable;
        this.isUnique = builder.isUnique;
        this.isPrimary = builder.isPrimary;
        this.comment = builder.comment;
        this.collate = builder.collate;
        this.formatType = builder.formatType;
        this.storageType = builder.storageType;
    }

    public static class Builder {

        private String name;

        private DataTypeDefinition dataType;

        private Boolean isNullable;

        private Boolean isAutoIncrementable;

        private Boolean isUnique;

        private Boolean isPrimary;

        private String comment;

        private String collate;

        private ColumnFormatType formatType;

        private ColumnStorageType storageType;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder dataType(DataTypeDefinition dataType) {
            this.dataType = dataType;
            return this;
        }

        public Builder isNullable(Boolean isNullable) {
            this.isNullable = isNullable;
            return this;
        }

        public Builder isAutoIncrementable(Boolean isAutoIncrementable) {
            this.isAutoIncrementable = isAutoIncrementable;
            return this;
        }

        public Builder isUnique(Boolean isUnique) {
            this.isUnique = isUnique;
            return this;
        }

        public Builder isPrimary(Boolean isPrimary) {
            this.isPrimary = isPrimary;
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder collate(String collate) {
            this.collate = collate;
            return this;
        }

        public Builder formatType(ColumnFormatType formatType) {
            this.formatType = formatType;
            return this;
        }

        public Builder storageType(ColumnStorageType storageType) {
            this.storageType = storageType;
            return this;
        }

        public ColumnDefinition build() {
            return new ColumnDefinition(this);
        }

    }

    @Override
    public String convertDDL() {
        return super.convertDDL();
    }

}
