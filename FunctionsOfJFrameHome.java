import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class FunctionsOfJFrameHome {
	private static void tableCreation(Statement stmt) throws SQLException {
		
		Stack<String> stack = new Stack<String>();
		
		String sq1 = "create table members "
				+ "(MID int(11) primary key not null,"
				+ "FName varchar(25) not null,"
				+ "LName varchar(30) not null,"
				+ "DOB date not null,"
				+ "Opt_in boolean not null,"
				+ "Email varchar(45) not null,"
				+ "Employee_EID int(11) null,"
				+ "foreign key(Employee_EID) REFERENCES employee(EID))";
		stack.push(sq1);
		String sq2 = "create table discount "
				+ "(DiscountID INT PRIMARY KEY NOT NULL,"
				+ "Sale_price decimal(6,2) null,"
				+ "Start_date date not null,"
				+ "end_date date not null,"
				+ "Reg_price decimal(6,2),"
				+ "Is_Members_Only boolean null,"
				+ "Inventory_PID int,"
				+ "foreign key(Inventory_PID) REFERENCES inventory(PID))";
		stack.push(sq2);
		String sq3 = "create table shipment_status "
				+ "(OrderID int primary key not null,"
				+ "Qty_on_order int not null,"
				+ "Qty_in_transit int not null,"
				+ "arrival_date date not null,"
				+ "Inventory_PID int,"
				+ "foreign key(Inventory_PID) REFERENCES inventory(PID))";
		stack.push(sq3);
		String sq4 = "create table inventory "
				+ "(PID int(11) primary key not null,"
				+ "PName varchar(45) not null,"
				+ "Department_DID int,"
				+ "Price decimal(6,2) not null,"
				+ "QTY int not null,"
				+ "foreign key(Department_DID) REFERENCES department(DID))";
		stack.push(sq4);
		String sq5 = "create table department "
				+ "(DID int(11) primary key not null,"
				+ "DName varchar(45) not null,"
				+ "Extension int not null,"
				+ "Num_of_emps int not null,"
				+ "Mgr_S_EID int,"
				+ "foreign key(Mgr_S_EID) REFERENCES employee(EID))";
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
	private static void addLegacyData(Statement stmt) throws SQLException {
		Stack<String> stack = new Stack<String>();
		
		String addEmpdata = "insert into employee "
				+ "values (1,  'Gertrude',     'Bennings',   2, 27000.00, '1949-05-11',  111223333, 1),"
					  + "(2,  'Jim',          'Johnson',     2, 28500.00, '2012-01-01',  867530900, 1),"
					  + "(3,  'Dairyl',       'Roquline',    1, 280000.00, '1949-05-11', 222334444, 3),"
					  + "(4,  'Damerious',    'Traxoritrix', 2, 17000.00, '1999-04-22',  643254784, 1),"
				      + "(5,  'Hellen',       'Hammond',     3,  4500.00, '1944-09-09',  567262111, 5),"
					  + "(6,  'Jerry',        'Atrick',      1, 75000.00, '1987-07-19',  532831357, 3),"
					  + "(7,  'Michael',      'Schumacher',  3, 42000.00, '1969-01-03',  320945800, 5),"
					  + "(8,  'Pierre',       'Gasly',       4, 32000.00, '1983-07-23',  732748963, 8),"
					  + "(9,  'Krombopulous', 'Michael',     4, 32000.00, '1983-07-23',  320945800, 8),"
					  + "(10, 'Xi',           'Jinping',     5, 18000.00, '1953-06-15',  118259483, 10)"; 
		stack.push(addEmpdata);
		
		String addInvData = "insert into inventory"
			   + " values (1,  'Tomatoes',               2, 4.49,  11),"
					  + "(2,  'Potatoes',               2, 2.69,  24),"
					  + "(3,  '2% Milk',                4, 3.99,   8),"
					  + "(4,  'Rocky Mountain Oysters', 3, 14.89, 14),"
					  + "(5,  'Swiss Cheese',           1, 8.99,  14),"
					  + "(6,  'Alaskan Bull Worm',      3, 11.99, 18),"
					  + "(7,  'Lard (40 Gal)',          3, 999.12, 1),"
					  + "(8,  'Whole Milk',             4, 3.99,   9),"
					  + "(9,  'Gouda Cheese',            1, 12.99, 18),"
					  + "(10, 'Pork Tenderloin',        3, 34.99,  6),"
					  + "(11, 'Yogurt',                 4, 4.99,  16)";
		stack.push(addInvData);
		
		String addDeptData = "insert into department"
				 + " values(1, 'Deli',     1111, 2, NULL),"
					   + "(2, 'Produce',   2222, 3, NULL),"
					   + "(3, 'Butcher',   3333, 2, NULL),"
					   + "(4, 'Dairy',     4444, 2, NULL),"
					   + "(5, 'Logistics', 5555, 1, NULL)";
		stack.push(addDeptData);
		
		String addInvStatusData = "INSERT INTO shipment_status"
                + " VALUES ('1', '30', '15', '2022-12-26',NULL),"
                + "('2', '50', '50', '2022-12-26',NULL),"
                + "('3', '24', '60', '2023-01-07',NULL),"
                + "('4', '16', '45', '2023-01-07',NULL),"
                + "('5', '100', '190', '2023-01-14',NULL),"
                + "('6', '1', '1', '2023-01-14',NULL),"
                + "('7', '154', '200', '2023-01-14',NULL),"
                + "('8', '45', '36', '2023-01-21',NULL),"
                + "('9', '21', '45', '2023-01-21',NULL)";
		stack.push(addInvStatusData);
		
		String addRewardMemData = "INSERT INTO members"
	            + " VALUES ('1', 'Dave', 'Smith', '1957-12-14', 1, 'genericemail@gmail.com', null),"
	            + "('2', 'Chase', 'McDoogle', '1980-08-15', 0, 'coolranch@gmail.com', null),"
	            + "('3', 'Ira', 'Stevenson', '1892-01-20', 1, 'imold@gmail.com', null),"
	            + "('4', 'Jake', 'Cheese', '2022-03-22', 1, 'googoogaga@gmail.com', null),"
	            + "('5', 'Phil', 'Knightly', '1996-12-16', 0, 'doritoslayer@gmail.com', null),"
	            + "('6', 'Gertrude', 'Bennings', '1944-08-22', 1, 'gbennings@gmail.com', null),"
	            + "('7', 'Quandale', 'Dingle', '1949-05-11', 1, 'qding@gmail.com', null),"
	            + "('8', 'Dairyl', 'Roquline', '2012-01-01', 1, 'drose@msn.com', null),"
	            + "('9', 'Damerious', 'Taxoritrix', '1999-04-22', 1, 'dtrax@msn.com', null),"
	            + "('10', 'Hellen', 'Hammond', '1944-09-09', 1, 'hammy222@gmail.com', null),"
	            + "('11', 'Jerry', 'Atrick', '1987-07-19', 0, 'prunejuice@gmail.com', null),"
	            + "('12', 'Michael', 'Schumacher', '1969-01-03', 1, 'mikeshou@msn.com', null),"
	            + "('13', 'Pierre', 'Gasly', '1983-07-23', 1, 'pgassy@gmail.com', null),"
	            + "('14', 'Krombopulous', 'Michael', '1996-11-16', 0, 'krombo@msn.com', null),"
	            + "('15', 'Xi', 'Jinping', '1953-06-15', 1, 'chinarules@msn.com', null)";
	    stack.push(addRewardMemData);
	    
        String addDiscountsData = "INSERT INTO discount"
                + " VALUES (1, '2.50', '2022-11-08', '2022-11-15', '4.49', 1, null),"
                + "(2, '4.99', '2022-12-09', '2022-12-24', '8.99', 1, null),"
                + "(3, '1.00', '2022-12-09', '2022-12-25', '9.12', 0, null)";
        stack.push(addDiscountsData);
        
        int sent = 0;
		while(!stack.empty()) {
			stmt.execute(stack.pop());
			System.out.println("Statement: " + sent + " has finished");
			sent++;
		}
	}
	
/*################################CONNECTING TO DATABASE############################################*/
	public static int Connect(int formulaNum) {
    	Scanner k = new Scanner(System.in);
    	int errorCode = -1;
    	Connection conn;
    	boolean permission = false;
    	
    	/*attempt to connect to the database*/
    	while(!permission) {
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/term", "root", "!Fall2022");
				permission = true;
				Statement stmt = conn.createStatement();
				errorCode = 1;
				if(formulaNum==1) {
					tableCreation(stmt);
				}
				else if(formulaNum==2) {
					addLegacyData(stmt);
				}
				
			}
			catch(Exception e) {
				//error code -1 = connection error
				//error code 1 = SQLException
				return errorCode;
			}
    	}
    	permission = false;
    	return 0;
    }
}
