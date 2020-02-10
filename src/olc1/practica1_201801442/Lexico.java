/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc1.practica1_201801442;

import java.util.LinkedList;
import javax.swing.JOptionPane;


/**
 *
 * @author Sergio
 */
public class Lexico {
    
    public LinkedList<Token> salida = new LinkedList<Token>();
    
    private int estado;
    private String lexema;
    private int linea = 1;
    private int columna = 0;
    
    public void analizarLexico(String cadena_Entrada){
        estado =0;
        lexema = "";
        for (int i = 0; i < cadena_Entrada.length(); i++) {
            columna ++;
            char letra = cadena_Entrada.charAt(i);
            int ascii = letra;
            
            switch(estado){
                case 0:
                    //comentario una linea
                    if(letra == '\n'){
                        columna =0;
                        linea++;
                    }
                    else if(ascii==47){
                        lexema += "/";
                        estado=1;
                    }
                    else if(ascii==60){
                        lexema+=letra;
                        estado=4;
                    }
                    else if(ascii==123){
                        lexema += letra;
                        agregarToken(Token.Tipo.LLAVE_IZQ);
                    }
                    else if(ascii==125){
                        lexema+=letra;
                        agregarToken(Token.Tipo.LLAVE_DER);
                    }
                    else if(((ascii >= 65) && (ascii <= 90)) || ((ascii >= 97) && (ascii <= 122))){
                        lexema+=letra;
                        estado=7;
                    }
                    else if((ascii >= 48) && (ascii <= 57)){
                        lexema+=letra;
                        estado=8;
                    }
                    else if(ascii==45){
                        lexema+=letra;
                        estado=9;
                    }
                    break;
                    
                
                case 1:
                    if(ascii==47){
                        lexema+="/";
                        estado = 2;
                    }
                    break;
                case 2:
                    if(letra!='\n'){
                        lexema += letra;
                    }
                    else{
                        agregarToken(Token.Tipo.COMENTARIO_UNA_LINEA);
                    }
                    break;
                case 4:
                    if(ascii==33){
                        lexema+=letra;
                        estado = 5;
                    }
                    break;
                case 5:
                    if(ascii!=33){
                        lexema+=letra;
                    }
                    else{
                        lexema+=letra;
                        estado=6;
                    }
                    break;
                case 6:
                    if(ascii==62){
                        lexema+=letra;
                        agregarToken(Token.Tipo.COMENTARIO_INICIO_FIN);
                    }
                    break;
                case 7:
                    if(((ascii >= 65) && (ascii <= 90)) || ((ascii >= 97) && (ascii <= 122))){
                        lexema+=letra;
                    }
                    else if((ascii >= 48) && (ascii <= 57)){
                        lexema+=letra;
                    }
                    else if(ascii==95){
                        lexema+=letra;
                    }
                    else{
                        if(lexema.equals("CONJ")){
                            agregarToken(Token.Tipo.RESERVADA_CONJ);
                        }
                        else{
                            agregarToken(Token.Tipo.ID);
                            
                        }
                        i--;
                    }
                    break;
                case 8:
                    if((ascii >= 48) && (ascii <= 57)){
                        lexema+=letra;
                    }
                    else{
                        agregarToken(Token.Tipo.ENTERO);
                        i--;
                    }
                    break;
                case 9:
                    if(ascii==62){
                        lexema+=letra;
                        agregarToken(Token.Tipo.FLECHA);
                    }
                    else{
                        ///comprobar que si falle el error -x en lugar de ->
                        agregarToken(Token.Tipo.DESCONOCIDO);
                        i--;
                    }
                    break;
            }
                
                    
        }
    }
    
    public void agregarToken(Token.Tipo tipo){
        salida.addLast(new Token(lexema,linea,columna,tipo));
        lexema="";
        estado =0;
    }
    
    public void mostrarTokens(){
        String alfa="";
        for(Token t : salida){
            alfa += " Lexema: "+t.getValor()+", Linea: "+t.getFila()+" , Columna: "+t.getColumna()+"\n" ;
        }
        JOptionPane.showMessageDialog(null, alfa + salida.size());
        
    }
}
