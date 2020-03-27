package sql.parser;

import sql.parser.column.ColumnDefinition;
import sql.parser.index.IndexDefinition;
import sql.parser.primary.PrimaryKeyDefinition;
import sql.parser.reference.ReferenceDefinition;

/**
 * @author liwenhe
 */
public class TableCreateDefinition extends AbstractOperation {

    private ColumnDefinition columnDefinition;

    private IndexDefinition indexDefinition;

    private PrimaryKeyDefinition primaryKeyDefinition;

    private ReferenceDefinition referenceDefinition;

}
