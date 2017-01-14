
import java.io.*;
import cs1.Keyboard;

public class JavaVisualizer {

	int openParenCount = 0;
	int closeParenCount = 0;
	int totalParenCount;
	boolean foundFOR;
	
	public void parseIt(){
		
		System.out.println("\nCurrently, this program will only output \n" +
							"the for loop parameters. Please input a file \n" +
							"in the same directory as this program.\n" +
							"\n *** PLEASE ONLY INPUT MOO.JAVA FOR NOW ****");
		System.out.print("File to read in: " );
		String inputFile = Keyboard.readString();
		System.out.println("");
		System.out.println("~~~~~~~~~FINDING FOR LOOPS~~~~~~~~~~~");
		
		try{
			File file = new File (ClassLoader.getSystemResource(inputFile).getFile());
		}
		catch (Exception e){
			System.out.println("\nFile Not Detected \n" +
							   "May have not input a file in the same directory.\n");
		}

		try {
			FileInputStream fstream = new FileInputStream(inputFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			String strLine;
			foundFOR = false;
			
			//Read File Line By Line
			while ((strLine = br.readLine()) != null) {
			
				strLine = strLine.trim();
				loopDetector("for", strLine);
				printForParams(strLine);
					
			}// end while strLine = br.readLine

			//Close the input stream
			br.close();
		}
		catch (IOException e){
			System.out.println("Fail");
		}
	}
	
	public int countChar (String str, String character) {
		int counter = 0;
			for( int i = 0; i < str.length(); i++ ) {
    			if( str.substring(i,i+1).equals( character ) ) {
        		counter++;
   			} 
		}
		return counter;
	} // end countChar
	
	public void printForParams( String strLine ){
		String s = strLine;
		if (foundFOR){
			System.out.println ( strLine );
			
			String init = s.substring(s.indexOf("(")+1, s.indexOf(";"));
		   	System.out.println("initialization: " + init.trim());
		   	
		   	s = s.substring(s.indexOf(";")+1);
		   	String bool = s.substring(0, s.indexOf(";"));
		   	System.out.println("boolean: " + bool.trim());
		   	
		   	s = s.substring(s.indexOf(";")+1);
		   	String update = s.substring(0, s.indexOf(")"));
		   	System.out.println("update: " + update.trim());
		   	
		   	System.out.println("");
		   	
			openParenCount += countChar(strLine, "(" );
			closeParenCount += countChar(strLine, ")" );

			if (openParenCount == closeParenCount) {
				foundFOR = false;
			}
		}
	} // end printForParams
	
	public void loopDetector( String keyword, String strLine ){
		if ( strLine.indexOf(keyword) > -1 ){
			foundFOR = true;
		}	
	}
					

	
}
