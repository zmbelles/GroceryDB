package jFrameLogin;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class JFrameHome extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameHome frame = new JFrameHome();
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
	public JFrameHome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeAdmin = new JLabel("Welcome, Admin");
		lblWelcomeAdmin.setBounds(165, 12, 143, 15);
		contentPane.add(lblWelcomeAdmin);
		
		JButton btnAddTables = new JButton("Add Tables");
		btnAddTables.addActionListener(new ActionListener() {
			
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				FunctionsOfJFrameHome fjfh = new FunctionsOfJFrameHome();
				fjfh.connect(1);
			}
		});
		btnAddTables.setBounds(29, 39, 164, 25);
		contentPane.add(btnAddTables);
		
		JButton btnAddLegacyData = new JButton("Add Legacy Data");
		btnAddLegacyData.setBounds(29, 91, 164, 25);
		contentPane.add(btnAddLegacyData);
		
		JButton btnAddLegacyData_1 = new JButton("Perform Queries");
		btnAddLegacyData_1.setBounds(257, 39, 164, 25);
		contentPane.add(btnAddLegacyData_1);
		
		JButton btnAddNewData = new JButton("Add New Data");
		btnAddNewData.setBounds(257, 91, 164, 25);
		contentPane.add(btnAddNewData);
		
		JTextPane txtpnAcostasWeGuarentee = new JTextPane();
		txtpnAcostasWeGuarentee.setForeground(UIManager.getColor("Button.foreground"));
		txtpnAcostasWeGuarentee.setBackground(UIManager.getColor("Button.background"));
		txtpnAcostasWeGuarentee.setText("Acosta's, \"We Guarentee nothing... but freshness\"");
		txtpnAcostasWeGuarentee.setBounds(48, 195, 341, 21);
		contentPane.add(txtpnAcostasWeGuarentee);
	}

}
