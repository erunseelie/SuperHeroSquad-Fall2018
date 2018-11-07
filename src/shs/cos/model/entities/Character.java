package shs.cos.model.entities;

public abstract class Character
{

	protected String enName;
	protected int enHealth;

	public String getName()
	{
		return enName;
	}

	public int getHealth()
	{
		return enHealth;
	}
	
	public void applyDamage(int damage)
	{
		enHealth -= damage;
	}

}
