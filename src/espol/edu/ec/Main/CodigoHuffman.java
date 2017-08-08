/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.Main;

import espol.edu.ec.TDA.ArbolHuffman;
import java.awt.BorderLayout;
import java.util.HashMap;
import espol.edu.ec.jvfx.Ventana;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author navi9
 */
public class CodigoHuffman extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
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
        */
        
       Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Ventana ventana = new Ventana();
        Scene escenario = new Scene(ventana.getRoot(),800,400);
        primaryStage.setScene(escenario);
        primaryStage.setTitle("COMPRESOR DE ARCHIVOS");
        primaryStage.show();
        
    }
    
}
