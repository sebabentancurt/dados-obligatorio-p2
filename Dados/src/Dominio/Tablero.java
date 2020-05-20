/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Logica.Helper;

/**
 *
 * @author Mateo
 */

public class Tablero {

    public static final int FILAS = 4;
    public static final int COLUMNAS = 5;

    private String matriz[][];

    /**
     * Construye la matriz con los valores de posicion
     * @param consola
     */
    public Tablero() {
        this.matriz = new String[FILAS][COLUMNAS];

        int k = 1;
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                matriz[i][j] = Helper.addColorToString(Integer.toString(k),"green");
                k++;
            }
        }
    }

    /**
     * Ingresa una letra en color en una posicion de la matriz
     * @param posicion
     * @param letra
     * @param color
     * @return
     */
    public Boolean ingresarLetra(int posicionIngreso, String letra, String color) {
        Boolean ingresoLetra = false;
        int posicionActual = 1;
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if ((posicionActual == posicionIngreso && posicionEstaVacia(i,j,posicionIngreso))){
                    matriz[i][j] = Helper.addColorToString(letra, color);
                    ingresoLetra = true;
                }
                posicionActual++;
            }
        }
        return ingresoLetra;
    }


    /**
     * Dada una coordenada (x,y) y una posicion de la matriz indica si es vacia o no
     * @param fila
     * @param columna
     * @param valor
     * @return
     */
    public Boolean posicionEstaVacia(int fila, int columna, int posicion) {
        String match = Helper.addColorToString(Integer.toString(posicion), "green");
        Boolean vacio = false;
        if (this.matriz[fila][columna].equals(match)) {
            vacio = true;
        }
        return vacio;
    }

    /**
     * Indica si un tablero tiene alguna posicion vacia
     * @return Boolean
     */
    public Boolean estaCompleto(){
        Boolean completo = true;
        int posicionActual = 1;
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (Boolean.TRUE.equals(posicionEstaVacia(i,j,posicionActual))){
                    completo = false;
                }
                posicionActual++;
            }
        }
        return completo;
    }

    public String[][] getMatriz(){
        return this.matriz;
    }
}
