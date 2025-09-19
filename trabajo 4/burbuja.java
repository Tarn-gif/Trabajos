import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int aux;
        int[] vtr = new int[10];
        Random rand = new Random();

        // numeros random
        for (int x = 0; x < 10; x++) {
            vtr[x] = rand.nextInt(51); 
        }

        // el antes
        System.out.println("asi se ve sin acomodar:");
        for (int x = 0; x < 10; x++) {
            System.out.print(vtr[x] + ", ");
        }

        System.out.println("\n");

        // burbuja de mayor a menor
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9 - x; y++) {
                if (vtr[y] < vtr[y + 1]) {
                    aux = vtr[y];
                    vtr[y] = vtr[y + 1];
                    vtr[y + 1] = aux;
                }
            }
        }

        // despues
        System.out.println("asi se ve acomodado (de mayor a menor):");
        for (int x = 0; x < 10; x++) {
            System.out.print(vtr[x] + ", ");
        }
        System.out.println();
    }
}