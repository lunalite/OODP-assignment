package cz2002_assignment;

import java.util.Date;

public class Reservation {
    
    // reserveCode is added the moment XMLMgr works its magic.
    // For now we will start from 5;
    private static int reserveCode;
    private String billingInfo;
    private Date checkInDate;
    private Date checkOutDate;
    private int numberOfAdults;
    private int numberOfChild;
    private ReservationStatus status;
    private Guest g;
    private Room r;
    

    Reservation(String BI, Date CID, Date COD, int AdultsNum, int ChildNum) {
        // increment reserveCode to ensure none of it is repeated.
        reserveCode ++;
        
        billingInfo = BI;
        checkInDate = CID;
        checkOutDate = COD;
        numberOfAdults = AdultsNum;
        numberOfChild = ChildNum;
        
        // Status-wise, check for certain conditions before it is confirmed.
        status = ReservationStatus.CONFIRMED;
    }

    
    public int getReserveCode(){return this.reserveCode;}
    public String getBillingInfo(){return this.billingInfo;}
    public Date getCheckInDate(){return this.checkInDate;}
    public Date getCheckOutDate(){return this.checkOutDate;}
    public int getNumberOfAdults(){return this.numberOfAdults;}
    public int getNumberOfChild(){return this.numberOfChild;}
    public ReservationStatus getStatus() {return this.status;}
    public Guest getGuest(){return this.g;}
    public Room getRoom(){return this.r;}
    /**
     *
     * @param status
     */
    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    /**
     *
     * @param reserveCode
     */
    public static void setReserveCode(int reservecode) {
        Reservation.reserveCode = reservecode;
    }
    
    public void setGuest(Guest guest){
        g = guest;
    }
    
    public void setRoom(Room room){
        r = room;
    }

}
