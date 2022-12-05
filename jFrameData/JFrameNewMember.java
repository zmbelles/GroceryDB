package jFrameData;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class JFrameNewMember extends JFrame {

	private JPanel contentPane;
	private JTextField txtFName;
	private JTextField txtLName;
	private JTextField txtDOB;
	private JTextField txtOptIn;
	private JTextField txtEmail;
	private Stack<String> stack = new Stack<String>();

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
	private String getEID() throws SQLException{
		String FName = txtFName.getText();
		String LName = txtLName.getText();
		String query = "select EID "
				+ "from employee "
				+ "where FName LIKE '"
				+ FName 
				+ "' && LName LIKE '"
				+ LName
				+ "'";
		Connection conn;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/term", "root", "!Fall2022");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		rs.next();
		try {
			String EID = rs.getString("EID");
			return EID;
		}
		catch(SQLException e) {
			if(e.toString().contains("Illegal operation on empty result set")) return "NULL";
			else return "err";
		}
	}
	private boolean executeQuery(Connection conn, Statement stmt){
		
		String addMem = "INSERT INTO members "
				+ "VALUES("
				+ stack.pop() + ", '"
				+ stack.pop() + "', '"
				+ stack.pop() + "', '"
				+ stack.pop() + "', "
				+ stack.pop() + ", '"
				+ stack.pop() + "', "
				+ stack.pop() + ")";
		try {
			stmt.execute(addMem);
			return false;
		} catch (SQLException e) {
			return true;
		}
	}
	
	private int connect() {
		int errorCode = -1;
    	Connection conn;
    	{
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/term", "root", "!Fall2022");
				Statement stmt = conn.createStatement();
				int newMID;
				String getOldMID = "select m1.MID "
						+ "from members m1 "
						+ "where m1.MID = (select max(m2.MID) from members m2)";
				ResultSet rs = stmt.executeQuery(getOldMID);
				rs.next();
				int oldMID = Integer.parseInt(rs.getString("MID"));
				newMID = oldMID;
				newMID++;
				stack.push(Integer.toString(newMID));
				boolean error = executeQuery(conn, stmt); 
				if(error) errorCode = 1;
				else errorCode = 0;
				return errorCode;
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
		lblAddNewMember.setBounds(173, 11, 164, 15);
		contentPane.add(lblAddNewMember);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameNewMember.this.setVisible(false);
				JFrameNewMember.this.setEnabled(false);
				
				//open data window
				JFrameData jfd = new JFrameData();
				jfd.setVisible(true);
				jfd.setEnabled(true);
				jfd.setAlwaysOnTop(true);
			}
		});
		btnBack.setBounds(100, 225, 117, 25);
		contentPane.add(btnBack);
		
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
				
				try {
					String EID = getEID();
					//if there's an error, prompt error popup
					if(EID!="err") {
						
						//check if there's no EID found
						if(EID=="NULL") {
							stack.push("null");
						}
						else {
							stack.push(EID);
						}
						//this will need to run regardless
						stack.push(email);
						stack.push(optIn);
						stack.push(DOB);
						stack.push(lName);
						stack.push(fName);
						int status = connect();
						if(status==0) {
							//close this window
							JFrameNewMember.this.setVisible(false);
							JFrameNewMember.this.setEnabled(false);
							
							//open data window
							JFrameData jfd = new JFrameData();
							jfd.setVisible(true);
							jfd.setEnabled(true);
							jfd.setAlwaysOnTop(true);
							
							//enable success popup
							Success s = new Success();
							s.setVisible(true);
							s.setEnabled(true);
							s.setAlwaysOnTop(true);
						}
						else{
							errorPopup erp = new errorPopup();
							erp.setEnabled(true);
							erp.setErrorText("Something went wrong");
							erp.setVisible(true);
							erp.setAlwaysOnTop(true);
						}
						
					}
					else{
						errorPopup erp = new errorPopup();
						erp.setEnabled(true);
						erp.setErrorText("Something went wrong adding EID");
						erp.setVisible(true);
						erp.setAlwaysOnTop(true);
					}
					
					
				} 
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					errorPopup erp = new errorPopup();
					erp.setEnabled(true);
					erp.setErrorText("Something went wrong adding EID");
					erp.setVisible(true);
					erp.setAlwaysOnTop(true);
				}
			}
		});
		btnConfirm.setBounds(243, 225, 117, 25);
		contentPane.add(btnConfirm);
	}
}
