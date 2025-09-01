const filas = 4;
const columnas = 2;
let matriz = [];

for (let i = 0; i < filas; i++) {
    matriz[i] = [];
    let entero;
    while (true) {
        entero = prompt("Ingrese un numero entero para la fila " + (i + 1) + ": ");
        if (!isNaN(entero) && parseInt(entero) == entero) break;
        alert("Entrada invalida, intente de nuevo");
    }
    matriz[i][0] = parseInt(entero);
    matriz[i][1] = Math.random() * 10;
}

console.log("Matriz (Entero | Flotante Aleatorio):");
for (let i = 0; i < filas; i++) {
    let linea = "";
    for (let j = 0; j < columnas; j++) {
        linea += matriz[i][j].toFixed(1) + " ";
    }
    console.log(linea);
}