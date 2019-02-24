package cinema.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Place {

    @JsonProperty("row")
    private String row;

    @JsonProperty("blockcolumn")
    private String blockcolumn;

    public Place(String row, String column) {
        this.row = row;
        this.blockcolumn = column;
    }

    public Place() {
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getBlockcolumn() {
        return blockcolumn;
    }

    public void setBlockcolumn(String blockcolumn) {
        this.blockcolumn = blockcolumn;
    }
}
