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
    
    public static LinkedList<Token> salida = new LinkedList<Token>();
    public static LinkedList<Conjunto> conjuntos = new LinkedList<Conjunto>();
    public static LinkedList<Expresion_Regular> expresiones = new LinkedList<Expresion_Regular>();
    
    private int estado;
    private String lexema;
    private int linea = 1;
    private int columna = 0;
    
    public void obtenerToken(String cadena_Entrada){
        estado =0;
        lexema = "";
        for (int i = 0; i < cadena_Entrada.length(); i++) {
            columna ++;
            char letra = cadena_Entrada.charAt(i);
            int ascii = letra;
            
            switch(estado){
                case 0:
                    if(ascii==34){
                        lexema +=letra;
                        estado =1;
                    }
                    else if(ascii==58){
                        lexema +=letra;  
                        estado =6;
                    }
                    else if(ascii==59){
                        lexema +=letra;  
                        estado =7;
                    }
                    else if(ascii==123){
                        lexema +=letra;  
                        estado =8;
                    }
                    else if(ascii==125){
                        lexema +=letra;  
                        estado =9;
                    }
                    else if(ascii==124){ // pipe
                        lexema +=letra;  
                        estado =10;
                    }
                    else if(ascii==42){ // kleene
                        lexema +=letra;  
                        estado = 11;
                    }
                    else if(ascii==43){ // mas obligatorio
                        lexema +=letra;  
                        estado = 12;
                    }
                    else if(ascii==45){ // -> por flecha o inicio 
                        lexema +=letra;  
                        estado = 13;
                    }
                    else if(ascii==47){ // inicio comentraio \n
                        lexema += letra;
                        estado =14;           
                    }
                    else if(ascii==60){ // comentario multi
                        lexema += letra;
                        estado = 16;
                    }
                    else if(ascii==63){ // interrogacion
                        lexema += letra;
                        estado = 19;
                    }
                    else if(ascii==37){ // porcentaje
                        lexema +=letra;
                        estado =20;
                    }
                    else if(ascii==46){ // concatenar
                        lexema +=letra;
                        estado =34;
                    }
                    else if(( (ascii >= 33) && (ascii <= 47)) || ((ascii >= 58) && (ascii <= 64)) || ((ascii >= 91) && (ascii <= 96)) || ((ascii >= 123) && (ascii <= 125))){
                        lexema +=letra;
                        estado =24;
                    }
                    else if(( (ascii >= 65) && (ascii <= 90)) || ((ascii >= 97) && (ascii <= 122)) ){ // analizando posibles letras 
                        lexema += letra;
                        estado =25;
                    }
                    else if(( (ascii >= 48) && (ascii <= 57)) ) { //analizando posibles numeros
                        lexema+=letra;
                        estado = 30;
                    }
                    break;
                case 1:
                    if(ascii==34){
                        lexema+=letra;
                        agregarToken(Token.Tipo.CADENA);
                    }
                    else if(ascii==126){
                        lexema+=letra;
                        estado= 2;
                    }
                    else if(ascii==44){
                        lexema += letra;
                        estado =3;
                    }
                    else{
                        lexema +=letra;
                        estado =4;
                    }
                    break;
                case 2:
                    if(( (ascii >= 33) && (ascii <= 47)) || ((ascii >= 58) && (ascii <= 64)) || ((ascii >= 91) && (ascii <= 96)) || ((ascii >= 123) && (ascii <= 125))){
                        lexema+=letra;
                        agregarToken(Token.Tipo.SIMBOLO);
                    }
                    else{
                        lexema +=letra;
                        agregarToken(Token.Tipo.DESCONOCIDO);
                        i--;
                    }
                    break;
                case 3:
                    if(( (ascii >= 33) && (ascii <= 47)) || ((ascii >= 58) && (ascii <= 64)) || ((ascii >= 91) && (ascii <= 96)) || ((ascii >= 123) && (ascii <= 125))){
                        lexema+=letra;
                        estado=5;
                    }
                    else{
                        i--; 
                        agregarToken(Token.Tipo.DESCONOCIDO);
                    }
                    break;
                case 4: // cadena
                    if(ascii!=34){
                        lexema+=letra;
                    }
                    else{
                        lexema+=letra;
                        agregarToken(Token.Tipo.CADENA);
                    }
                    break;
                case 5:
                    if(ascii==44){
                        lexema+=letra;
                        estado=3;
                    }
                    else{
                        i--;
                        agregarToken(Token.Tipo.SIMBOLO);
                    }
                    break;
                case 6:
                    if(ascii==126){
                        lexema+=letra;
                        estado = 2;
                    }
                    else if(ascii==44){
                        lexema+=letra;
                        estado=3;
                    }
                    else {
                        i--;
                        agregarToken(Token.Tipo.DOS_PUNTOS);
                    }
                    break;
                case 7:
                    if(ascii==126){
                        lexema+=letra;
                        estado = 2;
                    }
                    else if(ascii==44){
                        lexema+=letra;
                        estado=3;
                    }
                    else {
                        i--;
                        agregarToken(Token.Tipo.PUNTO_Y_COMA);
                    }
                    break;
                case 8:
                    if(ascii==126){
                        lexema+=letra;
                        estado = 2;
                    }
                    else if(ascii==44){
                        lexema+=letra;
                        estado=3;
                    }
                    else {
                        i--;
                        agregarToken(Token.Tipo.LLAVE_IZQ);
                    }
                    break;
                case 9:
                    if(ascii==126){
                        lexema+=letra;
                        estado = 2;
                    }
                    else if(ascii==44){
                        lexema+=letra;
                        estado=3;
                    }
                    else {
                        i--;
                        agregarToken(Token.Tipo.LLAVE_DER);
                    }
                    break;
                case 10:
                    if(ascii==126){
                        lexema+=letra;
                        estado = 2;
                    }
                    else if(ascii==44){
                        lexema+=letra;
                        estado=3;
                    }
                    else {
                        i--;
                        agregarToken(Token.Tipo.DISYUNCION);
                    }
                    break;
                case 11:
                    if(ascii==126){
                        lexema+=letra;
                        estado = 2;
                    }
                    else if(ascii==44){
                        lexema+=letra;
                        estado=3;
                    }
                    else {
                        i--;
                        agregarToken(Token.Tipo.KLEENE);
                    }
                    break;
                case 12:
                    if(ascii==126){
                        lexema+=letra;
                        estado = 2;
                    }
                    else if(ascii==44){
                        lexema+=letra;
                        estado=3;
                    }
                    else {
                        i--;
                        agregarToken(Token.Tipo.MAS_OBLIGATORIO);
                    }
                    break;
                case 13:
                    if(ascii==126){
                        lexema+=letra;
                        estado = 2;
                    }
                    else if(ascii==44){
                        lexema+=letra;
                        estado=3;
                    }
                    else if(ascii==62){
                        lexema += letra;
                        agregarToken(Token.Tipo.FLECHA);
                        
                    }
                    else{
                        i--;
                        agregarToken(Token.Tipo.DESCONOCIDO);
                    }
                    break;  
                case 14:
                    if(ascii==126){
                        lexema+=letra;
                        estado = 2;
                    }
                    else if(ascii==44){
                        lexema+=letra;
                        estado=3;
                    }
                    else if(ascii==47){
                        lexema += letra;
                        estado = 15;
                    }
                    else{
                        i--;
                        agregarToken(Token.Tipo.DESCONOCIDO);
                    }
                    break;
                case 15:
                    if(letra=='\n'){
                        agregarToken(Token.Tipo.COMENTARIO_UNA_LINEA);
                    }else{
                        lexema+=letra;
                    }
                    break;
                case 16:
                    if(ascii==33){
                        lexema+=letra;
                        estado = 17;
                    }
                    else if(ascii==126){
                        lexema+=letra;
                        estado = 2;
                    }
                    else if(ascii==44){
                        lexema+=letra;
                        estado=3;
                    }
                    else{
                        i--;
                        agregarToken(Token.Tipo.DESCONOCIDO);
                    }
                    break; 
                case 17:
                    if(ascii!=33){
                        lexema+=letra;
                    }
                    else{
                        lexema+=letra;
                        estado=18;
                    }
                    break;
                case 18:
                    if(ascii==62){
                        lexema+=letra;
                        agregarToken(Token.Tipo.COMENTARIO_INICIO_FIN);
                    }
                    break;   
                case 19:
                    if(ascii==126){
                        lexema+=letra;
                        estado = 2;
                    }
                    else if(ascii==44){
                        lexema+=letra;
                        estado=3;
                    }
                    else {
                        i--;
                        agregarToken(Token.Tipo.INTERRO);
                    }
                    break; 
                case 20:
                    if(ascii==37){
                        lexema +=letra;
                        estado=21;
                    }
                    else if(ascii==126){
                        lexema+=letra;
                        estado = 2;
                    }
                    else if(ascii==44){
                        lexema+=letra;
                        estado=3;
                    }
                    else{
                        i--;
                        agregarToken(Token.Tipo.DESCONOCIDO);
                    }
                    break;
                case 21:
                    if(letra=='\n'){
                        lexema+=letra;
                        estado=22;
                    }
                    break;
                case 22:
                    if(ascii==37){
                        lexema +=letra;
                        estado=23;
                    }
                    break;
                case 23:
                    if(ascii==37){
                        lexema+=letra;
                        agregarToken(Token.Tipo.PORCENTAJE);
                    }
                    break;
                case 24:
                    if(ascii==126){
                        lexema+=letra;
                        estado = 2;
                    }
                    else if(ascii==44){
                        lexema+=letra;
                        estado=3;
                    }
                    else{
                        i--;
                        agregarToken(Token.Tipo.DESCONOCIDO);
                    }
                    break; 
                case 25:
                    if(ascii==126){
                        lexema+=letra;
                        estado = 26;
                    }
                    else if(ascii==44){
                        lexema+=letra;
                        estado=27;
                    }
                    else if(((ascii >= 65) && (ascii <= 90)) || ((ascii >= 97) && (ascii <= 122)) || ((ascii >= 48) && (ascii <= 57)) || ascii==95 ){
                        lexema += letra;
                    }
                    else{
                        i--;
                        if(lexema.equals("CONJ")){
                            agregarToken(Token.Tipo.RESERVADA_CONJ);
                        }
                        else{
                            agregarToken(Token.Tipo.ID);
                        }
                    }
                    break; 
                case 26:   
                    if(( (ascii >= 65) && (ascii <= 90)) || ((ascii >= 97) && (ascii <= 122)) ){
                        lexema+=letra;
                        agregarToken(Token.Tipo.SIMBOLO);
                    }
                    else{
                        lexema +=letra;
                        agregarToken(Token.Tipo.DESCONOCIDO);
                        i--;
                    }
                    break;
                case 27:
                    if(( (ascii >= 65) && (ascii <= 90)) || ((ascii >= 97) && (ascii <= 122))){
                        lexema+=letra;
                        estado=28;
                    }
                    else{
                        i--; 
                        agregarToken(Token.Tipo.DESCONOCIDO);
                    }
                    break;
                case 28:
                    if(ascii==44){
                        lexema+=letra;
                        estado=27;
                    }
                    else{
                        i--;
                        agregarToken(Token.Tipo.SIMBOLO);
                    }
                    break;
                case 29:
                    if(((ascii >= 65) && (ascii <= 90)) || ((ascii >= 97) && (ascii <= 122)) || ((ascii >= 48) && (ascii <= 57)) || ascii==95 ){
                        lexema +=letra;
                    }
                    else{
                        i--;
                        if(lexema.equals("CONJ")){
                            agregarToken(Token.Tipo.RESERVADA_CONJ);
                        }
                    }
                    break;
                case 30:
                    if(( (ascii >= 48) && (ascii <= 57))){
                        lexema +=letra;
                    }
                    else if(ascii==126){
                        lexema +=letra;
                        estado =31;
                    }
                    else if(ascii==44){
                        lexema +=letra;
                        estado =32;
                    }
                    break;
                case 31:
                    if(( (ascii >= 48) && (ascii <= 57))){
                        lexema+=letra;
                    }
                    else{
                        agregarToken(Token.Tipo.SIMBOLO);
                        i--;
                    }
                    break;
                case 32:
                    if(( (ascii >= 48) && (ascii <= 57))){
                        lexema+=letra;
                    }
                    else{
                        i--;
                        estado = 33;
                    }
                    break;
                case 33:
                    if(ascii==44){
                        lexema+=letra;
                        estado=32;
                    }
                    else{
                        i--;
                        agregarToken(Token.Tipo.SIMBOLO);
                    }
                    break;   
                case 34:
                    if(ascii==126){
                        lexema+=letra;
                        estado = 2;
                    }
                    else if(ascii==44){
                        lexema+=letra;
                        estado=3;
                    }
                    else {
                        i--;
                        agregarToken(Token.Tipo.CONCATENAR);
                    }
                    break;
            }
        }
    }
    
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
                    else if(ascii==47){ //comentario linea
                        lexema += "/";
                        estado=1;
                    }
                    else if(ascii==60){ // comentario inicio fin
                        lexema+=letra;
                        estado=4;
                    }
                    else if(ascii==123){ // lave izquierda
                        lexema += letra;
                        agregarToken(Token.Tipo.LLAVE_IZQ);
                    }
                    else if(ascii==125){ // lave derecha 
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
                    else if(ascii==59){ // punto y coma
                        lexema+=letra;
                        agregarToken(Token.Tipo.PUNTO_Y_COMA);
                    }
                    else if(ascii==58){ //dos puntos
                        lexema+=letra;
                        agregarToken(Token.Tipo.DOS_PUNTOS);
                    }
                    else if(ascii==34){  // comilla doble
                        lexema+=letra;
                        estado=10;
                    }
                    else if(ascii==46){ // punto
                        lexema+=letra;
                        agregarToken(Token.Tipo.CONCATENAR);
                    }
                    else if(ascii==124){ // | pipe 
                        lexema+=letra;
                        agregarToken(Token.Tipo.DISYUNCION);
                    }
                    else if(ascii==63){ 
                        lexema+=letra;
                        agregarToken(Token.Tipo.INTERRO);
                    }
                    else if(ascii== 42){ // cerradura de kleene
                        lexema+=letra;
                        agregarToken(Token.Tipo.KLEENE);
                    }
                    else if(ascii==43){ // mas
                        lexema += letra;
                        agregarToken(Token.Tipo.MAS_OBLIGATORIO);
                    }
                    else if(ascii==126){ // ~
                        lexema +=letra;
                        agregarToken(Token.Tipo.INTERVALO_VIRGUILILLA);
                    }
                    else if(ascii==37){
                        lexema+=letra;
                        estado = 11;
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
                    break;
                case 10:
                    if(ascii==34){
                        lexema+=letra;                      
                        agregarToken(Token.Tipo.CADENA);
                    }
                    else if(ascii==126){
                        lexema+=letra;
                        agregarToken(Token.Tipo.SIMBOLO);
                    }
                    else if(ascii==44){
                        lexema+=letra;
                        agregarToken(Token.Tipo.SIMBOLO);
                    }
                    else{
                        lexema+=letra;
                    }
                case 11:
                    if(ascii==37){
                        lexema +=letra;
                        estado=12;
                    }
                    break;
                case 12:
                    if(letra=='\n'){
                        lexema+=letra;
                        estado=13;
                    }
                    break;
                case 13:
                    if(ascii==37){
                        lexema +=letra;
                        estado=14;
                    }
                    break;
                case 14:
                    if(ascii==37){
                        lexema+=letra;
                        agregarToken(Token.Tipo.PORCENTAJE);
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
            alfa += " Lexema: "+t.getValor()+" Tipo: "+t.getTipoString()+"\n" ;
        }
        JOptionPane.showMessageDialog(null, alfa + salida.size());
    }
    
    //ya no uso este
    public void limpiarTokens(){
        for (int i = 0; i < salida.size(); i++){
            if(salida.get(i).getTipo_Token()==Token.Tipo.RESERVADA_CONJ){
                Conjunto aux = new Conjunto(salida.get(i+2).getValor());
                if(salida.get(i+4).getTipo_Token()==Token.Tipo.ENTERO){
                    String aux_Inicio="";
                    String aux_Fin="";
                    if(salida.get(i+5).getTipo_Token()==Token.Tipo.INTERVALO_VIRGUILILLA){
                        aux_Inicio =  salida.get(i+4).getValor() ;
                        aux_Fin = salida.get(i+6).getValor();
                        JOptionPane.showMessageDialog(null, "Inicio: "+aux_Inicio+", Fin: "+aux_Fin);
                        aux.agregarNumero(Integer.parseInt(aux_Inicio),Integer.parseInt(aux_Fin));                    
                        conjuntos.add(aux);                     
                    }
                    else if(salida.get(i+5).getTipo_Token()==Token.Tipo.COMA){
                        String val = salida.get(i+4).getValor();
                        aux.agregarNComa(val);
                        int aux_ = i+5;
                        while(salida.get(aux_).getTipo_Token()!=Token.Tipo.PUNTO_Y_COMA){
                            if(salida.get(aux_).getTipo_Token()!=Token.Tipo.COMA){                               
                                aux.agregarNComa(salida.get(aux_).getValor());
                            }
                            aux_++;
                        }
                        conjuntos.add(aux);
                        i=aux_;
                    }
                }
                else if(salida.get(i+4).getTipo_Token()==Token.Tipo.ID){
                    String aux_Inicio="";
                    String aux_Fin="";
                    if(salida.get(i+5).getTipo_Token()==Token.Tipo.INTERVALO_VIRGUILILLA){
                        aux_Inicio = salida.get(i+4).getValor();
                        aux_Fin = salida.get(i+6).getValor();
                        
                        JOptionPane.showMessageDialog(null, "Inicio: "+aux_Inicio+", Fin: "+aux_Fin);
                        aux.agregarIntervalor(aux_Inicio, aux_Fin);
                        
                        conjuntos.add(aux);
                    }
                    else if(salida.get(i+5).getTipo_Token()==Token.Tipo.COMA){
                        String val = salida.get(i+4).getValor();
                        aux.agregarNComa(val);
                        int aux_ = i+5;
                        while(salida.get(aux_).getTipo_Token()!=Token.Tipo.PUNTO_Y_COMA){
                            if(salida.get(aux_).getTipo_Token()!=Token.Tipo.COMA){                               
                                aux.agregarNComa(salida.get(aux_).getValor());
                            }
                            aux_++;
                        }
                        conjuntos.add(aux);
                        i=aux_;
                    }
                }
                else if(salida.get(i+4).getTipo_Token()==Token.Tipo.SIMBOLO){
                    String aux_Inicio="";
                    String aux_Fin="";
                    
                    if(salida.get(i+5).getTipo_Token()==Token.Tipo.INTERVALO_VIRGUILILLA){
                        aux_Inicio = salida.get(i+4).getValor();
                        aux_Fin = salida.get(i+6).getValor();
                        
                        JOptionPane.showMessageDialog(null, "Inicio: "+aux_Inicio+", Fin: "+aux_Fin);
                        aux.agregarSimbolos(aux_Inicio, aux_Fin);
                        
                        conjuntos.add(aux);
                    }
                    else if(salida.get(i+5).getTipo_Token()==Token.Tipo.COMA){
                        
                    }
                }
            }
            else if(salida.get(i).getTipo_Token()==Token.Tipo.ID){
                if(salida.get(i+1).getTipo_Token()==Token.Tipo.FLECHA){
                    //aqui tengo la lista de popo
                    
                }
            }
        }
        this.mostrarConjuntos();
    }
    
    
    //si uso este
    public void lectura_Token(){
        boolean esPorcentaje = false;
        for (int i = 0; i < salida.size(); i++) {
            if(esPorcentaje==false){
                
                if(salida.get(i).getTipo_Token()==Token.Tipo.RESERVADA_CONJ){
                    if(salida.get(i+2).getTipo_Token()== Token.Tipo.ID){
                        Conjunto auxiliar = new Conjunto(salida.get(i+2).getValor());
                        //System.out.println(salida.get(i+2).getValor());
                            if(salida.get(i+4).getTipo_Token()== Token.Tipo.SIMBOLO){
                                //System.out.println(salida.get(i+4).getValor());
                                auxiliar.setValor(salida.get(i+4).getValor());
                                auxiliar.llenarLista();
                                conjuntos.add(auxiliar);
                                i=i+4;
                            }
                    }
                }
                else if(salida.get(i).getTipo_Token()==Token.Tipo.ID){
                    Expresion_Regular exp = new Expresion_Regular(salida.get(i).getValor());
                    //System.out.println(exp.getNombre());
                    if(salida.get(i+1).getTipo_Token()==Token.Tipo.FLECHA){
                        
                        for (int j = i+1; j < salida.size(); j++) {
                            if(salida.get(j).getTipo_Token()==Token.Tipo.PUNTO_Y_COMA){
                                i=j;
                                break;
                            }
                            else{
                                if(salida.get(j).getTipo_Token()!=Token.Tipo.FLECHA){
                                    //System.out.println(salida.get(j).getValor());
                                    // deberia agregar a lista de tokens
                                    exp.getListaTokens().addLast(salida.get(j));
                                }
                            }
                        }
                        expresiones.addLast(exp);
                    }
                }
                else if(salida.get(i).getTipo_Token()==Token.Tipo.PORCENTAJE){
                    esPorcentaje = true;
                }
            }
            else{
                
            }
            
        }
        //System.out.println(conjuntos.size());
        this.mostrarConjuntos();
        this.mostrarExpresiones();
        this.implementarArbol();
    }
    
    
    public void mostrarConjuntos(){
        String datos = "";
        for(Conjunto t : conjuntos){
            datos += t.getNombre() + " -> ";
            for (String val : t.getValores()) {
                datos += val +",";
            }
            datos += "\n";
        }
        JOptionPane.showMessageDialog(null, datos);
    }
    public void mostrarExpresiones(){
        String datos = "";
        for (Expresion_Regular exp : expresiones) {
            datos += exp.getNombre() + " -> ";
            for (Token t : exp.getListaTokens()) {
                datos += t.getTipoString();
            }
            datos +="\n";
        }
        JOptionPane.showMessageDialog(null, datos);
    }
    
    public void implementarArbol(){
        for (Expresion_Regular exp : expresiones) {
            exp.getArbol().setTokens(exp.getListaTokens());
        }
        for (Expresion_Regular exp : expresiones) {
            exp.getArbol().raiz = exp.getArbol().metodoPreorden();
        }
        for (Expresion_Regular exp : expresiones) {
            exp.getArbol().graficarArbol();
        }
        for (Expresion_Regular exp : expresiones) {
            exp.getArbol().ejecutar_Numeracion();
        }
    }
}
