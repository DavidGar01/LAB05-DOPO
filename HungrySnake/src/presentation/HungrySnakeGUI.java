package presentation;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Represents a HungrySnakeGUI
 * HungrySnakeGUI
 */

public class HungrySnakeGUI extends JFrame{
    
    public HungrySnakeGUI(){
        setTitle("HungrySnake");
        prepareView();
    }

    private void prepareView(){
        setSize(Toolkit.getDefaultToolkit().getScreenSize().width/4, Toolkit.getDefaultToolkit().getScreenSize().height/4);
        setLocationRelativeTo(null);
    }

    /**
     * starts the program
     * @param args
     */
    public static void main(String[] args){
        HungrySnakeGUI gui = new HungrySnakeGUI();
        gui.setVisible(true);
    }
}



