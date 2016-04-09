package cz2002_assignment;

import java.util.ArrayList;
import java.util.Date;
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
        Reservation.setReserveCode(5);
    }

    public Reservation createReservation(String billingInfo, Date checkInDate, Date checkOutDate, 
            int numberOfAdults, int numberOfChild) {
        reservationList.add(new Reservation(billingInfo, checkInDate, checkOutDate, numberOfAdults, numberOfChild));
        
        return reservationList.get(reservationList.size()-1);
    }

    public void updateReservation(int reserveCode) {
    	System.out.println("Do you want to: ");
    	System.out.println("(1)Change your reservation date");
    	System.out.println("(2)Change room type");
    	System.out.println("(3)Change both reservation date and room type");
    	System.out.println("(4)Go Back");
    	int choice = sc.nextInt();
    	while (choice != 1||choice != 2||choice != 3||choice != 4){
    		System.out.println("Invalid choice, please re-enter: ");
    		choice = sc.nextInt();
    	
            if (choice == 1||choice == 3){
                    System.out.println("Please enter your new check in date: ");
            }
            if (choice == 1||choice == 3){
                    System.out.println("Please enter your new room type: ");
                    //NEED TO UPDATE, ROOM TYPE NOT COMPLETED
            }
            if (choice==4){
                    break;
            }
        }
    	System.out.println("Reservation is updated. Have a pleasant trip.");
    }

    public void removeReservation(int reserveCode) {
    	reservationMgr.setStatus(resCode, ReservationStatus.EXPIRED);
    	int numOfDays= Reservation.getCheckOutDate-Reservation.getCheckInDate; 
    	//Don't know how to get num of days from the date
    	int day = Reservation.getCheckOutDate.DAY_OF_MONTH	//Don't know how get. WRONG 
    	for (int i=0; i<numOfDays; i++){
    		int day = 
    		Room.setRoomStatus("Vacant", )
    	}
    	System.out.println("Reservation is removed. We hope you will stay with us in the future.");
    }

    public Reservation searchReservation (int reserveCode) {
        reservationListItr = reservationList.iterator();
        while (reservationListItr.hasNext()){
            Reservation r = reservationListItr.next();
            if (r.getReserveCode() == (reserveCode)){
                return r;
            }
        }
        return null;
    }
    
    public void printReservation(Reservation r, String roomNo) {
        System.out.println("\n========================");
        System.out.println("Reservation code: " + r.getReserveCode());
        System.out.println("Room Number: " + roomNo);
        System.out.println("Billing information: " + r.getBillingInfo());
        System.out.println("check-in date: " + r.getCheckInDate());
        System.out.println("Check-out date: " + r.getCheckOutDate());
        System.out.println("Number of adults: " + r.getNumberOfAdults());
        System.out.println("Number of children: " + r.getNumberOfChild());
        System.out.println("Reservation status: " + r.getStatus());
        System.out.println("========================\n");
    }
    
    public void getReservation() {

    }

    public void acknowledge() {

    }

}
