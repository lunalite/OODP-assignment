package cz2002_assignment;

import java.util.Calendar;
import java.util.Date;

public class Reservation {
    
    
    // reserveCode is added the moment XMLMgr works its magic.
    // For now we will start from 5;
    
    /**
     * Reservation's reserveCounter for ensuring each codes are incremented
     */
    private static int reserveCounter = 5;
    
    /**
     * Reservation's reservation code
     */
    private int reserveCode;
    
    /**
     * Reservation billing info
     */
    private String billingInfo;
    
    /**
     * Reservation check in date
     */
    private Calendar checkInDate;
    
    /**
     * Reservation check out date 
     */
    private Calendar checkOutDate;
    
    /**
     * Reservation's number of adults
     */
    private int numberOfAdults;
    
    /**
     * Reservation's number of child
     */
    private int numberOfChild;
    
    /**
     * Reservation status
     */
    private ReservationStatus status;
    
    /**
     * Guest who did the reservation
     */
    private Guest g;
    
    /**
     * Room that is allocated to the reservation
     */
    private Room r;
    
    /**
     * Create new reservation
     * @param BI The reservation billing info
     * @param CID The reservation check in date
     * @param COD The reservation check out date
     * @param AdultsNum The number of adults in the reservation
     * @param ChildNum The number of child in the reservation
     */
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

    /**
     * Get reservation code
     * @return reserveCode
     */
    public int getReserveCode(){return this.reserveCode;}
    
    /**
     * Get reservation billing info
     * @return billingInfo
     */
    public String getBillingInfo(){return this.billingInfo;}
    
    /**
     * Get reservation check in date
     * @return checkInDate
     */
    public Calendar getCheckInDate(){return this.checkInDate;}
    
    /**
     * Get reservation check out date
     * @return checkOutDate
     */
    public Calendar getCheckOutDate(){return this.checkOutDate;}
    
    /**
     * Get number of adults in reservation
     * @return numberOfAdults
     */
    public int getNumberOfAdults(){return this.numberOfAdults;}
    
    /**
     * Get numberOfChild in reservation
     * @return numberOfChild
     */
    public int getNumberOfChild(){return this.numberOfChild;}
    
    /**
     * Get reservation status
     * @return ReservationStatus
     */
    public ReservationStatus getStatus() {return this.status;}
    
    /**
     * Get the guest that made the reservation
     * @return The guest object that made the reservation
     */
    public Guest getGuest(){return this.g;}
    
    /**
     * Get the room allocated to the reservation
     * @return The room that is reserved
     */
    public Room getRoom(){return this.r;}
    
    /**
     * Set the reservation status
     * @param status The status of the reservation
     */
    public void setStatus(ReservationStatus status) {this.status = status;}

    /**
     * Set the reservation code
     * @param reserveCode The reservation code of the reservation
     */
    public static void setReserveCode(int reserveCode) {reserveCode = reserveCode;}
    
    /**
     * Set guest who made the reservation
     * @param guest The guest who made the reservation
     */
    public void setGuest(Guest guest){g = guest;}
    
    /**
     * Set the room that is allocated to the reservation
     * @param room The room that is allocated to the reservation
     */
    public void setRoom(Room room){r = room;}
    
    /**
     * Set reservation's check in date
     * @param CID The reservation check in date
     */
    public void setCheckInDate(Calendar CID){checkInDate = CID;}
}
