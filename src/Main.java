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

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Sistema s = new Sistema();
        Consola consola = new Consola();
        
        consola.saludar();
        consola.menu(s);
        
        /*Consola consola = new Consola();
        Tablero tablero = new Tablero();
        consola.mostrarTablero(tablero.getMatriz());

        int[] posiciones = {9,10,7,5,2,12,8,3,20};
        for (int posicion : posiciones) {
            tablero.ingresarLetra(posicion, "A", "blue");
        }
        
        consola.mostrarTablero(tablero.getMatriz());
        Consola.printGreen(Integer.toString(tablero.secuenciaDiagonal("A", "blue")));*/

        
    }

}
