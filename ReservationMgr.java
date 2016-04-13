package cz2002_assignment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ReservationMgr {
    
    private List<Reservation> reservationList;
    private Iterator<Reservation> reservationListItr;
    private Scanner sc = new Scanner (System.in);
    
    ReservationMgr() {
        reservationList = new ArrayList();
        // reserveCode is added the moment XMLMgr works its magic.
        // For now we will start from 5;
    }

    public Reservation createReservation(String billingInfo, Calendar checkInDate, Calendar checkOutDate, 
            int numberOfAdults, int numberOfChild) {
        reservationList.add(new Reservation(billingInfo, checkInDate, checkOutDate, numberOfAdults, numberOfChild));
        
        return reservationList.get(reservationList.size()-1);
    }

    public void updateReservation(int reserveCode) {
        
        Reservation r = searchReservationByCode(reserveCode);
        if ( r == null ) {
            System.out.println("No such reservation code.");
            return;
        }
        Room room = r.getRoom();
        Guest g = r.getGuest();
        
    	System.out.println("Do you want to: ");
    	System.out.println("(1)Change your reservation date");
    	System.out.println("(2)Change room type");
    	System.out.println("(3)Change both reservation date and room type");
    	System.out.println("(4)Go Back");
    	int choice = sc.nextInt();
        sc.nextLine(); // flush
        
    	while (true){
            if (choice > 4 || choice <= 0) {
                System.out.println("Invalid choice, please re-enter: ");
    		choice = sc.nextInt();
                sc.nextLine(); // flush
            }
            if (choice == 1||choice == 3){
                // DO not need to check for dates if room is available again
                // Only check-in date is changed.
                System.out.println("Please enter your new check in date: ");
                int newCheckInDate = sc.nextInt();
                sc.nextLine(); // flush
                
                Calendar cal = new GregorianCalendar(2016,4,newCheckInDate);
                r.setCheckInDate(cal);
                
                // Setting all the previously reserved calendar back to vacant
                // Setting all the previously reserved room back to without guest
                for (int q = r.getCheckInDate().DAY_OF_MONTH; q < newCheckInDate ; q ++) {
                    room.getStatusCalendar(q).setStatus(RoomStatus.VACANT);
                    room.getStatusCalendar(q).setGuestName(null);
                }
                
                System.out.println("Check-in date changed.");
                if (choice == 1)
                    break;
            }
            
            if (choice == 2||choice == 3){
                System.out.println("Please enter your new room type: ");
                String newRoomType = sc.nextLine();
                RoomType newroomType = RoomType.valueOf(newRoomType.toUpperCase());
                
                // Changing to a new room type, have to search for empty rooms
                Room vacantRoom = RoomMgr.checkVacantRoom(newroomType, r.getCheckInDate().DAY_OF_MONTH, 
                        r.getCheckOutDate().DAY_OF_MONTH);
                
                // If there is vacant room, replace all the following conditions
                if (vacantRoom != null) {
                    for (int q = r.getCheckInDate().DAY_OF_MONTH; q <= r.getCheckOutDate().DAY_OF_MONTH ; q ++) {
                        room.getStatusCalendar(q).setStatus(RoomStatus.VACANT);
                        room.getStatusCalendar(q).setGuestName(null);
                    }
                    for (int q = r.getCheckInDate().DAY_OF_MONTH; q <= r.getCheckOutDate().DAY_OF_MONTH ; q ++) {
                        vacantRoom.getStatusCalendar(q).setStatus(RoomStatus.RESERVED);
                        vacantRoom.getStatusCalendar(q).setGuestName(g);
                    }
                    
                    r.setRoom(vacantRoom);
                    System.out.println("Reservation room type changed.");
                }
                else
                    System.out.println("No rooms available.");
                break;
            }
            
            if (choice==4){
                    break;
            }
            
        }
        g.setReservation(r);
        
        printReservation(r);
        
    	System.out.println("Reservation is updated. Have a pleasant trip.");
    }

    public void removeReservation(int reserveCode) {
        
        Reservation r = searchReservationByCode(reserveCode);

        if (r != null) {
            Room room = r.getRoom();
            Guest g = r.getGuest();
            r.setStatus(ReservationStatus.CANCELLED);
            for (int q = r.getCheckInDate().DAY_OF_MONTH; q <= r.getCheckOutDate().DAY_OF_MONTH ; q ++) {
                room.getStatusCalendar(q).setStatus(RoomStatus.VACANT);
                room.getStatusCalendar(q).setGuestName(null);
            }
            g.setReservation(null);
            System.out.println("Reservation is removed. We hope you will stay with us in the future.");
        }
        else
            System.out.println("No such reservation.");
    	
    }

    public Reservation searchReservationByCode (int reserveCode) {
        reservationListItr = reservationList.iterator();
        while (reservationListItr.hasNext()){
            Reservation r = reservationListItr.next();
            if (r.getReserveCode() == (reserveCode)){
                return r;
            }
        }
        return null;
    }
    
    public Reservation searchReservationByName (String guestName) {
        reservationListItr = reservationList.iterator();
        while (reservationListItr.hasNext()){
            Reservation r = reservationListItr.next();
            if (r.getGuest().getName().equals(guestName)){
                return r;
            }
        }
        return null;
    }
    
    public void printReservation(Reservation r) {
        System.out.println("\n========================");
        System.out.println("Reservation code: " + r.getReserveCode());
        System.out.println("Room Number: " + r.getRoom().getRoomNo());
        System.out.println("Billing information: " + r.getBillingInfo());
        Calendar CID = r.getCheckInDate();
        System.out.println("check-in date: " + CID.get(CID.YEAR) + "-" + CID.get(CID.MONTH) +
                "-" + CID.get(CID.DAY_OF_MONTH));
        Calendar COD = r.getCheckOutDate();
        System.out.println("Check-out date: " + COD.get(COD.YEAR) + "-" + COD.get(COD.MONTH) +
                "-" + COD.get(COD.DAY_OF_MONTH));
        System.out.println("Number of adults: " + r.getNumberOfAdults());
        System.out.println("Number of children: " + r.getNumberOfChild());
        System.out.println("Reservation status: " + r.getStatus());
        System.out.println("========================\n");
    }
    
    public void acknowledge() {

    }
    
    public Iterator<Reservation> getReservationItr(){
        return reservationList.iterator();
    }
}
