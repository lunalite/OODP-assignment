package cz2002_assignment;

import java.util.Date;

public class Reservation {

    private int reserveCode;
    private String billingInfo;
    private Date checkInDate;
    private Date checkOutDate;
    private int numberOfAdults;
    private int numberOfChild;
    private String status;

    Reservation() {

    }

    public String getStatus() {
        return this.status;
    }

    public int getReserveCode() {
        return this.reserveCode;
    }
    
    public Date getCheckInDate(){
    	return this.checkInDate;
    }
    
    public Date getCheckOutDate(){
    	return this.checkOutDate;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @param reserveCode
     */
    public void setReserveCode(int reserveCode) {
        this.reserveCode = reserveCode;
    }

}
