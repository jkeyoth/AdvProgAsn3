package edu.mines.jjj.peopledb;

public class Group
{
	private final String name;
	private String description;
	
	public Group(String name, String description)
	{
		this.name = name;
		this.description = name;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Group copy()
	{
		return new Group(name, description);
	}
	
}
