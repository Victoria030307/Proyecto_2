/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_hernandez_zarbo_abad.ArbolesAVL;

/**
 *
 * @author gustavo
 */
public class NodoAVLPalabraClave {
    private String palabra;
    private int altura;
    private NodoAVLPalabraClave izquierda;
    private NodoAVLPalabraClave derecha;

    public NodoAVLPalabraClave(String palabra) {
        this.palabra = palabra;
        this.altura = 1;
        this.izquierda = null;
        this.derecha = null;
    }

    public String getPalabra() {
        return palabra;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public NodoAVLPalabraClave getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoAVLPalabraClave izquierda) {
        this.izquierda = izquierda;
    }

    public NodoAVLPalabraClave getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoAVLPalabraClave derecha) {
        this.derecha = derecha;
    }
}
