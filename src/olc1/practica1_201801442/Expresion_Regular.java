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
public class Expresion_Regular {
    private LinkedList<Token> listaTokens = new LinkedList<Token>();
    private String nombre;
    
    private Arbol_Generador arbol = new Arbol_Generador();

    public Arbol_Generador getArbol() {
        return arbol;
    }

    public void setArbol(Arbol_Generador arbol) {
        this.arbol = arbol;
    }
    

    public Expresion_Regular(String nombre) {
        this.nombre = nombre;
    }

    public void setListaTokens(LinkedList<Token> listaTokens) {
        this.listaTokens = listaTokens;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LinkedList<Token> getListaTokens() {
        return listaTokens;
    }

    public String getNombre() {
        return nombre;
    }
    
    
    
    
    
}
