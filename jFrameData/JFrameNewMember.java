package jFrameData;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jFrameLogin.Success;
import jFrameLogin.errorPopup;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;
import java.awt.event.ActionEvent;

public class JFrameNewMember extends JFrame {

	private JPanel contentPane;
	private JTextField txtFName;
	private JTextField txtLName;
	private JTextField txtDOB;
	private JTextField txtOptIn;
	private JTextField txtEmail;
	private Stack<String> stack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameNewMember frame = new JFrameNewMember();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void executeQuery() throws SQLException {
		Connection conn;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/term", "root", "!Fall2022");
		Statement stmt = conn.createStatement();
		String addEmp = "INSERT INTO inventory "
				+ "VALUES("
				+ stack.pop() + ", "
				+ stack.pop() + ", "
				+ stack.pop() + ", "
				+ stack.pop() + ")";
		stmt.execute(addEmp);
	}
	
	public int connect() {
		int errorCode = -1;
    	Connection conn;
    	{
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/term", "root", "!Fall2022");
				Statement stmt = conn.createStatement();
				errorCode = 1;
				int newMID;
				String getNewMID = "Select MAX(MID) "
						+ "from members";
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(getNewMID);
				int oldMID = Integer.parseInt(rs.getString("PID"));
				newMID = oldMID++;
				return newMID;
			}
			catch(Exception e) {
				//error code 0 = connection error
				//error code 1 = SQLException
				return errorCode;
			}
    	}
	}
	/**
	 * Create the frame.
	 */
	public JFrameNewMember() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddNewMember = new JLabel("Add New Member");
		lblAddNewMember.setBounds(141, 12, 164, 15);
		contentPane.add(lblAddNewMember);
		
		txtFName = new JTextField();
		txtFName.setText("Enter member first name here");
		txtFName.setColumns(10);
		txtFName.setBounds(76, 39, 296, 19);
		contentPane.add(txtFName);
		
		txtLName = new JTextField();
		txtLName.setText("Enter member last name here");
		txtLName.setColumns(10);
		txtLName.setBounds(76, 70, 296, 19);
		contentPane.add(txtLName);
		
		txtDOB = new JTextField();
		txtDOB.setText("Enter member DOB name here (DD/MM/YYYY)");
		txtDOB.setColumns(10);
		txtDOB.setBounds(76, 101, 296, 19);
		contentPane.add(txtDOB);
		
		txtOptIn = new JTextField();
		txtOptIn.setText("Enter member opt-in status here (0 or 1)");
		txtOptIn.setColumns(10);
		txtOptIn.setBounds(76, 132, 296, 19);
		contentPane.add(txtOptIn);
		
		txtEmail = new JTextField();
		txtEmail.setText("Enter member email here (NULL if opted out)");
		txtEmail.setColumns(10);
		txtEmail.setBounds(76, 163, 296, 19);
		contentPane.add(txtEmail);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fName = txtFName.getText();
				String lName = txtLName.getText();
				String DOB = txtDOB.getText();
				String optIn = txtOptIn.getText();
				String email = txtEmail.getText();
				String MID = Integer.toString(connect());
				stack.push(email);
				stack.push(optIn);
				stack.push(DOB);
				stack.push(lName);
				stack.push(fName);
				try {
					executeQuery();
				} catch (SQLException e1) {
					errorPopup erp = new errorPopup();
					erp.dispatchEvent(new WindowEvent(erp, WindowEvent.WINDOW_CLOSED));
					erp.setAlwaysOnTop(true);
					JFrameNewMember.this.dispatchEvent(new WindowEvent(JFrameNewMember.this, WindowEvent.WINDOW_CLOSED));
					
					JFrameData jfd = new JFrameData();
					jfd.dispatchEvent(new WindowEvent(jfd, WindowEvent.WINDOW_OPENED));
					jfd.setAlwaysOnTop(true);
				}
				JFrameNewMember.this.dispatchEvent(new WindowEvent(JFrameNewMember.this, WindowEvent.WINDOW_CLOSED));
				
				JFrameData jfd = new JFrameData();
				jfd.dispatchEvent(new WindowEvent(jfd, WindowEvent.WINDOW_OPENED));
				jfd.setAlwaysOnTop(true);
				
				Success s = new Success();
				s.dispatchEvent(new WindowEvent(s, WindowEvent.WINDOW_OPENED));
				s.setAlwaysOnTop(true);
			}
		});
		btnConfirm.setBounds(153, 196, 117, 25);
		contentPane.add(btnConfirm);
	}

}
