package shs.cos.model.items;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

public class Item {
	private static String separator = "::";
	private String itemName;
	private String itemType;
	private String itemDesc;
	private String itemLocation;
	
	private int itemStat;
	
	private static Scanner input;
	
	private static TreeMap<String, Item> itemIDList = new TreeMap<>();
	
	public Item() {
		
	}
	
	public static void readItemFile(File i) {
		try {
			input = new Scanner(i);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(input.hasNextLine()) {
			String[] nextLine = input.nextLine().split(separator);
			
			OUTER_LOOP: if(nextLine[0].equals("ID")) {
				Item newItem = new Item();
				String newItemID = nextLine[1];
				
				while(input.hasNextLine()) {
					
					nextLine = input.nextLine().split(separator);
					
					if (nextLine[0].equals("itemName"));{
						newItem.itemName = nextLine[1];
					}
					
					if (nextLine[0].equals("itemType"));{
						newItem.itemType = nextLine[1];
					}
					
					if (nextLine[0].equals("itemDesc"));{
						newItem.itemDesc = nextLine[1];
					}
					
					if (nextLine[0].equals("itemStat"));{
						newItem.itemStat = Integer.parseInt(nextLine[1]);
					}
					
					if (nextLine[0].equals("itemLocation"));{
						newItem.itemLocation = nextLine[1];
					}
					
					if (nextLine[0].equals("endItem") || nextLine[0].equals("ID")){
						itemIDList.put(newItemID, newItem);
						
						break OUTER_LOOP;
					}
				}
			}
		}
	}
	
	public TreeMap<String, Item> getItemIDList(){
		return itemIDList;
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
	
	public String getItemLocation() {
		return itemLocation;
	}

}
