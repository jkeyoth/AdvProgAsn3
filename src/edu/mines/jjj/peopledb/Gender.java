package edu.mines.jjj.peopledb;

public enum Gender
{
	Male, Female;
	
	public static Gender fromString(String g)
	{
		if (g.toUpperCase() == "MALE")
			return Gender.Male;
		else if (g.toUpperCase() == "FEMALE")
			return Gender.Female;
		else
			throw new IllegalArgumentException("Gender not recognized.");
	}
}
