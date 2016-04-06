package cz2002_assignment;

public class Payment {

    private double totalBill;
    private double roomServiceBill;
    private double roomChargesBill;

    Payment() {
        totalBill = 0;
        roomServiceBill = 0;
        roomChargesBill = 0;
    }

    public void printBillInvoice(){
        // print bill invoice (with breakdowns on days of stay, room service order items and its total, tax and total amount)
    }
    public double getRoomServiceBill() {return roomServiceBill;}
    public double getRoomCharges() {return roomChargesBill;}
    public double getTotalBill() {
        return totalBill; //For GST in Singapore
    }
    public void setRoomServiceBill(double billAdd) { roomServiceBill += billAdd;}
    public void setRoomChargesBill(){}

}
