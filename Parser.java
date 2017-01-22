
import java.io.*;
import cs1.Keyboard;
import java.util.*;

public class Parser {

	private static String text = "";
	private static int curlyBraceCnt = 0;
	private static int parenCnt = 0;
    private static Flowchart blarg = new Flowchart(true);
    private static String OS = System.getProperty("os.name").toLowerCase();
	
	public void  parseIt(){
		System.out.println("\nCurrently, this program will only output \n" +
							"the for loop parameters. Please input a file \n" +
							"in the same directory as this program.\n" +
							"\n *** PLEASE ONLY INPUT Moo.java FOR NOW ****");
		System.out.print("File to read in: " );
		String inputFile = Keyboard.readString();
		System.out.println("");
		System.out.println("~~~~~~~~~FOR LOOP VIZUALIZER~~~~~~~~~~~");
		
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
				
			//Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				if (strLine.indexOf("//") > -1){
					strLine = strLine.substring(0, strLine.indexOf("//"));
				}
				text += strLine.trim();
				
			}// end while strLine = br.readLine
			
			//Close the input stream
			br.close();
		}
		catch (IOException e){
			System.out.println("Fail");
		}
		
		commentDeletor();
		System.out.println("");
		
		ArrayList<Integer> indicies = indexDetector(text, "for");
		int row = 0;
		boolean isLastLoop;
		for (int i = 0; i < indicies.size(); i++){
		    // getting FOR LOOP PARAMS
			String[] forInfo = loopSeparator(text, indicies.get(i));
			if (i != indicies.size()-1){
				isLastLoop = false;
			} 
			else {
				isLastLoop = true;
			}
			// creating FOR LOOP VISUALIZER
			row = blarg.assembleLoop(isLastLoop, row, 9, forInfo[0], forInfo[1], forInfo[2], forInfo[3]);	
			
		}
		blarg.print();
	}// main

	/*
		canUseAnsi(): determines whether os can support ANSI encodings
	*/
	public boolean canUseAnsi(){
		return (OS.indexOf("mac") >= 0) || (OS.indexOf("nix") >= 0);
	}
	/*
		indexDetector (String, String): takes in string version of file and loop keyword (i.e. "for")
		Finds all indicies in string where keyword starts
		Returns indicies in an ArrayList
	*/
	public ArrayList<Integer> indexDetector (String s, String keyword){
		ArrayList<Integer> indexList = new ArrayList<Integer>();
		int fromIndex = 0;
		while ((s.indexOf(keyword,fromIndex) > -1) && 
			   (s.indexOf("\"", fromIndex) > -1)){

			/*
				This detects the beginning and end of any strings within text
				Prevents detection of the "for" keyword within Strings
			*/
			if (s.indexOf("\"", fromIndex) < s.indexOf(keyword, fromIndex) &&
				s.indexOf("\"", fromIndex) > -1 ) {
				for (int i = s.indexOf("\"", fromIndex) + 1; i < s.length() - 2; i++){
					if ((s.substring(i,i+1)).equals("\"")){
						fromIndex = i+1;
						break;
					}
				}
			}
			
				
			int index = s.indexOf(keyword, fromIndex);
			
			// needed to when "for" keyword is not detected
			if (index != -1){
				indexList.add(index);
			}

			if (s.indexOf(keyword, fromIndex) != -1){
				fromIndex = s.indexOf(keyword,fromIndex)+keyword.length();
			}
		}
		return indexList;
		
	}// indexDetector
	
	/*
		commentDeletor(): deletes block comments from text
	*/
	public void commentDeletor (){
		while ( text.indexOf("/*") > -1 ){
			text = text.substring(0, text.indexOf("/*")) +
					text.substring(text.indexOf("*/")+2);
		}
	}
	
	/*
		loopSeparator (String, int): takes in string version of file and index where loop starts
		Prints out INITIALIZATION, BOOLEAN, UPDATE, BODY
	*/
	public String[] loopSeparator( String s, int index ){
	        String[] forStuff = new String[4];
	        s = s.substring(index);
	 	int openParen = 0;
	 	int closeParen = 0;
	 	String forParams = "";
	 	
	 	for (int i = 0; i < s.length() - 1; i++){
	 		if (s.substring(i, i+1).equals("(")){
	 			openParen += 1;
	 		}
	 		//System.out.println("OPEN: " + openParen);
	 		if (s.substring(i, i+1).equals(")")){
	 			closeParen += 1;
	 		}
	 		
	 		//System.out.println("CLOSE: " + closeParen);
		   if (openParen == closeParen && openParen != 0 && closeParen != 0){
				forParams = s.substring(s.indexOf("("), i+1);
				break;
			}
	 		
	 		
	 	}
	 	
	 	//FOR PARAMS
	 	String init = forParams.substring(1, forParams.indexOf(";"));
		forStuff[0] = init.trim();
	 	forParams = forParams.substring(forParams.indexOf(";") + 1);
	 	
	 	String bool = forParams.substring(0, forParams.indexOf(";"));
		forStuff[1] = bool.trim();
	 	forParams = forParams.substring(forParams.indexOf(";") + 1);
	 	
	 	String update = forParams.substring(0,forParams.length()-1);
		forStuff[2] = update.trim();
	 	
	 	//BODY
	 	int openCurly = 0;
	 	int closeCurly = 0;
	 	String forBody = "";
	 	for (int i = 0; i < s.length() - 1; i++){
	 		if (s.substring(i, i+1).equals("{")){
	 			openCurly += 1;
	 		}
	 		if (s.substring(i, i+1).equals("}")){
	 			closeCurly += 1;
	 		}
		   if (openCurly == closeCurly && openCurly != 0 && closeCurly != 0){
				forBody = s.substring(s.indexOf("{")+1, i);
				break;
			}
	 		
	 	}
		forStuff[3] = forBody;
	 	//System.out.println("BODY: " + forBody);
	 	//System.out.println("");
	        return forStuff;
	


	}

}
