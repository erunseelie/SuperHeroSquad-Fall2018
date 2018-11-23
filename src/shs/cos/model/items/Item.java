package shs.cos.model.items;

import shs.cos.model.utils.io.IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;


public class Item {
    private static String separator = IO.separator;
    private static Scanner input;
    private static TreeMap<String, Item> itemIDList = new TreeMap<>();
    private static ArrayList<Item> playerItems = new ArrayList<>();
    private String itemID;
    private String itemName;
    private String itemType;
    private String itemDesc;
    private String itemLocation;
    private int itemStat;

    public Item() {

    }

    public static boolean readItemFile(File f) {
        try {
            input = new Scanner(f);
        } catch (Exception e) {
            return false;
        }

        while (input.hasNextLine()) {
            String[] nextLine = input.nextLine().split(separator);

            OUTER_LOOP:
            if (nextLine[0].equals("ID")) {
                Item newItem = new Item();
                newItem.itemID = nextLine[1];

                while (input.hasNextLine()) {

                    nextLine = input.nextLine().split(separator);

                    if (nextLine[0].equals("itemName")) {
                        newItem.itemName = nextLine[1];
                    }

                    if (nextLine[0].equals("itemType")) {
                        newItem.itemType = nextLine[1];
                    }

                    if (nextLine[0].equals("itemDesc")) {
                        newItem.itemDesc = nextLine[1];
                    }

                    if (nextLine[0].equals("itemStat")) {
                        newItem.itemStat = Integer.parseInt(nextLine[1]);
                    }

                    if (nextLine[0].equals("itemLocation")) {
                        if (nextLine.length > 1) newItem.itemLocation = nextLine[1];
                        else newItem.itemLocation = "invalid";
                    }

                    if (nextLine[0].equals("endItem") || nextLine[0].equals("ID")) {
                        itemIDList.put(newItem.itemID, newItem);

                        break OUTER_LOOP;
                    }
                }
            }
        }
        return true;
    }

    public static TreeMap<String, Item> getItemIDList() {
        return itemIDList;
    }

    public static ArrayList<Item> getPlayerItems() {
        return playerItems;
    }

    public static ArrayList<Item> addPlayerItem(Item i) {
        playerItems.add(i);
        return playerItems;
    }

    public static ArrayList<Item> removePlayerItem(Item i) {
        playerItems.remove(i);
        return playerItems;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public int getItemStat() {
        return itemStat;
    }

    public String getLocation() {
        return itemLocation;
    }

    public String getItemID() {
        return itemID;
    }
}
