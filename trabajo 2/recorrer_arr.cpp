#include <iostream>
using namespace std;

int arr[5];
int op, x , cab;
int z = 1;
bool res = false;

int main(){
    
    cout << "ingrese su opción" << endl;
    cout << "[1] recorrer y agregar" << endl;
    cout << "[2] recorrer, agregar y encontrar" << endl;
    cin >> op;
    
    switch(op){
        case 1:
            cout << "Recorrer arreglo" << endl;
            cout << endl;
            for(int x = 0; x < 5; x++){
                cout << "ingresa un número:";
                cin >> arr[x];
            }
            
            cout << "este es su arreglo:" << endl;
            
            for(int x = 0; x < 5; x++){
                cout << arr[x] << ",";
            }
            cout << endl;
            
            while(z > 0){
                cout << "ingrese en donde quiere sobrescribir un valor [del 0 al 4]:";
                cin >> x;
                cout << "ingrese el nuevo número:";
                cin >> cab;
                
                arr[x] = cab; 
                cout << "quieres seguir cambiando el arreglo? si = 1, no = 0:";
                cin >> z;
            }
            
            cout << "así se ve su arreglo ahora:" << endl;
            for(int x = 0; x < 5; x++){
                cout << arr[x] << ",";
            }
            
        break;
        
        case 2:
            cout << "Recorrer arreglo y buscar" << endl;
            cout << endl;
            for(int x = 0; x < 5; x++){
                cout << "ingresa un número:";
                cin >> arr[x];
            }
            
            cout << "ingrese cual número quieres buscar de tu arreglo:";
            cin >> cab;
            x = 0;
            
            while(x < 5 && !res) {
                if(cab != arr[x]) {
                    cout << "en este cajón no está.." << endl;
                    x++;
                } 
                else {
                    cout << "hey.. lo encontré aquí: " << x << endl;
                    res = true;
                }
            }
            
            if(!res) {
                cout << "el número no se encuentra en el arreglo" << endl;
            }
        break;
    }
}