import cs1.Keyboard;
import java.util.*;
public class run{
    public static ArrayList inputAssembler(boolean isNested){
	ArrayList retAL = new ArrayList();
	System.out.println("initialization?");
	retAL.add(Keyboard.readString());
	System.out.println("boolean?");
	retAL.add(Keyboard.readString());
	System.out.println("update?");
	retAL.add(Keyboard.readString());
	System.out.println("how many loops are nested inside the body?");
	int nestedAns = Keyboard.readInt(); 
	for(int i = nestedAns; i > 0; i -= 1){
	    System.out.println("any statements in body before nested loop?");
            retAL.add(Keyboard.readString());
	    retAL.add(inputAssembler(true));
	}
	if(! isNested){
	    System.out.println("additional statements in the body?");
	    retAL.add(Keyboard.readString());
	}
	return retAL;
    }
    public static void main(String[] args){
	ArrayList input = new ArrayList();
	System.out.println(inputAssembler(false));
    }//end of main
}//end of run
