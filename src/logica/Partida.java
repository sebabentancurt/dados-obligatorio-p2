<<<<<<< Updated upstream:src/logica/Partida.java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import dominio.Jugador;
import dominio.Tablero;
import interfaz.Consola;

/**
 *
 * @author Mateo
 */
public class Partida {

    private Jugador jugadorRojo;
    private Jugador jugadorAzul;
    private String letraRojo;
    private String letraAzul;
    private boolean modoTest;
    private Tablero tablero;

    public Partida(Jugador unJugadorRojo, Jugador unJugadorAzul, String unaLetraRojo, String unaLetraAzul, boolean modoTest) {
        this.setJugadorRojo(unJugadorRojo);
        this.setJugadorAzul(unJugadorAzul);
        this.setLetraRojo(unaLetraRojo);
        this.setLetraAzul(unaLetraAzul);
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

    public char getLetraRojo() {
        return letraRojo;
    }

    public char getLetraAzul() {
        return letraAzul;
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

    public void setLetraRojo(char unaLetra) {
        this.letraRojo = unaLetra;
    }

    public void setLetraAzul(char unaLetra) {
        this.letraAzul = unaLetra;
    }

    public void setModoTest(boolean modoTest) {
        this.modoTest = modoTest;
    }
    
    public void setTablero () {
        this.tablero = new Tablero();
    }

    public void jugar() {
        boolean abandono = false;
        while(!this.getTablero().estaCompleto() || abandono){
            //solicitarRojo()
            //verificarRojo()
            //aplicarRojo()
            
            //solicitarAzul()
            //verificarAzul()
            //aplicarAzul()
        }
    }

}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Dominio.Dado;
import Dominio.Jugador;
import Dominio.Tablero;
import Interfaz.Consola;

/**
 *
 * @author Mateo
 */
public class Partida {

    private Jugador jugadorRojo;
    private Jugador jugadorAzul;
    private char letraRojo;
    private char letraAzul;
    private boolean modoTest;
    private Tablero tablero;

    public Partida(Jugador unJugadorRojo, Jugador unJugadorAzul, char unaLetraRojo, char unaLetraAzul, boolean modoTest) {
        this.setJugadorRojo(unJugadorRojo);
        this.setJugadorAzul(unJugadorAzul);
        this.setLetraRojo(unaLetraRojo);
        this.setLetraAzul(unaLetraAzul);
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

    public char getLetraRojo() {
        return letraRojo;
    }

    public char getLetraAzul() {
        return letraAzul;
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

    public void setLetraRojo(char unaLetra) {
        this.letraRojo = unaLetra;
    }

    public void setLetraAzul(char unaLetra) {
        this.letraAzul = unaLetra;
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

            unaConsola.mostrarTablero(tablero.getMatriz());

            //solicitarRojo()
            //verificarRojo()
            //aplicarRojo()
            //solicitarAzul()
            //verificarAzul()
            //aplicarAzul()
        }
    }

    public void SolicitarRojo() {
        Dado[] dados = tirarDados();
        
    }

    public Dado[] tirarDados() {
        Dado[] dados = new Dado[5];

        for (int i = 0; i < dados.length; i++) {
            dados[i] = new Dado();
        }

        return dados;
    }

}
>>>>>>> Stashed changes:Dados/src/Logica/Partida.java
