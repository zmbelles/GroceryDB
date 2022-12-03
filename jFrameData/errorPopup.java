package jFrameData;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class errorPopup extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblErrorUnableTo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			errorPopup dialog = new errorPopup();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setErrorText(String errorMessage) {
		String newErrorMessage = "ERROR: ";
		newErrorMessage += errorMessage;
		lblErrorUnableTo.setText(newErrorMessage);
	}
	/**
	 * Create the dialog.
	 */
	public errorPopup() {
		setBounds(100, 100, 435, 188);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblErrorUnableTo = new JLabel("ERROR: UNABLE TO CONNECT TO DATABASE");
		lblErrorUnableTo.setFont(new Font("DialogInput", Font.BOLD, 10));
		lblErrorUnableTo.setBounds(26, 24, 383, 81);
		contentPanel.add(lblErrorUnableTo);
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				errorPopup.this.setVisible(false);
				errorPopup.this.setEnabled(false);
				errorPopup.this.dispatchEvent(new WindowEvent(
						errorPopup.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnOk.setBounds(158, 98, 117, 25);
		contentPanel.add(btnOk);
	}
}
