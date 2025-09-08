import java.util.scanner;

public class recorrer_arr {
    public static void main(String[] args) {
        int[] arr = new int[5];
        int op, x, cab;
        int z = 1;
        boolean res = false;

        scanner sc = new scanner(System.in);

        System.out.println("ingrese su opcion");
        System.out.println("[1] recorrer y agregar");
        System.out.println("[2] recorrer, agregar y encontrar");
        op = sc.nextInt();

        switch (op) {
            case 1:
                System.out.println("recorrer arreglo\n");
                for (int i = 0; i < 5; i++) {
                    System.out.print("ingresa un numero: ");
                    arr[i] = sc.nextInt();
                }

                System.out.println("este es su arreglo:");
                for (int i = 0; i < 5; i++) {
                    System.out.print(arr[i] + ",");
                }
                System.out.println();

                while (z > 0) {
                    System.out.print("ingrese en donde quiere sobrescribir un valor [del 0 al 4]: ");
                    x = sc.nextInt();
                    System.out.print("ingrese el nuevo numero: ");
                    cab = sc.nextInt();

                    arr[x] = cab;
                    System.out.print("quieres seguir cambiando el arreglo? si = 1, no = 0: ");
                    z = sc.nextInt();
                }

                System.out.println("asi se ve su arreglo ahora:");
                for (int i = 0; i < 5; i++) {
                    System.out.print(arr[i] + ",");
                }
                break;

            case 2:
                System.out.println("recorrer arreglo y buscar\n");
                for (int i = 0; i < 5; i++) {
                    System.out.print("ingresa un numero: ");
                    arr[i] = sc.nextInt();
                }

                System.out.print("ingrese cual numero quieres buscar de tu arreglo: ");
                cab = sc.nextInt();
                x = 0;

                while (x < 5 && !res) {
                    if (cab != arr[x]) {
                        System.out.println("en este cajon no esta..");
                        x++;
                    } else {
                        System.out.println("hey.. lo encontre aqui: " + x);
                        res = true;
                    }
                }

                if (!res) {
                    System.out.println("el numero no se encuentra en el arreglo");
                }
                break;
        }
        sc.close();
    }
}