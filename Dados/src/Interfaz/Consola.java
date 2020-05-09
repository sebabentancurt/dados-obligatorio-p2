/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Dominio.Jugador;
import Logica.Sistema;
import java.util.Scanner;

/**
 *
 * @author Mateo
 */
public class Consola {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public void saludar() {
        printGreen("BIENVENIDO A DADOS!");
    }

    public void menu(Sistema unSistema) {

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
                registroJugador(unSistema);
                menu(unSistema);
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
                printRed("ERROR, VUELVA A SELECCIONAR");
                menu(unSistema);

        }
    }
    
    public void registroJugador(Sistema unSistema){
        Scanner in = new Scanner(System.in);
        
        printGreen("REGISTRO DE JUGADOR:");
        System.out.println("Ingrese nombre");
        String nombre = in.nextLine();
        System.out.println("Ingrese edad");
        int edad = in.nextInt();
        in.nextLine();
        System.out.println("Ingrese alias");
        String alias = in.nextLine();
        
        Jugador j = new Jugador(nombre, edad, alias);
        unSistema.agregarJugador(j);
        printGreen(j.getAlias() + " fue registrado con exito!");
    }
    
    public void crearPartida(){
    
    }


    public void printGreen(String value){
        System.out.println(ANSI_GREEN + value + ANSI_RESET);
    }

    public void printRed(String value){
        System.out.println(ANSI_RED + value + ANSI_RESET);
    }

    public void print(String value){
        System.out.print(value);
    }

    public void println(String value){
        System.out.println(value);
    }

    public String addColor(String value, String color){
        String prefix = "";
        switch (color) {
            case "red":
                    prefix = ANSI_RED;
                break;
            case "blue":
                    prefix = ANSI_BLUE;
                break;
            case "green":
                prefix = ANSI_GREEN;
            break;
            default:
                break;
        }
        return prefix + value + ANSI_RESET;
    }
}
