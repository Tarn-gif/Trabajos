def insertsort(lar, arr):
    for x in range(lar):
        temp = arr[x]
        y = x - 1
        while y >= 0 and temp < arr[y]:
            arr[y + 1] = arr[y]
            y -= 1
        arr[y + 1] = temp

arr = [5,10,7,2,8,3,1,6,9,4]
lar = 10

print("este es el arreglo sin ordenar:", end=" ")
for x in range(10):
    print(arr[x], end=", ")

print()
print("ahora asi se ve arreglado:", end=" ")
insertsort(lar, arr)
for x in range(10):
    print(arr[x], end=", ")