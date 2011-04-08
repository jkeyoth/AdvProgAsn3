package edu.mines.jjj.peopledb;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import edu.mines.jjj.peopledb.Person.PersonBuilder;

public class PeopleDBTest
{
	private final PeopleDB db = PeopleDB.getInstance();

	@Test
	public void testBuildAllPeople()
	{
		db.deleteAllRows();

		Person p =
			new PersonBuilder().firstName("john").lastName("doe")
				.username("jdog").gender(Gender.Male)
				.relationship(Relationship.Married).age(21).build();
		Person p2 =
			new PersonBuilder().firstName("jane").lastName("doe")
				.username("ladyjane").gender(Gender.Female)
				.relationship(Relationship.Single).age(18).build(); // jane is such a liar

		p.update();// inserts into db
		p2.update();// inserts into db

		ArrayList<Person> allPeeps = db.buildAllPeople();

		for (Person tp : allPeeps)
		{
			System.out.println(tp);
		}

		assertEquals("Person p does not match", p, allPeeps.get(0));
		assertEquals("Person p2 does not match", p, allPeeps.get(1));

	}

	@Test
	public void testInsertAndBuildPerson()
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
