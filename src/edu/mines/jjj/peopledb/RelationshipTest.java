package edu.mines.jjj.peopledb;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RelationshipTest
{
	
	@Test
	public void testFromString()
	{
		assertEquals("Does not match", Relationship.fromString("Single"),
			Relationship.Single);
		assertEquals("Does not match", Relationship.fromString("InRelationship"),
			Relationship.InRelationship);
		assertEquals("Does not match", Relationship.fromString("married"),
			Relationship.Married);
		assertEquals("Does not match", Relationship.fromString("enGaged"),
			Relationship.Engaged);
	}
	
}
