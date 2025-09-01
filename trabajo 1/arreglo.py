'arreglos'

'arreglo de flotantes'
mat = [
    [5.0,2.4,7.8],
    [2.3,1.6,9.2],
    [1.0,4.9,8.1]
    ]
    
print ('números flotantes en un 3*3')
print(mat)

print('##################################')

'arreglo de enteros'
arr = [0] * 5 
i = 1

'arreglo dinámico'
arr2 = []

'arreglo de enteros'
for i in range(5):
    val = input('ingrese número:')
    arr[i] = val

print('números de arreglo de enteros')
print('números que ingresó: ', arr)
print('##################################')

'arreglo dinamico'
while True:
    ent = input('escribe lo que sea (o "fin" para terminar): ')
    if ent.lower() == 'fin':
        break
    
    arr2.append(ent)
    
print('palabras o letras que escribiste: ',arr2)
print('##################################')

'recorrido de un arreglo'
arr3 = [1,2,3,4,5,6,7,8,9,10]
print('recorrido lineal secuencial: ', end = '')
print('\nlos elementos del array son: ', end = '')
for idx in arr3:
    print(idx, end = ' ')
print()