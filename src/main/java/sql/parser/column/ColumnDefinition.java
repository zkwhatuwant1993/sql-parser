package sql.parser.column;

import org.apache.commons.lang3.StringUtils;
import sql.parser.AbstractOperation;
import sql.parser.CharacterSet;
import sql.parser.OperationKeyword;
import sql.parser.datatype.DataTypeDefinition;

/**
 * <p>Column Data Type and Attributes of definition.</p>
 * <p>{@link ColumnDefinition} can not be initialized if you want to get ColumnDefinition.</p>
 * <p>But you can initialize it by {@link ColumnDefinition.Builder}.</p>
 *
 * for example:
 * <pre>
 * new ColumnDefinition.Builder().build()
 * new ColumnDefinition.Builder().name("xx").build()
 * </pre>
 *
 * @author liwenhe
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

        /**
         * <p>{@link Builder#build()} build a {@link ColumnDefinition} class</p>
         * <p>A few parameters be initialized, for example:</p>
         * <pre>
         * this.isNullable = Boolean.TRUE;
         * this.isPrimary = Boolean.FALSE;
         * this.isAutoIncrementable = Boolean.FALSE;
         * this.isUnique = Boolean.FALSE;
         * this.formatType = ColumnFormatType.DEFAULT;
         * this.storageType = ColumnStorageType.DISK;
         * </pre>
         */
        public Builder() {
            this.isNullable = Boolean.TRUE;
            this.isPrimary = Boolean.FALSE;
            this.isAutoIncrementable = Boolean.FALSE;
            this.isUnique = Boolean.FALSE;
        }

        /**
         * <p>The column name of definition.</p>
         *
         * <p>
         * The column name must be not empty or blank
         * if the column name is empty or blank, then throw a exception {@link ColumnDefinitionException}.
         * </p>
         *
         * @param name column name
         * @return {@link Builder} if this method is invoked
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * <p>The column type of definition.</p>
         *
         * <p>
         * The column type must be not null
         * if the column type is empty or blank, then throw a exception {@link ColumnDefinitionException}.
         * </p>
         *
         * @param dataType data_type represents the data type in a column definition
         * @return {@link Builder} if this method is invoked
         */
        public Builder dataType(DataTypeDefinition dataType) {
            this.dataType = dataType;
            return this;
        }

        /**
         * <p>The column attribute of definition.</p>
         *
         * <p>
         * if you don't use this method, the attribute 'isNullable' set default value to {@link Boolean#TRUE}
         * </p>
         *
         * @param isNullable column attribute
         * @return {@link Builder} if this method is invoked
         */
        public Builder isNullable(Boolean isNullable) {
            this.isNullable = isNullable;
            return this;
        }

        /**
         * <p>The column attribute of definition.</p>
         *
         * <p>An integer or floating-point column can have the additional attribute AUTO_INCREMENT</p>
         * <p>
         * When you insert a value of NULL (recommended) or 0 into an indexed AUTO_INCREMENT column,
         * the column is set to the next sequence value.
         * </p>
         * <p>if you don't use this method, the attribute 'isAutoIncrementable' set default value to {@link Boolean#FALSE}</p>
         *
         * @param isAutoIncrementable column attribute
         * @return {@link Builder} if this method is invoked
         */
        public Builder isAutoIncrementable(Boolean isAutoIncrementable) {
            this.isAutoIncrementable = isAutoIncrementable;
            return this;
        }

        /**
         * <p>The column attribute of definition.</p>
         *
         * <p>
         * if you don't use this method, the attribute 'isUnique' set default value to {@link Boolean#FALSE}
         * </p>
         *
         * @param isUnique column attribute
         * @return {@link Builder} if this method is invoked
         */
        public Builder isUnique(Boolean isUnique) {
            this.isUnique = isUnique;
            return this;
        }

        /**
         * <p>The column attribute of definition.</p>
         *
         * <p>
         * if you don't use this method, the attribute 'isPrimary' set default value to {@link Boolean#FALSE}
         * </p>
         *
         * @param isPrimary column attribute
         * @return {@link Builder} if this method is invoked
         */
        public Builder isPrimary(Boolean isPrimary) {
            this.isPrimary = isPrimary;
            return this;
        }

        /**
         * <p>The column attribute of definition.</p>
         *
         * <p>A comment for a column can be specified with the COMMENT option, up to 1024 characters long.</p>
         *
         * @param comment column attribute
         * @return {@link Builder} if this method is invoked
         */
        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder collate(String collate) {
            this.collate = collate;
            return this;
        }

        /**
         * <p>The column attribute of definition.</p>
         *
         * <p>In NDB Cluster, it is also possible to specify a data storage
         * format for individual columns of NDB tables using COLUMN_FORMAT.
         * </p>
         *
         * <p>For NDB tables, the default value for COLUMN_FORMAT is FIXED.</p>
         * <p>COLUMN_FORMAT currently has no effect on columns of tables using storage engines other than NDB.
         * MySQL 8.0 silently ignores COLUMN_FORMAT.</p>
         *
         * @param formatType column attribute
         * @return {@link Builder} if this method is invoked
         */
//        public Builder formatType(ColumnFormatType formatType) {
//            this.formatType = formatType;
//            return this;
//        }

        /**
         * <p>The column attribute of definition.</p>
         *
         * <p>The STORAGE clause has no effect on tables using storage engines other than NDB.</p>
         * <p>The STORAGE keyword is supported only in the build of mysqld that is supplied with NDB Cluster.</p>
         * <p>It is not recognized in any other version of MySQL, where any attempt to use the STORAGE keyword causes a syntax error.</p>
         *
         * @param storageType column attribute
         * @return {@link Builder} if this method is invoked
         */
//        public Builder storageType(ColumnStorageType storageType) {
//            this.storageType = storageType;
//            return this;
//        }

        public ColumnDefinition build() {
            return new ColumnDefinition(this);
        }

    }

    @Override
    public String convertDDL() {
        if (StringUtils.isEmpty(this.name) || StringUtils.isBlank(this.name)) {
            throw new ColumnDefinitionException("Column 'name' must be not empty or blank");
        }

        if (null != dataType) {
            throw new ColumnDefinitionException("Column 'dataType' must be not null");
        }

        this.getDdlJoiner().add(this.backQuoteString(this.name));
        this.getDdlJoiner().add(this.dataType.convertDDL());

        if (this.isNullable) {
            this.getDdlJoiner().add(OperationKeyword.NULL.name());
        } else {
            this.getDdlJoiner().add(OperationKeyword.NOT.name());
            this.getDdlJoiner().add(OperationKeyword.NULL.name());
        }

        if (this.isAutoIncrementable) {
            this.getDdlJoiner().add(OperationKeyword.AUTO_INCREMENT.name());
        }

        if (this.isUnique) {
            this.getDdlJoiner().add(OperationKeyword.UNIQUE.name());
            this.getDdlJoiner().add(OperationKeyword.KEY.name());
        }

        if (this.isPrimary) {
            this.getDdlJoiner().add(OperationKeyword.PRIMARY.name());
            this.getDdlJoiner().add(OperationKeyword.KEY.name());
        }

        if (!StringUtils.isEmpty(this.comment)) {
            this.getDdlJoiner().add(OperationKeyword.COMMENT.name());
            this.getDdlJoiner().add(this.comment);
        }

        if (CharacterSet.exists(this.collate)) {
            this.getDdlJoiner().add(OperationKeyword.COLLATE.name());
            this.getDdlJoiner().add(this.collate.toUpperCase());
        }

        return super.convertDDL();
    }

}
