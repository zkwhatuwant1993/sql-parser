package sql.parser.table;

import org.apache.commons.lang3.StringUtils;
import sql.parser.OperationKeyword;
import sql.parser.exception.DefaultValueException;
import sql.parser.exception.NumericException;

import java.sql.JDBCType;
import java.util.*;

/**
 * @author liwenhe
 * @date 2020-03-24 16:47
 */
public class OperationTableCreate extends AbstractOperationTable {

    private Map<String, FieldInfo> fields = new LinkedHashMap<>();

    private String engine = "InnoDB";

    private String comment;

    private List<Index> indexs;

    private Boolean ifNotExists = Boolean.TRUE;

    public OperationTableCreate(String database, String table) {
        super(database, table);
        this.getDdlJoiner().add(OperationKeyword.CREATE.name());
        this.getDdlJoiner().add(OperationKeyword.TABLE.name());
    }

    public OperationTableCreate ifNotExists(Boolean ifNotExists) {
        this.ifNotExists = ifNotExists;
        return this;
    }

    public OperationTableCreate fields(String fieldName, FieldInfo fieldInfo) {
        this.fields.put(fieldName, fieldInfo);
        return this;
    }

    public OperationTableCreate engine(String engine) {
        this.engine = engine;
        return this;
    }

    public OperationTableCreate comment(String comment) {
        this.comment = comment;
        return this;
    }

    public OperationTableCreate index(List<Index> indexs) {
        this.indexs = indexs;
        return this;
    }

    public OperationTableCreate foreignKey() {
        return this;
    }

    @Override
    public OperationTableCreate collate(String collate) {
        this.setCollate(collate);
        return this;
    }

    @Override
    public OperationTableCreate characterSet(String characterSet) {
        this.setCharacterSet(characterSet);
        return this;
    }

    @Override
    public String convertDDL() {
        if (ifNotExists) {
            this.getDdlJoiner().add(OperationKeyword.IF.name());
            this.getDdlJoiner().add(OperationKeyword.NOT.name());
            this.getDdlJoiner().add(OperationKeyword.EXISTS.name());
        }

        this.getDdlJoiner()
                .add(String.format(
                        "%s.%s"
                        , this.backQuoteString(this.getDatabase())
                        , this.backQuoteString(this.getTable())));

        this.getDdlJoiner().add(LT_BRACKET_STRING);

        StringJoiner primaryJoiner = new StringJoiner(", ");
        Iterator<Map.Entry<String, FieldInfo>> iterator = fields.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, FieldInfo> field = iterator.next();
            this.getDdlJoiner().add(this.backQuoteString(field.getKey()));

            FieldType fieldType = field.getValue().getType();
            switch (fieldType.getType()) {
                case TINYINT:
                case SMALLINT:
                case INTEGER:
                case BIGINT:
                case BIT:
                case CHAR:
                case BINARY:
                case VARBINARY:
                case VARCHAR:
                    if (fieldType.getPrecision() == null) {
                        this.getDdlJoiner().add(fieldType.getType().getName());
                    } else {
                        this.getDdlJoiner().add(
                                String.format(
                                        "%s(%d)"
                                        , fieldType.getType().getName()
                                        , fieldType.getPrecision()));
                    }
                    break;
                case FLOAT:
                case DOUBLE:
                    if (fieldType.getPrecision() == null || fieldType.getScale() == null) {
                        throw new NumericException("For double(M, D), M and D must be not null.");
                    }
                case DECIMAL:
                case NUMERIC:
                    if (fieldType.getPrecision() == null) {
                        throw new NumericException("For float(M,D), double(M,D) or decimal(M,D), M must be not null.");
                    }

                    if (fieldType.getPrecision() != null
                            && fieldType.getScale() != null
                            && fieldType.getPrecision() < fieldType.getScale()) {
                        throw new NumericException("For float(M,D), double(M,D) or decimal(M,D), M must be >= D.");
                    }

                    if (fieldType.getScale() != null) {
                        this.getDdlJoiner().add(
                                String.format(
                                        "%s(%d,%d)"
                                        , fieldType.getType().getName()
                                        , fieldType.getPrecision()
                                        , fieldType.getScale()));
                    } else {
                        this.getDdlJoiner().add(
                                String.format(
                                        "%s(%d)"
                                        , fieldType.getType().getName()
                                        , fieldType.getPrecision()));
                    }
                    break;
                default:
                    this.getDdlJoiner().add(fieldType.getType().getName());
            }

            if (!field.getValue().isNullable()) {
                this.getDdlJoiner().add(OperationKeyword.NOT.name());
                this.getDdlJoiner().add(OperationKeyword.NULL.name());
            }

            if (!field.getValue().isAutoIncrement()) {
                this.getDdlJoiner().add(OperationKeyword.AUTO_INCREMENT.name());
            }

            if (!StringUtils.isEmpty(field.getValue().getDefaultValue())) {
                this.getDdlJoiner().add(OperationKeyword.DEFAULT.name());
                switch (fieldType.getType()) {
                    case TINYINT:
                    case SMALLINT:
                    case INTEGER:
                    case BIGINT:
                    case BIT:
                    case BINARY:
                    case VARBINARY:
                    case FLOAT:
                    case DOUBLE:
                    case DECIMAL:
                    case NUMERIC:
                        this.getDdlJoiner().add(field.getValue().getDefaultValue());
                        break;
                    case CHAR:
                    case VARCHAR:
                        this.getDdlJoiner().add(String.format("'%s'", field.getValue().getDefaultValue()));
                        break;
                    default:
                        throw new DefaultValueException("BLOB, TEXT, GEOMETRY or JSON column can't have a default value");
                }
            }

            if (!StringUtils.isEmpty(field.getValue().getComment())) {
                this.getDdlJoiner().add(OperationKeyword.COMMENT.name());
                this.getDdlJoiner().add(String.format("'%s'", field.getValue().getComment()));
            }

            if (field.getValue().isPrimaryKey()) {
                primaryJoiner.add(this.backQuoteString(field.getKey()));
            }

            if (!iterator.hasNext()) {
                if (primaryJoiner.length() != 0) {
                    this.getDdlJoiner().add(COMMA_STRING);
                    this.getDdlJoiner().add(OperationKeyword.CONSTRAINT.name());
                    this.getDdlJoiner().add(OperationKeyword.PRIMARY.name());
                    this.getDdlJoiner().add(OperationKeyword.KEY.name());
                    this.getDdlJoiner().add(LT_BRACKET_STRING);
                    this.getDdlJoiner().merge(primaryJoiner);
                    this.getDdlJoiner().add(RT_BRACKET_STRING);
                }
            } else {
                this.getDdlJoiner().add(COMMA_STRING);
            }
        }

        if (null != this.indexs) {
            for (Index index : this.indexs) {
                StringJoiner indexFieldJoiner = new StringJoiner(", ");
                for (String indexField : index.getFields()) {
                    indexFieldJoiner.add(this.backQuoteString(indexField));
                }

                this.getDdlJoiner().add(COMMA_STRING);
                if (index.isUnique()) {
                    this.getDdlJoiner().add(OperationKeyword.UNIQUE.name());
                }
                this.getDdlJoiner().add(OperationKeyword.INDEX.name());
                this.getDdlJoiner().add(index.getName());
                this.getDdlJoiner().add(LT_BRACKET_STRING);
                this.getDdlJoiner().merge(indexFieldJoiner);
                this.getDdlJoiner().add(RT_BRACKET_STRING);
            }
        }

        this.getDdlJoiner().add(RT_BRACKET_STRING);

        this.getDdlJoiner().add(OperationKeyword.ENGINE.name()).add(this.engine);
        this.getDdlJoiner().add(OperationKeyword.DEFAULT.name());
        this.getDdlJoiner().add(OperationKeyword.CHARACTER.name());
        this.getDdlJoiner().add(OperationKeyword.SET.name());
        this.getDdlJoiner().add(this.getCharacterSet());

        return this.getDdlJoiner().toString();
    }

    public static void main(String[] args) {
        System.out.println(
                new OperationTableCreate("user1", "user1")
                .fields("id", new FieldInfo(
                        new FieldType(JDBCType.VARCHAR, 64, null)
                        , false, true, true, null, "编号"))
                        .fields("username", new FieldInfo(
                                new FieldType(JDBCType.VARCHAR, 64, null)
                                , false, true, false, "liwenhe", "用户名"))
                        .fields("money", new FieldInfo(
                                new FieldType(JDBCType.DECIMAL, 6, 2)
                                , false, true, false, null, "金额"))
                        .index(new IndexBuilder()
                                .index("username_idx", true, "username")
                                .index("money_idx", "money")
                                .index("username_money_idx", true, "username", "money")
                                .build())
                .convertDDL());
    }

}
