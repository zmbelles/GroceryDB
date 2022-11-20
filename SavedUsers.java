import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.Scanner;

public class SavedUsers {
	
	Vector<User> users = new Vector<>();
	
	private static void parseData(String str, int userNum){	
		    Scanner lineScanner = new Scanner(str);
		    lineScanner.useDelimiter(",");
		    while(lineScanner.hasNext()){
		    	User tmp = new User();
		    	tmp.setUName(lineScanner.next());
		    	tmp.setPermissions(Integer.parseInt(lineScanner.next()));
		    }
	}
	/*assumes elevated privileges*/
	
	SavedUsers() throws IOException{
		File file = new File("users.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String tmp;
		int sentinel = 0;
		while(br.readLine() != null) {
			//TODO: change it so both files are read here 
			tmp = br.readLine();
			parseData(tmp, sentinel);
			sentinel++;
		}
	}
	/**
	 * Creates a new user to access the system and adds them to saved users Vector
	 * 
	 * @param uName The users username
	 * @param pass  already hashed password
	 * @param perm  permissions for the user (1 for normal 2 for heightened)
	 */
	public void setUser(String uName, int pass, int perm) {
		User u1 = new User();
		u1.setUName(uName);
		u1.setPass(pass);
		u1.setPermissions(perm);
		users.add(u1);
	}
	public boolean exists(String name) {
		for(int i=0; i<users.size();i++) {
			User tmp = new User();
			users.get(i);
			//TODO: research ArrayList<T>
		}
		
		return false;
	}
}
