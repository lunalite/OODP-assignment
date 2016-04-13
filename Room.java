package cz2002_assignment;

import java.time.Month;
import java.time.YearMonth;

public class Room {

    /**
    * Room number.
    */ 
    private String roomNo;
    
    /**
    * Room wifi access.
    */ 
    private boolean wifiEnabled;
    
    /**
    * Room unique selling point description.
    */ 
    private String faceView;
    
    /**
    * Room smoking facilities access.
    */ 
    private boolean smoking;

    /**
    * Room Type, determines the number and type of beds in the room
    */ 
    private RoomType roomType;
    
    /**
    * Room status for each day in the calendar
    */ 
    private RoomCalendar[] statusCalendar;

    
    /**
    * Default constructor
    */ 
    Room() {
    }

    /**
    * Creates a new Room with the given parameters.
    * @param roomNo This room's number.
    * @param wifiEnabled Whether this room has wifi access.
    * @param faceView This room unique selling point description
    * @param smoking Whether this room allows customers to smoke within it
    * @param roomType This room type such as Single / Double / Triple
    */ 
    Room(String roomNo, boolean wifiEnabled, String faceView, boolean smoking, RoomType roomType) {
        this.roomNo = roomNo;
        this.wifiEnabled = wifiEnabled;
        this.faceView = faceView;
        this.smoking = smoking;
        this.roomType = roomType;
        
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
            
        }
    }
    
    /**
    * Set room calendar data via providing preset data
    * @param statusCal The rooms status calendar
    */ 
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
    
    /**
     * Get room type
     * @return roomType
     */
    public RoomType getRoomType() {return roomType;}
    
    /**
     * Set room type
     * @param roomType The room's room type
     */
    public void setRoomType(RoomType roomType) {this.roomType = roomType;}


    /**
     * Get room number
     * @return roomNo
     */
    public String getRoomNo() {return this.roomNo;}
    
    /**
     * Set room number
     * @param roomNo The room's number
     */
    public void setRoomNo(String roomNo) {this.roomNo = roomNo;}
         
    /**
     * Get room wifi accessibility
     * @return wifiEnabled
     */
    public boolean getIsWifiEnabled() {return wifiEnabled;}
    
    /**
     * Set room wifi accessibility
     * @param wifiEnabled The room's wifi accessibility
     */
    public void setWifiEnabled(boolean wifiEnabled) {this.wifiEnabled = wifiEnabled;}

    /**
     * Get room face view description
     * @return faceView
     */
    public String getFaceView() {return faceView;}
    
    /**
     * Set room face view description
     * @param faceView The room's face view description
     */
    public void setFaceView(String faceView) {this.faceView = faceView;}
    
    /**
     * Get room smoking facilities accessibility
     * @return smoking
     */
    public boolean getIsSmokingAllowed() {return smoking;}
    
    /**
     * Set room smoking facilities accessibility
     * @param smoking The room's smoking facilities accessibility
     */
    public void setSmokingAllowed(boolean smoking) {this.smoking = smoking;}
    
    /**
     * Get room status on specific day
     * @param reportDay The requested report date
     * @return roomNo
     */
    public RoomStatus getRoomStatus(int reportDay) {return statusCalendar[reportDay - 1].getStatus();}
    
    /**
     * Set room status on specific day
     * @param status The room status
     * @param day The day to set the room status
     */
    public void setRoomStatus (RoomStatus status, int day) {statusCalendar[day - 1].setStatus(status);}
    
    /**
     * Get room status calendar array
     * @return statusCalendar[]
     */
    public RoomCalendar[] getStatusCalendar() {return statusCalendar;}
    
    /**
     * Get room status calendar object on specific day
     * @param day The request day of status calendar
     * @return statusCalendar
     */
    public RoomCalendar getStatusCalendar(int day){return statusCalendar[day-1];}

}
