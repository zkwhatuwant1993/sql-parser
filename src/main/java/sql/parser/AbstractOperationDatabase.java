package sql.parser;

import sql.parser.AbstractOperation;

/**
 * @author liwenhe
 * @date 2020-03-24 14:58
 */
public abstract class AbstractOperationDatabase extends AbstractOperation {

    private final String database;

    public AbstractOperationDatabase(String database) {
        this.database = database;
    }

    public String getDatabase() {
        return database;
    }

}
