/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import helper.Color;

/**
 *
 * @author Mateo
 */

public class Tablero {

    public static final int FILAS = 4;
    public static final int COLUMNAS = 5;

    private String[][] matriz;

    /**
     * Construye la matriz con los valores de posicion
     * 
     * @param consola
     */
    public Tablero() {
        this.matriz = new String[FILAS][COLUMNAS];

        int k = 1;
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                matriz[i][j] = Color.addColorToString(Integer.toString(k), "green");
                k++;
            }
        }
    }

    /**
     * Ingresa una letra en color en una posicion de la matriz
     * 
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
                if ((posicionActual == posicionIngreso && coordenadaPosicionEstaVacia(i, j, posicionIngreso))) {
                    matriz[i][j] = Color.addColorToString(letra, color);
                    ingresoLetra = true;
                }
                posicionActual++;
            }
        }
        return ingresoLetra;
    }

    /**
     * Devuelve la cantidad de letra en secuencias verticales, horizontales, o
     * diagonales mayores o igual a 3.
     * 
     * @param letra
     * @param color
     * @return
     */
    public int letraEnSecuencia(String letra, String color) {
        return this.secuenciaHorizontal(letra, color) + this.secuenciaVertical(letra, color) + this.secuenciaDiagonal(letra, color) + this.secuenciaDiagonalInversa(letra, color);
    }

    /**
     * Dada una coordenada (x,y) y una posicion de la matriz indica si es vacia o no
     * 
     * @param fila
     * @param columna
     * @param valor
     * @return
     */
    public Boolean coordenadaPosicionEstaVacia(int fila, int columna, int posicion) {
        String match = Color.addColorToString(Integer.toString(posicion), "green");
        Boolean vacio = false;
        if (this.matriz[fila][columna].equals(match)) {
            vacio = true;
        }
        return vacio;
    }

    /**
     * Dada una posicion de la matriz indica si es vacia o no
     * 
     * @param posicion
     * @return
     */
    public Boolean posicionEstaVacia(int posicionIngreso) {
        String match = Color.addColorToString(Integer.toString(posicionIngreso), "green");
        Boolean vacio = false;
        int posicionActual = 1;
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (posicionActual == posicionIngreso && this.matriz[i][j].equals(match)) {
                    vacio = true;
                }
                posicionActual++;
            }
        }

        return vacio;
    }

    /**
     * Indica si un tablero tiene alguna posicion vacia
     * 
     * @return Boolean
     */
    public Boolean estaCompleto() {
        Boolean completo = true;
        int posicionActual = 1;
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (Boolean.TRUE.equals(coordenadaPosicionEstaVacia(i, j, posicionActual))) {
                    completo = false;
                }
                posicionActual++;
            }
        }
        return completo;
    }

    public String[][] getMatriz() {
        return this.matriz;
    }

    private int secuenciaDiagonalInversaSuperior(String letra, String color) {
        String match = Color.addColorToString(letra, color);
        int cantidad = 0;
        int filaI = 0;
        int columnaI = 0;
        for (int i = 0; i < FILAS; i++) {
            int diagonalCantidad = 0;
            for (int j = 0; j <= i; j++) {
                filaI = i - j;
                columnaI = j;
                Boolean letraEnPosicion = this.getValue(filaI, columnaI).equals(match);

                if (letraEnPosicion) {
                    diagonalCantidad++;
                }

                if (!letraEnPosicion) {
                    if (secuenciaValida(diagonalCantidad)) {
                        cantidad += diagonalCantidad;
                    }
                    diagonalCantidad = 0;
                }

                if (letraEnPosicion && esFilaInicial(filaI) && secuenciaValida(diagonalCantidad)) {
                    cantidad += diagonalCantidad;
                    diagonalCantidad = 0;
                }

            }
        }
        return cantidad;
    }

    private int secuenciaDiagonalInversaInferior(String letra, String color) {
        String match = Color.addColorToString(letra, color);
        int cantidad = 0;
        int filaI = 0;
        int columnaI = 0;
        for (int i = 0; i < FILAS; i++) {
            int diagonalCantidad = 0;
            for (int j = 0; j < COLUMNAS - i - 1; j++) {
                columnaI = j + i + 1;
                filaI = FILAS - j - 1;

                Boolean letraEnPosicion = this.getValue(filaI, columnaI).equals(match);

                if (letraEnPosicion) {
                    diagonalCantidad++;
                }

                if (!letraEnPosicion) {
                    if (secuenciaValida(diagonalCantidad)) {
                        cantidad += diagonalCantidad;
                    }
                    diagonalCantidad = 0;
                }

                if (letraEnPosicion && esColumnaFinal(columnaI) && secuenciaValida(diagonalCantidad)) {
                    cantidad += diagonalCantidad;
                    diagonalCantidad = 0;
                }

            }
        }
        return cantidad;
    }

    private int secuenciaDiagonalSuperior(String letra, String color) {
        String match = Color.addColorToString(letra, color);
        int columnaI = this.getColumnasI();
        int filaI = 0;
        int cantidad = 0;
        while (columnaI >= 0) {
            int tmpFilaI = filaI;
            int tmpColumnaI = columnaI;
            int diagonalCantidad = 0;
            while (this.filaColumnaValida(tmpFilaI, tmpColumnaI)) {
                Boolean letraEnPosicion = this.getValue(tmpFilaI, tmpColumnaI).equals(match);

                if (letraEnPosicion) {
                    diagonalCantidad++;
                }

                if (!letraEnPosicion) {
                    if (secuenciaValida(diagonalCantidad)) {
                        cantidad += diagonalCantidad;
                    }
                    diagonalCantidad = 0;
                }

                if (letraEnPosicion && esFilaOColumnaFinal(tmpFilaI,tmpColumnaI) && secuenciaValida(diagonalCantidad)) {
                    cantidad += diagonalCantidad;
                    diagonalCantidad = 0;
                }

                tmpFilaI++;
                tmpColumnaI++;
            }
            columnaI--;
        }
        return cantidad;
    }

    private int secuenciaDiagonalInferior(String letra, String color) {
        String match = Color.addColorToString(letra, color);
        int columnaI = 0;
        int filaI = this.getFilasI();
        int cantidad = 0;
        while (filaI >= 1) {
            int tmpFilaI = filaI;
            int tmpColumnaI = columnaI;
            int diagonalCantidad = 0;
            while (this.filaColumnaValida(tmpFilaI, tmpColumnaI)) {
                Boolean letraEnPosicion = this.getValue(tmpFilaI, tmpColumnaI).equals(match);

                if (letraEnPosicion) {
                    diagonalCantidad++;
                }

                if (!letraEnPosicion) {
                    if (secuenciaValida(diagonalCantidad)) {
                        cantidad += diagonalCantidad;
                    }
                    diagonalCantidad = 0;
                }

                if (letraEnPosicion && esFilaFinal(tmpFilaI) && secuenciaValida(diagonalCantidad)) {
                    cantidad += diagonalCantidad;
                    diagonalCantidad = 0;
                }

                tmpFilaI++;
                tmpColumnaI++;
            }
            filaI--;
        }
        return cantidad;
    }

    /**
     * Devuelve la cantidad de letras por secuencias validas de todas las filas
     * 
     * @param letra
     * @param color
     * @return
     */
    private int secuenciaHorizontal(String letra, String color) {
        String match = Color.addColorToString(letra, color);
        int cantidad = 0;
        int filaCantidad = 0;
        for (int i = 0; i < FILAS; i++) {
            filaCantidad = 0;
            for (int j = 0; j < COLUMNAS; j++) {
                Boolean letraEnPosicion = this.matriz[i][j].equals(match);

                if (letraEnPosicion) {
                    filaCantidad++;
                }

                if (!letraEnPosicion) {
                    if (secuenciaValida(filaCantidad)) {
                        cantidad += filaCantidad;
                    }
                    filaCantidad = 0;
                }

                if (letraEnPosicion && esColumnaFinal(j) && secuenciaValida(filaCantidad)) {
                    cantidad += filaCantidad;
                    filaCantidad = 0;
                }

            }
        }
        return cantidad;
    }

    /**
     * Devuelve la cantidad de letras por secuencias validas de todas las filas
     * 
     * @param letra
     * @param color
     * @return
     */
    private int secuenciaVertical(String letra, String color) {
        String match = Color.addColorToString(letra, color);
        int cantidad = 0;
        int columnaCantidad = 0;
        for (int i = 0; i < COLUMNAS; i++) {
            columnaCantidad = 0;
            for (int j = 0; j < FILAS; j++) {
                Boolean letraEnPosicion = this.matriz[j][i].equals(match);

                if (letraEnPosicion) {
                    columnaCantidad++;
                }

                if (!letraEnPosicion) {
                    if (secuenciaValida(columnaCantidad)) {
                        cantidad += columnaCantidad;
                    }
                    columnaCantidad = 0;
                }

                if (letraEnPosicion && esFilaFinal(j) && secuenciaValida(columnaCantidad)) {
                    cantidad += columnaCantidad;
                    columnaCantidad = 0;
                }

            }
        }
        return cantidad;
    }

    private int secuenciaDiagonal(String letra, String color) {
        return secuenciaDiagonalSuperior(letra, color) + secuenciaDiagonalInferior(letra, color);
    }

    private int secuenciaDiagonalInversa(String letra, String color) {
        return secuenciaDiagonalInversaSuperior(letra, color) + secuenciaDiagonalInversaInferior(letra, color);
    }

    /**
     * Recibe cantidad de una secuencia y retorna si es valida o no
     * 
     * @param cantidad
     * @return
     */
    private Boolean secuenciaValida(Integer cantidad) {
        return cantidad >= 3;
    }

    private Boolean esFilaFinal(Integer fila) {
        return fila == FILAS - 1;
    }

    private Boolean esColumnaFinal(Integer columna) {
        return columna == COLUMNAS - 1;
    }

    private Boolean esFilaInicial(Integer fila) {
        return fila == 0;
    }

    private Boolean esColumnaInicial(Integer columna) {
        return columna == 0;
    }

    private Boolean esFilaOColumnaFinal(Integer fila, Integer columna) {
        return esFilaFinal(fila) || esColumnaFinal(columna);
    }

    private Boolean esFilaOColumnaInicial(Integer fila, Integer columna) {
        return esFilaInicial(fila) || esColumnaInicial(columna);
    }

    private boolean filaColumnaValida(int filaI, int columnaI) {
        Boolean valido = false;
        if (filaI <= this.getFilasI() && columnaI <= this.getColumnasI()) {
            valido = true;
        }
        return valido;
    }

    /**
     * Devuelve el valor
     * 
     * @param fila
     * @param columna
     * @return
     */
    private String getValue(int filaI, int columnaI) {
        return this.matriz[filaI][columnaI];
    }

    private int getColumnasI() {
        return COLUMNAS - 1;
    }

    private int getFilasI() {
        return FILAS - 1;
    }
}
