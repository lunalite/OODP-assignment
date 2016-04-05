package cz2002_assignment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomMgr {

    private final int totalRooms  = 48;
    private int currentDay;
    
    private Date timeStamp;
    private Room[] roomData; //Record of all the rooms
    private Payment[] totalPaymentArr;

    RoomMgr(int today) {
        roomData = new Room[totalRooms];
        totalPaymentArr = new Payment[totalRooms]; //initialise the record for payment
        currentDay = today; //Ensure the day's date is known
        
        // 48 rooms from floors 02 - 07
        // 6 Floors with 8 rooms each
        // Format of String roomNo: e.g. "0201", "0708"
        
        /*
        for (int i = 0; i < totalRooms; i++) {
            roomData[i] = new Room(String.format("%02d-%02d", (i / 8) + 2, (i % 8) + 1), true, "", false, "Single", 120.0f);
        }
        */
        
        //Room(String roomNo, boolean wifiEnabled, String faceView, boolean smoking, String bedType, double rate)
        
        for (int i = 0; i < 16; i++) {
            roomData[i] = new Room(String.format("%02d%02d", (i / 8) + 2, (i % 8) + 1), true, "", false, "Single", 120.0f);
        }
        
        for (int i = 16; i < 21; i++) {
            roomData[i] = new Room(String.format("%02d%02d", (i / 8) + 2, (i % 8) + 1), true, "", false, "Double", 160.0f);
        }
        
        for (int i = 21; i < 40; i++) {
            roomData[i] = new Room(String.format("%02d%02d", (i / 8) + 2, (i % 8) + 1), true, "", false, "Twin", 160.0f);
        }
        
        for (int i = 40; i < totalRooms; i++) {
            roomData[i] = new Room(String.format("%02d%02d", (i / 8) + 2, (i % 8) + 1), true, "", false, "Triple", 200.0f);
        }
        
        for (int i = 0; i < totalRooms; i ++) {
            if (roomData[i].getRoomStatus(currentDay).contentEquals("Occupied")){
                totalPaymentArr[i] = new Payment();
            }
        }
    }

    public void checkIn(String roomNo, int today) {
        //mainApp class already checked for room that it is vacant
        timeStamp = new Date(); //Create a timeStamp the moment a family checks in.
        totalPaymentArr[roomStrToInt(roomNo)-1] = new Payment();  //Create the payment class that is associated with the room
        roomData[roomStrToInt(roomNo)-1].setRoomStatus("Occupied", today); //set roomstatus to occupied from occupied.
        
    }

    public void getRoomDetail() {

    }

    public void checkOut() {

    }

    public void doPayment() {

    }
    
    public int roomStrToInt(String roomStr) { // integer-wise, uses int 1-48 for each rooms from 02-01 to 07-07 respectively
        int roomInt = 0;
        int floor = Integer.parseInt(roomStr.substring(0,2));
        int room = Integer.parseInt(roomStr.substring(roomStr.lastIndexOf("-")+1,roomStr.lastIndexOf("-")+3));
        roomInt += (floor - 2)*8 + room;
        return roomInt;
    }
    
    // Room Type Occupancy Rate
    //Single : Number : 10 out of 20
    //Rooms : 02-03, 03-04, 03-05
    //Double : Number : 5 out of 10
    //Rooms : 02-04, 05-04, 05-05
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
            String roomStatus = roomData[i].getRoomStatus(reportDay);
            String roomType = roomData[i].getRoomType();
            
            
            switch (roomType) {
                case "Single":
                    singleCount++;
                    
                    if (roomStatus.contentEquals("Occupied")) {
                        singleOccupiedCount++;
                        singleOccupiedRooms = singleOccupiedRooms + " " + roomData[i].getRoomNo().substring(0,2) + 
                                "-" + roomData[i].getRoomNo().substring(2,4) + ",";
                    }
                    
                    break;
                case "Double":
                    doubleCount++;
                    
                    if (roomStatus.contentEquals("Occupied")) {
                        doubleOccupiedCount++;
                        doubleOccupiedRooms = doubleOccupiedRooms + " " + roomData[i].getRoomNo().substring(0,2) +
                                "-" + roomData[i].getRoomNo().substring(2,4) + ",";
                    }
                    
                    break;
                case "Twin":
                    twinCount++;
                    
                    if (roomStatus.contentEquals("Occupied")) {
                        twinOccupiedCount++;
                        twinOccupiedRooms = twinOccupiedRooms + " " + roomData[i].getRoomNo().substring(0,2) +
                                "-" + roomData[i].getRoomNo().substring(2,4) + ",";
                    }
                    
                    break;
                case "Triple":
                    tripleCount++;
                    
                    if (roomStatus.contentEquals("Occupied")) {
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
            String roomStatus = roomData[i].getRoomStatus(reportDay);
            
            switch (roomStatus) {
                case "Vacant":
                    vacantCount++;
                    vacantRooms = vacantRooms + " " + roomData[i].getRoomNo().substring(0,2) +
                                "-" + roomData[i].getRoomNo().substring(2,4) + ",";
                    break;
                case "Occupied":
                    occupiedCount++;
                    occupiedRooms = occupiedRooms + " " + roomData[i].getRoomNo().substring(0,2) +
                                "-" + roomData[i].getRoomNo().substring(2,4) + ",";
                    break;
                case "Reserved":
                    reservedCount++;
                    reservedRooms = reservedRooms + " " + roomData[i].getRoomNo().substring(0,2) +
                                "-" + roomData[i].getRoomNo().substring(2,4) + ",";
                    break;
                case "Under Maintenance":
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
    
    public Room getRoom(String roomNo){return roomData[roomStrToInt(roomNo)-1];}
    public Payment getPayment(String roomNo){return totalPaymentArr[roomStrToInt(roomNo)-1];}
}
