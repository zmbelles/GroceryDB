package jFrameData;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Success extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Success dialog = new Success();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Success() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTaskCompletedSuccessfully = new JLabel("Task Completed Successfully!");
			lblTaskCompletedSuccessfully.setFont(new Font("Dialog", Font.BOLD, 18));
			lblTaskCompletedSuccessfully.setBounds(77, 63, 319, 65);
			contentPanel.add(lblTaskCompletedSuccessfully);
		}
		{
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Success.this.setVisible(false);
					Success.this.setEnabled(false);
					Success.this.dispatchEvent(new WindowEvent(
							Success.this, WindowEvent.WINDOW_CLOSING));
				}
			});
			okButton.setActionCommand("OK");
			okButton.setBounds(180, 127, 87, 25);
			contentPanel.add(okButton);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}

}
