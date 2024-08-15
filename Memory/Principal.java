package Memory;

// Programa que contiene el juego
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.ArrayList;

public class Principal extends JFrame implements ActionListener {
    private static final int TAMAÑO = 2; // Ajustar a 4x2 para tener 8 botones
    private JButton[][] buttons = new JButton[TAMAÑO][TAMAÑO * 2]; // 4x2 grid
    private ImageIcon[] imagenes = new ImageIcon[4]; // 4 imágenes dobles
    private ImageIcon interrogante;
    private int[][] posiciones = new int[TAMAÑO][TAMAÑO * 2];
    private JButton primerButton = null;
    private JButton segundoButton = null;
    private boolean estado = false;
    private int primerRow = -1, primerCol = -1;

    public Principal() {
        // Configurar la ventana
        this.setTitle("Juego Memory");
        this.setLayout(new GridLayout(TAMAÑO, TAMAÑO * 2)); // 4x2 grid
        // Cargar imágenes
        cargaImagenes();
        // Inicializar tablero
        iniciarTablero();
        // Configurar la ventana 
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void cargaImagenes() {
        // Cargar 4 imágenes que aparecerán dos veces cada una
        for (int i = 0; i < 4; i++) {
            imagenes[i] = new ImageIcon(getClass().getResource("/Memory/Imagenes/img" + (i + 1) + ".png"));
        }
        // Cargar la imagen del interrogante
        interrogante = new ImageIcon(getClass().getResource("/Memory/Imagenes/interrogante.png"));
    }

    private void iniciarTablero() {
        // Crear una lista de posiciones de imágenes
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add(i);
            list.add(i);
        }
        Collections.shuffle(list);

        // Asignar posiciones a la matriz de posiciones
        int index = 0;
        for (int i = 0; i < TAMAÑO; i++) {
            for (int j = 0; j < TAMAÑO * 2; j++) { // 4x2 grid
                posiciones[i][j] = list.get(index++);
                buttons[i][j] = new JButton();
                buttons[i][j].setIcon(interrogante); // Mostrar interrogante en el frente
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (estado) {
            return;
        }
        JButton botonclicado = (JButton) e.getSource();
        // Buscar la posición del botón clicado 
        int row = -1, col = -1;
        for (int i = 0; i < TAMAÑO; i++) {
            for (int j = 0; j < TAMAÑO * 2; j++) {
                if (buttons[i][j] == botonclicado) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        // Mostrar la imagen en el botón
        botonclicado.setIcon(imagenes[posiciones[row][col]]);
        if (primerButton == null) {
            primerButton = botonclicado;
            primerRow = row;
            primerCol = col;
        } else if (segundoButton == null) {
            segundoButton = botonclicado;
            estado = true;

            // Comparar las imágenes usando los índices en lugar de los iconos directamente
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    verificarPareja();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void verificarPareja() {
        if (primerButton != null && segundoButton != null) {
            // Verificar si los índices de las imágenes coinciden
            if (posiciones[primerRow][primerCol] == posiciones[getButtonRow(segundoButton)][getButtonCol(segundoButton)]) {
                primerButton.setEnabled(false);
                segundoButton.setEnabled(false);
            } else {
                primerButton.setIcon(interrogante);
                segundoButton.setIcon(interrogante);
            }
            primerButton = null;
            segundoButton = null;
            estado = false;

            // Comprobar si el juego ha terminado
            if (juegoTerminado()) {
                JOptionPane.showMessageDialog(this, "¡Felicidades! Has encontrado todas las parejas.");
                System.exit(0);
            }
        }
    }

    private int getButtonRow(JButton button) {
        for (int i = 0; i < TAMAÑO; i++) {
            for (int j = 0; j < TAMAÑO * 2; j++) {
                if (buttons[i][j] == button) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int getButtonCol(JButton button) {
        for (int i = 0; i < TAMAÑO; i++) {
            for (int j = 0; j < TAMAÑO * 2; j++) {
                if (buttons[i][j] == button) {
                    return j;
                }
            }
        }
        return -1;
    }

    private boolean juegoTerminado() {
        for (int i = 0; i < TAMAÑO; i++) {
            for (int j = 0; j < TAMAÑO * 2; j++) {
                if (buttons[i][j].isEnabled()) {
                    return false;
                }
            }
        }
        return true;
    }

    // Este es el punto de entrada del programa
    public static void main(String[] args) {
        // Inicia el juego
        new Principal();
    }
}
