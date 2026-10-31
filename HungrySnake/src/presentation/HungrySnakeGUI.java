package presentation;
import domain.*;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.awt.*;
import java.util.ArrayList;

/**
 * Represents a HungrySnakeGUI
 * HungrySnakeGUI
 */

public class HungrySnakeGUI extends JFrame{
    //JFrame size
    private static final Dimension PREFERRED_DIMENSION = new Dimension (Toolkit.getDefaultToolkit().getScreenSize().width/2, Toolkit.getDefaultToolkit().getScreenSize().height/2);
    //For JPanelArea there's less 16px for width and there's less 64px for the height 

    //Model
    private HungrySnake hungrySnake;

    //Menu items
    private JMenuItem nuevo;
    private JMenuItem abrir;
    private JMenuItem salvar;
    private JMenuItem salir;
    private JMenuItem colores;
    private JMenuItem reiniciar;

    //Information items
    private JLabel length;
    private JLabel obstacles;
    private JLabel steps;
    private JLabel fruits;

    //Game
    private JPanel game;
    private JButton jugar;
    private JPanel[][] board;

    //Game attributes
    private int rows = 25;
    private int columns = 25;
    private int stepsNumber;
    private int fruitsNumber = 1;
    private double obstaclesNumber = 2;
    private char lastDirection;
    private boolean ended;


    //Game colors
    private Color snakeColor = Color.BLUE;
    private Color obstaclesColor = Color.BLACK;
    private Color fruitsColor = Color.RED;
    private Color firstFloorColor = new Color(162,208,73);
    private Color secondFloorColor = new Color(169,215,81);
    private ArrayList<Color> allColors;
    private Color colorBeforeDeath= Color.BLUE;;

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
        reiniciar = new JMenuItem("Reiniciar");
        colores = new JMenuItem("Cambiar Color");

        JMenuBar barra = new JMenuBar();
        JMenu archivo = new JMenu("Archivo");
        JMenu color = new JMenu("Colores");
        archivo.add(nuevo);
        archivo.add(reiniciar);
        archivo.addSeparator();
        archivo.add(abrir);
        archivo.add(salvar);
        archivo.addSeparator();
        archivo.add(salir);
        color.add(colores);

        

        barra.add(archivo);
        barra.add(color);
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

        length = new JLabel("0", JLabel.CENTER);
        length.setFont(new Font("Arial", Font.BOLD, 32));
        length.setForeground(snakeColor);

        JLabel snakeLength = new JLabel("Tamaño serpiente:", JLabel.CENTER);
        snakeLength.setFont(new Font("Arial", Font.BOLD, PREFERRED_DIMENSION.width/55));

        JLabel fruitsInformation = new JLabel("Cantidad de frutas:", JLabel.CENTER);
        fruitsInformation.setFont(new Font("Arial", Font.BOLD, PREFERRED_DIMENSION.width/55));

        fruits = new JLabel("0", JLabel.CENTER);
        fruits.setFont(new Font("Arial", Font.BOLD, 32));
        fruits.setForeground(fruitsColor);

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
        
        obstacles = new JLabel("0"+"%", JLabel.CENTER);
        obstacles.setFont(new Font("Arial", Font.BOLD, 32));
        obstacles.setForeground(obstaclesColor);
        steps = new JLabel("0", JLabel.CENTER);
        steps.setFont(new Font("Arial", Font.BOLD, 32));
        steps.setForeground(Color.WHITE);
        JLabel wallsInformation = new JLabel("Porcentaje de obstaculos:", JLabel.CENTER);
        wallsInformation.setFont(new Font("Arial", Font.BOLD, PREFERRED_DIMENSION.width/55));
        JLabel stepsInformation = new JLabel("Cantidad de pasos:", JLabel.CENTER);
        stepsInformation.setFont(new Font("Arial", Font.BOLD, PREFERRED_DIMENSION.width/55));


        for(int i = 0; i < 7; i++){
            if(i==1){
                panel.add(wallsInformation);
            }
            else if (i == 2){
                panel.add(obstacles);
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
        reiniciar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                restartGameAction();
            }
        });
        colores.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                changeColorAction();
            }
        });

    }
    private void prepareControllerBoard(){
        jugar.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                startGameAction();
            }
        });
        game.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    moveAction('E');
                }
                else if (e.getKeyCode() == KeyEvent.VK_LEFT){
                    moveAction('W');
                }
                else if (e.getKeyCode() == KeyEvent.VK_UP){
                    moveAction('N');
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN){
                    moveAction('S');
                }
            }

        });
    }

    private void moveAction(char direction){
        try {  
            if(!hungrySnake.move(direction)){
                ended = true;
                lastDirection = direction;
                decreaseSnakeColor();
                refresh();    
                JOptionPane.showMessageDialog(this, "¡Se acabó el juego!", "Fin", JOptionPane.INFORMATION_MESSAGE);    
                game.setFocusable(false);
                JOptionPane.showMessageDialog(this, "Estadísticas de la partida:"+"\n"+"Tamaño máximo de la serpiente: "+String.valueOf(hungrySnake.size())+"\n"+"Pasos dados: "+ String.valueOf(stepsNumber)+"\n"+"Frutas: " + String.valueOf(fruitsNumber) + "\n"+"Porcentaje de obstáculos: "+String.valueOf(obstaclesNumber)+"%", "Estadísticas partida", JOptionPane.INFORMATION_MESSAGE);
                ended = true;
            }
            else{
                lastDirection = direction;
                stepsNumber++;
                refresh();    

            }
               
            
        } catch (HungrySnakeException e) {
            if(e.getMessage().equals(HungrySnakeException.EXIT_EDGE)){
                lastDirection = direction;
                ended = true;
                decreaseSnakeColor();
                refresh();  
                JOptionPane.showMessageDialog(this, HungrySnakeException.EXIT_EDGE +"\n"+"¡Se acabó el juego!", "Fin", JOptionPane.INFORMATION_MESSAGE);
                game.setFocusable(false);
                JOptionPane.showMessageDialog(this, "Estadísticas de la partida:"+"\n"+"Tamaño máximo de la serpiente: "+String.valueOf(hungrySnake.size())+"\n"+"Pasos dados: "+ String.valueOf(stepsNumber)+"\n"+"Frutas: " + String.valueOf(fruitsNumber) + "\n"+"Porcentaje de obstáculos: "+String.valueOf(obstaclesNumber)+"%", "Estadísticas partida", JOptionPane.INFORMATION_MESSAGE);
            }
            else if(e.getMessage().equals(HungrySnakeException.WIN)){
                lastDirection = direction;
                refresh();
                JOptionPane.showMessageDialog(this, "¡Ganaste! ¡Felicidades!", "Fin", JOptionPane.INFORMATION_MESSAGE); 
                game.setFocusable(false);
                JOptionPane.showMessageDialog(this, "Estadísticas de la partida:"+"\n"+"Tamaño máximo de la serpiente: "+String.valueOf(hungrySnake.size())+"\n"+"Pasos dados: "+ String.valueOf(stepsNumber)+"\n"+"Frutas: " + String.valueOf(fruitsNumber) + "\n"+"Porcentaje de obstáculos: "+String.valueOf(obstaclesNumber)+"%", "Estadísticas partida", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE );
            }

        }

    }

    private void decreaseSnakeColor(){
        colorBeforeDeath = snakeColor;
        int red = snakeColor.getRed();
        int green = snakeColor.getGreen();
        int blue = snakeColor.getBlue();
        
        snakeColor = new Color((int)(red+(255-red)*0.5), (int)(green+(255-green)*0.5), (int)(blue+(255-blue)*0.5));
    }

    private void exitAction(){
        int selection = JOptionPane.showConfirmDialog(this, "¿Quieres salir?", "Salir", JOptionPane.YES_NO_OPTION);
        if(selection ==  JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }

    private void restartGameAction(){
        try {
            if(lastDirection != '\u0000'){
                hungrySnake = new HungrySnake(rows, columns, fruitsNumber, obstaclesNumber);
                lastDirection = 'E';
                snakeColor = colorBeforeDeath;
                length.setForeground(snakeColor);
                ended = false;
                stepsNumber = 0;
                game.setFocusable(true);
                game.requestFocusInWindow();
                game.removeAll();
                prepareGame();
                refresh();
            }else{
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error: No ha se ha iniciado la partida" , "Error",JOptionPane.ERROR_MESSAGE );
            }
            
        } catch (HungrySnakeException e) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE );
        }   
    }

    private void startGameAction(){
        stepsNumber = 0;
        String newFruitsNumber = JOptionPane.showInputDialog(this, "Dame la cantidad de frutas que va a tener: ");
        if(newFruitsNumber != null){
            try{
                fruitsNumber = Integer.parseInt(newFruitsNumber);
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(this, "El texto no es un número válido." + "\n"+"Se pondrá por defecto 1", "No es un número válido", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            fruitsNumber = 1;
        }
        String newWallsNumber = JOptionPane.showInputDialog(this, "Dame el porcentaje de obstáculos que va a tener: ");
        if(newWallsNumber != null){
            try{
                obstaclesNumber = Double.parseDouble(newWallsNumber);
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(this, "El texto no es un número válido."+ "\n"+"Se pondrá por defecto 2%", "No es un número válido", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            obstaclesNumber = 2.0;
        }
        String newRows = JOptionPane.showInputDialog(this, "Dame la cantidad de filas del mapa: ");
        if(newRows != null){
            try{
                rows = Integer.parseInt(newRows);
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(this, "El texto no es un número válido."+ "\n"+"Se pondrá por defecto 25", "No es un número válido", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            rows = 25;
        }
        String newColumns = JOptionPane.showInputDialog(this, "Dame la cantidad de columnas del mapa: ");
        if(newColumns != null){
            try{
                columns = Integer.parseInt(newColumns);
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(this, "El texto no es un número válido."+ "\n"+"Se pondrá por defecto 25", "No es un número válido", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            columns = 25;
        }
            try{
              hungrySnake = new HungrySnake(rows, columns, fruitsNumber, obstaclesNumber);
              snakeColor = colorBeforeDeath;
              length.setForeground(snakeColor);
              ended = false;
             }
            catch(HungrySnakeException e){
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE );
                startGameAction();
           };
        lastDirection = 'E';
        
        JOptionPane.showMessageDialog(this, "Mueve la serpiente con las flechas del teclado, intenta comer la mayor cantidad de frutas en el menor número de pasos sin estrellarte.", "Indicaciones", JOptionPane.INFORMATION_MESSAGE);


        game.setFocusable(true);
        game.requestFocusInWindow();
        game.removeAll();
        prepareGame();
        refresh();
    }

    private void prepareGame(){
        int maxSize = Math.max(rows, columns);
        game.setLayout(new GridLayout(maxSize, maxSize)); 
        board = new JPanel[maxSize][maxSize];
        

        for(int i = 0; i < maxSize; i++){
            for(int j = 0; j < maxSize; j++){
                board[i][j] = new JPanel();
                if(i>=rows || j >=columns){
                    board[i][j].setBackground(new Color(161, 130,98));
                }
                else if((i+j)%2 == 0){
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
        char[][] gameBoard = hungrySnake.board();
        
        for(int i = 0; i < rows; i++){
            for(int j = 0;j< columns; j++){
                board[i][j].removeAll();
                paintPanelComponents(gameBoard[i][j], i,j);
            }
        }
        

        
        obstacles.setText(String.valueOf(obstaclesNumber)+"%");
        steps.setText(String.valueOf(stepsNumber));
        fruits.setText(String.valueOf(fruitsNumber));
        length.setText(String.valueOf(hungrySnake.size()));

        game.revalidate();
        game.repaint();
    }

    private void paintPanelComponents(char c, int row, int column){
        JPanel panel = new JPanel();
        //Obstacle
        if(c == 'O'){
            panel.setBackground(obstaclesColor);
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
                panel.setBackground(firstFloorColor);
             }
            else{
                panel.setBackground(secondFloorColor);
                }
        }
        //SnakeBody
        else if(c == 'S'){
            panel.setBackground(snakeColor);
        }

        //SnakeHead
        else if(c == 'H'){
            panel = new JPanel(){
                @Override
                protected void paintComponent (Graphics g){
                    super.paintComponent(g);
                    g.setColor(snakeColor);
                    g.fillRect(0,0,getWidth(),getHeight());
                    if(lastDirection == 'E'){
                        g.setColor(Color.WHITE);
                        if(ended){
                            g.fillRect(11*getWidth()/20,4*getHeight()/20,7*getWidth()/25,4*getHeight()/10);
                        }
                        else{
                            g.fillRect(12*getWidth()/20,4*getHeight()/20,4*getWidth()/25,3*getHeight()/10);
                        }
                        g.fillRect(12*getWidth()/20,12*getHeight()/20,4*getWidth()/25,3*getHeight()/10);
                        g.setColor(Color.BLACK);
                        if(ended){
                            g.fillRect(13*getWidth()/20,5*getHeight()/20,4*getWidth()/25,3*getHeight()/10);
                        }
                        else{
                            g.fillRect(13*getWidth()/20,5*getHeight()/20,3*getWidth()/25,2*getHeight()/10);
                        }
                        g.fillRect(13*getWidth()/20,13*getHeight()/20,3*getWidth()/25,2*getHeight()/10);

                    }
                    else if(lastDirection == 'W'){
                        g.setColor(Color.WHITE);
                        g.fillRect(5*getWidth()/20,4*getHeight()/20,4*getWidth()/25,3*getHeight()/10);
                        if(ended){
                            g.fillRect(5*getWidth()/20,12*getHeight()/20,7*getWidth()/25,4*getHeight()/10);
                        }
                        else{
                            g.fillRect(5*getWidth()/20,12*getHeight()/20,4*getWidth()/25,3*getHeight()/10);
                        }
                        g.setColor(Color.BLACK);
                        g.fillRect(5*getWidth()/20,5*getHeight()/20,3*getWidth()/25,2*getHeight()/10);
                        if(ended){
                            g.fillRect(5*getWidth()/20,13*getHeight()/20,4*getWidth()/25,3*getHeight()/10);
                        }
                        else{
                            g.fillRect(5*getWidth()/20,13*getHeight()/20,3*getWidth()/25,2*getHeight()/10);
                        }
                        
                    }
                    else if(lastDirection == 'S'){
                        g.setColor(Color.WHITE);
                        g.fillRect(4*getHeight()/20,12*getWidth()/20,3*getHeight()/10,4*getWidth()/25);
                        if(ended){
                            g.fillRect(12*getHeight()/20,11*getWidth()/20,4*getHeight()/10,7*getWidth()/25);
                        }
                        else{
                            g.fillRect(12*getHeight()/20,12*getWidth()/20,3*getHeight()/10,4*getWidth()/25);
                        }
                        g.setColor(Color.BLACK);
                        g.fillRect(5*getHeight()/20,13*getWidth()/20,2*getHeight()/10,3*getWidth()/25);
                        if(ended){
                            g.fillRect(13*getHeight()/20,13*getWidth()/20,3*getHeight()/10,4*getWidth()/25);
                        }
                        else{
                            g.fillRect(13*getHeight()/20,13*getWidth()/20,2*getHeight()/10,3*getWidth()/25);
                        }
                    }
                    else if(lastDirection == 'N'){
                        g.setColor(Color.WHITE);
                        g.fillRect(4*getHeight()/20,4*getWidth()/20,3*getHeight()/10,4*getWidth()/25);
                        if(ended){
                            g.fillRect(12*getHeight()/20,4*getWidth()/20,4*getHeight()/10,7*getWidth()/25);
                        }
                        else{
                            g.fillRect(12*getHeight()/20,4*getWidth()/20,3*getHeight()/10,4*getWidth()/25);
                        }
                        
                        g.setColor(Color.BLACK);
                        g.fillRect(5*getHeight()/20,4*getWidth()/20,2*getHeight()/10,3*getWidth()/25);
                        if(ended){
                            g.fillRect(13*getHeight()/20,4*getWidth()/20,3*getHeight()/10,4*getWidth()/25);
                        }
                        else{
                            g.fillRect(13*getHeight()/20,4*getWidth()/20,2*getHeight()/10,3*getWidth()/25);
                        }
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


    private void changeColorAction(){
        String[] opciones = {"Frutas","Serpiente","Obstáculos","Piso 1", "Piso 2"};
                int opcion = JOptionPane.showOptionDialog( this, "¿A que le quieres cambiar el color?", "Cambiar color", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, "Caja");
                if(opcion != -1){
                    Color color1 = JColorChooser.showDialog( this, "Selecciona un color", Color.ORANGE);
                    allColors = new ArrayList<>();
                    allColors.add(snakeColor);
                    allColors.add(obstaclesColor);
                    allColors.add(fruitsColor);
                    allColors.add(firstFloorColor);
                    allColors.add(secondFloorColor);
                    boolean isIn = false;
                    
                    if(allColors.contains(color1)){
                        JOptionPane.showMessageDialog(this, "Este color ya está en uso", "Color", JOptionPane.ERROR_MESSAGE);
                        isIn = true;
                    }
                    

                    if(!isIn && color1 != null){
                        
                        if (opcion == 0){
                            fruitsColor = color1;
                            fruits.setForeground(fruitsColor);
                        }
                        else if(opcion == 1){
                            snakeColor = color1;
                            length.setForeground(snakeColor);
                            if(ended){
                                colorBeforeDeath = color1;
                            }
                        }
                        else if(opcion == 2) {
                            obstaclesColor = color1;
                            obstacles.setForeground(obstaclesColor);
                        }
                        else if(opcion == 3){
                            firstFloorColor = color1;

                        }else{
                            secondFloorColor = color1;

                        }
                        refresh();
                    }
                    
        }

                    
                    
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





