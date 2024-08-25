package Ajedrez;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class Pieza {
    protected boolean esBlanca;
    private Image imagen;
    public Pieza(boolean esBlanca , String nombreImagen) {
        this.esBlanca = esBlanca;
        this.imagen = new ImageIcon(getClass().getResource("/Ajedrez/" + nombreImagen)).getImage();
    }
    public boolean esBlanca() {
        return esBlanca;
    }
    public abstract boolean esMovimientoValido(int inicioX, int inicioY, int finX, int finY, Pieza[][] tablero);
    public void dibujar(Graphics g, int x, int y) {
        int anchoImagen = imagen.getWidth(null);
        int alturaImagen = imagen.getHeight(null);
        //calculo de la posición centrada
        int xCentrada = x + (100 - anchoImagen) /2;
        int yCentrada = y + (100 - alturaImagen) /2;
        g.drawImage(imagen, xCentrada, yCentrada, null);
    }
}