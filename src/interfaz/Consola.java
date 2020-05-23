
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

import logica.Sistema;

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
                    printRed("Debe registrar al menos 2 jugadores para comenzar el juego.");
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

    public static String leerString(String message) {
        Scanner scan = new Scanner(System.in);
        Consola.printGreen(message);
        while (!scan.hasNextLine()) {
            Consola.printRed("Entrada no es un texto. Intente nuevamente.");

        }
        return scan.nextLine();
    }

    public static Integer leerInt(String message) {
        Scanner scan = new Scanner(System.in);
        Consola.printGreen(message);

        while (!scan.hasNextInt()) {
            Consola.printRed("Entrada no es un entero. Intente nuevamente.");
            scan.next();
        }
        return scan.nextInt();
    }

    public static String leerLetra(String message) {
        Scanner scan = new Scanner(System.in);
        Consola.printGreen(message);

        while (!scan.hasNext("[a-zA-Z]")) {
            Consola.printRed("Entrada no es una letra. Intente nuevamente.");
            scan.next();
        }
        return scan.nextLine();
    }

    /**
     * Lee una opcion como Int entre rangos
     *
     * @param message
     * @return
     */
    public static Integer leerOpcion(String message, Integer minOpcion, Integer maxOpcion) {
        Scanner scan = new Scanner(System.in);
        System.out.println(message);
        Integer opcion = 0;
        while (true) {
            if (!scan.hasNextInt()) {
                Consola.printRed("Opción no es un entero. Intente nuevamente.");
                scan.next();
                continue;
            }

            opcion = scan.nextInt();
            if (!(opcion >= minOpcion && opcion <= maxOpcion)) {
                Consola.printRed("Opción no valida. Intente nuevamente.");
                continue;
            }
            break;
        }
        return opcion;
    }

    public void crearPartida(Sistema unSistema) {
        Jugador[] jugadoresSeleccionados = seleccionarJugadores(unSistema);
        String[] letrasSeleccionadas = seleccionarLetras();
        boolean modoTest = consultaModoTest();

        Partida partida = new Partida(jugadoresSeleccionados[0], jugadoresSeleccionados[1], letrasSeleccionadas[0], letrasSeleccionadas[1], modoTest);
        partida.jugar(this);

    }

    /**
     * Retorna dos jugadores seleccionados de la lista completa de jugadores
     *
     * @param unSistema
     * @return
     */
    public Jugador[] seleccionarJugadores(Sistema unSistema) {

        Jugador[] jugadoresSeleccionados = new Jugador[2];

        imprimirListaJugadores(unSistema);

        Integer seleccionRojo = Consola.leerOpcion("SELECCIONE JUGADOR ROJO", 1, unSistema.getListaJugadores().size());
        Jugador jugadorRojo = unSistema.getListaJugadores().get(seleccionRojo - 1);

        Integer seleccionAzul = Consola.leerOpcion("SELECCIONE JUGADOR AZUL", 1, unSistema.getListaJugadores().size());
        while (seleccionRojo.equals(seleccionAzul)) {
            Consola.printRed("El jugador ya ha sido seleccionado. Intente nuevamente");
            seleccionAzul = Consola.leerOpcion("SELECCIONE JUGADOR AZUL", 1, unSistema.getListaJugadores().size());
        }
        Jugador jugadorAzul = unSistema.getListaJugadores().get(seleccionRojo - 1);

        jugadoresSeleccionados[0] = jugadorRojo;
        jugadoresSeleccionados[1] = jugadorAzul;

        return jugadoresSeleccionados;
    }

    /**
     * Imprime la lista de jugadores de no ser vacia
     *
     * @param unSistema
     */
    public void imprimirListaJugadores(Sistema unSistema) {
        printGreen("LISTA DE JUGADORES");
        if (unSistema.getListaJugadores().isEmpty()) {
            printRed("LISTA VACIA");
        } else {
            for (int i = 0; i < unSistema.getListaJugadores().size(); i++) {
                println((i + 1) + ". " + unSistema.getListaJugadores().get(i));
            }
        }
    }

    public String[] seleccionarLetras() {
        Scanner in = new Scanner(System.in);
        String[] letrasSeleccionadas = new String[2];

        String letraRojo = Consola.leerLetra("Ingrese letra jugador Rojo");

        String letraAzul = Consola.leerLetra("Ingrese letra jugador Azul");
        while (letraAzul.equals(letraRojo)) {
            Consola.printRed("Los jugadores deben tener letra diferente. Intente nuevamente.");
            letraAzul = Consola.leerLetra("Ingrese letra jugador Azul");
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

    public void mostrarDados(ArrayList<Integer> dados) {
        print("base: " + dados.get(0) + " ");

        print("extras: ");
        for (int i = 1; i < dados.size(); i++) {
            print(dados.get(i) + " ");
        }
    }

    /**
     * Imprime la matriz formateada
     */
    public void mostrarTablero(String[][] matriz) {
        int k = 1;
        for (int i = 0; i < Tablero.FILAS; i++) {
            for (int j = 0; j < Tablero.COLUMNAS; j++) {
                this.print(matriz[i][j]);
                if ((k >= 10)) {
                    this.print("    ");
                } else {
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
