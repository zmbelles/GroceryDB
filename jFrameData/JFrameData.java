package jFrameData;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jFrameLogin.JFrameHome;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
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
				emp.setEnabled(true);
				emp.setVisible(true);
				emp.setAlwaysOnTop(true);
				JFrameData.this.setEnabled(false);
				JFrameData.this.setAlwaysOnTop(false);
				JFrameData.this.setVisible(false);
			}
		});
		btnAddNewEmployees.setBounds(12, 89, 200, 50);
		contentPane.add(btnAddNewEmployees);
		
		JButton btnAddNewDepts = new JButton("Add New Departments");
		btnAddNewDepts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameNewDepartment dept = new JFrameNewDepartment();
				dept.setEnabled(true);
				dept.setVisible(true);
				dept.setAlwaysOnTop(true);
				JFrameData.this.setEnabled(false);
				JFrameData.this.setAlwaysOnTop(false);
				JFrameData.this.setVisible(false);
			}
		});
		btnAddNewDepts.setBounds(225, 39, 200, 50);
		contentPane.add(btnAddNewDepts);
		
		JButton btnAddNewDiscounts = new JButton("Add New Discounts");
		btnAddNewDiscounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameNewDiscount disc = new JFrameNewDiscount();
				disc.setEnabled(true);
				disc.setVisible(true);
				disc.setAlwaysOnTop(true);
				JFrameData.this.setEnabled(false);
				JFrameData.this.setAlwaysOnTop(false);
				JFrameData.this.setVisible(false);
			}
		});
		btnAddNewDiscounts.setBounds(225, 89, 200, 50);
		contentPane.add(btnAddNewDiscounts);
		
		JButton btnAddNewMembers = new JButton("Add New Members");
		btnAddNewMembers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameNewMember mem = new JFrameNewMember();
				mem.setEnabled(true);
				mem.setVisible(true);
				mem.setAlwaysOnTop(true);
				JFrameData.this.setEnabled(false);
				JFrameData.this.setAlwaysOnTop(false);
				JFrameData.this.setVisible(false);
			}
		});
		btnAddNewMembers.setBounds(12, 39, 200, 50);
		contentPane.add(btnAddNewMembers);
		
		JButton btnAddNewShipments = new JButton("Add New Shipments");
		btnAddNewShipments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameNewShipment ship = new JFrameNewShipment();
				ship.setEnabled(true);
				ship.setVisible(true);
				ship.setAlwaysOnTop(true);
				JFrameData.this.setEnabled(false);
				JFrameData.this.setAlwaysOnTop(false);
				JFrameData.this.setVisible(false);
			}
		});
		btnAddNewShipments.setBounds(12, 137, 200, 50);
		contentPane.add(btnAddNewShipments);
		
		JLabel lblWhichTable = new JLabel("Which table would you like to add data to?");
		lblWhichTable.setBounds(118, 13, 232, 15);
		contentPane.add(lblWhichTable);
		
		JButton btnAddNewInventory = new JButton("Add New Inventory");
		btnAddNewInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameNewInventory inv = new JFrameNewInventory();
				inv.setEnabled(true);
				inv.setVisible(true);
				inv.setAlwaysOnTop(true);
				JFrameData.this.setEnabled(false);
				JFrameData.this.setAlwaysOnTop(false);
				JFrameData.this.setVisible(false);
			}
		});
		btnAddNewInventory.setBounds(225, 137, 200, 50);
		contentPane.add(btnAddNewInventory);
		
		JButton btnBackToHome = new JButton("Back to Home");
		btnBackToHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrameHome jfh = new JFrameHome();
				jfh.setEnabled(true);
				jfh.setVisible(true);
				jfh.setAlwaysOnTop(true);
				JFrameData.this.setEnabled(false);
				JFrameData.this.setAlwaysOnTop(false);
				JFrameData.this.setVisible(false);
				}
		});
		btnBackToHome.setBounds(154, 214, 130, 25);
		contentPane.add(btnBackToHome);
		
	}
	
}
