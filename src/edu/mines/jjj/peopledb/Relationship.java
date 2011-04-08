package edu.mines.jjj.peopledb;

public enum Relationship
{
	Single, Married, Engaged, InRelationship;
	
	public static Relationship fromString(String r)
	{
		if (r.toUpperCase().equals("SINGLE"))
			return Relationship.Single;
		else if (r.toUpperCase().equals("MARRIED"))
			return Relationship.Married;
		else if (r.toUpperCase().equals("ENGAGED"))
			return Relationship.Engaged;
		else if (r.toUpperCase().equals("INRELATIONSHIP"))
			return Relationship.InRelationship;
		else
			throw new IllegalArgumentException("Relationship " + r + " not recognized.");
	}
}
