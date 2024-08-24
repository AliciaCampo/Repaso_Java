package Ajedrez;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class JuegoAjedrez {
    private Pieza[][] tablero;
    private TableroAjedrez panelTablero;
    private boolean turnoBlanco;
    private JLabel lblTurno;

    public JuegoAjedrez() {
        tablero = new Pieza[8][8];
        inicializarTablero();
        panelTablero = new TableroAjedrez(tablero);
        turnoBlanco = true;

        panelTablero.addMouseListener(new java.awt.event.MouseAdapter() {
            private int seleccionX = -1;
            private int seleccionY = -1;

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int fila = e.getY() / panelTablero.getTamanoCasilla();
                int columna = e.getX() / panelTablero.getTamanoCasilla();

                if (seleccionX == -1 && seleccionY == -1) {
                    if (tablero[fila][columna] != null && tablero[fila][columna].esBlanca() == turnoBlanco) {
                        seleccionX = fila;
                        seleccionY = columna;
                    }
                } else {
                    if (Movimiento.esMovimientoValido(tablero[seleccionX][seleccionY], seleccionX, seleccionY, fila, columna, tablero)) {
                        moverPieza(seleccionX, seleccionY, fila, columna);
                        seleccionX = -1;
                        seleccionY = -1;
                        cambiarTurno();
                        verificarEstadoJuego();
                    } else {
                        seleccionX = -1;
                        seleccionY = -1;
                    }
                }
                panelTablero.repaint();
            }
        });
    }

    public JPanel obtenerPanelTablero() {
        JPanel panelJuego = new JPanel(new BorderLayout());
        lblTurno = new JLabel("Turno: Blancas");
        panelJuego.add(lblTurno, BorderLayout.NORTH);
        panelJuego.add(panelTablero, BorderLayout.CENTER);
        return panelJuego;
    }

    private void inicializarTablero() {
        tablero[0][0] = new Torre(true);
        tablero[0][1] = new Caballo(true);
        tablero[0][2] = new Alfil(true);
        tablero[0][3] = new Reina(true);
        tablero[0][4] = new Rey(true);
        tablero[0][5] = new Alfil(true);
        tablero[0][6] = new Caballo(true);
        tablero[0][7] = new Torre(true);
        for (int i = 0; i < 8; i++) {
            tablero[1][i] = new Peon(true);
        }

        tablero[7][0] = new Torre(false);
        tablero[7][1] = new Caballo(false);
        tablero[7][2] = new Alfil(false);
        tablero[7][3] = new Reina(false);
        tablero[7][4] = new Rey(false);
        tablero[7][5] = new Alfil(false);
        tablero[7][6] = new Caballo(false);
        tablero[7][7] = new Torre(false);
        for (int i = 0; i < 8; i++) {
            tablero[6][i] = new Peon(false);
        }
    }

    private void moverPieza(int inicioX, int inicioY, int finX, int finY) {
        Pieza pieza = tablero[inicioX][inicioY];
        tablero[finX][finY] = pieza;
        tablero[inicioX][inicioY] = null;

        if (pieza instanceof Peon && (finX == 0 || finX == 7)) {
            tablero[finX][finY] = new Reina(pieza.esBlanca());
        }
    }

    private void cambiarTurno() {
        turnoBlanco = !turnoBlanco;
        lblTurno.setText("Turno: " + (turnoBlanco ? "Blancas" : "Negras"));
    }

    private void verificarEstadoJuego() {
        boolean jaqueBlanco = estaEnJaque(true);
        boolean jaqueNegro = estaEnJaque(false);

        if (jaqueBlanco) {
            JOptionPane.showMessageDialog(null, "Las Blancas están en Jaque");
        }
        if (jaqueNegro) {
            JOptionPane.showMessageDialog(null, "Las Negras están en Jaque");
        }
    }

    private boolean estaEnJaque(boolean esBlanco) {
        int reyX = -1, reyY = -1;

        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                Pieza pieza = tablero[fila][columna];
                if (pieza instanceof Rey && pieza.esBlanca() == esBlanco) {
                    reyX = fila;
                    reyY = columna;
                    break;
                }
            }
        }

        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                Pieza pieza = tablero[fila][columna];
                if (pieza != null && pieza.esBlanca() != esBlanco) {
                    if (Movimiento.esMovimientoValido(pieza, fila, columna, reyX, reyY, tablero)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
