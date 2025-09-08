using system;

class program {
    static void main() {
        int[] arr = new int[5];
        int op, x, cab;
        int z = 1;
        bool res = false;

        console.writeline("ingrese su opcion");
        console.writeline("[1] recorrer y agregar");
        console.writeline("[2] recorrer, agregar y encontrar");
        op = int.parse(console.readline());

        switch (op) {
            case 1:
                console.writeline("recorrer arreglo\n");
                for (int i = 0; i < 5; i++) {
                    console.write("ingresa un numero: ");
                    arr[i] = int.parse(console.readline());
                }

                console.writeline("este es su arreglo:");
                for (int i = 0; i < 5; i++) {
                    console.write(arr[i] + ",");
                }
                console.writeline();

                while (z > 0) {
                    console.write("ingrese en donde quiere sobrescribir un valor [del 0 al 4]: ");
                    x = int.parse(console.readline());
                    console.write("ingrese el nuevo numero: ");
                    cab = int.parse(console.readline());

                    arr[x] = cab;
                    console.write("quieres seguir cambiando el arreglo? si = 1, no = 0: ");
                    z = int.parse(console.readline());
                }

                console.writeline("asi se ve su arreglo ahora:");
                for (int i = 0; i < 5; i++) {
                    console.write(arr[i] + ",");
                }
                console.writeline();
                break;

            case 2:
                console.writeline("recorrer arreglo y buscar\n");
                for (int i = 0; i < 5; i++) {
                    console.write("ingresa un numero: ");
                    arr[i] = int.parse(console.readline());
                }

                console.write("ingrese cual numero quieres buscar de tu arreglo: ");
                cab = int.parse(console.readline());
                x = 0;

                while (x < 5 && !res) {
                    if (cab != arr[x]) {
                        console.writeline("en este cajon no esta..");
                        x++;
                    } else {
                        console.writeline("hey.. lo encontre aqui: " + x);
                        res = true;
                    }
                }

                if (!res) {
                    console.writeline("el numero no se encuentra en el arreglo");
                }
                break;
        }
    }
}