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
        
        YearMonth yearMonthObject = YearMonth.of(2016, Month.APRIL);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        statusCalendar = new RoomCalendar[daysInMonth];
        
        
        for (int i = 0; i < daysInMonth; i++) {
            statusCalendar[i] = new RoomCalendar(RoomStatus.VACANT, 100, "");
            
            /*
            int randomStatus = (int) (Math.random() * 100 % 4);
            
            
            if (randomStatus == 0) {
                statusCalendar[i] = new RoomCalendar(RoomStatus.VACANT, 100, "");
            }
            else if (randomStatus == 1) {
                statusCalendar[i] = new RoomCalendar(RoomStatus.RESERVED, 100, "");
            }
            else if (randomStatus == 2) {
                statusCalendar[i] = new RoomCalendar(RoomStatus.OCCUPIED, 100, "");
            }
            else {
                statusCalendar[i] = new RoomCalendar(RoomStatus.UNDER_MAINTENANCE, 100, "");
            }
            */
            
            
            //statusCalendar[i] = new RoomCalendar("Vacant", 100);
        }
    }
    
    public void importRoomCal(RoomCalendar[] statusCal) {
        YearMonth yearMonthObject = YearMonth.of(2016, Month.APRIL);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        statusCalendar = new RoomCalendar[daysInMonth];

        for (int i = 0; i < daysInMonth; i ++) {
            if (statusCal[i] != null) {
                statusCalendar[i] = statusCal[i];
            }
            else
                statusCalendar[i] = new RoomCalendar();
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
