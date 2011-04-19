package edu.mines.jjj.peopledb;

/**
 * Handles creation and maintenance of Group
 * @author jkeyoth, jdinges
 *
 */
public class Group {
  private final String name;
  private String description;

  public Group(String name, String description) {
    this.name = name;
    if (!checkValidName(name)) {
      throw new IllegalArgumentException("Group name is not valid");
    }
    this.description = description;
  }
  
  /**
   * Returns Description of group
   * @return Group
   */
  public String getDescription() {
    return description;
  }

  /**
   * User can define description of group
   * @param description String
   */
  public void setDescription(String description) {
    this.description = description;
  }
  
  /**
   * Returns the name of the Group
   * @return String
   */
  public String getName() {
    return name;
  }
  
  /**
   * Updates instance
   */
  public void update() {
    PeopleDB.getInstance().insertGroup(this);
  }
  
  /**
   * Copies the Group
   * @return Group
   */
  public Group copy() {
    return new Group(name, description);
  }

  // auto generated by eclipse
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  // auto generated by eclipse
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Group other = (Group) obj;
    if (description == null) {
      if (other.description != null) {
        return false;
      }
    }
    else if (!description.equals(other.description)) {
      return false;
    }
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    }
    else if (!name.equals(other.name)) {
      return false;
    }
    return true;
  }

  private boolean checkValidName(String name) {
    boolean good = true;
    good = name.length() > 0;
    if (good) {
      good = !name.matches(".*[^a-zA-Z0-9].*");
    }
    else {
      return false;
    }

    return good;
  }

  @Override
  public String toString() {
    return name + " : " + description;
  }

}
