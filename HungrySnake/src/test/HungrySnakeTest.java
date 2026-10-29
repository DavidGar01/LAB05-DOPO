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

}
