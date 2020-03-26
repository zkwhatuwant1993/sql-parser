package sql.parser;

import sql.parser.AbstractOperation;

/**
 * @author liwenhe
 * @date 2020-03-24 14:57
 */
public abstract class AbstractOperationTable extends AbstractOperation {

    private String database;

    private String table;

    public AbstractOperationTable(String database, String table) {
        this.database = database;
        this.table = table;
    }

    protected String getDatabase() {
        return database;
    }

    protected String getTable() {
        return table;
    }

}
