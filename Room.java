package cz2002_assignment;

import java.time.Month;
import java.time.YearMonth;

public class Room {

    private String roomNo;
    private boolean wifiEnabled;
    private String faceView;
    private boolean smoking;
    private RoomType roomType;
    private RoomCalendar[] statusCalendar;

    Room() {

    }

    Room(String roomNo, boolean wifiEnabled, String faceView, boolean smoking, String bedType, double rate) {
        this.roomNo = roomNo;
        this.wifiEnabled = wifiEnabled;
        this.faceView = faceView;
        this.smoking = smoking;
        this.roomType = new RoomType(bedType, rate);
        
        
        
        YearMonth yearMonthObject = YearMonth.of(2016, Month.APRIL);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        statusCalendar = new RoomCalendar[daysInMonth];
        
        
        for (int i = 0; i < daysInMonth; i++) {
            int randomStatus = (int) (Math.random() * 100 % 4);
            String status = "";
            
            if (randomStatus == 0) {
                status = "Vacant";
            }
            else if (randomStatus == 1) {
                status = "Occupied";
            }
            else if (randomStatus == 2) {
                status = "Reserved";
            }
            else {
                status = "Under Maintenance";
            }
            
            statusCalendar[i] = new RoomCalendar(status, 100);
            
            //statusCalendar[i] = new RoomCalendar("Vacant", 100);
        }
    }
    
    
    public String getRoomType() {
        return roomType.getBedType();
    }
    
    public String getRoomStatus(int reportDay) {
        return statusCalendar[reportDay - 1].getStatus();
    }

    public String getRoomNo() {
        return this.roomNo;
    }

    /**
     *
     * @param roomNo
     */
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

}
