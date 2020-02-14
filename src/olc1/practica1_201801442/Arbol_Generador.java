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
    public Nodo_Arbol raiz;
    private int contador = 0; // quite el static
    private int numerador=1;
    private LinkedList<Token> tokens;
    
    public Arbol_Generador() {
        this.raiz = null;
    }

    public void setTokens(LinkedList<Token> tokens) {
        this.tokens = tokens;
    }

    public LinkedList<Token> getTokens() {
        return tokens;
    }
    
    
    Nodo_Arbol metodoPreorden(){
        System.out.println("Me llaman preorden "+contador);
        //String valor2 = Lexico.salida.get(contador).getValor();
        //System.out.println("Tamanio de mi lista de tokens: "+tokens.size() );
        //String valor = tokens.get(contador).getValor();
        //System.out.println(valor);
        if(contador >= tokens.size()){
            return null;
        }
        else if(tokens.get(contador).getTipo_Token()==Token.Tipo.CONCATENAR){
            System.out.println("Concatenar");
            Nodo_Arbol retorno = new Nodo_Arbol();    
            retorno.setValor(tokens.get(contador).getValor());
            retorno.setValue(tokens.get(contador));
            contador++;
            retorno.setIzquierda(metodoPreorden());
            retorno.setDerecha(metodoPreorden());
            return retorno;
        }
        else if(tokens.get(contador).getTipo_Token()==Token.Tipo.DISYUNCION){
            System.out.println("Disyuncion");
            Nodo_Arbol retorno = new Nodo_Arbol();    
            retorno.setValor(tokens.get(contador).getValor());
            retorno.setValue(tokens.get(contador));
            contador++;
            retorno.setIzquierda(metodoPreorden());
            retorno.setDerecha(metodoPreorden());
            return retorno;
        }
        else if(tokens.get(contador).getTipo_Token()==Token.Tipo.MAS_OBLIGATORIO){
            System.out.println("Mas Obligatorio");
            Nodo_Arbol retorno = new Nodo_Arbol();    
            retorno.setValor(tokens.get(contador).getValor());
            retorno.setValue(tokens.get(contador));
            contador++;
            retorno.setIzquierda(metodoPreorden());
            //retorno.setDerecha(metodoPreorden());
            return retorno;
        }
        else if(tokens.get(contador).getTipo_Token()==Token.Tipo.KLEENE){
            System.out.println("Kleene");
            Nodo_Arbol retorno = new Nodo_Arbol();    
            retorno.setValor(tokens.get(contador).getValor());
            retorno.setValue(tokens.get(contador));
            contador++;
            retorno.setIzquierda(metodoPreorden());
            //retorno.setDerecha(metodoPreorden());
            return retorno;
        }
        else if(tokens.get(contador).getTipo_Token()==Token.Tipo.INTERRO){
            System.out.println("Kleene");
            Nodo_Arbol retorno = new Nodo_Arbol();    
            retorno.setValor(tokens.get(contador).getValor());
            retorno.setValue(tokens.get(contador));
            contador++;
            retorno.setIzquierda(metodoPreorden());
            //retorno.setDerecha(metodoPreorden());
            return retorno;
        }
        else if(tokens.get(contador).getTipo_Token()==Token.Tipo.CADENA){
            System.out.println("Cadena");
            Nodo_Arbol retorno = new Nodo_Arbol();
            retorno.setValor( tokens.get(contador).getValor());
            retorno.setValue(tokens.get(contador));
            retorno.setEsConjunto(false);
            contador++;
            return retorno;
        }
        else if(tokens.get(contador).getTipo_Token()==Token.Tipo.LLAVE_IZQ){
            System.out.println("ID");
            Nodo_Arbol retorno = new Nodo_Arbol();
            retorno.setValor( tokens.get(contador+1).getValor());
            retorno.setValue(tokens.get(contador));
            retorno.setEsConjunto(true);
            contador+=3;
            return retorno;
        }
        
        return null;
    }
    
    public void graficarArbol(){
        String grafica = "digraph grafica{\n rankdir=TB;\n node [shape=circle, style=filled, fillcolor=cornsilk1];\n";
        grafica += this.raiz.getCodigoNodo();
        grafica += "}";
        System.out.println(grafica);
    }
    
    private void actualizar_Estado(Nodo_Arbol raiz){
        if(raiz.getIzquierda()!=null){
            actualizar_Estado(raiz.getIzquierda());
        }
        if(raiz.getValue().getTipo_Token()==Token.Tipo.CONCATENAR){
            
        }
        else if(raiz.getValue().getTipo_Token()==Token.Tipo.MAS_OBLIGATORIO){
            
        }
        else if(raiz.getValue().getTipo_Token()==Token.Tipo.INTERRO){
            
        }
        else if(raiz.getValue().getTipo_Token()==Token.Tipo.DISYUNCION){
            
        }
        else if(raiz.getValue().getTipo_Token()==Token.Tipo.KLEENE){
            
        }
        else{
            raiz.setNumero_Hoja(numerador);
            numerador++;
        }
        if(raiz.getDerecha()!=null){
            actualizar_Estado(raiz.getDerecha());
        }
        
    }
    
    private void inOrden(Nodo_Arbol raiz){
        if(raiz.getIzquierda()!=null){
            inOrden(raiz.getIzquierda());
        }
        System.out.println(raiz.getValor()+" y el numero de la hoja es "+raiz.getNumero_Hoja());
        if(raiz.getDerecha()!=null){
            inOrden(raiz.getDerecha());
        }
    }
    public void ejecutar_Numeracion(){
        this.actualizar_Estado(this.raiz);
        this.inOrden(this.raiz);
    }
}
