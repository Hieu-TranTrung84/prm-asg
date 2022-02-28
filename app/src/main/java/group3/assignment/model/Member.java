package group3.assignment.model;

import java.io.Serializable;

public class Member implements Serializable {
    private int idMember;
    private String name;
    private String dob;

    public Member(int idMember, String name, String dob) {
        this.idMember = idMember;
        this.name = name;
        this.dob = dob;
    }

    public Member() {
    }

    public int getIdMember() {
        return idMember;
    }

    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Member{" +
                "idMember=" + idMember +
                ", name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}
