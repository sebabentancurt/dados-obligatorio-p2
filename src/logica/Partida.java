
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

    //Getters
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

    //Setters
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

    public void jugar(Consola unaConsola) {
        while (!this.getTablero().estaCompleto() || this.getAbandono()) {

            mostrarPuntaje(unaConsola);

            unaConsola.mostrarTablero(getTablero().getMatriz());

            unaConsola.printGreen("TURNO DE JUGADOR ROJO");
            jugada(unaConsola, getJugadorRojo());

            mostrarPuntaje(unaConsola);

            unaConsola.mostrarTablero(getTablero().getMatriz());

            unaConsola.printGreen("TURNO DE JUGADOR AZUL");
            jugada(unaConsola, getJugadorAzul());
        }
        this.terminarPartida(unaConsola);
    }

    public void jugada(Consola unaConsola, Jugador unJugador) {
        ArrayList<Integer> dados = tirarDados(unaConsola);
        pedirJugada(unaConsola, dados, unJugador);
    }

    public void pedirJugada(Consola unaConsola, ArrayList<Integer> dados, Jugador unJugador) {
        unaConsola.mostrarDados(dados);

        String respuesta = unaConsola.leerString("Ingrese jugada");
        switch (respuesta) {
            case "A":
                //ayuda();
                pedirJugada(unaConsola, dados, unJugador);
                break;
            case "P":
                unaConsola.println("PASA DE TURNO");
                break;
            case "X":
                abandonar(unJugador);
                break;
            default:
                String[] respuestaArray = respuesta.split(" ");

                ArrayList<Integer> jugada = new ArrayList<Integer>();
                for (String num : respuestaArray) {
                    jugada.add(Integer.parseInt(num));
                }

                if (verificarJugada(jugada, dados)) {
                    aplicarJugadaEnTablero(jugada, unJugador);
                } else {
                    unaConsola.printRed("Jugada no valida, vuelva a ingresar");
                    pedirJugada(unaConsola, dados, unJugador);
                }

                break;
        }

    }

    public ArrayList<Integer> tirarDados(Consola unaConsola) {
        Dado[] dados = new Dado[5];
        ArrayList<Integer> numDados = new ArrayList();

        if (getModoTest()) {
            for (int i = 0; i < dados.length; i++) {
                dados[i] = new Dado(unaConsola.leerOpcion("Ingrese numero de dado", 1, 6));
            }
        } else {
            for (int i = 0; i < dados.length; i++) {
                dados[i] = new Dado();
            }
        }

        for (int i = 0; i < dados.length; i++) {
            numDados.add(dados[i].getNumero());
        }

        return numDados;
    }

    public boolean verificarJugada(ArrayList<Integer> jugada, ArrayList<Integer> dados) {
        if (jugada.size() == 1 && jugada.contains(0)) {
            return tablero.posicionEstaVacia(dados.get(0));
        } else {
            int total = dados.get(0);
            for (int i = 1; i < dados.size(); i++) {
                if (!dados.contains(jugada.get(i))) {
                    return false;
                }
                total += jugada.get(i);
            }

            return tablero.posicionEstaVacia(total);
        }
    }

    public void aplicarJugadaEnTablero(ArrayList<Integer> jugada, Jugador unJugador) {
        int pos = 0;
        String color;

        if (unJugador.equals(jugadorRojo)) {
            color = "red";
        } else {
            color = "blue";
        }
        if (jugada.size() == 1 && jugada.contains(0)) {
            pos = jugada.get(0);
        } else {
            for (Integer num : jugada) {
                pos += jugada.get(num);
            }
        }
        boolean ingresar = tablero.ingresarLetra(pos, unJugador.getLetraParaJugar(), color);
    }

    public void mostrarPuntaje(Consola unaConsola) {
        setPuntajeRojo(tablero.letraEnSecuencia(jugadorRojo.getLetraParaJugar(), "red"));
        setPuntajeRojo(tablero.letraEnSecuencia(jugadorAzul.getLetraParaJugar(), "blue"));

        unaConsola.println("PUNTAJE:");
        unaConsola.println(jugadorRojo.getAlias() + ": " + this.getPuntajeRojo());
        unaConsola.println(jugadorAzul.getAlias() + ": " + this.getPuntajeAzul());
    }

    public void abandonar(Jugador unJugador) {
        this.setAbandono(true);
        if (unJugador.equals(this.getJugadorRojo())) {
            this.setPuntajeRojo(-1);
        } else {
            this.setPuntajeAzul(-1);
        }
    }

    public void terminarPartida(Consola unaConsola) {
        this.getJugadorRojo().setPartidasJugadas(this.getJugadorRojo().getPartidasJugadas() + 1);
        this.getJugadorAzul().setPartidasJugadas(this.getJugadorAzul().getPartidasJugadas() + 1);
        if (this.getPuntajeRojo() > this.getPuntajeAzul()) {
            unaConsola.println("Ganador: " + this.getJugadorRojo().getAlias());
            this.getJugadorRojo().setPartidasGanadas(this.getJugadorRojo().getPartidasGanadas() + 1);
        } else if (this.getPuntajeRojo() < this.getPuntajeAzul()) {
            unaConsola.println("Ganador: " + this.getJugadorAzul().getAlias());
            this.getJugadorAzul().setPartidasGanadas(this.getJugadorAzul().getPartidasGanadas() + 1);
        } else if (this.getPuntajeRojo() == this.getPuntajeAzul()) {
            unaConsola.println("EMPATE");
        }
    }

}
