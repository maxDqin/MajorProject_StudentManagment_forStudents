package org.enrollment.mp;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InsertEnrolment extends JFrame {

	private JPanel contentPane;
	private JTextField txtStudentId;
	private JTextField txtFirstName;
	private JTextField txtSurname;
	private JTextField txtYear;
	private JTextField textMessage;
	protected Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					InsertEnrolment frame = new InsertEnrolment();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public InsertEnrolment(){
		setTitle("Insert");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setBounds(27, 44, 79, 14);
		contentPane.add(lblStudentId);

		JLabel lblFirstName = new JLabel("FIrst Name");
		lblFirstName.setBounds(27, 83, 79, 14);
		contentPane.add(lblFirstName);

		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(27, 124, 62, 14);
		contentPane.add(lblSurname);

		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(27, 162, 46, 14);
		contentPane.add(lblYear);

		txtStudentId = new JTextField();
		txtStudentId.setBounds(101, 41, 86, 20);
		contentPane.add(txtStudentId);
		txtStudentId.setColumns(10);

		txtFirstName = new JTextField();
		txtFirstName.setBounds(101, 80, 86, 20);
		contentPane.add(txtFirstName);
		txtFirstName.setColumns(10);

		txtSurname = new JTextField();
		txtSurname.setBounds(101, 121, 86, 20);
		contentPane.add(txtSurname);
		txtSurname.setColumns(10);

		txtYear = new JTextField();
		txtYear.setBounds(101, 159, 86, 20);
		contentPane.add(txtYear);
		txtYear.setColumns(10);

		JLabel lblRequestStatus = new JLabel("Request Status");
		lblRequestStatus.setBounds(270, 83, 95, 27);
		contentPane.add(lblRequestStatus);

		textMessage = new JTextField();
		textMessage.setEditable(false);
		textMessage.setBounds(219, 130, 205, 78);
		contentPane.add(textMessage);
		textMessage.setColumns(10);
		
		
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Subject sub = new Subject();
				String[] array_code = sub.getSubjectCode();
				String[] array_name = sub.getSubjectName();
				int[] array_semester = sub.getSemester();
				
				String sid = txtStudentId.getText();
				String year = txtYear.getText();
				String surname = txtSurname.getText();
				String firstName = txtFirstName.getText();
				int status = 0;
				for(int i=0;i<array_code.length;i++) {
					String sub_code = array_code[i];
					String sub_name = array_name[i];
					int semester = array_semester[i];
					int result = 888;
					try {
						Class.forName("org.sqlite.JDBC");
						Connection con =DriverManager.getConnection("jdbc:sqlite:./enrolmentSystem.db");
						PreparedStatement stmt=con.prepareStatement("insert into enrolment values(?,?,?,?,?,?,?,?)");
						stmt.setString(1, sid);
						stmt.setString(2, firstName);
						stmt.setString(3, surname);
						stmt.setString(4, sub_code);
						stmt.setString(5, sub_name);
						stmt.setString(6, year);
						stmt.setInt(7, semester);
						stmt.setInt(8, result);
						status = stmt.executeUpdate();
					}catch(SQLException e1) {
						System.out.println(e1.getMessage());
					} catch (ClassNotFoundException e2) {
						e2.printStackTrace();
					}
				}
				
				if(status==0) {
					textMessage.setText("failed");
				}else {
					textMessage.setText("inserted successfully");
				}
			}
		});
		
		btnInsert.setBounds(81, 200, 89, 23);
		contentPane.add(btnInsert);

		JLabel lblNewLabel = new JLabel("Insert Enrolment Menu");
		lblNewLabel.setBounds(148, 13, 158, 16);
		contentPane.add(lblNewLabel);
	}
}