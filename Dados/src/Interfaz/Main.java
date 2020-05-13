package Interfaz;


import Logica.Sistema;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mateo
 */
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
        
        /*
        Tablero tablero = new Tablero(consola);
        tablero.mostrar();
        tablero.ingresarValor(5, "A", "blue");
        tablero.mostrar();
        */
    }

}
