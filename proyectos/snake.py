import msvcrt
import time
import random
import os

# ===== CONFIG =====
ancho = 20
alto = 10

sx = [5, 4, 3]
sy = [5, 5, 5]
largo = 3

dx = 1
dy = 0

comida_x = 0
comida_y = 0

nivel = 1
vivo = True

# ===== COLORES ANSI =====
RESET = "\033[0m"
VERDE = "\033[32m"
ROJO = "\033[31m"
CYAN = "\033[36m"
AMARILLO = "\033[33m"

def limpiar():
    os.system("cls")

def nuevacomida():
    global comida_x, comida_y
    while True:
        comida_x = random.randint(0, ancho - 1)
        comida_y = random.randint(0, alto - 1)
        if not essnake(comida_x, comida_y):
            break

def essnake(x, y):
    for i in range(largo):
        if sx[i] == x and sy[i] == y:
            return True
    return False

def mostrar():
    limpiar()

    ancho_interno = ancho + 2

    print(CYAN + "╔" + "═" * ancho_interno + "╗" + RESET)

    titulo = f"NIVEL {nivel}"
    espacios = ancho_interno - len(titulo)
    print(CYAN + "║" + RESET + titulo + " " * espacios + CYAN + "║" + RESET)

    print(CYAN + "╠" + "═" * ancho_interno + "╣" + RESET)

    for y in range(alto):
        linea = CYAN + "║ " + RESET
        for x in range(ancho):
            if x == sx[0] and y == sy[0]:
                linea += VERDE + "@" + RESET
            elif essnake(x, y):
                linea += VERDE + "o" + RESET
            elif x == comida_x and y == comida_y:
                linea += ROJO + "*" + RESET
            else:
                linea += " "
        linea += CYAN + " ║" + RESET
        print(linea)
    
    print(CYAN + "╚" + "═" * ancho_interno + "╝" + RESET)
    print(AMARILLO + "W A S D para moverte | Ctrl+C salir" + RESET)

def leer():
    global dx, dy
    if msvcrt.kbhit():
        t = msvcrt.getch().decode().lower()
        if t == "w" and dy != 1:
            dx, dy = 0, -1
        elif t == "s" and dy != -1:
            dx, dy = 0, 1
        elif t == "a" and dx != 1:
            dx, dy = -1, 0
        elif t == "d" and dx != -1:
            dx, dy = 1, 0

def mover():
    global vivo, largo, nivel

    for i in range(largo - 1, 0, -1):
        sx[i] = sx[i - 1]
        sy[i] = sy[i - 1]

    sx[0] += dx
    sy[0] += dy

    if sx[0] < 0 or sx[0] >= ancho or sy[0] < 0 or sy[0] >= alto:
        vivo = False

    for i in range(1, largo):
        if sx[0] == sx[i] and sy[0] == sy[i]:
            vivo = False

    if sx[0] == comida_x and sy[0] == comida_y:
        sx.append(sx[-1])
        sy.append(sy[-1])
        largo += 1
        nivel += 1
        nuevacomida()

# ===== MAIN =====
nuevacomida()

while vivo:
    mostrar()
    leer()
    mover()
    time.sleep(max(0.05, 0.3 - nivel * 0.02))

limpiar()
print(ROJO + " fin del juego jeje" + RESET)
print(f"Puntaje final: {nivel}")
