package Ajedrez;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableroAjedrez extends JPanel {
    private final int TAMANO_CASILLA = 100;
    private Pieza [][] tablero ; 

    public TableroAjedrez (Pieza [] [] tablero){
        this.tablero =  tablero ;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                int fila  = e.getX() / TAMANO_CASILLA ;
                int columna =  e.getY() / TAMANO_CASILLA;
                //logica para mover piezas
            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int fila = 0; fila < 8; fila ++){
            for (int columna = 0; columna < 8; columna++){
                if ((fila + columna) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.gray);
                }
                g.fillRect(columna * TAMANO_CASILLA, fila * TAMANO_CASILLA, TAMANO_CASILLA, TAMANO_CASILLA);
                Pieza pieza  = tablero [fila] [columna] ;
                if (pieza != null){
                    pieza.dibujar(g,columna * TAMANO_CASILLA,fila *TAMANO_CASILLA);
                }
            }
        }
    }
}
