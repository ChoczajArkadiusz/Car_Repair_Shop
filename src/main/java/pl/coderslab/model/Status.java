package pl.coderslab.model;

public class Status {
    private static String currentStatus;


    public Status() {
        currentStatus = "accepted";
    }

    protected static void changeToRepairCostApproved() {
        currentStatus = "Repair cost approved";
    }

    protected static void changeToInRepair() {
        currentStatus = "In Repair";
    }

    protected static void changeToReadyForHandOver() {
        currentStatus = "Ready for hand over";
    }

    protected static void changeToCanceled() {
        currentStatus = "Canceled";
    }

    protected static void changeToRepairedAndHandedOver() {
        currentStatus = "Rapaired and handed over to the customer";
    }

}
