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
	    	if(uName.equals(userAttempt)) {
	    		return true;
	    	}
	    	//dump the part we dont need
	    	lineScanner.next();
	    }
		lineScanner.close();
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
		do {
			//TODO: change it so both files are read here 
			thisUser = br.readLine();
			thisHash = br1.readLine();
			String hashAttempt = Integer.toString(hash.hashCode());
			
			boolean tmp1 = userFound(thisUser, name);
			boolean tmp2 = (thisHash.equals(hashAttempt));
			if(tmp1==true) {uFound = true;}
			if(tmp2==true) {hFound = true;}
		}while(br.readLine() != null);
		if(uFound && hFound) {
			br1.close();
			br.close();
			return true;
		}
		br1.close();
		br.close();
		return false;
	}
}
