package cinema.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty("phone")
    @Column(name = "phone")
    private String phone;

    @JsonProperty("fullName")
    @Column(name = "fullname")
    private String fullName;

    @JsonProperty("row")
    @Column(name = "row")
    private String row;

    @JsonProperty("blockcolumn")
    @Column(name = "blockcolumn")
    private String blockcolumn;

    public Account(String phone, String fullName, String row, String blockcolumn) {
        this.phone = phone;
        this.fullName = fullName;
        this.row = row;
        this.blockcolumn = blockcolumn;
    }

    public Account() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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