package edu.mines.jjj.peopledb;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import edu.mines.jjj.peopledb.Person.PersonBuilder;

/**
 * Test class for Person
 * 
 * @author jkeyoth
 * 
 */

public class PersonTest extends TestCase
{
	
	private Person person;
	
	@Before
	public void setup()
	{
		person =
			new PersonBuilder().firstName("testFirst").lastName("testLast").username(
				"testUser").gender(Gender.Male).relationship(Relationship.Single).age(21)
				.build();
		
	}
	
	@Test
	public void testPerson()
	{
		setup();
		assertEquals("First name doesn't match", person.getFirstName(), "testFirst");
		assertEquals("Last name doesn't match", person.getLastName(), "testLast");
		assertEquals("User name doesn't match", person.getUsername(), "testUser");
		assertEquals("Gender doesn't match", person.getGender(), Gender.Male);
		assertEquals("Age does not match", person.getAge(), 21);
		assertEquals("Relationship doesn't match", person.getRelationship(),
			Relationship.Single);
	}
	
	@Test
	public void testUpdate()
	{
		setup();
		PeopleDB.getInstance().deleteAllRows();
		
		person.update();
		
		Person p2 = PeopleDB.getInstance().buildPerson(person.getUsername());
		
		assertNotNull("p2 is null", p2);
		
		assertEquals("Person added does not match person returned", person, p2);
		
		person =
			new PersonBuilder().firstName("testFirstNew").lastName("testLast")
				.username(
					"testUser").gender(Gender.Male).relationship(Relationship.Single)
				.age(22)
				.build();
		
		person.update();
		
		p2 = PeopleDB.getInstance().buildPerson(person.getUsername());
		
		assertNotNull("p2 is null", p2);
		
		assertEquals("Person updated first name does not match person returned",
			p2.getFirstName(),
			"testFirstNew");
		assertEquals("Person updated age does not match person returned", p2.getAge(), 22);
	}
	
}
