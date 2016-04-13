package cz2002_assignment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GuestMgr {
    
    /**
     * List of all the guests in the system
     */
    private static List<Guest> guestList;
    
    /**
     * Iterator to traverse through the list of guests
     */
    private Iterator<Guest> guestListItr;
    
    /**
     * Scanner to take user inputs
     */
    private Scanner sc = new Scanner(System.in);
    
    /**
     * Guest manager constructor
     */    
    public GuestMgr() {
        guestListItr = guestList.iterator();
        while (guestListItr.hasNext()) {
            guestListItr.next().updateGuestRoom();
        }
    }
    
    /**
     * Adds new guest via taking in user inputs in real-time
     */
    public void addGuest() {
            System.out.println("Please insert name of guest to be added: ");
            String name2B = sc.nextLine();
            System.out.println("Please insert gender of guest to be added: ");
            String gender2B = sc.nextLine();
            System.out.println("Please insert address of guest to be added: ");
            String address2B = sc.nextLine();
            System.out.println("Please insert IC of guest to be added: ");
            String identity2B = sc.nextLine();
            System.out.println("Please insert nationality of guest to be added: ");
            String nat2B = sc.nextLine();
            System.out.println("Please insert contact of guest to be added: ");
            String contact2 = sc.nextLine();
            int contact2B = Integer.parseInt(contact2);
            System.out.println("Please insert credit card details of guest to be added: ");
            String ccd2B = sc.nextLine();
            guestList.add(new Guest(name2B, gender2B, address2B, identity2B, nat2B, contact2B, ccd2B, ""));
            
            System.out.println("Guest added");
    }
    
    /**
     * Search guest via provided name
     * 
     * @param guestSearchName The name of the guest to search for
     * @return Guest Either Guest object will be return or null if guest not found
     */
    public Guest searchGuestByName(String guestSearchName){
        guestListItr = guestList.iterator();
        while (guestListItr.hasNext()){
            Guest g = guestListItr.next();
            if (g.getName().contains(guestSearchName)){
                System.out.println("Is the guest name " + g.getName() + "? (y/n)");
                String guestNameConfirm = sc.nextLine();

                if (guestNameConfirm.equals("y")) {
                    return g;
                }
            }
        }
        System.out.println("No such name of guest is available.");
        return null;
    }
    
    /**
     * Search guest via room number
     * 
     * @param roomSearchNo The room number to be searched
     * @return Guest
     */
    public Guest searchGuestByRoom(String roomSearchNo){
        Guest g = RoomMgr.getRoom(roomSearchNo).getStatusCalendar(CZ2002_Assignment.currentDay).getGuest();
        return g;
    }
    
    /**
     * Print the guest details
     * 
     * @param guest The guest object to be printed
     */
    public void printGuestDetails(Guest guest) {
        System.out.println("\n========================");
        System.out.println("Name: " + guest.getName());
        System.out.println("Gender: " + guest.getGender());
        System.out.println("IC: " + guest.getIdentity());
        System.out.println("Address: " + guest.getAddress());
        System.out.println("Nationality: " + guest.getNationality());
        System.out.println("Contact: " + guest.getContact());
        System.out.println("CreditCardDet: " + guest.getCreditCardDet());
        System.out.println("========================\n");
    }
   
    /**
     * Get the all the guests in a list
     * 
     * @return guestList
     */
    public static List<Guest> getGuestList(){return guestList;}
    
    /**
     * Get specific guest via provided guest name
     * 
     * @param guestName The guest name
     * @return Guest
     */
    public static Guest getGuest(String guestName) {
        Guest g = null;
        
        for (int i = 0; i < guestList.size(); i ++) {
            g = guestList.get(i);
            if (g.getName().equals(guestName)) {
                return g;
            }
        }
        
        g = null;
        return g;
    }
    
    /**
     * Set guest list with provided guest list
     * 
     * @param guestLi The new Guest List
     */
    public static void setGuestList(List<Guest> guestLi) {guestList = new ArrayList(guestLi);}
}
