/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.jvfx;

import espol.edu.ec.TDA.ArbolHuffman;
import espol.edu.ec.Util.Util;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author paulestrada
 */
public class Ventana {
    private String[] opciones;
    
    //accion = 0 -> Comprimir   -----   accion = 1 -> Descomprimir
    private int accion;
    
    private HBox root;
    private VBox inputCont;
    private VBox outputCont;
    
    private Label archivoLabel;
    private Label nuevoArchivoLabel;
    
    private TextField archivoTF;
    private TextField nuevoArchivoTF;
    
    private ChoiceBox choiceBox;
    
    private Button button;
    
    private Text output;
    
    public Ventana () {
        this.opciones = new String[] {"Comprimir","Descomprimir"};
        this.choiceBox = new ChoiceBox(FXCollections.observableArrayList("Comprimir", "Descomprimir"));
        this.choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
       
                int caso = newValue.intValue();
                archivoTF.setText("");
                nuevoArchivoTF.setText("");
                output.setText("");
                switch (caso) {
                    case 0:
                        accion = 0;
                        button.setText("Comprimir");
                        break;
                    case 1:
                        accion = 1;
                        button.setText("Descomprimir");
                        break;
                }
            }
        });
        
        this.archivoTF = new TextField();
        this.nuevoArchivoTF = new TextField();
        
        this.archivoLabel = new Label("Ingrese nombre de archivo: ");
        this.nuevoArchivoLabel = new Label("Ingrese nuevo nombre de archivo: ");
        
        this.button = new Button();
        this.button.setText(" -- ");
        this.button.setOnAction(e -> {
            switch (accion) {
                    case 0:
                        comprimir();
                        break;
                    case 1:
                        descomprimir();
                        break;
                }
        });
        
        this.output = new Text();
        this.output.maxWidth(300);
        
        
        this.inputCont = new VBox();
        this.inputCont.getChildren().addAll(this.choiceBox,this.archivoLabel,this.archivoTF,this.nuevoArchivoLabel,this.nuevoArchivoTF,this.button);
        this.inputCont.setSpacing(15);
        
        this.outputCont = new VBox();
        this.outputCont.setAlignment(Pos.CENTER);
        this.outputCont.setSpacing(20);
        this.outputCont.setMaxWidth(400);
        this.outputCont.getChildren().addAll(this.output);
        
        this.root = new HBox();
        this.root.getChildren().addAll(this.inputCont,this.outputCont);
        
        
    }
    
    public HBox getRoot() {
        return this.root;
    }
    
    
    private void comprimir() {
        if (!this.archivoTF.getText().isEmpty() && !this.nuevoArchivoTF.getText().isEmpty()) {
            String archivo = Util.leerTexto(this.archivoTF.getText());
            if (!archivo.isEmpty()) {
                String result = "Archivo abierto: \n";
                result += archivo + "\n\n";
                
                //calculando frecuencias
                HashMap<String, Integer> frecuencias = Util.calcularFrecuencias(archivo);
                
                //creando arbol
                ArbolHuffman arbol= new ArbolHuffman();
                arbol.calcularArbol(frecuencias);
                
                //calculando codigo
                HashMap<String,String> codigos = arbol.calcularCodigos();
                
                //calculando codigo binario
                String codBin = arbol.codificar(archivo, codigos);
                String codHexa = Util.binarioHexa(codBin);
                
                result += "Tabla de Frecuencias: \n" + frecuencias + "\n\n";
                result += "Codigos del archivo: \n" + codigos + "\n\n";
                result += "Archivo en binario: \n" + codBin + "\n\n";
                result += "Archivo en Hexa: \n" + codHexa + "\n\n";
                
                try {
                    
                    String nombreArchivo = this.nuevoArchivoTF.getText().split(".txt")[0];
                    Util.guardarTexto(nombreArchivo, codHexa, frecuencias);
                    result += "Archivo comprimido guardado: \n";
                } catch (IOException ex) {
                    result += "Error guardando archivo comprimido: \n";
                }
                this.output.setText(result);
            } else {
                this.output.setText("Archivo no encontrado.");
            }
        } else {
            this.output.setText("Llene todos los campos");
        }
    }
    
    
    private void descomprimir() {
        if (!this.archivoTF.getText().isEmpty() && !this.nuevoArchivoTF.getText().isEmpty()) {
            String archivo = Util.leerTexto(this.archivoTF.getText());
            String codigos = Util.leerTexto(this.archivoTF.getText().split(".txt")[0]+"_compress.txt");
            HashMap<String, Integer> frecuencias = Util.leerMapa(this.archivoTF.getText().split(".txt")[0]+"_tablaFrec.txt");
            if (!archivo.isEmpty() && !codigos.isEmpty() && !frecuencias.isEmpty()) {
               
              
                String codBin = Util.hexaBinario(archivo);
                
                
                ArbolHuffman arbol= new ArbolHuffman();
                arbol.calcularArbol(frecuencias);
                String archivoDeco = arbol.decodificar(codBin);
                
                
                String result = "Archivo abierto: \n";
                result += archivo + "\n\n";
                result += "Tabla de frecuencias del archivo: \n" + frecuencias + "\n\n";
                result += "Mapa de codigos del archivo: \n" + codigos + "\n\n";
                result += "Binario del archivo: \n" + codBin + "\n\n";
                result += "Archivo decodificado: \n" + archivoDeco + "\n\n";
                
                
                
                try {
                    String nombreArchivo = this.nuevoArchivoTF.getText().split(".txt")[0];
                    Util.guardarTexto(nombreArchivo, archivoDeco, null);
                    result += "Archivo decodificado guardado: \n";
                } catch (IOException ex) {
                    result += "Error guardando archivo decodificado.\n";
                }
                
                this.output.setText(result);
            } else {
                this.output.setText("Archivo no encontrado.");
            }
        } else {
            this.output.setText("Llene todos los campos");
        }
    }
}
