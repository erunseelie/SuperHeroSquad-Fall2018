package shs.cos.entities;

public class Player extends Character
{

	private String currentWeaponID;
	private String currentArmorID;

	public Player()
	{
		//check for balance
		enHealth = 100;
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
