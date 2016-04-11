package cz2002_assignment;

public class Payment {

    private double taxes = 0.07; // GST
    private double totalBill;
    private double roomServiceBill;
    private double roomChargesBill;
    
    Payment() {
        totalBill = 0;
        roomServiceBill = 0;
        roomChargesBill = 0;
    }

    public double getRoomServiceBill() {return roomServiceBill;}
    public double getRoomCharges() {return roomChargesBill;}
    public double getTotalBill() {
        return totalBill; 
    }
    public double getTaxes() {return totalBill*taxes;}
    
    public void addRoomServiceBill(double billAdd) { roomServiceBill += billAdd;}
    public void calRoomChargesBill(String roomNo, int CID, int COD){
        Room r = RoomMgr.getRoom(roomNo);
        for (int i = CID; i <= COD; i ++) {
            double rate = r.getStatusCalendar(i).getRate();
            double roomTypeRate = r.getRoomType().getRate();
            roomChargesBill += rate * roomTypeRate;
        }
    }

}
