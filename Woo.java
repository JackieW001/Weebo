public class Woo {

	public static void main(String[] args){
	
		System.out.println("\nWelcome to the Weebo Java Visualizer\n"+
						   "Currently this program will only output the\nx"+
						   "for loop parameters of any for loops within\n"+
						   "your .java file. This program doesn't accomodate\n"+
						   "for nested for loops yet.\n"+
						   "\nIf you would like to read in a file, copy the file\n"+
						   "into this cloned github repo. The file will not be\n"+
						   "read if it is not within this repo.\n"+
						   "\nIf you would like to view a model example of\n"+
						   "the Java Visualizer, type in Moo.java when the\n"+
						   "program prompts you for a file to read in.\n");
		Parser parse = new Parser();
		parse.parseIt();
		System.out.println("~~~~~~~~~~~~~~~~~~ END ~~~~~~~~~~~~~~~~~");
	}
}

