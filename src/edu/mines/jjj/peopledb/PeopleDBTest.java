package edu.mines.jjj.peopledb;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.mines.jjj.peopledb.Person.PersonBuilder;

public class PeopleDBTest
{
	private PeopleDB db = PeopleDB.getInstance();
	
	@Test
	public void testInsertAndBuild()
	{
		db.deleteAllRows();
		
		Person p =
			new PersonBuilder().firstName("dbTestFirst").lastName("dbTestLast")
				.username("dbTestUser").gender(Gender.Male)
				.relationship(Relationship.Single).age(21).build();
		
		db.insertPerson(p);
		
		Person p2 = db.buildPerson("dbTestUser");
		
		assertEquals("Person inserted does not match person returned", p, p2);
		
	}
}
