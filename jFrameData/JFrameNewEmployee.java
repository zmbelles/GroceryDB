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
	private Stack<String> stack;
	private JTextField txtEmpDept;
	private JButton btnConfirm_1;

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
	public void executeQuery() throws SQLException {
		Connection conn;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/term", "root", "!Fall2022");
		Statement stmt = conn.createStatement();
		String addEmp = "INSERT INTO employee "
				+ "VALUES("
				+ stack.pop() + ", "
				+ stack.pop() + ", "
				+ stack.pop() + ", "
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
				int newEmpNum;
				String getNewEmpNum = "Select MAX(EID) "
						+ "from employee";
				ResultSet rs = stmt.executeQuery(getNewEmpNum);
				int oldEmpNum = Integer.parseInt(rs.getString("EID"));
				newEmpNum = oldEmpNum++;
				return newEmpNum;
				
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
				Stack<String> stack = new Stack<String>();
				String empSSN = txtEmpSSN.getText();
				String empDOB = txtEmpDOB.getText();
				String empSal = txtEmpDOB.getText();
				String empDept = txtEmpDept.getText();
				String empLName = txtEmpLName.getText();
				String empFName = txtEmpFName.getText();
				String newEmpNum = Integer.toString(connect());
				
				stack.push(empSSN);
				stack.push(empDOB);
				stack.push(empSal);
				stack.push(empDept);
				stack.push(empLName);
				stack.push(empFName);
				stack.push(newEmpNum);
				
				try {
					executeQuery();
					JFrameNewEmployee.this.setEnabled(false);
					JFrameNewEmployee.this.setVisible(false);
					
					JFrameData jfd = new JFrameData();
					jfd.setEnabled(true);
					jfd.setVisible(true);
					jfd.setAlwaysOnTop(true);
					
					Success s = new Success();
					s.setEnabled(true);
					s.setVisible(true);
					s.setAlwaysOnTop(true);
				} catch (SQLException e1) {
					errorPopup error = new errorPopup();
					error.setEnabled(true);
					error.setErrorText(e1.toString());
					error.setVisible(true);
					error.setAlwaysOnTop(true);
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
				data.setAlwaysOnTop(true);
			}
		});
		btnConfirm_1.setBounds(81, 225, 117, 25);
		contentPane.add(btnConfirm_1);
	}
}
