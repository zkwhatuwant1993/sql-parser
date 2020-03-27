package sql.parser.column;

/**
 * Specify whether the column is stored on disk or in memory by using a STORAGE clause.
 *
 * <ul>
 *     <li>{@link ColumnStorageType#DISK} causes the column to be stored on disk</li>
 *     <li>{@link ColumnStorageType#MEMORY} causes in-memory storage to be used</li>
 * </ul>
 *
 * @author liwenhe
 */
public enum ColumnStorageType {

    DISK,
    MEMORY

}
