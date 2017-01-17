public class SuperTwoDArray{
    private String[][] _data;
    private int _rsize;
    private int _csize;
    private String _defaultFiller;
    
    //default constructor
    //creates a 30 by 30 2d array and fills it with " "    
    public SuperTwoDArray(){
        _defaultFiller = " ";
        _data = new String[30][30];
        _rsize = 30;
        _csize = 30;
        for(int i = 0; i < _data.length; i+=1){
            for(int j = 0; j < _data[i].length; j+=1){
                _data[i][j] = _defaultFiller;
            }
        }
    }
    //sets the value at r,c to value
    public void setCell(int r, int c, String value){
        if(r >= _rsize){
            rexpand();
        }
        else if(c >= _csize){
            cexpand();
        }
        _data[r][c] = value;
    }
    //expands the number of rows by 10
    public void rexpand(){
        String[][] tempArr = new String[_rsize+10][_csize];
        //fills array with defaultFIller
        for(int i = 0; i < tempArr.length; i+=1){
            for(int j = 0; j < tempArr[i].length; j+=1){
                tempArr[i][j] = _defaultFiller;
            }
        }
        //copies over elements of _data
        for(int i = 0; i < _data.length; i+=1){
            for(int j = 0; j < _data[i].length; j+=1){
                tempArr[i][j] = _data[i][j];
            }
        }
        //
        _data = tempArr;
        _rsize += 10;
    }
    //expands the number of columns by 10
    public void cexpand(){
        String[][] tempArr = new String[_rsize][_csize+10];
        //fills array with _defaultFiller
        for(int i = 0; i < tempArr.length; i+=1){
            for(int j = 0; j < tempArr[i].length; j+=1){
                tempArr[i][j] = _defaultFiller;
            }
        }
        //copies over elements of _data
        for(int i = 0; i < _data.length; i+=1){
            for(int j = 0; j < _data[i].length; j+=1){
                tempArr[i][j] = _data[i][j];
            }
        }
        //
        _data = tempArr;
        _csize += 10;
    }
    //
    //prints out the contents of _data row by row
    public void printArray(){
        String rowStr;
        for(int i = 0; i < _rsize; i+=1){
            rowStr = "";
            for(int j = 0; j < _csize; j+=1){
                rowStr += _data[i][j];
            }
            System.out.println(rowStr);
        }
    }
}