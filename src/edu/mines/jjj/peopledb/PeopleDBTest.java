package edu.mines.jjj.peopledb;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import edu.mines.jjj.peopledb.Person.PersonBuilder;

/**
 * 
 * Test class for PeopleDBTest
 * 
 * @author jkeyoth, jdinges
 * 
 */

public class PeopleDBTest {
  private final PeopleDB db = PeopleDB.getInstance();

  /*
   * @Test public void testCreateFriendship() { db. deleteAllRows(); Person p1 = new
   * PersonBuilder().firstName("john").lastName("doe") .username("jdog").gender(Gender.Male)
   * .relationship(Relationship.Married).age(21).build();
   * 
   * Person p2 = new PersonBuilder().firstName("jose").lastName("quervo")
   * .username("qBeer").gender(Gender.Male) .relationship(Relationship.Single).age(25).build();
   * 
   * db.addFriendship(p1, p2);
   * 
   * //Finish later
   * 
   * }
   */

  @Test
  public void testAddPersonToGroup_getGroupsForPerson() {
    db.deleteAllRows();

    Person p = new PersonBuilder().firstName("john").lastName("doe").username("jdog")
            .gender(Gender.Male).relationship(Relationship.Married).age(21).build();

    Group g1 = new Group("testGroup1", "first group for testing");
    Group g2 = new Group("testGroup2", "second group for testing");

    p.update();
    g1.update();
    g2.update();

    db.addPersonToGroup(p, g1);
    db.addPersonToGroup(p, g2);

    ArrayList<Group> groups = db.getGroupsForPerson(p);

    Group retg1 = groups.get(0), retg2 = groups.get(1);

    assertEquals("Group 1 does not match", g1, retg1);
    assertEquals("Group 2 does not match", g2, retg2);

  }

  @Test
  public void testBuildAllGroups() {
    db.deleteAllRows();

    Group g = new Group("testGroup1", "first group for testing");
    Group g2 = new Group("testGroup2", "second group for testing");

    g.update();
    g2.update();

    ArrayList<Group> allGroups = db.buildAllGroups();

    assertEquals("Group 1 doesnt match", g, allGroups.get(0));
    assertEquals("Group 2 doesnt match", g2, allGroups.get(1));
  }

  @Test
  public void testBuildAllPeople() {
    db.deleteAllRows();

    Person p = new PersonBuilder().firstName("john").lastName("doe").username("jdog")
            .gender(Gender.Male).relationship(Relationship.Married).age(21).build();
    Person p2 = new PersonBuilder().firstName("jane").lastName("doe").username("ladyjane")
            .gender(Gender.Female).relationship(Relationship.Single).age(18).build(); // jane is
                                                                                      // such a liar

    p.update();// inserts into db
    p2.update();// inserts into db

    ArrayList<Person> allPeeps = db.buildAllPeople();

    assertEquals("Person p does not match", p, allPeeps.get(0));
    assertEquals("Person p2 does not match", p2, allPeeps.get(1));

  }

  @Test
  public void testInsertAndBuildGroup() {
    db.deleteAllRows();

    Group g = new Group("testGroup", "a group used for testing");

    g.update(); // inserts into db.

    Group g2 = db.buildGroup("testGroup");

    assertEquals("Groups do not match", g, g2);
  }

  @Test
  public void testInsertAndBuildPerson() {
    db.deleteAllRows();

    Person p = new PersonBuilder().firstName("dbTestFirst").lastName("dbTestLast")
            .username("dbTestUser").gender(Gender.Male).relationship(Relationship.Single).age(21)
            .build();

    db.insertPerson(p);

    Person p2 = db.buildPerson("dbTestUser");

    assertEquals("Person inserted does not match person returned", p, p2);

    // test update instead of insert
    p = new PersonBuilder().firstName("dbTestFirst").lastName("dbTestLast").username("dbTestUser")
            .gender(Gender.Male).relationship(Relationship.Single).age(22).build();

    p.update();

    Person p3 = db.buildPerson("dbTestUser");

    assertEquals("Update did not change age", 22, p3.getAge());
  }
}
