package jFrameData;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jFrameLogin.errorPopup;
import jFrameLogin.JFrameHome;

import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class JFrameData extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameData frame = new JFrameData();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public JFrameData() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddNewEmployees = new JButton("Add New Employees");
		btnAddNewEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameNewEmployee emp = new JFrameNewEmployee();
				emp.dispatchEvent(new WindowEvent(emp, WindowEvent.WINDOW_OPENED));
				JFrameData.this.dispatchEvent(new WindowEvent(JFrameData.this, WindowEvent.WINDOW_CLOSED));
			}
		});
		btnAddNewEmployees.setBounds(12, 89, 200, 50);
		contentPane.add(btnAddNewEmployees);
		
		JButton btnAddNewDepts = new JButton("Add New Departments");
		btnAddNewDepts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameNewDepartment dept = new JFrameNewDepartment();
				dept.dispatchEvent(new WindowEvent(dept, WindowEvent.WINDOW_OPENED));
				JFrameData.this.dispatchEvent(new WindowEvent(JFrameData.this, WindowEvent.WINDOW_CLOSED));
			}
		});
		btnAddNewDepts.setBounds(225, 39, 200, 50);
		contentPane.add(btnAddNewDepts);
		
		JButton btnAddNewDiscounts = new JButton("Add New Discounts");
		btnAddNewDiscounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameNewDiscount disc = new JFrameNewDiscount();
				disc.dispatchEvent(new WindowEvent(disc, WindowEvent.WINDOW_OPENED));
				JFrameData.this.dispatchEvent(new WindowEvent(JFrameData.this, WindowEvent.WINDOW_CLOSED));
			}
		});
		btnAddNewDiscounts.setBounds(225, 89, 200, 50);
		contentPane.add(btnAddNewDiscounts);
		
		JButton btnAddNewMembers = new JButton("Add New Members");
		btnAddNewMembers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameNewMember mem = new JFrameNewMember();
				mem.dispatchEvent(new WindowEvent(mem, WindowEvent.WINDOW_OPENED));
				JFrameData.this.dispatchEvent(new WindowEvent(JFrameData.this, WindowEvent.WINDOW_CLOSED));
			}
		});
		btnAddNewMembers.setBounds(12, 39, 200, 50);
		contentPane.add(btnAddNewMembers);
		
		JButton btnAddNewShipments = new JButton("Add New Shipments");
		btnAddNewShipments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameNewShipment ship = new JFrameNewShipment();
				ship.dispatchEvent(new WindowEvent(ship, WindowEvent.WINDOW_OPENED));
				JFrameData.this.dispatchEvent(new WindowEvent(JFrameData.this, WindowEvent.WINDOW_CLOSED));
			}
		});
		btnAddNewShipments.setBounds(12, 137, 200, 50);
		contentPane.add(btnAddNewShipments);
		
		JLabel lblWhichTable = new JLabel("Which table would you like to add data to?");
		lblWhichTable.setBounds(62, 12, 327, 15);
		contentPane.add(lblWhichTable);
		
		JButton btnAddNewInventory = new JButton("Add New Inventory");
		btnAddNewInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameNewInventory inv = new JFrameNewInventory();
				inv.dispatchEvent(new WindowEvent(inv, WindowEvent.WINDOW_OPENED));
				JFrameData.this.dispatchEvent(new WindowEvent(JFrameData.this, WindowEvent.WINDOW_CLOSED));
			}
		});
		btnAddNewInventory.setBounds(225, 137, 200, 50);
		contentPane.add(btnAddNewInventory);
		
		JButton btnBackToHome = new JButton("Back to Home");
		btnBackToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameHome jfh = new JFrameHome();
				jfh.dispatchEvent(new WindowEvent(jfh, WindowEvent.WINDOW_OPENED));
				JFrameData.this.dispatchEvent(new WindowEvent(JFrameData.this, WindowEvent.WINDOW_CLOSED));
			}
		});
		btnBackToHome.setBounds(154, 214, 130, 25);
		contentPane.add(btnBackToHome);
		
	}
	
}
