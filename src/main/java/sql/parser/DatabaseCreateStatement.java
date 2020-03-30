package sql.parser;

import sql.parser.AbstractOperation;
import sql.parser.OperationKeyword;

import java.util.StringJoiner;

/**
 * <p>Create Database Statement</p>
 *
 * @author zyy
 */
public class DatabaseCreateStatement extends AbstractOperation {

    private static final String KEYWORD = String.join(" ", OperationKeyword.CREATE.name(),
        OperationKeyword.DATABASE.name());

    private static final String IF_NOT_EXISTS_KEYWORD = String.join(" ", OperationKeyword.IF.name(),
        OperationKeyword.NOT.name(),
        OperationKeyword.EXISTS.name());

    /**
     * If and only if this variable is {@link Boolean#TRUE}, the "if not exists" option is specified.Default value is
     * {@link Boolean#FALSE}
     */
    private boolean ifNotExists;

    /**
     * Creates a database with the given {@code databaseName}.
     */
    private String databaseName;

    private DatabaseCreateSpecification definition;

    private DatabaseCreateStatement(Builder builder) {
        this.databaseName = builder.databaseName;
        this.ifNotExists = builder.ifNotExists;
        this.definition = builder.definition;
    }

    public static void main(String[] args) {
        DatabaseCreateStatement build = new Builder()
            .ifNotExists()
            .name("db")
            .specification(
                new DatabaseCreateSpecification.Builder()
                    .collate("UTF8_GENERAL_CI")
                    .charset("utf8")
                    .build()
            )
            .build();
        System.out.println(build.convertDDL());
    }

    @Override
    public String convertDDL() {
        StringJoiner joiner = this.getDdlJoiner();
        joiner.add(KEYWORD);
        if (ifNotExists) {
            joiner.add(IF_NOT_EXISTS_KEYWORD);
        }
        joiner.add(databaseName);
        joiner.add(definition.convertDDL());
        return super.convertDDL();
    }

    /**
     * DatabaseCreateStatement's Builder for support "method call chain"
     */
    public static class Builder {
        private boolean ifNotExists = false;
        private String databaseName;
        private DatabaseCreateSpecification definition;

        /**
         * Specify the "IF NOT EXISTS" option
         *
         * @return {@link Builder} if this method is invoked
         * @see DatabaseCreateStatement#ifNotExists
         */
        public Builder ifNotExists() {
            this.ifNotExists = true;
            return this;
        }

        /**
         * Gives database name that will be created.
         *
         * @return {@link Builder} if this method is invoked
         * @see DatabaseCreateStatement#databaseName
         */
        public Builder name(String databaseName) {
            this.databaseName = databaseName;
            return this;
        }

        /**
         * Specify database create specification.
         *
         * @param specification Create specification
         * @return {@link Builder} if this method is invoked
         */
        public Builder specification(DatabaseCreateSpecification specification) {
            this.definition = specification;
            return this;
        }


        /**
         * Build a "DatabaseCreateStatement"
         *
         * @return Database Create Statement
         * @see DatabaseCreateStatement
         */
        public DatabaseCreateStatement build() throws DatabaseCreateException {
            if (databaseName == null) {
                throw new DatabaseCreateException("Database name is not specified.");
            }
            return new DatabaseCreateStatement(this);
        }
    }
}
