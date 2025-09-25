#include <iostream>
using namespace std;

int main() {
    int arr[10] = {10,2,5,7,9,19,60,67,35};

    cout << "Orden de como estaba antes el arreglo: ";
    for (int x = 0; x < 10; x++) cout << arr[x] << " ";
    cout << endl;

    for (int x = 0; x < 9; x++) {
        int si = x;
        for (int y = x+1; y < 10; y++){
            if (arr[y] < arr[si]) si = y;
        }
        int tem = arr[x]; arr[x] = arr[si]; arr[si] = tem;
    }

    cout << "Ordenado por magia: ";
    for (int x = 0; x < 10; x++) { 
        cout << arr[x] << " ";
    }
    cout << endl;

    return 0;
}
