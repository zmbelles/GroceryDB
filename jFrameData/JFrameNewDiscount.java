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
public class JFrameNewDiscount extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterSalePrice;
	private JTextField txtEnterSaleStart;
	private JTextField txtSaleEndDate;
	private JTextField txtRegPrice;
	private JTextField txtMembersOnly;
	private JButton btnConfirm;
	private Stack<String> stack;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameNewDiscount frame = new JFrameNewDiscount();
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
	public JFrameNewDiscount() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddNewDiscount = new JLabel("Add New Discount");
		lblAddNewDiscount.setBounds(157, 12, 164, 15);
		contentPane.add(lblAddNewDiscount);
		
		txtEnterSalePrice = new JTextField();
		txtEnterSalePrice.setText("Enter sale price ($xx.xx)");
		txtEnterSalePrice.setColumns(10);
		txtEnterSalePrice.setBounds(77, 39, 296, 19);
		contentPane.add(txtEnterSalePrice);
		
		txtEnterSaleStart = new JTextField();
		txtEnterSaleStart.setText("Enter sale start date (DD/MM/YYYY)");
		txtEnterSaleStart.setColumns(10);
		txtEnterSaleStart.setBounds(77, 70, 296, 19);
		contentPane.add(txtEnterSaleStart);
		
		txtSaleEndDate = new JTextField();
		txtSaleEndDate.setText("Enter sale end date (DD/MM/YYYY)");
		txtSaleEndDate.setColumns(10);
		txtSaleEndDate.setBounds(77, 101, 296, 19);
		contentPane.add(txtSaleEndDate);
		
		txtRegPrice = new JTextField();
		txtRegPrice.setText("Enter regular item price ($xx.xx)");
		txtRegPrice.setColumns(10);
		txtRegPrice.setBounds(77, 132, 296, 19);
		contentPane.add(txtRegPrice);
		
		txtMembersOnly = new JTextField();
		txtMembersOnly.setText("Is this members only? (0 for no, 1 for yes)");
		txtMembersOnly.setColumns(10);
		txtMembersOnly.setBounds(77, 163, 296, 19);
		contentPane.add(txtMembersOnly);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String discoID = Integer.toString(connect());
				String price = txtEnterSalePrice.getText();
				String start = txtEnterSaleStart.getText();
				String end = txtSaleEndDate.getText();
				String reg = txtRegPrice.getText();
				String isMemOnly = txtMembersOnly.getText();
				
				stack.push(isMemOnly);
				stack.push(reg);
				stack.push(end);
				stack.push(start);
				stack.push(price);
				stack.push(discoID);
				try {
					executeQuery();
					JFrameNewDiscount.this.setVisible(false);
					JFrameNewDiscount.this.setEnabled(false);
					
					JFrameData jfd = new JFrameData();
					jfd.setVisible(true);
					jfd.setEnabled(true);
					jfd.setAlwaysOnTop(true);
					
					Success s = new Success();
					s.setVisible(true);
					s.setEnabled(true);
					s.setAlwaysOnTop(true);
				} 
				catch (SQLException e1) {
					errorPopup erp = new errorPopup();
					erp.setEnabled(true);
					erp.setVisible(true);
					erp.setAlwaysOnTop(true);
					
					JFrameData jfd = new JFrameData();
					jfd.setEnabled(rootPaneCheckingEnabled);
					jfd.setVisible(false);
					jfd.setAlwaysOnTop(true);
				}
				
			}
		});
		btnConfirm.setBounds(157, 194, 117, 25);
		contentPane.add(btnConfirm);
		
	}

}
