package cz2002_assignment;

public class RoomCalendar {

    /**
     * Room status for the day
     */
    private RoomStatus status;
    
    /**
     * Room rate for the day
     */
    private double rate;
    
    /**
     * The guest who occupied or reserved the room
     */
    private Guest guest;
    
    /**
     * Default constructor
     */
    RoomCalendar(){
    }
    
    /**
     * Creates Room Calendar Object 
     * @param status The room status for the day
     * @param rate The room rate for the day
     * @param guestName The room guest's name if occupied or reserved
     */
    RoomCalendar(RoomStatus status, double rate, String guestName) {
        this.status = status;
        this.rate = rate;      
        if (guestName.isEmpty())
            guest = null;
        else
            guest = GuestMgr.getGuest(guestName);
    }

    /**
     * Get room status
     * @return status
     */
    public RoomStatus getStatus() {return this.status;}
    
    /**
     * Get room rate
     * @return rate
     */
    public double getRate() {return this.rate;}
    
    /**
     * Get the guest who occupied or reserved the room
     * @return guest
     */
    public Guest getGuest() {
        if (guest != null)
            return this.guest;
        else
            return null;
    }
    
    /**
     * Get room status
     * @param status The room's status
     */
    public void setStatus(RoomStatus status) {this.status = status;}
    
    /**
     * Set the guest who occupied or reserved the room 
     * @param guest The guest who is going to occupy or reserve the room
     */
    public void setGuestName(Guest guest) {this.guest = guest;}
}
