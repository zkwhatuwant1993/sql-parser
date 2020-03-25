package sql.parser.table;

import sql.parser.enums.IndexType;

import java.util.List;

/**
 * @author liwenhe
 * @date 2020-03-25 13:27
 */
public class Index {

    private String name;

    private IndexType type;

    private Boolean isUnique;

    private List<String> fields;

    public Index(String name, IndexType type, Boolean isUnique, List<String> fields) {
        this.name = name;
        this.type = type;
        this.isUnique = isUnique;
        this.fields = fields;
    }

    public String getName() {
        return name;
    }

    public IndexType getType() {
        return type;
    }

    public Boolean isUnique() {
        return isUnique;
    }

    public List<String> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return "Index{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", isUnique=" + isUnique +
                ", fields=" + fields +
                '}';
    }

}
