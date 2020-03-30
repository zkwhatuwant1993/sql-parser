package sql.parser;

import sql.parser.AbstractOperation;
import sql.parser.OperationKeyword;

/**
 * <p>Alter Database Statement enables you to change the overall characteristics of a database.
 * </p>
 *
 * @author zyy
 */
public class DatabaseAlterStatement extends AbstractOperation {

    private static final String KEYWORD = String.join(" ", OperationKeyword.ALTER.name(),
        OperationKeyword.DATABASE.name());

    /**
     * Changes a database with the given {@code databaseName}.
     */
    private String databaseName;

    private DatabaseAlterSpecification definition;

    private DatabaseAlterStatement(Builder builder) {
        this.databaseName = builder.databaseName;
        this.definition = builder.definition;
    }

    public static void main(String[] args) {
        DatabaseAlterStatement build = new Builder()
            .name("db")
            .specification(
                new DatabaseAlterSpecification.Builder()
                    .collate("b")
                    .charset("a")
                    .build()
            )
            .build();
        System.out.println(build.convertDDL());
    }

    @Override
    public String convertDDL() {
        this.getDdlJoiner()
            .add(KEYWORD)
            .add(databaseName)
            .add(definition.convertDDL());

        return super.convertDDL();
    }

    /**
     * DatabaseAlterStatement's Builder for support "method call chain"
     */
    public static class Builder {

        private String databaseName;
        private DatabaseAlterSpecification definition;

        /**
         * Gives database name that will be changed.
         *
         * @return {@link Builder} if this method is invoked
         * @see DatabaseAlterStatement#databaseName
         */
        public Builder name(String databaseName) {
            this.databaseName = databaseName;
            return this;
        }

        /**
         * Specify database alter specification.
         *
         * @param specification Database alter specification.
         * @return {@link Builder} if this method is invoked.
         */
        public Builder specification(DatabaseAlterSpecification specification) {
            this.definition = specification;
            return this;
        }


        /**
         * Build a "DatabaseCreateStatement"
         *
         * @return Database Create Statement
         * @see DatabaseAlterStatement
         * @throws DatabaseAlterException Alter exception.
         */
        public DatabaseAlterStatement build() throws DatabaseAlterException {
            if (databaseName == null) {
                throw new DatabaseAlterException("Database name is not specified.");
            }
            return new DatabaseAlterStatement(this);
        }
    }
}
