package group3.assignment.model;

import java.io.Serializable;

public class Book implements Serializable {
    private int idBook;
    private String name;
    private int rent;
    private int idBookCategory;

    public Book() {
    }

    public Book(int idBook, String name, int rent, int idBookCategory) {
        this.idBook = idBook;
        this.name = name;
        this.rent = rent;
        this.idBookCategory = idBookCategory;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getIdBookCategory() {
        return idBookCategory;
    }

    public void setIdBookCategory(int idBookCategory) {
        this.idBookCategory = idBookCategory;
    }

    @Override
    public String toString() {
        return "Book{" +
                "idBook=" + idBook +
                ", name='" + name + '\'' +
                ", rent=" + rent +
                ", idBookCategory=" + idBookCategory +
                '}';
    }
}
