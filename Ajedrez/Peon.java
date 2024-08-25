package Ajedrez;
import java.awt.Color;
import java.awt.Graphics;
public class Peon  extends Pieza {
    public Peon( boolean esBlanca){
        super(esBlanca, esBlanca ? "peonBlanco.png" : "peonNegro.png");
    }
    @Override
    public boolean esMovimientoValido (int inicioX, int inicioY, int finX, int finY, Pieza [][] tablero){
        if (esBlanca) {
            return inicioX == finX && (inicioY - 1 == finY || (inicioY == 6 && finY == 4)); 
        } else {
            return inicioX == finX && (inicioY + 1 == finY || (inicioY == 1 && finY == 3));
        }
    }
    /*@Override
    public void dibujar ( Graphics g , int x, int y ) {
        g.setColor((esBlanca ? Color.WHITE : Color.black));
        g.fillOval (x + 25, y + 25, 50, 50);
    }*/
}