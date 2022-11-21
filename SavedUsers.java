import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.Scanner;

public class SavedUsers {
	
	private static boolean userFound(String thisSavedUser, String userAttempt) {
		Scanner lineScanner = new Scanner(thisSavedUser);
	    lineScanner.useDelimiter(",");
	    while(lineScanner.hasNext()){
	    	String uName = lineScanner.next();
	    	if(uName==userAttempt) {
	    		return true;
	    	}
	    	//dump the part we dont need
	    	lineScanner.next();
	    }
		
		return false;
	}
	
	private static boolean hashFound(String thisSavedUser, String userAttempt) {
		Scanner lineScanner = new Scanner(thisSavedUser);
	    lineScanner.useDelimiter(",");
	    while(lineScanner.hasNext()){
	    	String uName = lineScanner.next();
	    	if(uName==userAttempt) {
	    		return true;
	    	}
	    	//dump the part we dont need
	    	lineScanner.next();
	    }
		
		return false;
	}
	public boolean exists(String name, String hash) throws IOException {
		File users = new File("users.txt");
		File hashes = new File("hashes.txt");
		BufferedReader br = new BufferedReader(new FileReader(users));
		BufferedReader br1 = new BufferedReader(new FileReader(hashes));
		String thisUser;
		String thisHash;
		boolean uFound = false;
		boolean hFound = true;
		/*the number of users will always equal the number of hashes*/
		while(br.readLine() != null) {
			//TODO: change it so both files are read here 
			thisUser = br.readLine();
			thisHash = br1.readLine();
			boolean tmp1 = userFound(thisUser, name);
			boolean tmp2 = (hash == thisHash);
			if(tmp1==true) {uFound = true;}
			if(tmp2==true) {hFound = true;}
		}
		if(uFound && hFound) {return true;}
		return false;
	}
}
