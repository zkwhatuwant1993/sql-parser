package sql.parser.index;

import sql.parser.AbstractOperation;

public abstract class AbstractIndexDefinition extends AbstractOperation {

    protected final static String INDEX = "INDEX";

    protected abstract String type();

    protected abstract String ddl();

    @Override
    public String convertDDL() {
        if (null != this.type()) {
            this.getDdlJoiner().add(type());
        }
        this.getDdlJoiner().add(INDEX);
        return this.ddl();
    }

}
