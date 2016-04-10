package cz2002_assignment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

public class RoomServiceOrder {

    private String orderCode;
    private Calendar dateTime;
    private RoomServiceOrderStatus status; // Variations: confirmed, preparing, delivered
    private String remarks;
    private double bill;
    private List<Item> itemList;
    private Iterator<Item> itemListItr;
    
    public RoomServiceOrder(List<Item> itemOrder, String re, String roomNo, int index) {
        dateTime = new GregorianCalendar(2016, 4, CZ2002_Assignment.currentDay);
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
    
    
    
    public Calendar getDateTime() {return this.dateTime;}
    public RoomServiceOrderStatus getStatus() {return this.status;}
    public String getRemarks() {return this.remarks;}
    public double getBill() {return this.bill;}
    public String getOrderCode() {return this.orderCode;}
    public List<Item> getItemList() {return this.itemList;}
    
    /**
     *
     * @param dateTime
     */
    public void setDateTime(Calendar dateTime) {this.dateTime = dateTime;}
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
