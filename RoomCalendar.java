package cz2002_assignment;

public class RoomCalendar {

    private RoomStatus status;
    private double rate;
    
    RoomCalendar(){
        
    }
    
    RoomCalendar(RoomStatus status, double rate) {
        this.status = status;
        this.rate = rate;
    }

    public void setStatus(RoomStatus status) {this.status = status;}
    public RoomStatus getStatus() {return this.status;}
    public double getRate() {return this.rate;}

}
