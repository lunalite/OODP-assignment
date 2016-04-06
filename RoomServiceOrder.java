package cz2002_assignment;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RoomServiceOrder {

    private String orderCode;
    private Date dateTime;
    private RoomServiceOrderStatus status; // Variations: confirmed, preparing, delivered
    private String remarks;
    private double bill;
    private List<Item> itemList;
    private Iterator<Item> itemListItr;
    
    public RoomServiceOrder(List<Item> itemOrder, String re, String roomNo, int index) {
        dateTime = new Date();
        remarks = re;
        itemList = new ArrayList(itemOrder);
        status = RoomServiceOrderStatus.CONFIRMED;
        calTotalBill();
        
        //Order code is formed from room number and the number of orders already present
        orderCode = roomNo + (index+1); 
    }
    
    private void calTotalBill() {
        bill = 0; //initialise the total bill
        itemListItr = itemList.iterator();
        while (itemListItr.hasNext()){
            bill += itemListItr.next().getPrice();
        }
    }
    
    public void printOrder(){
        System.out.println("========================");
        System.out.println("Order code: " + orderCode);
        System.out.println("Date/Time: " + dateTime.toString());
        System.out.println("Items Ordered: ");
        int code = 1;
        for (Item itemObj : itemList){
            System.out.printf("(%d) %s\n", code, itemObj.getName());
            code ++;
        }
        System.out.println("Remarks: " + remarks);
        System.out.printf("Total bill: $%.2f\n", bill);
        System.out.println("Status: " + status);
        System.out.println("========================");
        System.out.println("");
    }
    
    public Date getDateTime() {return this.dateTime;}
    public RoomServiceOrderStatus getStatus() {return this.status;}
    public String getRemarks() {return this.remarks;}
    public double getBill() {return this.bill;}
    /**
     *
     * @param dateTime
     */
    public void setDateTime(Date dateTime) {this.dateTime = dateTime;}
    /**
     *
     * @param status
     */
    public void setStatus(RoomServiceOrderStatus status) {this.status = status;}
    /**
     *
     * @param remarks
     */
    public void setRemarks(String remarks) {this.remarks = remarks;}

}
