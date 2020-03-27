package sql.parser.column;

/**
 * Permissible column formats are FIXED, DYNAMIC, and DEFAULT.
 *
 * <ul>
 *     <li>{@link ColumnFormatType#FIXED} is used to specify fixed-width storage</li>
 *     <li>{@link ColumnFormatType#DYNAMIC} permits the column to be variable-width</li>
 *     <li>
 *         {@link ColumnFormatType#DEFAULT} causes the column to use fixed-width or variable-width storage as determined
 *     by the column's data type (possibly overridden by a ROW_FORMAT specifier)
 *     </li>
 * </ul>
 *
 * @author liwenhe
 */
public enum ColumnFormatType {

    FIXED,
    DYNAMIC,
    DEFAULT

}
