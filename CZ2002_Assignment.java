package cz2002_assignment;

import static cz2002_assignment.RoomMgr.roomStrToInt;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;


public class CZ2002_Assignment {

    public static final int maxRoomNoPerFloor = 8; //Maximum room per floor
    public static final int maxFloorNo = 7; //Maximum floor number for rooms
    public static final int minFloorNo = 2; //Minimum floor number for rooms
    public static final int currentDay = 1; //Current Day of system (Currently for April alone)
    public static final int laterDay = 4; //Current Day of system (Currently for April alone)
    
    
    public static void main(String[] args) {
        
        //Uploading all the XML files unto the arrays present in the mainApp
        XMLMgr xMLMgr = new XMLMgr();
        xMLMgr.fromXML();
        
        Scanner sc = new Scanner(System.in);
        int choice;
        
        // initialising of the related control classes
        ReservationMgr reservationMgr = new ReservationMgr();
        RoomMgr roomMgr = new RoomMgr(xMLMgr.getRoomData(), xMLMgr.getRoomCalData()); // Create all rooms
        RoomServiceMgr roomServiceMgr = new RoomServiceMgr();
        GuestMgr guestMgr = new GuestMgr();
        MenuMgr.updateList(xMLMgr.getItemMenu());
        PaymentMgr paymentMgr = new PaymentMgr(xMLMgr.getPaymentList());
        
        do {

            System.out.println("Welcome to ABC's Hotel CMS, what are your related matters?");
            System.out.println("(1) Reservation related");
            System.out.println("(2) Room related");
            System.out.println("(3) Room service related");
            System.out.println("(4) Payment related");
            System.out.println("(5) Guest related");
            System.out.println("(6) Exit");
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
                    
                    //Start with main method for reservationMgr
                    System.out.println("(1) Create Room Reservation");
                    System.out.println("(2) Update Room Reservation");
                    System.out.println("(3) Remove Room Reservation");
                    System.out.println("(4) Print Room Reservation");
                    System.out.print("\nEnter the number of your choice: ");
                    int reservationOption = sc.nextInt();
                    sc.nextLine();
                    
                    //Create room reservation   
                    if (reservationOption == 1) {
                        Guest g = null;
                        while (g == null) {
                            System.out.println("Please insert guest name for new reservation (-1 to exit): ");
                            String guestNameRes = sc.nextLine();
                            if (guestNameRes.contains("-1"))
                                break;
                            g = guestMgr.searchGuestByName(guestNameRes);
                            if (g == null) {
                                System.out.println("No such guest available.");
                                System.out.println("Do you want to add this guest? (y/n)");
                                String reply = sc.nextLine();
                                if (reply.contains("y")) {
                                    System.out.println("Initialising guest adding to database...");
                                    guestMgr.addGuest();
                                    g = guestMgr.searchGuestByName(guestNameRes);
                                    break;
                                }
                            }
                        }
                        
                            String guestRoomTypeRes = "";
                            do {
                                // Prevents error from happening due to enum not being available
                                System.out.println("Please insert room type for new reservation(single, double, twin, triple): ");
                                guestRoomTypeRes = sc.nextLine();
                            } while (!guestRoomTypeRes.contains("single") && !guestRoomTypeRes.contains("double") &&
                                    !guestRoomTypeRes.contains("twin") && !guestRoomTypeRes.contains("triple"));
                            System.out.println("Please insert number of adults: ");
                            int guestNumAd = sc.nextInt();
                            System.out.println("Please insert number of children: ");
                            int guestNumCh = sc.nextInt();
                            System.out.println("Please insert start day for reservation(Day within April 2016): ");
                            int guestStartDayRes = sc.nextInt();
                            System.out.println("Please insert end day for reservation(Day within April 2016): ");
                            int guestEndDayRes = sc.nextInt();
                            sc.nextLine(); //flush
                            System.out.println("Please insert your billing info: ");
                            String guestBillInfo = sc.nextLine();

                            // Check that room is vacant
                            // Does not give the choice of selecting other rooms to guests as of now.
                            // Returns first vacant room for the period wanted
                            Room roomVacant = roomMgr.checkVacantRoom(RoomType.valueOf(guestRoomTypeRes.toUpperCase()), 
                                    guestStartDayRes, guestEndDayRes);

                            if (roomVacant != null) {
                                // Reservation must be tagged to a room and guest.
                                // Each guest can only reserve 1 room
                                Reservation r = reservationMgr.createReservation(guestBillInfo, 
                                        new GregorianCalendar(2016, 4, guestStartDayRes), 
                                        new GregorianCalendar(2016, 4, guestEndDayRes), 
                                        guestNumAd, guestNumCh);

                                g.setReservation(r);
                                r.setRoom(roomVacant);
                                r.setGuest(g);

                                for (int q = guestStartDayRes; q <= guestEndDayRes; q ++) {
                                    roomVacant.getStatusCalendar(q).setStatus(RoomStatus.RESERVED);
                                    roomVacant.getStatusCalendar(q).setGuestName(g);
                                }

                                reservationMgr.printReservation(r);
                            }

                            else
                                System.out.println("No room available.");
                        
                        System.out.println("");
                    }
                    
                    //Update room reservation
                    else if (reservationOption == 2) {
                        System.out.println("Please enter your reservation code: ");
                        int resCode = sc.nextInt();
                        reservationMgr.updateReservation(resCode);
                    }
                    
                    //Remove room reservation
                    else if (reservationOption == 3) {
                    	//Need to check if reservation code is valid?
                    	System.out.println("Please enter your reservation code: ");
                    	int resCode = sc.nextInt();
                    	reservationMgr.removeReservation(resCode);
                    }
                    
                    //Print room reservation
                    else if (reservationOption == 4) {
                    	//Need to check if reservation code is valid?
                        System.out.println("(1) Print reservation by reservation code.");
                        System.out.println("(2) Print all available reservations.");
                        int reservationPrintOption = sc.nextInt();
                        
                        // Print reservation by code
                        if (reservationPrintOption == 1) {
                            System.out.println("Please enter your reservation code: ");
                            int resCode = sc.nextInt();
                            Reservation r = reservationMgr.searchReservationByCode(resCode);
                            reservationMgr.printReservation(r);
                        }
                        
                        // Print all reservation
                        else if (reservationPrintOption == 2){
                            Iterator<Reservation> resItr = reservationMgr.getReservationItr();
                            if (!resItr.hasNext())
                                System.out.println("No reservations availble.");
                            else {
                                while (resItr.hasNext()) {
                                    reservationMgr.printReservation(resItr.next());
                                }
                            }
                        }
                    }

                    System.out.println("Thank you for your patronage");
                    System.out.println("");
                    break;
                    
                case 2:
                    
                    //Start with main method for roomMgr
                    System.out.println("(1) Room Check in");
                    System.out.println("(2) Room Check out");
                    System.out.println("(3) Room Status Statistics Report");
                    System.out.println("(4) Room Availability Check");
                    System.out.println("(5) Update Room Details");
                    System.out.println("(6) Add new Room");
                    System.out.print("\nEnter the number of your choice: ");
                    int roomOption = sc.nextInt();
                    sc.nextLine();
                    
                    // Check in procedures
                    if (roomOption == 1) {
                        System.out.print("Please enter room number (e.g. 02-05): ");
                        String roomNoCI = sc.nextLine();
                        
                        //Check if room number is present in system
                        if (roomNoCheck(roomNoCI, roomMgr.getRoomData()) == true) {
                            
                            System.out.println("Please insert name of guest: ");
                            String resGuestName = sc.nextLine();
                            Guest g = guestMgr.searchGuestByRoom(roomNoCI);
                            
                            if (g == null) {
                                //Walk-in check-in
                                
                                //Check if guest is already in database. If no, initialise the adding of guest
                                g = guestMgr.searchGuestByName(resGuestName);
                                if (g == null){
                                    System.out.println("Initialising guest adding to database...");
                                    guestMgr.addGuest();
                                }
                                
                                System.out.println("Please insert check-out date: ");
                                int roomCOD = sc.nextInt();
                                
                                boolean roomNotVacant = false;
                                for (int i = currentDay; i <= roomCOD; i ++) {
                                    if (!roomMgr.getRoom(roomNoCI).getRoomStatus(i).equals(RoomStatus.VACANT)) {
                                        System.out.println("Room not vacant on the " + i + "th of April.");
                                        roomNotVacant = true;
                                    }
                                }
                                
                                if (roomNotVacant == false) {
                                    roomMgr.checkIn(roomNoCI, currentDay, roomCOD); 
                                    g.setRoom(RoomMgr.getRoom(roomNoCI));
                                    System.out.println("You have checked in to " + roomNoCI + " successfully.");
                                    //Create a new payment class that is associated with the room 
                                    //This ensures a new payment will be available once checked in
                                    paymentMgr.newPayment(roomStrToInt(roomNoCI)-1);
                                }
                            }
                            
                            else {
                            // check in by reservation
                                if (resGuestName.equals(g.getName())) {
                                    Reservation r = reservationMgr.searchReservationByName(resGuestName);
                                    roomMgr.checkIn(roomNoCI, r);
                                    System.out.println("Congratulations " + g.getName() + "You have checked in to " + 
                                            roomNoCI + " successfully.");
                                    paymentMgr.newPayment(roomStrToInt(roomNoCI)-1);
                                }
                                else
                                    System.out.println("Wrong name.");
                            }
                        }
                        
                        System.out.println("");
                    }
                    
                    //Check out procedures
                    else if (roomOption == 2) {
                        System.out.println("Please enter room number (e.g. 02-05): ");
                        String roomNoCO = sc.nextLine();
                        
                        //Check if room number is present in system
                        if (roomNoCheck(roomNoCO, roomMgr.getRoomData()) == true) {
                            
                            if (roomMgr.getRoom(roomNoCO).getRoomStatus(laterDay) == RoomStatus.OCCUPIED){
                                
                                roomMgr.checkOut(roomNoCO, laterDay); 
                                System.out.println("You have checked out of " + roomNoCO + " successfully.");
                                Room r = RoomMgr.getRoom(roomNoCO);
                                Guest guest = r.getStatusCalendar(laterDay).getGuest();
                                guest.setRoom(null);;
                                r.getStatusCalendar(laterDay).setGuestName(null);
                                
                                // Calculate and Print payment
                                paymentMgr.getPayment(roomNoCO).calRoomChargesBill(roomNoCO, currentDay, laterDay);
                                paymentMgr.printBillInvoice(roomStrToInt(roomNoCO), roomServiceMgr.getOrders(roomStrToInt(roomNoCO)));
                                
                            }
                        }
                        System.out.println("");
                    }
                    
                    // Room status statistics Report
                    else if (roomOption == 3) {
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
                    
                    //Check Room Availability
                    else if (roomOption == 4) {
                        System.out.print("Please enter room number (e.g. 02-05): ");
                        String roomNoCA = sc.nextLine();
                        
                        //Check if room number is present in system
                        if (roomNoCheck(roomNoCA, roomMgr.getRoomData())) {
                            
                            System.out.print("Please the day to check : ");
                            int checkDay = sc.nextInt();
                            
                            if (roomMgr.getRoom(roomNoCA).getRoomStatus(checkDay).equals(RoomStatus.VACANT)){
                                System.out.println("Room " + roomNoCA + " is available on " + checkDay + " April.");
                            }
                            else {
                                System.out.println("Room " + roomNoCA + " is not available on " + checkDay + " April.");
                            }
                        }
                        System.out.println("");
                    }
                    
                    //Update Room Details
                    else if (roomOption == 5) {
                        System.out.print("Please enter room number (e.g. 02-05): ");
                        String roomNoURD = sc.nextLine();
                        
                        //Check if room number is present in system
                        if (roomNoCheck(roomNoURD, roomMgr.getRoomData())) {
                            
                            int updateOption = -1;
                            
                            while(updateOption != 0) {
                                System.out.println("-------------------------------------------");
                                System.out.println("Room " + roomMgr.getRoom(roomNoURD).getRoomNo());
                                System.out.println("(1) Room Type       : " + roomMgr.getRoom(roomNoURD).getRoomType().toString());
                                System.out.println("(2) Wifi Enabled    : " + (roomMgr.getRoom(roomNoURD).getIsWifiEnabled() ? "YES" : "NO") );
                                System.out.println("(3) Smoking Allowed : " + (roomMgr.getRoom(roomNoURD).getIsSmokingAllowed() ? "YES" : "NO") );
                                System.out.println("(4) Room Face View  : " + roomMgr.getRoom(roomNoURD).getFaceView());
                                System.out.println("(5) Room Status");
                                System.out.println("(0) Back to Main Menu");

                                System.out.print("Update Option : ");
                                updateOption = sc.nextInt();
                                
                                if (updateOption == 1){
                                    System.out.println("(1) Single");
                                    System.out.println("(2) Double");
                                    System.out.println("(3) Twin");
                                    System.out.println("(4) Triple");
                                    System.out.print("Change Room Type to : ");
                                    
                                    int updateRoomType = sc.nextInt();
                                    if (updateRoomType == 1) {
                                        roomMgr.getRoom(roomNoURD).setRoomType(RoomType.SINGLE);
                                    }
                                    else if (updateRoomType == 2) {
                                        roomMgr.getRoom(roomNoURD).setRoomType(RoomType.DOUBLE);
                                    }
                                    else if (updateRoomType == 3) {
                                        roomMgr.getRoom(roomNoURD).setRoomType(RoomType.TWIN);
                                    }
                                    else if (updateRoomType == 4) {
                                        roomMgr.getRoom(roomNoURD).setRoomType(RoomType.TRIPLE);
                                    }
                                    
                                    System.out.println("Room " + roomMgr.getRoom(roomNoURD).getRoomNo() 
                                            + " Room Type changed to " + roomMgr.getRoom(roomNoURD).getRoomType().toString());
                                
                                }
                                else if (updateOption == 2) {
                                    roomMgr.getRoom(roomNoURD).setWifiEnabled(!roomMgr.getRoom(roomNoURD).getIsWifiEnabled());
                                    System.out.println("Room " + roomMgr.getRoom(roomNoURD).getRoomNo() 
                                            + " Wifi Enabled status switched to " + (roomMgr.getRoom(roomNoURD).getIsWifiEnabled() ? "YES" : "NO"));
                                }
                                else if (updateOption == 3) {
                                    roomMgr.getRoom(roomNoURD).setSmokingAllowed(!roomMgr.getRoom(roomNoURD).getIsSmokingAllowed());
                                    System.out.println("Room " + roomMgr.getRoom(roomNoURD).getRoomNo() 
                                            + " Smoking Allowance switched to " + (roomMgr.getRoom(roomNoURD).getIsSmokingAllowed() ? "YES" : "NO"));
                                }
                                else if (updateOption == 4) {
                                    sc.nextLine(); //Consume previous nextInt trailing space 
                                    System.out.print("New Room Face View Description : ");
                                    String updateFaceView = sc.nextLine();
                                    roomMgr.getRoom(roomNoURD).setFaceView(updateFaceView);
                                    System.out.println("Room " + roomMgr.getRoom(roomNoURD).getRoomNo() 
                                            + " Face View description changed to " + roomMgr.getRoom(roomNoURD).getFaceView());
                                }
                                else if (updateOption == 5) {
                                    for (int i = 1; i <= roomMgr.getRoom(roomNoURD).getStatusCalendar().length; i++) {
                                        System.out.print(String.format("%3s - %-18s", "" + i, roomMgr.getRoom(roomNoURD).getRoomStatus(i)));
                                        if (i % 7 == 0) {
                                            System.out.println("");
                                        }
                                        else {
                                            System.out.print("|");
                                        }
                                    }
                                    System.out.println("");
                                    System.out.print("Day to update : ");
                                    int updateDay = sc.nextInt();
                                    
                                    System.out.println("(1) Vacant");
                                    System.out.println("(2) Occupied");
                                    System.out.println("(3) Reserved");
                                    System.out.println("(4) Under maintenance");
                                    System.out.print("Update " + updateDay + " to : ");
                                    
                                    int newStatus = sc.nextInt();
                                    
                                    if (newStatus == 1) {
                                        roomMgr.getRoom(roomNoURD).setRoomStatus(RoomStatus.VACANT, updateDay);
                                    }
                                    else if (newStatus == 2) {
                                        roomMgr.getRoom(roomNoURD).setRoomStatus(RoomStatus.OCCUPIED, updateDay);
                                    }
                                    else if (newStatus == 3) {
                                        roomMgr.getRoom(roomNoURD).setRoomStatus(RoomStatus.RESERVED, updateDay);
                                    }
                                    else if (newStatus == 4) {
                                        roomMgr.getRoom(roomNoURD).setRoomStatus(RoomStatus.UNDER_MAINTENANCE, updateDay);
                                    }
                                    System.out.println("Room " + roomMgr.getRoom(roomNoURD).getRoomNo() + " Room Status on " 
                                            + updateDay + " April changed to " + roomMgr.getRoom(roomNoURD).getRoomStatus(updateDay));
                                    }
                            }
                        }
                    }  
                    
                    else if (roomOption == 6) {
                        System.out.print("Please enter new room number (e.g. 02-05): ");
                        String roomNoANR = sc.nextLine();
                        
                        //Check if room number is not present in system
                        if (!roomNoCheck(roomNoANR, roomMgr.getRoomData())) {
                            System.out.print("\nIs room wifi enabled?(Y/N) : ");
                            boolean hasWifi = sc.nextLine().trim().toUpperCase().contentEquals("Y");
                            
                            System.out.print("\nCan guests smoke in room?(Y/N) : ");
                            boolean canSmoke = sc.nextLine().trim().toUpperCase().contentEquals("Y");
                            
                            System.out.print("\nRoom face view description : ");
                            String faceViewDes = sc.nextLine();
                            
                            System.out.print("\nRoom Types");
                            System.out.print("\n(1) Single");
                            System.out.print("\n(2) Double");
                            System.out.print("\n(3) Twin");
                            System.out.print("\n(4) Triple");
                            System.out.print("\nRoom type selection : ");
                            String roomTypeSelection = sc.nextLine();
                            
          
                            if (roomTypeSelection.contentEquals("1")) {
                                roomMgr.addRoom(roomNoANR.replace("-", ""), hasWifi, faceViewDes, canSmoke, RoomType.SINGLE);
                            }
                            else if (roomTypeSelection.contentEquals("2")) {
                                roomMgr.addRoom(roomNoANR.replace("-", ""), hasWifi, faceViewDes, canSmoke, RoomType.DOUBLE);
                            }
                            else if (roomTypeSelection.contentEquals("3")) {
                                roomMgr.addRoom(roomNoANR.replace("-", ""), hasWifi, faceViewDes, canSmoke, RoomType.TWIN);
                            }
                            else if (roomTypeSelection.contentEquals("4")) {
                                roomMgr.addRoom(roomNoANR.replace("-", ""), hasWifi, faceViewDes, canSmoke, RoomType.TRIPLE);
                            }
                            
                            System.out.println("Room " + roomNoANR + " added.");
                            roomMgr.displayRoomDetails(roomNoANR);
                            
                        }
                        else {
                            System.out.println("Room number exists.");
                        }
                    }                    
                    
                    System.out.println("");
                    
                    break;
                    
                case 3:
                    
                    System.out.println("(1) Order room service menu items");
                    System.out.println("(2) Create room service menu items");
                    System.out.println("(3) Update room service menu items");
                    System.out.println("(4) Remove room service menu items");
                    int roomSOption = sc.nextInt();
                    sc.nextLine(); //Flush null char
                    
                    // Start of ordering room service menu items
                    if (roomSOption == 1) {
                        
                        // Entering of room number for roomServiceMgr
                        System.out.println("Please enter your room number: ");
                        String roomNumber_3 = sc.nextLine();

                        //Check room
                        if (roomNoCheck(roomNumber_3, roomMgr.getRoomData()) == false)
                            break;
                        System.out.println("Room is " + roomMgr.getRoom(roomNumber_3).getRoomStatus(currentDay));
                        System.out.println("");
                        if (!roomMgr.getRoom(roomNumber_3).getRoomStatus(currentDay).equals(RoomStatus.OCCUPIED)){
                            break;
                        }

                        System.out.println("(1) Show Menu (Adding of orders)");
                        System.out.println("(2) Check order");
                        System.out.println("(3) Remove order");
                        int roomServOption = sc.nextInt();
                        sc.nextLine() ; // flush away null character

                        if (roomServOption == 1) {
                                //Create a stack of itemOrder list of items ordered locally                       
                                List<Item> itemOrder = new ArrayList(); 
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

                                //Create order object to prepare for addition of items
                                double bill = roomServiceMgr.createOrder(roomNumber_3 ,itemOrder, remarks); 
                                paymentMgr.getPayment(roomNumber_3).addRoomServiceBill(bill);
                            }
                        }

                        // Checking of orders
                        else  if (roomServOption == 2) {
                            if (roomServiceMgr.checkOrder(roomNumber_3) == false){ //Checking that there are orders present
                                System.out.println("No orders available.");
                                System.out.println("");
                                break;
                            }
                            System.out.println("Please input the order code if already known. Else type 1 to list all orders available.");
                            String orderCodeNo = sc.nextLine();
                            roomServiceMgr.getOrder(roomNumber_3, orderCodeNo);
                        }

                        // Removing of orders
                        else  if (roomServOption == 3) {
                            if (roomServiceMgr.checkOrder(roomNumber_3) == false){ //Checking that there are orders present
                                System.out.println("No orders available.");
                                System.out.println("");
                                break;
                            }
                            System.out.println("Please input the order code if already known. Else type 1 to remove all orders available.");
                            String orderCodeNo = sc.nextLine();
                            roomServiceMgr.removeOrder(roomNumber_3, orderCodeNo);

                        }
                    }
                    
                    // Start of creation for room service items
                    else if (roomSOption == 2) {
                        System.out.println("Please insert name of food: ");
                        String nameItemCreate = sc.nextLine();
                        System.out.println("Please insert description of food: ");
                        String descItemCreate = sc.nextLine();
                        System.out.println("Please insert price of food: ");
                        double priceItemCreate = sc.nextDouble();
                        
                        MenuMgr.createItem(nameItemCreate, descItemCreate, priceItemCreate);
                        System.out.println("Item created.");
                    }
                    
                    // Start of update for room service items
                    else if (roomSOption == 3) {
                        System.out.println("Please insert name of item to be updated: ");
                        String nameItemUpdate = sc.nextLine();
                        int itemMenuIndex = MenuMgr.searchItem(nameItemUpdate);
                        
                        if (itemMenuIndex != -1){
                            System.out.println("Please select what you want to update: ");
                            System.out.println("(1) Name of item");
                            System.out.println("(2) Description of item");
                            System.out.println("(3) Price of item");
                            System.out.println("(4) Everything");
                            System.out.println("(5) None of the above");
                            int itemUpdateChoice = sc.nextInt();
                            sc.nextLine();
                            
                            if (itemUpdateChoice == 1) {
                                System.out.println("Please insert new name: ");
                                String itemNewName = sc.nextLine();
                                MenuMgr.updateItemN(itemMenuIndex, itemNewName);
                                System.out.println("Name is updated.");
                            }
                            else if (itemUpdateChoice == 2){
                                System.out.println("Please insert new description: ");
                                String itemNewDescription = sc.nextLine();
                                MenuMgr.updateItemD(itemMenuIndex, itemNewDescription);
                                System.out.println("Description is updated.");
                            }
                            else if (itemUpdateChoice == 3){
                                System.out.println("Please insert new price: ");
                                double itemNewPrice = sc.nextDouble();
                                MenuMgr.updateItemP(itemMenuIndex, itemNewPrice);
                                System.out.println("Price is updated.");
                            }
                            else if (itemUpdateChoice == 4){
                                System.out.println("Please insert new name: ");
                                String itemNewName = sc.nextLine();
                                MenuMgr.updateItemN(itemMenuIndex, itemNewName);
                                System.out.println("Please insert new description: ");
                                String itemNewDescription = sc.nextLine();
                                MenuMgr.updateItemD(itemMenuIndex, itemNewDescription);
                                System.out.println("Please insert new price: ");
                                double itemNewPrice = sc.nextDouble();
                                MenuMgr.updateItemP(itemMenuIndex, itemNewPrice);
                                System.out.println("Item is updated.");
                            }
                        }
                    }
                    
                    // Start of removal of room service items
                    else if (roomSOption == 4) {
                        System.out.println("Please insert name of item to be removed: ");
                        String nameItemRemove = sc.nextLine();
                        int itemMenuIndex = MenuMgr.searchItem(nameItemRemove);
                        if (itemMenuIndex != -1) {
                            MenuMgr.removeItem(itemMenuIndex);
                            System.out.println("Item is removed.");
                        }
                    }
                    
                    System.out.println("");
                    
                    break;
                    
                case 4:
                    
                    // Entering of room number for Payment
                    System.out.println("Please enter your room number: ");
                    String roomNumber_4 = sc.nextLine();
                    
                    //Check if room number is present in system
                    if (roomNoCheck(roomNumber_4, roomMgr.getRoomData()) == false)
                        break;
                    
                    // Check if room number is occupied
                    System.out.println("Room is " + roomMgr.getRoom(roomNumber_4).getRoomStatus(currentDay));
                    System.out.println("");
                    if (!roomMgr.getRoom(roomNumber_4).getRoomStatus(currentDay).equals(RoomStatus.OCCUPIED)){
                        break;
                    }
                    
                    //Start with main method for Payment
                    System.out.println("(1) Check Bill");
                    System.out.println("(2) Pay total Bill");
                    int paymentOption = sc.nextInt();
                    sc.nextLine() ; // flush away null character
                    
                    //Show total bill
                    if (paymentOption == 1) {
                        System.out.println("(1) Check for room charges");
                        System.out.println("(2) Check for room service bill");    
                        System.out.println("(3) Check for total bill");    
                        int billPaymentCheckOption = sc.nextInt();
                       
                        // Checking for room charges
                        if (billPaymentCheckOption == 1){
                            Room r = RoomMgr.getRoom(roomNumber_4);
                                double rate = r.getStatusCalendar(currentDay).getRate();
                                double roomTypeRate = r.getRoomType().getRate();
                                double roomChargesBill = rate * roomTypeRate;
                            System.out.printf("Room charges for today is: $%.2f\n", roomChargesBill);
                        }
                        
                        // Checking for room service bills
                        else if (billPaymentCheckOption==2){
                            System.out.printf("$%.2f\n", paymentMgr.getPayment(roomNumber_4).getRoomServiceBill());
                        }
                        
                        // Checking for subtotal bills
                        else if (billPaymentCheckOption==3){
                            System.out.printf("$%.2f\n", paymentMgr.getPayment(roomNumber_4).getTotalBill());
                        }
                    }
                    
                    //Pay total Bill
                    else if (paymentOption == 2) {
                        //Check if person has checked out from room by comparing timeStamp
                        //Check for total bill
                        double totalBill = paymentMgr.getPayment(roomNumber_4).getTotalBill()*
                                paymentMgr.getPayment(roomNumber_4).getTaxes();
                        System.out.printf("Total bill is: $%.2f", totalBill);
                        System.out.println("Would you like to pay by credit or cash?");
                        String paymentMethod = sc.nextLine();
                        if (paymentMethod.contains("credit")) {
                            System.out.println("Thank you for paying by credit.");
                        }
                        else if (paymentMethod.contains("Cash")) 
                            System.out.println("Thank you for paying by cash.");
                        
                        paymentMgr.newPayment(roomStrToInt(roomNumber_4));
                        System.out.println("\nHope to see you soon!\n");
                    }
                    
                    System.out.println("");
                    
                    break;
                    
                case 5:
                    
                    //Start of main method for Guest class
                    System.out.println("(1) Add new guest details");
                    System.out.println("(2) Check guest details");
                    System.out.println("(3) Update guest details");
                    int guestOption = sc.nextInt();   
                    sc.nextLine(); // Flushing of null character
                    
                    // Adding new guest details
                    if (guestOption == 1) {
                        guestMgr.addGuest();
                    }
                    
                    // Checking for guest details
                    else if (guestOption == 2) {
                        System.out.println("(1) Search by name");
                        System.out.println("(2) Search by room number");
                        int guestSearchOption = sc.nextInt();     
                        sc.nextLine(); //Flush away null character
                        
                        // Searching of guest by name
                        if (guestSearchOption == 1) {
                            System.out.println("Please insert name of guest (part of or whole) to be searched:");
                            String guestSearchName = sc.nextLine();
                            Guest g = guestMgr.searchGuestByName(guestSearchName);
                            if (g != null)
                                guestMgr.printGuestDetails(g);
                        }
                        
                        // Searching of guests through Room number
                        else if (guestSearchOption == 2) {
                            System.out.println("Please insert room number of guest to be searched:");
                            String guestSearchRoom = sc.nextLine();
                            Guest g = guestMgr.searchGuestByRoom(guestSearchRoom);
                            if (g != null)
                                guestMgr.printGuestDetails(g);
                            else if (roomMgr.getRoom(guestSearchRoom).getRoomStatus(currentDay) != (RoomStatus.OCCUPIED))
                                System.out.println("No guest is currently living in the room.");
                        }
                    }
                    
                    // Updating of guest details
                    else if (guestOption == 3) {
                        System.out.println("Please insert name of guest (part of or whole) to be searched:");
                        String guestSearchName = sc.nextLine();
                        Guest g = guestMgr.searchGuestByName(guestSearchName);
                        if (g != null) {
                            guestMgr.printGuestDetails(g);
                            System.out.println("(1) Update name");
                            System.out.println("(2) Update gender");
                            System.out.println("(3) Update identity");
                            System.out.println("(4) Update address");
                            System.out.println("(5) Update nationality");
                            System.out.println("(6) Update contact");
                            System.out.println("(7) Update credit card details");
                            System.out.println("(8) Update everything");
                            int guestUpdateOption = sc.nextInt();
                            sc.nextLine(); //flush
                            //Update name
                            if (guestUpdateOption == 1) {
                                System.out.println("Please insert new name: ");
                                String newGuestName = sc.nextLine();
                                g.setName(newGuestName);
                                System.out.println("Details updated.");
                            }
                            // Update gender
                            else if (guestUpdateOption == 2) {
                                System.out.println("Please insert new gender: ");
                                String newGuestGender = sc.nextLine();
                                g.setGender(newGuestGender);
                                System.out.println("Details updated.");
                            }
                            // Update identity
                            else if (guestUpdateOption == 3) {
                                System.out.println("Please insert new IC: ");
                                String newGuestIC = sc.nextLine();
                                g.setIdentity(newGuestIC);
                                System.out.println("Details updated.");
                            }
                            // Update address
                            else if (guestUpdateOption == 4) {
                                System.out.println("Please insert new address: ");
                                String newGuestAddress = sc.nextLine();
                                g.setAddress(newGuestAddress);
                                System.out.println("Details updated.");
                            }
                            // Update nationality
                            else if (guestUpdateOption == 5) {
                                System.out.println("Please insert new nationality: ");
                                String newGuestNat = sc.nextLine();
                                g.setNationality(newGuestNat);
                                System.out.println("Details updated.");
                            }
                            // Update contact
                            else if (guestUpdateOption == 6) {
                                System.out.println("Please insert new contact: ");
                                String newGuestCont = sc.nextLine();
                                g.setContact(Integer.parseInt(newGuestCont));
                                System.out.println("Details updated.");
                            }
                            // Update credit card details
                            else if (guestUpdateOption == 7) {
                                System.out.println("Please insert new credit card details: ");
                                String newGuestCCD = sc.nextLine();
                                g.setCreditCardDet(newGuestCCD);
                                System.out.println("Details updated.");
                            }
                            // Update everything
                            else if (guestUpdateOption == 8) {
                                System.out.println("Please insert new name: ");
                                String newGuestName = sc.nextLine();
                                g.setName(newGuestName);
                                System.out.println("Please insert new gender: ");
                                String newGuestGender = sc.nextLine();
                                g.setGender(newGuestGender);                                
                                System.out.println("Please insert new IC: ");
                                String newGuestIC = sc.nextLine();
                                g.setIdentity(newGuestIC);        
                                System.out.println("Please insert new address: ");
                                String newGuestAddress = sc.nextLine();
                                g.setAddress(newGuestAddress);                                  
                                System.out.println("Please insert new nationality: ");
                                String newGuestNat = sc.nextLine();
                                g.setNationality(newGuestNat);            
                                System.out.println("Please insert new contact: ");
                                String newGuestCont = sc.nextLine();
                                g.setContact(Integer.parseInt(newGuestCont));
                                System.out.println("Please insert new credit card details: ");
                                String newGuestCCD = sc.nextLine();
                                g.setCreditCardDet(newGuestCCD);
                                System.out.println("Details updated.");
                            }
                        }
                    }
                    
                    System.out.println("");
                    
                    break;
                case 6: 
                    xMLMgr.toXML(paymentMgr.getPaymentList());
                    System.out.println("Program terminating ....");
                    break;
            }
            
        } while (choice < 6);

        sc.close();
    }
    
    // Check if room number is present
    public static boolean roomNoCheck(String roomN, Room[] rooms) {
       
        for (int i = 0; i < rooms.length; i++) {
            //System.out.println(rooms[i].getRoomNo());
            if (rooms[i].getRoomNo().contains(roomN.replace("-", ""))) {
                return true;
            }
        }
        
        return false;
    }
}

