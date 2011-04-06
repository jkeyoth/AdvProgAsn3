package edu.mines.jjj.peopledb;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import edu.mines.jjj.peopledb.Person.PersonBuilder;

public class PersonTest extends TestCase
{

	private Person person;

	@Before
	public void setup()
	{
		person = new PersonBuilder().firstName("testFirst").lastName("testLast").username(
			"testUser").gender(Gender.Male).relationship(Relationship.Single).build();

	}

	@Test
	public void testAsArrayList()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testPerson()
	{
		setup();
		assertEquals("First name doesn't match", person.getFirstName(), "testFirst");
		assertEquals("Last name doesn't match", person.getLastName(), "testLast");
		assertEquals("User name doesn't match", person.getUsername(), "testUser");
		assertEquals("Gender doesn't match", person.getGender(), Gender.Male);
		assertEquals("Relationship doesn't match", person.getRelationship(), Relationship.Single);
	}

	@Test
	public void testUpdate()
	{
		fail("Not yet implemented");
	}

}
