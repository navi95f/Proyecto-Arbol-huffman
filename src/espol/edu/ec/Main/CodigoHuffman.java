/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.Main;

import espol.edu.ec.TDA.ArbolHuffman;
import java.awt.BorderLayout;
import java.util.HashMap;

/**
 *
 * @author navi9
 */
public class CodigoHuffman {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

       HashMap<String, Integer> mapa= new HashMap<>();
       ArbolHuffman arbol= new ArbolHuffman();
       mapa.put("A", 10);
       mapa.put("B", 3);
       mapa.put("C", 5);
       mapa.put("D", 2);
       arbol.calcularArbol(mapa);
       HashMap<String,String> codigos=arbol.calcularCodigos();
       System.out.println(codigos);
       String codigo=arbol.codificar("AAABBBCCCD", codigos);
       System.out.println(codigo);
       String letras= arbol.decodificar(codigo);
        System.out.println(letras);
    }
    
}
