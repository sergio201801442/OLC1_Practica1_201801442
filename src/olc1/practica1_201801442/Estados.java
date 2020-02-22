/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc1.practica1_201801442;

import java.util.LinkedList;

/**
 *
 * @author Sergio
 */
public class Estados {
    private String nombre;
    private LinkedList<Integer> estados;
    private LinkedList<Transicion> transiciones;
    
    public Estados(String nombre, LinkedList<Integer> estados) {
        this.nombre = nombre;
        this.estados = estados;
    }

    public Estados() {
    }
    public void nombrar(int n){
        this.nombre = "S_"+ n;
    }
    public LinkedList<Transicion> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(LinkedList<Transicion> transiciones) {
        this.transiciones = transiciones;
    }
    
    public Estados(String nombre){
        this.nombre = nombre;
    }

    public LinkedList<Integer> getEstados() {
        return estados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEstados(LinkedList<Integer> estados) {
        this.estados = estados;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
