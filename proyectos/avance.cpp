#include <iostream>
#include <iomanip>
#include <string>
using namespace std;

const int filas = 10;
const int colum = 10;

// colorines
const string RESET = "\033[0m";
const string ROJO = "\033[31m";
const string VERDE = "\033[32m";
const string AZUL = "\033[34m";
const string AMARILLO = "\033[33m";
const string CYAN = "\033[36m";
const string MAGENTA = "\033[35m";

void mostrarTablero(char tabl[filas][colum]) {
    cout << "  ";
    for (int y = 1; y <= colum; y++) {
        cout << setw(3) << y;
    }
    cout << endl;

    for (int x = 0; x < filas; x++) {
        char letra = 'A' + x;
        cout << " " << letra << " ";
        for (int y = 0; y < colum; y++) {
            char celda = tabl[x][y];
            string color = RESET;
            
            switch (celda) {
                case 'A': color = ROJO; break;
                case 'B': color = VERDE; break;
                case 'C': color = AZUL; break;
                case 'D': color = AMARILLO; break;
                case 'E': color = CYAN; break;
                case 'F': color = MAGENTA; break;
                default: color = RESET; break;
            }
            
            cout << color << " " << celda << " " << RESET;
        }
        cout << endl;
    }
}

// casillas a indices
bool convertirCasilla(string casilla, int &filaOut, int &colOut) {
    if (casilla.size() < 2) return false;
    char letra = toupper(casilla[0]);
    if (letra < 'A' || letra >= 'A' + filas) return false;
    filaOut = letra - 'A';
    try {
        colOut = stoi(casilla.substr(1)) - 1;
    } catch (...) {
        return false;
    }
    if (colOut < 0 || colOut >= colum) return false;
    return true;
}

bool colocarBarco(char tabl[filas][colum], int filaIni, int colIni, int tam, char orientacion, char tipo) {
    orientacion = toupper(orientacion);
    if (orientacion == 'H') {
        if (colIni + tam > colum) return false;
        for (int i = 0; i < tam; i++)
            if (tabl[filaIni][colIni + i] != '~') return false;
        for (int i = 0; i < tam; i++)
            tabl[filaIni][colIni + i] = tipo;
    } else if (orientacion == 'V') {
        if (filaIni + tam > filas) return false;
        for (int i = 0; i < tam; i++)
            if (tabl[filaIni + i][colIni] != '~') return false;
        for (int i = 0; i < tam; i++)
            tabl[filaIni + i][colIni] = tipo;
    } else {
        return false;
    }
    return true;
}

void colocarBarcosJugador(char tabl[filas][colum], int jugador) {
    // Barcos: 1 de 4, 2 de 3, 3 de 2, 4 de 1
    int barcosTam[] = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
    char barcosTipo[] = {'A','B','C','D','E','F','G','H','I','J'};

    cout << "\n--- Turno del jugador " << jugador << " para colocar barcos ---\n";

    for (int i = 0; i < 10; i++) {
        int tam = barcosTam[i];
        char tipo = barcosTipo[i];
        bool colocado = false;
        
        while (!colocado) {
            mostrarTablero(tabl);
            string casilla;
            char orientacion;
            
            cout << "\nColoca un barco de tamaño " << tam << endl;
            cout << "Casilla inicial (ej: B5): ";
            cin >> casilla;
            
            if (tam > 1) {
                cout << "Horizontal (H) o Vertical (V): ";
                cin >> orientacion;
            } else {
                orientacion = 'H';
            }
            
            int fila, col;
            if (convertirCasilla(casilla, fila, col)) {
                if (colocarBarco(tabl, fila, col, tam, orientacion, tipo)) {
                    cout << "Barco colocado!\n";
                    colocado = true;
                } else {
                    cout << "Error: no se puede colocar el barco ahí (colisión o fuera de rango)\n";
                }
            } else {
                cout << "Casilla inválida.\n";
            }
        }
    }
}

int main() {
    char tablero1[filas][colum];
    char tablero2[filas][colum];

    // tablero
    for (int i = 0; i < filas; i++)
        for (int j = 0; j < colum; j++) {
            tablero1[i][j] = '~';
            tablero2[i][j] = '~';
        }

    // colocar barcos
    colocarBarcosJugador(tablero1, 1);
    colocarBarcosJugador(tablero2, 2);

    cout << "\nTablero del Jugador 1:\n";
    mostrarTablero(tablero1);

    cout << "\nTablero del Jugador 2:\n";
    mostrarTablero(tablero2);

    return 0;
}