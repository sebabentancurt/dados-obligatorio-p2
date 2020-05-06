/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.util.Scanner;

/**
 *
 * @author Mateo
 */
public class Consola {

    public void saludar() {

        System.out.println("BIENVENIDO A DADOS!");
    }

    public void menu() {

        System.out.println("MENÚ:");
        System.out.println("1.REGISTRAR JUGADOR");
        System.out.println("2.JUGAR");
        System.out.println("3.VER RANKING");
        System.out.println("4.SALIR");

        Scanner in = new Scanner(System.in);
        int respuesta;

        respuesta = in.nextInt();
        switch (respuesta) {
            case 1:
                //CODIGO ACA
                System.out.println("REGISTRAR UN JUGADOR NUEVO!");
                //registroJugadores();
                break;
            case 2:
                //CODIGO ACA
                System.out.println("COMENZAR PARTIDA!");
                //partida();
                break;
            case 3:
                //CODIGO ACA
                System.out.println("ESTE ES EL RANKING");
                //mostrarRanking();
                break;
            case 4:
                System.out.println("GRACIAS POR JUGAR, HASTA LA VUELTA ;)");
                break;
            default:
                System.out.println("ERROR, VUELVA A SELECCIONAR");
                menu();

        }
    }
}
