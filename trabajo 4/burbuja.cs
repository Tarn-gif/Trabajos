using System;

class Program {
    static void Main() {
        int aux;
        int[] vtr = new int[10];
        Random rand = new Random();

        // llenar vector con numeros random
        for (int x = 0; x < 10; x++) {
            vtr[x] = rand.Next(0, 51); // 0 a 50
        }
        // mostrar vector sin ordenar
        Console.WriteLine("asi se ve sin acomodar:");
        for (int x = 0; x < 10; x++) {
            Console.Write(vtr[x] + ", ");
        }

        Console.WriteLine("\n");

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
        // mostrar vector ordenado
        Console.WriteLine("asi se ve acomodado (de mayor a menor):");
        for (int x = 0; x < 10; x++) {
            Console.Write(vtr[x] + ", ");
        }
        Console.WriteLine();
    }
}