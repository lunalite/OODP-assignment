package cz2002_assignment;

import java.util.ArrayList;
import java.util.List;

public class MenuMgr {

    private static List<Item> itemMenu;
    
    public static void updateList(List<Item> itemClone){
        itemMenu = (List)(((ArrayList)itemClone).clone());
    }
    
    public static void createItem(String name, String desc, double price){
        
        //Add the food food items to itemMenu
    }
    
    public static void itemList() {
        int count = 1;  //Used to list the order of the item listed in menu 
                        //according to what is stored within itemMenu list.
        System.out.println("\n========================");
        for (Item food : itemMenu){
            System.out.printf("(%d) %-15s $%.2f\n", count, food.getName(), food.getPrice());
            count++;
        }
        System.out.println("========================");
    }
    
    public static void getItemDescription(int x){
        try {
        System.out.println(itemMenu.get(x-1).getDescription());
        System.out.println("Do you want to purchase this item? (y/n)");
        }
        catch (Exception e) {
            System.out.println("Error. Please input the right value.");
        }
    }
    public static String getItemName(int x){return itemMenu.get(x-1).getName();}
    public static Item getItem(int x){return itemMenu.get(x-1);}
}
