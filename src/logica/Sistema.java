//Mateo Sapiurka - Sebastian Bentancurt
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import interfaz.Consola;

import java.util.ArrayList;
import java.util.Collections;

import dominio.Jugador;

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
        Consola.printlnGreen("Registrar jugador:");
        String nombre = Consola.leerString("Ingrese nombre:");
        int edad = Consola.leerInt("Ingrese edad");
        String alias = Consola.leerString("Ingrese alias:");

        Jugador jugador = new Jugador(nombre, edad, alias);

        if (this.existeJugador(jugador)) {
            Consola.printlnRed("Ya existe jugador con mismo alias");
        } else {
            this.agregarJugador(jugador);
            Consola.printlnGreen(jugador.getAlias() + " fue registrado con exito!");
        }
        Consola.esperarParaContinuar();
    }

    public boolean existeJugador(Jugador unJugador){
        return this.getListaJugadores().contains(unJugador);
    }

    public void crearPartida() {
        Jugador[] jugadoresSeleccionados = this.seleccionarJugadores();
        String[] letrasSeleccionadas = seleccionarLetras();
        for (int i = 0; i < jugadoresSeleccionados.length; i++){
            jugadoresSeleccionados[i].setLetraParaJugar(letrasSeleccionadas[i]);
        }
        boolean modoTest = Consola.consultaModoTest();
        Consola.println("");
        Consola.printGreen("Se va a comenzar el juego.");
        Consola.esperarParaContinuar();
        Consola.println("");

        Partida partida = new Partida(jugadoresSeleccionados[0], jugadoresSeleccionados[1], modoTest);
        partida.jugar();

    }

    /**
     * Retorna dos jugadores seleccionados de la lista completa de jugadores
     *
     * @param unSistema
     * @return
     */
    public Jugador[] seleccionarJugadores() {

        Jugador[] jugadoresSeleccionados = new Jugador[2];

        this.imprimirListaJugadores();
        Consola.println("");

        Integer seleccionRojo = Consola.leerOpcion("Seleccione jugador rojo:", 1, this.getListaJugadores().size());
        Jugador jugadorRojo = this.getListaJugadores().get(seleccionRojo - 1);

        Integer seleccionAzul = Consola.leerOpcion("Seleccione jugador azul:", 1, this.getListaJugadores().size());
        while (seleccionRojo.equals(seleccionAzul)) {
            Consola.printlnRed("El jugador ya ha sido seleccionado. Intente nuevamente");
            seleccionAzul = Consola.leerOpcion("Seleccione jugador azul:", 1, this.getListaJugadores().size());
        }
        Jugador jugadorAzul = this.getListaJugadores().get(seleccionAzul - 1);

        jugadoresSeleccionados[0] = jugadorRojo;
        jugadoresSeleccionados[1] = jugadorAzul;

        return jugadoresSeleccionados;
    }

    /**
     * Imprime la lista de jugadores de no ser vacia
     *
     */
    public void imprimirListaJugadores() {
        if (this.getListaJugadores().isEmpty()) {
            Consola.printlnRed("No existen jugadores registrados.");
        } else {
            for (int i = 0; i < this.getListaJugadores().size(); i++) {
                Consola.println((i + 1) + ") " + this.getListaJugadores().get(i));
            }
        }
    }

    public String[] seleccionarLetras() {
        String[] letrasSeleccionadas = new String[2];

        String letraRojo = Consola.leerLetra("Ingrese letra jugador Rojo").toUpperCase();

        String letraAzul = Consola.leerLetra("Ingrese letra jugador Azul").toUpperCase();
        while (letraAzul.equals(letraRojo)) {
            Consola.printlnRed("Los jugadores deben tener letra diferente. Intente nuevamente.");
            letraAzul = Consola.leerLetra("Ingrese letra jugador Azul").toUpperCase();
        }

        letrasSeleccionadas[0] = letraRojo;
        letrasSeleccionadas[1] = letraAzul;

        return letrasSeleccionadas;
    }
}
