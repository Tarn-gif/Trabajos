#include <iostream>
using namespace std;
int arr[] = {1,3,5,4,2,6,9,8,10,7};
int num;
int num_imp;
int num_par;
int len = 10;
float prom_par;
float prom_imp;

int main() {
    
    for(int x = 0; x < len; x++){
        cout << "ingrese sus numeros en la casilla " << x << " :";
        cin >> arr[x];
        cout << endl;
    }
    for(int x = 0; x < 10; x++){
        if(arr[x] % 2 == 0){
            num_imp++;
        }
        else{
            num_par++;
        }
    }
    
    prom_par = (num_par % len) * 10;
    prom_imp = (num_imp % len) * 10;
    
    cout << num_imp << " son numeros impares con un promedio de: " << prom_imp << "%, " << num_par << " son numeros pares con un promedio de: " << prom_par <<"%" << " del " << len * 10 << "%" << endl;
    
}