#include <iostream>
using namespace std;
//recorrer arreglo

int arr[3][3] = {{1,2,3},{1,2,3},{1,2,3}}; //arreglo de matriz 3*3


int main(){
    //recorrido horizontal
    cout << "recorrido horizontal"<< endl;
    for(int x = 0; x < 3; x++){
        for(int y = 0; y < 3; y++){
            cout << arr[x][y] << " ";
        }
        cout << endl;
    }
    
    cout << "-------------"<< endl;
    
    //recorrido vertical
    cout << "recorrido vertical"<< endl;
    for(int x = 0; x < 3; x++){
        for(int y = 0; y < 3; y++){
            cout << arr[y][x] << " ";
        }
        cout << endl;
    }
    
return 0;
}