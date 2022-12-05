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

	private Stack<String> stack = new Stack<String>();
	private JPanel contentPane;
	private JTextField txtOrderId;
	private JTextField txtQtyOnOrder;
	private JTextField txtQtyInTransit;
	private JTextField txtArrivalDate;
	private JButton btnBack;
	private JTextField txtPID;

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
	
	private boolean executeQuery(Connection conn, Statement stmt){
		String addShip = "INSERT INTO shipment_status "
				+ "VALUES("
				+ stack.pop() + ", "
				+ stack.pop() + ", "
				+ stack.pop() + ", '"
				+ stack.pop() + "', "
				+ stack.pop() + ")";
		try {
			stmt.execute(addShip);
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
				errorCode = 1;
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
				String PID = txtPID.getText();
				stack.push(PID);
				stack.push(arrivalDate);
				stack.push(transitQty);
				stack.push(orderQty);
				stack.push(OID);
				int status = connect();
				if(status==0) {
					//close this window
					JFrameNewShipment.this.setVisible(false);
					JFrameNewShipment.this.setEnabled(false);
					
					//open data window
					JFrameData jfd = new JFrameData();
					jfd.setVisible(true);
					jfd.setEnabled(true);
					
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
		btnConfirm.setBounds(223, 191, 117, 25);
		contentPane.add(btnConfirm);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameNewShipment.this.setVisible(false);
				JFrameNewShipment.this.setEnabled(false);
				
				//open data window
				JFrameData jfd = new JFrameData();
				jfd.setVisible(true);
				jfd.setEnabled(true);
				jfd.setAlwaysOnTop(true);
			}
		});
		btnBack.setBounds(90, 191, 117, 25);
		contentPane.add(btnBack);
		
		txtPID = new JTextField();
		txtPID.setText("Enter item Product ID");
		txtPID.setColumns(10);
		txtPID.setBounds(62, 161, 296, 19);
		contentPane.add(txtPID);
	}
}
