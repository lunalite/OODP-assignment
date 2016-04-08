package cz2002_assignment;

public class ReservationMgr {

    ReservationMgr() {

    }

    public void createReservation() {

    }

    public void updateReservation(int reserveCode) {
    	System.out.println("Do you want to: ");
    	System.out.println("(1)Change your reservation date");
    	System.out.println("(2)Change room type");
    	System.out.println("(3)Change both reservation date and room type");
    	System.out.println("(4)Go Back");
    	Int choice = sc.nextInt();
    	while (choice != 1||choice != 2||choice != 3||choice != 4){
    		System.out.println("Invalid choice, please re-enter: ");
    		choice = sc.nextInt();
    	}
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
    	
    	System.out.println("Reservation is updated. Have a pleasant trip.");
    }

    public void removeReservation(int reserveCode) {
    	Reservation.setStatus("Vacant");
    	int numOfDays= Reservation.getCheckOutDate-Reservation.getCheckInDate; 
    	//Don't know how to get num of days from the date
    	int day = Reservation.getCheckOutDate.DAY_OF_MONTH	//Don't know how get. WRONG 
    	for (int i=0; i<numOfDays; i++){
    		int day = 
    		Room.setRoomStatus("Vacant", )
    	}
    	System.out.println("Reservation is removed. We hope you will stay with us in the future.");
    }

    public void getReservation() {

    }

    public void acknowledge() {

    }

}
