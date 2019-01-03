package pl.coderslab;

import java.util.Date;

public class Order {
    private Integer id;
    private Date acceptanceDate;
    private Date scheduledStartDate;
    private Date startDate;
    private Integer employeeId;
    private String problemDescription;
    private String repairDescription;
    private String status;
    private Integer vehicleId;
    private Double manHours;
    private Double manHourCost;
    private Double partsCost;
    private Double costForCustomer;


    public Order() {
    }


    public Order(Date acceptanceDate, String problemDescription) {
        this.acceptanceDate = acceptanceDate;
        this.problemDescription = problemDescription;
    }


    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public Date getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(Date acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public Date getScheduledStartDate() {
        return scheduledStartDate;
    }

    public void setScheduledStartDate(Date scheduledStartDate) {
        this.scheduledStartDate = scheduledStartDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getRepairDescription() {
        return repairDescription;
    }

    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Double getManHours() {
        return manHours;
    }

    public void setManHours(Double manHours) {
        this.manHours = manHours;
    }

    public Double getManHourCost() {
        return manHourCost;
    }

    public void setManHourCost(Double manHourCost) {
        this.manHourCost = manHourCost;
    }

    public Double getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(Double partsCost) {
        this.partsCost = partsCost;
    }

    public Double getCostForCustomer() {
        return costForCustomer;
    }

    public void setCostForCustomer(Double costForCustomer) {
        this.costForCustomer = costForCustomer;
    }


}
