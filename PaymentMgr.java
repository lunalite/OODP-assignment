/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz2002_assignment;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author HD
 */
public class PaymentMgr {
    
    /**
     * Array that contains all the payment
     */
    private Payment[] totalPaymentArr = new Payment[RoomMgr.totalRooms];

    /**
     * Create an instance of payment manager and insert any pre-existing data
     * @param paymentListPresent The pre-existing data set
     */
    PaymentMgr(Payment[] paymentListPresent) {
        for (int i = 0; i < RoomMgr.totalRooms; i ++) {
            // TO instantiate payment class for all rooms at the start. 
            if (paymentListPresent[i] != null)
                totalPaymentArr[i] = paymentListPresent[i];
            else
                totalPaymentArr[i] = new Payment();
        }
    }
    
    /**
     * Create new payment that is tagged to room number
     * @param roomNo The room number that made the payment
     */
    public void newPayment(int roomNo) {
        totalPaymentArr[roomNo] = new Payment();
    }
    
    /**
     * Print bill invoice
     * 
     * @param roomNo The room number of the bill
     * @param orderList The order list to print
     */
    public void printBillInvoice(int roomNo, List<RoomServiceOrder> orderList){
        if (RoomServiceMgr.getOrders(roomNo) != null) {
        // print bill invoice (with breakdowns on days of stay, room service order items and its total, tax and total amount)
        System.out.println("\n========================");
        for (RoomServiceOrder rSO : RoomServiceMgr.getOrders(roomNo)) {
            Calendar cal = rSO.getDateTime();
            System.out.println(cal.get(cal.YEAR) + "-" + cal.get(cal.MONTH) + "-" + 
                    cal.get(cal.DAY_OF_MONTH) + ": " + rSO.getBill());
        }
        System.out.printf("Total room service bill: $%.2f\n", totalPaymentArr[roomNo-1].getRoomServiceBill());
        System.out.printf("Total room charges: $%.2f\n", totalPaymentArr[roomNo-1].getRoomCharges());
        System.out.printf("subtotal bill: $%.2f\n", totalPaymentArr[roomNo-1].getTotalBill());
        System.out.printf("Total tax: $%.2f\n", totalPaymentArr[roomNo-1].getTaxes());
        System.out.printf("Total bill: $%.2f\n", (totalPaymentArr[roomNo-1].getTaxes()+totalPaymentArr[roomNo-1].getTotalBill()));
        System.out.println("========================\n");
        }
        
        else {
            System.out.println("\n========================");
            System.out.printf("Total room charges: $%.2f\n", totalPaymentArr[roomNo-1].getRoomCharges());
            System.out.printf("subtotal bill: $%.2f\n", totalPaymentArr[roomNo-1].getTotalBill());
            System.out.printf("Total tax: $%.2f\n", totalPaymentArr[roomNo-1].getTaxes());
            System.out.printf("Total bill: $%.2f\n", (totalPaymentArr[roomNo-1].getTaxes()+totalPaymentArr[roomNo-1].getTotalBill()));
            System.out.println("========================\n");
        }
    }
    
    /**
     * Get payment object via room number
     * @param roomNo The room number to get
     * @return The payment object that is tagged to the specified room number
     */
    public Payment getPayment(String roomNo){
        return totalPaymentArr[RoomMgr.roomStrToInt(roomNo)-1];
    }
    
    /**
     * Get payment list
     * @return totalPaymentArry
     */
    public Payment[] getPaymentList(){return totalPaymentArr;}
}
