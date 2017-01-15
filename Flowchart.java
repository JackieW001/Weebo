import java.util.*;
public class Flowchart{
    private String[][] grid;
    //constructor
    public Flowchart(){
        grid = new String[60][70];
        for(int i = 0; i < grid.length; i+=1){
            for(int j = 0; j < grid[i].length; j+=1){
                grid[i][j] = " ";
            }
        }            
    }
    //
    //static methods
    //creates a string that is len repeats input
    public static String repeater(String input, int len){
        String retStr = "";
        for(int i = 0 ; i < len; i += 1){
            retStr += input;
        }
        return retStr;
    }
    
    //
    //setGridElem
    public void setCell(int r, int c, String str){
        grid[r][c] = str;
    }
    //fills in cells with single character strings of an input string to the left of the input cell
    public void enterStringHorz(int r, int c, String str){
	for(int i = 0; i < str.length(); i += 1){
	    setCell(r, c+i, str.substring(i,i+1));
	}
    }
    //fills in cells with single character strings of an input string below the input cell
    public void enterStringVert(int r, int c, String str){
        for(int i = 0; i < str.length(); i += 1){
	    setCell(r+i, c, str.substring(i,i+1));
	}
    }

    //
    //inserts a downwards arrow stating the inititalization statment
    //r,c denotes the start of the arrow
    public void initArrow(int r, int c, String init){
        enterStringVert(r,c,"|_|V"); //leaves a cell to insert init
        enterStringHorz(r+1,c,init);
    }
    //inserts a boolean expression with two directions for the value of the expression
    //r,c denotes the coordinates of the start of the bool expression
    public void boolRhombus(int r, int c, String bool){
        enterStringHorz(r,c-9,"-false-< " + bool + " >-true-");
    }
    //inserts an arrow that exits the flow chart entirely
    //r,c denotes the coordinates of the start of the exit arrow
    public void exitArrow(int r, int c){
        enterStringVert(r,c, "||||_|V"); // leaves a cell to insert word exit
        enterStringHorz(r+4,c,"exit");
    }
    //inserts an arrow pointing to the next loop(if there is one)
    //r,c denotes the coordinates of the start of the arrow
    //body of the top loop is needed in order to have the arrow pass its size
    public void arrowToNextLoop(int r, int c, String body){
        //counts how many lines are in the body
        int linesInBody = 1; //starts with a min of one line of code
        for(int i = 0; i < body.length() - 1; i += 1){
            if(body.substring(i,i+1).equals("\n")){
                linesInBody += 1;
            }
        } 
        enterStringVert(r,c, repeater("|", linesInBody+5));
        enterStringHorz(r+linesInBody+4,c+1, repeater("-", 9));
    }
    //figures out how far east the true arrow has to branch out..
    //..in order to not bumb into the update statment
    public int strechEast(String bool, String update){
        if(update.length() - bool.length() > 5){
            return update.length() - bool.length() - 5; //maths calculations for this were done on paper
        }
        else{
            return 0; //no extra hyphens needed/ the bool already passes the update by enough
        }
    }
    //figures out how far back the arrow returning..
    //.. to the for loop above has to strech west..
    //.. in order to line up with the bool above
    public int strechWest(String bool, String update){
        return strechEast(bool,update) + 5 + bool.length();
    }
    //inserts an arrow leading down to the contents inside the for loop
    //r,c denotes the coordinates of the start of the arrow
    public void arrowToInnards(int r, int c, String bool, String update){
        String horzHyphs = repeater("-", strechEast(bool,update)+1);
        enterStringHorz(r,c, horzHyphs);
        enterStringVert(r+1,c + horzHyphs.length() - 1, "|||V");
    }
    //inserts body statments of the contents inside the loop that don't..
    //.. contain for loops
    //r,c denotes the coordinates of the start of the first body statment
    public void insertBody(int r, int c, String body){
        //counts how many lines are in the body
        int linesInBody = 1; //starts with a min of one line of code
        for(int i = 0; i < body.length() - 1; i += 1){
            if(body.substring(i,i+1).equals("\n")){
                linesInBody += 1;
            }
        }        
        enterStringVert(r, c-2, repeater("[", linesInBody));
        enterStringVert(r, c-1, repeater(" ", linesInBody));
        
        String bodyAlias = body;
        for(int i = 0; i < bodyAlias.length() - 1; i += 1){
            if(! (bodyAlias.indexOf("\n") == -1)){
                enterStringHorz(r+i,c,bodyAlias.substring(0,bodyAlias.indexOf("\n")));
                bodyAlias = bodyAlias.substring(bodyAlias.indexOf("\n")+1);           
            }
            else{
                enterStringHorz(r+i,c,bodyAlias);
                break;
            }
        }
        //determines the length of the longest stament in body
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
        enterStringVert(r,c + longestLine,repeater(" ",linesInBody));
        enterStringVert(r,c + longestLine + 1,repeater("]",linesInBody));
    }//end of insertBody
    //method: returnArrow creates an arrow that returns back to the for loop above
    //r,c denotes the start of the return arrow
    public void returnArrow(int r, int c, String bool, String update){
        setCell(r,c,"-"); //default start of arrow
        int strech = strechWest(bool,update);
        if(strech > 0){
            enterStringHorz(r,c-strech,repeater("-",strech));
        }
        enterStringVert(r-4,c-strech,"^|_|"); //leaves a spot for update
        enterStringHorz(r-2,c-strech,update);
        
    } 
    
    //single non-nested loop assembler
    //r,c denotes the start of the init arrow (precond: c>=9) 
    public void assembleLoop(boolean exit, int r, int c, String init, String bool, String update, String body){
        initArrow(r,c,init);
        boolRhombus(r+4,c,bool);
        if(exit){
            exitArrow(r+5,c-9);
        }
        else{
            arrowToNextLoop(r+5,c-9,body);
        }
        arrowToInnards(r+4,17+bool.length(),bool, update );
        insertBody(r+9,17+bool.length()+strechEast(bool,update), body);
        returnArrow(r+9,17+bool.length()+strechEast(bool,update)-3, bool,update);
    }
    //
    //printer
    public void printGrid(){
        String rowStr;
        for(int i = 0; i < grid.length; i+=1){
            rowStr = "";
            for(int j = 0; j < grid[i].length; j+=1){
                rowStr += grid[i][j];
            }
            System.out.println(rowStr);
        }
    }
    //main for now
    public static void main(String args[]){
        Flowchart blarg = new Flowchart();
        blarg.assembleLoop(false, 0,9,"int i = 0", "i<20", "i+= 10000", "sop;\nSOSOPSOSPOSPOSP;\nwe the best;");
        blarg.assembleLoop(true, 13,9,"int j = 0", "j<java.lengt()", "i++", "sop;\nyesJackie;\na Statement that reqs;");
        blarg.printGrid();
        
    }
}
