package Memory;

// Programa que contiene el juego
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.ArrayList;

public class Principal extends JFrame implements ActionListener {
    private static final int TAMAÑO = 3; // Ajuste a 3x3 para tener 9 botones
    private JButton[][] buttons = new JButton[TAMAÑO][TAMAÑO];
    private ImageIcon[] imagenes = new ImageIcon[5]; // 4 imágenes dobles + 1 interrogante
    private ImageIcon interrogante;
    private int[][] posiciones = new int[TAMAÑO][TAMAÑO];
    private JButton primerButton = null;
    private JButton segundoButton = null;
    private boolean estado = false;
    private int primerRow = -1, primerCol = -1;

    public Principal() {
        // Configurar la ventana
        this.setTitle("Juego Memory");
        this.setLayout(new GridLayout(TAMAÑO, TAMAÑO));
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
        // Asignar la imagen del interrogante en la última posición
        imagenes[4] = interrogante;
    }

    private void iniciarTablero() {
        // Crear una lista de posiciones de imágenes
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add(i);
            list.add(i);
        }
        list.add(4); // Añadir la imagen del interrogante
        Collections.shuffle(list);

        // Asignar posiciones a la matriz de posiciones
        int index = 0;
        for (int i = 0; i < TAMAÑO; i++) {
            for (int j = 0; j < TAMAÑO; j++) {
                posiciones[i][j] = list.get(index++);
                buttons[i][j] = new JButton();
                buttons[i][j].setIcon(interrogante);
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
            for (int j = 0; j < TAMAÑO; j++) {
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
            for (int j = 0; j < TAMAÑO; j++) {
                if (buttons[i][j] == button) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int getButtonCol(JButton button) {
        for (int i = 0; i < TAMAÑO; i++) {
            for (int j = 0; j < TAMAÑO; j++) {
                if (buttons[i][j] == button) {
                    return j;
                }
            }
        }
        return -1;
    }

    private boolean juegoTerminado() {
        for (int i = 0; i < TAMAÑO; i++) {
            for (int j = 0; j < TAMAÑO; j++) {
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
