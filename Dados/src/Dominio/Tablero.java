/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author Mateo
 */

public class Tablero {

    static final int FILAS = 7;
    static final int COLUMNAS = 5;

    private String matriz[][];

    public Tablero() {
        this.matriz = new String[FILAS][COLUMNAS];

        int k = 0;
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                matriz[i][j] = Integer.toString(k);
                k++;
            }
        }
    }

    public void mostrar() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.println(matriz[i][j]);
            }
        }
    }

    public Boolean ingresarJugada() {
        
    }

    public Boolean espacioLibre(x,y){

    }
}
