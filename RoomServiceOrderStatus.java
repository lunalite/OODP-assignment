/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz2002_assignment;

public enum RoomServiceOrderStatus {
    CONFIRMED, PREPARING, DELIVERED;
    
    /**
     * Returns room service order status object as readable string
     * @return format Possible strings Confirmed | Preparing | Delivered
     */
    public String toString(){
        String format = "";
        switch (this) {
            case CONFIRMED:
                format = "Confirmed";
                break;
            case PREPARING:
                format = "Preparing";
                break;
            case DELIVERED:
                format = "Delivered";
                break;
        }
        return format;
    }
}
