package edu.mines.jjj.peopledb;

import java.util.ArrayList;

/**
 * 
 * This class models a person.
 * 
 * @author jkeyoth, jdinges
 * 
 */

public class Person {
  /**
   * A builder class for Person
   * 
   * @author jkeyoth, jdinges
   * 
   */

  public static class PersonBuilder {
    private String firstName, lastName, username;
    private Gender gender;
    private Relationship relationship;
    private int age;

    /**
     * Sets default values, so not everything has to be built in testing. Default names and age will
     * not pass validation in Person constructor
     */
    public PersonBuilder() {
      firstName = "";
      lastName = "";
      username = "";
      gender = Gender.Male;
      relationship = Relationship.Single;
      age = -1;
    }

    /**
     * Set the age on the builder
     * 
     * @param age
     *          the age to give the person
     * @return this builder, for chaining
     */
    public PersonBuilder age(final int age) {
      this.age = age;
      return this;
    }

    /**
     * Build the person. Calls Person's constructor.
     * 
     * @return The built Person
     */
    public Person build() {
      return new Person(this);
    }

    /**
     * Set the first name on the builder
     * 
     * @param firstName
     *          The first name to give the person
     * @return this builder, for chaining
     */
    public PersonBuilder firstName(final String firstName) {
      this.firstName = firstName;
      return this;
    }

    /**
     * Set the gender on the builder
     * 
     * @param gender
     *          The gender to give this person
     * @return this builder, for chaining
     */
    public PersonBuilder gender(final Gender gender) {
      this.gender = gender;
      return this;
    }

    public int getAge() {
      return age;
    }

    public String getFirstName() {
      return firstName;
    }

    public Gender getGender() {
      return gender;
    }

    public String getLastName() {
      return lastName;
    }

    public Relationship getRelationship() {
      return relationship;
    }

    public String getUsername() {
      return username;
    }

    /**
     * Set the last name on the builder
     * 
     * @param lastName
     *          The last name to give the person
     * @return this builder, for chaining
     */

    public PersonBuilder lastName(final String lastName) {
      this.lastName = lastName;
      return this;
    }

    /**
     * Set the relationship status on the builder
     * 
     * @param relationship
     *          The relationship status to give the person
     * @return this builder, for chaining
     */
    public PersonBuilder relationship(final Relationship relationship) {
      this.relationship = relationship;
      return this;
    }

    /**
     * Set the username on the builder
     * 
     * @param username
     *          The username to give the user.
     * @return this builder, for chaining
     */
    public PersonBuilder username(final String username) {
      this.username = username;
      return this;
    }

  }

  private final ArrayList<Person> friends;
  private final ArrayList<Group> groups;
  private final PeopleDB db;
  private final String firstName, lastName, username;
  private final Gender gender;
  private final Relationship relationship;
  private final int age;

  /**
   * The constructor for a Person. Used exclusively with the builder.
   * 
   * @param builder
   */

  private Person(final PersonBuilder builder) {
    this.firstName = builder.getFirstName();
    if (!checkValidName(firstName))
      throw new IllegalArgumentException("First name not valid");

    this.lastName = builder.getLastName();
    if (!checkValidName(lastName))
      throw new IllegalArgumentException("Last name not valid");

    this.username = builder.getUsername();
    if (!checkValidUsername(username))
      throw new IllegalArgumentException("User name not valid");

    this.gender = builder.getGender();
    this.relationship = builder.getRelationship();

    this.age = builder.getAge();
    if (age < 0)
      throw new IllegalArgumentException("Negative age is not allowed");

    friends = new ArrayList<Person>();
    groups = new ArrayList<Group>();
    db = PeopleDB.getInstance();
  }

  /**
   * Add a person to this person's friend list.
   * 
   * @param newFriend
   *          The person to add
   */
  public void addFriend(final Person newFriend) {
    if (!friends.contains(newFriend)) {
      friends.add(newFriend);
    }
  }

  /**
   * Add this person to a group.
   * 
   * @param g
   */
  public void addGroup(final Group g) {
    if (!groups.contains(g)) {
      groups.add(g);
    }
  }

  /**
   * Copy this person.
   * 
   * @return a copy of this person
   */
  public Person copy() {
    final Person retPerson = new PersonBuilder().firstName(firstName).lastName(lastName)
            .username(username).gender(gender).relationship(relationship).build();

    for (final Group g : groups) {
      retPerson.addGroup(g.copy());
    }

    for (final Person p : friends) {
      retPerson.addFriend(p);
    }

    return retPerson;
  }

  // auto generated by eclipse
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final Person other = (Person) obj;
    if (age != other.age)
      return false;
    if (firstName == null) {
      if (other.firstName != null)
        return false;
    }
    else if (!firstName.equals(other.firstName))
      return false;
    if (friends == null) {
      if (other.friends != null)
        return false;
    }
    else if (!friends.equals(other.friends))
      return false;
    if (gender != other.gender)
      return false;
    if (lastName == null) {
      if (other.lastName != null)
        return false;
    }
    else if (!lastName.equals(other.lastName))
      return false;
    if (relationship != other.relationship)
      return false;
    if (username == null) {
      if (other.username != null)
        return false;
    }
    else if (!username.equals(other.username))
      return false;
    return true;
  }

  public int getAge() {
    return age;
  }

  public String getFirstName() {
    return firstName;
  }

  public Gender getGender() {
    return gender;
  }

  /**
   * Get all groups this person belongs to as an array list.
   * 
   * @return An array list of all groups.
   */

  public ArrayList<Group> getGroups() {
    final ArrayList<Group> retGroups = new ArrayList<Group>();
    for (final Group g : groups) {
      retGroups.add(g.copy());
    }

    return retGroups;
  }

  public String getLastName() {
    return lastName;
  }

  public Relationship getRelationship() {
    return relationship;
  }

  public String getUsername() {
    return username;
  }

  // auto generated by eclipse
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + age;
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((friends == null) ? 0 : friends.hashCode());
    result = prime * result + ((gender == null) ? 0 : gender.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((relationship == null) ? 0 : relationship.hashCode());
    result = prime * result + ((username == null) ? 0 : username.hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sbuilder = new StringBuilder();
    sbuilder.append("User ").append(username).append(": ").append(firstName).append(" ")
            .append(lastName).append(", ").append(age).append(" year old ").append(gender)
            .append(". Relationship status: ").append(relationship);

    String str = sbuilder.toString();

    return str;
  }

  /**
   * Add or update this person to the database
   */
  public void update() {
    db.insertPerson(this);
  }

  private boolean checkValidName(String name) {
    boolean good = true;
    good = name.length() > 0;
    if (good) {
      good = !name.matches(".*[^a-zA-Z].*");
    }
    else {
      return false;
    }

    return good;
  }

  private boolean checkValidUsername(String uname) {
    boolean good = true;
    good = uname.length() > 0;
    if (good) {
      good = !uname.matches(".*[^a-zA-Z0-9_].*");
    }
    else {
      return false;
    }

    return good;
  }
}
