import java.util.*;
import java.io.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    static char[][] tablero;
    static int tam;
    static int jugFila, jugCol;
    static int sFila, sCol;
    static int barreras;
    static int antidotos;
    static int nivel;
    static boolean jugando = true;
    static int puntuacion = 0;
    static int virusInicialFila;
    static int virusInicialCol;

    static final String RESET = "\u001B[0m";
    static final String ROJO = "\u001B[31m";
    static final String VERDE = "\u001B[32m";
    static final String AMARILLO = "\u001B[33m";
    static final String CYAN = "\u001B[36m";
    static final String GRIS = "\u001B[37m";

    public static void main(String[] args) {
        menuPrincipal();
    }

    static void menuPrincipal() {
        while (true) {
            System.out.println("=== VIRUS OUTBREAK ===");
            System.out.println("1. Nuevo juego");
            System.out.println("2. Cargar partida");
            System.out.println("3. Salir");
            System.out.print("→ ");
            int op = sc.nextInt();

            switch (op) {
                case 1 -> iniciarJuego();
                case 2 -> cargarPartida();
                case 3 -> { return; }
            }
        }
    }

    static void iniciarJuego() {
        System.out.println("1. Fácil");
        System.out.println("2. Difícil");
        System.out.println("3. Extremo");
        nivel = sc.nextInt();

        switch (nivel) {
            case 1 -> { tam = 10; barreras = 8; antidotos = 3; }
            case 2 -> { tam = 8; barreras = 6; antidotos = 2; }
            case 3 -> { tam = 6; barreras = 4; antidotos = 1; }
            default -> { tam = 8; barreras = 6; antidotos = 2; }
        }

        tablero = new char[tam][tam];
        for (int i = 0; i < tam; i++) Arrays.fill(tablero[i], 'O');

        jugFila = rand.nextInt(tam);
        jugCol = rand.nextInt(tam);
        tablero[jugFila][jugCol] = 'P';

        do {
            sFila = rand.nextInt(tam);
            sCol = rand.nextInt(tam);
        } while (sFila == jugFila && sCol == jugCol);
        tablero[sFila][sCol] = 'S';

        int vf, vc;
        do {
            vf = rand.nextInt(tam);
            vc = rand.nextInt(tam);
        } while (tablero[vf][vc] != 'O');
        tablero[vf][vc] = 'V';
        virusInicialFila = vf;
        virusInicialCol = vc;

        loopJuego();
    }

    static void loopJuego() {
        jugando = true;

        while (jugando) {
            boolean pasarRonda = false;

            mostrarTablero();
            System.out.println("Barreras: " + barreras + " | Antídotos: " + antidotos);
            System.out.println("WASD mover | 2 barrera | 3 antídoto | 4 guardar | 5 salir");
            String accion = sc.next().toUpperCase();

            switch (accion) {
                case "W", "A", "S", "D" -> pasarRonda = moverJugador(accion);
                case "2" -> {
                    System.out.print("Dirección barrera (W/A/S/D): ");
                    String dir = sc.next().toUpperCase();
                    pasarRonda = colocarBarrera(dir);
                }
                case "3" -> {
                if (antidotos > 0) {
                    usarAntidoto();
                    pasarRonda = true;
                } else {
                    pasarRonda = false;
                }
            }
                case "4" -> pasarRonda = false;
                case "5" -> { return; }
                default -> pasarRonda = false;
            }

            validarEstado();
            if (!jugando) break;

            if (pasarRonda) {
                propagarVirus();
                validarEstado();
            }
        }
    }

    static boolean moverJugador(String dir) {
    int nf = jugFila, nc = jugCol;

    switch (dir) {
        case "W" -> nf--;
        case "S" -> nf++;
        case "A" -> nc--;
        case "D" -> nc++;
    }

    if (!esValido(nf, nc)) return false;
    if (tablero[nf][nc] == 'B' || tablero[nf][nc] == 'V') return false;

    tablero[jugFila][jugCol] = 'O';
    jugFila = nf;
    jugCol = nc;
    tablero[jugFila][jugCol] = 'P';
    return true;
    }
    
    static boolean colocarBarrera(String dir) {
        if (barreras <= 0) return false;
        
        int f = jugFila;
        int c = jugCol;
        
        switch (dir) {
            case "W" -> f--;
            case "S" -> f++;
            case "A" -> c--;
            case "D" -> c++;
            default -> { return false; }
        }
        
        if (!esValido(f, c)) return false;
        if (tablero[f][c] != 'O') return false;
        
        tablero[f][c] = 'B';
        barreras--;
        return true;
    }

    static void usarAntidoto() {
        if (antidotos <= 0) return;
        antidotos--;
    
        int radio;  
    
        switch (nivel) {
            case 1 -> radio = 3;
            case 2 -> radio = 2;
            case 3 -> radio = 2;
            default -> radio = 2;
        }
    
        for (int i = Math.max(0, jugFila - radio); i <= Math.min(tam - 1, jugFila + radio); i++) {
            for (int j = Math.max(0, jugCol - radio); j <= Math.min(tam - 1, jugCol + radio); j++) {
                if (tablero[i][j] == 'V') tablero[i][j] = 'O';
            }
        }
    }

    static void propagarVirus() {
        char[][] nuevo = new char[tam][tam];
        for (int i = 0; i < tam; i++) nuevo[i] = tablero[i].clone();

        int[][] d = {{-1,0},{1,0},{0,-1},{0,1}};
        boolean infectado = false;

        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                if (tablero[i][j] == 'V') {
                    for (int[] m : d) {
                        int ni = i + m[0], nj = j + m[1];
                        if (esValido(ni, nj) && tablero[ni][nj] != 'B' && tablero[ni][nj] != 'S') {
                            if (tablero[ni][nj] == 'P') infectado = true;
                            nuevo[ni][nj] = 'V';
                            
                        }
                    }
                }
            }
        }

        tablero = nuevo;

        if (infectado) {
            mostrarTablero();
            System.out.println("PERDISTE");
            jugando = false;
        }
    }

    static void validarEstado() {
        if (jugFila == sFila && jugCol == sCol) {
            mostrarTablero();
            System.out.println("GANASTE");
            jugando = false;
        }
    }

    static boolean esValido(int f, int c) {
        return f >= 0 && f < tam && c >= 0 && c < tam;
    }

    static void mostrarTablero() {
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void guardarPartida() {}
    static void cargarPartida() {}
}
