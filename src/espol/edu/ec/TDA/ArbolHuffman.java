/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.TDA;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author navi9
 */
public class ArbolHuffman {
    private RamaHuffman raiz;

    public ArbolHuffman() {
        this.raiz=null;
    }
    
    public void calcularArbol(HashMap<String, Integer> mapa){
        Comparator<RamaHuffman> comparator= (RamaHuffman r1, RamaHuffman r2)->(r1.getFrecuencia()-r2.getFrecuencia());
        PriorityQueue <RamaHuffman> arbol= new PriorityQueue<>(comparator);
        
        for(String key: mapa.keySet()){
            arbol.offer(new Hoja(mapa.get(key), key));            
        }
        
        while(arbol.size()>1){
            RamaHuffman r1 = arbol.poll();
            RamaHuffman r2 = arbol.poll();
            
            RamaHuffman nuevoNodo= new Nodo(r1, r2, r1.getFrecuencia()+r2.getFrecuencia());
            arbol.offer(nuevoNodo);  
        }
        
        this.raiz=arbol.poll();
    }
    
    public HashMap<String, String> calcularCodigos(){      
        HashMap<String, String> mapa= new HashMap<>();
        if(this.raiz==null){
            return mapa;
        }else{           
            RamaHuffman nodo = this.raiz;
            this.buscarCodigo("", mapa, nodo);
                 
        }
        return mapa;
    }
    
    private void buscarCodigo(String codigo, HashMap<String, String> mapa, RamaHuffman nodo){
        
        if(nodo!=null){
            if(nodo instanceof Hoja){
                mapa.put(((Hoja) nodo).getValor(), codigo);
                
            }else{
                buscarCodigo(codigo+"0", mapa, ((Nodo)nodo).getIzquierda());
                buscarCodigo(codigo+"1", mapa, ((Nodo)nodo).getDerecha());
            }
        }
        
    }
    
    public String codificar (String texto, HashMap<String,String> mapa){
        String codigo="";
        String[] letras= texto.split("(?!^)");
        
        for(String letra: letras){
            codigo+=mapa.get(letra);
        }
        
        return codigo;  
    }
    
    public String decodificar(String codigo){
    
        String texto="";
        RamaHuffman nodo=this.raiz;
        
        for(int i=0; i<codigo.length(); i++){
            
            if(Character.getNumericValue(codigo.charAt(i))==0){
                if(!(nodo instanceof Hoja)){
                    nodo=((Nodo)nodo).getIzquierda();
                }
                    
            }else{
                if(!(nodo instanceof Hoja)){
                    nodo=((Nodo)nodo).getDerecha();
                }
            }
            
            if(nodo instanceof Hoja){
                texto+=((Hoja)nodo).getValor();
                nodo=this.raiz;
            }
        }
        
        return texto;
    }
}
