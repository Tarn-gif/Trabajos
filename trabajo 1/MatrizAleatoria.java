import java.util.Random;
import java.util.Scanner;

public class MatrizAleatoria {
    public static void main(String[] args) {
        final int filas = 4;
        final int columnas = 2;
        float[][] matriz = new float[filas][columnas];
        
        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();
        
        for (int fila = 0; fila < filas; fila++) {
            int entero;
            while (true) {
                System.out.print("Ingrese un número entero para la fila " + (fila + 1) + ": ");
                if (sc.hasNextInt()) {
                    entero = sc.nextInt();
                    sc.nextLine();
                    break;
                } else {
                    System.out.println("Entrada inválida. Intente de nuevo.");
                    sc.nextLine();
                }
            }
            
            matriz[fila][0] = entero;
            matriz[fila][1] = rnd.nextFloat() * 10.0f; 
        }
        
        System.out.println("\nMatriz (Entero | Flotante Aleatorio):");
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                System.out.printf("%.1f ", matriz[fila][col]);
            }
            System.out.println();
        }
        
        sc.close(); // esto ya no tiene sentido a estas horas de la noche
    }
}