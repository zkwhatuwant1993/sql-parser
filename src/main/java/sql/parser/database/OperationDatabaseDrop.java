package sql.parser.database;

import sql.parser.OperationKeyword;

/**
 * @author liwenhe
 * @date 2020-03-24 15:25
 */
public class OperationDatabaseDrop extends AbstractOperationDatabase {

    private Boolean ifExists = Boolean.TRUE;

    public OperationDatabaseDrop(String database) {
        super(database);
        this.getDdlJoiner().add(OperationKeyword.DROP.name());
        this.getDdlJoiner().add(OperationKeyword.DATABASE.name());
    }

    public OperationDatabaseDrop ifExists(Boolean ifExists) {
        this.ifExists = ifExists;
        return this;
    }

    @Override
    public String convertDDL() {
        if (ifExists) {
            this.getDdlJoiner().add(OperationKeyword.IF.name());
            this.getDdlJoiner().add(OperationKeyword.EXISTS.name());
        }

        this.getDdlJoiner().add(this.backQuoteString(this.getDatabase()));

        return this.getDdlJoiner().toString();
    }

}
