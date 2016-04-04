package cz2002_assignment;

import java.util.ArrayList;
import java.util.List;

public class RoomServiceMgr {
    
    private String roomNumber;
    private static List<RoomServiceOrder> RoomOrderList;
    
    RoomServiceMgr() {
        RoomOrderList = new ArrayList();
    }
    
    public void showMenu() {
        MenuMgr.updateList();
        MenuMgr.itemList();
    }
    
    public void getItemDescription(int x){
        MenuMgr.getItemDescription(x);
    }

    public void createOrder(List<Item> itemOrder, String remarks) {
        RoomServiceOrder rmOrder = new RoomServiceOrder(itemOrder, remarks);
        RoomOrderList.add(rmOrder); //Keeps a record of all the orders available
        rmOrder.printOrder(); //Prints a receipt of the order
    }

    public void updateOrder(int orderCode) {
        // Update order based on the order code
    }

    public void removeOrder() {

    }

    public void getOrder(int orderCode) {
        // Returns the Order based on the order code.
    }
    
    public void setRoomNo(String roomNo){roomNumber = roomNo;}
}
