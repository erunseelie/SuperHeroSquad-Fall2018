package shs.cos.entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

public class Monster extends Character
{

	private static String seperator = "::";

	private String monsterName;
	private String description;
	private int monHealth;
	private int monDefense;
	private String atkName;
	private String weakness;
	private String location;
	private int monSpeed;

	private static Scanner input;

	private TreeMap<String, Monster> monsterList = new TreeMap<>();

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

		}
	}
	
	public String getName()
	{
		return monsterName;
	}
	
	public String getDesc()
	{
		return description;
	}
	
	public TreeMap<String, Monster> getMonsterList()
	{
		return monsterList;
	}
	
	public int getMonHealth()
	{
		return monHealth;
	}
	
	public void setMonHealh(int damage)
	{
		monHealth -= damage;
	}
	
	public int getMonDefense()
	{
		return monDefense;
	}
	
	public int getMonSpeed()
	{
		return monSpeed;
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

}