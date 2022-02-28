package group3.assignment.model;

import java.io.Serializable;

public class Employee implements Serializable {
    private String idEmployee;
    private String name;
    private String password;

    public Employee() {
    }

    public Employee(String idEmployee, String name, String password) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.password = password;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "idEmployee='" + idEmployee + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
