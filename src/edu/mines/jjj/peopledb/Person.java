package edu.mines.jjj.peopledb;

import java.util.ArrayList;

public class Person
{
	private final PeopleDB db;

	private final String firstName, lastName, username;
	private final Gender gender;
	private final Relationship relationship;

	// TODO: make a builder pattern thinger
	public Person(String fname, String lname, String uname, Gender gender, Relationship relationship)
	{
		this.firstName = fname;
		this.lastName = lname;
		this.username = uname;
		this.gender = gender;
		this.relationship = relationship;
		db = PeopleDB.getInstance();
	}

	public ArrayList<String> asArrayList()
	{
		ArrayList<String> al = new ArrayList<String>();
		al.add(firstName);
		al.add(lastName);
		al.add(username);
		al.add(gender.toString());
		al.add(relationship.toString());

		return al;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public Gender getGender()
	{
		return gender;
	}

	public String getLastName()
	{
		return lastName;
	}

	public Relationship getRelationship()
	{
		return relationship;
	}

	public String getUsername()
	{
		return username;
	}

	public void update()
	{
		db.insertPerson(this);
	}

	public static void main(String[] args)
	{
		Person p = new Person("testFirst", "testLast", "testUser", Gender.Male,
			Relationship.Engaged);
		p.update();
	}
}
