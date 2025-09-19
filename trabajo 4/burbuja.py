import random

vtr = [random.randint(0, 50) for _ in range(10)]

print("asi se ve sin acomodar:")
print(", ".join(map(str, vtr)))
print()

# burbuja descendente
for x in range(9):
    for y in range(9 - x):
        if vtr[y] < vtr[y + 1]:
            vtr[y], vtr[y + 1] = vtr[y + 1], vtr[y]

# burbuja ascendente
print("asi se ve acomodado:")
print(", ".join(map(str, vtr)))