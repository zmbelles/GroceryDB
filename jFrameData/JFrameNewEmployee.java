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
	private Stack<String> stack;

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
	public int connect() {
		int errorCode = -1;
    	Connection conn;
    	{
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/term", "root", "!Fall2022");
				Statement stmt = conn.createStatement();
				errorCode = 1;
				int newEmpNum;
				String getNewEmpNum = "Select MAX(EID) "
						+ "from employee";
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(getNewEmpNum);
				int oldEmpNum = Integer.parseInt(rs.getString("EID"));
				boolean success = importEmpData(stmt, oldEmpNum++);
				if(success) {
					return 0;
				}
				
			}
			catch(Exception e) {
				//error code 0 = connection error
				//error code 1 = SQLException
				return errorCode;
			}
    	}
    	return errorCode;
	}
	private boolean importEmpData(Statement stmt, int newEmpNum){
		
		return false;
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
		txtEmpSal.setBounds(81, 106, 262, 19);
		contentPane.add(txtEmpSal);
		
		txtEmpDOB = new JTextField();
		txtEmpDOB.setText("Enter employee DOB here (DD/MM/YYYY)");
		txtEmpDOB.setColumns(10);
		txtEmpDOB.setBounds(81, 137, 262, 19);
		contentPane.add(txtEmpDOB);
		
		txtEmpSSN = new JTextField();
		txtEmpSSN.setText("Enter employee SSN here");
		txtEmpSSN.setColumns(10);
		txtEmpSSN.setBounds(81, 168, 262, 19);
		contentPane.add(txtEmpSSN);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Stack<String> stack = new Stack<String>();
				String empSSN = txtEmpSSN.getText();
				String empDOB = txtEmpDOB.getText();
				String empSal = txtEmpDOB.getText();
				String empLName = txtEmpLName.getText();
				String empFName = txtEmpFName.getText();
				
				stack.push(empFName);
				stack.push(empLName);
				stack.push(empSal);
				stack.push(empDOB);
				stack.push(empSSN);
				connect();
			}
		});
		btnConfirm.setBounds(143, 199, 117, 25);
		contentPane.add(btnConfirm);
		
		JLabel lblAddNewEmployee = new JLabel("Add New Employee");
		lblAddNewEmployee.setBounds(143, 12, 164, 15);
		contentPane.add(lblAddNewEmployee);
	}
}
