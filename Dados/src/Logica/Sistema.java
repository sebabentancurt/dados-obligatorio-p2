/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Dominio.Jugador;
import Interfaz.Consola;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Mateo
 */
public class Sistema {
    public ArrayList<Jugador> listaJugadores;

    public Sistema() {
        listaJugadores = new ArrayList<Jugador>();
    }

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void agregarJugador(Jugador unJugador) {
        this.getListaJugadores().add(unJugador);
    }

    public ArrayList<Jugador> listaOrdenada() {
        Collections.sort(this.getListaJugadores());
        return this.getListaJugadores();
    }

    public void registroJugador() {
        Consola.printGreen("REGISTRO DE JUGADOR:");
        String nombre = Consola.leerString("Ingrese nombre:");
        int edad = Consola.leerInt("Ingrese edad");
        String alias = Consola.leerString("Ingrese alias:");

        Jugador jugador = new Jugador(nombre, edad, alias);

        Jugador j = new Jugador(nombre, edad, alias);

        if (this.existeJugador(j)) {
            Consola.printRed("Ya existe jugador con mismo alias");
        } else {
            this.agregarJugador(j);
            Consola.printGreen(j.getAlias() + " fue registrado con exito!");
        }

        Consola.printGreen(jugador.getAlias() + " fue registrado con exito!");
    }

    /*public void comenzarPartida() {
        
    }*/

    public boolean existeJugador(Jugador unJugador){
        return this.getListaJugadores().contains(unJugador);
    }

}
