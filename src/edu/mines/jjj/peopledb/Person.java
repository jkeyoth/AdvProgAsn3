package edu.mines.jjj.peopledb;

import java.util.ArrayList;

public class Person
{
	
	public static class PersonBuilder
	{
		private String firstName, lastName, username;
		private Gender gender;
		private Relationship relationship;
		

		public PersonBuilder()
		{
		}

		public Person build()
		{
			return new Person(this);
		}
		public PersonBuilder firstName(String firstName)
		{
			this.firstName = firstName;
			return this;
		}
		public PersonBuilder gender(Gender gender)
		{
			this.gender = gender;
			return this;
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

		public PersonBuilder lastName(String lastName)
		{
			this.lastName = lastName;
			return this;
		}

		public PersonBuilder relationship(Relationship relationship)
		{
			this.relationship = relationship;
			return this;
		}

		public PersonBuilder username(String username)
		{
			this.username = username;
			return this;
		}

	}
	private ArrayList<Person> friends = new ArrayList<Person>();
	private final PeopleDB db;
	private final String firstName, lastName, username;
	private final Gender gender;

	private final Relationship relationship;

	// TODO: make a builder pattern thinger
	public Person(PersonBuilder builder)
	{
		this.firstName = builder.getFirstName();
		this.lastName = builder.getLastName();
		this.username = builder.getUsername();
		this.gender = builder.getGender();
		this.relationship = builder.getRelationship();
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
	
	public void addFriend(Person newFriend){
		if(!friends.contains(newFriend))
			friends.add(newFriend);
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

}
