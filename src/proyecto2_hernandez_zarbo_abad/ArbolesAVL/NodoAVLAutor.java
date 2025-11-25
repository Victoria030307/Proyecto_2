/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_hernandez_zarbo_abad.ArbolesAVL;

import proyecto2_hernandez_zarbo_abad.EDAux.Articulo;

/**
 *
 * @author gustavo
 */
public class NodoAVLAutor {
    private String autor;
    private Articulo[] articulos;
    private int agregados;
    private int altura;
    private NodoAVLAutor izquierda;
    private NodoAVLAutor derecha;

    public NodoAVLAutor(String autor, Articulo articulo) {
        this.autor = autor;
        this.articulos = new Articulo[5];
        this.articulos[0] = articulo;
        this.agregados = 1;
        this.altura = 1;
        this.izquierda = null;
        this.derecha = null;
    }
    
    public void agregarArticulo(Articulo articulo) {
        if (agregados == articulos.length) {
            Articulo[] nuevoArreglo = new Articulo[this.articulos.length * 2];
            for (int i = 0; i < this.articulos.length; i++) {
                nuevoArreglo[i] = this.articulos[i];
            }
            this.articulos = nuevoArreglo;
        }
        this.articulos[this.agregados] = articulo;
        this.agregados++;
    }

    public String getAutor() {
        return autor;
    }

    public Articulo[] getArticulos() {
        Articulo[] resultado = new Articulo[agregados];
        for (int i = 0; i < agregados; i++) {
            resultado[i] = articulos[i];
        }
        return resultado;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public NodoAVLAutor getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoAVLAutor izquierda) {
        this.izquierda = izquierda;
    }

    public NodoAVLAutor getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoAVLAutor derecha) {
        this.derecha = derecha;
    }
}