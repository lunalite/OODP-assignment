/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz2002_assignment;

/**
 *
 * @author Lalexis
 */
public enum ReservationStatus {
    CONFIRMED, WAITLIST, CHECKED_IN, EXPIRED, CANCELLED;
    
    /**
     * Returns reservation status object as readable string
     * @return format Possible strings Confirmed | On waitlist | Checked in | Expired | Cancelled
     */
    public String toString(){
        String format = "";
        switch (this) {
            case CONFIRMED:
                format = "Confirmed";
                break;
            case WAITLIST:
                format = "On waitlist";
                break;
            case CHECKED_IN:
                format = "Checked in";
                break;
            case EXPIRED:
                format = "Expired";
                break;
            case CANCELLED:
                format = "Cancelled";
                break;
        }
        return format;
    }
}
