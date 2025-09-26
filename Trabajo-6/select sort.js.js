let arr = [10, 2, 5, 7, 9, 19, 60, 67, 35, 0];

console.log("Orden de como estaba antes el arreglo:", arr.join(" "));

for (let x = 0; x < 9; x++) {
    let si = x;
    for (let y = x + 1; y < 10; y++) {
        if (arr[y] < arr[si]) si = y;
    }
    let temp = arr[x];
    arr[x] = arr[si];
    arr[si] = temp;
}

console.log("Ordenado por magia:", arr.join(" "));