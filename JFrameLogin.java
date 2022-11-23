import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class JFrameLogin extends JFrame implements ActionListener{
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JPasswordField passwordField;
	JLabel lblAcostasBarginMart;
	JTextPane userPane;
	JButton btnSubmit;
	static JFrameLogin frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new JFrameLogin();
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
	public JFrameLogin()  {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setBackground(UIManager.getColor("Button.background"));
		txtUsername.setText("Username:");
		txtUsername.setBounds(12, 63, 80, 19);
		contentPane.add(txtUsername);
		txtUsername.setColumns(8);
		
		txtPassword = new JTextField();
		txtPassword.setText("Password:");
		txtPassword.setColumns(8);
		txtPassword.setBackground(UIManager.getColor("Button.background"));
		txtPassword.setBounds(12, 109, 80, 19);
		contentPane.add(txtPassword);
		
		lblAcostasBarginMart = new JLabel("Acosta's Bargin Mart User Login");
		lblAcostasBarginMart.setBounds(111, 12, 239, 15);
		contentPane.add(lblAcostasBarginMart);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(129, 109, 221, 19);
		contentPane.add(passwordField);
		
		userPane = new JTextPane();
		userPane.setBounds(129, 61, 221, 21);
		contentPane.add(userPane);
		
		JLabel lblError = new JLabel("Something went wrong: ");
		lblError.setBounds(47, 210, 321, 40);
		lblError.setVisible(false);
		contentPane.add(lblError);
		
		JLabel wrongPassLbl = new JLabel("ERROR: Wrong Password");
		wrongPassLbl.setBounds(161, 171, 207, 27);
		wrongPassLbl.setVisible(false);
		contentPane.add(wrongPassLbl);
		
		JCheckBox ORbox1 = new JCheckBox("");
		ORbox1.setBounds(87, 8, 21, 23);
		ORbox1.setVisible(false);
		contentPane.add(ORbox1);
		
		JCheckBox ORbox2 = new JCheckBox("");
		ORbox2.setBounds(347, 8, 21, 23);
		ORbox2.setVisible(false);
		contentPane.add(ORbox2);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(168, 135, 117, 25);
		btnSubmit.addActionListener(new ActionListener()
		{
			  @Override
			  public void actionPerformed(ActionEvent e)
			  {
				  SavedUsers s = new SavedUsers();
				  String user = userPane.getText();
				  String pass = new String(passwordField.getPassword());
				  
				  try {
					if(s.exists(user, pass)) {
						  frame.setVisible(false);
						  JFrameHome jfh = new JFrameHome();
						  jfh.setVisible(true);
					  }
					  else {
						  wrongPassLbl.setVisible(true);
						  ORbox1.setVisible(true);
						  ORbox2.setVisible(true);
					  }
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					lblError.setText(lblError.getText() + e1);
					lblError.setVisible(true);
				}
			  }
			});
		contentPane.add(btnSubmit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//this wont work unless i have this method??? idk man javas dumb
		
	}
}
