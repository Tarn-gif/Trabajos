arr = [
    [1, 2, 3],
    [1, 2, 3],
    [1, 2, 3]
]

# recorrido horizontal
print("recorrido horizontal")
for x in range(3):
    for y in range(3):
        print(arr[x][y], end=" ")
    print()

print("-------------")

# recorrido vertical
print("recorrido vertical")
for x in range(3):
    for y in range(3):
        print(arr[y][x], end=" ")
    print()