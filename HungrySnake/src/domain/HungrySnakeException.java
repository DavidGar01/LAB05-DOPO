package domain;

public class HungrySnakeException extends Exception{
    public static final String NUMBER_NEGATIVES = "Hay numeros negativos";
    public static final String CANT_PUT_A_SNAKE= "No se puede asignar posicion a la serpiente";
    public static final String LAST_DIRECTION = "No se puede mover a esa direccion";
    public static final String EXIT_EDGE = "La serpiete se salio de los limites del tablero, fin";

    public HungrySnakeException(String message){
        super(message);
    }
}