package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Place {

    @JsonProperty("row")
    String row;

    @JsonProperty("column")
    String column;

    public Place(String row, String column) {
        this.row = row;
        this.column = column;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }
}
