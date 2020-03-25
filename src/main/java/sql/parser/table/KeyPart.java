package sql.parser.table;

import org.apache.commons.lang3.StringUtils;
import sql.parser.AbstractOperation;
import sql.parser.enums.AscendingType;
import sql.parser.exception.KeyPartException;

/**
 * @author liwenhe
 * @date 2020-03-25 16:47
 */
public class KeyPart extends AbstractOperation {

    private final static Integer DEFAULT_LENGTH = 765;

    private String column;

    private Integer length;

    private AscendingType ascending;

    private KeyPart(Builder builder) {
        this.column = builder.column;
        this.length = builder.length;
        this.ascending = builder.ascending;
    }

    public String getColumn() {
        if (StringUtils.isEmpty(this.column) || StringUtils.isBlank(this.column)) {
            throw new KeyPartException("KeyPart's column must be not empty or blank.");
        }
        return column;
    }

    public Integer getLength() {
        if (null != length && length > DEFAULT_LENGTH) {
            throw  new KeyPartException(String.format("KeyPart's length must be <= %d", DEFAULT_LENGTH));
        }
        return length;
    }

    public AscendingType getAscending() {
        return ascending;
    }

    public static class Builder {

        private String column;

        private Integer length;

        private AscendingType ascending;

        public Builder column(String coloumn) {
            this.column = coloumn;
            return this;
        }

        public Builder length(Integer length) {
            this.length = length;
            return this;
        }

        public Builder ascending(AscendingType ascending) {
            this.ascending = ascending;
            return this;
        }

        public KeyPart build() {
            return new KeyPart(this);
        }

    }

    @Override
    public String convertDDL() {
        this.getDdlJoiner().add(this.backQuoteString(this.getColumn()));
        if (null != this.getLength()) {
            this.getDdlJoiner().add(this.getLength().toString());
        }
        if (null != this.getAscending()) {
            this.getDdlJoiner().add(this.getAscending().name());
        }
        return this.getDdlJoiner().toString();
    }

}
