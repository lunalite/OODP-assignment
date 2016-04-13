package cz2002_assignment;

public class Payment {

    /**
     * The taxes charged for the payment
     */
    private double taxes = 0.07; // GST
    
    /**
     * The total bill
     */
    private double totalBill;
    
    /**
     * The bill for room services
     */
    private double roomServiceBill;
    
    /**
     * The bill for the room
     */
    private double roomChargesBill;
    
    /**
     * Default constructor
     */
    Payment() {
        totalBill = 0;
        roomServiceBill = 0;
        roomChargesBill = 0;
    }

    /**
     * Get room services bill
     * @return roomServiceBill
     */
    public double getRoomServiceBill() {return roomServiceBill;}
    
    /**
     * Get the bill for the room
     * @return roomChargesBill
     */
    public double getRoomCharges() {return roomChargesBill;}
    
    /**
     * Get the total bill
     * @return totalBill
     */
    public double getTotalBill() {
        totalBill = roomServiceBill + roomChargesBill;
        return totalBill;
    }
    
    /**
     * Get the amount of taxes
     * @return totalBill*taxes
     */
    public double getTaxes() {return totalBill*taxes;}
    
    /**
     * Add room service bill to room charges
     * @param billAdd The bill to add onto the room charges
     */
    public void addRoomServiceBill(double billAdd) { roomServiceBill += billAdd;}
    
    /**
     * Calculates the room charges
     * @param roomNo The room to get the rates
     * @param CID The check in date to start charging
     * @param COD The check out date to stop charging
     */
    public void calRoomChargesBill(String roomNo, int CID, int COD){
        Room r = RoomMgr.getRoom(roomNo);
        for (int i = CID; i <= COD; i ++) {
            double rate = r.getStatusCalendar(i).getRate();
            double roomTypeRate = r.getRoomType().getRate();
            roomChargesBill += rate * roomTypeRate;
        }
    }

}
