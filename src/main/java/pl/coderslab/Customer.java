package pl.coderslab;

import java.sql.Date;

public class Customer {
    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthDate;


    public Customer() {
    }


    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public Customer(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }


    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


}
