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
public class Jugador implements Comparable<Jugador>{

    private String nombre;
    private String alias;
    private String letraParaJugar;
    private int edad;
    private int partidasGanadas;
    private int partidasJugadas;

    /**
     * Constructor
     *
     * @param nombre
     * @param edad
     * @param alias
     */
    public Jugador(String nombre, int edad, String alias) {
        this.setNombre(nombre);
        this.setEdad(edad);
        this.setAlias(alias);
        this.setPartidasJugadas(0);
        this.setPartidasGanadas(0);
    }

    // ---------------- Getters ---------------- 
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getAlias() {
        return alias;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }
    
    public String getLetraParaJugar(){
        return letraParaJugar;
    }

    // ---------------- Setters ----------------- 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setPartidasJugadas(int cantPartidasJugadas) {
        partidasJugadas = cantPartidasJugadas;
    }

    public void setPartidasGanadas(int cantPartidasGanadas) {
        partidasGanadas = cantPartidasGanadas;
    }
    
    public void setLetraParaJugar(String unaLetra){
        letraParaJugar = unaLetra;
    }

    @Override

    public String toString() {
        return this.getAlias() + " - Partidas ganadas: " + this.getPartidasGanadas() + ". Partidas jugadas: " + this.getPartidasJugadas();
    }
    
    public int compareTo(Jugador unJugador){
        return (unJugador.getPartidasGanadas() - this.getPartidasGanadas());
    }

    public boolean equals(Object obj) {
        return this.getAlias().equals(((Jugador) obj).getAlias());
    }

}
