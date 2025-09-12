const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

let caja = [
  ["1", "2", "3"],
  ["4", "5", "6"],
  ["7", "8", "9"]
];

function muestracaja() {
  for (let x = 0; x < 3; x++) {
    let fila = "";
    for (let y = 0; y < 3; y++) {
      fila += " " + caja[x][y] + " ";
      if (y < 2) fila += "│";
    }
    console.log(fila);
    if (x < 2) console.log("---|---|---");
  }
  console.log();
}

function ganasiom() {
  for (let x = 0; x < 3; x++) {
    if (caja[x][0] === caja[x][1] && caja[x][1] === caja[x][2]) return true;
    if (caja[0][x] === caja[1][x] && caja[1][x] === caja[2][x]) return true;
  }
  if (caja[0][0] === caja[1][1] && caja[1][1] === caja[2][2]) return true;
  if (caja[0][2] === caja[1][1] && caja[1][1] === caja[2][0]) return true;
  return false;
}

function movimiento(jugador, callback) {
  rl.question(`jugador ${jugador}, ingresa un movimiento (1-9): `, (input) => {
    let mv = parseInt(input);
    let ej = Math.floor((mv - 1) / 3);
    let ij = (mv - 1) % 3;
    if (caja[ej][ij] !== "X" && caja[ej][ij] !== "O") {
      caja[ej][ij] = jugador;
      callback();
    } else {
      console.log("movimiento no valido, mi todo cerebrón");
      movimiento(jugador, callback);
    }
  });
}

let mv = 0;
let jugador = "X";

function turno() {
  if (mv >= 9) {
    muestracaja();
    console.log("lol, empate...");
    rl.close();
    return;
  }
  muestracaja();
  movimiento(jugador, () => {
    mv++;
    if (ganasiom()) {
      muestracaja();
      console.log(`jugador ${jugador} no puede ser... ¡Ganaste, mi loco!`);
      rl.close();
      return;
    }
    jugador = (jugador === "X") ? "O" : "X";
    turno();
  });
}

turno();