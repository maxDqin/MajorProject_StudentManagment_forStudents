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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Update extends JFrame {

	private JPanel contentPane;
	private JTextField txtStudentId;
	private JTextField txtSubjectCode;
	private JLabel lblResult;
	private JTextField txtResult;
	private JButton btnUpdate;
	private JLabel lblRetrieveupdateenrolment;
	private JTextArea txtDetails;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Update frame = new Update();
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
	public Update() {
		setTitle("Update");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setBounds(17, 63, 71, 14);
		contentPane.add(lblStudentId);

		txtStudentId = new JTextField();
		txtStudentId.setBounds(87, 60, 86, 20);
		contentPane.add(txtStudentId);
		txtStudentId.setColumns(10);

		JLabel lblSubjectCode = new JLabel("Subject Code");
		lblSubjectCode.setBounds(196, 63, 86, 14);
		contentPane.add(lblSubjectCode);

		txtSubjectCode = new JTextField();
		txtSubjectCode.setBounds(281, 60, 86, 20);
		contentPane.add(txtSubjectCode);
		txtSubjectCode.setColumns(10);

		lblResult = new JLabel("Result");
		lblResult.setBounds(392, 63, 46, 14);
		contentPane.add(lblResult);

		txtResult = new JTextField();
		txtResult.setBounds(434, 60, 86, 20);
		contentPane.add(txtResult);
		txtResult.setColumns(10);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String sid = txtStudentId.getText();
					String cid = txtSubjectCode.getText();
					int res = 0;
					try {
						res = Integer.parseInt(txtResult.getText());
					} catch (NumberFormatException e0) {

					}
					Class.forName("org.sqlite.JDBC");
					Connection con = DriverManager.getConnection("jdbc:sqlite:./enrolmentSystem.db");
					PreparedStatement stmt = con
							.prepareStatement("update enrolment set result=? where sid=? and sub_code=? ");
					stmt.setInt(1, res);
					stmt.setString(2, sid);
					stmt.setString(3, cid);
					int x = stmt.executeUpdate();
					if (x == 0)
						txtDetails.setText("failed to update.");
					else
						txtDetails.setText("update sccessfully.");
					con.close();
				} catch (SQLException e1) {

				} catch (ClassNotFoundException e2) {
				}
			}
		});
		btnUpdate.setBounds(434, 266, 89, 23);
		contentPane.add(btnUpdate);

		lblRetrieveupdateenrolment = new JLabel("Update/Withdraw");
		lblRetrieveupdateenrolment.setBounds(225, 21, 129, 14);
		contentPane.add(lblRetrieveupdateenrolment);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(17, 110, 522, 123);
		contentPane.add(scrollPane);

		txtDetails = new JTextArea();
		scrollPane.setViewportView(txtDetails);
		txtDetails.setEditable(false);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// This function aims to withdraw a subject enrolled by a student.
				// please note that only student id, subject code and current result are correctly input,
				// can this subject be withdrawn
				// after withdrawn, delete the related record on database.
				try {
					String sid = txtStudentId.getText();
					String cid = txtSubjectCode.getText();
					int res = 0;
					try {
						res = Integer.parseInt(txtResult.getText());
					} catch (NumberFormatException e0) {

					}
					Class.forName("org.sqlite.JDBC");
					Connection con = DriverManager.getConnection("jdbc:sqlite:./enrolmentSystem.db");
					PreparedStatement stmt = con
							.prepareStatement("delete from enrolment where result=? and sid=? and sub_code=? ");
					stmt.setInt(1, res);
					stmt.setString(2, sid);
					stmt.setString(3, cid);
					int x = stmt.executeUpdate();
					if (x == 1)
						txtDetails.setText("withdraw sccessfully.");
					else
						txtDetails.setText("failed to withdraw.");
					con.close();
				} catch (SQLException e1) {

				} catch (ClassNotFoundException e2) {
				}
			}
		});
		btnWithdraw.setBounds(294, 263, 117, 29);
		contentPane.add(btnWithdraw);
	}
}
