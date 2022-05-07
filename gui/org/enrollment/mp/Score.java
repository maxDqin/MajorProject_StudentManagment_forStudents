package org.enrollment.mp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JScrollBar;

public class Score extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Score frame = new Score();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Score() {
		setTitle("Score");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 431, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblScore = new JLabel("Score Statistics");
		lblScore.setBounds(172, 21, 107, 16);
		contentPane.add(lblScore);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setBounds(60, 73, 61, 16);
		contentPane.add(lblSubject);
		
		textField = new JTextField();
		textField.setBounds(172, 68, 95, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblOption = new JLabel("Option");
		lblOption.setBounds(60, 163, 61, 16);
		contentPane.add(lblOption);
		
		JList list = new JList();
		list.setBounds(198, 129, 1, 1);
		contentPane.add(list);
		
		JList list_1 = new JList();
		list_1.setBounds(210, 142, 1, 1);
		contentPane.add(list_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(172, 163, 124, 23);
		comboBox.addItem("Max");
		comboBox.addItem("Min");
		comboBox.addItem("Average");
		comboBox.addItem("HD");
		comboBox.addItem("D");
		comboBox.addItem("CR");
		comboBox.addItem("P");
		comboBox.addItem("F");
		contentPane.add(comboBox);
		
		JLabel lblSemester = new JLabel("Semester");
		lblSemester.setBounds(60, 112, 61, 16);
		contentPane.add(lblSemester);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(182, 106, 61, 16);
		comboBox_1.addItem("1");
		comboBox_1.addItem("2");
		contentPane.add(comboBox_1);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(60, 135, 61, 16);
		contentPane.add(lblYear);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(172, 129, 95, 27);
		comboBox_2.addItem("2020");
		comboBox_2.addItem("2021");
		contentPane.add(comboBox_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 197, 377, 115);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton = new JButton("Show");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					String sub_code = textField.getText();
					int semester = Integer.parseInt(comboBox_1.getSelectedItem().toString());
					int year = Integer.parseInt(comboBox_2.getSelectedItem().toString());;
					String option = comboBox.getSelectedItem().toString();
					Class.forName("org.sqlite.JDBC");
					Connection con = DriverManager.getConnection("jdbc:sqlite:./enrolmentSystem.db");
					PreparedStatement stmt;
					ResultSet res ;
					String result = "";
					switch(option) {
					case "HD":
						stmt = con.prepareStatement("select * from enrolment "
								+ "where result>=? and result<=? and year=? and semester=? and sub_code=?");
						stmt.setInt(1, 80);
						stmt.setInt(2, 100);
						stmt.setInt(3, year);
						stmt.setInt(4, semester);
						stmt.setString(5, sub_code);
						res = stmt.executeQuery();
						while(res.next()) {
							result += res.getString(1) + " " + res.getString(2) + " " + res.getString(3) + " " + res.getString(5)
							+ " " + res.getInt(8) +"\n";
						}
						break;
//					case "D":
//						// fill in
//						break;
//					case "CR":
//						// fill in
//						break;
//					case "P":
//						// fill in
//						break;
//					case "F":
//						// fill in
//						break;
//					case "Average":
//						stmt = con.prepareStatement("select avg(result) from enrolment where year=? and semester=? and sub_code=? ");
//						// fill in
//						result = res.getString(1);
//						break;
//					case "Max":
//						// fill in
//					default:
//						// fill in
					}
					textArea.setText(result);
				} catch (Exception e1) {

				}
			}
		});
		btnNewButton.setBounds(210, 324, 117, 29);
		contentPane.add(btnNewButton);
	}
}
