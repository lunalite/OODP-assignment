package cz2002_assignment;

public enum RoomType {
    SINGLE(120.0), DOUBLE(160.0), TWIN(160.0), TRIPLE(200.0);
    
    /**
     * Room's Rate
     */
    private double rate;
    
    /**
     * Set rate value
     * @param rate Room rate that's tagged to room type
     */
    RoomType(double rate) {
        this.rate = rate;
    }

    /**
     * Get room rate
     * @return rate
     */
    public double getRate() {return rate;}
    
    /**
     * Returns room type object as readable string
     * @return format Possible strings Single | Double | Twin | Triple
     */
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
