/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Interfaz.Consola;

/**
 *
 * @author Mateo
 */

public class Tablero {

    static final int FILAS = 4;
    static final int COLUMNAS = 5;

    private String matriz[][];
    private Consola consola;

    /**
     * Construye la matriz con los valores de posicion
     * @param consola
     */
    public Tablero(Consola consola) {
        this.matriz = new String[FILAS][COLUMNAS];
        this.consola = consola;

        int k = 1;
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                matriz[i][j] = this.consola.addColor(Integer.toString(k),"green");
                k++;
            }
        }
    }

    /**
     * Imprime la matriz formateada
     */
    public void mostrar() {
        int k = 1;
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                this.consola.print(matriz[i][j]);
                if ((k >= 10)){
                    this.consola.print("    ");
                }else {
                    this.consola.print("     ");
                }
                k++;
            }
            this.consola.println("");
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
                    matriz[i][j] = this.consola.addColor(letra, color);
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
        String match = this.consola.addColor(Integer.toString(posicion), "green");
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
                if (posicionEstaVacia(i,j,posicionActual)){
                    completo = false;
                }
                posicionActual++;
            }
        }
        return completo;
    }

}
