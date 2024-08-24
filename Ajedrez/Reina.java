package Ajedrez;
import java.awt.Color;
import java.awt.Graphics;
public class Reina extends Pieza {
    public Reina(boolean esBlanca) {
        super(esBlanca);
    }
    @Override
    public boolean esMovimientoValido(int inicioX, int inicioY, int finX, int finY, Pieza[][] tablero) {
        // Movimiento en línea recta o diagonal
        boolean movimientoRecto = inicioX == finX || inicioY == finY;
        boolean movimientoDiagonal = Math.abs(finX - inicioX) == Math.abs(finY - inicioY);
        return movimientoRecto || movimientoDiagonal;
    }
    @Override
    public void dibujar(Graphics g, int x, int y) {
        g.setColor(esBlanca ? Color.WHITE : Color.BLACK);
        g.fillRect(x + 20, y + 20, 60, 60);
    }
}