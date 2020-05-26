
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import dominio.Dado;
import dominio.Jugador;
import dominio.Tablero;
import interfaz.Consola;
import java.util.ArrayList;

/**
 *
 * @author Mateo
 */
public class Partida {

    private Jugador jugadorRojo;
    private Jugador jugadorAzul;
    private int scoreRojo = 0;
    private int scoreAzul = 0;
    private boolean modoTest;
    private Tablero tablero;
    private boolean abandono = false;

    public Partida(Jugador unJugadorRojo, Jugador unJugadorAzul, boolean modoTest) {
        this.setJugadorRojo(unJugadorRojo);
        this.setJugadorAzul(unJugadorAzul);
        this.setModoTest(modoTest);
        this.setTablero();

    }

    // Getters
    public Jugador getJugadorRojo() {
        return jugadorRojo;
    }

    public Jugador getJugadorAzul() {
        return jugadorAzul;
    }

    public boolean getModoTest() {
        return modoTest;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public int getPuntajeRojo() {
        return scoreRojo;
    }

    public int getPuntajeAzul() {
        return scoreAzul;
    }

    public boolean getAbandono() {
        return abandono;
    }

    // Setters
    public void setJugadorRojo(Jugador unJugador) {
        this.jugadorRojo = unJugador;
    }

    public void setJugadorAzul(Jugador unJugador) {
        this.jugadorAzul = unJugador;
    }

    public void setModoTest(boolean modoTest) {
        this.modoTest = modoTest;
    }

    public void setTablero() {
        this.tablero = new Tablero();
    }

    public void setPuntajeRojo(int unNumero) {
        this.scoreRojo = unNumero;
    }

    public void setPuntajeAzul(int unNumero) {
        this.scoreAzul = unNumero;
    }

    public void setAbandono(boolean unBoolean) {
        this.abandono = unBoolean;
    }

    public void jugar() {
        while (!this.getTablero().estaCompleto() || this.getAbandono()) {

            mostrarPuntaje();
            Consola.mostrarTablero(this.getTablero().getMatriz());

            Consola.printlnGreen("Turno de jugador rojo");
            jugada(getJugadorRojo());

            mostrarPuntaje();
            Consola.println("");
            Consola.mostrarTablero(getTablero().getMatriz());

            Consola.printlnGreen("Turno de jugador azul");
            jugada(getJugadorAzul());
        }
        this.terminarPartida();
    }

    public void jugada(Jugador unJugador) {
        ArrayList<Integer> dados = tirarDados();
        Consola.println("");
        pedirJugada(dados, unJugador);
    }

    public void pedirJugada(ArrayList<Integer> dados, Jugador unJugador) {
        Consola.mostrarDados(dados);
        int dadoExtra = dados.remove(0);
        ArrayList<Integer> jugada = new ArrayList<Integer>();

        String respuesta = Consola.leerString("Ingrese jugada:").trim();
        switch (respuesta) {
            case "A":
                this.ayuda(dados);
                this.pedirJugada(dados, unJugador);
                break;
            case "P":
                Consola.printGreen("Paso de turno");
                Consola.esperarParaContinuar();
                break;
            case "X":
                this.abandonar(unJugador);
                break;
            case "0":
                jugada.add(dadoExtra);
                if (verificarJugada(jugada, dados, true)) {
                    aplicarJugadaEnTablero(jugada, dados, unJugador, true);
                } else {
                    Consola.printlnRed("Jugada no valida, vuelva a ingresar");
                    pedirJugada(dados, unJugador);
                }
                break;
            default:
                try {
                    String[] dadosSeleccionados = respuesta.split(" ");

                    for (String dadoSeleccionado : dadosSeleccionados) {
                        jugada.add(Integer.parseInt(dadoSeleccionado));
                    }

                    if (verificarJugada(jugada, dados, false)) {
                        aplicarJugadaEnTablero(jugada, dados, unJugador, false);
                    } else {
                        Consola.printlnRed("Jugada no valida, vuelva a ingresar");
                        pedirJugada(dados, unJugador);
                    }
                } catch (NumberFormatException e) {
                    Consola.printlnRed("Jugada no valida. Intente nuevamente.");
                    Consola.println("");
                    this.pedirJugada(dados, unJugador);
                }

                break;
        }

    }

    public ArrayList<Integer> tirarDados() {
        Dado[] dados = new Dado[5];
        ArrayList<Integer> numDados = new ArrayList();

        for (int i = 0; i < dados.length; i++) {
            if (this.getModoTest()) {
                dados[i] = new Dado(Consola.leerOpcion("Ingrese número de dado: (1-6)", 1, 6));
            } else {
                dados[i] = new Dado();
            }
            numDados.add(dados[i].getNumero());

        }

        return numDados;
    }

    public boolean verificarJugada(ArrayList<Integer> jugada, ArrayList<Integer> dados, boolean dadoBase) {
        if (dadoBase) {
            return tablero.posicionEstaVacia(dados.get(0));
        } else {
            int total = dados.get(0);
            for (int i = 0; i < jugada.size(); i++) {
                if (!dados.contains(jugada.get(i))) {
                    return false;
                }
                total += jugada.get(i);
            }

            return tablero.posicionEstaVacia(total);
        }
    }

    public void aplicarJugadaEnTablero(ArrayList<Integer> jugada, ArrayList<Integer> dados, Jugador unJugador,
            boolean dadoBase) {
        int posicion = 0;
        String color;

        if (unJugador.equals(jugadorRojo)) {
            color = "red";
        } else {
            color = "blue";
        }

        if (dadoBase) {
            posicion = jugada.get(0);
        } else {
            posicion = dados.get(0);
            for (Integer dado : jugada) {
                posicion += jugada.get(dado);
            }
        }
        this.getTablero().ingresarLetra(posicion, unJugador.getLetraParaJugar(), color);
    }

    public void mostrarPuntaje() {
        setPuntajeRojo(tablero.letraEnSecuencia(jugadorRojo.getLetraParaJugar(), "red"));
        setPuntajeAzul(tablero.letraEnSecuencia(jugadorAzul.getLetraParaJugar(), "blue"));

        Consola.printlnGreen("Puntaje:");
        Consola.println(jugadorRojo.getAlias() + ": " + this.getPuntajeRojo());
        Consola.println(jugadorAzul.getAlias() + ": " + this.getPuntajeAzul());
        Consola.println("");
    }

    public void abandonar(Jugador unJugador) {
        this.setAbandono(true);
        if (unJugador.equals(this.getJugadorRojo())) {
            this.setPuntajeRojo(-1);
        } else {
            this.setPuntajeAzul(-1);
        }
    }

    public void ayuda(ArrayList<Integer> dados) {
        boolean haySolucion = false;
        while (!haySolucion) {
            // dado base
            if (this.tablero.posicionEstaVacia(dados.get(0))) {
                Consola.println("0");
                haySolucion = true;
                break;
            }

            // dado base + 1
            for (int i = 1; i < dados.size(); i++) {
                if (this.tablero.posicionEstaVacia(dados.get(0) + dados.get(i))) {
                    Consola.println("" + dados.get(i));
                    haySolucion = true;
                    break;
                }
            }

            // dado base + 2
            for (int i = 1; i < dados.size(); i++) {
                for (int j = 1; j < dados.size(); j++) {
                    if (i != j && this.tablero.posicionEstaVacia(dados.get(0) + dados.get(i) + dados.get(j))) {
                        Consola.println(dados.get(i) + " + " + dados.get(j));
                        haySolucion = true;
                        break;
                    }
                }
            }

            // dado base + 3
            for (int i = 1; i < dados.size(); i++) {
                for (int j = 1; j < dados.size(); j++) {
                    for (int k = 1; k < dados.size(); k++) {
                        if (i != j && j != k && this.tablero
                                .posicionEstaVacia(dados.get(0) + dados.get(i) + dados.get(j) + dados.get(k))) {
                            Consola.println(dados.get(i) + " + " + dados.get(j) + " + " + dados.get(k));
                            haySolucion = true;
                            break;
                        }
                    }

                }
            }

            // dado base + 4
            for (int i = 1; i < dados.size(); i++) {
                for (int j = 1; j < dados.size(); j++) {
                    for (int k = 1; k < dados.size(); k++) {
                        for (int l = 1; l < dados.size(); l++) {
                            if (i != j && j != k && k != l && this.tablero.posicionEstaVacia(
                                    dados.get(0) + dados.get(i) + dados.get(j) + dados.get(k) + dados.get(l))) {
                                Consola.println(dados.get(i) + " + " + dados.get(j) + " + " + dados.get(k) + " + "
                                        + dados.get(l));
                                haySolucion = true;
                                break;
                            }
                        }
                    }
                }
            }

            // no hay solucion
            if (!haySolucion) {
                Consola.println("P");
            }
        }
    }

    public void terminarPartida() {
        this.getJugadorRojo().setPartidasJugadas(this.getJugadorRojo().getPartidasJugadas() + 1);
        this.getJugadorAzul().setPartidasJugadas(this.getJugadorAzul().getPartidasJugadas() + 1);

        if (this.getPuntajeRojo() > this.getPuntajeAzul()) {
            Consola.println("Ganador: " + this.getJugadorRojo().getAlias());
            this.getJugadorRojo().setPartidasGanadas(this.getJugadorRojo().getPartidasGanadas() + 1);
        } else if (this.getPuntajeRojo() < this.getPuntajeAzul()) {
            Consola.println("Ganador: " + this.getJugadorAzul().getAlias());
            this.getJugadorAzul().setPartidasGanadas(this.getJugadorAzul().getPartidasGanadas() + 1);
        } else if (this.getPuntajeRojo() == this.getPuntajeAzul()) {
            Consola.println("EMPATE");
        }
    }

}
