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

    Room(String roomNo, boolean wifiEnabled, String faceView, boolean smoking, RoomType ROOMTYPE) {
        this.roomNo = roomNo;
        this.wifiEnabled = wifiEnabled;
        this.faceView = faceView;
        this.smoking = smoking;
        this.roomType = ROOMTYPE;
    }
    
    public void importRoomCal(RoomCalendar[] statusCal) {
        YearMonth yearMonthObject = YearMonth.of(2016, Month.APRIL);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        statusCalendar = new RoomCalendar[daysInMonth];

        for (int i = 0; i < 30; i ++) {
            if (statusCalendar[i] == null)
                statusCalendar[i] = new RoomCalendar();
            statusCalendar[i] = statusCal[i];
            
        }
    }
    
    public RoomType getRoomType() {return roomType;}
    public RoomStatus getRoomStatus(int reportDay) {return statusCalendar[reportDay - 1].getStatus();}
    public String getRoomNo() {return this.roomNo;}

    /**
     *
     * @param roomNo
     */
    public void setRoomNo(String roomNo) {this.roomNo = roomNo;}
    public void setRoomStatus (RoomStatus status, int day) {statusCalendar[day - 1].setStatus(status);}
        

}
