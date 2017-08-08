/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author navi9
 */
public class Util {
    
    public static String leerTexto(String archivo){
        String texto="";
        
        try{
            File file=new File(archivo);
            Scanner scanner= new Scanner(file);
            while(scanner.hasNext()){
                texto+=scanner.next();
            }
        }
        catch(FileNotFoundException e){
            return texto;
        }       
        return texto;
    }
    
    
    public static HashMap<String, Integer> calcularFrecuencias(String texto){
        HashMap<String, Integer> mapa = new HashMap<>();
        String[] textoSplit = texto.split("(?!^)");
        
        for(String letra: textoSplit ){
            if(!mapa.containsKey(letra)){
                mapa.put(letra, 1);
            }else{
                mapa.put(letra, mapa.get(letra)+1);
            }
        }       
        return mapa;
    }
    
    public static String binarioHexa(String binario){
        int decimal = Integer.parseInt(binario, 2);
        String hexa= Integer.toHexString(decimal);
        
        return hexa;
    }
    
    public static String hexaBinario(String hexa){
        
        int decimal = Integer.parseInt(hexa, 16);
        String binario= Integer.toBinaryString(decimal);
        
        return binario;
    }
    
    public static void guardarTexto(String nombreArchivo, String texto, HashMap<String, Integer> tablaFrec ) throws IOException{
    
        try{
            PrintWriter writer = new PrintWriter(nombreArchivo+".txt", "UTF-8");
            writer.write(texto);
            writer.close();
        }catch (FileNotFoundException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (tablaFrec != null) {
            try{
                PrintWriter writer = new PrintWriter(nombreArchivo+"_tablaFrec.txt", "UTF-8");
                writer.write(tablaFrec.toString().replace("{", "").replace("}", ""));
                writer.close();
            }catch (FileNotFoundException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    
    public static HashMap<String, Integer> leerMapa(String nombreArchivo){
        String texto="";
        HashMap<String, Integer> mapa= new HashMap<>();
        try{
            File file=new File(nombreArchivo);
            Scanner scanner= new Scanner(file);
            while(scanner.hasNext()){
                texto+=scanner.next();
            }
        }
        catch(FileNotFoundException e){
            return mapa;
        }       
       
        String[] codigos= texto.split(",");
        for(String codigo: codigos){
            String[] terminos= codigo.split("=");
            mapa.put(terminos[0], Integer.parseInt(terminos[1]));
        }
        
        return mapa;
    }
}
