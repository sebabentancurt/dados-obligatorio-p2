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
    public Boolean ingresarValor(int posicion, String letra, String color) {
        Boolean ingresoValor = false;
        int k = 1;
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if ((k == posicion && posicionVacia(i,j,k))){
                    matriz[i][j] = this.consola.addColor(letra, color);
                    ingresoValor = true;
                }
                k++;
            }
        }
        return ingresoValor;
    }


    /**
     * Dada una coordenada (x,y) y un valor de la matriz indica si es vacia o no
     * @param fila
     * @param columna
     * @param valor
     * @return
     */
    public Boolean posicionVacia(int fila, int columna, int valor) {
        String match = this.consola.addColor(Integer.toString(valor), "green");
        Boolean vacio = true;
        if (this.matriz[fila][columna].equals(match)) {
            vacio = false;
        }
        return vacio;
    }

}
