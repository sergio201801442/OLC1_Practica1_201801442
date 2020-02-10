/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc1.practica1_201801442;

/**
 *
 * @author Sergio
 */
public class Nodo_Arbol {
    
    private Nodo_Arbol izquierda;
    private Nodo_Arbol derecha;
    private String valor;

    public Nodo_Arbol(Nodo_Arbol izquierda, Nodo_Arbol derecha, String valor) {
        this.izquierda = izquierda;
        this.derecha = derecha;
        this.valor = valor;
    }

    public Nodo_Arbol() {
    }

    public Nodo_Arbol getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo_Arbol izquierda) {
        this.izquierda = izquierda;
    }

    public Nodo_Arbol getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo_Arbol derecha) {
        this.derecha = derecha;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
}
