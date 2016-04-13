package cz2002_assignment;

public class Item {
    
    /**
     * The item's name
     */
    private String name;
    
    /**
     * The item's description
     */
    private String description;
    
    /**
     * The item's price
     */
    private double price;

    /**
     * Constructor to set item variables
     * 
     * @param name Item's name
     * @param description Item's description
     * @param price Item's price
     */
    Item(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
    
    /**
     * Set item's name
     * 
     * @param name Item's name
     */
    public void setName(String name) {this.name = name;}
    
    /**
     * Set item's description
     * 
     * @param description Item's description
     */
    public void setDescription(String description) {this.description = description;}
    
    /**
     * Set item's price
     * 
     * @param price Item's price
     */
    public void setPrice(Double price) {this.price = price;}
    
    /**
     * Get item's name
     * 
     * @return name Item's name
     */
    public String getName() {return name;}
    
    /**
     * Get item's description
     * 
     * @return description Item's description
     */
    public String getDescription() {return description;}
    
    /**
     * Get item's price
     * 
     * @return price Item's price
     */
    public double getPrice() {return price;}

}
