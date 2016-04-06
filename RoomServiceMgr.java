package cz2002_assignment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RoomServiceMgr {
    
    private List<RoomServiceOrder> RoomOrderList[];
    
    RoomServiceMgr() {
        RoomOrderList = new LinkedList[48]; //Total room numbers i.e. have to get it from roomMgr
    }
    
    public void showMenu() {
        MenuMgr.updateList();
        MenuMgr.itemList();
    }
    
    public void getItemDescription(int x){
        MenuMgr.getItemDescription(x);
    }

    public double createOrder(String rmNo, List<Item> itemOrder, String remarks) {
        if (RoomOrderList[roomStrToInt(rmNo) - 1] == null) {
            RoomOrderList[roomStrToInt(rmNo) - 1] = new LinkedList();
        }
        RoomServiceOrder rmOrder = new RoomServiceOrder(itemOrder, remarks, rmNo, 
                RoomOrderList[roomStrToInt(rmNo) - 1].size());
        RoomOrderList[roomStrToInt(rmNo) - 1].add(rmOrder); //Keeps a record of all the orders available
        rmOrder.printOrder(); //Prints a receipt of the order
        return rmOrder.getBill();
    }

    public void updateOrder(int orderCode, String rmNo) {
        // Update order based on the order code and room number.
        
    }

    public void removeOrder(String rmNo, String orderCode) {
        // Removes the Order based on the order code.
        if (orderCode.equals("1")){
            RoomOrderList[roomStrToInt(rmNo)-1].clear();
        }
        else {
            int index = Integer.parseInt(orderCode.replace(rmNo, ""));
            RoomOrderList[roomStrToInt(rmNo)-1].remove(index-1);
        }
    }
    
    public boolean checkOrder (String rmNo){
        if (RoomOrderList[roomStrToInt(rmNo) - 1] == null ||
                RoomOrderList[roomStrToInt(rmNo) - 1].isEmpty())
            return false;
        else 
            return true;
    }
    
    public void getOrder(String roomNo, String orderCode) {
        // Returns the Order based on the order code.
        if (orderCode.equals("1")){
            for (RoomServiceOrder orders : RoomOrderList[roomStrToInt(roomNo) - 1]){
                orders.printOrder();
            }
        }
        else {
            int index = Integer.parseInt(orderCode.replace(roomNo, ""));
            RoomOrderList[roomStrToInt(roomNo) - 1].get(index - 1).printOrder();
        }
    }
    //Repeated code in RoomMgr
    public int roomStrToInt(String roomStr) { // integer-wise, uses int 1-48 for each rooms from 02-01 to 07-07 respectively
        int roomInt = 0;
        int floor = Integer.parseInt(roomStr.substring(0,2));
        int room = Integer.parseInt(roomStr.substring(roomStr.lastIndexOf("-")+1,roomStr.lastIndexOf("-")+3));
        roomInt += (floor - 2)*8 + room;
        return roomInt;
    }
    
}
