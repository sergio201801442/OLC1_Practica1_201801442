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
public class Nodo_Arbol {
    
    private Nodo_Arbol izquierda;
    private Nodo_Arbol derecha;
    private String valor;
    private Token value;
    private boolean esConjunto;
    private int numero_Hoja;
    private String anulable;
    private LinkedList<Integer> primeros = new LinkedList<Integer>();
    private LinkedList<Integer> ultimos = new LinkedList<Integer>();
    private LinkedList<Integer> siguientes = new LinkedList<Integer>();
    
    public Nodo_Arbol(Nodo_Arbol izquierda, Nodo_Arbol derecha, String valor) {
        this.izquierda = izquierda;
        this.derecha = derecha;
        this.valor = valor;
    }

    public void setUltimos(LinkedList<Integer> ultimos) {
        this.ultimos = ultimos;
    }

    public LinkedList<Integer> getUltimos() {
        return ultimos;
    }

    public void setValue(Token value) {
        this.value = value;
    }

    public int getNumero_Hoja() {
        return numero_Hoja;
    }

    public void setNumero_Hoja(int numero_Hoja) {
        this.numero_Hoja = numero_Hoja;
    }

    
    public Token getValue() {
        return value;
    }

    
    public Nodo_Arbol() {
        int numero_Hoja=-1;
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

    public void setEsConjunto(boolean esConjunto) {
        this.esConjunto = esConjunto;
    }

    public String getAnulable() {
        return anulable;
    }

    public void setAnulable(String anulable) {
        this.anulable = anulable;
    }

    public LinkedList<Integer> getPrimeros() {
        return primeros;
    }

    public void setPrimeros(LinkedList<Integer> primeros) {
        this.primeros = primeros;
    }

    public void setSiguientes(LinkedList<Integer> siguientes) {
        this.siguientes = siguientes;
    }

    public LinkedList<Integer> getSiguientes() {
        return siguientes;
    }
 
    
    public String getCodigoNodo(){
        String codigo ="";
        if(this.izquierda==null && this.derecha==null){
            if(this.getValor().contains("\"")){
            codigo += this.hashCode()+"[label="+this.getValor()+"+\"["+this.getNumero_Hoja()+","+this.getAnulable()+"]\"];\n";
            }
            else{
            codigo += this.hashCode()+"[label="+"\""+this.getValor()+"["+this.getNumero_Hoja()+","+this.getAnulable()+"]\""+"];\n";
            }
        }
        else{
            if(this.getValor().contains("\"")){
            codigo += this.hashCode()+"[label="+this.getValor()+","+this.getAnulable()+"];\n";
            }
            else{
            codigo += this.hashCode()+"[label="+"\""+this.getValor()+","+this.getAnulable()+"\""+"];\n";
            }
        }
        if(this.izquierda!=null){
            codigo += this.izquierda.getCodigoNodo()+this.hashCode()+":C0->"+izquierda.hashCode()+"\n";
        }
        if(this.derecha!=null){
            codigo += this.derecha.getCodigoNodo()+this.hashCode()+":C1->"+derecha.hashCode()+"\n";
        }
        return codigo;
    }
    
    
    
    
    
}
