
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import dominio.Jugador;
import dominio.Tablero;

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

    public String getLetraRojo() {
        return letraRojo;
    }

    public String getLetraAzul() {
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

    public void setLetraRojo(String unaLetra) {
        this.letraRojo = unaLetra;
    }

    public void setLetraAzul(String unaLetra) {
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
