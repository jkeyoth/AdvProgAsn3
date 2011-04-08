package edu.mines.jjj.peopledb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.mines.jjj.peopledb.Person.PersonBuilder;

/**
 * This class handles all database transactions.
 * 
 * @author Josh Keyoth, Josh Dinges, James Brown
 * 
 */

public final class PeopleDB
{

	private static PeopleDB singleton = null;

	// column names: TABLE_NAME_TYPE
	public static final String ID_INT = "id"; // all tables will have an id column

	public static final String PEOPLE_FIRSTNAME_TEXT = "fname";
	public static final String PEOPLE_LASTNAME_TEXT = "lname";
	public static final String PEOPLE_USERNAME_TEXT = "uname";
	public static final String PEOPLE_GENDER_TEXT = "gender";
	public static final String PEOPLE_RELATIONSHIP_TEXT = "relationship";
	public static final String PEOPLE_AGE_INT = "age";

	public static final String FRIENDSHIP_PERSON1_FK_INT = "person1";
	public static final String FRIENDSHIP_PERSON2_FK_INT = "person2";

	public static final String GROUP_NAME_TEXT = "name";
	public static final String GROUP_DESCRIPTION_TEXT = "description";

	public static final String GROUP_MEMBER_MEMBER_ID_FK_INT = "member_id";
	public static final String GROUP_MEMBER_GROUP_ID_FK_INT = "group_id";
	// TODO: maybe add a date joined?

	// table names
	public static final String TABLE_PEOPLE = "people";
	public static final String TABLE_FRIENDSHIP = "friendship";
	public static final String TABLE_GROUP = "groups";
	public static final String TABLE_GROUP_MEMBER = "group_member";

	private Connection connection;

	private PeopleDB()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Failed to load mSQL driver.");
			System.exit(1);
		}

		try
		{

			connection = DriverManager.getConnection("jdbc:sqlite:people.db");
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}

		runCreate();
	}

	// Assume that friends table is named friends
	private void addFriendship(Person friend1, Person friend2)
	{
		int friend1Id, friend2Id;

		String stmtBegin = "insert into friends(";

	}

	public void addPersonToGroup(Person p, Group g)
	{
		try
		{

			int personId = getIdFromUsername(p.getUsername());

			ResultSet results =
				singleton.connection.createStatement().executeQuery(
					"select " + ID_INT + " from " + TABLE_GROUP + " where "
						+ GROUP_NAME_TEXT + " = '"
						+ g.getName() + "';");

			int groupId = results.getInt(ID_INT);

			results.close();

			String cols =
				GROUP_MEMBER_MEMBER_ID_FK_INT + ", " + GROUP_MEMBER_GROUP_ID_FK_INT;
			String vals = "?,?";

			PreparedStatement stmt = connection.prepareStatement("INSERT INTO "
				+ TABLE_GROUP_MEMBER + "("
				+ cols + ") values(" + vals + ");");

			stmt.setInt(1, personId);
			stmt.setInt(2, groupId);

			stmt.executeUpdate();
			stmt.close();

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public ArrayList<Group> buildAllGroups()
	{
		ArrayList<Group> allGroups = new ArrayList<Group>();

		try
		{
			ResultSet results = singleton.connection.createStatement().executeQuery(
				"select " + GROUP_NAME_TEXT + " from " + TABLE_GROUP + ";");

			while (results.next())
			{
				allGroups.add(buildGroup(results.getString(GROUP_NAME_TEXT)));
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return allGroups;
	}

	public ArrayList<Person> buildAllPeople()
	{
		ArrayList<Person> allPeeps = new ArrayList<Person>();

		ResultSet results;

		try
		{
			results =
				singleton.connection.createStatement().executeQuery(
					"select " + PEOPLE_USERNAME_TEXT + " from " + TABLE_PEOPLE + ";");

			while (results.next())
			{
				allPeeps
					.add(buildPerson(results.getString(PEOPLE_USERNAME_TEXT)));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return allPeeps;
	}

	public Group buildGroup(String groupName)
	{
		try
		{
			ResultSet results;

			results =
				singleton.connection.createStatement().executeQuery(
					"select * from " + TABLE_GROUP + " where " + GROUP_NAME_TEXT + " = '"
						+ groupName
						+ "';");

			return new Group(results.getString(GROUP_NAME_TEXT), results
				.getString(GROUP_DESCRIPTION_TEXT));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	// TODO: Add friendships and groups
	public Person buildPerson(String username)
	{
		ResultSet results;

		try
		{
			results =
				singleton.connection.createStatement().executeQuery(
					"select * from " + TABLE_PEOPLE + " where " + PEOPLE_USERNAME_TEXT
						+ " = '" + username
						+ "';");

			return new PersonBuilder()
					.firstName(results.getString(PEOPLE_FIRSTNAME_TEXT))
					.lastName(results.getString(PEOPLE_LASTNAME_TEXT))
					.username(results.getString(PEOPLE_USERNAME_TEXT))
					.gender(Gender.fromString(results.getString(PEOPLE_GENDER_TEXT)))
					.relationship(
						Relationship.fromString(results
							.getString(PEOPLE_RELATIONSHIP_TEXT)))
					.age(results.getInt(PEOPLE_AGE_INT))
					.build();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public void deleteAllRows()
	{
		try
		{
			connection.createStatement().execute("delete from " + TABLE_PEOPLE + ";");
			connection.createStatement().execute("delete from " + TABLE_GROUP + ";");
			connection.createStatement().execute(
				"delete from " + TABLE_GROUP_MEMBER + ";");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public ArrayList<Group> getGroupsForPerson(Person p)
	{
		ArrayList<Group> groupsForPerson = new ArrayList<Group>();

		try
		{
			int pId = getIdFromUsername(p.getUsername());

			ResultSet results =
				singleton.connection.createStatement().executeQuery(
					"select " + GROUP_MEMBER_GROUP_ID_FK_INT + " from "
						+ TABLE_GROUP_MEMBER + " where " +
						GROUP_MEMBER_MEMBER_ID_FK_INT + " = " + pId + ";"
					);
			ArrayList<Integer> gIds = new ArrayList<Integer>();

			while (results.next())
			{
				gIds.add(results.getInt(GROUP_MEMBER_GROUP_ID_FK_INT));
			}

			for (int i : gIds)
			{

				String state = "select " + GROUP_NAME_TEXT + ", " + GROUP_DESCRIPTION_TEXT
							+ " from " + TABLE_GROUP +
							" where " + ID_INT + " = " + i + ";";
				results =
					singleton.connection.createStatement().executeQuery(state);

				groupsForPerson.add(new Group(results.getString(GROUP_NAME_TEXT), results
					.getString(GROUP_DESCRIPTION_TEXT)));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return groupsForPerson;
	}

	private int getIdFromUsername(String uname)
	{
		try
		{
			ResultSet results =
				singleton.connection.createStatement().executeQuery(
					"select id from people where " + PEOPLE_USERNAME_TEXT + " = '"
						+ uname + "'");

			return results.getInt("id");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return -1;
	}

	public void insertGroup(Group g)
	{
		String cols = GROUP_NAME_TEXT + ", " + GROUP_DESCRIPTION_TEXT;
		String vals = "?,?";

		try
		{
			PreparedStatement stmt =
				connection.prepareStatement("INSERT INTO " + TABLE_GROUP + "("
					+ cols + ") values(" + vals + ");");
			stmt.setString(1, g.getName());
			stmt.setString(2, g.getDescription());

			stmt.executeUpdate();
			stmt.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public boolean insertPerson(Person p)
	{
		try
		{
			String cols =
				PEOPLE_FIRSTNAME_TEXT + ", " + PEOPLE_LASTNAME_TEXT + ", "
					+ PEOPLE_USERNAME_TEXT + ", " + PEOPLE_GENDER_TEXT + ", "
					+ PEOPLE_RELATIONSHIP_TEXT + ", " + PEOPLE_AGE_INT;

			String vals = "?,?,?,?,?,?";

			PreparedStatement stmt =
				connection.prepareStatement("INSERT INTO " + TABLE_PEOPLE + "(" + cols
					+ ") values(" + vals + ");");

			stmt.setString(1, p.getFirstName());
			stmt.setString(2, p.getLastName());
			stmt.setString(3, p.getUsername());
			stmt.setString(4, p.getGender().toString());
			stmt.setString(5, p.getRelationship().toString());

			stmt.setInt(6, p.getAge());
			stmt.executeUpdate();
			stmt.close();

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return true;
	}

	private void runCreate()
	{
		String peopleState = "create table if not exists " + TABLE_PEOPLE + "(" +
			ID_INT + " integer primary key, " +
			PEOPLE_FIRSTNAME_TEXT + " text, " +
			PEOPLE_LASTNAME_TEXT + " text, " +
			PEOPLE_USERNAME_TEXT + " text unique, " +
			PEOPLE_GENDER_TEXT + " text, " +
			PEOPLE_RELATIONSHIP_TEXT + " text, " +
			PEOPLE_AGE_INT + " integer" +
			");";

		String groupState = "create table if not exists " + TABLE_GROUP + "(" +
			ID_INT + " integer primary key, " +
			GROUP_NAME_TEXT + " text unique, " +
			GROUP_DESCRIPTION_TEXT + " text);";

		String groupMemberState =
			"create table if not exists " + TABLE_GROUP_MEMBER + "(" +
				ID_INT + " integer primary key, " +
				GROUP_MEMBER_MEMBER_ID_FK_INT + " integer, " +
				GROUP_MEMBER_GROUP_ID_FK_INT + " integer, " +
				"FOREIGN KEY(" + GROUP_MEMBER_MEMBER_ID_FK_INT + ") REFERENCES "
				+ TABLE_PEOPLE + "(" + ID_INT + ")" +
				"FOREIGN KEY(" + GROUP_MEMBER_GROUP_ID_FK_INT + ") REFERENCES "
				+ TABLE_GROUP + "(" + ID_INT + ")" +
					");";

		try
		{
			connection.createStatement().execute(peopleState);
			connection.createStatement().execute(groupState);
			connection.createStatement().execute(groupMemberState);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	public static PeopleDB getInstance()
	{
		if (singleton == null)
		{
			singleton = new PeopleDB();
		}
		return singleton;
	}

}
