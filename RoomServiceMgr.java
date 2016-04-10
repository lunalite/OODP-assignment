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
        printOrder(rmOrder); //Prints a receipt of the order
        return rmOrder.getBill();
    }

    public void printOrder(RoomServiceOrder rmOrder){
        System.out.println("========================");
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
        System.out.println("========================");
        System.out.println("");
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
                printOrder(orders);
            }
        }
        else {
            int index = Integer.parseInt(orderCode.replace(roomNo, ""));
            printOrder(RoomOrderList[roomStrToInt(roomNo) - 1].get(index - 1));
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
