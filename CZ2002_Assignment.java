package cz2002_assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.dom.DOMSource; 
import javax.xml.transform.stream.StreamResult; 

public class CZ2002_Assignment {

    private static final int maxRoomNoPerFloor = 8; //Maximum room per floor
    private static final int maxFloorNo = 7; //Maximum floor number for rooms
    private static final int minFloorNo = 2; //Minimum floor number for rooms
    private static final int currentDay = 12; //Current Day of system (Current for April alone)
    private static final int laterDay = 15; //Current Day of system (Current for April alone)
    private static List<Guest> guestList;
    
    
    /*
    ** 
    */
    //PLEASE EDIT THIS FILE FOR IT TO WORK!!
    //Point to the location of guestListXML file.
    private static final String guestXMLFilePath = "D:\\Documents\\NetBeansProjects\\CZ2002_Assignment\\src\\cz2002_assignment\\XML\\guestList.xml";
    //*****
    private static Document doc;
    private static File guestFile = new File(guestXMLFilePath);
    /*
    ** End declaration of variables for conversion to XML files
    */
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        
        // initialising of the related control classes
        ReservationMgr reservationMgr = new ReservationMgr();
        RoomMgr roomMgr = new RoomMgr(currentDay); // Create all rooms
        RoomServiceMgr roomServiceMgr = new RoomServiceMgr();
        guestList = new ArrayList(); //initialise a list for all guests that registered under this hotel
        
        
        // importing of guestList data from XML file
        try {	
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(guestFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Guest");
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    guestList.add(new Guest(eElement.getElementsByTagName("name").item(0).getTextContent(),
                            eElement.getElementsByTagName("gender").item(0).getTextContent(),
                            eElement.getElementsByTagName("identity").item(0).getTextContent(),
                            eElement.getElementsByTagName("address").item(0).getTextContent(),
                            eElement.getElementsByTagName("nationality").item(0).getTextContent(),
                            Integer.parseInt(eElement.getElementsByTagName("contact").item(0).getTextContent()),
                            eElement.getElementsByTagName("creditCardDet").item(0).getTextContent()));
                }
            }
        }   
        catch (Exception e) {e.printStackTrace();}
        // End import for guest XML file   
        
        do {

            System.out.println("Welcome to ABC's Hotel CMS, what are your related matters?");
            System.out.println("(1) Reservation related");
            System.out.println("(2) Room related");
            System.out.println("(3) Room service related");
            System.out.println("(4) Payment related");
            System.out.println("(5) Guest related");
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
                    
                    //Start with main method for roomMgr
                    System.out.println("(1) Room Check in");
                    System.out.println("(2) Room Check out");
                    System.out.println("(3) Room Status Statistics Report");
                    System.out.print("\nEnter the number of your choice: ");
                    int roomOption = sc.nextInt();
                    sc.nextLine();
                    
                    // Check in procedures
                    if (roomOption == 1) {
                        System.out.println("Please enter room number (e.g. 02-05): ");
                        String roomNoCI = sc.nextLine();
                        
                        //Check if room number is present in system
                        if (roomNoCheck(roomNoCI) == true) {
                            
                            //Check if room number is available for use within system
                            if (!roomMgr.getRoom(roomNoCI).getRoomStatus(currentDay).equals("vacant")){
                                roomMgr.checkIn(roomNoCI, currentDay); 
                                System.out.println("You have checked in to " + roomNoCI + " successfully.");
                            }
                        }
                        System.out.println("");
                    }
                    
                    //Check out procedures
                    else if (roomOption == 2) {
                        System.out.println("Please enter room number (e.g. 02-05): ");
                        String roomNoCO = sc.nextLine();
                        
                        //Check if room number is present in system
                        if (roomNoCheck(roomNoCO) == true) {
                            //Check if room number is available for use within system
                            if (!roomMgr.getRoom(roomNoCO).getRoomStatus(laterDay).equals("occupied")){
                                roomMgr.checkOut(roomNoCO, laterDay); 
                                System.out.println("You have checked in to " + roomNoCO + " successfully.");
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
                    System.out.println("");
                    
                    break;
                case 3:
                    
                    // Entering of room number for roomServiceMgr
                    System.out.println("Please enter your room number: ");
                    String roomNumber_3 = sc.nextLine();
                    
                    //Check if room number is present in system
                    if (roomNoCheck(roomNumber_3) == false)
                        break;
                    
                    // Check if room number is occupied
                    System.out.println("Room is " + roomMgr.getRoom(roomNumber_3).getRoomStatus(currentDay));
                    System.out.println("");
                    if (!roomMgr.getRoom(roomNumber_3).getRoomStatus(currentDay).equals("Occupied")){
                        break;
                    }
                    // End check room
                                       
                    //Start with main method for roomService
                    System.out.println("(1) Show Menu (Adding of orders)");
                    System.out.println("(2) Check order");
                    System.out.println("(3) Remove order");
                    int roomServOption = sc.nextInt();
                    sc.nextLine() ; // flush away null character
                    
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
                            
                            //Create order object to prepare for addition of items
                            double bill = roomServiceMgr.createOrder(roomNumber_3 ,itemOrder, remarks); 
                            roomMgr.getPayment(roomNumber_3).setRoomServiceBill(bill);
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
                    System.out.println("");
                    
                    break;
                case 4:
                    
                    // Entering of room number for Payment
                    System.out.println("Please enter your room number: ");
                    String roomNumber_4 = sc.nextLine();
                    
                    //Check if room number is present in system
                    if (roomNoCheck(roomNumber_4) == false)
                        break;
                    
                    // Check if room number is occupied
                    System.out.println("Room is " + roomMgr.getRoom(roomNumber_4).getRoomStatus(currentDay));
                    System.out.println("");
                    if (!roomMgr.getRoom(roomNumber_4).getRoomStatus(currentDay).equals("Occupied")){
                        break;
                    }
                    // End check room
                    
                    //Start with main method for Payment
                    System.out.println("(1) Check Bill");
                    System.out.println("(2) Pay total Bill");
                    int paymentOption = sc.nextInt();
                    sc.nextLine() ; // flush away null character
                    
                    //Show total bill
                    if (paymentOption == 1) {
                        System.out.println("(1) Check for room charges");
                        System.out.println("(2) Check for room service bill");    
                        System.out.println("(2) Check for total bill");    
                        int billPaymentCheckOption = sc.nextInt();
                       
                        // Checking for room charges
                        if (billPaymentCheckOption == 1){
                            roomMgr.getPayment(roomNumber_4).setRoomChargesBill();
                        }
                        
                        else if (billPaymentCheckOption==2){
                            roomMgr.getPayment(roomNumber_4).getRoomServiceBill();
                        }
                        
                        else if (billPaymentCheckOption==3){
                            roomMgr.getPayment(roomNumber_4).getTotalBill();
                        }
                    }
                    
                    //Pay total Bill
                    else if (paymentOption == 2) {
                        //Check if person has checked out from room by comparing timeStamp
                        //Check for total bill
                        //Ask for payment method
                    }
                    
                    System.out.println("");
                    
                    break;
                case 5:
                    
                    // initialise a iterator
                    Iterator<Guest> guestListItr = guestList.iterator();
                    
                    //Start of main method for Guest class
                    System.out.println("(1) Add new guest details");
                    System.out.println("(2) Check guest details");
                    int guestOption = sc.nextInt();   
                    sc.nextLine(); // Flushing of null character
                    
                    // Adding new guest details
                    if (guestOption == 1) {
                        System.out.println("Please insert name of guest to be added: ");
                        String name2B = sc.nextLine();
                        System.out.println("Please insert gender of guest to be added: ");
                        String gender2B = sc.nextLine();
                        System.out.println("Please insert address of guest to be added: ");
                        String address2B = sc.nextLine();
                        System.out.println("Please insert identity of guest to be added: ");
                        String identity2B = sc.nextLine();
                        System.out.println("Please insert nationality of guest to be added: ");
                        String nat2B = sc.nextLine();
                        System.out.println("Please insert contact of guest to be added: ");
                        String contact2 = sc.nextLine();
                        int contact2B = Integer.parseInt(contact2);
                        System.out.println("Please insert credit card details of guest to be added: ");
                        String ccd2B = sc.nextLine();
                        guestList.add(new Guest(name2B, gender2B, address2B, identity2B, nat2B, contact2B, ccd2B));
                        
                        //Start of outputting file into guestList.XML
                        try {
                            // Initialise the document builder
                            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                            DocumentBuilder builder = dbFactory.newDocumentBuilder();
                            doc = builder.parse(guestFile);
                            // end initialisation of document builder
                            
                            Node root = doc.getDocumentElement();
                            
                            Element guest = doc.createElement("Guest");
                            Element name = doc.createElement("name");
                            Element gen = doc.createElement("gender");
                            Element add = doc.createElement("identity");
                            Element id = doc.createElement("address");
                            Element nat = doc.createElement("nationality");
                            Element contact = doc.createElement("contact");
                            Element ccd = doc.createElement("creditCardDet");
                            
                            name.appendChild(doc.createTextNode(name2B));
                            gen.appendChild(doc.createTextNode(gender2B));
                            add.appendChild(doc.createTextNode(address2B));
                            id.appendChild(doc.createTextNode(identity2B));
                            nat.appendChild(doc.createTextNode(nat2B));
                            contact.appendChild(doc.createTextNode(contact2));
                            ccd.appendChild(doc.createTextNode(ccd2B));
                            
                            root.appendChild(guest);
                            guest.appendChild(name);
                            guest.appendChild(gen);
                            guest.appendChild(add);
                            guest.appendChild(id);
                            guest.appendChild(nat);
                            guest.appendChild(contact);
                            guest.appendChild(ccd);
                            
                            // Start output file through transformer to guestFile
                            TransformerFactory tFactory = TransformerFactory.newInstance();
                            Transformer transformer = tFactory.newTransformer();
                            DOMSource source = new DOMSource(doc);
                            StreamResult result = new StreamResult(guestFile);
                            transformer.transform(source, result); 
                            System.out.println("Done");

                        }
                        catch (Exception e) {System.out.println(e.getMessage());}

                        //End of output file
                    }
                    //End of adding new guest details
                    
                    // Checking for guest details
                    else if (guestOption == 2) {
                        System.out.println("(1) Search by name");
                        System.out.println("(2) Search by room number");
                        int guestSearchOption = sc.nextInt();     
                        sc.nextLine(); //Flush away null character
                        
                        if (guestSearchOption == 1) {
                            System.out.println("Please insert name of guest (part of or whole) to be searched:");
                            String guestSearchName = sc.nextLine();
                            boolean guestFound = false;
                            
                            while (guestListItr.hasNext()){
                                Guest g = guestListItr.next();
                                if (g.getName().contains(guestSearchName)){
                                    System.out.println("Is the guest name " + g.getName() + "? (y/n)");
                                    String guestNameConfirm = sc.nextLine();
                                    
                                    if (guestNameConfirm.equals("y")) {
                                        //print out details
                                        g.printDetails();
                                        guestFound = true;
                                        break;
                                    }
                                }
                            }
                            
                        if (guestFound == false)
                            System.out.println("No such name of guest is available.");
                        }
                        
                        // Searching of guests through Room number not yet implemented
                        else if (guestSearchOption == 2) {
                            System.out.println("Please insert room number of guest to be searched:");
                            String guestSearchRoom = sc.nextLine();
                            
                        }
                    }
                    // End of Checking for guest details
                    
                    System.out.println("");
                    
                    break;
                case 6: 
                    break;
                case 7:
                    System.out.println("Program terminating ....");
            }
            
        } while (choice < 7);

        sc.close();
    }

    // Check if room number is present
    public static boolean roomNoCheck(String roomN){
        boolean pass = true;
        if (Integer.parseInt(roomN.substring(0,2)) < minFloorNo || Integer.parseInt(roomN.substring(0,2)) > maxFloorNo) {
            System.out.println("No such floor number. Please limit floor number to min: " + minFloorNo + " and max: " + maxFloorNo);
            pass = false;
        }
        if (Integer.parseInt(roomN.substring(roomN.lastIndexOf("-")+1,roomN.lastIndexOf("-")+3)) > maxRoomNoPerFloor) {
            System.out.println("No such room number. Please limit room number to max: " + maxRoomNoPerFloor);
            pass = false;
        }
        return pass;
    }
    
}



