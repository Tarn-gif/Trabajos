import java.util.*;
import java.io.*;

public class VirusOutbreak {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    static char[][] tablero;
    static int tam;
    static int jugFila, jugCol;
    static int sFila, sCol;
    static int barreras;
    static int antidotos;
    static int nivel; // 1 fácil, 2 difícil, 3 extremo
    static boolean jugando = true;
    static int puntuacion = 0;

    // === COLORES ANSI ===
    static final String RESET = "\u001B[0m";
    static final String ROJO = "\u001B[31m";
    static final String VERDE = "\u001B[32m";
    static final String AMARILLO = "\u001B[33m";
    static final String AZUL = "\u001B[34m";
    static final String CYAN = "\u001B[36m";
    static final String GRIS = "\u001B[37m";
    static final String BLANCO = "\u001B[97m";

    public static void main(String[] args) {
        menuPrincipal();
    }

    // ========================= MENÚ ============================
    static void menuPrincipal() {
        while (true) {
            System.out.println(AZUL + "=== VIRUS OUTBREAK ===" + RESET);
            System.out.println("1. Nuevo juego");
            System.out.println("2. Cargar partida");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            int op = sc.nextInt();

            switch (op) {
                case 1 -> iniciarJuego();
                case 2 -> cargarPartida();
                case 3 -> {
                    System.out.println("Saliendo...");
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    // ======================= CONFIGURAR JUEGO =======================
    static void iniciarJuego() {
        System.out.println("Selecciona dificultad:");
        System.out.println("1. Fácil");
        System.out.println("2. Difícil");
        System.out.println("3. Extremo");
        System.out.print("→ ");
        nivel = sc.nextInt();

        switch (nivel) {
            case 1 -> { tam = 10; barreras = 8; antidotos = 3; }
            case 2 -> { tam = 8; barreras = 6; antidotos = 2; }
            case 3 -> { tam = 6; barreras = 4; antidotos = 1; }
            default -> { tam = 8; barreras = 6; antidotos = 2; }
        }

        tablero = new char[tam][tam];
        for (int i = 0; i < tam; i++)
            Arrays.fill(tablero[i], 'O');

        // colocar jugador y salida
        jugFila = rand.nextInt(tam);
        jugCol = rand.nextInt(tam);
        tablero[jugFila][jugCol] = 'P';

        do {
            sFila = rand.nextInt(tam);
            sCol = rand.nextInt(tam);
        } while (sFila == jugFila && sCol == jugCol);
        tablero[sFila][sCol] = 'S';

        // colocar virus inicial
        int vFila, vCol;
        do {
            vFila = rand.nextInt(tam);
            vCol = rand.nextInt(tam);
        } while (tablero[vFila][vCol] != 'O');
        tablero[vFila][vCol] = 'V';

        loopJuego();
    }

    // ======================= BUCLE PRINCIPAL =======================
    static void loopJuego() {
        jugando = true;

        while (jugando) {
            mostrarTablero();
            System.out.println("Barreras: " + AMARILLO + barreras + RESET + " | Antídotos: " + CYAN + antidotos + RESET);
            System.out.println("1. Mover (W/A/S/D)");
            System.out.println("2. Colocar barrera");
            System.out.println("3. Usar antídoto");
            System.out.println("4. Guardar partida");
            System.out.println("5. Salir al menú");
            System.out.print("→ ");
            String accion = sc.next().toUpperCase();

            switch (accion) {
                case "1", "W", "A", "S", "D" -> moverJugador(accion);
                case "2" -> colocarBarrera();
                case "3" -> usarAntidoto();
                case "4" -> guardarPartida();
                case "5" -> { return; }
                default -> System.out.println("Acción inválida.");
            }

            // 🔹 Verificar si el jugador ganó antes de propagar el virus
            validarEstado();
            if (!jugando) break;

            // 🔹 Propagar virus solo si el juego sigue
            propagarVirus();

            // 🔹 Revisar nuevamente el estado después de propagar
            validarEstado();
        }
    }

    // ======================= MOSTRAR TABLERO =======================
    static void mostrarTablero() {
        System.out.println("\n===== TABLERO =====");

        // Encabezado de columnas
        System.out.print("   ");
        for (int j = 0; j < tam; j++) {
            System.out.printf("%2d ", j);
        }
        System.out.println();

        // Línea separadora
        System.out.print("   ");
        for (int j = 0; j < tam; j++) {
            System.out.print("---");
        }
        System.out.println();

        // Filas
        for (int i = 0; i < tam; i++) {
            System.out.printf("%2d|", i);
            for (int j = 0; j < tam; j++) {
                char c = tablero[i][j];
                String simbolo;
                switch (c) {
                    case 'V' -> simbolo = ROJO + "V" + RESET;
                    case 'B' -> simbolo = AMARILLO + "B" + RESET;
                    case 'P' -> simbolo = CYAN + "P" + RESET;
                    case 'S' -> simbolo = VERDE + "S" + RESET;
                    default -> simbolo = GRIS + "O" + RESET;
                }
                System.out.print(" " + simbolo + " ");
            }
            System.out.println();
        }

        // Línea inferior
        System.out.print("   ");
        for (int j = 0; j < tam; j++) {
            System.out.print("---");
        }
        System.out.println("\n===================\n");
    }

    // ======================= MOVIMIENTO DEL JUGADOR =======================
    static void moverJugador(String dir) {
        int nf = jugFila, nc = jugCol;

        switch (dir) {
            case "W" -> nf--;
            case "S" -> nf++;
            case "A" -> nc--;
            case "D" -> nc++;
            default -> {
                System.out.print("Dirección: ");
                dir = sc.next().toUpperCase();
                moverJugador(dir);
                return;
            }
        }

        if (!esValido(nf, nc)) {
            System.out.println("Movimiento fuera del tablero.");
            return;
        }

        char celda = tablero[nf][nc];
        if (celda == 'B' || celda == 'V') {
            System.out.println("No puedes moverte ahí, hay un obstáculo o el virus.");
            return;
        }

        tablero[jugFila][jugCol] = 'O';
        jugFila = nf;
        jugCol = nc;
        tablero[jugFila][jugCol] = 'P';
    }

    static void colocarBarrera() {
        if (barreras <= 0) {
            System.out.println("No te quedan barreras.");
            return;
        }

        System.out.print("Fila: ");
        int f = sc.nextInt();
        System.out.print("Columna: ");
        int c = sc.nextInt();

        if (esValido(f, c) && tablero[f][c] == 'O') {
            tablero[f][c] = 'B';
            barreras--;
            System.out.println("Barrera colocada.");
        } else if (esValido(f, c) && tablero[f][c] == 'B') {
            System.out.println("Reubicando barrera...");
            tablero[f][c] = 'O';
            barreras++;
        } else {
            System.out.println("No se puede colocar ahí.");
        }
    }

    static void usarAntidoto() {
        if (antidotos <= 0) {
            System.out.println("No tienes antídotos.");
            return;
        }

        antidotos--;
        // menos fuerte cuanto mayor es la dificultad: nivel 1 -> radio 3, 2 -> 2, 3 -> 1
        int radio = Math.max(1, 4 - nivel);
        for (int i = Math.max(0, jugFila - radio); i < Math.min(tam, jugFila + radio + 1); i++) {
            for (int j = Math.max(0, jugCol - radio); j < Math.min(tam, jugCol + radio + 1); j++) {
                if (tablero[i][j] == 'V') tablero[i][j] = 'O';
            }
        }
        System.out.println("Antídoto usado, el virus retrocede.");
    }

    // ======================= PROPAGACIÓN DEL VIRUS =======================
    static void propagarVirus() {
        char[][] nuevo = new char[tam][tam];
        for (int i = 0; i < tam; i++)
            nuevo[i] = tablero[i].clone();

        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        boolean jugadorInfectado = false;

        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                if (tablero[i][j] == 'V') {
                    for (int[] d : dirs) {
                        int ni = i + d[0], nj = j + d[1];
                        if (esValido(ni, nj) && (tablero[ni][nj] == 'O' || tablero[ni][nj] == 'P' || tablero[ni][nj] == 'S')) {
                            nuevo[ni][nj] = 'V';
                            if (tablero[ni][nj] == 'P') jugadorInfectado = true;
                        }
                    }
                }
            }
        }

        tablero = nuevo;

        if (jugadorInfectado) {
            mostrarTablero();
            System.out.println(ROJO + "El virus alcanzó al jugador. Perdiste." + RESET);
            jugando = false;
        }
    }

    static boolean esValido(int f, int c) {
        return f >= 0 && f < tam && c >= 0 && c < tam;
    }

    // ======================= VALIDACIÓN DE ESTADO =======================
    static void validarEstado() {
        if (jugFila == sFila && jugCol == sCol) {
            puntuacion = (barreras + antidotos) * 100;
            mostrarTablero();
            System.out.println(VERDE + "¡Escapaste del virus! Puntuación: " + puntuacion + RESET);
            jugando = false;
        } else if (virusContieneTodo()) {
            mostrarTablero();
            System.out.println(ROJO + "El virus se propagó por todo el tablero. Perdiste." + RESET);
            jugando = false;
        } else if (virusConfinado()) {
            puntuacion = (barreras * 50) + (antidotos * 75);
            mostrarTablero();
            System.out.println(CYAN + "Contuviste el virus. ¡Ganaste! Puntuación: " + puntuacion + RESET);
            jugando = false;
        }
    }

    static boolean virusContieneTodo() {
        for (int i = 0; i < tam; i++)
            for (int j = 0; j < tam; j++)
                if (tablero[i][j] == 'O' || tablero[i][j] == 'S' || tablero[i][j] == 'P')
                    return false;
        return true;
    }

    static boolean virusConfinado() {
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                if (tablero[i][j] == 'V') {
                    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
                    for (int[] d : dirs) {
                        int ni = i + d[0], nj = j + d[1];
                        if (esValido(ni, nj) && tablero[ni][nj] == 'O') {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    // ======================= GUARDAR Y CARGAR =======================
    static void guardarPartida() {
        try (PrintWriter pw = new PrintWriter("partida_guardada.txt")) {
            pw.println(tam + " " + jugFila + " " + jugCol + " " + sFila + " " + sCol + " " + barreras + " " + antidotos + " " + nivel);
            for (int i = 0; i < tam; i++) {
                for (int j = 0; j < tam; j++) {
                    pw.print(tablero[i][j]);
                }
                pw.println();
            }
            System.out.println("Partida guardada.");
        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    static void cargarPartida() {
        try (Scanner file = new Scanner(new File("partida_guardada.txt"))) {
            tam = file.nextInt();
            jugFila = file.nextInt();
            jugCol = file.nextInt();
            sFila = file.nextInt();
            sCol = file.nextInt();
            barreras = file.nextInt();
            antidotos = file.nextInt();
            nivel = file.nextInt();
            file.nextLine();

            tablero = new char[tam][tam];
            for (int i = 0; i < tam; i++) {
                String linea = file.nextLine();
                for (int j = 0; j < tam; j++) {
                    tablero[i][j] = linea.charAt(j);
                }
            }

            System.out.println("Partida cargada.");
            loopJuego();
        } catch (Exception e) {
            System.out.println("No se pudo cargar la partida.");
        }
    }
}
