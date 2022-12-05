package jFrameLogin;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jFrameData.JFrameData;
import jFrameData.Success;
import jFrameData.errorPopup;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class JFrameHome extends JFrame {

	private JPanel contentPane;
	JLabel lblWelcomeAdmin;
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
	public void setWelcomeText(String text) {
		lblWelcomeAdmin.setText(text);;
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
		
		lblWelcomeAdmin = new JLabel("Welcome, Admin");
		lblWelcomeAdmin.setBounds(165, 12, 143, 15);
		contentPane.add(lblWelcomeAdmin);
		
		JButton btnAddTables = new JButton("Add Tables");
		btnAddTables.addActionListener(new ActionListener() {
			
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				FunctionsOfJFrameHome fjfh = new FunctionsOfJFrameHome();
				int status = fjfh.connect(1);
				if(status==0) {
					Success success_popup = new Success();
					success_popup.setEnabled(true);
					success_popup.setVisible(true);
					success_popup.setAlwaysOnTop(true);
				}
				else {
					errorPopup err = new errorPopup();
					err.setEnabled(true);
					err.setVisible(true);
					err.setAlwaysOnTop(true);
				}
			}
		});
		btnAddTables.setBounds(29, 39, 164, 25);
		contentPane.add(btnAddTables);
		
		JButton btnAddLegacyData = new JButton("Add Legacy Data");
		btnAddLegacyData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FunctionsOfJFrameHome fjfh = new FunctionsOfJFrameHome();
				int status = fjfh.connect(2);
				if(status==0) {
					Success success_popup = new Success();
					success_popup.setEnabled(true);
					success_popup.setVisible(true);
					success_popup.setAlwaysOnTop(true);
				}
				else {
					errorPopup err = new errorPopup();
					err.setEnabled(true);
					err.setVisible(true);
					err.setAlwaysOnTop(true);
				}
			}
		});
		btnAddLegacyData.setBounds(29, 91, 164, 25);
		contentPane.add(btnAddLegacyData);
		
		JButton btnPerform = new JButton("Perform Queries");
		btnPerform.setBounds(257, 39, 164, 25);
		contentPane.add(btnPerform);
		
		JButton btnAddNewData = new JButton("Add New Data");
		btnAddNewData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrameData data = new JFrameData();
				data.setEnabled(true);
				data.setVisible(true);
				data.setAlwaysOnTop(true);
				JFrameHome.this.setEnabled(false);
				JFrameHome.this.setAlwaysOnTop(false);
				JFrameHome.this.setVisible(false);
			}
		});
		btnAddNewData.setBounds(257, 91, 164, 25);
		contentPane.add(btnAddNewData);
		
		JTextPane txtpnAcostasWeGuarentee = new JTextPane();
		txtpnAcostasWeGuarentee.setForeground(UIManager.getColor("Button.foreground"));
		txtpnAcostasWeGuarentee.setBackground(UIManager.getColor("Button.background"));
		txtpnAcostasWeGuarentee.setText("Acosta's, \"We Guarentee nothing... but freshness\"");
		txtpnAcostasWeGuarentee.setBounds(48, 195, 341, 21);
		contentPane.add(txtpnAcostasWeGuarentee);
		
		JButton btnlogOut = new JButton("Log Out");
		btnlogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameHome.this.setVisible(false);
				JFrameHome.this.setEnabled(false);
				
				JFrameLogin login = new JFrameLogin();
				login.setVisible(true);
				login.setEnabled(true);
			}
		});
		btnlogOut.setBounds(144, 143, 164, 25);
		contentPane.add(btnlogOut);
	}

}
