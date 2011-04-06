package edu.mines.jjj.peopledb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class handles all database transactions.
 * 
 * @author Josh Keyoth, Josh Dinges, James Brown
 * 
 */

public final class PeopleDB
{

	private static PeopleDB singleton = null;

	/**
	 * database column names
	 */
	public static String TABLE_PEOPLE = "people";

	private static ArrayList<String> PEOPLE_COLUMNS;

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

		PEOPLE_COLUMNS = new ArrayList<String>();
		PEOPLE_COLUMNS.add("firstName");
		PEOPLE_COLUMNS.add("lastName");
		PEOPLE_COLUMNS.add("userName");
		PEOPLE_COLUMNS.add("gender");
		PEOPLE_COLUMNS.add("relationship");
		runCreate();
	}
	public void deleteAllRows()
	{
		try
		{
			connection.createStatement().execute("delete from people;");
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
			String cols = "(";
			String vals = "(";
			for (String s : PEOPLE_COLUMNS)
			{
				cols += s + ", ";
				vals += "?, ";
			}

			cols = cols.substring(0, cols.length() - 2);
			vals = vals.substring(0, vals.length() - 2);

			cols += ") ";
			vals += ");";

			PreparedStatement stmt = connection
				.prepareStatement("INSERT INTO people" + cols + " values " + vals);

			System.out.println(cols);

			ArrayList<String> infoList = p.asArrayList();

			for (int i = 1; i < infoList.size() + 1; i++)
			{
				stmt.setString(i, infoList.get(i - 1));

			}

			stmt.executeUpdate();
			stmt.close();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
	private void runCreate()
	{
		String state = "create table if not exists people(id integer primary key, ";

		for (String s : PEOPLE_COLUMNS)
		{
			String toAdd = s + " text, ";
			if (s == "username")
				toAdd = s + " text unique, ";
			state += toAdd;

		}
		state = state.substring(0, state.length() - 2);

		state += ");";

		System.out.println(state);
		try
		{
			connection.createStatement().execute(state);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}
	
	//Assume that friends table is named friends
	private void addFriendship(Person friend1, Person friend2)
	{
		int friend1Id, friend2Id;
		
		
		String stmtBegin = "insert into friends(";
		
	}

	public static PeopleDB getInstance()
	{
		if (singleton == null)
		{
			singleton = new PeopleDB();
		}
		return singleton;
	}

	public static ArrayList<String> getPersonInfo(Person p)
	{
		ArrayList<String> info = new ArrayList<String>();
		ResultSet results;

		try
		{
			results = singleton.connection.createStatement()
				.executeQuery("select * from people where userName = '"
					+ p.getUsername() + "'");

			for (String s : PEOPLE_COLUMNS)
				info.add(results.getString(s));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return info;
	}

}
