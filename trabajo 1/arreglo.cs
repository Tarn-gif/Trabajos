using System;

class Program
{
    static void Main()
    {
        const int filas = 4;
        const int columnas = 2;
        float[,] matriz = new float[filas, columnas];
        Random rnd = new Random(); // que mentira que aquí es más fácil el random number
        
        // uuuh.. supongo que la llena matricez 3000000
        for (int fila = 0; fila < filas; fila++)
        {
            Console.Write($"Ingrese un número entero para la fila {fila + 1}: ");
            int entero;
            while (!int.TryParse(Console.ReadLine(), out entero))
            {
                Console.Write("Entrada inválida. Ingrese un número entero: ");
            }
            
            matriz[fila, 0] = entero;
            matriz[fila, 1] = (float)(rnd.NextDouble() * 10.0);
        }
        
        // si, imprime 3000000 matricez devuelta
        Console.WriteLine("\nMatriz (Entero | Flotante Aleatorio):");
        for (int fila = 0; fila < filas; fila++)
        {
            for (int col = 0; col < columnas; col++)
            {
                Console.Write($"{matriz[fila, col]:0.0} ");
            }
            Console.WriteLine();
        }
    }
}