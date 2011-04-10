package edu.mines.jjj.peopledb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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

public class MainGui extends JFrame implements ListSelectionListener, ActionListener
{
	
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
	
	public MainGui()
	{
		setupGui();
		
	}
	
	private void setupGui()
	{
		tabPan = new JTabbedPane();
		
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
		GridBagConstraints cons = new GridBagConstraints();
		
		personViewPan.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		
		personListModel = new DefaultListModel();
		personListModel.addElement("testering");
		
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
		
		cons = new GridBagConstraints();
		
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
		
		friendsPan = new JPanel(new BorderLayout(5, 5));
		
		friendCreatePan = new JPanel(new GridBagLayout());
		
		friend1ListModel = new DefaultListModel();
		friend1ListModel.addElement("testarate");
		
		friend1 = new JList(friend1ListModel);
		friend1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		friend1.setSelectedIndex(0);
		friend1.addListSelectionListener(this);
		
		JScrollPane friend1ListScroll = new JScrollPane(friend1);
		
		friend2ListModel = new DefaultListModel();
		friend2ListModel.addElement("testarate'sFriend");
		
		friend2 = new JList(friend2ListModel);
		friend2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		friend2.setSelectedIndex(0);
		friend2.addListSelectionListener(this);
		
		JScrollPane friend2ListScroll = new JScrollPane(friend2);
		
		frdAddBtn = new JButton("Add");
		frdAddBtn.addActionListener(this);
		frdClearBtn = new JButton("Close");
		frdClearBtn.addActionListener(this);
		
		cons.ipadx = 10;
		cons.ipady = 10;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.fill = GridBagConstraints.BOTH;
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		
		friendCreatePan.add(new JLabel("Friend 1:"), cons);
		
		cons.gridx = 1;
		friendCreatePan.add(new JLabel("Friend 2:"), cons);
		
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
		
		JScrollPane friend1ViewListScroll = new JScrollPane(friend1ViewList);
		
		friendOf1ViewListModel = new DefaultListModel();
		friendOf1ViewListModel.addElement("testie's friend");
		
		friendOf1ViewList = new JList(friendOf1ViewListModel);
		
		JScrollPane friendOf1ViewListScroll = new JScrollPane(friendOf1ViewList);
		
		cons.ipadx = 10;
		cons.ipady = 10;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.fill = GridBagConstraints.BOTH;
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		
		friendsViewPan.add(new JLabel("Select Person:"), cons);
		
		cons.gridx = 1;
		friendsViewPan.add(new JLabel("Friends"), cons);
		
		cons.gridx = 0;
		cons.gridy = 1;
		
		friendsViewPan.add(friend1ViewListScroll, cons);
		
		cons.gridx = 1;
		
		friendsViewPan.add(friendOf1ViewListScroll, cons);
		
		friendsPan.add(friendCreatePan, BorderLayout.NORTH);
		friendsPan.add(friendsViewPan, BorderLayout.CENTER);
		
		tabPan.addTab("People", personPan);
		tabPan.addTab("Groups", groupPan);
		tabPan.addTab("Friends", friendsPan);
		
		this.setSize(800, 600);
		this.add(tabPan);
		
	}
	
	public static void main(final String[] args)
	{
		final MainGui gui = new MainGui();
		gui.setVisible(true);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	@Override
	public void valueChanged(final ListSelectionEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void actionPerformed(final ActionEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
}
