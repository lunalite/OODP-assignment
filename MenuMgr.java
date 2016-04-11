package cz2002_assignment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MenuMgr {

    private static List<Item> itemMenu;
    
    public static void updateList(List<Item> itemClone){
        itemMenu = (List)(((ArrayList)itemClone).clone());
    }
    
    public static void createItem(String name, String desc, double price){
        //Add the food food items to itemMenu
        itemMenu.add(new Item(name, desc, price));
        System.out.println("Item added.");
    }
    
    public static int searchItem(String name){
        Scanner sc = new Scanner (System.in);
        Iterator<Item> itemMenuItr = itemMenu.iterator();
        boolean itemFound = false;
        int index = -1;
        while (itemMenuItr.hasNext()) {
            index ++;
            Item i = itemMenuItr.next();
            if (i.getName().contains(name)) {
                System.out.println("Is the item name " + i.getName() + "? (y/n)");
                    String itemNameConfirm = sc.nextLine();

                    if (itemNameConfirm.equals("y")) {
                        //print out details
                        System.out.println("\n========================");
                        System.out.println("Name: " + i.getName());
                        System.out.println("Description: " + i.getDescription());
                        System.out.println("Price: " + i.getPrice());
                        System.out.println("========================");
                        itemFound = true;
                        break;
                    }
                }
            }            
        if (itemFound == false) {
            System.out.println("No such name of item is available.");
            index = -1;
        }
        return index;
        }
    
    public static void updateItemN(int index, String n){
        itemMenu.get(index).setName(n);
    }
    
    public static void updateItemD(int index, String d){
        itemMenu.get(index).setDescription(d);
    }
    
    public static void updateItemP(int index, double p){
        itemMenu.get(index).setPrice(p);    
    }
    
    public static void removeItem(int index){
        itemMenu.remove(index);
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
    public static List<Item> getItemMenuList(){return itemMenu;}
}
