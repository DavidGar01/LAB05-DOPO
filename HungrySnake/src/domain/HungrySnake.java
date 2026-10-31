package domain;
import java.util.ArrayList;
/**
 * Represents a HungrySnake
 */
public class HungrySnake{
    private char[][] board;
    private ArrayList<int[]> fruits;
    private ArrayList<int[]> obstacles;
    private ArrayList<int[]> snake;
    private char lastDirectionSnake;
    private int row;
    private int column;

    /**
     * Create a HungrySnake
     * @param row are the game's rows
     * @param column are the game's columns
     * @param fruits are the fruits that game will have
     * @param obstacles are the percentage of obstacles
     * @throws HungrySnakeException
     */
    public HungrySnake(int row, int column, int fruits, double obstacles) throws HungrySnakeException{
        if(row<=0 || column <=0 || fruits<0 || obstacles<0){
            throw new HungrySnakeException(HungrySnakeException.NEGATIVE_NUMBERS);
        }
        if((row*column)<= (fruits + ((row*column) * obstacles/100))){
            throw new HungrySnakeException(HungrySnakeException.CANT_PUT_A_SNAKE);
        }
        board = new char[row][column];
        this.fruits = new ArrayList<>();
        this.obstacles = new ArrayList<>();
        this.snake = new ArrayList<>();
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
            this.obstacles.add(new int[]{a,b});
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
     * @param direction is the direction where the snake will move
     * @return if the snake didn't crash with an obstacle
     */
    public boolean move(char direction) throws HungrySnakeException {
        boolean isOk = true;
        int posYHead = snake.get(0)[0];
        int posXHead = snake.get(0)[1];

        if (direction == 'N') {
            if (lastDirectionSnake != 'S' || size() <= 1) {
                int newY = posYHead - 1;
                int newX = posXHead;

                if (newY < 0) {
                    throw new HungrySnakeException(HungrySnakeException.EXIT_EDGE);
                }

                if (board[newY][newX] == 'F') {
                    board[posYHead][posXHead] = 'S';
                    board[newY][newX] = 'H';
                    snake.add(0, new int[]{newY, newX});
                    lastDirectionSnake = 'N';
                    if(size()+obstacles.size()<row*column){
                        int a = (int)(Math.random()*row);
                        int b = (int)(Math.random()*column);
                        while(board[a][b] !='\u0000'){
                            a = (int)(Math.random()*row);
                            b = (int)(Math.random()*column);
                        }
                        board[a][b]='F';
                        fruits.remove(new int[]{newY, newX});
                        fruits.add(new int[]{a,b});
                    }
                } else if (board[newY][newX] == '\u0000') {
                    int[] tempTail = snake.remove(size() - 1);
                    board[tempTail[0]][tempTail[1]] = '\u0000';
                    snake.add(0, new int[]{newY, newX});
                    if(size() > 1){
                        board[posYHead][posXHead] = 'S';
                    }
                    board[newY][newX] = 'H';
                    
                    lastDirectionSnake = 'N';
                } else {
                    isOk = false;
                }
            } else {
                throw new HungrySnakeException(HungrySnakeException.LAST_DIRECTION);
            }
        } else if (direction == 'S') {
            if (lastDirectionSnake != 'N' || size() <= 1) {
                int newY = posYHead + 1;
                int newX = posXHead;

                if (newY >= this.row) {
                    throw new HungrySnakeException(HungrySnakeException.EXIT_EDGE);
                }

                if (board[newY][newX] == 'F') {
                    board[posYHead][posXHead] = 'S';
                    board[newY][newX] = 'H';
                    snake.add(0, new int[]{newY, newX});
                    lastDirectionSnake = 'S';
                    if(size()+obstacles.size()<row*column){
                        int a = (int)(Math.random()*row);
                        int b = (int)(Math.random()*column);
                        while(board[a][b] !='\u0000'){
                            a = (int)(Math.random()*row);
                            b = (int)(Math.random()*column);
                        }
                        board[a][b]='F';
                        fruits.remove(new int[]{newY, newX});
                        fruits.add(new int[]{a,b});
                    }

                } else if (board[newY][newX] == '\u0000') {
                    int[] tempTail = snake.remove(size() - 1);
                    board[tempTail[0]][tempTail[1]] = '\u0000';
                    snake.add(0, new int[]{newY, newX});
                    if(size() > 1){
                        board[posYHead][posXHead] = 'S';
                    }
                    board[newY][newX] = 'H';
                
                    lastDirectionSnake = 'S';
                } else {
                    isOk = false;
                }
            } else {
                throw new HungrySnakeException(HungrySnakeException.LAST_DIRECTION);
            }
        } else if (direction == 'E') {
            if (lastDirectionSnake != 'W' || size() <= 1) {
                int newY = posYHead;
                int newX = posXHead + 1;

                if (newX >= this.column) {
                    throw new HungrySnakeException(HungrySnakeException.EXIT_EDGE);
                }

                if (board[newY][newX] == 'F') {
                    board[posYHead][posXHead] = 'S';
                    board[newY][newX] = 'H';
                    snake.add(0, new int[]{newY, newX});
                    lastDirectionSnake = 'E';
                    if(size()+obstacles.size()<row*column){
                        int a = (int)(Math.random()*row);
                        int b = (int)(Math.random()*column);
                        while(board[a][b] !='\u0000'){
                            a = (int)(Math.random()*row);
                            b = (int)(Math.random()*column);
                        }
                        board[a][b]='F';
                        fruits.remove(new int[]{newY, newX});
                        fruits.add(new int[]{a,b});
                    }
                } else if (board[newY][newX] == '\u0000') {
                    int[] tempTail = snake.remove(size() - 1);
                    board[tempTail[0]][tempTail[1]] = '\u0000';
                    snake.add(0, new int[]{newY, newX});
                    if(size() > 1){
                        board[posYHead][posXHead] = 'S';
                    }
                    board[newY][newX] = 'H';
                    
                    lastDirectionSnake = 'E';
                } else {
                    isOk = false;
                }
            } else {
                throw new HungrySnakeException(HungrySnakeException.LAST_DIRECTION);
            }
        } else if (direction == 'W') {
            if (lastDirectionSnake != 'E' || size() <= 1) {
                int newY = posYHead;
                int newX = posXHead - 1;

                if (newX < 0) {
                    throw new HungrySnakeException(HungrySnakeException.EXIT_EDGE);
                }

                if (board[newY][newX] == 'F') {
                    board[posYHead][posXHead] = 'S';
                    board[newY][newX] = 'H';
                    snake.add(0, new int[]{newY, newX});
                    lastDirectionSnake = 'W';
                    if(size()+obstacles.size()<row*column){
                        int a = (int)(Math.random()*row);
                        int b = (int)(Math.random()*column);
                        while(board[a][b] !='\u0000'){
                            a = (int)(Math.random()*row);
                            b = (int)(Math.random()*column);
                        }
                        board[a][b]='F';
                        fruits.remove(new int[]{newY, newX});
                        fruits.add(new int[]{a,b});
                    }
                } else if (board[newY][newX] == '\u0000') {
                    int[] tempTail = snake.remove(size() - 1);
                    board[tempTail[0]][tempTail[1]] = '\u0000';
                    snake.add(0, new int[]{newY, newX});
                    if(size() > 1){
                        board[posYHead][posXHead] = 'S';
                    }
                    board[newY][newX] = 'H';
                    lastDirectionSnake = 'W';
                } else {
                    isOk = false;
                }
            } else {
                throw new HungrySnakeException(HungrySnakeException.LAST_DIRECTION);
            }
        }
        return isOk;
    }

    /**
     * return the size of the arraylist snake
     * @return snake's size
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