package org.enrollment.mp;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMenu extends JFrame {

	
private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		setTitle("EnrolMent Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 266, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InsertEnrolment insertFrame = new InsertEnrolment();
				insertFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				insertFrame.setVisible(true);
			}
		});
		btnInsert.setBounds(69, 89, 123, 49);
		contentPane.add(btnInsert);
		
		JLabel lblEnrolmentMainMenu = new JLabel("Enrolment Main Menu");
		lblEnrolmentMainMenu.setBounds(59, 32, 151, 35);
		contentPane.add(lblEnrolmentMainMenu);
		
		
	}
}
