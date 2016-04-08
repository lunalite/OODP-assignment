package cz2002_assignment;

public enum RoomType {
    SINGLE, DOUBLE, TWIN, TRIPLE;
    
    private double rate;

    public String toString(){
        String format = "";
        switch (this) {
            case SINGLE:
                format = "Single";
                rate = 120.0f;
                break;
            case DOUBLE:
                format = "Double";
                rate = 160.0f;
                break;
            case TWIN:
                format = "Twin";
                rate = 160.0f;
                break;
            case TRIPLE:
                format = "Triple";
                rate = 200.0f;
                break;
        }
        return format;
    }
}
