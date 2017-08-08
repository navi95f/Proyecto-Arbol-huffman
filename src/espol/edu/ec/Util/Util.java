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
        int cont=0;
        String[] binarioArray = binario.split("");
        String binarioCompleto = "";
        String hex = "";
        
        //contando cuantos bloques de 4 hay
        cont = binario.length() / 4;
        //sacando la diferencia de los digitos que no entraron al bloque de 4
        int diferencia = (binario.length() - (cont*4));
        
        //numero por completar de ceros
        int porCompletar = 4-diferencia;
        
 
        for (String bin : binarioArray) {
            binarioCompleto += bin;
        }
        
        //rellenando de ceros 
        for (int i=0;i<porCompletar;i++) {
            binarioCompleto += "0";
        }
        
        binarioArray = binarioCompleto.split("");
        for (int i=0;i<binarioArray.length - 3;i+=4) {
            String s = binarioArray[i] + binarioArray[i + 1] + binarioArray[i + 2] + binarioArray[i + 3];
            long decimal = Long.parseLong(s,2);
            hex += Long.toHexString(decimal);
        }
        
        for (int i=0;i<porCompletar;i++) {
            hex += "-";
        }
        
        return hex;
    }
    
    public static String hexaBinario(String hexa){
        
        String binario = "";
        String[] hexaArray = hexa.split("");
        int cont = 0;
        
        for (String h : hexaArray) {
            if (!h.equals("-")) {
                long decimal = Long.parseLong(h,16);
                String bin = Long.toBinaryString(decimal);
                if (bin.length() < 4) {
                    int dif = 4-bin.length();
                    for (int i=0;i<dif;i++) {
                        binario+="0";
                    }
                    binario += bin;
                } else {
                    binario += bin;
                }
            } else {
                cont++;
            }
        }
        if (cont != 0) {
            //quitando los digitos añadidos para completar el bloque
            binario = binario.substring(0, binario.length() - cont);
        }
        
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
