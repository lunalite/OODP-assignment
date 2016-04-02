package cz2002_assignment;

public class Item {
    
    private String name;
    private String description;
    private double price;

    Item(String n, String d, double p) {

        name = n;
        description = d;
        price = p;
    }
    
    public String getName() {return name;}
    public String getDescription() {return description;}
    public double getPrice() {return price;}

}
