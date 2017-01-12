import cs1.Keyboard;
import java.util.*;
public class run{
    public static ArrayList inputAssembler(int nestedDeep){
	ArrayList retAL = new ArrayList();
	System.out.println("initialization?");
	retAL.add(nestedDeep + "@@init@@" + Keyboard.readString());
	System.out.println("boolean?");
	retAL.add(nestedDeep + "@@bool@@" + Keyboard.readString());
	System.out.println("update?");
	retAL.add(nestedDeep + "@@upd@@" + Keyboard.readString());
	System.out.println("how many loops are nested inside the body?");
	int nestedAns = Keyboard.readInt(); 
	for(int i = 0; i < nestedAns; i += 1){
	    System.out.println("any statements in body before nested loop" + i);
	    ArrayList insertBody = new ArrayList();
	    insertBody.add(
	    retAL.add(nestedDeep + "@@statement@@" + Keyboard.readString());
	    retAL.add(inputAssembler(nestedDeep + 1));
	}
	if(nestedDeep == 0){
	    System.out.println("any statments in the body?");
            retAL.add(nestedDeep + "@@statement@@" + Keyboard.readString());
        }
	else{
	    System.out.println("any additional statments in body?");
            retAL.add(nestedDeep + "@@statement@@" + Keyboard.readString());
        }
	    
	return retAL;
    }
    public static void main(String[] args){
	ArrayList input = new ArrayList();
	System.out.println(inputAssembler(0));
    }//end of main
}//end of run
