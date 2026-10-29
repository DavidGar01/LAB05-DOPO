package presentation;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

/**
 * Represents a HungrySnakeGUI
 * HungrySnakeGUI
 */

public class HungrySnakeGUI extends JFrame{
    private static final Dimension PREFERRED_DIMENSION = new Dimension (Toolkit.getDefaultToolkit().getScreenSize().width/2, Toolkit.getDefaultToolkit().getScreenSize().height/2);

    private JMenuItem nuevo;
    private JMenuItem abrir;
    private JMenuItem salvar;
    private JMenuItem salir;

    public HungrySnakeGUI(){
        setTitle("HungrySnake");
        prepareView();
        prepareController();
    }

    private void prepareView(){
        setSize(600,600);
        setLocationRelativeTo(null);
        setResizable(false);
        setJMenuBar(prepareViewMenu());
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

    }

    private void exitAction(){
        int selection = JOptionPane.showConfirmDialog(this, "¿Quieres salir?", "Salir", JOptionPane.YES_NO_OPTION);
        if(selection ==  JOptionPane.YES_OPTION){
            System.exit(0);
        }
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
    }
    private void openAction(){
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(this);
        File archivo = chooser.getSelectedFile();
        JOptionPane.showMessageDialog(this, "Acción en construcción:\n" +"Acción abrir\n"+"Nombre del archivo: "+ archivo.getName(), "Abrir", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveAction(){
        JFileChooser chooser = new JFileChooser();
        chooser.showSaveDialog(this);
        File archivo = chooser.getSelectedFile();
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



