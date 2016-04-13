/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz2002_assignment;

/**
 *
 * @author Team 6
 */
public enum RoomStatus {
    VACANT, OCCUPIED, RESERVED, UNDER_MAINTENANCE;

    /**
     * Returns room status object as readable string
     * @return format Possible strings Vacant | Occupied | Reserved | Under maintenance
     */
    public String toString(){
        String format = "";
        switch (this) {
            case VACANT:
                format = "Vacant";
                break;
            case OCCUPIED:
                format = "Occupied";
                break;
            case RESERVED:
                format = "Reserved";
                break;
            case UNDER_MAINTENANCE:
                format = "Under maintenance";
        }
        return format;
    }
}
