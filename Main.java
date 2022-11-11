import java.sql.*;
import java.util.Scanner;
import java.util.Vector;

public class Main {
	public void createTables(Connection conn) {
	}
    public static void main(String[] args) {
    	Scanner k = new Scanner(System.in);
    	Connection conn;
    	boolean permission = false;
    	while(!permission) {
    		System.out.print("Please enter your password");
    		String pass = k.next();
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", pass);
				permission = true;
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
				Statement stmt = conn.createStatement();
				while(!stmts.isEmpty()) {
					stmt.execute(stmts.firstElement());
					stmts.remove(0);
				}
				conn.close();
			}
			catch(Exception e) {
				System.out.println("Error: " + e);
			}
    	}
    	permission = false;
    }
}
