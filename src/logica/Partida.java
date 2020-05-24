
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

    public void jugar(Consola unaConsola) {
        boolean abandono = false;
        while (!this.getTablero().estaCompleto() || abandono) {

            unaConsola.mostrarTablero(getTablero().getMatriz());

            unaConsola.printGreen("TURNO DE JUGADOR ROJO");
            jugada(unaConsola, getJugadorRojo());

            unaConsola.mostrarTablero(getTablero().getMatriz());

            unaConsola.printGreen("TURNO DE JUGADOR AZUL");
            jugada(unaConsola, getJugadorAzul());
        }
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
                break;
            case "P":
                unaConsola.println("PASA DE TURNO");
                break;
            case "X":
                //abandonar();
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
        for (Integer num : jugada) {
            pos += jugada.get(num);
        }
        if(unJugador.equals(jugadorRojo)){
            color = "red";
        }else{
            color = "blue";
        }        
        boolean ingresar = tablero.ingresarLetra(pos, unJugador.getLetraParaJugar(),color);
    }

}
