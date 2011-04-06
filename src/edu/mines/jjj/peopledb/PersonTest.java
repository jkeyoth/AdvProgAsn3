package edu.mines.jjj.peopledb;

import java.util.ArrayList;

import junit.framework.TestCase;

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
		setup();
		ArrayList<String> al = person.asArrayList();
		assertEquals("First name doesn't match", al.get(0), "testFirst");
		assertEquals("Last name doesn't match", al.get(1), "testLast");
		assertEquals("User name doesn't match", al.get(2), "testUser");
		assertEquals("Gender doesn't match", al.get(3), Gender.Male.toString());
		assertEquals("Relationship doesn't match", al.get(4), Relationship.Single.toString());

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
		setup();
		PeopleDB.getInstance().deleteAllRows();

		person.update();

		ArrayList<String> al = PeopleDB.getPersonInfo(person);

		assertEquals("First name doesn't match", al.get(0), "testFirst");
		assertEquals("Last name doesn't match", al.get(1), "testLast");
		assertEquals("User name doesn't match", al.get(2), "testUser");
		assertEquals("Gender doesn't match", al.get(3), Gender.Male.toString());
		assertEquals("Relationship doesn't match", al.get(4), Relationship.Single.toString());

	}

}
