/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Dominio.Jugador;
import Logica.Helper;
import Logica.Partida;
import Logica.Sistema;
import Dominio.Tablero;
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

    public void registroJugador(Sistema unSistema) {
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

    public void crearPartida(Sistema unSistema) {
        Jugador[] jugadoresSeleccionados = seleccionarJugadores(unSistema);
        String[] letrasSeleccionadas = seleccionarLetras();
        boolean modoTest = consultaModoTest();
        
        Partida partida = new Partida(jugadoresSeleccionados[0], jugadoresSeleccionados[1], letrasSeleccionadas[0], letrasSeleccionadas[1], modoTest, this);

    }

    public Jugador[] seleccionarJugadores(Sistema unSistema) {
        int seleccion;
        Jugador rojo;
        Jugador azul;
        Jugador[] jugadoresSeleccionados = new Jugador[2];
        Scanner in = new Scanner(System.in);

        imprimirListaJugadores(unSistema);

        println("SELECCIONE JUGADOR ROJO");
        seleccion = in.nextInt();
        rojo = unSistema.getListaJugadores().get(seleccion - 1);
        println("Jugador rojo: " + rojo);

        println("SELECCIONE JUGADOR AZUL");
        seleccion = in.nextInt();
        azul = unSistema.getListaJugadores().get(seleccion - 1);
        println("Jugador azul: " + azul);

        jugadoresSeleccionados[0] = rojo;
        jugadoresSeleccionados[1] = azul;

        return jugadoresSeleccionados;
    }

    public void imprimirListaJugadores(Sistema unSistema) {
        println("LISTA DE JUGADORES");
        if (unSistema.getListaJugadores().isEmpty()) {
            println("LISTA VACIA");
        } else {
            for (int i = 0; i < unSistema.getListaJugadores().size(); i++) {
                println((i + 1) + ". " + unSistema.getListaJugadores().get(i));
            }
        }
    }

    public String[] seleccionarLetras() {
        Scanner in = new Scanner(System.in);
        String letraRojo;
        String letraAzul;
        String[] letrasSeleccionadas = new String[2];
        
        println("Ingrese letra jugador Rojo");
        letraRojo = in.nextLine();
        
        println("Ingrese letra jugador Azul");
        letraAzul = in.nextLine();
        
        letrasSeleccionadas[0] = letraRojo;
        letrasSeleccionadas[1] = letraAzul;
        
        return letrasSeleccionadas;
    }
    
    public boolean consultaModoTest(){
        boolean modoTest = false;
        String respuesta;
        Scanner in = new Scanner (System.in);
        
        println("DESEA ACTIVAR MODO TEST? S/N");
        respuesta = in.nextLine();
        
        if (respuesta.equals("S")){
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

    public void printGreen(String value) {
        System.out.println(Helper.addColorToString(value, "green"));
    }

    public void printRed(String value) {
        System.out.println(Helper.addColorToString(value, "red"));
    }

    public void print(String value) {
        System.out.print(value);
    }

    public void println(String value) {
        System.out.println(value);
    }


}
