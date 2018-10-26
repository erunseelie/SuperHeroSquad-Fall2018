package shs.cos.rooms;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.File;
import java.io.FileNotFoundException;

public class Room
{
	private static String seperator = "::";

	private String roomName;
	private String description;
	private ArrayList<String> connections = new ArrayList<>();
	private ArrayList<String> itemIDList = new ArrayList<>();
	private ArrayList<String> puzzleIDList = new ArrayList<>();
	private ArrayList<String> monsterIDList = new ArrayList<>();

	private static Scanner input;
	static TreeMap<String, Room> roomList = new TreeMap<>();
	
	static String currentPlayerLocationKey = "B0R0";

	public Room()
	{

	}

	public void readRoomFile(File f)
	{
		try
		{
			input = new Scanner(f);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		while (input.hasNextLine())
		{
			String[] nextLine = input.nextLine().split(seperator);

			OUTER_LOOP: if (nextLine[0].equals("ID"))
			{
				Room newRoom = new Room();
				String newRoomID = nextLine[1];

				while (input.hasNextLine())
				{
					nextLine = input.nextLine().split(seperator);
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

					if (nextLine[0].equals("ID") || nextLine[0].equals("endRoom"))
					{
						roomList.put(newRoomID, newRoom);
						break OUTER_LOOP;
					}
				}
			}
		}
	}

	public TreeMap<String, Room> getMap()
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

	public void setCurrentRoom(String passedKey)
	{
		currentPlayerLocationKey = passedKey;
	}

	public String getCurrentRoomKey()
	{
		return currentPlayerLocationKey;
	}
}