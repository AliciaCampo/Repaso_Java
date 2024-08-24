package Ajedrez;

import javax.swing.JPanel;

public class JuegoAjedrez {
    private TableroAjedrez tableroAjedrez;

    public JuegoAjedrez() {
        Pieza[][] tablero = new Pieza[8][8];
        // Inicializar las piezas en el tablero
        // Por ejemplo: tablero[0][0] = new Torre(true);
        tableroAjedrez = new TableroAjedrez(tablero);
    }

    public JPanel obtenerPanelTablero() {
        return tableroAjedrez;
    }
}

