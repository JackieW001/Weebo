import java.util.*;
public class Flowchart{
    private String[][] grid;
    //constructor
    public Flowchart(){
        grid = new String[20][100];
        for(int i = 0; i < grid.length; i+=1){
            for(int j = 0; j < grid[i].length; j+=1){
                grid[i][j] = " ";
            }
        }            
    }
    
    //setGridElem
    public void setSpot(int i, int j, String str){
        grid[i][j] = str;
    }
    //single loop filler
    public void fill(ArrayList input){
        int boolLength = ((String)input.get(1)).substring(9).length();
        int updaLength = ((String)input.get(2)).substring(9).length();
        int initLength = ((String)input.get(0)).substring(9).length();
        //
        setSpot(0,5,"|");
        setSpot(1,5, ((String)input.get(0)).substring(9));
        setSpot(2,5,"|");
        setSpot(3,5,"V");
        //
        setSpot(4,0,"-");
        setSpot(4,1,"-");
        setSpot(4,2,"-");
        setSpot(4,3,"-");
        setSpot(4,4,"<");
        setSpot(4,5, ((String)input.get(1)).substring(9));
        setSpot(4,6,">");
        setSpot(4,7,"-");
        setSpot(4,8,"-");
        setSpot(4,9,"-");
        setSpot(4,10,"-");
        setSpot(4,11,"-");
        //
        setSpot(5,0,"|");
        setSpot(6,0,"no");
        setSpot(7,0,"|");
        setSpot(8,0,"V");
        setSpot(9,0,"exit");
        //
        setSpot(5,13+initLength,"|");
        setSpot(6,13 + initLength ,"yes");
        
        setSpot(7,6 + max(updaLength+1,initLength),"|");
        setSpot(8,13 + initLength ,"V");
        //
        setSpot(9,7,"[");
        setSpot(9,8, ((String)((ArrayList)input.get(3)).get(0)).substring(9));
        setSpot(9,9,"]");
        //
        setSpot(9,6,"-");
        setSpot(9,5,"-");
        setSpot(9,4,"-");
        setSpot(9,3,"-");
        setSpot(8,6,"|");
        setSpot(7,6, ((String)input.get(2)).substring(9));
        setSpot(6,5,"|");
        setSpot(5,6,"^");
    }
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
    //max
    public static int max(int a, int b){
        if(a<b) return b;
        else return a;
    }
}