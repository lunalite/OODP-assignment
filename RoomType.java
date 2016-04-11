package cz2002_assignment;

public enum RoomType {
    SINGLE(120.0), DOUBLE(160.0), TWIN(160.0), TRIPLE(200.0);
    
    private double rate;
    
    RoomType(double f) {
        rate = f;
    }
    
    public double getRate() {return rate;}
    
    public String toString(){
        String format = "";
        switch (this) {
            case SINGLE:
                format = "Single";
                break;
            case DOUBLE:
                format = "Double";               
                break;
            case TWIN:
                format = "Twin";
                break;
            case TRIPLE:
                format = "Triple";
                break;
        }
        return format;
    }
}
