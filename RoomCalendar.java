package cz2002_assignment;

public class RoomCalendar {

    private RoomStatus status;
    private double rate;
    private Guest guest;
    
    RoomCalendar(){
    }
    
    RoomCalendar(RoomStatus statuS, double rate, String guestName) {
        status = statuS;
        this.rate = rate;
        guest = new GuestMgr().getGuest(guestName);
    }

    public RoomStatus getStatus() {return this.status;}
    public double getRate() {return this.rate;}
    public Guest getGuest() {return this.guest;}
    
    public void setStatus(RoomStatus status) {this.status = status;}
    public void setGuestName(Guest g) {this.guest = g;}
}
