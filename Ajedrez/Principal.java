package Ajedrez;

import javax.swing.JFrame;

public class Principal {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Juego de Ajedrez");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(875,900);
        JuegoAjedrez juego = new JuegoAjedrez(); 
        //actualización del tablero
        ventana.add(juego.obtenerPanelTablero());
        ventana.setVisible(true);
    }   
}