/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Dominio.Jugador;

/**
 *
 * @author Mateo
 */
public class Partida {

    private Jugador jugadorRojo;
    private Jugador jugadorAzul;
    private String letraRojo;
    private String letraAzul;
    public boolean modoTest;

    public Partida(Jugador unJugadorRojo, Jugador unJugadorAzul, String unaLetraRojo, String unaLetraAzul, boolean modoTest) {
        this.setJugadorRojo(unJugadorRojo);
        this.setJugadorAzul(unJugadorAzul);
        this.setLetraRojo(unaLetraRojo);
        this.setLetraAzul(unaLetraAzul);
        this.setModoTest(modoTest);
    }
    
    //Getters
    
    public Jugador getJugadorRojo(){
        return jugadorRojo;
    }
    
    public Jugador getJugadorAzul(){
        return jugadorAzul;
    }
    
    public String getLetraRojo(){
        return letraRojo;
    }
    
    public String getLetraAzul(){
        return letraAzul;
    }
    
    public boolean getModoTest(){
        return modoTest;
    }
    
    //Setters

    public void setJugadorRojo(Jugador unJugador) {
        this.jugadorRojo = unJugador;
    }
    
    public void setJugadorAzul (Jugador unJugador){
        this.jugadorAzul = unJugador;
    }
    
    public void setLetraRojo (String unaLetra){
        this.letraRojo = unaLetra;
    }
    
    public void setLetraAzul (String unaLetra) {
        this.letraAzul = unaLetra;
    }
    
    public void setModoTest (boolean modoTest){
        this.modoTest = modoTest;
    }

}
