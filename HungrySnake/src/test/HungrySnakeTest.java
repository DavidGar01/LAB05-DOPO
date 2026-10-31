package test;
import domain.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HungrySnakeTest {

    /**
     * Shouldn't create a board with negative numbers
     */

    @Test
    public void shouldNotCreateBoardWithNegativeNumbers(){
        try{
            HungrySnake game = new HungrySnake(-1,-1,-1,-1);
            fail("Didn't throw an exception");
        }
        catch(HungrySnakeException e){
            assertEquals(e.getMessage(), HungrySnakeException.NEGATIVE_NUMBERS);
        }
    }
    /**
     * Shouldn't create a board where the snake can't enter
     */
    @Test
    public void shouldNotCreateABoardWhereSnakeCanNotEnter(){
      try{
            HungrySnake game = new HungrySnake(10,10,50,50);
            fail("Didn't throw an exception");
        }
        catch(HungrySnakeException e){
            assertEquals(e.getMessage(), HungrySnakeException.CANT_PUT_A_SNAKE);
        }
    }
    /**
     * Should create a board where just there's a space for the snake
     */
    @Test
    public void shouldCreateABoardWhereThereIsAtLeastOnePositionForSnake(){
        try{
            HungrySnake game = new HungrySnake(10,10,49,50);
            char[][] board = game.board();
            assertEquals(10,board[0].length);
            assertEquals(10,board.length);


        }
        catch(HungrySnakeException e){
            fail("Did throw an exception");
        }
    }
    /**
     * Should add correct number of fruits to the board.
     */
    @Test
    public void shouldAddCorrectNumberOfFruitsToTheBoard(){
        try{
            HungrySnake game = new HungrySnake(10,10,50,0);
            char[][] board = game.board();
            int fruitsNumber = 0;
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++){
                    if (board[i][j] == 'F'){
                        fruitsNumber++;
                    }
                }
            }
            assertEquals(50,fruitsNumber);


        }
        catch(HungrySnakeException e){
            fail("Did throw an exception");
        }
    }

    /**
     * Should add correct percentage of obstacles to the board.
     */
    @Test
    public void shouldAddCorrectNumberOfObstaclesToTheBoard(){
        try{
            HungrySnake game = new HungrySnake(10,10,0,50);
            char[][] board = game.board();
            int obstaclesNumber = 0;
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++){
                    if (board[i][j] == 'O'){
                        obstaclesNumber++;
                    }
                }
            }
            assertEquals(50,obstaclesNumber);


        }
        catch(HungrySnakeException e){
            fail("Did throw an exception");
        }
    }

    /**
     * Should snake move correctly
     */
    @Test public void shouldSnakeMoveCorrectly(){
        try{
            HungrySnake game = new HungrySnake(10,10,0,0);
            char[][] board = game.board();
            int[] snakePosition = null;

            for(int i = 0; i < 10; i++){
                if(snakePosition != null){
                    break;
                }
                for(int j = 0; j < 10; j++){
                    if (board[i][j] == 'H'){
                        snakePosition = new int[]{i,j};
                    }
                }
            }
            int yPos = snakePosition[0];
            int xPos = snakePosition[1];
            if(snakePosition[0] != 0){
                game.move('N');
                board = game.board();
                assertEquals('H', board[snakePosition[0]-1][snakePosition[1]]);
            }
            else if(snakePosition[1] != 0){
                game.move('W');
                board = game.board();
                assertEquals('H', board[snakePosition[0]][snakePosition[1]-1]);
            }
            else if(snakePosition[0] != 9){
                game.move('S');
                board = game.board();
                assertEquals('H', board[snakePosition[0]+1][snakePosition[1]]);
            }else if(snakePosition[1] != 9){
                game.move('E');
                board = game.board();
                assertEquals('H', board[snakePosition[0]-1][snakePosition[1]]);
            }


        }
        catch(HungrySnakeException e){
            fail("Did throw an exception");
        }
    }
    /**
     * Should be just one head if snake moves
     */
    @Test 
    public void shouldJustBeOneHeadIfSnakeMoves(){
        try{
            HungrySnake game = new HungrySnake(10,10,0,0);
            char[][] board = game.board();
            int[] snakePosition = null;

            for(int i = 0; i < 10; i++){
                if(snakePosition != null){
                    break;
                }
                for(int j = 0; j < 10; j++){
                    if (board[i][j] == 'H'){
                        snakePosition = new int[]{i,j};
                    }
                }
            }
            int yPos = snakePosition[0];
            int xPos = snakePosition[1];
            if(snakePosition[0] != 0){
                game.move('N');
            }
            else if(snakePosition[1] != 0){
                game.move('W');
            }
            else if(snakePosition[0] != 9){
                game.move('S');
            }else if(snakePosition[1] != 9){
                game.move('E');
            }
            board = game.board();
            int headsNumber = 0;
            for(int i = 0; i<10; i++){
                for(int j = 0; j <10; j++){
                    if (board[i][j] == 'H'){
                        headsNumber++;
                    }
                }
            }   
            assertEquals(1, headsNumber);

        }
        catch(HungrySnakeException e){
            fail("Did throw an exception");
        }
    }

    /**
     * Snake should not exit the edge.
     */
    @Test
    public void shouldNotExitTheEdge(){
        try{
            HungrySnake game = new HungrySnake(5,5,0,0);
            for(int i = 0; i < 6; i++){
                game.move('E');
            }
        }
        catch(HungrySnakeException e){
            assertEquals(HungrySnakeException.EXIT_EDGE,e.getMessage());
        }

    }

    /**
     * Should fruit be replaced and snake must grow.
     */
    @Test
    public void shouldFruitBeReplacedAndSnakeGrows(){
        try{
            HungrySnake game = new HungrySnake(5,1,1,0);
            char[][] board = game.board();
            int snakePosY = 0;
            int fruitPosY = 0;
            for(int i = 0; i<5; i++){
                if(board[i][0] == 'F'){
                    fruitPosY = i;
                }
                else if(board[i][0] == 'H'){
                    snakePosY = i;
                }
            }
            if(snakePosY<fruitPosY){
                for(int i =0; i < fruitPosY-snakePosY; i++){
                    game.move('S');
                }
            }
            else{
                for(int i =0; i < snakePosY-fruitPosY; i++){
                    game.move('N');
                }
            }
            board = game.board();
            int fruitFound = 0;

            for(int i = 0; i<5; i++){
                if(board[i][0] == 'F'){
                    fruitFound++;
                }
            }
            assertEquals(2, game.size());
            assertEquals(1, fruitFound);
        }
        catch(HungrySnakeException e){
            fail("Did throw an exception");
        }
    }

    /**
     * Should not add more fruits if there's no space
     */
    @Test
    public void shouldNotAddMoreFruitsIfThereIsNoSpace(){
        try{
            HungrySnake game = new HungrySnake(2,1,1,0);
            char[][] board = game.board();
            int snakePosY = 0;
            int fruitPosY = 0;
            for(int i = 0; i<2; i++){
                if(board[i][0] == 'F'){
                    fruitPosY = i;
                }
                else if(board[i][0] == 'H'){
                    snakePosY = i;
                }
            }
            if(snakePosY<fruitPosY){
                for(int i =0; i < fruitPosY-snakePosY; i++){
                    game.move('S');
                }
            }
            else{
                for(int i =0; i < snakePosY-fruitPosY; i++){
                    game.move('N');
                }
            }
            board = game.board();
            int fruitFound = 0;

            for(int i = 0; i<2; i++){
                if(board[i][0] == 'F'){
                    fruitFound++;
                }
            }
            assertEquals(2, game.size());
            assertEquals(0, fruitFound);
        }
        catch(HungrySnakeException e){
            fail("Did throw an exception");
        }
    }
    
    /**
     * Shouldn't be ok the move is snake collides with an obstacle
     */
    public void shouldNotBeOkIfSnakeCollidesWithAnObstacle(){
        try{
            HungrySnake game = new HungrySnake(2,1,1,0);
            char[][] board = game.board();
            int snakePosY = 0;
            int obstaclePosY = 0;
            for(int i = 0; i<2; i++){
                if(board[i][0] == 'O'){
                    obstaclePosY = i;
                }
                else if(board[i][0] == 'H'){
                    snakePosY = i;
                }
            }
            if(snakePosY<obstaclePosY){
                for(int i =0; i < obstaclePosY-snakePosY; i++){
                    assertFalse(game.move('S'));
                }
            }
            else{
                for(int i =0; i < snakePosY-obstaclePosY; i++){
                    assertFalse(game.move('N'));
                }
            }
            
        }
        catch(HungrySnakeException e){
            fail("Did throw an exception");
        }
    }

    /**
     * If snake moves the last space where it was must be void.
     */
    @Test
    public void shouldSpaceSnakeWasBeNull(){

        try{
            HungrySnake game = new HungrySnake(10,10,0,0);
            char[][] board = game.board();
            int[] snakePosition = null;

            for(int i = 0; i < 10; i++){
                if(snakePosition != null){
                    break;
                }
                for(int j = 0; j < 10; j++){
                    if (board[i][j] == 'H'){
                        snakePosition = new int[]{i,j};
                    }
                }
            }
            int yPos = snakePosition[0];
            int xPos = snakePosition[1];
            if(snakePosition[0] != 0){
                game.move('N');
            }
            else if(snakePosition[1] != 0){
                game.move('W');
            }
            else if(snakePosition[0] != 9){
                game.move('S');
            }else if(snakePosition[1] != 9){
                game.move('E');
            }
            board = game.board();
            assertTrue('\u0000'==game.board()[yPos][xPos]);

        }
        catch(HungrySnakeException e){
            fail("Did throw an exception");
        }
    }

    /**
     * Snake shouldn't move back if it has at least two of length
     */
    @Test
    public void shouldNotMoveBackIfItHasAtLeastTwoOfLength(){
        try{
            HungrySnake game = new HungrySnake(5,1,1,0);
            char[][] board = game.board();
            int snakePosY = 0;
            int fruitPosY = 0;
            for(int i = 0; i<5; i++){
                if(board[i][0] == 'F'){
                    fruitPosY = i;
                }
                else if(board[i][0] == 'H'){
                    snakePosY = i;
                }
            }
            if(snakePosY<fruitPosY){
                for(int i =0; i < fruitPosY-snakePosY; i++){
                    game.move('S');
                    game.move('N');
                }
            }
            else{
                for(int i =0; i < snakePosY-fruitPosY; i++){
                    game.move('N');
                    game.move('S');

                }
            }
            
             fail("Did throw an exception");
        }
        catch(HungrySnakeException e){
            assertEquals(HungrySnakeException.LAST_DIRECTION, e.getMessage());
        }
    }
    /**
     * Boards shouldn't be bigger than 50x50, includes you shouldn't put more than 50 rows or more than 50 columns
     */
    @Test
    public void shouldNotMakeMapsSoBig(){
        try{
            HungrySnake game = new HungrySnake(51,51,0,0);
            fail("Didn't throw an exception");
        }
        catch(HungrySnakeException e){
            assertEquals(e.getMessage(), HungrySnakeException.BOARD_TOO_BIG);
        }
    }

}
