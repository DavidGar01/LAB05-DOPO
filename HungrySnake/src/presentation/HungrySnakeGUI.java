package presentation;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.awt.*;

/**
 * Represents a HungrySnakeGUI
 * HungrySnakeGUI
 */

public class HungrySnakeGUI extends JFrame{
    //JFrame size
    private static final Dimension PREFERRED_DIMENSION = new Dimension (Toolkit.getDefaultToolkit().getScreenSize().width/2, Toolkit.getDefaultToolkit().getScreenSize().height/2);
    //For JPanelArea there's less 16px for width and there's less 64px for the height 

    //Menu items
    private JMenuItem nuevo;
    private JMenuItem abrir;
    private JMenuItem salvar;
    private JMenuItem salir;

    //Information items
    private JLabel length;
    private JLabel walls;
    private JLabel steps;
    private JLabel fruits;

    //Game
    private JPanel game;
    private JButton jugar;
    private JPanel[][] board;

    //Game attributes
    private int rows = 25;
    private int columns = 25;
    private int stepsNumber = 0;
    private int fruitsNumber = 1;
    private double wallsNumber = 2;
    private char lastDirection = 'N';

    //Game colors
    private Color snakeColor = Color.BLUE;
    private Color wallsColor = Color.BLACK;
    private Color fruitsColor = Color.RED;
    private Color firstFloorColor = new Color(162,208,73);
    private Color secondFloorColor = new Color(169,215,81);

    public HungrySnakeGUI(){
        setTitle("HungrySnake");
        prepareView();
        prepareController();
    }

    private void prepareView(){
        setSize(PREFERRED_DIMENSION);
        setLocationRelativeTo(null);
        setResizable(false);
        setJMenuBar(prepareViewMenu());
        add(prepareViewBoard());
    }

    private JMenuBar prepareViewMenu(){
        nuevo = new JMenuItem("Nuevo");
        abrir = new JMenuItem("Abrir");
        salvar = new JMenuItem("Salvar");
        salir = new JMenuItem("Salir");
        JMenuBar barra = new JMenuBar();
        JMenu archivo = new JMenu("Archivo");
        archivo.add(nuevo);
        archivo.addSeparator();
        archivo.add(abrir);
        archivo.add(salvar);
        archivo.addSeparator();
        archivo.add(salir);

        barra.add(archivo);
        return barra;
    }

    private JPanel prepareViewBoard(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(prepareInformationLeftArea(), BorderLayout.WEST);
        panel.add(preparePlayArea(), BorderLayout.CENTER);
        panel.add(prepareInformationRightArea(), BorderLayout.EAST);
        return panel;
    }

    private JPanel prepareInformationLeftArea(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7,1));
        panel.setPreferredSize(new Dimension((PREFERRED_DIMENSION.width -  PREFERRED_DIMENSION.height)/2,0));
        panel.setBackground(new Color(86,138,53));

        length = new JLabel("1", JLabel.CENTER);
        length.setFont(new Font("Arial", Font.BOLD, 32));
        length.setForeground(Color.WHITE);

        JLabel snakeLength = new JLabel("Tamaño serpiente:", JLabel.CENTER);
        snakeLength.setFont(new Font("Arial", Font.BOLD, PREFERRED_DIMENSION.width/50));

        JLabel fruitsInformation = new JLabel("Cantidad de frutas:", JLabel.CENTER);
        fruitsInformation.setFont(new Font("Arial", Font.BOLD, PREFERRED_DIMENSION.width/50));

        fruits = new JLabel(String.valueOf(fruitsNumber), JLabel.CENTER);
        fruits.setFont(new Font("Arial", Font.BOLD, 32));
        fruits.setForeground(Color.WHITE);

        for(int i = 0; i < 7; i++){
            if(i==1){
                panel.add(snakeLength);
            }
            else if (i == 2){
                panel.add(length);
            }
            else if(i==4){
                panel.add(fruitsInformation);
            }
            else if (i == 5){
                panel.add(fruits);
            }
            else{
                panel.add(new JLabel());
            }
        }

        return panel;
    }
    private JPanel preparePlayArea(){
        game = new JPanel();
        game.setBackground(new Color(161, 130,98));
        game.setPreferredSize(new Dimension(PREFERRED_DIMENSION.width -  PREFERRED_DIMENSION.height,0));
        game.setLayout(new BorderLayout());
        
        JLabel panel1 = new JLabel();
        panel1.setPreferredSize(new Dimension(0,(PREFERRED_DIMENSION.width -  PREFERRED_DIMENSION.height)/8));
        game.add(panel1, BorderLayout.NORTH);

        JLabel panel2 = new JLabel();
        panel2.setPreferredSize(new Dimension(0,(PREFERRED_DIMENSION.width -  PREFERRED_DIMENSION.height)/8));
        game.add(panel2, BorderLayout.SOUTH);

        JLabel panel3 = new JLabel();
        panel3.setPreferredSize(new Dimension((PREFERRED_DIMENSION.width -  PREFERRED_DIMENSION.height)/8,0));
        game.add(panel3, BorderLayout.WEST);

        JLabel panel4 = new JLabel();
        panel4.setPreferredSize(new Dimension((PREFERRED_DIMENSION.width -  PREFERRED_DIMENSION.height)/8,0));
        game.add(panel4, BorderLayout.EAST);

        game.add(prepareStartArea(), BorderLayout.CENTER);
        return game;
    }

    private JPanel prepareStartArea(){
        JPanel gameStart = new JPanel();
        gameStart.setLayout(new GridLayout(7,3));
        gameStart.setBackground(new Color(217,217,217));

        

        JLabel hungryWord = new JLabel("Hungry", JLabel.CENTER);
        hungryWord.setFont(new Font("Arial", Font.BOLD, PREFERRED_DIMENSION.width/30));
        hungryWord.setForeground(snakeColor);

        JLabel snakeWord = new JLabel("Snake", JLabel.CENTER);
        snakeWord.setFont(new Font("Arial", Font.BOLD, PREFERRED_DIMENSION.width/30));
        snakeWord.setForeground(snakeColor);

        JLabel gameWord = new JLabel("Game", JLabel.CENTER);
        gameWord.setFont(new Font("Arial", Font.BOLD, PREFERRED_DIMENSION.width/30));
        gameWord.setForeground(snakeColor);

        jugar = new JButton("Jugar");
        jugar.setFont(new Font("Arial", Font.BOLD, PREFERRED_DIMENSION.width/40));

        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 3; j++){  
                if (i == 1 && j == 1){
                  gameStart.add(hungryWord);
                }
                else if (i ==2 && j == 1){
                  gameStart.add(snakeWord);
                }
                else if(i == 3 && j == 1){
                    gameStart.add(gameWord);
                }
                else if (i == 5 && j == 1){
                  gameStart.add(jugar);
                }
                else{
                  gameStart.add(new JLabel());
                }
            }
        }

        return gameStart;
    }

    private JPanel prepareInformationRightArea(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7,1));
        panel.setPreferredSize(new Dimension((PREFERRED_DIMENSION.width -  PREFERRED_DIMENSION.height)/2,0));
  
        panel.setBackground(new Color(86,138,53));
        
        walls = new JLabel(String.valueOf(wallsNumber)+"%", JLabel.CENTER);
        walls.setFont(new Font("Arial", Font.BOLD, 32));
        walls.setForeground(Color.WHITE);
        steps = new JLabel(String.valueOf(stepsNumber), JLabel.CENTER);
        steps.setFont(new Font("Arial", Font.BOLD, 32));
        steps.setForeground(Color.WHITE);
        JLabel wallsInformation = new JLabel("Porcentaje de muros:", JLabel.CENTER);
        wallsInformation.setFont(new Font("Arial", Font.BOLD, PREFERRED_DIMENSION.width/50));
        JLabel stepsInformation = new JLabel("Cantidad de pasos:", JLabel.CENTER);
        stepsInformation.setFont(new Font("Arial", Font.BOLD, PREFERRED_DIMENSION.width/50));


        for(int i = 0; i < 7; i++){
            if(i==1){
                panel.add(wallsInformation);
            }
            else if (i == 2){
                panel.add(walls);
            }
            else if(i==4){
                panel.add(stepsInformation);
            }
            else if (i == 5){
                panel.add(steps);
            }
            else{
                panel.add(new JLabel());
            }
        }
        return panel;
    }

    private void prepareController(){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                exitAction();
            }
        }
        );

        prepareControllerMenu();
        prepareControllerBoard();
    }
    private void prepareControllerMenu(){
        salir.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                exitAction();
            }
        });

        salvar.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                saveAction();
            }
        });

        abrir.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                openAction();
            }
        });
        nuevo.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                startGameAction();
            }
        });
    }
    private void prepareControllerBoard(){
        jugar.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                startGameAction();
            }
        });
    }

    private void exitAction(){
        int selection = JOptionPane.showConfirmDialog(this, "¿Quieres salir?", "Salir", JOptionPane.YES_NO_OPTION);
        if(selection ==  JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }

    private void startGameAction(){
        String newFruitsNumber = JOptionPane.showInputDialog(this, "Dame la cantidad de frutas que va a tener: ");
        if(newFruitsNumber != null){
            try{
                fruitsNumber = Integer.parseInt(newFruitsNumber);
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(this, "El texto no es un número válido", "No es un número válido", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            fruitsNumber = 1;
        }
        String newWallsNumber = JOptionPane.showInputDialog(this, "Dame el porcentaje de muros que va a tener: ");
        if(newWallsNumber != null){
            try{
                wallsNumber = Double.parseDouble(newWallsNumber);
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(this, "El texto no es un número válido", "No es un número válido", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            wallsNumber = 1;
        }

        game.removeAll();
        prepareGame();
        refresh();
        game.revalidate();
        game.repaint();
    }

    private void prepareGame(){
        game.setLayout(new GridLayout(rows, columns));
        board = new JPanel[rows][columns];

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                board[i][j] = new JPanel();
                if((i+j)%2 == 0){
                    board[i][j].setBackground(firstFloorColor);
                }
                else{
                    board[i][j].setBackground(secondFloorColor);
                }
                board[i][j].setLayout(new BorderLayout());
                game.add(board[i][j]);
            }
        }
    }
    
    private void refresh(){
        int[] snakePosition = new int[]{3,3};
        int[] wall1Position = new int[]{12,2};
        int[] wall2Position = new int[]{5,5};
        int[] fruitPosition = new int[]{10,8};

        paintPanelComponents('H',snakePosition[0], snakePosition[1] );
        paintPanelComponents('O',wall1Position[0], wall1Position[1] );
        paintPanelComponents('O',wall2Position[0], wall2Position[1] );
        paintPanelComponents('F',fruitPosition[0], fruitPosition[1] );

        walls.setText(String.valueOf(wallsNumber)+"%");
        steps.setText(String.valueOf(stepsNumber));
        fruits.setText(String.valueOf(fruitsNumber));
    }

    private void paintPanelComponents(char c, int row, int column){
        JPanel panel = new JPanel();
        //Wall
        if(c == 'O'){
            panel.setBackground(wallsColor);
        }
        //Fruit
        else if(c == 'F'){
            panel = new JPanel(){
                @Override
                protected void paintComponent (Graphics g){
                    super.paintComponent(g);
                    g.setColor(fruitsColor);
                    g.fillOval(getWidth()/10,getHeight()/10,4*getWidth()/5,4*getHeight()/5);
                    g.setColor(new Color(161, 130,98));
                    g.fillRect(9*getWidth()/20,-2*getHeight()/20,4*getWidth()/25,4*getHeight()/10);
                }
            };
            if((row+column)%2 == 0){
                panel.setBackground(new Color(162,208,73));
             }
            else{
                panel.setBackground(new Color(169,215,81));
                }
        }
        //SnakeBody
        else if(c == 'S'){
            panel.setBackground(snakeColor);
        }

        //SnakeHead
        else if(c == 'H'){
            panel = new JPanel(){
            
                protected void paintComponent (Graphics g){
                    super.paintComponent(g);
                    g.setColor(snakeColor);
                    g.fillRect(0,0,getWidth(),getHeight());
                    if(lastDirection == 'E'){
                        g.setColor(Color.WHITE);
                        g.fillRect(12*getWidth()/20,4*getHeight()/20,4*getWidth()/25,3*getHeight()/10);
                        g.fillRect(12*getWidth()/20,12*getHeight()/20,4*getWidth()/25,3*getHeight()/10);
                        g.setColor(Color.BLACK);
                        g.fillRect(13*getWidth()/20,5*getHeight()/20,3*getWidth()/25,2*getHeight()/10);
                        g.fillRect(13*getWidth()/20,13*getHeight()/20,3*getWidth()/25,2*getHeight()/10);

                    }
                    else if(lastDirection == 'W'){
                        g.setColor(Color.WHITE);
                        g.fillRect(5*getWidth()/20,4*getHeight()/20,4*getWidth()/25,3*getHeight()/10);
                        g.fillRect(5*getWidth()/20,12*getHeight()/20,4*getWidth()/25,3*getHeight()/10);
                        g.setColor(Color.BLACK);
                        g.fillRect(5*getWidth()/20,5*getHeight()/20,3*getWidth()/25,2*getHeight()/10);
                        g.fillRect(5*getWidth()/20,13*getHeight()/20,3*getWidth()/25,2*getHeight()/10);
                    }
                    else if(lastDirection == 'S'){
                        g.setColor(Color.WHITE);
                        g.fillRect(4*getHeight()/20,12*getWidth()/20,3*getHeight()/10,4*getWidth()/25);
                        g.fillRect(12*getHeight()/20,12*getWidth()/20,3*getHeight()/10,4*getWidth()/25);
                        g.setColor(Color.BLACK);
                        g.fillRect(5*getHeight()/20,13*getWidth()/20,2*getHeight()/10,3*getWidth()/25);
                        g.fillRect(13*getHeight()/20,13*getWidth()/20,2*getHeight()/10,3*getWidth()/25);
                    }
                    else if(lastDirection == 'N'){
                        g.setColor(Color.WHITE);
                        g.fillRect(4*getHeight()/20,4*getWidth()/20,3*getHeight()/10,4*getWidth()/25);
                        g.fillRect(12*getHeight()/20,4*getWidth()/20,3*getHeight()/10,4*getWidth()/25);
                        g.setColor(Color.BLACK);
                        g.fillRect(5*getHeight()/20,4*getWidth()/20,2*getHeight()/10,3*getWidth()/25);
                        g.fillRect(13*getHeight()/20,4*getWidth()/20,2*getHeight()/10,3*getWidth()/25);
                    }
                }
            };
        }
        else{
            if((row + column)%2 == 0){
                panel.setBackground(firstFloorColor);
            }
            else{
                panel.setBackground(secondFloorColor);
            }
            
        }
        board[row][column].add(panel);
    }

    
    private void openAction(){
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(this);
        File archivo = chooser.getSelectedFile();
        if(archivo == null){
            return;
        }
        JOptionPane.showMessageDialog(this, "Acción en construcción:\n" +"Acción abrir\n"+"Nombre del archivo: "+ archivo.getName(), "Abrir", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveAction(){
        JFileChooser chooser = new JFileChooser();
        chooser.showSaveDialog(this);
        File archivo = chooser.getSelectedFile();
        if(archivo == null){
            return;
        }
        JOptionPane.showMessageDialog(this, "Acción en construcción:\n" +"Acción salvar\n"+"Nombre del archivo: "+ archivo.getName(), "Salvar", JOptionPane.INFORMATION_MESSAGE);
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





