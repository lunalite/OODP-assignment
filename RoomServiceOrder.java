package cz2002_assignment;

public class RoomServiceOrder {

    private int orderCode;
    private int dateTime;
    private String status;
    private String remarks;
    private double bill;

    public void getOrder() {

    }

    public int getDateTime() {
        return this.dateTime;
    }

    public String getStatus() {
        return this.status;
    }

    public String getRemarks() {
        return this.remarks;
    }

    /**
     *
     * @param dateTime
     */
    public void setDateTime(int dateTime) {
        this.dateTime = dateTime;
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
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
