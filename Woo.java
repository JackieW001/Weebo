public class Woo {

	public static void main(String[] args){
		try {
			System.out.print("\033[H\033[2J");
			System.out.flush();
		}
		catch (Exception e){;}
	
		System.out.println("Welcome to the Weebo Java Flow Chart Visualizer!\n\n"+
						   "This program will help visualize \n"+
						   "any for loops within\n"+
						   "your .java file. This program doesn't accomodate\n"+
						   "for nested for loops yet. If you have a nested\n"+
						   "loop, the program will detect it but not visualize it\n"+
						   "correctly.\n\n"+

						   "If you would like to read in a file, copy the file\n"+
						   "into this cloned github repo.\n"+
						   "The file will not be read if it is not within this repo.\n"+
						   "**Please make sure that your .java file does not have\n"+
						   "any syntax errors as the visualizer will not be able\n"+
						   "to run properly with syntax errors.** Run ||$ javac filename.java||\n"+
						   "to find any syntax errors your code may have.\n\n"+

						   "If you would like to view a model example of\n"+
						   "the Java Visualizer, type in Moo.java when the\n"+
						   "program prompts you for a file to read in.\n" +
						   "||File to read in: **type in file here**||\n"+
						   "----------------------------------------------------------\n");
		Parser parse = new Parser();
		parse.parseIt();
		System.out.println("~~~~~~~~~~~~~~~~~~ END ~~~~~~~~~~~~~~~~~");
	}
}

