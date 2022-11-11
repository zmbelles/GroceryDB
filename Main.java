import java.sql.*;
import java.util.Scanner;
import java.util.Vector;

public class Main {
	public static void addLegacyData(Statement stmt) throws SQLException {
		Vector<String> stmts = new Vector<>();
		String addEmpdata = "insert into employee"
				+ "values(1, 'Gertrude', 'Bennings',   2, 27000, '1949-05-11',  111223333,  6,  4),"
				+ "(2,  'Jim',          'Johnson',     2, 28500, '2012-01-01',  867530900,  7,  4),"
				+ "(3,  'Dairyl',       'Roquline',    1, 280000, '1949-05-11', 222334444,  7,  4),"
				+ "(4,  'Damerious',    'Traxoritrix', 2, 17000, '1999-04-22',  643254784,  9,  4),"
				+ "(5,  'Hellen',       'Hammond',     3,  4500, '1944-09-09',  567262111, 10,  7),"
				+ "(6,  'Jerry',        'Atrick',      1, 75000, '1987-07-19',  532831357, 11,  3),"
				+ "(7,  'Michael',      'Schumacher',  3, 42000, '1969-01-03',  320945800, 12,  7),"
				+ "(8,  'Pierre',       'Gasly',       4, 32000, '1983-07-23',  732748963, 13,  8),"
				+ "(9,  'Krombopulous', 'Michael',     4, 32000, '1983-07-23',  320945800, 14,  8),"
				+ "(10, 'Xi',           'Jinping',     5, 18000, '1953-06-15',  118259483, 15, 10)"; 
		stmts.add(addEmpdata);
		String addInvData = "insert into inventory"
				+ "values('Tomatoes',               1, 2, 4.49,   11),"
					  + "('Potatoes',               2, 2, 2.69,   24),"
					  + "('2% Milk',                3, 4, 3.99,    8),"
					  + "('Rocky Mountain Oysters', 4, 3, 14.89,  14),"
					  + "('Swiss Cheese',           5, 1, 8.99,   14),"
					  + "('Alaskan Bull Worm',      6, 3, 11.99,  18),"
					  + "('Dan Acosta,              7, 3, 1115.12, 1),"
					  + "('Whole Milk',             8, 4, 3.99,    9),"
					  + "('Gouda Cheese'            9, 1, 12.99,  18),"
					  + "('Pork Tenderloin',       10, 3, 34.99,   6),"
					  + "'Yogurt',                 11, 4, 4.99,   16)";
		stmts.add(addInvData);
		String addDeptData = "insert into department"
				+ "values(1, 'Deli',       3, 1111, 2),"
					   + "2, 'Produce',    4, 2222, 3),"
					   + "3, 'Butcher',    7, 3333, 2),"
					   + "4, 'Dairy',      8, 4444, 2),"
					   + "5, 'Logistics', 10, 5555, 1)";
		stmts.add(addDeptData);
		while(!stmts.isEmpty()) {
			stmt.execute(stmts.firstElement());
			stmts.remove(0);
		}
	}
	
	public static void tableCreation(Statement stmt) throws SQLException {
		Vector<String> allStmts = new Vector<>();
		String sql = "create table employee"
				+ "(EID int(11) primary key not null,"
				+ "FName varchar(45) not null,"
				+ "LName varchar(45) not null,"
				+ "Salary decimal(6,2) not null, "
				+ "DOB date not null,"
				+ "SSN int not null,"
				+ "S_EID int(11) not null,"
				+ "foreign key(S_EID) REFERENCES employee(EID))";
		allStmts.add(sql);
		String sq2 = "create table members"
				+ "(MID int(11) primary key not null,"
				+ "DOB date not null,"
				+ "Opt_in boolean not null,"
				+ "Email varchar(45) not null,"
				+ "Employee_EID int(11) not null,"
				+ "foreign key(Employee_EID) REFERENCES employee(EID))";
		allStmts.add(sq2);
		String sq3 = "create table department"
				+ "(DID int(11) primary key not null,"
				+ "DName varchar(45) not null,"
				+ "Extension int not null,"
				+ "Num_of_emps int not null,"
				+ "Mgr_S_EID int not null,"
				+ "foreign key(Mgr_S_EID) REFERENCES employee(EID))";
		allStmts.add(sq3);
		String sq4 = "create table inventory"
				+ "(PID int(11) primary key not null,"
				+ "Price decimal(4,2) not null,"
				+ "QTY int not null,"
				+ "Department_DID int not null,"
				+ "foreign key(Department_DID) REFERENCES department(DID))";
		allStmts.add(sq4);
		String sq5 = "create table discount"
				+ "(Sale_price decimal(4,2) not null,"
				+ "Start_date date not null,"
				+ "end_date date not null,"
				+ "Is_Members_Only boolean not null,"
				+ "New_price decimal(4,2),"
				+ "Inventory_PID int not null,"
				+ "foreign key(Inventory_PID) REFERENCES inventory(PID))";
		allStmts.add(sq5);
		String sq6 = "create table shipment_status"
				+ "(Qty_on_order int not null,"
				+ "Qty_in_transit int not null,"
				+ "arrival_date date not null,"
				+ "Inventory_PID int not null,"
				+ "foreign key(Inventory_PID) REFERENCES inventory(PID))";
		allStmts.add(sq6);
		while(!allStmts.isEmpty()) {
			stmt.execute(allStmts.firstElement());
			allStmts.remove(0);
		}
	}
    
	public static void performQueries(Statement stmt) throws SQLException {
		//TODO: write and implement SQL Queries
	}
	
	public static void addData(Statement stmt) throws SQLException {
		
	}
	public static void main(String[] args) {
    	Scanner k = new Scanner(System.in);
    	Connection conn;
    	boolean permission = false;
    	while(!permission) {
    		System.out.print("Please enter your password");
    		String pass = k.next();
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/term", "root", pass);
				permission = true;
				Statement stmt = conn.createStatement();
				
				System.out.println("Have you created the tables yet? (Y or N");
				String in = k.next();
				if(in=="N" || in=="n") {
					System.out.println("Adding tables.");
					tableCreation(stmt);
				}
				
				System.out.println("Have you added the legacy data yet? (Y or N)");
				in = k.next();
				if(in=="N" || in=="n") {
					System.out.println("Adding legacy data.");
					addLegacyData(stmt);
				}
				System.out.println("What would you like to do?");
				System.out.println("==========================");
				System.out.println("1. Perform queries");
				System.out.println("2. add data");
				System.out.println("3. Exit");
				int choice = k.nextInt();
				if(choice==1) {
					performQueries(stmt);
				}
				else if(choice == 2) {
					
				}
				
			}
			catch(Exception e) {
				System.out.println("Error: " + e);
			}
    	}
    	permission = false;
    }
}
