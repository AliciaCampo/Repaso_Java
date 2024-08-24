package Ajedrez;
import java.awt.Graphics;
public abstract class Pieza {
    protected boolean esBlanca;
    public Pieza(boolean esBlanca) {
        this.esBlanca = esBlanca;
    }
    public boolean esBlanca() {
        return esBlanca;
    }
    public abstract boolean esMovimientoValido(int inicioX, int inicioY, int finX, int finY, Pieza[][] tablero);
    public abstract void dibujar(Graphics g, int x, int y);
}