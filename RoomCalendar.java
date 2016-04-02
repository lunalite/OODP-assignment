package cz2002_assignment;

public class RoomCalendar {

    private String status;
    private double rate;
    
    RoomCalendar() {
        
    }
    
    RoomCalendar(String status, double rate) {
        this.status = status;
        this.rate = rate;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getStatus() {
        return this.status;
    }

}
