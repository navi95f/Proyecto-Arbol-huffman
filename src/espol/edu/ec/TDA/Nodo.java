/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.TDA;

/**
 *
 * @author navi9
 */
public class Nodo extends RamaHuffman{
    private RamaHuffman izquierda, derecha;

    public Nodo(RamaHuffman izquierda, RamaHuffman derecha, int frecuencia) {
        super(frecuencia);
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    public RamaHuffman getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(RamaHuffman izquierda) {
        this.izquierda = izquierda;
    }

    public RamaHuffman getDerecha() {
        return derecha;
    }

    public void setDerecha(RamaHuffman derecha) {
        this.derecha = derecha;
    }
    
    
    
    
    
}
