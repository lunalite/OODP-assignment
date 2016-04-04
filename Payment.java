package cz2002_assignment;

public class Payment {

    private double totalBill;
    private double roomServiceBill;
    private double roomChargesBill;

    Payment() {

    }

    public double getRoomServiceBill() {return roomServiceBill;}
    public double getRoomCharges() {return roomChargesBill;}
    public double getTotalBill() {return totalBill;}
    public void setRoomServiceBill(double billAdd) { roomServiceBill += billAdd;}

}
