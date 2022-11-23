package jFrameData;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBounds(0, 0, 438, 260);
		contentPane.add(contentPane_1);
		contentPane_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblWelcomeAdmin = new JLabel("Welcome, Admin");
		contentPane_1.add(lblWelcomeAdmin);
		
		JButton btnAddNewEmployees = new JButton("Add New Employees");
		btnAddNewEmployees.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrameNewEmployee jfne = new JFrameNewEmployee();
				jfne.setVisible(true);
				jfne.setEnabled(true);
				jfne.dispatchEvent(new WindowEvent(jfne, WindowEvent.WINDOW_OPENED));
				JFrameData.this.dispatchEvent(new WindowEvent(
						JFrameData.this, WindowEvent.WINDOW_CLOSING));
				JFrameData.this.setAlwaysOnTop(false);
				
			}
		});
		contentPane_1.add(btnAddNewEmployees);
		
		JButton btnAddNewTables = new JButton("Add New Departments");
		contentPane_1.add(btnAddNewTables);
		
		JButton btnAddNewDiscounts = new JButton("Add New Discounts");
		contentPane_1.add(btnAddNewDiscounts);
		
		JButton btnAddNewInventory = new JButton("Add New Inventory");
		contentPane_1.add(btnAddNewInventory);
		
		JButton btnAddNewMembers = new JButton("Add New Members");
		contentPane_1.add(btnAddNewMembers);
		
		JButton btnAddNewShipments = new JButton("Add New Shipments");
		contentPane_1.add(btnAddNewShipments);
		
		JTextPane txtAcostaGuarentee = new JTextPane();
		txtAcostaGuarentee.setFont(new Font("Dialog", Font.PLAIN, 12));
		txtAcostaGuarentee.setText("          Acosta's, \"We guarentee nothing... but freshness\"");
		txtAcostaGuarentee.setForeground(UIManager.getColor("Button.foreground"));
		txtAcostaGuarentee.setBackground(UIManager.getColor("Button.background"));
		contentPane_1.add(txtAcostaGuarentee);
		
	}
	
}
