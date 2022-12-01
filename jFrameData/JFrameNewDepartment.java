package jFrameData;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class JFrameNewDepartment extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameNewDepartment frame = new JFrameNewDepartment();
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
	public JFrameNewDepartment() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddNewDept = new JLabel("Add New Department");
		lblAddNewDept.setBounds(151, 12, 164, 15);
		contentPane.add(lblAddNewDept);
		
		textField = new JTextField();
		textField.setText("Enter sale price ($xx.xx)");
		textField.setColumns(10);
		textField.setBounds(79, 39, 296, 19);
		contentPane.add(textField);
	}

}
