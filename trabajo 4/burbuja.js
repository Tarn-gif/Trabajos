let vtr = new Array(10);

// llenar vector random
for (let x = 0; x < 10; x++) {
    vtr[x] = Math.floor(Math.random() * 51); // 0 a 50
}
// mostrar vector sin ordenar
console.log("asi se ve sin acomodar:");
console.log(vtr.join(", "));
console.log("");

// burbuja de mayor a menor
for (let x = 0; x < 9; x++) {
    for (let y = 0; y < 9 - x; y++) {
        if (vtr[y] < vtr[y + 1]) {
            let aux = vtr[y];
            vtr[y] = vtr[y + 1];
            vtr[y + 1] = aux;
        }
    }
}
// asi queda jeje ajaj AAAAAAA
console.log("asi se ve acomodado (de mayor a menor):");
console.log(vtr.join(", "));