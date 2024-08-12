package Memory;
//programa que contiene el juego 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.ArrayList;
public class Principal extends JFrame implements ActionListener  {
    private static final int TAMAÑO = 4;
    private JButton [][] buttons = new JButton[TAMAÑO][TAMAÑO];
    private ImageIcon [] imagenes = new ImageIcon[8];
    private ImageIcon interrogante;
    private int [][] posiciones = new int [TAMAÑO][TAMAÑO];
    private JButton primerButton = null;
    private JButton segundoButton = null;
    private boolean estado = false;

    public Principal () {
        //configurar la ventana
        setTitle("Juego Memory");
        setLayout(new GridLayout(TAMAÑO,TAMAÑO));
        //cargar imagenes
        cargaImagenes();
        //inicializar tablero
        iniciarTablero();
        //configurar la ventana 
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void cargaImagenes() {
        for (int i = 0 ; i < 8 ; i++){
            imagenes[i]= new ImageIcon("Imagenes/img" + (i % 4 + 1 ) + ".png");
        }
        interrogante = new ImageIcon("Imagenes/interrogante.png");
        
    }

    private void iniciarTablero() {
        //crear una lista de posiciones de imagenes 
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add(i);
            list.add(i);
        }
        Collections.shuffle(list);
        //Asignar posiciones a la matriz de posiciones
        int index = 0;
        for (int i = 0; i < TAMAÑO; i++) {
            for (int j = 0 ; j < TAMAÑO ; j++) {
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
        JButton botonclicado =  (JButton) e.getSource();
        //Buscar la posicion del boton clicado 
        int row = -1 , col = -1 ;
        for (int i = 0 ; i <  TAMAÑO; i++) {
            for (int j = 0 ; j < TAMAÑO ; j ++){
                if (buttons[i][j] == botonclicado){
                    row = i ;
                    col = j;
                    break;
                }
            }
        }
        //mostrar la imagen en el boton 
        botonclicado.setIcon(imagenes[posiciones[row][col]]);
        if (primerButton == null){
            primerButton =  botonclicado;

        } else if (segundoButton == null) {
            segundoButton = botonclicado ;
            estado = true;
            //comparar las imagenes 
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
        if (primerButton.getIcon().equals(segundoButton.getIcon())) {
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
    private boolean juegoTerminado () {
        for (int i = 0 ; i < TAMAÑO ; i ++){
            for (int j = 0 ; j < TAMAÑO ; j ++){
                if (buttons[i][j].isEnabled()){
                    return false;
                }
            }
        }
        return false;
    }
     // Este es el punto de entrada del programa
     public static void main(String[] args) {
        // Inicia el juego
        new Principal();
    }
}
