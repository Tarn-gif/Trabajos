arr = [0] * 5
z = 1
res = False

print("ingrese su opcion")
print("[1] recorrer y agregar")
print("[2] recorrer, agregar y encontrar")
op = int(input("> "))

if op == 1:
    print("recorrer arreglo\n")
    for i in range(5):
        arr[i] = int(input("ingresa un numero: "))

    print("este es su arreglo:")
    print(",".join(map(str, arr)))

    while z > 0:
        x = int(input("ingrese en donde quiere sobrescribir un valor [del 0 al 4]: "))
        cab = int(input("ingrese el nuevo numero: "))
        arr[x] = cab
        z = int(input("quieres seguir cambiando el arreglo? si = 1, no = 0: "))

    print("asi se ve su arreglo ahora:")
    print(",".join(map(str, arr)))

elif op == 2:
    print("recorrer arreglo y buscar\n")
    for i in range(5):
        arr[i] = int(input("ingresa un numero: "))

    cab = int(input("ingrese cual numero quieres buscar de tu arreglo: "))
    x = 0

    while x < 5 and not res:
        if cab != arr[x]:
            print("en este cajon no esta..")
            x += 1
        else:
            print(f"hey.. lo encontre aqui: {x}")
            res = True

    if not res:
        print("el numero no se encuentra en el arreglo")