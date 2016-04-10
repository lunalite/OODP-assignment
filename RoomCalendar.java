package cz2002_assignment;

public class RoomCalendar {

    private static RoomStatus status;
    private double rate;
    private Guest guestName;
    
    RoomCalendar(){
        
    }
    
    RoomCalendar(RoomStatus status, double rate) {
        this.status = status;
        this.rate = rate;
    }

    public static RoomStatus getStatus() {return RoomCalendar.status;}
    public double getRate() {return this.rate;}
    
    public void setStatus(RoomStatus status) {this.status = status;}
    public void setGuestName(Guest g) {this.guestName = g;}
}
