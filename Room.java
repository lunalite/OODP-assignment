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
    public void setRoomType(RoomType roomType) {this.roomType = roomType;}


    /**
     *
     * @param roomNo
     */
    public String getRoomNo() {return this.roomNo;}
    public void setRoomNo(String roomNo) {this.roomNo = roomNo;}
    
    public RoomStatus getRoomStatus(int reportDay) {return statusCalendar[reportDay - 1].getStatus();}
    public void setRoomStatus (RoomStatus status, int day) {statusCalendar[day - 1].setStatus(status);}
        
    public boolean getIsWifiEnabled() {return wifiEnabled;}
    public void setWifiEnabled(boolean wifiEnabled) {this.wifiEnabled = wifiEnabled;}

    public String getFaceView() {return faceView;}
    public void setFaceView(String faceView) {this.faceView = faceView;}
    
    public boolean getIsSmokingAllowed() {return smoking;}
    public void setSmokingAllowed(boolean smoking) {this.smoking = smoking;}
    
    public RoomCalendar[] getStatusCalendar() {return statusCalendar;}
    public RoomCalendar getStatusCalendar(int day){return statusCalendar[day-1];}

}
