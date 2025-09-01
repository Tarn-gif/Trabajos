#include <iostream>
#include <random>
#include <iomanip> // cosas que pasan cuando tienes unos errores con tu cacharro
using namespace std;

const int filas = 4;
const int columnas = 2;  
float matriz[filas][columnas];

int main() {

    random_device rd;
    mt19937 gen(rd());
    uniform_real_distribution<float> dis(0.0, 10.0);

    // llena matricez 3000000
    for(int x = 0; x < filas; x++){
        int entero;
        cout << "Ingrese un número entero para la fila " << x+1 << ": ";
        cin >> entero;
        
        matriz[x][0] = entero;
        matriz[x][1] = dis(gen);
    }

    // la impresora de matricez 3000000
    cout << fixed << setprecision(1); // parte de errores en mi cacharro
    cout << "Matriz (Entero | Flotante Aleatorio):\n";
    for(int x = 0; x < filas; x++){
        for(int d = 0; d < columnas; d++){
            cout << matriz[x][d] << " ";
        }
        cout << endl;
    }
    
}