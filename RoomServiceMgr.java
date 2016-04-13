package cz2002_assignment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RoomServiceMgr {
    
    private static List<RoomServiceOrder> RoomOrderList[];
    
    RoomServiceMgr() {
        RoomOrderList = new LinkedList[RoomMgr.totalRooms]; //Total room numbers i.e. have to get it from roomMgr
        for (int i = 0; i < RoomMgr.totalRooms; i ++) {
        }
    }
    
    public void showMenu() {
        MenuMgr.itemList();
    }
    
    public void getItemDescription(int x){
        MenuMgr.getItemDescription(x);
    }

    public double createOrder(String rmNo, List<Item> itemOrder, String remarks) {
        if (RoomOrderList[RoomMgr.roomStrToInt(rmNo) - 1] == null) {
            RoomOrderList[RoomMgr.roomStrToInt(rmNo) - 1] = new LinkedList();
        }
        RoomServiceOrder rmOrder = new RoomServiceOrder(itemOrder, remarks, rmNo, 
                RoomOrderList[RoomMgr.roomStrToInt(rmNo) - 1].size());
        RoomOrderList[RoomMgr.roomStrToInt(rmNo) - 1].add(rmOrder); //Keeps a record of all the orders available
        printOrder(rmOrder); //Prints a receipt of the order
        return rmOrder.getBill();
    }

    public void printOrder(RoomServiceOrder rmOrder){
        System.out.println("\n========================");
        System.out.println("Order code: " + rmOrder.getOrderCode());
        System.out.println("Date/Time: " + rmOrder.getDateTime().get(rmOrder.getDateTime().YEAR) + "-" + 
                rmOrder.getDateTime().get(rmOrder.getDateTime().MONTH) + "-" + 
                rmOrder.getDateTime().get(rmOrder.getDateTime().DAY_OF_MONTH));
        System.out.println("Items Ordered: ");
        int code = 1;
        for (Item itemObj : rmOrder.getItemList()){
            System.out.printf("(%d) %s\n", code, itemObj.getName());
            code ++;
        }
        System.out.println("Remarks: " + rmOrder.getRemarks());
        System.out.printf("Total bill: $%.2f\n", rmOrder.getBill());
        System.out.println("Status: " + rmOrder.getStatus());
        System.out.println("========================\n");
    }
    
    public void updateOrder(int orderCode, String rmNo) {
        // Update order based on the order code and room number.
        
    }

    public void removeOrder(String rmNo, String orderCode) {
        // Removes the Order based on the order code.
        if (orderCode.equals("1")){
            RoomOrderList[RoomMgr.roomStrToInt(rmNo)-1].clear();
        }
        else {
            int index = Integer.parseInt(orderCode.replace(rmNo, ""));
            RoomOrderList[RoomMgr.roomStrToInt(rmNo)-1].remove(index-1);
        }
    }
    
    public boolean checkOrder (String rmNo){
        if (RoomOrderList[RoomMgr.roomStrToInt(rmNo) - 1] == null ||
                RoomOrderList[RoomMgr.roomStrToInt(rmNo) - 1].isEmpty())
            return false;
        else 
            return true;
    }
    
    public void getOrder(String roomNo, String orderCode) {
        // Returns the Order based on the order code.
        if (orderCode.equals("1")){
            for (RoomServiceOrder orders : RoomOrderList[RoomMgr.roomStrToInt(roomNo) - 1]){
                printOrder(orders);
            }
        }
        else {
            int index = Integer.parseInt(orderCode.replace(roomNo, ""));
            printOrder(RoomOrderList[RoomMgr.roomStrToInt(roomNo) - 1].get(index - 1));
        }
    }
    
    public static List<RoomServiceOrder> getOrders(int roomNo){
        if (RoomOrderList[roomNo - 1] != null)
            return RoomOrderList[roomNo-1];
        else
            return null;
    }
}
