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
public class Lista_Siguientes {
    private String valor;
    private int numero;
    private LinkedList<Integer> siguientes; 

    public Lista_Siguientes() {
    }

    public int getNumero() {
        return numero;
    }

    public LinkedList<Integer> getSiguientes() {
        return siguientes;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setSiguientes(LinkedList<Integer> siguientes) {
        this.siguientes = siguientes;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    

    public String getValor() {
        return valor;
    }
    
}
