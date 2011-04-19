package edu.mines.jjj.peopledb;

/**
 * This class builds the GUI
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.mines.jjj.peopledb.Person.PersonBuilder;

public class MainGui extends JFrame implements ListSelectionListener, ActionListener {

  // Gui Members------------------------------------------------
  private JTabbedPane tabPan;

  // person panel stuff
  private JPanel personPan;

  // create person fields
  private JPanel personCreatePan;

  private JTextField fnameField;

  private JTextField lnameField;

  private JTextField unameField;

  private JComboBox genderComb;

  private JComboBox relationComb;

  private JSpinner ageSpin;

  private JButton perAddBtn;

  private JButton perClearBtn;

  // view person fields;
  private JPanel personViewPan;

  private JList personList;

  private DefaultListModel personListModel;

  private JLabel perNameOut;

  private JLabel perInfoOut;

  // add group stuff
  private JPanel groupCreatePan;

  private JTextField grpNameField;

  private JTextArea grpDescArea;

  private JButton grpAddBtn;

  private JButton grpClearBtn;

  // view group stuff
  private JPanel groupViewPan;

  private JPanel groupPan;

  private JList groupList;

  private DefaultListModel groupListModel;

  private JLabel grpNameOut;

  private JTextArea grpDescOut;

  // add friends stuff
  private JPanel friendCreatePan;

  private JPanel friendsPan;

  private JList friend1;

  private DefaultListModel friend1ListModel;

  private JList friend2;

  private DefaultListModel friend2ListModel;

  private JButton frdAddBtn;

  private JButton frdClearBtn;

  // view friends stuff
  private JPanel friendsViewPan;

  private JList friend1ViewList;
  private DefaultListModel friend1ViewListModel;

  private JList friendOf1ViewList;
  private DefaultListModel friendOf1ViewListModel;
  // End Gui Members-------------------------------------------------------

  private PeopleDB db;

  private ArrayList<Person> people;
  private ArrayList<Group> groups;

  public MainGui() {
    setupGui();

    db = PeopleDB.getInstance();

    loadPersonInfo();
    loadGroupInfo();
    loadFriendInfo();

  }
  
  /**
   * Makes updates everyone in the database with their list of friends
   */
  private void loadFriendInfo() {
	  ArrayList<String> friends1 = db.buildAllFriends1();
	  ArrayList<String> friends2 = db.buildAllFriends2();

	  for(int i = 0; i<friends1.size(); i++){
		  String f1 = friends1.get(i);
		  String f2 = friends2.get(i);
		  Person p1 = getPersonByUsername(f1);
		  people.remove(p1);
		  Person p2 = getPersonByUsername(f2);
		  people.remove(p2);
		  p1.addFriend(p2);
		  p2.addFriend(p1);
		  people.add(p1);
		  people.add(p2);
	  }
	  
  }

  /**
   * Loads all of the people into people ArrayList
   */
  private void loadPersonInfo() {
    people = db.buildAllPeople();

    personListModel.clear();

    for (Person p : people) {
      personListModel.addElement(p.getUsername());
    }
  }
  
  /**
   * Loads groups
   */
  private void loadGroupInfo() {
    groups = db.buildAllGroups();

    groupListModel.clear();

    for (Group g : groups) {
      groupListModel.addElement(g.getName());
    }
  }

  /**
   * Initializes the GUI
   */
  private void setupGui() {
    tabPan = new JTabbedPane();

    setupPersonGui();
    setupGroupGui();
    setupFriendGui();

    tabPan.addTab("People", personPan);
    tabPan.addTab("Groups", groupPan);
    tabPan.addTab("Friends", friendsPan);

    this.setSize(800, 600);
    this.add(tabPan);

  }
  
  /**
   * Initializes Person Tab of GUI
   */
  private void setupPersonGui() {
    personPan = new JPanel(new BorderLayout(5, 5));

    // create person stuff
    personCreatePan = new JPanel(new GridLayout(0, 2));
    fnameField = new JTextField(10);
    lnameField = new JTextField(10);
    unameField = new JTextField(10);
    genderComb = new JComboBox(Gender.values());
    relationComb = new JComboBox(Relationship.values());
    ageSpin = new JSpinner(new SpinnerNumberModel(18, 0, 120, 1));
    perAddBtn = new JButton("Add");
    perAddBtn.addActionListener(this);
    perClearBtn = new JButton("Clear");
    perClearBtn.addActionListener(this);

    // add stuff
    personCreatePan.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    personCreatePan.add(new JLabel("First Name:"));
    personCreatePan.add(fnameField);

    personCreatePan.add(new JLabel("Last Name:"));
    personCreatePan.add(lnameField);

    personCreatePan.add(new JLabel("User Name:"));
    personCreatePan.add(unameField);

    personCreatePan.add(new JLabel("Gender:"));
    personCreatePan.add(genderComb);

    personCreatePan.add(new JLabel("Relationship:"));
    personCreatePan.add(relationComb);

    personCreatePan.add(new JLabel("Age:"));
    personCreatePan.add(ageSpin);

    personCreatePan.add(perAddBtn);
    personCreatePan.add(perClearBtn);

    // view person stuff
    personViewPan = new JPanel(new GridBagLayout());
    final GridBagConstraints cons = new GridBagConstraints();

    personViewPan.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

    personListModel = new DefaultListModel();

    personList = new JList(personListModel);
    personList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    personList.setSelectedIndex(0);
    personList.addListSelectionListener(this);
    // personList.setVisibleRowCount(15);

    final JScrollPane personListScroll = new JScrollPane(personList);

    perNameOut = new JLabel("No Student Selected");
    perInfoOut = new JLabel("I'm here for positioning");

    cons.ipadx = 10;
    cons.ipady = 10;
    cons.weightx = 1;
    cons.weighty = 1;
    cons.fill = GridBagConstraints.BOTH;
    cons.gridx = 0;
    cons.gridy = 0;
    cons.gridwidth = 1;
    cons.gridheight = 2;
    personViewPan.add(personListScroll, cons);

    cons.gridx = 1;

    personViewPan.add(new JSeparator(SwingConstants.VERTICAL), cons);

    cons.weightx = 5;
    cons.gridx = 2;
    cons.gridheight = 1;
    personViewPan.add(perNameOut, cons);

    cons.gridy = 1;
    personViewPan.add(perInfoOut, cons);

    // personTab
    personPan.add(personCreatePan, BorderLayout.NORTH);
    personPan.add(personViewPan, BorderLayout.CENTER);
  }

  /**
   * Initializes Group Tab of GUI
   */
  public void setupGroupGui() {
    // group stuff
    groupPan = new JPanel(new BorderLayout(5, 5));

    // add group stuff
    groupCreatePan = new JPanel(new GridBagLayout());

    grpNameField = new JTextField(10);
    grpDescArea = new JTextArea(3, 10);
    final JScrollPane grpDescScroll = new JScrollPane(grpDescArea);
    grpAddBtn = new JButton("Add");
    grpClearBtn = new JButton("Clear");

    grpAddBtn.addActionListener(this);
    grpClearBtn.addActionListener(this);

    GridBagConstraints cons = new GridBagConstraints();

    cons.weighty = 1;
    cons.weightx = 1;
    cons.gridx = 0;
    cons.gridy = 0;
    cons.gridwidth = 1;
    cons.gridheight = 1;
    cons.ipadx = 10;
    cons.ipady = 10;
    cons.fill = GridBagConstraints.BOTH;

    groupCreatePan.add(new JLabel("Group Name:"), cons);

    cons.gridx = 1;
    groupCreatePan.add(grpNameField, cons);

    cons.gridx = 0;
    cons.gridy = 1;
    groupCreatePan.add(new JLabel("Group Description:"), cons);

    cons.gridx = 1;
    cons.gridheight = 6;
    groupCreatePan.add(grpDescScroll, cons);

    cons.gridx = 0;
    cons.gridy = 7;
    cons.insets = new Insets(0, 0, 50, 0);
    groupCreatePan.add(grpAddBtn, cons);

    cons.gridx = 1;

    groupCreatePan.add(grpClearBtn, cons);

    groupViewPan = new JPanel(new GridBagLayout());
    groupViewPan.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

    groupListModel = new DefaultListModel();
    groupListModel.addElement("testtesttest");

    groupList = new JList(groupListModel);
    groupList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    groupList.setSelectedIndex(0);
    groupList.addListSelectionListener(this);

    final JScrollPane groupListScroll = new JScrollPane(groupList);

    grpNameOut = new JLabel("No Group Selected");
    grpDescOut = new JTextArea("For show only", 1, 10);
    grpDescOut.setEditable(false);
    grpDescOut.setBackground(groupViewPan.getBackground());// this is magic
    grpDescOut.setFont(grpNameOut.getFont());// more magic
    cons = new GridBagConstraints();

    cons.ipadx = 10;
    cons.ipady = 10;
    cons.weightx = 1;
    cons.weighty = 1;
    cons.fill = GridBagConstraints.BOTH;
    cons.gridx = 0;
    cons.gridy = 0;
    cons.gridwidth = 1;
    cons.gridheight = 2;

    groupViewPan.add(groupListScroll, cons);

    cons.gridx = 1;

    groupViewPan.add(new JSeparator(SwingConstants.VERTICAL), cons);
    cons.weightx = 5;
    cons.gridx = 2;
    cons.gridheight = 1;
    groupViewPan.add(grpNameOut, cons);

    cons.gridy = 1;
    groupViewPan.add(grpDescOut, cons);

    groupPan.add(groupCreatePan, BorderLayout.NORTH);
    groupPan.add(groupViewPan, BorderLayout.CENTER);
  }
  
  /**
   * Initializes Friend Tab of GUI
   */
  public void setupFriendGui() {
	//Create layoout
    friendsPan = new JPanel(new BorderLayout(5, 5));
    friendCreatePan = new JPanel(new GridBagLayout());
    
    friend1ListModel = new DefaultListModel();
    friend1ListModel = personListModel;

    friend1 = new JList(friend1ListModel);
    friend1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    friend1.setSelectedIndex(0);
    friend1.addListSelectionListener(this);

    final JScrollPane friend1ListScroll = new JScrollPane(friend1);

    friend2ListModel = new DefaultListModel();
    //friend2ListModel.addElement("testarate'sFriend");
    friend2ListModel = personListModel;
    friend2 = new JList(friend2ListModel);
    friend2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    friend2.setSelectedIndex(0);
    friend2.addListSelectionListener(this);

    final JScrollPane friend2ListScroll = new JScrollPane(friend2);

    frdAddBtn = new JButton("Add");
    frdAddBtn.addActionListener(this);
    frdClearBtn = new JButton("Close");
    frdClearBtn.addActionListener(this);
    final GridBagConstraints cons = new GridBagConstraints();
    cons.ipadx = 10;
    cons.ipady = 10;
    cons.weightx = 1;
    cons.weighty = 1;
    cons.fill = GridBagConstraints.BOTH;
    cons.gridx = 0;
    cons.gridy = 0;
    cons.gridwidth = 1;
    cons.gridheight = 1;

    friendCreatePan.add(new JLabel("Person 1:"), cons);

    cons.gridx = 1;
    friendCreatePan.add(new JLabel("Person 2:"), cons);

    cons.gridx = 0;
    cons.gridy = 1;
    cons.gridheight = 2;
    friendCreatePan.add(friend1ListScroll, cons);

    cons.gridx = 1;
    friendCreatePan.add(friend2ListScroll, cons);

    cons.gridx = 0;
    cons.gridy = 3;
    cons.gridheight = 1;
    friendCreatePan.add(frdAddBtn, cons);

    cons.gridx = 1;
    friendCreatePan.add(frdClearBtn, cons);

    friendsViewPan = new JPanel(new GridBagLayout());

    friend1ViewListModel = new DefaultListModel();
    friend1ViewListModel.addElement("testie");

    friend1ViewList = new JList(friend1ViewListModel);
    friend1ViewList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    friend1ViewList.setSelectedIndex(0);
    friend1ViewList.addListSelectionListener(this);

    final JScrollPane friend1ViewListScroll = new JScrollPane(friend1ViewList);

    /*friendOf1ViewListModel = new DefaultListModel();
    friendOf1ViewListModel.addElement("testie's friend");

    friendOf1ViewList = new JList(friendOf1ViewListModel);

    final JScrollPane friendOf1ViewListScroll = new JScrollPane(friendOf1ViewList);
	*/
    cons.ipadx = 10;
    cons.ipady = 10;
    cons.weightx = 1;
    cons.weighty = 1;
    cons.fill = GridBagConstraints.BOTH;
    cons.gridx = 0;
    cons.gridy = 0;
    cons.gridwidth = 1;
    cons.gridheight = 1;

    //friendsViewPan.add(new JLabel("Select Person:"), cons);

    //cons.gridx = 1;
    friendsViewPan.add(new JLabel("Friends of Friend 1:"), cons);

    cons.gridx = 0;
    cons.gridy = 1;

    friendsViewPan.add(friend1ViewListScroll, cons);

    cons.gridx = 1;

    //friendsViewPan.add(friendOf1ViewListScroll, cons);

    friendsPan.add(friendCreatePan, BorderLayout.NORTH);
    friendsPan.add(friendsViewPan, BorderLayout.CENTER);
  }
  
  /**
   * Main
   * @param args
   */
  public static void main(final String[] args) {
    final MainGui gui = new MainGui();
    gui.setVisible(true);
    gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }
  
  /**
   * Returns a person given a username
   * @param uname
   * @return Person
   */
  private Person getPersonByUsername(String uname) {
    for (Person p : people) {
      if (p.getUsername().equals(uname)) {
        return p;
      }
    }
    return null;
  }

  /**
   * Returns the group given the name
   * @param name
   * @return Group
   */
  private Group getGroupByName(String name) {
    for (Group g : groups) {
      if (g.getName() == name) {
        return g;
      }
    }
    return null;
  }
  
  /**
   * Handles user clicking the ListBoxes. Does different things depending on the tab
   */
  @Override
  public void valueChanged(final ListSelectionEvent e) {
    Object source = e.getSource();

    if (source.equals(personList)) {
      Person selected = getPersonByUsername((String) personList.getSelectedValue());

      if (selected != null) {

        perNameOut.setText(selected.getUsername() + ": " + selected.getFirstName() + " "
                + selected.getLastName());
        perInfoOut.setText("Age:" + selected.getAge() + " Gender:"
                + selected.getGender().toString() + " Relationship Status:"
                + selected.getRelationship().toString());
      }
    }
    if (source.equals(groupList)) {
      Group selected = getGroupByName((String) groupList.getSelectedValue());

      if (selected != null) {
        grpNameOut.setText(selected.getName());

        grpDescOut.setText(selected.getDescription());
      }
    }
    if (source.equals(friend1)){
    	Person selected = getPersonByUsername((String) friend1.getSelectedValue());
    	
    	if (selected != null) {
    		//DO SOMETHING HERE!!
    		//Get selected's friends
    		ArrayList<Person> pfriends = selected.getFriends();
    		
    		//friend1ViewListModel = new DefaultListModel();
    		
    	    friend1ViewListModel.clear();

    	    for (Person p : pfriends) {
    	    	friend1ViewListModel.addElement(p.getUsername());
    	    }
    	    
    	    friend1ViewList = new JList(friend1ViewListModel);
    	}
    }
    
  }
  
  /**
   * Handles button clicking.
   */
  @Override
  public void actionPerformed(final ActionEvent e) {
    Object source = e.getSource();

    if (source.equals(perAddBtn)) {
      addPerson();
    }
    if (source.equals(perClearBtn)) {
      clearPerson();
    }
    if (source.equals(grpAddBtn)) {
      addGroup();
    }
    if (source.equals(grpClearBtn)) {
      clearGroup();
    }
    if (source.equals(frdAddBtn)) {
    	// add friendship
    	//System.out.println("New Friendship! Handle later");
    	if(!friend1.isSelectionEmpty() && !friend2.isSelectionEmpty()){
    		String f1Username = (String) friend1.getSelectedValue();
    		String f2Username = (String) friend2.getSelectedValue();
    		Person f1 = getPersonByUsername(f1Username);
    		Person f2 = getPersonByUsername(f2Username);
    		
    		if(!f1.equals(null) && !f2.equals(null)){
    			if(!f1.isFriendsWith(f2) && !f2.isFriendsWith(f1)){
    				db.addFriendship(f1,f2);
    			}
    		}
    		ArrayList<Person> f1Friends = f1.getFriends();
    		friend1ViewListModel.clear();

    	    for (Person p : f1Friends) {
    	    	friend1ViewListModel.addElement(p.getUsername());
    	    }
    	    
    	    friend1ViewList = new JList(friend1ViewListModel);
    	}
    }
    if (source.equals(frdClearBtn)){
    	// remove a friendship
    	System.out.println("Remove a frienship! Handle later");
    	if(!friend1.isSelectionEmpty() && !friend2.isSelectionEmpty()){
    		String f1Username = (String) friend1.getSelectedValue();
    		String f2Username = (String) friend2.getSelectedValue();
    		Person f1 = getPersonByUsername(f1Username);
    		Person f2 = getPersonByUsername(f2Username);
    		
    		if(!f1.equals(null) && !f2.equals(null)){
    			if(f1.isFriendsWith(f2) && f2.isFriendsWith(f1)){
    				db.removeFriendship(f1,f2);
    			}
    		}
    		ArrayList<Person> f1Friends = f1.getFriends();
    		friend1ViewListModel.clear();

    	    for (Person p : f1Friends) {
    	    	friend1ViewListModel.addElement(p.getUsername());
    	    }
    	    
    	    friend1ViewList = new JList(friend1ViewListModel);
    	}
    	
    }
  }
  
  /**
   * Adds a person to the db and people ArrayList
   */
  private void addPerson() {

    String fname = fnameField.getText();
    String lname = lnameField.getText();
    String uname = unameField.getText();

    Gender gender = (Gender) genderComb.getSelectedItem();
    Relationship relation = (Relationship) relationComb.getSelectedItem();

    SpinnerNumberModel model = (SpinnerNumberModel) ageSpin.getModel();
    int age = model.getNumber().intValue();

    try {
      Person toAdd = new PersonBuilder().firstName(fname).lastName(lname).username(uname)
              .gender(gender).relationship(relation).age(age).build();
      toAdd.update();
    }
    catch (IllegalArgumentException e) {
      System.out.println("caught a bad name or age");
      JOptionPane.showMessageDialog(this, e.getMessage(), "Need a better name",
              JOptionPane.INFORMATION_MESSAGE, null);
    }

    loadPersonInfo();
  }
  
  /**
   * Removes a person from the DB and People array list
   */
  private void clearPerson() {
    fnameField.setText("");
    lnameField.setText("");
    unameField.setText("");

    genderComb.setSelectedIndex(0);
    relationComb.setSelectedIndex(0);

    SpinnerNumberModel model = (SpinnerNumberModel) ageSpin.getModel();
    model.setValue(18);
  }
  
  /**
   * Adds a group
   */
  private void addGroup() {
    String gname = grpNameField.getText();
    String gdesc = grpDescArea.getText();

    try {
      Group g = new Group(gname, gdesc);

      g.update();
    }
    catch (IllegalArgumentException e) {
      System.out.println("caught bad group name or desc");

      JOptionPane.showMessageDialog(this, "Bad group name.", "Need a better name",
              JOptionPane.INFORMATION_MESSAGE, null);
    }

    loadGroupInfo();
  }

  /**
   * Clears a group
   */
  private void clearGroup() {
    grpNameField.setText("");
    grpDescArea.setText("");
  }
}
