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
public class Conjunto {
    private String nombre;
    private String valor;
    private LinkedList<String> valores = new LinkedList<String>();

    public Conjunto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public LinkedList<String> getValores() {
        return valores;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValores(LinkedList<String> valores) {
        this.valores = valores;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    public void llenarLista(){
        if(valor.contains("~")){
            boolean esNumero = false;
            String[] partes = valor.split("~");
            String inicios = partes[0];
            String fins = partes[1];
            
            try{
                int inicio  = Integer.parseInt(inicios);
                int fin = Integer.parseInt(fins);
                esNumero = true;
                System.out.println("Inicio: "+inicio +", Fin: "+fin);
                
                for (int i = inicio; i < fin+1; i++) {
                    this.valores.addLast(i+"");
                }
            }catch(Exception e){
                esNumero = false;
                
                int inicio = (char)inicios.charAt(0);
                int fin = (char)fins.charAt(0);
                
                for (int i = inicio; i < fin +1; i++) {
                    this.valores.addLast((char)i +"");
                }
                
            }
        }
        else{
            String texto="";
            String aux = this.valor+",";
            this.valor+=",";
            for (int i = 0; i < aux.length(); i++) {
                char a = aux.charAt(i);
                if(a==','){
                    this.valores.addLast(texto);
                    texto="";
                }
                else{
                    texto += a;
                }
            }
        }
    }
    
    public void agregarIntervalor(String inicio, String fin){
        char i = inicio.charAt(0);
        char f = fin.charAt(0);
        int ascii_i = i;
        int ascii_f = f;
        
        for (int j = ascii_i; j < ascii_f+1; j++) {
            char v = (char) j;
            valores.add(v+"");
        }
    }
    
    public void agregarNumero(int inicio, int fin){
        for (int j = inicio; j < fin+1; j++) {
            valores.add(j+"");
        }
    }
    public void agregarNComa(String numero){
        valores.add(numero);
    }
    
    public void agregarSimbolos(String inicio, String fin){
        char i = inicio.charAt(0);
        char f = fin.charAt(0);
        int ascii_i = i;
        int ascii_f = f;
        
        for (int j = ascii_i; j < ascii_f+1; j++) {
            if((j>=33&&j<=47) || (j>=58 && j<=64) || (j>=91 && j<=96) || (j>=123 && j<=125)){               
                char v = (char) j;
                valores.add(v+"");
            }
        }
    }
    
}
