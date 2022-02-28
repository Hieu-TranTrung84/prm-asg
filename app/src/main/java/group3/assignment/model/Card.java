package group3.assignment.model;

import java.io.Serializable;
import java.util.Date;

public class Card implements Serializable {
    private int idCard;
    private String idEmployee;
    private int idMember;
    private int idBook;
    private Date date;
    private int price;
    private int returnBook;

    public Card() {
    }

    public Card(int idCard, String idEmployee, int idMember, int idBook, Date date, int price, int returnBook) {
        this.idCard = idCard;
        this.idEmployee = idEmployee;
        this.idMember = idMember;
        this.idBook = idBook;
        this.date = date;
        this.price = price;
        this.returnBook = returnBook;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public int getIdMember() {
        return idMember;
    }

    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getReturnBook() {
        return returnBook;
    }

    public void setReturnBook(int returnBook) {
        this.returnBook = returnBook;
    }

    @Override
    public String toString() {
        return "Card{" +
                "idCard=" + idCard +
                ", idEmployee='" + idEmployee + '\'' +
                ", idMember=" + idMember +
                ", idBook=" + idBook +
                ", date=" + date +
                ", price=" + price +
                ", returnBook=" + returnBook +
                '}';
    }
}
