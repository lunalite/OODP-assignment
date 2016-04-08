/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz2002_assignment;

/**
 *
 * @author HD
 */
public class PaymentMgr {
    
    private static Payment[] totalPaymentArr = new Payment[RoomMgr.totalRooms];

    PaymentMgr() {
        
        for (int i = 0; i < RoomMgr.totalRooms; i ++) {
            // TO instantiate payment class for all rooms at the start. 
            totalPaymentArr[i] = new Payment();
        }
    }
    
    public static void newPayment(int roomNo) {
        totalPaymentArr[roomNo] = new Payment();
    }
    
    public void printBillInvoice(){
        // print bill invoice (with breakdowns on days of stay, room service order items and its total, tax and total amount)
    }
    
    public Payment getPayment(String rmno){
        return totalPaymentArr[RoomMgr.roomStrToInt(rmno)-1];
    }
}
