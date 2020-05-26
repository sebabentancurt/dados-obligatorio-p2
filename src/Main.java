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
        // TODO code application logic here
        Sistema s = new Sistema();
        Consola consola = new Consola();
        Tablero tablero = new Tablero();
        s.agregarJugador(new Jugador("seba",21,"sebaalias"));
        s.agregarJugador(new Jugador("mateo",27,"Mateoalias"));
        consola.saludar();
        consola.menu(s);
        
        /*consola.mostrarTablero(tablero.getMatriz());

        int[] posiciones = {9,10,7,5,2,12,14,20,15,1,11,17,6,18,13,19,8,4,3,16};
        for (int posicion : posiciones) {
            tablero.ingresarLetra(posicion, "A", "blue");
        }
        
        consola.mostrarTablero(tablero.getMatriz());
        Consola.printGreen(Integer.toString(tablero.letraEnSecuencia("A", "blue")));*/

    }

}
