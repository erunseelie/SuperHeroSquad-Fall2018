package shs.cos;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.File;
import java.io.FileNotFoundException;

public class Room
{
	//indicator used to seperate data type indicator and the actual data to be added
	private static String separator = "::";

	//variables for storing individual room data
	private String roomName;
	private String description;
	private ArrayList<String> connections = new ArrayList<>();
	private ArrayList<String> itemIDList = new ArrayList<>();
	private ArrayList<String> puzzleIDList = new ArrayList<>();
	private ArrayList<String> monsterIDList = new ArrayList<>();

	private static Scanner input;
	
	//treeMap used to store rooms based on their key
	static TreeMap<String, Room> roomList = new TreeMap<>();

	//var used to track players current position
	static String currentPlayerLocationKey = "B0R0";

	//default constructor
	public Room()
	{

	}

	//reads in roomdata file, primary functionality of class
	public static void readRoomFile(File f)
	{
		//creates scanner to read file
		try
		{
			input = new Scanner(f);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		//loop used to cycle through lines in file
		while (input.hasNextLine())
		{
			String[] nextLine = input.nextLine().split(separator);

			//indicator point that loop breaks to when ever the end of a rooms respective data is reached
			OUTER_LOOP: if (nextLine[0].equals("ID"))
			{
				Room newRoom = new Room();
				String newRoomID = nextLine[1];

				//checks current text line to get data type and info so that information is added to the right variable/arraylist
				while (input.hasNextLine())
				{
					nextLine = input.nextLine().split(separator);
					if (nextLine[0].equals("roomName"))
					{
						newRoom.roomName = nextLine[1];
					}

					if (nextLine[0].equals("desc"))
					{
						newRoom.description = nextLine[1];
					}

					if (nextLine[0].equals("door"))
					{
						newRoom.connections.add(nextLine[1]);
					}

					if (nextLine[0].equals("puzzle"))
					{
						newRoom.puzzleIDList.add(nextLine[1]);
					}

					if (nextLine[0].equals("item"))
					{
						newRoom.itemIDList.add(nextLine[1]);
					}

					if (nextLine[0].equals("monster"))
					{
						newRoom.monsterIDList.add(nextLine[1]);
					}

					//check to break if the "endRoom" indicator is found. Also checks if a new room ID is detected as a fall back.
					if (nextLine[0].equals("ID") || nextLine[0].equals("endRoom"))
					{
						roomList.put(newRoomID, newRoom);
						break OUTER_LOOP;
					}
				}
			}
		}
	}

	//getters and setters
	public static TreeMap<String, Room> getMap()
	{
		return roomList;
	}

	public String getRoomName()
	{
		return roomName;
	}

	public String getDesc()
	{
		return description;
	}

	public ArrayList<String> getConnections()
	{
		return connections;
	}

	public ArrayList<String> getItems()
	{
		return itemIDList;
	}

	public ArrayList<String> getPuzzles()
	{
		return puzzleIDList;
	}

	public ArrayList<String> getMonsters()
	{
		return monsterIDList;
	}

	public static void setCurrentRoom(String passedKey)
	{
		currentPlayerLocationKey = passedKey;
	}

	public static String getCurrentRoomKey()
	{
		return currentPlayerLocationKey;
	}
}