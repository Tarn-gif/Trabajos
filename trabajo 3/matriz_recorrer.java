public class matriz_recorrer {
    public static void main(String[] args) {
        int[][] arr = {
            {1, 2, 3},
            {1, 2, 3},
            {1, 2, 3}
        };

        // recorrido horizontal
        System.out.println("recorrido horizontal");
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                System.out.print(arr[x][y] + " ");
            }
            System.out.println();
        }

        System.out.println("-------------");

        // recorrido vertical
        System.out.println("recorrido vertical");
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                System.out.print(arr[y][x] + " ");
            }
            System.out.println();
        }
    }
}