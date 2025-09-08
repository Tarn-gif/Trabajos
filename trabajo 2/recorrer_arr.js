const readline = require("readline-sync");

let arr = new Array(5);
let z = 1;
let res = false;

console.log("ingrese su opcion");
console.log("[1] recorrer y agregar");
console.log("[2] recorrer, agregar y encontrar");
let op = parseInt(readline.question("> "));

switch (op) {
    case 1:
        console.log("recorrer arreglo\n");
        for (let i = 0; i < 5; i++) {
            arr[i] = parseInt(readline.question("ingresa un numero: "));
        }

        console.log("este es su arreglo:");
        console.log(arr.join(","));

        while (z > 0) {
            let x = parseInt(readline.question("ingrese en donde quiere sobrescribir un valor [del 0 al 4]: "));
            let cab = parseInt(readline.question("ingrese el nuevo numero: "));

            arr[x] = cab;
            z = parseInt(readline.question("quieres seguir cambiando el arreglo? si = 1, no = 0: "));
        }

        console.log("asi se ve su arreglo ahora:");
        console.log(arr.join(","));
        break;

    case 2:
        console.log("recorrer arreglo y buscar\n");
        for (let i = 0; i < 5; i++) {
            arr[i] = parseInt(readline.question("ingresa un numero: "));
        }

        let cab = parseInt(readline.question("ingrese cual numero quieres buscar de tu arreglo: "));
        let x = 0;

        while (x < 5 && !res) {
            if (cab !== arr[x]) {
                console.log("en este cajon no esta..");
                x++;
            } else {
                console.log("hey.. lo encontre aqui: " + x);
                res = true;
            }
        }

        if (!res) {
            console.log("el numero no se encuentra en el arreglo");
        }
        break;
}