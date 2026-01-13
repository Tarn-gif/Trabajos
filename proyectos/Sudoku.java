import java.util.Random;
import java.util.Scanner;

public class Sudoku {

    static int[][] tab = new int[9][9];
    static int[][] sol = new int[9][9];
    static int nivel = 1;

    static final int ANCHO = 29;

    static Scanner sc = new Scanner(System.in);
    static Random rnd = new Random();

    public static void main(String[] args) {
        while (true) {
            iniciarnivel();
            jugarnivel();
            nivel++;
        }
    }

    static void iniciarnivel() {
        limpiarTablero();
        generarcompleto();
        copiarsol();
        quitarnumeros();
    }

    static void mostrar() {
        limpiarPantalla();

        System.out.println("+" + "-".repeat(ANCHO) + "+");
        System.out.println(centrar("NIVEL " + nivel));
        System.out.println("+" + "-".repeat(ANCHO) + "+");

        for (int f = 0; f < 9; f++) {

            if (f % 3 == 0)
                System.out.println("|  +-------+-------+-------+  |");

            System.out.print("|  | ");
            for (int c = 0; c < 9; c++) {
                System.out.print(v(f, c) + " ");
                if ((c + 1) % 3 == 0 && c != 8)
                    System.out.print("| ");
            }
            System.out.println("|  |");
        }

        System.out.println("|  +-------+-------+-------+  |");
        System.out.println("+" + "-".repeat(ANCHO) + "+");
        System.out.println("Fila Col Num (1-9) | 0 salir");
    }

    static String centrar(String txt) {
        int espacios = ANCHO - txt.length();
        int izq = espacios / 2;
        int der = espacios - izq;
        return "|" + " ".repeat(izq) + txt + " ".repeat(der) + "|";
    }

    static String v(int f, int c) {
        return tab[f][c] == 0 ? "." : String.valueOf(tab[f][c]);
    }

    static void jugarnivel() {
        while (true) {
            mostrar();

            if (completo()) {
                System.out.println("Nivel completado 🎉");
                sc.nextLine();
                break;
            }

            int f = sc.nextInt();
            if (f == 0)
                System.exit(0);

            int c = sc.nextInt();
            int n = sc.nextInt();

            f--;
            c--;

            if (f < 0 || f > 8 || c < 0 || c > 8 || n < 1 || n > 9) {
                System.out.println("Entrada inválida");
                sc.nextLine();
                continue;
            }

            if (sol[f][c] == n) {
                tab[f][c] = n;
            } else {
                System.out.println("Incorrecto");
                sc.nextLine();
            }
        }
    }

    static boolean completo() {
        for (int f = 0; f < 9; f++)
            for (int c = 0; c < 9; c++)
                if (tab[f][c] == 0)
                    return false;
        return true;
    }

    static void limpiarTablero() {
        for (int f = 0; f < 9; f++)
            for (int c = 0; c < 9; c++)
                tab[f][c] = 0;
    }

    static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void copiarsol() {
        for (int f = 0; f < 9; f++)
            for (int c = 0; c < 9; c++)
                sol[f][c] = tab[f][c];
    }

    static boolean generarcompleto() {
        for (int f = 0; f < 9; f++) {
            for (int c = 0; c < 9; c++) {
                if (tab[f][c] == 0) {
                    int[] nums = mezclar();
                    for (int n : nums) {
                        if (valido(f, c, n)) {
                            tab[f][c] = n;
                            if (generarcompleto())
                                return true;
                            tab[f][c] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    static int[] mezclar() {
        int[] n = {1,2,3,4,5,6,7,8,9};
        for (int i = 0; i < 9; i++) {
            int j = rnd.nextInt(9);
            int tmp = n[i];
            n[i] = n[j];
            n[j] = tmp;
        }
        return n;
    }

    static boolean valido(int f, int c, int n) {

        for (int i = 0; i < 9; i++)
            if (tab[f][i] == n || tab[i][c] == n)
                return false;

        int fi = (f / 3) * 3;
        int ci = (c / 3) * 3;

        for (int i = fi; i < fi + 3; i++)
            for (int j = ci; j < ci + 3; j++)
                if (tab[i][j] == n)
                    return false;

        return true;
    }

    static void quitarnumeros() {
        int quitar = 30 + nivel * 2;
        if (quitar > 60)
            quitar = 60;

        while (quitar > 0) {
            int f = rnd.nextInt(9);
            int c = rnd.nextInt(9);

            if (tab[f][c] != 0) {
                tab[f][c] = 0;
                quitar--;
            }
        }
    }
}
