package sql.parser.table;

import sql.parser.enums.IndexType;
import sql.parser.exception.IndexException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liwenhe
 * @date 2020-03-25 13:31
 */
public class IndexBuilder {

    private final List<Index> indexs = new ArrayList<>();

    public IndexBuilder index(String name, String... field) {
        return index(name, IndexType.BTREE, Boolean.FALSE, field);
    }

    public IndexBuilder index(String name, Boolean isUnique, String... field) {
        return index(name, IndexType.BTREE, isUnique, field);
    }

    public IndexBuilder index(String name, IndexType type, Boolean isUnique, String... field) {
        if (null == field || field.length == 0) {
            throw new IndexException("Index's field must be null or empty");
        }

        indexs.add(new Index(name, type, isUnique, Arrays.asList(field)));

        return this;
    }

    public List<Index> build() {
        return indexs;
    }

    public static void main(String[] args) {
        List<Index> indices = new IndexBuilder()
                .index("sdafsdaf", Boolean.FALSE, "id", "username")
                .index("test", "username")
                .build();

        System.out.println(indices.toString());
    }

}
