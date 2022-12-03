package jFrameData;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class JFrameNewShipment extends JFrame {

	private Stack<String> stack;
	private JPanel contentPane;
	private JTextField txtOrderId;
	private JTextField txtQtyOnOrder;
	private JTextField txtQtyInTransit;
	private JTextField txtArrivalDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameNewShipment frame = new JFrameNewShipment();
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
		String addShip = "INSERT INTO shipment "
				+ "VALUES("
				+ stack.pop() + ", "
				+ stack.pop() + ", "
				+ stack.pop() + ", "
				+ stack.pop() + ")";
		stmt.execute(addShip);
	}
	
	public int connect() {
		int errorCode = -1;
    	Connection conn;
    	{
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/term", "root", "!Fall2022");
				Statement stmt = conn.createStatement();
				errorCode = 1;
				int newSID;
				String getNewSID = "Select MAX(OrderID) "
						+ "from shipment";
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(getNewSID);
				int oldSID = Integer.parseInt(rs.getString("OrderID"));
				newSID = oldSID++;
				return newSID;
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
	public JFrameNewShipment() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddNewShipment = new JLabel("Add New Shipment");
		lblAddNewShipment.setBounds(153, 12, 164, 15);
		contentPane.add(lblAddNewShipment);
		
		txtOrderId = new JTextField();
		txtOrderId.setText("Enter Order ID");
		txtOrderId.setColumns(10);
		txtOrderId.setBounds(62, 39, 296, 19);
		contentPane.add(txtOrderId);
		
		txtQtyOnOrder = new JTextField();
		txtQtyOnOrder.setText("Enter number of items on order");
		txtQtyOnOrder.setColumns(10);
		txtQtyOnOrder.setBounds(62, 70, 296, 19);
		contentPane.add(txtQtyOnOrder);
		
		txtQtyInTransit = new JTextField();
		txtQtyInTransit.setText("Enter number of items in transit (if known)");
		txtQtyInTransit.setColumns(10);
		txtQtyInTransit.setBounds(62, 100, 296, 19);
		contentPane.add(txtQtyInTransit);
		
		txtArrivalDate = new JTextField();
		txtArrivalDate.setText("Enter arrival date(DD/MM/YYYY)");
		txtArrivalDate.setColumns(10);
		txtArrivalDate.setBounds(62, 130, 296, 19);
		contentPane.add(txtArrivalDate);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String OID = txtOrderId.getText();
				String orderQty = txtQtyOnOrder.getText();
				String transitQty = txtQtyInTransit.getText();
				String arrivalDate = txtArrivalDate.getText();
				
				stack.push(arrivalDate);
				stack.push(transitQty);
				stack.push(orderQty);
				stack.push(OID);
				
				try {
					executeQuery();
					JFrameNewShipment.this.setEnabled(false);
					JFrameNewShipment.this.setVisible(false);
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
		btnConfirm.setBounds(150, 163, 117, 25);
		contentPane.add(btnConfirm);
	}
}
