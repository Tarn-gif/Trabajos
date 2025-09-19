#include <iostream>
using namespace std;
#include <ctime>

int main() {
    int aux;
    int vtr[10];
    srand(time(0));
    
    //generador de números aleatorios
    for (int x = 0; x < 10; x++) {
        vtr[x] = ((rand() % 51));
    }
    
    cout << "asi se ve sin acomodar: " << endl;
    for (int x = 0; x < 10; x++) {
        cout << vtr[x] << ", ";
    }
    
    cout << endl;
    cout << endl;
    
    // vector acomodado con burbuja
    for (int x = 0; x < 10 - 1; x++) {
        for (int y = 0; y < 10 - x - 1; y++) {
            if (vtr[y] < vtr[y + 1]) {
                aux = vtr[y];
                vtr[y] = vtr[y + 1];
                vtr[y + 1] = aux;
            }
        }
    }
    
    cout << "asi se ve sin acomodado (de mayor a menor): " << endl;
    
    // cout de el vector despues de acomodarlo de mayor a menor
    for (int x = 0; x < 10; x++) {
        cout << vtr[x] << ", ";
    }
    
    cout << endl;
    
    return 0;
}