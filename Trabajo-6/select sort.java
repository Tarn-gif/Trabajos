public class Main {
    public static void main(String[] args) {
        int[] arr = {10, 2, 5, 7, 9, 19, 60, 67, 35, 0};

        System.out.print("Orden de como estaba antes el arreglo: ");
        for (int x = 0; x < 10; x++) System.out.print(arr[x] + " ");
        System.out.println();

        for (int x = 0; x < 9; x++) {
            int si = x;
            for (int y = x + 1; y < 10; y++) {
                if (arr[y] < arr[si]) si = y;
            }
            int temp = arr[x];
            arr[x] = arr[si];
            arr[si] = temp;
        }

        System.out.print("Ordenado por magia: ");
        for (int x = 0; x < 10; x++) System.out.print(arr[x] + " ");
        System.out.println();
    }
}