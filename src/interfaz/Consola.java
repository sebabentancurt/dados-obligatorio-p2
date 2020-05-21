/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import dominio.Jugador;
import dominio.Tablero;
import helper.Color;
import logica.Partida;
import logica.Sistema;

import java.util.Scanner;

/**
 *
 * @author Mateo
 */
public class Consola {

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
                unSistema.registroJugador();
                menu(unSistema);
                break;
            case 2:
                if (unSistema.getListaJugadores().size() > 1) {
                    crearPartida(unSistema);
                } else {
                    printRed("Debe registrarse al menos 2 jugadores");
                }
                menu(unSistema);
                break;
                
            case 3:
                System.out.println("RANKING JUGADORES");
                unSistema.listaOrdenada();
                imprimirListaJugadores(unSistema);
                menu(unSistema);
                break;
            case 4:
                System.out.println("GRACIAS POR JUGAR, HASTA LA VUELTA ;)");
                break;
            default:
                printRed("ERROR, VUELVA A SELECCIONAR");
                menu(unSistema);

        }
    }

    public static String leerString(String message){
        Scanner scan = new Scanner(System.in);
        System.out.println(message);
        while (!scan.hasNextLine()) 
        {
            Consola.printRed("Entrada no es un texto. Intente nuevamente.");
  
        }
        return scan.nextLine();
    }

    public static Integer leerInt(String message){
        Scanner scan = new Scanner(System.in);
        System.out.println(message);
        
        while (!scan.hasNextInt()) 
        {
            Consola.printRed("Entrada no es un entero. Intente nuevamente.");
            scan.next();
        }
        return scan.nextInt();
    }

    public static String leerLetra(){
        Scanner scan = new Scanner(System.in);
        
        while (!scan.hasNext("[a-zA-Z]")) 
        {
            Consola.printRed("Entrada no es una letra. Intente nuevamente.");
            scan.next();
        }
        return scan.nextLine();
    }

    public static Integer leerOpcion(String message){
        Scanner scan = new Scanner(System.in);
        System.out.println(message);
        
        while (!scan.hasNextInt()) 
        {
            Consola.printRed("Entrada no es un entero. Intente nuevamente.");
            scan.next();
        }
        return scan.nextInt();
    }

    public void crearPartida(Sistema unSistema) {
        Jugador[] jugadoresSeleccionados = seleccionarJugadores(unSistema);
        char[] letrasSeleccionadas = seleccionarLetras();
        boolean modoTest = consultaModoTest();

        Partida partida = new Partida(jugadoresSeleccionados[0], jugadoresSeleccionados[1], letrasSeleccionadas[0], letrasSeleccionadas[1], modoTest);
        partida.jugar();

    }

    public Jugador[] seleccionarJugadores(Sistema unSistema) {
        int seleccionRojo = -1;
        int seleccionAzul = -1;
        Jugador rojo;
        Jugador azul;
        Jugador[] jugadoresSeleccionados = new Jugador[2];
        Scanner in = new Scanner(System.in);

        imprimirListaJugadores(unSistema);

        println("SELECCIONE JUGADOR ROJO");
        while (seleccionRojo < 1 || seleccionRojo > unSistema.getListaJugadores().size()) {
            seleccionRojo = in.nextInt();
        }
        rojo = unSistema.getListaJugadores().get(seleccionRojo - 1);
        println("Jugador rojo: " + rojo);

        println("SELECCIONE JUGADOR AZUL");
        while (seleccionAzul < 1 || seleccionAzul > unSistema.getListaJugadores().size() || seleccionAzul == seleccionRojo) {
            seleccionAzul = in.nextInt();
        }
        azul = unSistema.getListaJugadores().get(seleccionAzul - 1);
        println("Jugador azul: " + azul);

        jugadoresSeleccionados[0] = rojo;
        jugadoresSeleccionados[1] = azul;

        return jugadoresSeleccionados;
    }

    public void imprimirListaJugadores(Sistema unSistema) {
        println("LISTA DE JUGADORES");
        if (unSistema.getListaJugadores().isEmpty()) {
            printRed("LISTA VACIA");
            println("registre al menos 2 jugadores");
        } else {
            for (int i = 0; i < unSistema.getListaJugadores().size(); i++) {
                println((i + 1) + ". " + unSistema.getListaJugadores().get(i));
            }
        }
    }

    public char[] seleccionarLetras() {
        Scanner in = new Scanner(System.in);
        char letraRojo = '1';
        char letraAzul = '2';
        char[] letrasSeleccionadas = new char[2];

        while (!Character.isLetter(letraRojo)) {
            println("Ingrese letra jugador Rojo");
        letraRojo = Character.toUpperCase(in.nextLine().charAt(0));
        }
        
        while (!Character.isLetter(letraAzul)) {
        println("Ingrese letra jugador Azul");
        letraAzul = Character.toUpperCase(in.nextLine().charAt(0));
        }

        letrasSeleccionadas[0] = letraRojo;
        letrasSeleccionadas[1] = letraAzul;

        return letrasSeleccionadas;
    }

    public boolean consultaModoTest() {
        boolean modoTest = false;
        String respuesta = "";
        Scanner in = new Scanner(System.in);

        println("DESEA ACTIVAR MODO TEST? S/N");
        while (!respuesta.equalsIgnoreCase("S") && !respuesta.equalsIgnoreCase("N")) {
            respuesta = in.nextLine();
        }

        if (respuesta.equals("S")) {
            modoTest = true;
            println("MODO TEST ACTIVADO");
        }

        return modoTest;
    }

    /**
     * Imprime la matriz formateada
     */
    public void mostrarTablero(String[][] matriz) {
        int k = 1;
        for (int i = 0; i < Tablero.FILAS; i++) {
            for (int j = 0; j < Tablero.COLUMNAS; j++) {
                this.print(matriz[i][j]);
                if ((k >= 10)){
                    this.print("    ");
                }else {
                    this.print("     ");
                }
                k++;
            }
            this.println("");
        }
    }

    public static void printGreen(String value) {
        System.out.println(Color.addColorToString(value, "green"));
    }

    public static void printRed(String value) {
        System.out.println(Color.addColorToString(value, "red"));
    }

    public static void print(String value) {
        System.out.print(value);
    }

    public static void println(String value) {
        System.out.println(value);
    }


}
