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
public class JFrameNewInventory extends JFrame {

	private JPanel contentPane;
	private JTextField txtProductName;
	private JTextField txtProductPrice;
	private JTextField txtProductQty;
	private Stack<String> stack = new Stack<String>();
	private JTextField txtDept;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameNewInventory frame = new JFrameNewInventory();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private boolean executeQuery(Connection conn, Statement stmt) {
		
		String addEmp = "INSERT INTO inventory "
				+ "VALUES ("
				+ stack.pop() + ", '"
				+ stack.pop() + "', "
				+ stack.pop() + ", "
				+ stack.pop() + ", "
				+ stack.pop() + ")";
		try {
			stmt.execute(addEmp);
			return false;
		}
		//if error return true
		catch (SQLException e) {
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
				int newPID;
				String getOldEmpNum = "select i1.PID "
						+ "from inventory i1 "
						+ "where i1.PID = (select max(i2.PID) from inventory i2)";
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(getOldEmpNum);
				rs.next();
				int oldPID = Integer.parseInt(rs.getString("PID"));
				newPID = oldPID;
				newPID++;
				stack.push(Integer.toString(newPID));
				boolean error = executeQuery(conn, stmt); 
				if(error) errorCode = 1;
				else errorCode = 0;
				return errorCode;
			}
			catch(Exception e) {
				//error code 0 = connection error
				//error code 1 = SQLException
				errorCode = 1;
				return errorCode;
			}
    	}
	}
	/**
	 * Create the frame.
	 */
	public JFrameNewInventory() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddNewInv = new JLabel("Add New Inventory");
		lblAddNewInv.setBounds(135, 12, 133, 15);
		contentPane.add(lblAddNewInv);
		
		txtProductName = new JTextField();
		txtProductName.setText("Enter product name here");
		txtProductName.setColumns(10);
		txtProductName.setBounds(89, 38, 262, 37);
		contentPane.add(txtProductName);
		
		txtProductPrice = new JTextField();
		txtProductPrice.setText("Enter product price here");
		txtProductPrice.setColumns(10);
		txtProductPrice.setBounds(89, 121, 262, 39);
		contentPane.add(txtProductPrice);
		
		txtProductQty = new JTextField();
		txtProductQty.setText("Enter product quantity here");
		txtProductQty.setColumns(10);
		txtProductQty.setBounds(89, 165, 262, 37);
		contentPane.add(txtProductQty);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productName = txtProductName.getText();
				String productPrice = txtProductPrice.getText();
				String productQty = txtProductQty.getText();
				String deptID = txtDept.getText();
				stack.push(productQty);
				stack.push(productPrice);
				stack.push(deptID);
				stack.push(productName);
				
				int status = connect();
				if(status==0) {
					//close this window
					JFrameNewInventory.this.setVisible(false);
					JFrameNewInventory.this.setEnabled(false);
					
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
		});
		btnConfirm.setBounds(230, 220, 117, 25);
		contentPane.add(btnConfirm);
		
		txtDept = new JTextField();
		txtDept.setText("Enter Department ID here");
		txtDept.setColumns(10);
		txtDept.setBounds(89, 80, 262, 37);
		contentPane.add(txtDept);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameNewInventory.this.setVisible(false);
				JFrameNewInventory.this.setEnabled(false);
				
				//open data window
				JFrameData jfd = new JFrameData();
				jfd.setVisible(true);
				jfd.setEnabled(true);
				jfd.setAlwaysOnTop(true);
			}
		});
		btnBack.setBounds(100, 221, 117, 25);
		contentPane.add(btnBack);
	}
}
