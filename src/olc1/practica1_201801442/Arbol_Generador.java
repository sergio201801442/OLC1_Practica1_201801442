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
public class Arbol_Generador {
    private Nodo_Arbol raiz;
    public static int contador = 0;
    
    
    public Arbol_Generador() {
        this.raiz = null;
    }
    
    Nodo_Arbol metodoPreorden(){
        
        String valor = Lexico.salida.get(contador).getValor();
        if(valor.equals(".")){
            Nodo_Arbol retorno = new Nodo_Arbol();    
            retorno.setValor(valor);
            contador++;
            retorno.setIzquierda(metodoPreorden());
            retorno.setDerecha(metodoPreorden());
            return retorno;
        }
        if(Lexico.salida.get(contador).getTipo_Token()==Token.Tipo.ID){
            Nodo_Arbol retorno = new Nodo_Arbol();
            retorno.setValor( Lexico.salida.get(contador).getValor());
            contador++;
            return retorno;
        }
        return null;
    }
    
}
