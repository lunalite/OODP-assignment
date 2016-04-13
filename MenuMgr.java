package cz2002_assignment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MenuMgr {

    /**
     * List of items offered in room service
     */
    private static List<Item> itemMenu;

    /**
     * Update the list of items in room service menu
     *
     * @param itemClone The new list of items to be replaced
     */
    public static void updateList(List<Item> itemClone) {
        itemMenu = (List) (((ArrayList) itemClone).clone());
    }

    /**
     * Create new item and add to the list of items offered
     *
     * @param name The name of the new item
     * @param desc The description of the new item
     * @param price The price of the new item
     */
    public static void createItem(String name, String desc, double price) {
        //Add the food food items to itemMenu
        itemMenu.add(new Item(name, desc, price));
        System.out.println("Item added.");
    }

    /**
     * Searches for particular item and determine if it is offered in the menu
     *
     * @param name The name of the item to search for
     * @return item index
     */
    public static int searchItem(String name) {
        Scanner sc = new Scanner(System.in);
        Iterator<Item> itemMenuItr = itemMenu.iterator();
        boolean itemFound = false;
        int index = -1;
        while (itemMenuItr.hasNext()) {
            index++;
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

    /**
     * Update specific item name via item index
     *
     * @param index The item index
     * @param name The item new name
     */
    public static void updateItemN(int index, String name) {
        itemMenu.get(index).setName(name);
    }

    /**
     * Update specific item description via item index
     *
     * @param index The item index
     * @param description The item description
     */
    public static void updateItemD(int index, String description) {
        itemMenu.get(index).setDescription(description);
    }

    /**
     * Update specific item price via item index
     *
     * @param index The item index
     * @param price The item price
     */
    public static void updateItemP(int index, double price) {
        itemMenu.get(index).setPrice(price);
    }

    /**
     * Remove specific item via item index
     *
     * @param index The index of the item to be removed
     */
    public static void removeItem(int index) {
        itemMenu.remove(index);
    }

    /**
     * Display all the items details in the offered room service menu
     */
    public static void itemList() {
        int count = 1;  //Used to list the order of the item listed in menu 
        //according to what is stored within itemMenu list.
        System.out.println("\n========================");
        for (Item food : itemMenu) {
            System.out.printf("(%d) %-15s $%.2f\n", count, food.getName(), food.getPrice());
            count++;
        }
        System.out.println("========================");
    }

    /**
     * Get item description via item index
     *
     * @param x The item index
     */
    public static void getItemDescription(int x) {
        try {
            System.out.println(itemMenu.get(x - 1).getDescription());
            System.out.println("Do you want to purchase this item? (y/n)");
        } catch (Exception e) {
            System.out.println("Error. Please input the right value.");
        }
    }

    /**
     * Get item name via item index
     *
     * @param x The item index
     * @return The name of the Item
     */
    public static String getItemName(int x) {
        return itemMenu.get(x - 1).getName();
    }

    /**
     * Get item via index
     *
     * @param x The item index
     * @return Item at index
     */
    public static Item getItem(int x) {
        return itemMenu.get(x - 1);
    }

    /**
     * Get the list of items
     *
     * @return itemMenu The list of items
     */
    public static List<Item> getItemMenuList() {
        return itemMenu;
    }
}
