package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "sold_tickets")
public class Ticket {
    @Column(name = "show_id")
    private String movieName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "row")
    private int    row;

    @Column(name = "col")
    private int    col;

    @Column(name = "price")
    private int    price;

    public Ticket() {
    }

    public Ticket(String movieName, String username, int row, int col, int price) {
        this.movieName = movieName;
        this.userName = username;
        this.row = row;
        this.col = col;
        this.price = price;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
