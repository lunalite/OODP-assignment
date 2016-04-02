package cz2002_assignment;

public class RoomType {

    private double rate;
    private String bedType;

    RoomType() {

    }

    RoomType(String bedType, double rate) {
        this.rate = rate;
        this.bedType = bedType;
    }
    
    public String getBedType() {
        return this.bedType;
    }

}
