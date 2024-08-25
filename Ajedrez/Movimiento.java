package Ajedrez;

public class Movimiento {

    // Método para verificar si un movimiento es válido para una pieza dada
    public static boolean esMovimientoValido(Pieza pieza, int inicioX, int inicioY, int finX, int finY, Pieza[][] tablero) {
        if (pieza == null) {
            return false;
        }

        // No permitir mover fuera del tablero
        if (finX < 0 || finX > 7 || finY < 0 || finY > 7) {
            return false;
        }

        // Verifica el movimiento específico de cada pieza
        return pieza.esMovimientoValido(inicioX, inicioY, finX, finY, tablero);
    }

    // Verifica si el camino entre dos posiciones está libre de piezas
    public static boolean caminoLibre(int inicioX, int inicioY, int finX, int finY, Pieza[][] tablero) {
        int deltaX = Integer.signum(finX - inicioX);
        int deltaY = Integer.signum(finY - inicioY);
        int x = inicioX + deltaX;
        int y = inicioY + deltaY;

        while (x != finX || y != finY) {
            if (tablero[x][y] != null) {
                return false; // Hay una pieza en el camino
            }
            x += deltaX;
            y += deltaY;
        }
        return true;
    }

    // Verifica si la posición de destino está ocupada por una pieza del mismo color
    public static boolean destinoOcupadoPorMismoColor(int finX, int finY, Pieza[][] tablero, Pieza pieza) {
        Pieza piezaDestino = tablero[finX][finY];
        return piezaDestino != null && piezaDestino.esBlanca() == pieza.esBlanca();
    }

    // Verifica si el movimiento de una pieza a una posición específica es válido
    public static boolean esMovimientoReyValido(int inicioX, int inicioY, int finX, int finY) {
        int dx = Math.abs(finX - inicioX);
        int dy = Math.abs(finY - inicioY);
        return (dx <= 1 && dy <= 1); // El Rey se mueve una casilla en cualquier dirección
    }

    public static boolean esMovimientoReinaValido(int inicioX, int inicioY, int finX, int finY, Pieza[][] tablero) {
        return esMovimientoTorreValido(inicioX, inicioY, finX, finY, tablero) ||
               esMovimientoAlfilValido(inicioX, inicioY, finX, finY, tablero);
    }

    public static boolean esMovimientoTorreValido(int inicioX, int inicioY, int finX, int finY, Pieza[][] tablero) {
        if (inicioX != finX && inicioY != finY) {
            return false; // La Torre solo se mueve en línea recta
        }
        return caminoLibre(inicioX, inicioY, finX, finY, tablero);
    }

    public static boolean esMovimientoAlfilValido(int inicioX, int inicioY, int finX, int finY, Pieza[][] tablero) {
        if (Math.abs(finX - inicioX) != Math.abs(finY - inicioY)) {
            return false; // El Alfil solo se mueve en diagonales
        }
        return caminoLibre(inicioX, inicioY, finX, finY, tablero);
    }

    public static boolean esMovimientoCaballoValido(int inicioX, int inicioY, int finX, int finY) {
        int dx = Math.abs(finX - inicioX);
        int dy = Math.abs(finY - inicioY);
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2); // Movimiento en forma de L
    }

    public static boolean esMovimientoPeonValido(int inicioX, int inicioY, int finX, int finY, Pieza[][] tablero, Pieza pieza) {
        int dx = finX - inicioX;
        int dy = finY - inicioY;
        boolean esBlanca = pieza.esBlanca();
        int direccion = esBlanca ? -1 : 1;
    
        // Movimiento normal de una casilla hacia adelante
        if (dy == 0 && dx == direccion && tablero[finX][finY] == null) {
            System.out.println("Movimiento normal de peón permitido.");
            return true;
        }
    
        // Movimiento doble hacia adelante desde la posición inicial
        if (dy == 0 && dx == 2 * direccion && inicioX == (esBlanca ? 6 : 1) 
            && tablero[finX][finY] == null && tablero[inicioX + direccion][inicioY] == null) {
            System.out.println("Movimiento doble desde la posición inicial permitido.");
            return true;
        }
    
        // Captura diagonal
        if (Math.abs(dy) == 1 && dx == direccion && tablero[finX][finY] != null 
            && tablero[finX][finY].esBlanca() != esBlanca) {
            System.out.println("Captura diagonal permitida.");
            return true;
        }
    
        System.out.println("Movimiento de peón no permitido.");
        return false;
    }
}
