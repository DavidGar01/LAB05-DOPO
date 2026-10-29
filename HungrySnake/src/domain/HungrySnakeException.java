package domain;

public class HungrySnakeException extends Exception{
    public static final String NEGATIVE_NUMBERS = "Hay números negativos";
    public static final String CANT_PUT_A_SNAKE= "No se puede asignar posición a la serpiente";
    public static final String LAST_DIRECTION = "No se puede mover a esa dirección";
    public static final String EXIT_EDGE = "La serpiente se salió de los límites del tablero, fin";

    public HungrySnakeException(String message){
        super(message);
    }
}