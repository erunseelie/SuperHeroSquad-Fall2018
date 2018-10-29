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

}