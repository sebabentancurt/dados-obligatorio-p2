//Mateo Sapiurka - Sebastian Bentancurt
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
/**
 *
 * @author Mateo
 */

import logica.Sistema;
import dominio.Tablero;
import interfaz.Consola;
import dominio.Jugador;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Sistema s = new Sistema();
        Consola consola = new Consola();
        /*
        s.agregarJugador(new Jugador("seba",21,"sebaalias"));
        s.agregarJugador(new Jugador("mateo",27,"Mateoalias"));
         */
        consola.saludar();
        consola.menu(s);
    }

}
