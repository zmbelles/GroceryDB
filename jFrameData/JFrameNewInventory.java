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
	private Stack<String> stack;
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
				int newPID;
				String getNewEmpNum = "Select MAX(PID) "
						+ "from inventory";
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(getNewEmpNum);
				int oldPID = Integer.parseInt(rs.getString("PID"));
				newPID = oldPID++;
				return newPID;
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
		txtProductName.setBounds(89, 72, 262, 37);
		contentPane.add(txtProductName);
		
		txtProductPrice = new JTextField();
		txtProductPrice.setText("Enter product price here");
		txtProductPrice.setColumns(10);
		txtProductPrice.setBounds(89, 121, 262, 39);
		contentPane.add(txtProductPrice);
		
		txtProductQty = new JTextField();
		txtProductQty.setText("Enter product quantity here");
		txtProductQty.setColumns(10);
		txtProductQty.setBounds(89, 172, 262, 37);
		contentPane.add(txtProductQty);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productName = txtProductName.getText();
				String productPrice = txtProductPrice.getText();
				String productQty = txtProductQty.getText();
				String PID = Integer.toString(connect());
				stack.push(productName);
				stack.push(productPrice);
				stack.push(productQty);
				stack.push(PID);
				try {
					executeQuery();
					JFrameNewInventory.this.setEnabled(false);
					JFrameNewInventory.this.setVisible(false);
					JFrameData jfd = new JFrameData();
					jfd.setEnabled(true);
					jfd.setVisible(true);
					jfd.setAlwaysOnTop(true);
					
					Success s = new Success();
					s.setEnabled(true);
					s.setVisible(true);
					s.setAlwaysOnTop(true);
				} catch (SQLException e1) {
					errorPopup erp = new errorPopup();
					erp.setEnabled(true);
					erp.setErrorText(e1.toString());
					erp.setVisible(true);
					erp.setAlwaysOnTop(true);
				}
			}
		});
		btnConfirm.setBounds(201, 220, 117, 25);
		contentPane.add(btnConfirm);
	}
}
