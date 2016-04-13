package cz2002_assignment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

public class RoomServiceOrder {

    /**
     * Room service order code number
     */
    private String orderCode;
    
    /**
     * Room service order issued date time
     */
    private Calendar dateTime;
    
    /**
     * Room service order status
     */
    private RoomServiceOrderStatus status; // Variations: confirmed, preparing, delivered
    
    /**
     * Room service order additional remarks
     */
    private String remarks;
    
    /**
     * Room service order price/bill
     */
    private double bill;
    
    /**
     * Room service order items
     */
    private List<Item> itemList;
    
    /**
     * Iterator to run through room service order items
     */
    private Iterator<Item> itemListItr;
    
    /**
     * Default constructor
     */
    RoomServiceOrder() {} 
    
    /**
     * Create room service order
     * @param itemOrder List of items ordered
     * @param re Additional remarks
     * @param roomNo Ordered by room number
     * @param index Suffix to generate unique identifier
     */
    public RoomServiceOrder(List<Item> itemOrder, String re, String roomNo, int index) {
        dateTime = new GregorianCalendar(2016, 4, CZ2002_Assignment.currentDay);
        remarks = re;
        itemList = new ArrayList(itemOrder);
        status = RoomServiceOrderStatus.CONFIRMED;
        calTotalBill();
        
        //Order code is formed from room number and the number of orders already present
        orderCode = roomNo + (index+1); 
    }
    
    /**
     * calculate total bill, summing all items in room service items
     */
    private void calTotalBill() {
        bill = 0; //initialise the total bill
        itemListItr = itemList.iterator();
        while (itemListItr.hasNext()){
            bill += itemListItr.next().getPrice();
        }
    }
    
    /**
     * Get room service order issued date time
     * @return dateTime
     */
    public Calendar getDateTime() {return this.dateTime;}
    
    /**
     * Get room service order status
     * @return status
     */
    public RoomServiceOrderStatus getStatus() {return this.status;}
    
    /**
     * Get room service order additional remarks
     * @return remarks
     */
    public String getRemarks() {return this.remarks;}
    
    /**
     * Get room service order price/bill
     * @return bill
     */
    public double getBill() {return this.bill;}
    
    /**
     * Get room service order code
     * @return orderCode
     */
    public String getOrderCode() {return this.orderCode;}
    
    /**
     * Get room service order items
     * @return itemList
     */
    public List<Item> getItemList() {return this.itemList;}
    
    /**
     * Set room service order issued date time
     * @param dateTime The room service issued date time
     */
    public void setDateTime(Calendar dateTime) {this.dateTime = dateTime;}
    
    /**
     * Set room service order status
     * @param status The room service order status
     */
    public void setStatus(RoomServiceOrderStatus status) {this.status = status;}
    
    /**
     * Set room service order additional remarks
     * @param remarks The room service additional remarks
     */
    public void setRemarks(String remarks) {this.remarks = remarks;}

}
