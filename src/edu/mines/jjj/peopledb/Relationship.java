package edu.mines.jjj.peopledb;

/**
 * Enum class for possible relationship settings
 * @author jkeyoth, jdinges
 *
 */
public enum Relationship {
  Single, Married, Engaged, InRelationship;
  
  /**
   * Converts from string to Enum
   * @param r String
   * @return Relationship Enum
   */
  public static Relationship fromString(String r) {
    if (r.toUpperCase().equals("SINGLE")) {
      return Relationship.Single;
    }
    else if (r.toUpperCase().equals("MARRIED")) {
      return Relationship.Married;
    }
    else if (r.toUpperCase().equals("ENGAGED")) {
      return Relationship.Engaged;
    }
    else if (r.toUpperCase().equals("INRELATIONSHIP")) {
      return Relationship.InRelationship;
    }
    else {
      throw new IllegalArgumentException("Relationship " + r + " not recognized.");
    }
  }
}
