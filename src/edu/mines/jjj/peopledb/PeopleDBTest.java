package edu.mines.jjj.peopledb;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import edu.mines.jjj.peopledb.Person.PersonBuilder;

public class PeopleDBTest
{
	private PeopleDB db = PeopleDB.getInstance();
	
	@Test
	public void testInsertAndInfo()
	{
		Person p =
			new PersonBuilder().firstName("dbTestFirst").lastName("dbTestLast")
				.username("dbTestUser").gender(Gender.Male)
				.relationship(Relationship.Single).build();
		
		db.insertPerson(p);
		
		ArrayList<String> al = db.getPersonInfo(p);
		
		assertEquals("First name doesn't match", al.get(0), "dbTestFirst");
		assertEquals("Last name doesn't match", al.get(1), "dbTestLast");
		assertEquals("User name doesn't match", al.get(2), "dbTestUser");
		assertEquals("Gender doesn't match", al.get(3), Gender.Male.toString());
		assertEquals("Relationship doesn't match", al.get(4),
			Relationship.Single.toString());
		
	}
}
