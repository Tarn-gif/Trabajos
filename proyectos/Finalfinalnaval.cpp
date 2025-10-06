#include <iostream>
#include <iomanip>
#include <string>
using namespace std;

const int f=10;
const int c=10;

// colores
const string RESET="\033[0m";
const string ROJO="\033[31m";
const string VERDE="\033[32m";
const string AZUL="\033[34m";
const string AMARILLO="\033[33m";
const string CYAN="\033[36m";
const string MAGENTA="\033[35m";
const string BLANCO="\033[37m";

// mostrar tablero con colores
void mostrar(char t[f][c], bool ocultar=false){
    cout<<"  ";
    for(int y=1;y<=c;y++) cout<<setw(3)<<y;
    cout<<"\n";
    for(int x=0;x<f;x++){
        char l='A'+x;
        cout<<" "<<l<<" ";
        for(int y=0;y<c;y++){
            char cel=t[x][y];
            string col=RESET;
            if(cel=='X') col=ROJO; // impacto
            else if(cel=='o') col=BLANCO; // fallo
            else if(!ocultar){
                switch(cel){
                    case 'A': col=ROJO; break;
                    case 'B': col=VERDE; break;
                    case 'C': col=AZUL; break;
                    case 'D': col=AMARILLO; break;
                    case 'E': col=CYAN; break;
                    case 'F': col=MAGENTA; break;
                    default: col=RESET; break;
                }
            }
            cout<<col<<" "<<cel<<" "<<RESET;
        }
        cout<<"\n";
    }
}

// convertir casilla
bool conv(string s,int &fi,int &co){
    if(s.size()<2) return false;
    char l=toupper(s[0]);
    if(l<'A'||l>='A'+f) return false;
    fi=l-'A';
    try{ co=stoi(s.substr(1))-1;}catch(...){return false;}
    if(co<0||co>=c) return false;
    return true;
}

// colocar barco
bool poner(char t[f][c],int fi,int co,int tam,char o,char tipo){
    o=toupper(o);
    if(o=='H'){
        if(co+tam>c) return false;
        for(int i=0;i<tam;i++) if(t[fi][co+i]!='~') return false;
        for(int i=0;i<tam;i++) t[fi][co+i]=tipo;
    }else if(o=='V'){
        if(fi+tam>f) return false;
        for(int i=0;i<tam;i++) if(t[fi+i][co]!='~') return false;
        for(int i=0;i<tam;i++) t[fi+i][co]=tipo;
    }else return false;
    return true;
}

// colocar barcos jugador
void barcos(char t[f][c],int j){
    int tam[]={4,3,3,2,2,2,1,1,1,1};
    char tipo[]={'A','B','C','D','E','F','G','H','I','J'};
    cout<<"--- Turno jugador "<<j<<" ---\n";
    for(int i=0;i<10;i++){
        int ta=tam[i]; char ti=tipo[i]; bool ok=false;
        while(!ok){
            mostrar(t);
            string cas; char o;
            cout<<"\nBarco tamaño "<<ta<<"\nCasilla inicial (ej: B5): ";
            cin>>cas;
            if(ta>1){cout<<"H o V: "; cin>>o;} else o='H';
            int fi,co;
            if(conv(cas,fi,co)){
                if(poner(t,fi,co,ta,o,ti)){cout<<"Barco colocado!\n"; ok=true;}
                else cout<<"Error: no se puede colocar barco\n";
            }else cout<<"Casilla invalida\n";
        }
    }
}

// disparar a un tablero
bool disparo(char t[f][c],int fi,int co){
    if(t[fi][co]=='~'){ t[fi][co]='o'; return false; } // fallo
    else if(t[fi][co]>='A' && t[fi][co]<='J'){ t[fi][co]='X'; return true; } // impacto
    return false; // casilla ya disparada
}

// revisar si quedan barcos
bool quedan(char t[f][c]){
    for(int i=0;i<f;i++) for(int j=0;j<c;j++) if(t[i][j]>='A' && t[i][j]<='J') return true;
    return false;
}

int main(){
    char t1[f][c], t2[f][c];
    for(int i=0;i<f;i++) for(int j=0;j<c;j++){ t1[i][j]='~'; t2[i][j]='~'; }

    barcos(t1,1);
    barcos(t2,2);

    int turno=1;
    while(quedan(t1)&&quedan(t2)){
        char (*ataca)[c]=(turno==1? t2:t1);
        cout<<"\nTurno jugador "<<turno<<"\n";
        mostrar(ataca,true); // ocultar barcos
        string cas; int fi,co;
        cout<<"Dispara a (ej: B5): "; cin>>cas;
        if(!conv(cas,fi,co)){ cout<<"Casilla invalida\n"; continue; }
        bool acierto=disparo(ataca,fi,co);
        cout<<(acierto?"Impacto!\n":"Fallo!\n");
        turno=(turno==1?2:1);
    }
    cout<<"Jugador "<<(quedan(t1)?1:2)<<" gana!\n";
}
