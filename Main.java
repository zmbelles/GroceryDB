import java.sql.*;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

import jFrameData.errorPopup;

public class Main {

	public static void tableCreation(Statement stmt) throws SQLException {
		Stack<String> stack = new Stack<String>();
		
		String sq1 = "create table members "
				+ "(MID int(11) primary key not null,"
				+ "FName varchar(25) not null,"
				+ "LName varchar(30) not null,"
				+ "DOB date not null,"
				+ "Opt_in boolean not null,"
				+ "Email varchar(45) not null,"
				+ "EID int(11) null,"
				+ "foreign key(EID) REFERENCES employee(EID))";
		stack.push(sq1);
		String sq2 = "create table discount "
				+ "(DiscountID INT PRIMARY KEY NOT NULL,"
				+ "Sale_price decimal(6,2) null,"
				+ "Start_date date not null,"
				+ "end_date date not null,"
				+ "Reg_price decimal(6,2),"
				+ "Is_Members_Only boolean null,"
				+ "PID int,"
				+ "foreign key(PID) REFERENCES inventory(PID))";
		stack.push(sq2);
		String sq3 = "create table shipment_status "
				+ "(OrderID int primary key not null,"
				+ "Qty_on_order int not null,"
				+ "Qty_in_transit int not null,"
				+ "arrival_date date not null,"
				+ "PID int,"
				+ "foreign key(PID) REFERENCES inventory(PID))";
		stack.push(sq3);
		String sq4 = "create table inventory "
				+ "(PID int(11) primary key not null,"
				+ "PName varchar(45) not null,"
				+ "DID int,"
				+ "Price decimal(6,2) not null,"
				+ "QTY int not null,"
				+ "foreign key(DID) REFERENCES department(DID))";
		stack.push(sq4);
		String sq5 = "create table department "
				+ "(DID int(11) primary key not null,"
				+ "DName varchar(45) not null,"
				+ "Extension int not null,"
				+ "Num_of_emps int not null,"
				+ "EID int,"
				+ "foreign key(EID) REFERENCES employee(EID))";
		stack.push(sq5);
		String sq6 = "create table employee "
				+ "(EID int(11) primary key not null,"
				+ "FName varchar(45) not null,"
				+ "LName varchar(45) not null,"
				+ "Department_DID int(11) not null,"
				+ "Salary decimal(8,2) not null,"
				+ "DOB date not null,"
				+ "SSN int not null,"
				+ "S_EID int(11) not null,"
				+ "foreign key(S_EID) REFERENCES employee(EID))";
		stack.push(sq6);
		int sent =0;
		while(!stack.empty()) {
			stmt.execute(stack.pop());
			System.out.println("Statement " + sent + " has finished");
			sent++;
		}
	}
    
/*###############################ADDING DATA ALREADY IN SYSTEM###########################################*/	
	public static void addLegacyData(Statement stmt) throws SQLException {
		Stack<String> stack = new Stack<String>();
		String addEmpdata = "insert into employee "
				+ "values (1, 'Gertrude',    'Bennings',   2, 27000.00, '1949-05-11',  111223333, 1),"
					  + "(2,  'Quandale',     'Dingle',     2, 28500.00, '2012-01-01',  867530900, 1),"
					  + "(3,  'Dairyl',       'Roquline',    1, 280000.00, '1949-05-11', 222334444, 3),"
					  + "(4,  'Damerious',    'Traxoritrix', 2, 17000.00, '1999-04-22',  643254784, 1),"
				      + "(5,  'Hellen',       'Hammond',     3,  4500.00, '1944-09-09',  567262111, 5),"
					  + "(6,  'Jerry',        'Atrick',      1, 75000.00, '1987-07-19',  532831357, 3),"
					  + "(7,  'Michael',      'Schumacher',  3, 42000.00, '1969-01-03',  320945800, 5),"
					  + "(8,  'Pierre',       'Gasly',       4, 32000.00, '1983-07-23',  732748963, 8),"
					  + "(9,  'Krombopulous', 'Michael',     4, 32000.00, '1983-07-23',  320945800, 8),"
					  + "(10, 'Xi',           'Jinping',     5, 18000.00, '1953-06-15',  118259483, 10)"; 
		//stack.push(addEmpdata);
		
		String addInvData = "insert into inventory"
			   + " values (1,  'Tomatoes',              2, 4.49,  11),"
					  + "(2,  'Potatoes',               2, 2.69,  24),"
					  + "(3,  '2% Milk',                4, 3.99,   8),"
					  + "(4,  'Rocky Mountain Oysters', 3, 14.89, 14),"
					  + "(5,  'Swiss Cheese',           1, 8.99,  14),"
					  + "(6,  'Alaskan Bull Worm',      3, 11.99, 18),"
					  + "(7,  'Dan Acosta',             3, 999.12, 1),"
					  + "(8,  'Whole Milk',             4, 3.99,   9),"
					  + "(9,  'Gouda Cheese',           1, 9.12,  18),"
					  + "(10, 'Pork Tenderloin',        3, 34.99,  6),"
					  + "(11, 'Yogurt',                 4, 4.99,  16)";
		//stack.push(addInvData);
		
		String addDeptData = "insert into department"
				 + " values (1, 'Deli',     1111, 2, 3),"
					   + "(2, 'Produce',   2222, 3, 1),"
					   + "(3, 'Butcher',   3333, 2, 5),"
					   + "(4, 'Dairy',     4444, 2, 8),"
					   + "(5, 'Logistics', 5555, 1, 10)";
		//stack.push(addDeptData);
		String addInvStatusData = "INSERT INTO shipment_status"
                + " VALUES ('1', '30', '15', '2022-12-26',1),"
                + "('2', '50', '50', '2022-12-26',2),"
                + "('3', '24', '60', '2023-01-07',3),"
                + "('4', '16', '45', '2023-01-07',4),"
                + "('5', '100', '190', '2023-01-14',5),"
                + "('6', '1', '1', '2023-01-14',6),"
                + "('7', '154', '200', '2023-01-14',7),"
                + "('8', '45', '36', '2023-01-21',8),"
                + "('9', '21', '45', '2023-01-21',9)";
		//stack.push(addInvStatusData);
		
		String addRewardMemData = "INSERT INTO members"
	            + " VALUES ('1', 'Dave', 'Smith', '1957-12-14', 1, 'genericemail@gmail.com', null),"
	            + "('2', 'Chase', 'McDoogle', '1980-08-15', 0, 'coolranch@gmail.com', null),"
	            + "('3', 'Ira', 'Stevenson', '1892-01-20', 1, 'imold@gmail.com', null),"
	            + "('4', 'Jake', 'Cheese', '2022-03-22', 1, 'googoogaga@gmail.com', null),"
	            + "('5', 'Phil', 'Knightly', '1996-12-16', 0, 'doritoslayer@gmail.com', null),"
	            + "('6', 'Gertrude', 'Bennings', '1944-08-22', 1, 'gbennings@acostas.com', 1),"
	            + "('7', 'Quandale', 'Dingle', '1949-05-11', 1, 'qding@acostas.com', 2),"
	            + "('8', 'Dairyl', 'Roquline', '2012-01-01', 1, 'drose@acostas.com', 3),"
	            + "('9', 'Damerious', 'Taxoritrix', '1999-04-22', 1, 'dtrax@acostas.com', 4),"
	            + "('10', 'Hellen', 'Hammond', '1944-09-09', 1, 'hammy222@acostas.com', 5),"
	            + "('11', 'Jerry', 'Atrick', '1987-07-19', 0, 'prunejuice@acostas.com', 6),"
	            + "('12', 'Michael', 'Schumacher', '1969-01-03', 1, 'mschamp@acostas.com', 7),"
	            + "('13', 'Pierre', 'Gasly', '1983-07-23', 1, 'pgassy@acostas.com', 8),"
	            + "('14', 'Krombopulous', 'Michael', '1996-11-16', 0, 'krombo@acostas.com', 9),"
	            + "('15', 'Xi', 'Jinping', '1953-06-15', 1, 'chinarules@acostas.com.cn', 10)";
	    //stack.push(addRewardMemData);
	    
        String addDiscountsData = "INSERT INTO discount"
                + " VALUES (1, '2.50', '2022-11-08', '2022-11-15', '4.49', 1, 1),"
                + "(2, '4.99', '2022-12-09', '2022-12-24', '8.99', 1, 5),"
                + "(3, '1.00', '2022-12-09', '2022-12-25', '9.12', 0, 9)";
        //=====All I changed was the order of the stack push so it was in the correct order=====
        stack.push(addDiscountsData);
        stack.push(addRewardMemData);
        stack.push(addInvStatusData);
        stack.push(addInvData);
        stack.push(addDeptData);
        stack.push(addEmpdata);
        int sent = 0;
		while(!stack.empty()) {
			stmt.execute(stack.pop());
			System.out.println("Statement: " + sent + " has finished");
			sent++;
		}
	}
	
	/*###############################SQL QUERIES OF EACH GROUP MEMBER###########################################*/
	public static boolean performQueries(Statement stmt, int qNum, Connection conn){
		
		try {
			switch(qNum) {
			case 1:
				String sql = "SELECT PName,"
						+ " CASE WHEN DATEDIFF(end_date, NOW()) < 7 AND DATEDIFF(end_date, NOW()) > 0"
						+ " THEN 'Ends in less than a week'"
						+ " WHEN DATEDIFF(end_date, NOW()) < 0"
						+ " THEN 'Already ended'"
						+ " ELSE 'Ends in more than a week'"
						+ " END AS Discount_status"
						+ " FROM discount JOIN inventory USING(PID)";
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					String PName = rs.getString("PName");
					String Discount_Status = rs.getString("Discount_Status");
					System.out.println("Part Name: " + PName + "\n"
							+ "Discount End Status: " + Discount_Status);
				}
				break;
			case 2:
	//			#2 Finding email provider for employees born before 1955
				String sql2 = "SELECT substring_index(email, '@', -1) as email_provider"
						+ " FROM members M JOIN employee E USING(EID)"
						+ " WHERE EID IS NOT NULL AND M.DOB < '1955-01-01'";
				ResultSet rs2 = stmt.executeQuery(sql2);
				
				while(rs2.next()) {
					String email_provider = rs2.getString("email_provider");
					System.out.println("Email Provider: " + email_provider + "\n");
				}
				
				break;
			case 3:
	//			#3 The average salary per department
				String sql3 = "SELECT DName, ROUND(AVG(E.Salary), 2) AS Avg_Salary"
						+ " FROM employee E JOIN department D"
						+ " ON D.DID = E.Department_DID"
						+ " GROUP BY(D.DName)";
				ResultSet rs3 = stmt.executeQuery(sql3);
				
				while(rs3.next()) {
					String DName = rs3.getString("DName");
					String Avg_Salary = rs3.getString("Avg_Salary");
					System.out.println("Department Name: " + DName + "\n"
							+ "Average Salary: " + Avg_Salary);
				}
				
				break;
			case 4:
	//			#4 The average salary of employees depending on if they opt_in to the email
				String sql4 = "SELECT Opt_in, ROUND(AVG(Salary), 2) AS Avg_Salary"
						+ " FROM employee JOIN members USING(EID)"
						+ " WHERE EID IS NOT NULL GROUP BY(Opt_in)";
				ResultSet rs4 = stmt.executeQuery(sql4);
				
				while(rs4.next()) {
					String Opt_in = rs4.getString("Opt_in");
					String Avg_Salary = rs4.getString("Avg_Salary");
					System.out.println("Average Salary: " + Avg_Salary + "\n"
							+ "Opt_in: " + Opt_in);
				}
				
				break;
			case 5:
	//			#5 Item information page
				String sql5 = "SELECT I.PID, I.PName, I.QTY as current_inventory,"
						+ " CONCAT((ROUND((D.Sale_price / D.Reg_price) * 100, 0)), '%') AS percent_off,"
						+ " S.Qty_on_order, S.Qty_in_transit, S.arrival_date"
						+ " FROM shipment_status S LEFT JOIN discount D USING(PID)"
						+ " JOIN inventory I USING(PID)"
						+ " ORDER BY I.PName";
				ResultSet rs5 = stmt.executeQuery(sql5);
				
				while(rs5.next()) {
					String PID = rs5.getString("I.PID");
					String PName = rs5.getString("I.PName");
					String Qty = rs5.getString("current_inventory");
					String Percent_off = rs5.getString("percent_off");
					String Qty_on_order = rs5.getString("S.Qty_on_order");
					String Qty_in_transit = rs5.getString("S.Qty_in_transit");
					String Arrival_date = rs5.getString("S.arrival_date");
					System.out.println("Part ID: " + PID + "\n"
							+ "Part Name: " + PName + "\n"
							+ "Current Quantity: " + Qty + "\n"
							+ "Percent Off: " + Percent_off + "\n"
							+ "On Order: " + Qty_on_order + "\n"
							+ "In Transit: " + Qty_in_transit + "\n"
							+ "Arrival Date: " + Arrival_date);
				}
				break;
			case 6:
	//			#1)birthday calculator
				String sql6 = "select concat(FName, ' ', LName) as 'Member_Name',"
						+ " MID as 'Member_ID',"
						+ " DOB as 'Members_Birthday',"
						+ " curdate() as 'Current_Date',"
						+ " datediff(curdate(), DOB) as days_alive"
						+ " from members"
						+ " where DOB < '2000-01-01'";
				ResultSet rs6 = stmt.executeQuery(sql6);
				
				while(rs6.next()) {
					String member_name = rs6.getString("member_name");
					String member_id = rs6.getString("member_id");
					String members_birthday = rs6.getString("members_birthday");
					String days_alive = rs6.getString("days_alive");
					System.out.println("Member Name: " + member_name + "\n"
							+ "Member ID: " + member_id + "\n"
							+ "Member Birthday: " + members_birthday + "\n"
							+ "Days Alive: " + days_alive + "\n");
				}
				break;
			case 7:
	//			#2)item on sale for all customers
				String sql7 = "select I.PName as product_name, concat('$',I.Price) as original_price,"
						+ " concat('$',D.Sale_price) as discount_price,"
						+ " concat(100 - round((D.Sale_price/I.Price)*100,0), '%') as percentage_off"
						+ " from inventory I join discount D using(PID) where D.Is_Members_Only = 0";
				ResultSet rs7 = stmt.executeQuery(sql7);
				
				while(rs7.next()) {
					String product_name = rs7.getString("product_name");
					String original_price = rs7.getString("original_price");
					String discount_price = rs7.getString("discount_price");
					String percentage_off = rs7.getString("percentage_off");
					System.out.println("Product Name: " + product_name + "\n"
							+ "Original Price: " + original_price + "\n"
							+ "Discount Price: " + discount_price + "\n"
							+ "Percentage Off: " + percentage_off);
				}
				break;
			case 8:
	//			#3)How many product is there for each department and who is the mangager for that department
			String sql8 = "select D.DName as department_name, concat(E.FName, ' ' ,E.LName) as department_manager,"
					+ " concat('$', sum(I.price)) as total_amount"
					+ " from department D join employee E using(EID)"
					+ " join inventory I using(DID) group by DID order by DID";
			ResultSet rs8 = stmt.executeQuery(sql8);
			
			while(rs8.next()) {
				String department_name = rs8.getString("department_name");
				String department_manager = rs8.getString("department_manager");
				String total_amount = rs8.getString("total_amount");
				System.out.println("Department Name: " + department_name + "\n"
						+ "Department Manager: " + department_manager + "\n"
						+ "Total Amount: " + total_amount + "\n");
			}
				break;
			case 9:
//	            #4) Calculate the net amount of all product in store and item on order combined
	            String sql9 = "select PName as product_name, "
	            		+ "concat('$',sum(price*(QTY + Qty_on_order))) as net_amount"
	                    + " from inventory join shipment_status using(PID) group by PID";
	            ResultSet rs9 = stmt.executeQuery(sql9);
	            
	            while(rs9.next()) {
	                String PartName = rs9.getString("product_name");
	                String Net_amount = rs9.getString("net_amount");
	                System.out.println("Part Name: " + PartName + "\n"
	                        + "Net Amount: " + Net_amount + "\n");
	            }
	            break;
			default:
				return true;
			}
			return false;
		}
		catch(SQLException e) {
			return true;
		}
	}
/*#######################################MAIN METHOD########################################################*/
	public static void main(String[] args) {
    	Scanner k = new Scanner(System.in);
    	Connection conn;
    	boolean permission = false;
    	while(!permission) {
    		System.out.println("Please enter your password");
    		String pass = k.next();
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/term", "root", pass);
				permission = true;
				Statement stmt = conn.createStatement();
				
				System.out.println("Have you created the tables yet? (Y or N)");
				char in = k.next().charAt(0);
				if(in=='N' || in=='n') {
					System.out.println("Adding tables.");
					tableCreation(stmt);
				}
				
				System.out.println("Have you added the legacy data yet? (Y or N)");
				in = k.next().charAt(0);
				if(in=='N' || in=='n') {
					System.out.println("Adding legacy data.");
					addLegacyData(stmt);
				}
				System.out.println("What would you like to do?");
				System.out.println("==========================");
				System.out.println("1. Perform queries");
				System.out.println("2. Exit");
				int choice = k.nextInt();
				if(choice==1) {
					boolean error = false;
					boolean finished = false;
					do {
						System.out.println("Which query would you like to run?");
						System.out.println("Please enter a number between 1 and 9");
						int queryNum = k.nextInt();
						error = performQueries(stmt, queryNum, conn);
						if(error) {
							System.out.println("Something went wrong, please try again");
						}
						else {
							System.out.println("Would you like to run any more queries? (Y or N)");
							in = k.next().charAt(0);
							if(in=='N' || in=='n') {
								finished = true;
							}
						}
					} while(error || !finished);
				}
				else {
					System.exit(0);
				}
			}
			catch(Exception e) {
				if(e.toString().contains("already exists")) {
					System.out.println("Liar");
				}
			}
    	}
    	permission = false;
    }
}
