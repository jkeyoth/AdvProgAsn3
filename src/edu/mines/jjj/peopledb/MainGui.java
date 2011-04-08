package edu.mines.jjj.peopledb;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class MainGui extends JFrame
{

	private JTabbedPane tabPan;

	// student panel stuff
	private JPanel studentPan;
	// create student fields
	private JPanel studentCreatePan;
	private JTextField fnameField;
	private JTextField lnameField;
	private JTextField unameField;
	private JComboBox genderComb;
	private JComboBox relationComb;
	private JSpinner ageSpin;
	// view student fields;
	private JPanel studentViewPan;
	private JLabel fnameOutLbl, lnameOutLbl, unameOutLbl, genderOutLbl, relationOutLbl, ageOutLbl,
		groupsOutLbl, friendsOutLbl;

	// group panel stuff
	private JPanel groupPan;

	public MainGui()
	{
		setupGui();

	}
	private void setupGui()
	{
		tabPan = new JTabbedPane();

		studentPan = new JPanel();
		groupPan = new JPanel();

		tabPan.addTab("Student", studentPan);
		tabPan.addTab("Groups", groupPan);

		// create student stuff
		studentCreatePan = new JPanel();
		fnameField = new JTextField();
		lnameField = new JTextField();
		unameField = new JTextField();
		genderComb = new JComboBox(Gender.values());
		relationComb = new JComboBox(Relationship.values());
		ageSpin = new JSpinner(new SpinnerNumberModel(18, 0, 120, 1));

		// view student stuff
		studentViewPan = new JPanel();
		fnameOutLbl = new JLabel("");
		lnameOutLbl = new JLabel("");

	}
	public static void main(String[] args)
	{
		MainGui gui = new MainGui();
		gui.setVisible(true);

	}

}
