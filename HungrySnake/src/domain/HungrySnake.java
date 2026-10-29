package domain;
import java.util.ArrayList;
/**
 * Represents a HungrySnake
 * HungrySnake
 */
public class HungrySnake{
    private char[][] board;
    private ArrayList<int[]> fruits;
    private ArrayList<int[]> walls;
    private ArrayList<int[]> snake;
    private char lastDirectionSnake;
    private int row;
    private int column;

    /**
     * Create a HungrySnake
     * @param row
     * @param column
     * @param fruits
     * @param obstacles
     * @throws HungrySnakeException
     */
    public HungrySnake(int row, int column, int fruits, double obstacles) throws HungrySnakeException{
        if(row<0 || column <0 || fruits<0 || obstacles<0){
            throw new HungrySnakeException(HungrySnakeException.NEGATIVE_NUMBERS);
        }
        if((row*column)<= (fruits + ((row*column) * obstacles/100))){
            throw new HungrySnakeException(HungrySnakeException.CANT_PUT_A_SNAKE);
        }
        board = new char[row][column];
        this.fruits = new ArrayList<>();
        walls = new ArrayList<>();
        snake = new ArrayList<>();
        this.row = row;
        this.column = column;
        

        for(int i = 0; i<fruits; i++){
            int a = (int)(Math.random()*row);
            int b = (int)(Math.random()*column);
            while(board[a][b] !='\u0000'){
                a = (int)(Math.random()*row);
                b = (int)(Math.random()*column);
            }
            board[a][b]='F';
            this.fruits.add(new int[]{a,b});
        }

        int numObs = (int)((row * column) * obstacles/100);
        for(int i = 0; i<numObs; i++){
            int a = (int)(Math.random()*row);
            int b = (int)(Math.random()*column);
            while(board[a][b] !='\u0000'){
                a = (int)(Math.random()*row);
                b = (int)(Math.random()*column);
            }
            board[a][b]='O';
            walls.add(new int[]{a,b});
        }
        
        
        snake = new ArrayList<>();
        boolean found = false;
        for(int i = 0; i<row; i++){
            if(found == true){
                break;
            }
            for(int j = 0; j<column; j++){
                if(board[i][j] == '\u0000'){
                    snake.add(new int[]{i,j});
                    board[i][j] = 'H';
                    lastDirectionSnake = 'E';
                    found = true;
                    break;
                    
                }
            }
        }

    }

    /**
     * returns if the snake moved successfully
     * @param direction
     * @return isOk
     */
    public boolean move(char direction) throws HungrySnakeException{
        boolean isOk = true;
        int posYHead = snake.get(0)[0];
        int posXHead = snake.get(0)[1];
        if(direction=='N'){
            if(lastDirectionSnake!='S' || size()<=1){
                if(board[posYHead+1][posXHead] =='F'){
                    board[posYHead][posXHead] = 'S';
                    board[posYHead+1][posXHead] = 'H';
                    snake.add(0, new int[]{posYHead+1, posXHead});

                }
                else if(board[posYHead+1][posXHead] =='\u0000'){
                    int[] tempTail = snake.get(size()-1);
                    board[tempTail[0]][tempTail[1]] = '\u0000';
                    snake.remove(size()-1);

                    board[posYHead+1][posXHead] = 'H';
                    snake.set(0, new int[]{posYHead+1, posXHead});
            
                
                }else if(posYHead == 0){
                    throw new HungrySnakeException(HungrySnakeException.EXIT_EDGE);

                }else{
                    isOk=false;
                }
            }else{
                throw new HungrySnakeException(HungrySnakeException.LAST_DIRECTION);

            }
        
        }
        else if(direction=='S'){
            if(lastDirectionSnake!='N' || size()<=1){
                if(board[posYHead-1][posXHead] =='F'){
                    board[posYHead][posXHead] = 'S';
                    board[posYHead-1][posXHead] = 'H';
                    snake.add(0, new int[]{posYHead-1, posXHead});
               
                }
                else if (board[posYHead-1][posXHead] =='\u0000'){
                    int[] tempTail = snake.get(size()-1);
                    board[tempTail[0]][tempTail[1]] = '\u0000';
                    snake.remove(size()-1);

                    board[posYHead-1][posXHead] = 'H';
                    snake.set(0, new int[]{posYHead-1, posXHead});
                
                }else if(posYHead == this.row-1){
                    throw new HungrySnakeException(HungrySnakeException.EXIT_EDGE);
                

                }else{
                    isOk=false;
                }
            }else{
                throw new HungrySnakeException(HungrySnakeException.LAST_DIRECTION);
            }
            
        }
        else if(direction=='E'){
            if(lastDirectionSnake!='W' || size()<=1){
                if(board[posYHead][posXHead+1] =='F'){
                    board[posYHead][posXHead] = 'S';
                    board[posYHead][posXHead+1] = 'H';
                    snake.add(0, new int[]{posYHead, posXHead+1});
                
                }
                else if (board[posYHead][posXHead+1] =='\u0000'){
                    int[] tempTail = snake.get(size()-1);
                    board[tempTail[0]][tempTail[1]] = '\u0000';
                    snake.remove(size()-1);

                    board[posYHead][posXHead+1] = 'H';
                    snake.set(0, new int[]{posYHead, posXHead+1});
                
                
                }else if(posXHead == this.column-1){
                    throw new HungrySnakeException(HungrySnakeException.EXIT_EDGE);

                    
                }else{
                    isOk=false;
                }
            }else{
                throw new HungrySnakeException(HungrySnakeException.LAST_DIRECTION);
            }
        
        }
        else if(direction=='W'){
            if(lastDirectionSnake!='E' || size()<=1){
                if(board[posYHead][posXHead-1] =='F'){
                    board[posYHead][posXHead] = 'S';
                    board[posYHead][posXHead-1] = 'H';
                    snake.add(0, new int[]{posYHead, posXHead-1});
                
                }
                else if (board[posYHead][posXHead-1] =='\u0000'){
                    int[] tempTail = snake.get(size()-1);
                    board[tempTail[0]][tempTail[1]] = '\u0000';
                    snake.remove(size()-1);

                    board[posYHead][posXHead-1] = 'H';
                    snake.set(0, new int[]{posYHead, posXHead-1});
                
                
                }else if(posXHead == 0){
                    throw new HungrySnakeException(HungrySnakeException.EXIT_EDGE);
                    

                }else{
                    isOk=false;
                }
            }else{
                throw new HungrySnakeException(HungrySnakeException.LAST_DIRECTION);
            }
        }
        return isOk;
    }

    /**
     * return the size of the arraylist snake
     * @return snake.size()
     */
    public int size(){
        return snake.size();
    }

    /**
     * return the board of characters
     * @return board
     */
    public char[][] board(){
        return board;
    }
}