package group3.assignment.model;

import java.io.Serializable;

public class BookCategory implements Serializable {
    private int idBookCategory;
    private String nameBookCategory;

    public BookCategory() {
    }

    public BookCategory(int idBookCategory, String nameBookCategory) {
        this.idBookCategory = idBookCategory;
        this.nameBookCategory = nameBookCategory;
    }

    public int getIdBookCategory() {
        return idBookCategory;
    }

    public void setIdBookCategory(int idBookCategory) {
        this.idBookCategory = idBookCategory;
    }

    public String getNameBookCategory() {
        return nameBookCategory;
    }

    public void setNameBookCategory(String nameBookCategory) {
        this.nameBookCategory = nameBookCategory;
    }

    @Override
    public String toString() {
        return "BookCategory{" +
                "idBookCategory=" + idBookCategory +
                ", nameBookCategory='" + nameBookCategory + '\'' +
                '}';
    }
}
