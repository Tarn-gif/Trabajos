#include <iostream>
using namespace std;

int insertsort (int lar, int arr[10]){
    for(int x = 0; x < lar; x++){
        int temp = arr[x];
        int y = x - 1;
        while(y >= 0 && temp < arr[y]){
            arr[y+1] = arr[y];
            y = y - 1;
        }
        arr[y + 1] = temp;
    }
    
    return arr[10];
}

int main(){
    int arr[10] = {5,10,7,2,8,3,1,6,9,4};
    int lar = 10;
    
    cout << "este es el arreglo sin ordenar: ";
    for(int x = 0; x < 10; x++){
        cout << arr[x] << ", ";
    }
    
    cout << endl;
    cout << "ahora asi se ve arreglado: ";
    insertsort(lar,arr);
    for(int x = 0; x < 10; x++){
        cout << arr[x] << ", ";
    }
}