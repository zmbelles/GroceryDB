package jFrameLogin;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jFrameData.errorPopup;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JButton;

@SuppressWarnings("serial")
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
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(168, 135, 117, 25);
		btnSubmit.addActionListener(new ActionListener()
		{
			  @Override
			  public void actionPerformed(ActionEvent e)
			  {
				  Connection conn;
				  try {
					  FunctionsOfJFrameHome func = new FunctionsOfJFrameHome();
					  func.connect(1);
					  func.connect(2);
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/term", "root", "!Fall2022");
					Statement stmt = conn.createStatement();
					String getlogin = "SELECT username "
							+ "from users"
							+ "where username = " + txtUsername.getText() 
							+ " && password = " + Integer.toString(txtPassword.getText().hashCode());
					ResultSet rs = stmt.executeQuery(getlogin);
					String tmp = rs.getString("username");
					if(rs.wasNull()) {
						errorPopup error = new errorPopup();
						error.setErrorText("ERROR: WRONG USERNAME OR PASSWORD");
						error.setEnabled(true);
						error.setVisible(true);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
