using System;

class Program{
    const int f = 6;
    const int m = 8;

    static bool ranuraslibres(int[,] tabla){
        for (int x = 0; x < f; x++){
            for (int y = 0; y < m; y++){
                if (tabla[x, y] == 0){
                    return true;
                }
            }
        }
        Console.WriteLine("no hay más espacios.. es un empate lol");
        return false;
    }

    static void imptabla(int[,] tabla){
        for (int x = 0; x < f; x++){
            for (int y = 0; y < m; y++){
                if (tabla[x, y] == 1){
                    Console.Write(" O ");
                }
                else if (tabla[x, y] == 2){
                    Console.Write(" X ");
                }
                else{
                    Console.Write(" . ");
                }
            }
            Console.WriteLine();
        }
    }

    static int colocarficha(int[,] tabla, int colum){
        for (int x = f - 1; x >= 0; x--){
            if (tabla[x, colum] == 0){
                return x;
            }
        }
        return -1;
    }

    static bool topcolum(int[,] tabla, int colum){
        if (tabla[0, colum] != 0){
            Console.WriteLine("no hay espacio para poner la ficha btw");
            return true;
        }
        return false;
    }

    static bool ganador(int[,] tabla, int fila, int colum, int juegador){
        bool enct = false;
        int tot = 0;
        
        for (int x = 0; x < f; x++){
            if (enct){
                if (tabla[x, colum] == juegador){
                    tot++;
                }
                else{
                    enct = false;
                    tot = 0;
                }
            }
            if (tabla[x, colum] == juegador && !enct){
                enct = true;
                tot++;
            }
            if (tot == 4){
                Console.WriteLine("Jugador " + juegador + " ganó, se paso de enfermo omaigoto");
                return true;
            }
        }
        
        enct = false;
        tot = 0;
        
        for (int x = 0; x < m; x++){
            if (enct){
                if (tabla[fila, x] == juegador){
                    tot++;
                }
                else{
                    enct = false;
                    tot = 0;
                }
            }
            if (tabla[fila, x] == juegador && !enct){
                enct = true;
                tot++;
            }
            if (tot == 4){
                Console.WriteLine("Jugador " + juegador + " ganó like whaaaaat ojelna");
                return true;
            }
        }
        
        int nfila = fila;
        int ncolum = colum;
        enct = false;
        tot = 0;
        
        while (nfila > 0 && ncolum > 0){
            nfila--;
            ncolum--;
        }
        
        do{
            if (nfila >= f || ncolum >= m || ncolum < 0)
                break;
                
            if (enct){
                if (tabla[nfila, ncolum] == juegador){
                    tot++;
                }
                else{
                    enct = false;
                    tot = 0;
                }
            }
            if (tabla[nfila, ncolum] == juegador && !enct){
                enct = true;
                tot++;
            }
            if (tot == 4){
                Console.WriteLine("Jugador " + juegador + " ganó lmaooo");
                return true;
            }
            nfila++;
            ncolum++;
        } while (nfila < f && ncolum < m);
        
        nfila = fila;
        ncolum = colum;
        enct = false;
        tot = 0;
        
        while (nfila > 0 && ncolum < m - 1){
            nfila--;
            ncolum++;
        }
        
        do{
            if (nfila >= f || ncolum < 0 || ncolum >= m)
                break;
            if (enct){
                if (tabla[nfila, ncolum] == juegador){
                    tot++;
                }
                else{
                    enct = false;
                    tot = 0;
                }
            }
            if (tabla[nfila, ncolum] == juegador && !enct){
                enct = true;
                tot++;
            }
            if (tot == 4){
                Console.WriteLine("Jugador " + juegador + " ganó wtfffff");
                return true;
            }
            nfila++;
            ncolum--;
        } while (nfila < f && ncolum >= 0);
        
        return false;
    }

    static void Main(){
        int[,] matriz = new int[f, m];
        for (int x = 0; x < f; x++){
            for (int y = 0; y < m; y++){
                matriz[x, y] = 0;
            }
        }
        
        int juegador = 0;
        int ult = 0;
        
        do{
            int colocarcolum = -1;
            bool topacolum = true;
            
            if (ult == 1){
                juegador = 2;
            }
            else{
                juegador = 1;
            }
            
            do{
                Console.Write("turno del jugador " + juegador + " / escoge un numero del 1 al 8: ");
                colocarcolum = int.Parse(Console.ReadLine() ?? "0");
                Console.WriteLine();
                colocarcolum--;
                topacolum = topcolum(matriz, colocarcolum);
            }
            while ((colocarcolum < 0 || colocarcolum > 7) || topacolum);
            
            int colocarfila = colocarficha(matriz, colocarcolum);
            matriz[colocarfila, colocarcolum] = juegador;
            imptabla(matriz);
            
            if (ganador(matriz, colocarfila, colocarcolum, juegador)){
                break;
            }
            
            ult = juegador;
        }
        while (ranuraslibres(matriz));
    }
}