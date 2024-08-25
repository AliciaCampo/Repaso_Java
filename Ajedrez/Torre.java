package Ajedrez;
import java.awt.Color;
import java.awt.Graphics;
public class Torre extends Pieza {
    public Torre (boolean esBlanca){
        super(esBlanca, esBlanca ? "torreBlanco.png" : "torreNegro.png");
    }
    @Override
    public boolean esMovimientoValido (int inicioX, int inicioY, int finX, int finY, Pieza[][] tablero) {
        return inicioX == finX || inicioY == finY; //movimiento recto
    }
    /*@Override
    public void dibujar (Graphics g, int x , int y){
        g.setColor(esBlanca ?  Color.WHITE : Color.black);
        g.fillRect(x + 20 , y + 20 ,60 ,60);
    }*/
}