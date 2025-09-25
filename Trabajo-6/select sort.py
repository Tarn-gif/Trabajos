arr = [10, 2, 5, 7, 9, 19, 60, 67, 35, 0]

print("Orden de como estaba antes el arreglo:", arr)

for x in range(9):
    si = x
    for y in range(x + 1, 10):
        if arr[y] < arr[si]:
            si = y
    arr[x], arr[si] = arr[si], arr[x] 

print("Ordenado por magia:", arr)