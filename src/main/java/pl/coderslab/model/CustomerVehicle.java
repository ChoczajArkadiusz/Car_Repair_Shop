package pl.coderslab.model;

public class CustomerVehicle {
    private long id;
    private long customerId;
    private long vehicleId;


    public CustomerVehicle() {
    }

    public CustomerVehicle(long customerId, long vehicleId) {
        this.customerId = customerId;
        this.vehicleId = vehicleId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }


}
