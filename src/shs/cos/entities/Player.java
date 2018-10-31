package shs.cos.entities;

public class Player extends Character
{

	private String currentWeaponID;
	private String currentArmorID;

	public Player()
	{
		enHealth = 100;
	}

	private String getCurrentWeapon()
	{
		return currentWeaponID;
	}

	private String getCurrentArmor()
	{
		return currentArmorID;
	}

	private void setCurrentWeapon(String newWeapon)
	{
		currentWeaponID = newWeapon;
	}

	private void setCurrentArmor(String newArmor)
	{
		currentArmorID = newArmor;
	}

}
