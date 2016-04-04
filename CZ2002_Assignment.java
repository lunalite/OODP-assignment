package cz2002_assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CZ2002_Assignment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int choice;
        
        // initialising of the related control classes
        ReservationMgr reservationMgr = new ReservationMgr();
        RoomMgr roomMgr = new RoomMgr(); // Create all rooms
        RoomServiceMgr roomServiceMgr = new RoomServiceMgr();
        
        do {

            System.out.println("Welcome to ABC's Hotel CMS, what are your related matters?");
            System.out.println("(1) Reservation related");
            System.out.println("(2) Room related");
            System.out.println("(3) Room service related");
            System.out.println("(4) ");
            System.out.println("(5) ");
            System.out.println("(6) ");
            System.out.println("(7) Exit");
            System.out.print("\nEnter the number of your choice: ");

            choice = sc.nextInt();
            sc.nextLine();  // flush the null character away
            
            // Test Cases
            /** 
             *- 2 guests occupied each room type (single, standard, VIP, suite, deluxe,) with different details, eg bed type, etc.
             *- 2 rooms of each room availability status Under Maintenance.
             *- 3 reservations with corresponding different guest details [* set check-in date as 1 week later]
             *- At least 5 room service menu items 
             */
            
         
            
            switch (choice) {
                case 1:
                    break;
                case 2:
                    
                    System.out.println("(1) Room Check in");
                    System.out.println("(2) Room Status Statistics Report");
                    System.out.print("\nEnter the number of your choice: ");
                    int roomOption = sc.nextInt();
                    sc.nextLine();
                    
                    if (roomOption == 1) {
                        System.out.println("Please enter room number (e.g. 02-05): ");
                        String roomNoCI = sc.nextLine();
                        roomMgr.checkIn(roomNoCI);  //Need to implement room checking in for certain room number
                        System.out.println("");
                    }
                    
                    else if (roomOption == 2) {
                        System.out.println("(1) Room Type Occupancy Rate");
                        System.out.println("(2) Room Status");
                        System.out.print("\nEnter the number of your choice: ");
                        int reportOption = sc.nextInt();
                        
                        if (reportOption == 1) {
                            System.out.print("\nWhich day of the month of April? ");
                            int reportDay = sc.nextInt();
                            roomMgr.getOccupancyReport(reportDay);
                        }
                        else {
                            System.out.print("\nWhich day of the month of April? ");
                            int reportDay = sc.nextInt();
                            roomMgr.getStatusReport(reportDay);
                        }
                    }
                    
                    break;
                case 3:
                    
                    // Entering of room number for roomServiceMgr
                    System.out.println("Please enter your room number: ");
                    String roomNumber = sc.nextLine();
                    
                    roomServiceMgr.setRoomNo(roomNumber);
                    
                    System.out.println("(1) Show Menu");
                    int roomServOption = sc.nextInt();
                    
                    if (roomServOption == 1) {
                            List<Item> itemOrder = new ArrayList(); //Create a stack of itemOrder list of items ordered                        
                        do {           
                            roomServiceMgr.showMenu();
                            System.out.println("Select item for further description and purchase. (-1 to exit)");
                            int foodSelectOption = sc.nextInt();
                            if (foodSelectOption == -1)
                                break;
                            roomServiceMgr.getItemDescription(foodSelectOption);
                            char decision = sc.next().charAt(0);
                            if (decision == 'y'){
                                itemOrder.add(MenuMgr.getItem(foodSelectOption)); // Add item to basket
                                System.out.println("Item " + MenuMgr.getItemName(foodSelectOption) + " is added to the basket.");
                            }
                        } while (true);
                        
                        if (!itemOrder.isEmpty()) {
                            sc.nextLine(); //flushing the null char
                            System.out.println("Any remarks to add? (Type N.A. if no remarks)");
                            String remarks = sc.nextLine();
                            roomServiceMgr.createOrder(itemOrder, remarks); //Create order object to prepare for addition of items
                            roomMgr.getPayment(roomNumber).setRoomServiceBill(choice);
                        }
                    }
                    System.out.println("");
                    
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6: 
                    break;
                case 7:
                    System.out.println("Program terminating ....");
            }
            
        } while (choice < 7);

        sc.close();
    }

}
