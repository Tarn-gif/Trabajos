const arr = [
    [1, 2, 3],
    [1, 2, 3],
    [1, 2, 3]
];

// recorrido horizontal
console.log("recorrido horizontal");
for (let x = 0; x < 3; x++) {
    let fila = "";
    for (let y = 0; y < 3; y++) {
        fila += arr[x][y] + " ";
    }
    console.log(fila);
}

console.log("-------------");

// recorrido vertical
console.log("recorrido vertical");
for (let x = 0; x < 3; x++) {
    let columna = "";
    for (let y = 0; y < 3; y++) {
        columna += arr[y][x] + " ";
    }
    console.log(columna);
}