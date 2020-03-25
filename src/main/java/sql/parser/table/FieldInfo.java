package sql.parser.table;

/**
 * @author liwenhe
 * @date 2020-03-24 17:45
 */
public class FieldInfo {

    private FieldType type;

    private Boolean isNullable;

    private Boolean isAutoIncrement;

    private Boolean isPrimaryKey;

    private String defaultValue;

    private String comment;

    public FieldInfo(FieldType type, Boolean isNullable, Boolean isAutoIncrement, Boolean isPrimaryKey
            , String defaultValue, String comment) {
        this.type = type;
        this.isNullable = isNullable;
        this.isAutoIncrement = isAutoIncrement;
        this.isPrimaryKey = isPrimaryKey;
        this.defaultValue = defaultValue;
        this.comment = comment;
    }

    public FieldType getType() {
        return type;
    }

    public Boolean isNullable() {
        return isNullable;
    }

    public Boolean isAutoIncrement() {
        return isAutoIncrement;
    }

    public Boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getComment() {
        return comment;
    }

}
