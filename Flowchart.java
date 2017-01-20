import java.util.*;
public class Flowchart{
    private SuperTwoDArray grid;
    //
    //constructor
    public Flowchart(){
        grid = new SuperTwoDArray();        
    }
    //
    //static methods
    //repeater: creates a string that is len repeats input
    //useful for: streches of flowchart arrows of variable length
    public static String repeater(String input, int len){
        String retStr = "";
        for(int i = 0 ; i < len; i += 1){
            retStr += input;
        }
        return retStr;
    }
    
    //
    //enterStringHorz: fills in cells with single character strings of an input string to the left of the input cell
    public void enterStringHorz(int r, int c, String str){
	for(int i = 0; i < str.length(); i += 1){
	    grid.setCell(r, c+i, str.substring(i,i+1));
	}
    }
    /*    //OVERLOADED enterStringHorz
    //enterStringHorz: fills in cells with single character strings of an input string to the left of the input cell
    //possible types: init, bool etc. (for different colors)
    public void enterStringHorz(String type, int r, int c, String str){
	String color = "";
	if(type.equals("init")){ color = ANSI_CYAN;}
	if(type.equals("bool")){ color = ANSI_PURPLE;}
	if(type.equals("body")){ color = ANSI_GREEN;}
	if(type.equals("update")){ color = ANSI_YELLOW;}
	for(int i = 0; i < str.length(); i += 1){
	    grid.setCell(r, c+i, color + str.substring(i,i+1) + ANSI_RESET);
	}
	} */
    //enterStringVert: fills in cells with single character strings of an input string below the input cell
    public void enterStringVert(int r, int c, String str){
        for(int i = 0; i < str.length(); i += 1){
	    grid.setCell(r+i, c, str.substring(i,i+1));
	}
    }

    //
    //initArrow: inserts a downwards arrow stating the inititalization statment
    //r,c denotes the start of the arrow
    public void initArrow(int r, int c, String init){
        enterStringVert(r,c,"|_|V"); //leaves a cell to insert init
        enterStringHorz(r+1,c,init);
    }
    //boolRhombus: inserts a boolean expression with two directions for the value of the expression
    //r,c denotes the coordinates of the start of the bool expression
    public void boolRhombus(int r, int c, String bool){
        enterStringHorz(r,c-9,"-false-{ " + bool + " }-true-");
     }
    //exitArrow: inserts an arrow that exits the flow chart entirely
    //r,c denotes the coordinates of the start of the exit arrow
    public void exitArrow(int r, int c){
        enterStringVert(r,c, "||||_|V"); // leaves a cell to insert word exit
        enterStringHorz(r+4,c,"exit");
    }
    //arrowToNextLoop: inserts an arrow pointing to the next loop(if there is one)
    //r,c denotes the coordinates of the start of the arrow
    //body of the top loop is needed in order to have the arrow pass its size
    //returns int of row where next loop will start
    public int arrowToNextLoop(int r, int c, String body){
        //counts how many lines are in the body
        int linesInBody = 1; //starts with a min of one line of code
        for(int i = 0; i < body.length() - 1; i += 1){
            if(body.substring(i,i+1).equals("\n")){
                linesInBody += 1;
            }
        } 
        enterStringVert(r,c, repeater("|", linesInBody+5));
        enterStringHorz(r+linesInBody+4,c+1, repeater("-", 9));
	return r+linesInBody+5; //this is where the next loop will start
    }
    //strechEast: figures out how far east the true arrow has to branch out..
    //..in order to not bumb into the update statment
    public int strechEast(String bool, String update){
        if(update.length() - bool.length() > 5){
            return update.length() - bool.length() - 5; //maths calculations for this were done on paper
        }
        else{
            return 0; //no extra hyphens needed/ the bool already passes the update by enough
        }
    }
    //strechWest: figures out how far back the arrow returning..
    //.. to the for loop above has to strech west..
    //.. in order to line up with the bool above
    public int strechWest(String bool, String update){
        return strechEast(bool,update) + 5 + bool.length();
    }
    //arrowToInnards: inserts an arrow leading down to the contents inside the for loop
    //r,c denotes the coordinates of the start of the arrow
    public void arrowToInnards(int r, int c, String bool, String update){
        String horzHyphs = repeater("-", strechEast(bool,update)+1);
        enterStringHorz(r,c, horzHyphs);
        enterStringVert(r+1,c + horzHyphs.length() - 1, "|||V");
    }
    //insertBody: inserts body statments of the contents inside the loop that don't..
    //.. contain for loops
    //r,c denotes the coordinates of the start of the first body statment
    public void insertBody(int r, int c, String body){
        //counts how many lines are in the body..
        //.. so that a box can be created around the body statments
        int linesInBody = 1; //starts with a min of one line of code
        for(int i = 0; i < body.length() - 1; i += 1){
            if(body.substring(i,i+1).equals("\n")){
                linesInBody += 1;
            }
        }
        //closes of the body statement from the left        
        enterStringVert(r, c-2, repeater("[", linesInBody));
        enterStringVert(r, c-1, repeater(" ", linesInBody));
        
        //inserts each new line of body on a new row
        String bodyAlias = body;
        for(int i = 0; i < bodyAlias.length() - 1; i += 1){
            if(! (bodyAlias.indexOf("\n") == -1)){
                enterStringHorz(r+i,c,bodyAlias.substring(0,bodyAlias.indexOf("\n")));
                bodyAlias = bodyAlias.substring(bodyAlias.indexOf("\n")+1);           
            }
            else{
                enterStringHorz(r+i,c,bodyAlias);
                break; //there are no more newlines in code so the loop is done
            }
        }
        
        //determines the length of the longest stament in body..
        //..so that the right side of the body can be boxed off
        int longestLine = 0;
        int tempLineLenCounter = 0;
        for(int i = 0; i < body.length() - 1; i += 1){
            if(body.substring(i,i+1).equals("\n")){
                if(tempLineLenCounter > longestLine){
                    longestLine = tempLineLenCounter + 1; //the new longest line
                }
                tempLineLenCounter = 0; //resets length counter
                i += 1; //skips over the \n
            }
            else{
                tempLineLenCounter += 1;
                //acounts for special case where the last line is the longest line
                if((i == body.length() - 2) && (tempLineLenCounter + 1 > longestLine)){
                    longestLine = tempLineLenCounter + 2;
                }//end of if
            }//end of else
        }//end of for
        //closes of the body on the right hand side
        enterStringVert(r,c + longestLine,repeater(" ",linesInBody));
        enterStringVert(r,c + longestLine + 1,repeater("]",linesInBody));
    }//end of insertBody
    
    //returnArrow: creates an arrow that returns back to the for loop above
    //r,c denotes the start of the return arrow
    public void returnArrow(int r, int c, String bool, String update){
        grid.setCell(r,c,"-"); //default start of arrow
        //in case the arrow has to strech back to reallign with the bool expression
        int strech = strechWest(bool,update);
        if(strech > 0){
            enterStringHorz(r,c-strech,repeater("-",strech));
        }
        //upwards arrow part
        enterStringVert(r-4,c-strech,"^|_|"); //leaves a spot for update
        enterStringHorz(r-2,c-strech,update);
        
    } 
    
    //assembleLoop: inserts a single non-nested loop
    //r,c denotes the start of the init arrow (precond: c>=9) 
    //exit denotes whether or not the loop should have an exit arrow
    //.. or and arrow to the next loop
    //returns 0 if this loops is the last in the program
    //returns int of row where next loop will start otherwise
    public int assembleLoop(boolean exit, int r, int c, String init, String bool, String update, String body){
	int retInt = 0;
        initArrow(r,c,init);
        boolRhombus(r+4,c,bool);
        if(exit){
            exitArrow(r+5,c-9);
        }
        else{
            retInt = arrowToNextLoop(r+5,c-9,body);
        }
        arrowToInnards(r+4,17+bool.length(),bool, update );
        insertBody(r+9,17+bool.length()+strechEast(bool,update), body);
        returnArrow(r+9,17+bool.length()+strechEast(bool,update)-3, bool,update);
        return retInt;
    }

    //
    //print
    public void print(){
	grid.printArray();
    }
 
}
