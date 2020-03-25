package sql.parser.database;

import sql.parser.OperationKeyword;

/**
 * @author liwenhe
 * @date 2020-03-24 15:11
 */
public class OperationDatabaseCreate extends AbstractOperationDatabase {

    private Boolean ifNotExists = Boolean.TRUE;

    public OperationDatabaseCreate(String database) {
        super(database);
        this.getDdlJoiner().add(OperationKeyword.CREATE.name());
        this.getDdlJoiner().add(OperationKeyword.DATABASE.name());
    }

    public OperationDatabaseCreate collate(String collate) {
        this.setCollate(collate);
        return this;
    }

    public OperationDatabaseCreate characterSet(String characterSet) {
        this.setCharacterSet(characterSet);
        return this;
    }

    public OperationDatabaseCreate ifNotExists(Boolean ifNotExists) {
        this.ifNotExists = ifNotExists;
        return this;
    }

    @Override
    public String convertDDL() {
        if (ifNotExists) {
            this.getDdlJoiner().add(OperationKeyword.IF.name());
            this.getDdlJoiner().add(OperationKeyword.NOT.name());
            this.getDdlJoiner().add(OperationKeyword.EXISTS.name());
        }

        this.getDdlJoiner().add(this.backQuoteString(this.getDatabase()));

        this.getDdlJoiner().add(OperationKeyword.CHARACTER.name());
        this.getDdlJoiner().add(OperationKeyword.SET.name());
        this.getDdlJoiner().add(this.getCharacterSet());

        this.getDdlJoiner().add(OperationKeyword.COLLATE.name());
        this.getDdlJoiner().add(this.getCollate());

        return this.getDdlJoiner().toString();
    }

}
