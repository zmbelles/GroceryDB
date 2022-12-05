package jFrameData;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class JFrameNewEmployee extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmpFName;
	private JTextField txtEmpLName;
	private JTextField txtEmpSal;
	private JTextField txtEmpDOB;
	private JTextField txtEmpSSN;
	private JTextField txtEmpDept;
	private JButton btnConfirm_1;
	private Stack<String> stack = new Stack<String>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameNewEmployee frame = new JFrameNewEmployee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private boolean addMgrID(){
		int mgrID=0;
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/term", "root", "!Fall2022");
			Statement stmt = conn.createStatement();
			String getID = "select distinct S_EID "
					+ "from employee "
					+ "where Department_DID = " 
					+ stack.pop();
			
			ResultSet rs = stmt.executeQuery(getID);
			rs.next();
			stack.push(rs.getString("S_EID"));
			return true;
				
		} 
		catch (SQLException e1) {return false;}
		
	}
	private boolean executeQuery(Connection conn, Statement stmt){
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
		}
		String addEmp = "INSERT INTO employee "
				+ "VALUES ("
				+ stack.pop() + ", '"
				+ stack.pop() + "', '"
				+ stack.pop() + "', "
				+ stack.pop() + ", "
				+ stack.pop() + ", '"
				+ stack.pop() + "', "
				+ stack.pop() + ", "
				+ stack.pop() + ")";
		int n=5;
		n++;
		try{
			stmt.execute(addEmp);
		}
		//if error return true
		catch(SQLException e){
			return true;
		}
		//otherwise return false
		return false;
	}
	private int connect() {
		int errorCode = -1;
    	Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/term", "root", "!Fall2022");
			Statement stmt = conn.createStatement();
			int newEID=0;
			String getOldEID = "select e1.EID "
					+ "from employee e1 "
					+ "where e1.EID = (select max(e2.EID) from employee e2)";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(getOldEID);
			//no while since i know there's only one
			rs.next();
			int oldDID = Integer.parseInt(rs.getString("EID"));
			newEID = oldDID;
			newEID++;
			stack.push(Integer.toString(newEID));
			boolean error = executeQuery(conn, stmt); 
			if(error) errorCode = 1;
			else errorCode = 0;
			
		}
		catch(Exception e) {
			//error code 0 = connection error
			//error code 1 = SQLException
			return errorCode;
		}
		return errorCode;
	}
	/**
	 * Create the frame.
	 */
	public JFrameNewEmployee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtEmpFName = new JTextField();
		txtEmpFName.setText("Enter employee first name here");
		txtEmpFName.setBounds(81, 46, 262, 19);
		contentPane.add(txtEmpFName);
		txtEmpFName.setColumns(10);
		
		txtEmpLName = new JTextField();
		txtEmpLName.setText("Enter employee last name here");
		txtEmpLName.setColumns(10);
		txtEmpLName.setBounds(81, 77, 262, 19);
		contentPane.add(txtEmpLName);
		
		txtEmpSal = new JTextField();
		txtEmpSal.setText("Enter employee salary here");
		txtEmpSal.setColumns(10);
		txtEmpSal.setBounds(81, 135, 262, 19);
		contentPane.add(txtEmpSal);
		
		txtEmpDOB = new JTextField();
		txtEmpDOB.setText("Enter employee DOB here (DD/MM/YYYY)");
		txtEmpDOB.setColumns(10);
		txtEmpDOB.setBounds(81, 165, 262, 19);
		contentPane.add(txtEmpDOB);
		
		txtEmpSSN = new JTextField();
		txtEmpSSN.setText("Enter employee SSN here");
		txtEmpSSN.setColumns(10);
		txtEmpSSN.setBounds(81, 195, 262, 19);
		contentPane.add(txtEmpSSN);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String empSSN = txtEmpSSN.getText();
				String empDOB = txtEmpDOB.getText();
				String empSal = txtEmpDOB.getText();
				String empDept = txtEmpDept.getText();
				String empLName = txtEmpLName.getText();
				String empFName = txtEmpFName.getText();
				stack.push(empDept);
				boolean added = addMgrID();
				if(added) {
					stack.push(empSSN);
					stack.push(empDOB);
					stack.push(empSal);
					stack.push(empDept);
					stack.push(empLName);
					stack.push(empFName);
					int status = connect();
					if(status==0) {
						//close this window
						JFrameNewEmployee.this.setVisible(false);
						JFrameNewEmployee.this.setEnabled(false);
						
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
					erp.setErrorText("Something went wrong");
					erp.setVisible(true);
					erp.setAlwaysOnTop(true);
				}
			}
		});
		
		btnConfirm.setBounds(204, 225, 117, 25);
		contentPane.add(btnConfirm);
		
		JLabel lblAddNewEmployee = new JLabel("Add New Employee");
		lblAddNewEmployee.setBounds(143, 12, 164, 15);
		contentPane.add(lblAddNewEmployee);
		
		txtEmpDept = new JTextField();
		txtEmpDept.setText("Enter employee department number here");
		txtEmpDept.setColumns(10);
		txtEmpDept.setBounds(81, 105, 262, 19);
		contentPane.add(txtEmpDept);
		
		btnConfirm_1 = new JButton("Back");
		btnConfirm_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameNewEmployee.this.setEnabled(false);
				JFrameNewEmployee.this.setVisible(false);
				
				JFrameData data = new JFrameData();
				data.setEnabled(true);
				data.setVisible(true);
			}
		});
		btnConfirm_1.setBounds(81, 225, 117, 25);
		contentPane.add(btnConfirm_1);
	}
}
