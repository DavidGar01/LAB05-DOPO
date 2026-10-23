package presentation;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.JFileChooser;

/**
 * Represents a HungrySnakeGUI
 * HungrySnakeGUI
 */

public class HungrySnakeGUI extends JFrame{
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
        setSize(Toolkit.getDefaultToolkit().getScreenSize().width/4, Toolkit.getDefaultToolkit().getScreenSize().height/4);
        setLocationRelativeTo(null);
        nuevo = new JMenuItem("Nuevo");
        abrir = new JMenuItem("Abrir");
        salvar = new JMenuItem("Salvar");
        salir = new JMenuItem("Salir");
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
        JMenuBar barra = new JMenuBar();
        JMenu opciones = new JMenu("Opciones");
        opciones.add(nuevo);
        opciones.add(abrir);
        opciones.addSeparator();
        opciones.add(salvar);
        opciones.add(salir);

        barra.add(opciones);
        return barra;
    }
    private void prepareControllerMenu(){
        salir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                exitAction();
            }
        });

        salvar.addActionListener(new ActionListener(){
            @Override
            public void actionPerfomed(ActionEvent e){
                actionSave();
           } 
        });

        abrir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                actionOpen();
            }
        });
    }
    private void actionOpen(){
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(this);
        File archivo = chooser.getSelectedFile();
        JOptionPane.showMessageDialog(this, "Acción en construcción:\n" +"Acción abrir\n"+"Nombre del archivo: "+ archivo.getName(), "Abrir", JOptionPane.INFORMATION_MESSAGE);
    }

    private void actionSave(){
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



