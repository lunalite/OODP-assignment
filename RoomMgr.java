package cz2002_assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class RoomMgr {

    /**
     * Total number of rooms
     */
    public static int totalRooms = 48; //Not exactly need, can always replace with roomData.length
    
    //private Calendar timeStamp; //Useless variable for now
    
    /**
     * Room array that holds all the room objects
     */
    private static Room[] roomData; //Record of all the rooms

    /**
     * Default constructor
     */
    RoomMgr() {}
    
    /**
     * Creates room manager with preset data 
     * @param roomdata The rooms data
     * @param statusCal The rooms status calendar
     */
    RoomMgr(Room[] roomdata, RoomCalendar[][] statusCal) {
        roomData = new Room[totalRooms];
        
        // 48 rooms from floors 02 - 07
        // 6 Floors with 8 rooms each
        // Format of int roomNo: e.g. 0201, 0708
        
        // Single rooms 0201 - 0308 i.e. from 0-15
        // Double rooms 0401 - 0404 i.e. from 16-19
        // Twin rooms   0405 - 0608 i.e. from 20-39
        // Triple rooms 0701 - 0708 i.e. from 40-47
        
        for (int z = 0; z < totalRooms; z ++) {
            if (roomdata[z] != null)
                roomData[z] = roomdata[z];
            else
                roomData[z] = new Room();
            
            roomData[z].importRoomCal(statusCal[z]);   
        }
    }

    /**
     * room check in via walk in
     * @param roomNo The room number
     * @param today The check in day
     * @param tomorrow The check out day
     */
    public void checkIn(String roomNo, int today, int tomorrow) {
        //mainApp class already checked for room that it is vacant
        for (int i = today; i <= tomorrow; i ++) {
            //set roomstatus to occupied from vacant.
            roomData[roomStrToInt(roomNo)-1].setRoomStatus(RoomStatus.OCCUPIED, i); 
        }
    }
    
    /**
     * room check in via reservation
     * @param roomNo The room number
     * @param reservation The reservation object
     */
    public void checkIn(String roomNo, Reservation reservation) {
        Calendar CID = reservation.getCheckInDate();
        Calendar COD = reservation.getCheckOutDate();
        for (int i = CID.get(CID.DAY_OF_MONTH); i <= COD.get(COD.DAY_OF_MONTH); i ++) {
            //set roomstatus to occupied from vacant.
            roomData[roomStrToInt(roomNo)-1].setRoomStatus(RoomStatus.OCCUPIED, i); 
        }
    }

    /**
     * room check out
     * @param roomNo The room number
     * @param tomorrow The check out day
     */
    public void checkOut(String roomNo, int tomorrow) {
        //mainApp class already checked for room that it is occupied
        roomData[roomStrToInt(roomNo)-1].setRoomStatus(RoomStatus.VACANT, tomorrow); //set roomstatus to vacant from occupied.
    }
    
    /**
     * check if room is vacant from requested check in till check out dates
     * @param RT The room type
     * @param start The check in day
     * @param end The check out day
     * @return availableRoom
     */
    public static Room checkVacantRoom(RoomType RT, int start, int end) {
        // Single rooms 0201 - 0308 i.e. from 0-15
        // Double rooms 0401 - 0404 i.e. from 16-19
        // Twin rooms   0405 - 0608 i.e. from 20-39
        // Triple rooms 0701 - 0708 i.e. from 40-47
            int roomStart = 2;
            int roomEnd = 3;
            
        switch (RT) {
            case SINGLE:
                roomStart = 0;
                roomEnd = 16;
                break;
            case DOUBLE:
                roomStart = 16;
                roomEnd = 20;
                break;
            case TWIN:
                roomStart = 20;
                roomEnd = 40;
                break;
            case TRIPLE:
                roomStart = 40;
                roomEnd = 48;
                break;
        }
        
        // if no rooms found, return -1
        boolean roomEmpty = true;
        
        for (int i = roomStart; i < roomEnd; i ++) {
            for (int z = start; z <= end; z ++) {
                if (roomData[i].getRoomStatus(z) != RoomStatus.VACANT) {
                    roomEmpty = false;
                }
            }
            
            // if the room is at anytime empty for the whole period,
            // return the index and break out of loop.
            if (roomEmpty == true) {
                return roomData[i];
            }
            else
                roomEmpty = true;
        }
        return null;
    }
    
    // Room Type Occupancy Rate
    //Single : Number : 10 out of 20
    //Rooms : 02-03, 03-04, 03-05
    //Double : Number : 5 out of 10
    //Rooms : 02-04, 05-04, 05-05
    /**
     * Prints occupancy report for specific day
     * @param reportDay The requested report day
     */
    public void getOccupancyReport(int reportDay) {
        
        int singleCount = 0;
        int doubleCount = 0;
        int twinCount = 0;
        int tripleCount = 0;
        
        int singleOccupiedCount = 0;
        int doubleOccupiedCount = 0;
        int twinOccupiedCount = 0;
        int tripleOccupiedCount = 0;
        
        String singleOccupiedRooms = "";
        String doubleOccupiedRooms = "";
        String twinOccupiedRooms = "";
        String tripleOccupiedRooms = "";
        
        for (int i = 0; i < totalRooms; i++) {
            RoomStatus roomStatus = roomData[i].getRoomStatus(reportDay);
            RoomType roomType = roomData[i].getRoomType();
            
            
            switch (roomType) {
                case SINGLE:
                    singleCount++;
                    
                    if (roomStatus == RoomStatus.OCCUPIED) {
                        singleOccupiedCount++;
                        singleOccupiedRooms = singleOccupiedRooms + " " + roomData[i].getRoomNo().substring(0,2) + 
                                "-" + roomData[i].getRoomNo().substring(2,4) + ",";
                    }
                    
                    break;
                case DOUBLE:
                    doubleCount++;
                    
                    if (roomStatus == RoomStatus.OCCUPIED) {
                        doubleOccupiedCount++;
                        doubleOccupiedRooms = doubleOccupiedRooms + " " + roomData[i].getRoomNo().substring(0,2) +
                                "-" + roomData[i].getRoomNo().substring(2,4) + ",";
                    }
                    
                    break;
                case TWIN:
                    twinCount++;
                    
                    if (roomStatus == RoomStatus.OCCUPIED) {
                        twinOccupiedCount++;
                        twinOccupiedRooms = twinOccupiedRooms + " " + roomData[i].getRoomNo().substring(0,2) +
                                "-" + roomData[i].getRoomNo().substring(2,4) + ",";
                    }
                    
                    break;
                case TRIPLE:
                    tripleCount++;
                    
                    if (roomStatus == RoomStatus.OCCUPIED) {
                        tripleOccupiedCount++;
                        tripleOccupiedRooms = tripleOccupiedRooms + " " + roomData[i].getRoomNo().substring(0,2) +
                                "-" + roomData[i].getRoomNo().substring(2,4) + ",";
                    }
                    
                    break;
                default:
                    break;
            }  
        }
        
        System.out.println("Single : Number : " + singleOccupiedCount + " out of " + singleCount);
        System.out.println("Rooms :" + singleOccupiedRooms);
        System.out.println("Double : Number : " + doubleOccupiedCount + " out of " + doubleCount);
        System.out.println("Rooms :" + doubleOccupiedRooms);
        System.out.println("Twin   : Number : " + twinOccupiedCount + " out of " + twinCount);
        System.out.println("Rooms :" + twinOccupiedRooms);
        System.out.println("Triple : Number : " + tripleOccupiedCount + " out of " + tripleCount);
        System.out.println("Rooms :" + tripleOccupiedRooms);
    }

    // Room Status
    //Vacant :
    //Rooms : 02-03, 03-04, 03-05
    //Occupied :
    //Rooms : 02-04, 05-04, 05-05
    /**
     * Prints status report for specific day
     * @param reportDay The requested report day
     */
    public void getStatusReport(int reportDay) {
        int vacantCount = 0;
        int occupiedCount = 0;
        int reservedCount = 0;
        int underMaintenanceCount = 0;
        
        String vacantRooms = "";
        String occupiedRooms = "";
        String reservedRooms = "";
        String underMaintenanceRooms = "";
        
        for (int i = 0; i < totalRooms; i++) {
            RoomStatus roomStatus = roomData[i].getRoomStatus(reportDay);
            
            switch (roomStatus) {
                case VACANT:
                    vacantCount++;
                    vacantRooms = vacantRooms + " " + roomData[i].getRoomNo().substring(0,2) +
                                "-" + roomData[i].getRoomNo().substring(2,4) + ",";
                    break;
                case OCCUPIED:
                    occupiedCount++;
                    occupiedRooms = occupiedRooms + " " + roomData[i].getRoomNo().substring(0,2) +
                                "-" + roomData[i].getRoomNo().substring(2,4) + ",";
                    break;
                case RESERVED:
                    reservedCount++;
                    reservedRooms = reservedRooms + " " + roomData[i].getRoomNo().substring(0,2) +
                                "-" + roomData[i].getRoomNo().substring(2,4) + ",";
                    break;
                case UNDER_MAINTENANCE:
                    underMaintenanceCount++;
                    underMaintenanceRooms = underMaintenanceRooms + " " + roomData[i].getRoomNo().substring(0,2) +
                                "-" + roomData[i].getRoomNo().substring(2,4) + ",";
                    break;
                default:
                    break;
            }  
        }
        
        System.out.println("Vacant: " + vacantCount);
        System.out.println("Rooms :" + vacantRooms);
        System.out.println("Occupied: " + occupiedCount);
        System.out.println("Rooms :" + occupiedRooms);
        System.out.println("Reserved: " + reservedCount);
        System.out.println("Rooms :" + reservedRooms);
        System.out.println("Under Maintenance: " + underMaintenanceCount);
        System.out.println("Rooms :" + underMaintenanceRooms);
        
    }
    
    /**
     * Get room object via room's number
     * @param roomNo The room number
     * @return roomData[x]
     */
    public static Room getRoom(String roomNo){
        //return roomData[roomStrToInt(roomNo)-1];
        for (int i = 0; i < roomData.length; i++) {
            if (roomData[i].getRoomNo().contentEquals(roomNo.replace("-", ""))) {
                return roomData[i];
            }
        }
        return null;
    }
        
    /**
     * Get room list
     * @return roomData array that holds all the room objects
     */
    public Room[] getRoomList() {return roomData;}
    
    /**
     * Convert room number from string to integer value
     * @param roomStr The room number in string
     * @return roomInt
     */
    public static int roomStrToInt(String roomStr) { // integer-wise, uses int 1-48 for each rooms from 02-01 to 07-07 respectively
        int roomInt = 0;
        int floor = Integer.parseInt(roomStr.substring(0,2));
        int room = Integer.parseInt(roomStr.substring(roomStr.lastIndexOf("-")+1,roomStr.lastIndexOf("-")+3));
        roomInt += (floor - 2)*8 + room;
        return roomInt;
    }
       
    /**
     * Get room data array
     * @return roomData
     */
    public Room[] getRoomData() {
        return roomData;
    }
    
    /**
     * Add new room
     * @param roomNo This room's number.
     * @param hasWifi Whether this room has wifi access.
     * @param faceViewDes This room unique selling point description
     * @param canSmoke Whether this room allows customers to smoke within it
     * @param roomType This room type such as Single / Double / Triple
     */
    public void addRoom(String roomNo, boolean hasWifi, String faceViewDes, boolean canSmoke, RoomType roomType) {
        Room[] tempRoomData = new Room[roomData.length + 1];
        for (int i = 0; i < roomData.length; i++) {
            tempRoomData[i] = roomData[i];
        }

        // last element
        tempRoomData[roomData.length] = new Room(roomNo, hasWifi, faceViewDes, canSmoke, roomType);
        
        roomData = new Room[tempRoomData.length];
        for (int i = 0; i < tempRoomData.length; i++) {
            roomData[i] = tempRoomData[i];
        }
        
        totalRooms = roomData.length;
    }

    /**
     * Prints specific room details
     * @param roomNo The room number
     */
    public void displayRoomDetails(String roomNo) {
        Room displayRoom = getRoom(roomNo);

        System.out.println("Room Type       : " + displayRoom.getRoomType().toString());
        System.out.println("Wifi Enabled    : " + (displayRoom.getIsWifiEnabled() ? "YES" : "NO"));
        System.out.println("Smoking Allowed : " + (displayRoom.getIsSmokingAllowed() ? "YES" : "NO"));
        System.out.println("Room Face View  : " + displayRoom.getFaceView());
        System.out.println("Room Status");
        for (int i = 1; i <= displayRoom.getStatusCalendar().length; i++) {
            System.out.print(String.format("%3s - %-18s", "" + i, displayRoom.getRoomStatus(i)));
            if (i % 7 == 0) {
                System.out.println("");
            } else {
                System.out.print("|");
            }
        }
        System.out.println("");
    }
    
    /**
     * Convert room number from integer to string
     * @param roomNo The room number in integer
     * @return roomStr
     */
    public static String roomIntToStr (int roomNo) {
        int room = roomNo % 8 + 1;
        int floor = roomNo / 8 + 2;
        String roomSt = String.format("%02d", room);
        String floorSt = String.format("%02d", floor);
        String roomStr = floorSt + "-" + roomSt;
        return roomStr;
    }
}
