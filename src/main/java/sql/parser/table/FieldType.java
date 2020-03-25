package sql.parser.table;

import java.sql.JDBCType;

/**
 * @author liwenhe
 * @date 2020-03-24 17:56
 */
public class FieldType {

    private JDBCType type;

    private Integer precision;

    private Integer scale;

    public FieldType(JDBCType type, Integer length, Integer scale) {
        this.type = type;
        this.precision = length;
        this.scale = scale;
    }

    public JDBCType getType() {
        return type;
    }

    public Integer getPrecision() {
        return precision;
    }

    public Integer getScale() {
        return scale;
    }

}
