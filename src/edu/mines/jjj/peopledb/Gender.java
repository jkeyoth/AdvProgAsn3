package edu.mines.jjj.peopledb;

public enum Gender
{
	Male, Female;
	
	public static Gender fromString(String g)
	{
		if (g.toUpperCase().equals("MALE"))
			return Gender.Male;
		else if (g.toUpperCase().equals("FEMALE"))
			return Gender.Female;
		else
			throw new IllegalArgumentException("Gender " + g + " not recognized.");
	}
}
