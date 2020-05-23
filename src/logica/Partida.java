
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import dominio.Dado;
import dominio.Jugador;
import dominio.Tablero;
import interfaz.Consola;
import java.util.ArrayList;

/**
 *
 * @author Mateo
 */
public class Partida {

    private Jugador jugadorRojo;
    private Jugador jugadorAzul;
    private String letraRojo;
    private String letraAzul;
    private boolean modoTest;
    private Tablero tablero;

    public Partida(Jugador unJugadorRojo, Jugador unJugadorAzul, String unaLetraRojo, String unaLetraAzul, boolean modoTest) {
        this.setJugadorRojo(unJugadorRojo);
        this.setJugadorAzul(unJugadorAzul);
        this.setLetraRojo(unaLetraRojo);
        this.setLetraAzul(unaLetraAzul);
        this.setModoTest(modoTest);
        this.setTablero();

    }

    //Getters
    public Jugador getJugadorRojo() {
        return jugadorRojo;
    }

    public Jugador getJugadorAzul() {
        return jugadorAzul;
    }

    public String getLetraRojo() {
        return letraRojo;
    }

    public String getLetraAzul() {
        return letraAzul;
    }

    public boolean getModoTest() {
        return modoTest;
    }

    public Tablero getTablero() {
        return tablero;
    }

    //Setters
    public void setJugadorRojo(Jugador unJugador) {
        this.jugadorRojo = unJugador;
    }

    public void setJugadorAzul(Jugador unJugador) {
        this.jugadorAzul = unJugador;
    }

    public void setLetraRojo(String unaLetra) {
        this.letraRojo = unaLetra;
    }

    public void setLetraAzul(String unaLetra) {
        this.letraAzul = unaLetra;
    }

    public void setModoTest(boolean modoTest) {
        this.modoTest = modoTest;
    }

    public void setTablero() {
        this.tablero = new Tablero();
    }

    public void jugar(Consola unaConsola) {
        boolean abandono = false;
        while (!this.getTablero().estaCompleto() || abandono) {

            unaConsola.mostrarTablero(tablero.getMatriz());

            solicitarRojo(unaConsola);
            //verificarRojo() 
            //aplicarRojo() 
            //solicitarAzul() 
            //verificarAzul() 
            //aplicarAzul() 
        }
    }

    public void solicitarRojo(Consola unaConsola) {
        ArrayList<Integer> dados = tirarDados(unaConsola);

        unaConsola.mostrarDados(dados);

        String respuesta = unaConsola.leerString("Ingrese jugada");
        String[] respuestaArray = respuesta.split(" ");
        ArrayList<Integer> jugada = new ArrayList<Integer>();
        for (String num : respuestaArray) {
            jugada.add(Integer.parseInt(num));
        }
        
        if(verificarRojo(jugada, dados)){
            //aplicarJugada();
        }else{
            solicitarRojo(unaConsola);
        }
    }

    public ArrayList<Integer> tirarDados(Consola unaConsola) {
        Dado[] dados = new Dado[5];
        ArrayList<Integer> numDados = new ArrayList();

        if (getModoTest()) {
            for (int i = 0; i < dados.length; i++) {
                dados[i] = new Dado(unaConsola.leerOpcion("Ingrese numero de dado", 1, 6));
            }
        } else {
            for (int i = 0; i < dados.length; i++) {
                dados[i] = new Dado();
            }
        }
        for (int i = 0; i < dados.length; i++) {
            numDados.add(dados[i].getNumero());
        }

        return numDados;
    }

    public boolean verificarRojo(ArrayList<Integer> jugada, ArrayList<Integer> dados) {
        if (jugada.size() == 1 && jugada.contains(0)) {
            return tablero.posicionEstaVacia(dados[0]);
        } else {
            int total = dados.get(0);
            for(Integer num : jugada){
                if(!dados.contains(num)){
                    return false;
                }
                total += num;
            }
            
            return tablero.posicionEstaVacia(total)
        }
        
    }

}
