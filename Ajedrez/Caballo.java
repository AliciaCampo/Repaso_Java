package Ajedrez;
import java.awt.Color;
import java.awt.Graphics;
public class Caballo extends Pieza {
    public Caballo(boolean esBlanca) {
        super(esBlanca);
    }
    @Override
    public boolean esMovimientoValido(int inicioX, int inicioY, int finX, int finY, Pieza[][] tablero) {
        // Movimiento en "L": dos en una dirección y una en la perpendicular
        int dx = Math.abs(finX - inicioX);
        int dy = Math.abs(finY - inicioY);
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }
    @Override
    public void dibujar(Graphics g, int x, int y) {
        g.setColor(esBlanca ? Color.WHITE : Color.BLACK);
        g.fillRect(x + 20, y + 20, 60, 60);
    }
}