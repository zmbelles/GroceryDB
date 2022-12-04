package jFrameLogin;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JPasswordField txtPassword;
	JLabel lblAcostasBarginMart;
	JTextPane txtUsername;
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
		
		lblAcostasBarginMart = new JLabel("Acosta's Bargin Mart User Login");
		lblAcostasBarginMart.setBounds(111, 12, 239, 15);
		contentPane.add(lblAcostasBarginMart);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(129, 109, 221, 19);
		contentPane.add(txtPassword);
		
		txtUsername = new JTextPane();
		txtUsername.setBounds(129, 61, 221, 21);
		contentPane.add(txtUsername);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(168, 135, 117, 25);
		btnSubmit.addActionListener(new ActionListener()
		{
			  @Override
			  public void actionPerformed(ActionEvent e)
			  {
				  Connection conn;
				  try {
					  conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/term", "root", "!Fall2022");
					  Statement stmt = conn.createStatement();
					  FunctionsOfJFrameHome func = new FunctionsOfJFrameHome();
					  int[] status = func.supplyUsers(conn, stmt);
					  String uName = txtUsername.getText();
					  @SuppressWarnings("deprecation")
					  String pass = Integer.toString(txtPassword.getText().hashCode());
					  String getlogin = "select Username, Password "
							+ "from users "
							+ "where Username = '" + uName
							+ "' && Password = '" + pass + "'";
						  
					  try {
						  ResultSet rs = stmt.executeQuery(getlogin);
						  while(rs.next()) {
							  String user = rs.getString("Username");
							  if(rs.wasNull()) {
								  errorPopup error = new errorPopup();
								  error.setErrorText("ERROR: WRONG USERNAME OR PASSWORD");
								  error.setEnabled(true);
								  error.setVisible(true);
							  }
							  else { 
								  JFrameHome home = new JFrameHome();
								  String welcomeText = "Welcome, " + user;
								  home.setWelcomeText(welcomeText);
								  home.setVisible(true);
								  home.setEnabled(true);
								
								  JFrameLogin.this.setEnabled(false);
								  JFrameLogin.this.setVisible(false);
							  }
						  }
					  }catch(SQLException err) {
						  String error = err.toString();
						  System.out.println(error);
					  }
				  } catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				  } 
				  
			  }
		});
		contentPane.add(btnSubmit);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(34, 68, 85, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setBounds(34, 112, 69, 14);
		contentPane.add(lblPassword);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//this wont work unless i have this method??? idk man javas dumb
		
	}
}
