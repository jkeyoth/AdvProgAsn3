package edu.mines.jjj.peopledb;

public enum Relationship
{
	Single, Married, Engaged, InRelationship;
	
	public static Relationship fromString(String r)
	{
		if (r.toUpperCase() == "SINGLE")
			return Relationship.Single;
		else if (r.toUpperCase() == "MARRIED")
			return Relationship.Married;
		else if (r.toUpperCase() == "ENGAGED")
			return Relationship.Engaged;
		else if (r.toUpperCase() == "INRELATIONSIHP")
			return Relationship.InRelationship;
		else
			throw new IllegalArgumentException("Relationship not recognized.");
	}
}
