function insertsort(lar, arr) {
    for (let x = 0; x < lar; x++) {
        let temp = arr[x];
        let y = x - 1;
        while (y >= 0 && temp < arr[y]) {
            arr[y + 1] = arr[y];
            y = y - 1;
        }
        arr[y + 1] = temp;
    }
}

let arr = [5,10,7,2,8,3,1,6,9,4];
let lar = 10;

process.stdout.write("este es el arreglo sin ordenar: ");
for (let x = 0; x < 10; x++) {
    process.stdout.write(arr[x] + ", ");
}

console.log();
process.stdout.write("ahora asi se ve arreglado: ");
insertsort(lar, arr);
for (let x = 0; x < 10; x++) {
    process.stdout.write(arr[x] + ", ");
}
