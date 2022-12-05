package jFrameData;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class JFrameQueryDisplay extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel();
	private Stack<String> stack = new Stack<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameQueryDisplay frame = new JFrameQueryDisplay();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private boolean performQueries(String query, int num_rows) {
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/term", "root", "!Fall2022");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			Vector<String> vec = new Vector<String>();
			for(int i=0;i<num_rows;i++) {
				vec.add(stack.pop());
			}
			Object[] columns = new Object[vec.size()];
			Object[] row = new Object[num_rows];
			while(rs.next()) {
				for(int i=0;i<num_rows;i++) {
					String thisElement = vec.elementAt(i);
					row[i] = rs.getString(thisElement);
					columns[i] = vec.elementAt(i);
				}
				model.setColumnIdentifiers(columns);
				model.addRow(row);
			}
 			table.setModel(model);
			return false;
		} catch (SQLException e) {
			return true;
		}
	}
	/**
	 * Create the frame.
	 */
	public JFrameQueryDisplay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(25, 134, 576, 242);
		contentPane.add(table);
		
		JButton btnQuery1 = new JButton("Query 1");
		btnQuery1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String q1 = "SELECT DName, ROUND(AVG(E.Salary), 2) AS 'Average Salary' "
						+ "FROM employee E "
						+ "JOIN department D "
						+ "ON D.DID = E.Department_DID "
						+ "GROUP BY(D.DName)";
				stack.push("Average Salary");
				stack.push("DName");
				boolean error = performQueries(q1,2);
				if(!error) {
					
				}
			}
		});
		btnQuery1.setBounds(63, 11, 89, 23);
		contentPane.add(btnQuery1);
		
		JButton btnQuery2 = new JButton("Query 2");
		btnQuery2.setBounds(184, 11, 89, 23);
		contentPane.add(btnQuery2);
		
		JButton btnQuery3 = new JButton("Query 3");
		btnQuery3.setBounds(311, 11, 89, 23);
		contentPane.add(btnQuery3);
		
		JButton btnQuery4 = new JButton("Query 4");
		btnQuery4.setBounds(63, 45, 89, 23);
		contentPane.add(btnQuery4);
		
		JButton btnQuery5 = new JButton("Query 5");
		btnQuery5.setBounds(184, 45, 89, 23);
		contentPane.add(btnQuery5);
		
		JButton btnQuery6 = new JButton("Query 6");
		btnQuery6.setBounds(311, 45, 89, 23);
		contentPane.add(btnQuery6);
		
		JButton btnQuery7 = new JButton("Query 7");
		btnQuery7.setBounds(63, 79, 89, 23);
		contentPane.add(btnQuery7);
		
		JButton btnQuery8 = new JButton("Query 8");
		btnQuery8.setBounds(184, 79, 89, 23);
		contentPane.add(btnQuery8);
		
		JButton btnQuery9 = new JButton("Query 9");
		btnQuery9.setBounds(311, 79, 89, 23);
		contentPane.add(btnQuery9);
	}
}
