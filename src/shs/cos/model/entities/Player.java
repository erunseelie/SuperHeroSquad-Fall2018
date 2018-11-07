package shs.cos.model.entities;

public class Player extends Character
{
	//starting weapon is revolver
	private String currentWeaponID;
	
	//no starting armor, defaults to 0
	private String currentArmorID;

	public static int healthDefault = 100;

	public Player()
	{
		//check for balance
		enHealth = healthDefault;
	}

	public String getCurrentWeapon()
	{
		return currentWeaponID;
	}

	public String getCurrentArmor()
	{
		return currentArmorID;
	}

	public void setCurrentWeapon(String newWeapon)
	{
		currentWeaponID = newWeapon;
	}

	public void setCurrentArmor(String newArmor)
	{
		currentArmorID = newArmor;
	}

}
