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
public enum RoomStatus {
    VACANT, OCCUPIED, RESERVED, UNDER_MAINTENANCE;

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
