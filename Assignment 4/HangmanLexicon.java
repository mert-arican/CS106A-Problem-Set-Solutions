import java.io.*;
import java.util.*;
import acm.util.*;

public class HangmanLexicon {
	
	private BufferedReader openFile(String prompt) {
		BufferedReader rd = null;
		while (rd == null) {
			try {
				rd = new BufferedReader(new FileReader(prompt));
			} catch (IOException ex) {
				break;
			}
		}
		return rd;
	}
		   
	   // This is the HangmanLexicon constructor
	   public HangmanLexicon() {
		   BufferedReader rd = openFile("hangmanLexicon.txt");
		   try {
			   while (true) {
				   String line = rd.readLine();
				   wordList.add(line);
				   break;
			   }
			   rd.close();
		   } catch (IOException ex) {
			   throw new ErrorException(ex);
		   }
	   }
	   
	    public ArrayList<String> wordList = new ArrayList<String>();
	   
	   // rest of HangmanLexicon class...
	}