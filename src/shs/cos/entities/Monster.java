package shs.cos.entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

public class Monster extends Character
{

	private static String separator = "::";

	private String monDesc;
	private String atkName;
	private String weakness;
	private String location;
	private String itemDrop;
	
	//Monster Speed break down:
	//monSpeed == 2 means the monster gets 2 turns for every 1 player turn
	//monSpeed == 1 means the monster gets 1 turn for every 1 player turn
	//monSpeed == 0 means the monster gets 1 turn for every 2 player turns
	private int monSpeed;
	
	private int monDefense;
	private int monAtkValue;

	private static Scanner input;

	private static TreeMap<String, Monster> monsterList = new TreeMap<>();

	public Monster()
	{

	}

	public static void readMonsterFile(File f)
	{
		// creates scanner to read file
		try
		{
			input = new Scanner(f);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		// loop used to cycle through lines in file
		while (input.hasNextLine())
		{
			String[] nextLine = input.nextLine().split(separator);
			
			// indicator point that loop breaks to when ever the end of a rooms respective
			// data is reached
			OUTER_LOOP: if (nextLine[0].equals("ID"))
			{
				Monster newMonster = new Monster();
				String newMonsterID = nextLine[1];

				// checks current text line to get data type and info so that information is
				// added to the right variable/arraylist
				while (input.hasNextLine())
				{
					nextLine = input.nextLine().split(separator);
					
					if (nextLine[0].equals("monName"))
					{
						newMonster.enName = nextLine[1];
					}
					
					if (nextLine[0].equals("monDesc"))
					{
						newMonster.monDesc = nextLine[1];
					}
					
					if (nextLine[0].equals("monHealth"))
					{
						newMonster.enHealth = Integer.parseInt(nextLine[1]);
					}
					
					if (nextLine[0].equals("monDefense"))
					{
						newMonster.monDefense = Integer.parseInt(nextLine[1]);
					}
					
					if (nextLine[0].equals("atkName"))
					{
						newMonster.atkName = nextLine[1];
					}
					
					if (nextLine[0].equals("weakness"))
					{
						newMonster.weakness = nextLine[1];
					}
					
					if (nextLine[0].equals("location"))
					{
						newMonster.location = nextLine[1];
					}
					
					if (nextLine[0].equals("monSpeed"))
					{
						newMonster.monSpeed = Integer.parseInt(nextLine[1]);
					}
					
					if (nextLine[0].equals("itemDrop"))
					{
						newMonster.itemDrop = nextLine[1];
					}
					
					if (nextLine[0].equals("endMon") || nextLine[0].equals("ID"))
					{
						monsterList.put(newMonsterID, newMonster);
						break OUTER_LOOP;
					}
					
				}
			}
		}
	}
	
	public TreeMap<String, Monster> getMonsterList()
	{
		return monsterList;
	}
	
	public String getDesc()
	{
		return monDesc;
	}

	public int getMonDefense()
	{
		return monDefense;
	}
	
	public int getMonAtkValue()
	{
		return monAtkValue;
	}

	public int getMonSpeed()
	{
		return monSpeed;
	}
	
	public void setMonSpeed(int newSpeed)
	{
		monSpeed = newSpeed;
	}

	public String getAtkName()
	{
		return atkName;
	}

	public String getMonWeakness()
	{
		return weakness;
	}

	public String getMonLocation()
	{
		return location;
	}
	
	public String getItemDrop()
	{
		return itemDrop;
	}
	
}