package sql.parser;

import sql.parser.AbstractOperation;
import sql.parser.OperationKeyword;

import java.util.StringJoiner;

/**
 * Drops database statement means drops all tables in the database and deletes the database.
 *
 * @author zyy
 */
public class DatabaseDropStatement extends AbstractOperation {

    private static final String KEYWORD = String.join(" ", OperationKeyword.DROP.name(),
        OperationKeyword.DATABASE.name());

    private static final String IF_EXISTS_KEYWORD = String.join(" ", OperationKeyword.IF.name(),
        OperationKeyword.EXISTS.name());

    /**
     * The database will be dropped with given databaseName, If not specified, call constructors will cause
     * {@link DatabaseDropException}
     */
    private String databaseName;

    /**
     * If and only if this variable is {@link Boolean#TRUE}, the "if exists" option is specified.Default value is
     * {@link Boolean#FALSE}
     */
    private boolean ifExists;

    private DatabaseDropStatement(Builder builder) {
        this.databaseName = builder.databaseName;
        this.ifExists = builder.ifExists;
    }

    public static void main(String[] args) {
        DatabaseDropStatement build = new Builder()
            .ifExists()
            .name("dbName")
            .build();

        System.out.println(build.convertDDL());
    }

    @Override
    public String convertDDL() {
        StringJoiner ddlJoiner = this.getDdlJoiner().add(KEYWORD);
        if (ifExists) {
            ddlJoiner.add(IF_EXISTS_KEYWORD);
        }
        ddlJoiner.add(databaseName);
        return super.convertDDL();
    }

    /**
     * DatabaseCreateStatement's Builder for support "method call chain"
     */
    public static class Builder {

        private String databaseName;
        private boolean ifExists = false;

        public Builder() {
        }

        /**
         * Specify the database name that will be dropped.
         *
         * @param databaseName database name
         * @return {@link Builder}
         */
        public Builder name(String databaseName) {
            this.databaseName = databaseName;
            return this;
        }

        /**
         * Specify the "IF EXISTS" option.
         *
         * @return {@link Builder}
         */
        public Builder ifExists() {
            this.ifExists = true;
            return this;
        }

        /**
         * Build a "DatabaseDropStatement"
         *
         * @return Database Drop Statement
         * @see DatabaseDropStatement
         */
        public DatabaseDropStatement build() throws DatabaseDropException {
            if (databaseName == null) {
                throw new DatabaseDropException("Database name is not specified. ");
            }
            return new DatabaseDropStatement(this);
        }
    }
}
