using System;

class Program {
    static void Main() {
        int[,] arr = {
            {1, 2, 3},
            {1, 2, 3},
            {1, 2, 3}
        };

        // recorrido horizontal
        Console.WriteLine("recorrido horizontal");
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                Console.Write(arr[x, y] + " ");
            }
            Console.WriteLine();
        }

        Console.WriteLine("-------------");

        // recorrido vertical
        Console.WriteLine("recorrido vertical");
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                Console.Write(arr[y, x] + " ");
            }
            Console.WriteLine();
        }
    }
}