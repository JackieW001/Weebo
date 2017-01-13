import java.util.*;
public class Flowchart{
    private String[][] grid;
    //constructor
    public Flowchart(){
        grid = new String[10][30];
        for(int i = 0; i < grid.length; i+=1){
            for(int j = 0; j < grid[i].length; j+=1){
                grid[i][j] = "_";
            }
        }            
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
    public void initArrow(int r, int c, String init){
	setCell(r,c,"|");
	enterStringHorz(r+1,c,init);
	enterStringVert(r+2,c,"|V");
    }
    //inserts a 
    
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
        blarg.initArrow(0,0,"int i = 0");
	blarg.printGrid();
    }
}
