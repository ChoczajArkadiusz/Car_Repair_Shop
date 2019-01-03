package pl.coderslab;

public class Employee {
    private Integer id;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String note;
    private Double manHourCost;


    public Employee() {
    }

    public Employee(String firstName, String lastName, String address, String phone, String note, Double manHoursCost) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.note = note;
        this.manHourCost = manHoursCost;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getManHourCost() {
        return manHourCost;
    }

    public void setManHourCost(Double manHourCost) {
        this.manHourCost = manHourCost;
    }


}
