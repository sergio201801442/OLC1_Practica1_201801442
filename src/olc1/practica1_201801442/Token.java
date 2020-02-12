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
public class Token {
    
    public enum Tipo{
        COMENTARIO_UNA_LINEA, //YA
        COMENTARIO_INICIO_FIN,  //YA
        LLAVE_IZQ, //YA
        LLAVE_DER, //YA
        ID, //YA
        ENTERO, //YA
        FLECHA, //TA
        // EXPRESION_REGULAR,
        // EXPRESIONES REGULARES?
        PUNTO_Y_COMA, //TA
        PORCENTAJE, 
        CADENA, //YA
        DOS_PUNTOS, //YA
        RESERVADA_CONJ, //TA
        CONCATENAR, //ya
        DISYUNCION, //ya
        INTERRO, //ya
        KLEENE, //ya
        MAS_OBLIGATORIO, //ya
        INTERVALO_VIRGUILILLA, //ya
        COMA, //ya
        SIMBOLO,
        DESCONOCIDO
    }
    
    private String valor;
    private int fila;
    private int columna;
    private Tipo tipo_Token;

    public Token(String valor, int fila, int columna, Tipo tipo_Token) {
        this.valor = valor;
        this.fila = fila;
        this.columna = columna;
        this.tipo_Token = tipo_Token;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public Tipo getTipo_Token() {
        return tipo_Token;
    }

    public void setTipo_Token(Tipo tipo_Token) {
        this.tipo_Token = tipo_Token;
    }
    
    public String getTipoString(){
        
        switch(tipo_Token){
            
            case COMENTARIO_UNA_LINEA:
                return "Comentario una linea";
            case COMENTARIO_INICIO_FIN:
                return "Comentario inicio-fin";
            case LLAVE_IZQ:
                return "Llave Izquierda";
            case LLAVE_DER:
                return "Llave Derecha";
            case ID:
                return "Identificador";
            case FLECHA:
                return "Flecha/Asignacion";
            case PUNTO_Y_COMA:
                return "Punto y Coma";
            case PORCENTAJE:
                return "Porcentaje";
            case CADENA:
                return "Cadena";
            case DOS_PUNTOS:
                return "Dos Puntos";
            case RESERVADA_CONJ:
                return "Reservada Conjunto";
            case DESCONOCIDO:
                return "Desconocido";
            case SIMBOLO:
                return "Valor Conjunto";
            default:
                return "Desconocido";
        }
    }
    
}
