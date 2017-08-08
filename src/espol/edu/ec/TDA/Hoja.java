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
public class Hoja extends RamaHuffman {
    private String valor;
    
    public Hoja(int frecuencia, String valor) {
        super(frecuencia);
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
    
}
