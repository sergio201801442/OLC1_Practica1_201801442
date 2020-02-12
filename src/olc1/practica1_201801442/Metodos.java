/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc1.practica1_201801442;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Sergio
 */
public class Metodos {
    public String abrirARchivo(){
        try{
            JFileChooser jfc = new JFileChooser("C:\\Users\\Sergio\\Desktop");
            jfc.setAcceptAllFileFilterUsed(false);
            jfc.setFileFilter(new FileNameExtensionFilter("Archivos *.er", "er"));
            jfc.showOpenDialog(null);
            String ruta = jfc.getSelectedFile().getAbsolutePath();
            return ruta;
        }
        catch(Exception e){
            return "";
        }
    }
    
    public String readArchivo(String ruta){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        
        try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File (ruta);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea = "";
         String archivoTotal="";
         while((linea=br.readLine())!=null){
             archivoTotal += linea+"\n";
         }
         return archivoTotal;  
      }
      catch(Exception e){
         e.printStackTrace();
         return "";
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
    }
    
    
}
