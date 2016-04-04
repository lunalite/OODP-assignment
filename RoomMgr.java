package cz2002_assignment;

import java.util.Date;

public class RoomMgr {

    private final int totalRooms = 48;
    
    private Date timeStamp;
    private Room[] roomData;

    RoomMgr() {
        roomData = new Room[totalRooms];

        // 48 rooms from floors 02 - 07
        // 6 Floors with 8 rooms each
        // Format of String roomNo: e.g. "02-01", "07-07"
        
        /*
        for (int i = 0; i < totalRooms; i++) {
            roomData[i] = new Room(String.format("%02d-%02d", (i / 8) + 2, (i % 8) + 1), true, "", false, "Single", 120.0f);
        }
        */
        
        //Room(String roomNo, boolean wifiEnabled, String faceView, boolean smoking, String bedType, double rate)
        
        for (int i = 0; i < 16; i++) {
            roomData[i] = new Room(String.format("%02d-%02d", (i / 8) + 2, (i % 8) + 1), true, "", false, "Single", 120.0f);
        }
        
        for (int i = 16; i < 21; i++) {
            roomData[i] = new Room(String.format("%02d-%02d", (i / 8) + 2, (i % 8) + 1), true, "", false, "Double", 160.0f);
        }
        
        for (int i = 21; i < 40; i++) {
            roomData[i] = new Room(String.format("%02d-%02d", (i / 8) + 2, (i % 8) + 1), true, "", false, "Twin", 160.0f);
        }
        
        for (int i = 40; i < totalRooms; i++) {
            roomData[i] = new Room(String.format("%02d-%02d", (i / 8) + 2, (i % 8) + 1), true, "", false, "Triple", 200.0f);
        }
    }

    public void checkIn() {

    }

    public void getRoomDetail() {

    }

    public void checkOut() {

    }

    public void doPayment() {

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
                        singleOccupiedRooms = singleOccupiedRooms + " " + roomData[i].getRoomNo() + ",";
                    }
                    
                    break;
                case "Double":
                    doubleCount++;
                    
                    if (roomStatus.contentEquals("Occupied")) {
                        doubleOccupiedCount++;
                        doubleOccupiedRooms = doubleOccupiedRooms + " " + roomData[i].getRoomNo() + ",";
                    }
                    
                    break;
                case "Twin":
                    twinCount++;
                    
                    if (roomStatus.contentEquals("Occupied")) {
                        twinOccupiedCount++;
                        twinOccupiedRooms = twinOccupiedRooms + " " + roomData[i].getRoomNo() + ",";
                    }
                    
                    break;
                case "Triple":
                    tripleCount++;
                    
                    if (roomStatus.contentEquals("Occupied")) {
                        tripleOccupiedCount++;
                        tripleOccupiedRooms = tripleOccupiedRooms + " " + roomData[i].getRoomNo() + ",";
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
                    vacantRooms = vacantRooms + " " + roomData[i].getRoomNo() + ",";
                    break;
                case "Occupied":
                    occupiedCount++;
                    occupiedRooms = occupiedRooms + " " + roomData[i].getRoomNo() + ",";
                    break;
                case "Reserved":
                    reservedCount++;
                    reservedRooms = reservedRooms + " " + roomData[i].getRoomNo() + ",";
                    break;
                case "Under Maintenance":
                    underMaintenanceCount++;
                    underMaintenanceRooms = underMaintenanceRooms + " " + roomData[i].getRoomNo() + ",";
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

}
