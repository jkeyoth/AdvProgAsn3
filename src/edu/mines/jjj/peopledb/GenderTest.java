package edu.mines.jjj.peopledb;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GenderTest {

  @Test
  public void testFromString() {
    assertEquals("Does not match", Gender.fromString("Male"), Gender.Male);
    assertEquals("Does not match", Gender.fromString("male"), Gender.Male);
    assertEquals("Does not match", Gender.fromString("MALE"), Gender.Male);
    assertEquals("Does not match", Gender.fromString("Female"), Gender.Female);
    assertEquals("Does not match", Gender.fromString("female"), Gender.Female);
    assertEquals("Does not match", Gender.fromString("FEMALE"), Gender.Female);
  }

}
