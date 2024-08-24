package Ajedrez;
import java.awt.Color;
import java.awt.Graphics;
public class Alfil extends Pieza {
    public Alfil(boolean esBlanca) {
        super(esBlanca);
    }
    @Override
    public boolean esMovimientoValido(int inicioX, int inicioY, int finX, int finY, Pieza[][] tablero) {
        // Movimiento diagonal
        return Math.abs(finX - inicioX) == Math.abs(finY - inicioY);
    }
    @Override
    public void dibujar(Graphics g, int x, int y) {
        g.setColor(esBlanca ? Color.WHITE : Color.BLACK);
        g.fillRect(x + 20, y + 20, 60, 60);
    }
}