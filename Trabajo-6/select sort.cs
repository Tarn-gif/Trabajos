using System;

class Program {
    static void Main() {
        int[] arr = {10, 2, 5, 7, 9, 19, 60, 67, 35, 0};

        Console.Write("Orden de como estaba antes el arreglo: ");
        for (int x = 0; x < 10; x++) Console.Write(arr[x] + " ");
        Console.WriteLine();

        for (int x = 0; x < 9; x++) {
            int si = x;
            for (int y = x + 1; y < 10; y++) {
                if (arr[y] < arr[si]) si = y;
            }
            int temp = arr[x];
            arr[x] = arr[si];
            arr[si] = temp;
        }

        Console.Write("Ordenado por magia: ");
        for (int x = 0; x < 10; x++) Console.Write(arr[x] + " ");
        Console.WriteLine();
    }
}