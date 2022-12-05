package jFrameData;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import jFrameLogin.JFrameHome;

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
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Color;

public class JFrameQueryDisplay extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model = new DefaultTableModel();
	private Stack<String> stack = new Stack<String>();
	private JTable table;

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
	private void clearTable() {
		model.setRowCount(0);
	}
	private boolean performQueries(String query, int num_rows) {
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/term", "root", "!Fall2022");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			Vector<String> vec = new Vector<String>();
			Object[] row = new Object[num_rows];
			Object[] columns = new Object[num_rows];
			int j=0;
			//first populate columns
			while(j<num_rows) {
				vec.add(stack.pop());
				String thisElement = vec.elementAt(j);
				columns[j]=thisElement;
				model.setColumnIdentifiers(columns);
				j++;
			}
			while(rs.next()) {
				
				for(int i=0;i<num_rows;i++) {
					//populate with specified data
					String thisElement = vec.elementAt(i);
					row[i] = rs.getString(thisElement);
				
					
				}
				//if there was data and this isnt the first row
				if(!rs.wasNull() && j!=0) {
					model.addRow(row);
				}
			}
			
			//set the model to the table and return errorcode of false
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
		contentPane.setBackground(new Color(0, 0, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblQueryName = new JLabel("");
		lblQueryName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQueryName.setBackground(new Color(0, 0, 255));
		lblQueryName.setForeground(new Color(0,0,0));
		lblQueryName.setBounds(25, 109, 576, 14);
		contentPane.add(lblQueryName);
		
		JButton btnQuery1 = new JButton("Query 1");
		btnQuery1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuery1.setBackground(new Color(0, 0, 255));
		btnQuery1.setForeground(new Color(0, 0, 0));
		btnQuery1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTable();
				String q1 = "SELECT DName, ROUND(AVG(E.Salary), 2) AS 'Average Salary' "
						+ "FROM employee E "
						+ "JOIN department D "
						+ "ON D.DID = E.Department_DID "
						+ "GROUP BY(D.DName)";
				stack.push("Average Salary");
				stack.push("DName");
				boolean error = performQueries(q1,2);
				if(!error) {
					lblQueryName.setText("Average Salary from each department");
				}
			}
		});
		btnQuery1.setBounds(63, 11, 89, 23);
		contentPane.add(btnQuery1);
		
		JButton btnQuery2 = new JButton("Query 2");
		btnQuery2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuery2.setBackground(new Color(0, 0, 255));
		btnQuery2.setForeground(new Color(0, 0, 0));
		btnQuery2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTable();
				String sql = "SELECT PName,"
						+ " CASE WHEN DATEDIFF(end_date, NOW()) < 7 AND DATEDIFF(end_date, NOW()) > 0"
						+ " THEN 'Ends in less than a week'"
						+ " WHEN DATEDIFF(end_date, NOW()) < 0"
						+ " THEN 'Already ended'"
						+ " ELSE 'Ends in more than a week'"
						+ " END AS Discount_status"
						+ " FROM discount d, inventory i"
						+ " WHERE d.PID = i.PID";
				stack.push("Discount_Status");
				stack.push("PName");
				boolean error = performQueries(sql,2);
				if(!error) {
					lblQueryName.setText("Finding the discount ending time status");
				}
			}
		});
		btnQuery2.setBounds(184, 11, 89, 23);
		contentPane.add(btnQuery2);
		
		JButton btnQuery3 = new JButton("Query 3");
		btnQuery3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuery3.setBackground(new Color(0, 0, 255));
		btnQuery3.setForeground(new Color(0, 0, 0));
		btnQuery3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTable();
				String sql3 = "SELECT DName, ROUND(AVG(E.Salary), 2) AS Avg_Salary"
						+ " FROM employee E JOIN department D"
						+ " ON D.DID = E.Department_DID"
						+ " GROUP BY(D.DName)";
				stack.push("Avg_Salary");
				stack.push("DName");
				boolean error = performQueries(sql3,2);
				if(!error) {
					lblQueryName.setText("Average Salary from each department");
				}
			}
		});
		btnQuery3.setBounds(311, 11, 89, 23);
		contentPane.add(btnQuery3);
		
		JButton btnQuery4 = new JButton("Query 4");
		btnQuery4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuery4.setBackground(new Color(0, 0, 255));
		btnQuery4.setForeground(new Color(0, 0, 0));
		btnQuery4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTable();
				String sql4 = "SELECT Opt_in, ROUND(AVG(Salary), 2) AS Avg_Salary"
						+ " FROM employee JOIN members USING(EID)"
						+ " WHERE EID IS NOT NULL GROUP BY(Opt_in)";
				stack.push("Avg_Salary");
				stack.push("Opt_in");
				boolean error = performQueries(sql4,2);
				if(!error) {
					lblQueryName.setText("The average salary of employees depending on if they opt_in to the email");
				}
			}
		});
		btnQuery4.setBounds(63, 45, 89, 23);
		contentPane.add(btnQuery4);
		
		JButton btnQuery5 = new JButton("Query 5");
		btnQuery5.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuery5.setBackground(new Color(0, 0, 255));
		btnQuery5.setForeground(new Color(0, 0, 0));
		btnQuery5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTable();
				String sql5 = "SELECT I.PID, I.PName, I.QTY as current_inventory,"
						+ " CONCAT((ROUND((D.Sale_price / D.Reg_price) * 100, 0)), '%') AS percent_off,"
						+ " S.Qty_on_order, S.Qty_in_transit, S.arrival_date"
						+ " FROM shipment_status S LEFT JOIN discount D USING(PID)"
						+ " JOIN inventory I USING(PID)"
						+ " ORDER BY I.PName";
				stack.push("arrival_date");
				stack.push("Qty_in_transit");
				stack.push("Qty_on_order");
				stack.push("percent_off");
				stack.push("current_inventory");
				stack.push("PName");
				stack.push("PID");
				
				boolean error = performQueries(sql5,7);
				if(!error) {
					lblQueryName.setText("Item information page");
				}
			}
		});
		btnQuery5.setBounds(184, 45, 89, 23);
		contentPane.add(btnQuery5);
		
		JButton btnQuery6 = new JButton("Query 6");
		btnQuery6.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuery6.setBackground(new Color(0, 0, 255));
		btnQuery6.setForeground(new Color(0, 0, 0));
		btnQuery6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTable();
				String sql6 = "select concat(FName, ' ', LName) as 'Member_Name',"
						+ " MID as 'Member_ID',"
						+ " DOB as 'Members_Birthday',"
						+ " curdate() as 'Current_Date',"
						+ " datediff(curdate(), DOB) as days_alive"
						+ " from members"
						+ " where DOB < '2000-01-01'";
				stack.push("days_alive");
				stack.push("current_date");
				stack.push("Members_Birthday");
				stack.push("Member_ID");
				stack.push("Member_Name");
				boolean error = performQueries(sql6,5);
				if(!error) {
					lblQueryName.setText("birthday calculator");
				}
			}
		});
		btnQuery6.setBounds(311, 45, 89, 23);
		contentPane.add(btnQuery6);
		
		JButton btnQuery7 = new JButton("Query 7");
		btnQuery7.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuery7.setBackground(new Color(0, 0, 255));
		btnQuery7.setForeground(new Color(0, 0, 0));
		btnQuery7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTable();
				String sql7 = "select I.PName as product_name, concat('$',I.Price) as original_price,"
						+ " concat('$',D.Sale_price) as discount_price,"
						+ " concat(100 - round((D.Sale_price/I.Price)*100,0), '%') as percentage_off"
						+ " from inventory I join discount D using(PID) where D.Is_Members_Only = 0";
				stack.push("discount_price");
				stack.push("original_price");
				stack.push("product_name");
				boolean error = performQueries(sql7,3);
				if(!error) {
					lblQueryName.setText("items on sale for all customers");
				}
			}
		});
		btnQuery7.setBounds(63, 79, 89, 23);
		contentPane.add(btnQuery7);
		
		JButton btnQuery8 = new JButton("Query 8");
		btnQuery8.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuery8.setBackground(new Color(0, 0, 255));
		btnQuery8.setForeground(new Color(0, 0, 0));
		btnQuery8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTable();
				String sql8 = "select D.DName as department_name, concat(E.FName, ' ' ,E.LName) as department_manager,"
						+ " concat('$', sum(I.price)) as total_amount"
						+ " from department D join employee E using(EID)"
						+ " join inventory I using(DID) group by DID order by DID";
				stack.push("total_amount");
				stack.push("department_manager");
				stack.push("Department_name");
				boolean error = performQueries(sql8,3);
				if(!error) {
					lblQueryName.setText("How many product there are for each department and who is the mangager for that department");
				}
			}
		});
		btnQuery8.setBounds(184, 79, 89, 23);
		contentPane.add(btnQuery8);
		
		JButton btnQuery9 = new JButton("Query 9");
		btnQuery9.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuery9.setBackground(new Color(0, 0, 255));
		btnQuery9.setForeground(new Color(0, 0, 0));
		btnQuery9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTable();
				String sql9 = "create or replace view net_product_summary"
						+ " as select PName as product_name, concat('$',sum(price*(QTY + Qty_on_order))) as net_amount"
						+ " from inventory join shipment_status using(PID) group by PID";
				stack.push("department_manager");
				stack.push("Department_name");
				boolean error = performQueries(sql9,2);
				if(!error) {
					lblQueryName.setText("Using Views, calculate the net amount of all product in store and item on order combined");
				}
				else {
					errorPopup err = new errorPopup();
					err.setErrorText("VIEWS DON'T WORK WITH THIS INTERFACE. PLEASE RUN THROUGH MAIN");
					err.setEnabled(true);
					err.setVisible(true);
				}
			}
		});
		btnQuery9.setBounds(311, 79, 89, 23);
		contentPane.add(btnQuery9);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 131, 576, 273);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(255, 0, 0));
		scrollPane.setViewportView(table);
		
		JButton btn_back = new JButton("back");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameQueryDisplay.this.setEnabled(false);
				JFrameQueryDisplay.this.setVisible(false);
				
				JFrameHome home = new JFrameHome();
				home.setEnabled(true);
				home.setVisible(true);
			}
		});
		btn_back.setBackground(new Color(0, 0, 255));
		btn_back.setForeground(new Color(0, 0, 0));
		btn_back.setFont(new Font("Tahoma", Font.BOLD, 17));
		btn_back.setBounds(457, 11, 103, 87);
		contentPane.add(btn_back);
		
	}
}
