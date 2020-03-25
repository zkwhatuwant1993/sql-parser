package sql.parser.table.index;

import sql.parser.AbstractOperation;
import sql.parser.table.KeyPart;

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

    public static void main(String[] args) {
        System.out.println(new UniqueIndexDefinition
                .Builder()
                .name("test1213")
                .keyPart(new KeyPart.Builder().column("user").build())
                .build()
                .convertDDL()
        );
    }

}
