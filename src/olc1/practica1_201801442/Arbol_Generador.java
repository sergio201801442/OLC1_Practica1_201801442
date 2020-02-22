/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc1.practica1_201801442;

import java.io.FileWriter;
import java.util.LinkedList;

/**
 *
 * @author Sergio
 */
public class Arbol_Generador {
    public Nodo_Arbol raiz;
    private int contador = 0; // quite el static
    private int numerador=1;
    private int state = 0;
    private LinkedList<Token> tokens;
    private LinkedList<Lista_Siguientes> tabla_Sig = new LinkedList<Lista_Siguientes>(); 
    private LinkedList<Estados> estados_Transicion = new LinkedList<Estados>();
    public Arbol_Generador() {
        this.raiz = null;
    }
    private String primeros ="";
    private String aux ="";

    public void setTokens(LinkedList<Token> tokens) {
        this.tokens = tokens;
    }

    public LinkedList<Token> getTokens() {
        return tokens;
    }
    
    
    // este es el metodo para insertar
    
    Nodo_Arbol metodoPreorden(){
        System.out.println("Me llaman preorden "+contador);
        // este metodo es para recorrer la lista de tokens de la expresion regular
        //entonces de primero para evitar null pointers pregunto que el numero por 
        //el cual va no sea mayor
        if(contador >= tokens.size()){
            return null;
        }
        //esta es la opcion concatenar
        else if(tokens.get(contador).getTipo_Token()==Token.Tipo.CONCATENAR){
            System.out.println("Concatenar");
            Nodo_Arbol retorno = new Nodo_Arbol();    
            retorno.setValor(tokens.get(contador).getValor());
            retorno.setValue(tokens.get(contador));
            contador++;
            //aqui hago recursividad a la izquierda y derecha
            retorno.setIzquierda(metodoPreorden());
            retorno.setDerecha(metodoPreorden());
            if(retorno.getIzquierda().getAnulable().equals("F") || retorno.getDerecha().getAnulable().equals("F")){
                retorno.setAnulable("F");
            }
            else{
                retorno.setAnulable("T");
            }
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
            if(retorno.getIzquierda().getAnulable().equals("T") || retorno.getDerecha().getAnulable().equals("T")){
                retorno.setAnulable("T");
            }
            else{
                retorno.setAnulable("F");
            }
            return retorno;
        }
        //en este solo le mando a la izquierda porque no puede tener dos hijos
        else if(tokens.get(contador).getTipo_Token()==Token.Tipo.MAS_OBLIGATORIO){
            System.out.println("Mas Obligatorio");
            Nodo_Arbol retorno = new Nodo_Arbol();    
            retorno.setValor(tokens.get(contador).getValor());
            retorno.setValue(tokens.get(contador));
            retorno.setAnulable("F");
            contador++;
            retorno.setIzquierda(metodoPreorden());
            //retorno.setDerecha(metodoPreorden());
            return retorno;
        }
        //en este solo le mando a la izquierda porque no puede tener dos hijos
        else if(tokens.get(contador).getTipo_Token()==Token.Tipo.KLEENE){
            System.out.println("Kleene");
            Nodo_Arbol retorno = new Nodo_Arbol();    
            retorno.setValor(tokens.get(contador).getValor());
            retorno.setValue(tokens.get(contador));
            retorno.setAnulable("T");
            contador++;
            retorno.setIzquierda(metodoPreorden());
            //retorno.setDerecha(metodoPreorden());
            return retorno;
        }
        //en este solo le mando a la izquierda porque no puede tener dos hijos
        else if(tokens.get(contador).getTipo_Token()==Token.Tipo.INTERRO){
            System.out.println("Kleene");
            Nodo_Arbol retorno = new Nodo_Arbol();    
            retorno.setValor(tokens.get(contador).getValor());
            retorno.setValue(tokens.get(contador));
            retorno.setAnulable("T");
            contador++;
            retorno.setIzquierda(metodoPreorden());
            return retorno;
        }
        //este es hoja
        else if(tokens.get(contador).getTipo_Token()==Token.Tipo.CADENA){
            System.out.println("Cadena");
            Nodo_Arbol retorno = new Nodo_Arbol();
            retorno.setValor( tokens.get(contador).getValor());
            retorno.setValue(tokens.get(contador));
            retorno.setAnulable("F");
            retorno.setEsConjunto(false);
            contador++;
            return retorno;
        }
        //este es conjunto, es hoja tambien
        else if(tokens.get(contador).getTipo_Token()==Token.Tipo.LLAVE_IZQ){
            System.out.println("ID");
            Nodo_Arbol retorno = new Nodo_Arbol();
            retorno.setValor( tokens.get(contador+1).getValor());
            retorno.setValue(tokens.get(contador+1));
            retorno.setAnulable("F");
            retorno.setEsConjunto(true);
            contador+=3;
            return retorno;
        }
        
        return null;
    }
    public void graficarArbol2(){
        String grafica = "digraph D {\n" +
        "\n" +
        "  node [shape=plaintext fontname=\"Sans serif\" fontsize=\"8\"];";
        grafica+=this.raiz.getNodoTotal();
        grafica += " }";
        try{
            FileWriter fichero = null;
            fichero = new FileWriter("arbol"+Lexico.numero+".dot");
            fichero.write(grafica);
            fichero.close();
            
            Runtime rt = Runtime.getRuntime();//C:\ProyectoJava
            //rt.exec("dot -Tpng C:\\Users\\Sergio\\Documents\\NetBeansProjects\\EDD_DIC_2019_PY2_201801442\\recorrido"+numero+".dot -o recorrido"+numero+".png");
            rt.exec("dot -Tpng arbol"+Lexico.numero+".dot -o arbol"+Lexico.numero+".png");
            Lexico.numero++;
            //Formulario_Usuario.jLabel1.setIcon(new ImageIcon("C:\\Users\\Sergio\\Documents\\NetBeansProjects\\EDD_DIC_2019_PY2_201801442\\avl"+numero+".png"));
            //Process p = Runtime.getRuntime().exec("cmd /c \""+"C:\\Users\\Sergio\\Documents\\NetBeansProjects\\EDD_DIC_2019_PY2_201801442\\avl"+numero+".png"+"\"");
            //p.waitFor();
        }catch(Exception e){
            System.out.println(e);
        }
         
        this.getReporteTabla();
        this.getReportePrimeros();
    }
    
    public void graficarArbol(){
        String grafica = "digraph grafica{\n rankdir=TB;\n node [shape=box, style=filled, fillcolor=cornsilk1];\n";
        grafica += this.raiz.getCodigoNodo();
        grafica += "}";
        System.out.println(grafica);
         try{
            FileWriter fichero = null;
            fichero = new FileWriter("arbol"+Lexico.numero+".dot");
            fichero.write(grafica);
            fichero.close();
            
            Runtime rt = Runtime.getRuntime();//C:\ProyectoJava
            //rt.exec("dot -Tpng C:\\Users\\Sergio\\Documents\\NetBeansProjects\\EDD_DIC_2019_PY2_201801442\\recorrido"+numero+".dot -o recorrido"+numero+".png");
            rt.exec("dot -Tpng arbol"+Lexico.numero+".dot -o arbol"+Lexico.numero+".png");
            Lexico.numero++;
            //Formulario_Usuario.jLabel1.setIcon(new ImageIcon("C:\\Users\\Sergio\\Documents\\NetBeansProjects\\EDD_DIC_2019_PY2_201801442\\avl"+numero+".png"));
            //Process p = Runtime.getRuntime().exec("cmd /c \""+"C:\\Users\\Sergio\\Documents\\NetBeansProjects\\EDD_DIC_2019_PY2_201801442\\avl"+numero+".png"+"\"");
            //p.waitFor();
        }catch(Exception e){
            System.out.println(e);
        }
         
        this.getReporteTabla();
        this.getReportePrimeros();
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
    public void actualizar_Primeros_Ultimos(){
        this.primeros(this.raiz);
        this.ultimos(raiz);
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
    
    
    public void show(){
        System.out.println("Cambio Grafica");
        this.mostrarPriUlt(this.raiz);
    }
    
    
    private void mostrarPriUlt(Nodo_Arbol raiz){
        if(raiz.getIzquierda()!=null){
            mostrarPriUlt(raiz.getIzquierda());
        }
        if(raiz.getDerecha()!=null){
            mostrarPriUlt(raiz.getDerecha());
        }
        System.out.println("PRIMEROS de raiz " +raiz.getValor() );
        for (Integer a : raiz.getPrimeros()) {
            System.out.println(a+"");
        } 
        System.out.println("ULTIMOS de raiz " + raiz.getValor() );
        for (Integer a : raiz.getUltimos()) {
            System.out.println(a+"");
        }       
    }
    private String getInternoPrimeros(Nodo_Arbol raiz){
        if(raiz.getIzquierda()!=null){
            getInternoPrimeros(raiz.getIzquierda());
        }
        if(raiz.getDerecha()!=null){
            getInternoPrimeros(raiz.getDerecha());
        }
        System.out.println("PRIMEROS de raiz " +raiz.getValor());
        for (Integer a : raiz.getPrimeros()) {
            primeros += "<tr><td>"+raiz.getNumero_Hoja()+"</td><td>"+a+"</td></tr>\n";
        } 
        
        return primeros;
    }
    
    
    public void ejecutar_Numeracion(){
        this.actualizar_Estado(this.raiz);
        this.inOrden(this.raiz);
    }
    
    public void primeros(Nodo_Arbol raiz){
        if(raiz.getIzquierda()!=null){
            primeros(raiz.getIzquierda());
        }
        
        if(raiz.getDerecha()!=null){
            primeros(raiz.getDerecha());
        }   
        
        if(raiz.getValue().getTipo_Token()==Token.Tipo.CADENA){
            System.out.println("Entre a una cadena");
            LinkedList<Integer> priSig = new LinkedList<Integer>();
            priSig.addLast(raiz.getNumero_Hoja());
            raiz.setPrimeros(priSig);
        }
        else if(raiz.getValue().getTipo_Token()==Token.Tipo.ID){
            System.out.println("Entre a un identificador");
            LinkedList<Integer> priSig = new LinkedList<Integer>();
            priSig.addLast(raiz.getNumero_Hoja());
            raiz.setPrimeros(priSig);
        }
        else if(raiz.getValue().getTipo_Token()==Token.Tipo.CONCATENAR){
            LinkedList<Integer> primeros_I = new LinkedList<Integer>();
                if(raiz.getIzquierda().getAnulable().equals("T")){
                    for (Integer a :raiz.getIzquierda().getPrimeros()) {
                        primeros_I.addLast(a);
                    }
                    for (Integer a :raiz.getDerecha().getPrimeros()) {
                        primeros_I.addLast(a);
                    }
                }
                else{
                    for (Integer a :raiz.getIzquierda().getPrimeros()) {
                        primeros_I.addLast(a);
                    }
                }
            
            raiz.setPrimeros(primeros_I);
        }
        else if(raiz.getValue().getTipo_Token()==Token.Tipo.DISYUNCION){
            LinkedList<Integer> primeros_I = new LinkedList<Integer>();
            if(raiz.getIzquierda()!=null){
                for (Integer a :raiz.getIzquierda().getPrimeros()) {
                    primeros_I.addLast(a);
                }
            }
            if(raiz.getDerecha()!=null){
                for (Integer a :raiz.getDerecha().getPrimeros()) {
                    primeros_I.addLast(a);
                }
            }
            raiz.setPrimeros(primeros_I);
        }
        else if(raiz.getValue().getTipo_Token()==Token.Tipo.KLEENE){
            LinkedList<Integer> primeros_I = new LinkedList<Integer>();
            if(raiz.getIzquierda()!=null){
                for (Integer a :raiz.getIzquierda().getPrimeros()) {
                    primeros_I.addLast(a);
                }
            }
            raiz.setPrimeros(primeros_I);
        }
        else if(raiz.getValue().getTipo_Token()==Token.Tipo.MAS_OBLIGATORIO){
            LinkedList<Integer> primeros_I = new LinkedList<Integer>();
            if(raiz.getIzquierda()!=null){
                for (Integer a :raiz.getIzquierda().getPrimeros()) {
                    primeros_I.addLast(a);
                }
            }
            raiz.setPrimeros(primeros_I);
        }
        else if(raiz.getValue().getTipo_Token()==Token.Tipo.INTERRO){
            LinkedList<Integer> primeros_I = new LinkedList<Integer>();
            if(raiz.getIzquierda()!=null){
                for (Integer a :raiz.getIzquierda().getPrimeros()) {
                    primeros_I.addLast(a);
                }
            }
            raiz.setPrimeros(primeros_I);
        }
        
    }
  
    public void ultimos(Nodo_Arbol raiz){
        if(raiz.getIzquierda()!=null){
            ultimos(raiz.getIzquierda());
        }
        
        if(raiz.getDerecha()!=null){
            ultimos(raiz.getDerecha());
        }
        
        if(raiz.getValue().getTipo_Token()==Token.Tipo.CADENA){
            LinkedList<Integer> ultimos = new LinkedList<Integer>();
            ultimos.addLast(raiz.getNumero_Hoja());
            raiz.setUltimos(ultimos);
        }
        else if(raiz.getValue().getTipo_Token()==Token.Tipo.ID){
            LinkedList<Integer> ultimos = new LinkedList<Integer>();
            ultimos.addLast(raiz.getNumero_Hoja());
            raiz.setUltimos(ultimos);
        }
        else if(raiz.getValue().getTipo_Token()==Token.Tipo.INTERRO){
            LinkedList<Integer> ultimos = new LinkedList<Integer>();
            if(raiz.getIzquierda()!=null){
                for(Integer a : raiz.getIzquierda().getUltimos()){
                    ultimos.addLast(a);
                }
            }
            raiz.setUltimos(ultimos);
        }
        else if(raiz.getValue().getTipo_Token()==Token.Tipo.MAS_OBLIGATORIO){
            LinkedList<Integer> ultimos = new LinkedList<Integer>();
            if(raiz.getIzquierda()!=null){
                for(Integer a : raiz.getIzquierda().getUltimos()){
                    ultimos.addLast(a);
                }
            }
            raiz.setUltimos(ultimos);
        }
        else if(raiz.getValue().getTipo_Token()==Token.Tipo.CONCATENAR){
            LinkedList<Integer> ultimos = new LinkedList<Integer>();
            if(raiz.getDerecha().getAnulable().equals("T")){
                for (Integer a : raiz.getDerecha().getUltimos()) {
                    ultimos.addLast(a);
                }
                for (Integer a : raiz.getIzquierda().getUltimos()) {
                    ultimos.addLast(a);
                }               
            }
            else{
                for (Integer a : raiz.getDerecha().getUltimos()) {
                    ultimos.addLast(a);
                }
            }
            raiz.setUltimos(ultimos);
            
        }else if(raiz.getValue().getTipo_Token()==Token.Tipo.DISYUNCION){
            LinkedList<Integer> ultimos = new LinkedList<Integer>();
            if(raiz.getIzquierda()!=null){
                for(Integer a : raiz.getIzquierda().getUltimos()){
                    ultimos.addLast(a);
                }
            }
            if(raiz.getDerecha()!=null){
                for(Integer a : raiz.getDerecha().getUltimos()){
                    ultimos.addLast(a);
                }
            }
            raiz.setUltimos(ultimos);
        }
        else if(raiz.getValue().getTipo_Token()==Token.Tipo.KLEENE){
            LinkedList<Integer> ultimos = new LinkedList<Integer>();
            if(raiz.getIzquierda()!=null){
                for(Integer a : raiz.getIzquierda().getUltimos()){
                    ultimos.addLast(a);
                }
            }
            raiz.setUltimos(ultimos);
        }
    }
    
    public void next(){
        this.siguientes(this.raiz);
    }
    private void siguientes(Nodo_Arbol raiz){
        if(raiz.getIzquierda()!=null){
            siguientes(raiz.getIzquierda());
        }
        
        if(raiz.getDerecha()!=null){
            siguientes(raiz.getDerecha());
        }
        
        if(raiz.getValue().getTipo_Token()==Token.Tipo.CONCATENAR){
            if(raiz.getIzquierda()!=null){
                for(Integer a : raiz.getIzquierda().getUltimos()){     
                    Lista_Siguientes nueva = new Lista_Siguientes();
                    LinkedList<Integer> numeros = new LinkedList<Integer>();
                    System.out.println("Padre: "+a);                 
                    for(Integer aa : raiz.getDerecha().getPrimeros()){
                        numeros.addLast(aa);
                        System.out.println("Hijo: "+aa);
                    }
                    nueva.setSiguientes(numeros);
                    nueva.setNumero(a);
                    nueva.setValor(raiz.getValue().getValor());
                    this.tabla_Sig.addLast(nueva);
                    raiz.setSiguientes(numeros);
                }
            }
        }
        else if(raiz.getValue().getTipo_Token()==Token.Tipo.KLEENE){
            for(Integer a : raiz.getUltimos()){
                Lista_Siguientes nueva = new Lista_Siguientes();
                LinkedList<Integer> numeros = new LinkedList<Integer>();
                System.out.println("Padre: "+a);
                for(Integer aa : raiz.getPrimeros()){
                    numeros.addLast(aa);
                    System.out.println("Hijo: "+aa);
                }
                    nueva.setSiguientes(numeros);
                    nueva.setNumero(a);
                    nueva.setValor(raiz.getValue().getValor());    
                    this.tabla_Sig.addLast(nueva);
                    raiz.setSiguientes(numeros);
            }
        }
        else if(raiz.getValue().getTipo_Token()==Token.Tipo.MAS_OBLIGATORIO){
            for(Integer a : raiz.getUltimos()){
                Lista_Siguientes nueva = new Lista_Siguientes();
                LinkedList<Integer> numeros = new LinkedList<Integer>();
                System.out.println("Padre: "+a);
                for(Integer aa : raiz.getPrimeros()){
                    numeros.addLast(aa);
                    System.out.println("Hijo: "+aa);
                }
                    nueva.setSiguientes(numeros);
                    nueva.setNumero(a);
                    nueva.setValor(raiz.getValue().getValor());                  
                    this.tabla_Sig.addLast(nueva);
                    raiz.setSiguientes(numeros);
            }
        }
        
    }
    public void muestra_Grafo(){
        for (Lista_Siguientes x : this.tabla_Sig) {
            System.out.println("Papa: "+x.getValor() + " Numero: "+x.getNumero() );
            for(Integer entero : x.getSiguientes()){
                System.out.println(entero);
            }
        }
    }
     public void graficar(){
        String codigoGenerarl = " digraph sergio {\n";
        
        for (Lista_Siguientes x : this.tabla_Sig) {
            System.out.println("Papa: "+x.getValor() + " Numero: "+x.getNumero() );
            codigoGenerarl +=x.getNumero() +"[label = \""+x.getNumero()+"\", color = green, style=filled];\n";
            for(Integer entero : x.getSiguientes()){
                System.out.println(entero);
                codigoGenerarl +=x.getNumero() +"->"+entero+";\n";
            }
        }
        codigoGenerarl += "}";
        System.out.println(codigoGenerarl);
        
        FileWriter fichero = null;
        /*
        try{
            fichero = new FileWriter("arbol"+Lexico.numero+".dot");
            fichero.write(codigoGenerarl);
            fichero.close();
            
            Runtime rt = Runtime.getRuntime();//C:\ProyectoJava
            //rt.exec("dot -Tpng C:\\Users\\Sergio\\Documents\\NetBeansProjects\\EDD_DIC_2019_PY2_201801442\\recorrido"+numero+".dot -o recorrido"+numero+".png");
            rt.exec("dot -Tpng arbol"+Lexico.numero+".dot -o arbol"+Lexico.numero+".png");
            Lexico.numero++;
            //Formulario_Usuario.jLabel1.setIcon(new ImageIcon("C:\\Users\\Sergio\\Documents\\NetBeansProjects\\EDD_DIC_2019_PY2_201801442\\avl"+numero+".png"));
            //Process p = Runtime.getRuntime().exec("cmd /c \""+"C:\\Users\\Sergio\\Documents\\NetBeansProjects\\EDD_DIC_2019_PY2_201801442\\avl"+numero+".png"+"\"");
            //p.waitFor();
        }catch(Exception e){
            System.out.println(e);
        }*/

    }
     public void getReportePrimeros(){
        String codigoGraphviz = "digraph H {\ntbl [\nshape=plaintext \nlabel=<\n";
        codigoGraphviz += "<table border='0' cellborder='1' color='green' cellspacing='0'>\n";
        codigoGraphviz += "<tr><td> Numero</td><td> Primeros </td></tr>";
        codigoGraphviz += this.getInternoPrimeros(this.raiz);
        codigoGraphviz += "</table> \n>];\n}";
        this.primeros="";
        FileWriter fichero = null;
        try{
            fichero = new FileWriter("primeros"+Lexico.numero2+".dot");
            fichero.write(codigoGraphviz);
            fichero.close();
            
            Runtime rt = Runtime.getRuntime();//C:\ProyectoJava
            //rt.exec("dot -Tpng C:\\Users\\Sergio\\Documents\\NetBeansProjects\\EDD_DIC_2019_PY2_201801442\\recorrido"+numero+".dot -o recorrido"+numero+".png");
            rt.exec("dot -Tpng primeros"+Lexico.numero2+".dot -o primeros"+Lexico.numero2+".png");
            Lexico.numero2++;
            //Formulario_Usuario.jLabel1.setIcon(new ImageIcon("C:\\Users\\Sergio\\Documents\\NetBeansProjects\\EDD_DIC_2019_PY2_201801442\\avl"+numero+".png"));
            //Process p = Runtime.getRuntime().exec("cmd /c \""+"C:\\Users\\Sergio\\Documents\\NetBeansProjects\\EDD_DIC_2019_PY2_201801442\\avl"+numero+".png"+"\"");
            //p.waitFor();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void getReporteTabla(){
        String codigoGraphviz = "digraph H {\ntbl [\nshape=plaintext \nlabel=<\n";
        codigoGraphviz += "<table border='0' cellborder='1' color='green' cellspacing='0'>\n";
        codigoGraphviz += "<tr><td> Numero</td><td> Siguientes</td></tr>";
        
        for (Lista_Siguientes x : this.tabla_Sig) {
            String aux = "";
            for(Integer entero : x.getSiguientes()){
                aux += entero+",";
            }
            codigoGraphviz += "<tr><td>"+x.getNumero()+"</td><td>"+aux+"</td></tr>\n";
        }
        codigoGraphviz += "</table> \n>];\n}";
        
        FileWriter fichero = null;
        try{
            fichero = new FileWriter("siguientes"+Lexico.numero1+".dot");
            fichero.write(codigoGraphviz);
            fichero.close();
            
            Runtime rt = Runtime.getRuntime();//C:\ProyectoJava
            //rt.exec("dot -Tpng C:\\Users\\Sergio\\Documents\\NetBeansProjects\\EDD_DIC_2019_PY2_201801442\\recorrido"+numero+".dot -o recorrido"+numero+".png");
            rt.exec("dot -Tpng siguientes"+Lexico.numero1+".dot -o siguientes"+Lexico.numero1+".png");
            Lexico.numero1++;
            //Formulario_Usuario.jLabel1.setIcon(new ImageIcon("C:\\Users\\Sergio\\Documents\\NetBeansProjects\\EDD_DIC_2019_PY2_201801442\\avl"+numero+".png"));
            //Process p = Runtime.getRuntime().exec("cmd /c \""+"C:\\Users\\Sergio\\Documents\\NetBeansProjects\\EDD_DIC_2019_PY2_201801442\\avl"+numero+".png"+"\"");
            //p.waitFor();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void tabla(){
        if(this.raiz!=null){
            Estados estado = new Estados();
            estado.setEstados(this.raiz.getPrimeros());
            estado.nombrar(state);
            state++;
            estados_Transicion.addLast(estado);
            
            for(Integer x : estado.getEstados()){
                Estados nuevo = new Estados();
                nuevo.nombrar(state);
                state++;
                //soy el estado papa
                LinkedList<Integer> estados = new LinkedList<Integer>();
                for(Lista_Siguientes y : tabla_Sig){
                   if(x==y.getNumero()){
                       for(Integer a : y.getSiguientes()){
                           estados.addLast(a);
                       }
                   }
                }
                nuevo.setEstados(estados);
                estados_Transicion.addLast(nuevo);
                
            }
            for(Estados e : estados_Transicion){
                System.out.println(e.getNombre());
                String datos ="";
                for(Integer i : e.getEstados()){
                    datos += i+",";
                }
                System.out.println(datos);
            }
            state=0;
            String codigoGraphviz = "digraph H {\ntbl [\nshape=plaintext \nlabel=<\n";
        codigoGraphviz += "<table border='0' cellborder='1' color='blue' cellspacing='0'>\n";
        codigoGraphviz += "<tr><td> Estado</td><td> Val</td></tr>";
        for(Estados e : estados_Transicion){
            String name = e.getNombre();
            String val = "";
            for(Integer i : e.getEstados()){
                val += i +", ";
            }
            codigoGraphviz += "<tr><td>"+name+"</td><td>"+val+"</td></tr>\n";
        }
        codigoGraphviz += "</table> \n>];\n}";
        
        FileWriter fichero = null;
        try{
            fichero = new FileWriter("tabla"+Lexico.numero3+".dot");
            fichero.write(codigoGraphviz);
            fichero.close();
            
            Runtime rt = Runtime.getRuntime();//C:\ProyectoJava
            //rt.exec("dot -Tpng C:\\Users\\Sergio\\Documents\\NetBeansProjects\\EDD_DIC_2019_PY2_201801442\\recorrido"+numero+".dot -o recorrido"+numero+".png");
            rt.exec("dot -Tpng tabla"+Lexico.numero3+".dot -o tabla"+Lexico.numero3+".png");
            Lexico.numero3++;
            //Formulario_Usuario.jLabel1.setIcon(new ImageIcon("C:\\Users\\Sergio\\Documents\\NetBeansProjects\\EDD_DIC_2019_PY2_201801442\\avl"+numero+".png"));
            //Process p = Runtime.getRuntime().exec("cmd /c \""+"C:\\Users\\Sergio\\Documents\\NetBeansProjects\\EDD_DIC_2019_PY2_201801442\\avl"+numero+".png"+"\"");
            //p.waitFor();
        }catch(Exception e){
            System.out.println(e);
        }
        }
        
    }
    
    private void Transiciones(Nodo_Arbol raiz){
        LinkedList<Integer> aux1 = new LinkedList<Integer>();
        if(raiz.getIzquierda()!=null){
            Transiciones(raiz.getIzquierda());
        }
        if(raiz.getDerecha()!=null){
            Transiciones(raiz.getDerecha());
        }
        System.out.println("Siguientes de raiz " +raiz.getValor() );
        for (Lista_Siguientes x : this.tabla_Sig) {
            String auxiliar = "";
            for (Integer entero : x.getSiguientes()) {
                auxiliar += entero+", ";
            }
            System.out.println(auxiliar);
        }
        
    }
    
}
