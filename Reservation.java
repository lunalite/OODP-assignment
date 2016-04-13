package cz2002_assignment;

import java.util.Calendar;
import java.util.Date;

public class Reservation {
    
    private static int reserveCounter = 5;
    // reserveCode is added the moment XMLMgr works its magic.
    // For now we will start from 5;
    private int reserveCode;
    private String billingInfo;
    private Calendar checkInDate;
    private Calendar checkOutDate;
    private int numberOfAdults;
    private int numberOfChild;
    private ReservationStatus status;
    private Guest g;
    private Room r;
    

    Reservation(String BI, Calendar CID, Calendar COD, int AdultsNum, int ChildNum) {
        // increment reserveCode to ensure none of it is repeated.
        reserveCounter ++;
        
        reserveCode = reserveCounter;
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
    public Calendar getCheckInDate(){return this.checkInDate;}
    public Calendar getCheckOutDate(){return this.checkOutDate;}
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
    public void setReserveCode(int reservecode) {
        reserveCode = reservecode;
    }
    
    public void setGuest(Guest guest){
        g = guest;
    }
    
    public void setRoom(Room room){
        r = room;
    }
    public void setCheckInDate(Calendar CID){checkInDate = CID;}
}
