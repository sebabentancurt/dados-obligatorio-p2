//Mateo Sapiurka - Sebastian Bentancurt
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import dominio.Dado;
import dominio.Jugador;
import dominio.Tablero;
import helper.Color;
import java.util.ArrayList;
import logica.Partida;
import java.util.Scanner;
import java.util.regex.Pattern;

import logica.Sistema;

/**
 *
 * @author Mateo
 */
public class Consola {

    public void saludar() {
        printlnGreen("¡Bienvenido a dados!");
    }

    //Muestra menu y pide opcion
    public void menu(Sistema unSistema) {
        println("");
        println("1) Registrar Jugador");
        println("2) Jugar");
        println("3) Ver ranking");
        println("4) Salir");
        println("");
        int respuesta = Consola.leerOpcion("Ingrese opción:", 1, 4);
        println("");

        switch (respuesta) {
            case 1:
                unSistema.registroJugador();
                menu(unSistema);
                break;
            case 2:
                if (unSistema.getListaJugadores().size() > 1) {
                    unSistema.crearPartida();
                } else {
                    printlnRed("Debe registrar al menos 2 jugadores para comenzar el juego.");
                }
                esperarParaContinuar();
                menu(unSistema);
                break;

            case 3:
                printlnGreen("Ranking de jugadores:");
                unSistema.listaOrdenada();
                unSistema.imprimirListaJugadores();
                esperarParaContinuar();
                menu(unSistema);
                break;
            case 4:
                printlnGreen("Gracias por jugar, hasta la vuelta ;)");
                break;
            default:
                printlnRed("Ha ocurrido un error");
                menu(unSistema);
                break;
        }
    }

    public static String leerString(String message) {
        Scanner scan = new Scanner(System.in);
        Consola.printlnYellow(message);
        while (!scan.hasNextLine()) {
            Consola.printlnRed("Entrada no es un texto. Intente nuevamente.");

        }
        return scan.nextLine();
    }

    public static Integer leerInt(String message) {
        Scanner scan = new Scanner(System.in);
        Consola.printlnYellow(message);

        while (!scan.hasNextInt()) {
            Consola.printlnRed("Entrada no es un entero. Intente nuevamente.");
            scan.next();
        }
        return scan.nextInt();
    }

    

    public static void esperarParaContinuar() {
        Consola.println("");
        Consola.printYellow("Presiona \"ENTER\" para continuar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static String leerLetra(String message) {
        Scanner scan = new Scanner(System.in);
        Consola.printlnYellow(message);
        Pattern pattern = Pattern.compile("[A-Za-z]");

        while (!scan.hasNext(pattern)) {
            Consola.printlnRed("Entrada no es una letra. Intente nuevamente.");
            scan.next();
        }
        return scan.next();
    }

    public static Boolean leerConfirmacion(String message) {
        Scanner scan = new Scanner(System.in);
        Consola.printlnYellow(message + " S/N");
        Pattern pattern = Pattern.compile("[SsNn]");
        Boolean confirmado = false;

        while (!scan.hasNext(pattern)) {
            Consola.printlnRed("Opción no valida. Intente nuevamente.");
            scan.next();
        }

        String confirmacion = scan.next();
        if(confirmacion.equals("S") || confirmacion.equals("s")){
            confirmado = true;
        }
        return confirmado;
    }

    /**
     * Lee una opcion como Int entre rangos
     *
     * @param message
     * @return
     */
    public static Integer leerOpcion(String message, Integer minOpcion, Integer maxOpcion) {
        Scanner scan = new Scanner(System.in);
        Consola.printlnYellow(message);
        Integer opcion = 0;
        while (true) {
            if (!scan.hasNextInt()) {
                Consola.printlnRed("Opción no es un entero. Intente nuevamente.");
                scan.next();
                continue;
            }

            opcion = scan.nextInt();
            if (!(opcion >= minOpcion && opcion <= maxOpcion)) {
                Consola.printlnRed("Opción no valida. Intente nuevamente.");
                continue;
            }
            break;
        }
        return opcion;
    }



    

    public static boolean consultaModoTest() {
        boolean modoTest = Consola.leerConfirmacion("¿Desea activar el modo test?");

        if (modoTest) {
            Consola.printlnGreen("Modo test activado");
        }

        return modoTest;
    }

    public static void mostrarDados(ArrayList<Integer> dados) {
        Consola.printGreen("Base: " + dados.get(0) + " ");

        Consola.printGreen("Extras: ");
        for (int i = 1; i < dados.size(); i++) {
            printGreen(dados.get(i) + " ");
        }
        Consola.println("");
    }

    /**
     * Imprime la matriz formateada
     */
    public static void mostrarTablero(String[][] matriz) {
        Consola.printlnGreen("Tablero:");
        for (int i = 0; i < Tablero.FILAS; i++) {
            for (int j = 0; j < Tablero.COLUMNAS; j++) {
                Consola.print(String.format("%15s", matriz[i][j]) +" ");
            }
            Consola.println("");
        }
        Consola.println("");
    }

    public static void printlnGreen(String value) {
        System.out.println(Color.addColorToString(value, "green"));
    }

    public static void printlnRed(String value) {
        System.out.println(Color.addColorToString(value, "red"));
    }

    public static void printlnPurple(String value) {
        System.out.println(Color.addColorToString(value, "purple"));
    }
    public static void printlnYellow(String value) {
        System.out.println(Color.addColorToString(value, "yellow"));
    }

    public static void printlnBlue(String value) {
        System.out.println(Color.addColorToString(value, "blue"));
    }

    public static void printGreen(String value) {
        System.out.print(Color.addColorToString(value, "green"));
    }

    public static void printYellow(String value) {
        System.out.print(Color.addColorToString(value, "yellow"));
    }

    public static void print(String value) {
        System.out.print(value);
    }

    public static void println(String value) {
        System.out.println(value);
    }

}
