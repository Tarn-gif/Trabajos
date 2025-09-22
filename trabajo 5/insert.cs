using system;

class program {
    static void insertsort(int lar, int[] arr) {
        for (int x = 0; x < lar; x++) {
            int temp = arr[x];
            int y = x - 1;
            while (y >= 0 && temp < arr[y]) {
                arr[y + 1] = arr[y];
                y = y - 1;
            }
            arr[y + 1] = temp;
        }
    }

    static void main(string[] args) {
        int[] arr = {5,10,7,2,8,3,1,6,9,4};
        int lar = 10;

        console.write("este es el arreglo sin ordenar: ");
        for (int x = 0; x < 10; x++) {
            console.write(arr[x] + ", ");
        }

        console.writeline();
        console.write("ahora asi se ve arreglado: ");
        insertsort(lar, arr);
        for (int x = 0; x < 10; x++) {
            console.write(arr[x] + ", ");
        }
    }
}