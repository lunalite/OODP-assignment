package cz2002_assignment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GuestMgr {
    private static List<Guest> guestList;
    private Iterator<Guest> guestListItr;
    private Scanner sc = new Scanner(System.in);
        
    public GuestMgr() {
        guestListItr = guestList.iterator();
        while (guestListItr.hasNext()) {
            guestListItr.next().updateGuestRoom();
        }
    }
    
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
            String contact2 = "";
            do {
                contact2 = sc.nextLine();
                if (contact2.matches(".*[a-zA-Z]+.*"))
                    System.out.println("Please input integers only.");
                else
                    break;
            } while (true);
            int contact2B = Integer.parseInt(contact2);
            System.out.println("Please insert credit card details of guest to be added: ");
            String ccd2B = sc.nextLine();
            guestList.add(new Guest(name2B, gender2B, address2B, identity2B, nat2B, contact2B, ccd2B, ""));
            
            System.out.println("Guest added");
    }
    
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
    
    public Guest searchGuestByRoom(String roomSearchNo){
        Guest g = RoomMgr.getRoom(roomSearchNo).getStatusCalendar(CZ2002_Assignment.currentDay).getGuest();
        return g;
    }
    
    public void printGuestDetails(Guest g) {
        System.out.println("\n========================");
        System.out.println("Name: " + g.getName());
        System.out.println("Gender: " + g.getGender());
        System.out.println("IC: " + g.getIdentity());
        System.out.println("Address: " + g.getAddress());
        System.out.println("Nationality: " + g.getNationality());
        System.out.println("Contact: " + g.getContact());
        System.out.println("CreditCardDet: " + g.getCreditCardDet());
        System.out.println("========================\n");
    }
   
    public static List<Guest> getGuestList(){return guestList;}
    
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
    
    public static void setGuestList(List<Guest> guestLi) {guestList = new ArrayList(guestLi);}
}
