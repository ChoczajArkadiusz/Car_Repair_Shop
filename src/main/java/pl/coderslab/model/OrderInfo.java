package pl.coderslab.model;

public class OrderInfo {
    public Employee employee;
    public Order order;
    public Vehicle vehicle;

    public OrderInfo() {
        this.employee = new Employee();
        this.order = new Order();
        this.vehicle = new Vehicle();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }


}
