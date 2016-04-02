package cz2002_assignment;

import java.util.ArrayList;
import java.util.List;

public class MenuMgr {

    private List<Item> itemMenu;

    MenuMgr() {
        // instantiate the possible F&B items
        Item friedRice = new Item("Fried Rice", "Steamed rice that has been stir-fried in a wok, mixed with eggs, vegetables, and meat.", 9.90);
        Item friedChicken = new Item("Fried Chicken", "Chicken fried to succulent crispness.", 5.50);
        Item coke = new Item("Coke", "A cold-canned drink.", 3.00);
        
        //Add the food food items to itemMenu
        itemMenu = new ArrayList<Item>();
        itemMenu.add(friedRice);
        itemMenu.add(friedChicken);
        itemMenu.add(coke);
    }
    
    public void itemList() {
        int count = 1;  //Used to list the order of the item listed in menu 
                        //according to what is stored within itemMenu list.
        System.out.println("\n========================");
        for (Item food : itemMenu){
            System.out.printf("(%d) %-15s $%.2f\n", count, food.getName(), food.getPrice());
            count++;
        }
        System.out.println("========================");
    }
    
    public void getItemDescription(int x){
        try {
        System.out.println(itemMenu.get(x-1).getDescription());
        System.out.println("Do you want to purchase this item? (y/n)");
        }
        catch (Exception e) {
            System.out.println("Error. Please input the right value.");
        }
    }

}
