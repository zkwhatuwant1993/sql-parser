package sql.parser;

import sql.parser.AbstractOperation;
import sql.parser.OperationKeyword;
import sql.parser.column.ColumnDefinition;

/**
 * <p>
 * Database alter specification options specify database characteristics.
 *
 * </p>
 *
 * @author zyy
 */
public class DatabaseAlterSpecification extends AbstractOperation {


    private static final String CHARACTER_SET_KEYWORD = String.join(" ", OperationKeyword.DEFAULT.name(),
        OperationKeyword.CHARACTER.name(), OperationKeyword.SET.name());

    private static final String COLLATE_KEYWORD = String.join(" ", OperationKeyword.DEFAULT.name(),
        OperationKeyword.COLLATE.name());

    /**
     * <p>This variable to represent optional CHARACTER SET clause of the Database alter Specification.
     * The CHARACTER SET clause specifies the default database character set.
     * </p>
     *
     * <p>If {@code collationName} is not null, followed clause is specified:</p>
     * <pre>
     *     DEFAULT CHARACTER SET = charsetName
     * </pre>
     */
    private String charsetName;

    /**
     * <p>This variable to represent optional COLLATE clause of the Database alter Specification.
     * The COLLATE clause specifies the default database collation.
     * </p>
     *
     * <p>If {@code collationName} is not null, followed clause is specified:<p/>
     * <pre>
     *     DEFAULT COLLATE = collationName
     * </pre>
     */
    private String collationName;

    private DatabaseAlterSpecification(Builder builder) {
        this.charsetName = builder.charsetName;
        this.collationName = builder.collationName;
    }

    @Override
    public String convertDDL() {
        if (!CharacterSet.exists(charsetName, collationName)) {
            throw new CharacterSetException(String.format("Charset or collation does not support. Input charset is " +
                    "%s, collation is %s", charsetName,
                collationName));
        }
        return this.getDdlJoiner()
            .add(CHARACTER_SET_KEYWORD)
            .add(charsetName)
            .add(COLLATE_KEYWORD)
            .add(collationName)
            .toString();
    }

    /**
     * DatabaseAlterDefinition's Builder for support "method call chain"
     */
    public static class Builder {
        private String charsetName;
        private String collationName;

        public Builder() {

        }

        /**
         * <p>The CHARACTER SET clause of the Database alter Specification.</p>
         *
         * @param charsetName charsetName represents the charset name in CHARACTER SET clause.
         * @return {@link ColumnDefinition.Builder} if this method is invoked
         * @see DatabaseAlterSpecification#charsetName
         */
        public Builder charset(String charsetName) {
            this.charsetName = charsetName;
            return this;
        }

        /**
         * <p>The CHARACTER SET clause of the Database alter Specification.</p>
         *
         * @param collationName collationName represents the collation name in COLLATE clause.
         * @return {@link ColumnDefinition.Builder} if this method is invoked
         * @see DatabaseAlterSpecification#collationName
         */
        public Builder collate(String collationName) {
            this.collationName = collationName;
            return this;
        }

        /**
         * Build a "Database alter Definition"
         *
         * @return Database alter definition
         * @see DatabaseAlterSpecification
         */
        public DatabaseAlterSpecification build() {
            return new DatabaseAlterSpecification(this);
        }
    }
}
