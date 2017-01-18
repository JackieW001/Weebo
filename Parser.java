
import java.io.*;
import cs1.Keyboard;
import java.util.*;

public class Parser {

	static String text = "";
	static int curlyBraceCnt = 0;
	static int parenCnt = 0;
	
	public void  parseIt(){
		System.out.println("\nCurrently, this program will only output \n" +
							"the for loop parameters. Please input a file \n" +
							"in the same directory as this program.\n" +
							"\n *** PLEASE ONLY INPUT Moo.java FOR NOW ****");
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
				
			//Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				if (strLine.indexOf("//") > -1){
					strLine = strLine.substring(0, strLine.indexOf("//"));
				}
				text += strLine.trim();
				
			}// end while strLine = br.readLine
			System.out.println(text);
			//Close the input stream
			br.close();
		}
		catch (IOException e){
			System.out.println("Fail");
		}
		commentDeletor();
		System.out.println("YOUR JAVA FILE: " + text);
		System.out.println("");
		ArrayList<Integer> indicies = indexDetector(text, "for");
		for (int i = 0; i < indicies.size(); i++){
			loopSeparator(text, indicies.get(i));	
		}
	}// main
	
	/*
		indexDetector (String, String): takes in string version of file and loop keyword (i.e. "for")
		Finds all indicies in string where keyword starts
		Returns indicies in an ArrayList
	*/
	public ArrayList<Integer> indexDetector (String s, String keyword){
		ArrayList<Integer> indexList = new ArrayList<Integer>();
		int fromIndex = 0;
		while (s.indexOf(keyword,fromIndex) > -1){
			int index = s.indexOf(keyword, fromIndex);
			indexList.add(index);
			fromIndex = s.indexOf(keyword,fromIndex)+keyword.length();
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
	public void loopSeparator( String s, int index ){
	 	s = s.substring(index);
	 	System.out.println(s);
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
	 	System.out.println("INIT: " + init);
	 	forParams = forParams.substring(forParams.indexOf(";") + 1);
	 	
	 	String bool = forParams.substring(0, forParams.indexOf(";"));
	 	System.out.println("BOOLEAN: " + bool);
	 	forParams = forParams.substring(forParams.indexOf(";") + 1);
	 	
	 	String update = forParams.substring(0,forParams.length()-1);
	 	System.out.println("UPDATE: " + update);
	 	
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
	 	System.out.println("BODY: " + forBody);
	 	System.out.println("");
	        Flowchart blarg = new Flowchart();
		blarg.assembleLoop(true, 0, 9, init, bool, update, forBody);
		blarg.print();

	}

}
