//Mateo Sapiurka - Sebastian Bentancurt
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author Mateo
 */
public class Dado {
    private int numero;
    int max = 6;
    int min = 1;
    int rango = max - min + 1;

    public Dado() {
        int random = (int) (Math.random() * rango) + min;
        this.setNumero(random);
    }

    public Dado(int unNumero) {
        this.setNumero(unNumero);
    }

    public void setNumero(int unNumero) {
        numero = unNumero;
    }

    public int getNumero() {
        return numero;
    }
}
