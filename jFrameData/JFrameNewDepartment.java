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

@SuppressWarnings("serial")
public class JFrameNewDepartment extends JFrame {

	private JPanel contentPane;
	private JTextField txtDepartmentName;
	private JTextField txtDepartmentExtention;
	private JTextField txtEmployees;
	private JTextField txtManagerEID;
	private Stack<String> stack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameNewDepartment frame = new JFrameNewDepartment();
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
		String addDisco = "INSERT INTO discount "
				+ "VALUES("
				+ stack.pop() + ", "
				+ stack.pop() + ", "
				+ stack.pop() + ", "
				+ stack.pop() + ", "
				+ stack.pop() + ")";
		stmt.execute(addDisco);
	}
	
	public int connect() {
		int errorCode = -1;
    	Connection conn;
    	{
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/term", "root", "!Fall2022");
				Statement stmt = conn.createStatement();
				errorCode = 1;
				int newDID;
				String getNewDID = "Select MAX(DiscountID) "
						+ "from discount";
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(getNewDID);
				int oldDID = Integer.parseInt(rs.getString("DiscountID"));
				newDID = oldDID++;
				return newDID;
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
	public JFrameNewDepartment() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddNewDept = new JLabel("Add New Department");
		lblAddNewDept.setBounds(151, 12, 164, 15);
		contentPane.add(lblAddNewDept);
		
		txtDepartmentName = new JTextField();
		txtDepartmentName.setText("Enter Department Name");
		txtDepartmentName.setColumns(10);
		txtDepartmentName.setBounds(79, 39, 296, 19);
		contentPane.add(txtDepartmentName);
		
		txtDepartmentExtention = new JTextField();
		txtDepartmentExtention.setText("Enter Department Extention");
		txtDepartmentExtention.setColumns(10);
		txtDepartmentExtention.setBounds(79, 69, 296, 19);
		contentPane.add(txtDepartmentExtention);
		
		txtEmployees = new JTextField();
		txtEmployees.setText("Enter Number of Employees");
		txtEmployees.setColumns(10);
		txtEmployees.setBounds(79, 99, 296, 19);
		contentPane.add(txtEmployees);
		
		txtManagerEID = new JTextField();
		txtManagerEID.setText("Enter Manager's EmployeeID");
		txtManagerEID.setColumns(10);
		txtManagerEID.setBounds(79, 129, 296, 19);
		contentPane.add(txtManagerEID);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String deptName = txtDepartmentName.getText();
				String ext = txtDepartmentExtention.getText();
				String numEmps = txtEmployees.getText();
				String mgrEID = txtManagerEID.getText();
				
				stack.push(mgrEID);
				stack.push(numEmps);
				stack.push(ext);
				stack.push(deptName);
				
				int status = connect();
				if(status == 0) {
					JFrameData data = new JFrameData();
					data.setEnabled(true);
					data.setVisible(true);
					data.setAlwaysOnTop(true);
					
					Success succ = new Success();
					succ.setVisible(true);
					succ.setEnabled(true);
					succ.setAlwaysOnTop(true);
					
					JFrameNewDepartment.this.setEnabled(false);
					JFrameNewDepartment.this.setVisible(false);
					JFrameNewDepartment.this.setAlwaysOnTop(false);
				}
				else {
					errorPopup error = new errorPopup();
					error.setEnabled(true);
					error.setVisible(true);
					error.setAlwaysOnTop(true);
				}
			}
		});
		btnNewButton.setBounds(164, 192, 89, 23);
		contentPane.add(btnNewButton);
	}
}
