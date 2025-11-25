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
/** 
 * Representa un nodo dentro de un Árbol AVL de autores.
 * Cada nodo almacena el nombre de un autor y una colección dinámica de artículos
 * asociados a ese autor. También contiene la información necesaria para el
 * balanceo del árbol (altura y referencias a hijos).
 */
public class NodoAVLAutor {
    private String autor;
    private Articulo[] articulos;
    private int agregados;
    private int altura;
    private NodoAVLAutor izquierda;
    private NodoAVLAutor derecha;

    /**
     * Constructor para crear un nuevo nodo de autor con un primer artículo.
     * Inicializa el array de artículos, la altura y las referencias a nulo.
     * @param autor El nombre del autor para este nodo.
     * @param articulo El primer artículo asociado a este autor.
     */
    public NodoAVLAutor(String autor, Articulo articulo) {
        this.autor = autor;
        this.articulos = new Articulo[5];
        this.articulos[0] = articulo;
        this.agregados = 1;
        this.altura = 1;
        this.izquierda = null;
        this.derecha = null;
    }

    /**
     * Agrega un artículo a la lista de artículos del autor.
     * Si el array interno está lleno, lo redimensiona al doble de su tamaño.
     * @param articulo El artículo a agregar.
     */
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

    /**
     * Obtiene el nombre del autor almacenado en el nodo.
     * @return El nombre del autor.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Obtiene una copia del array de artículos asociados a este autor.
     * El array devuelto tiene el tamaño exacto de la cantidad de artículos agregados.
     * @return Array de Articulo[] del autor.
     */
    public Articulo[] getArticulos() {
        Articulo[] resultado = new Articulo[agregados];
        for (int i = 0; i < agregados; i++) {
            resultado[i] = articulos[i];
        }
        return resultado;
    }

    /**
     * Obtiene la altura actual del nodo en el árbol AVL.
     * @return La altura del nodo.
     */
    public int getAltura() {
        return altura;
    }

    /**
     * Establece la altura actual del nodo.
     * @param altura La nueva altura del nodo.
     */
    public void setAltura(int altura) {
        this.altura = altura;
    }

    /**
     * Obtiene el nodo hijo izquierdo.
     * @return El NodoAVLAutor de la izquierda.
     */
    public NodoAVLAutor getIzquierda() {
        return izquierda;
    }

    /**
     * Establece el nodo hijo izquierdo.
     * @param izquierda El nuevo NodoAVLAutor izquierdo.
     */
    public void setIzquierda(NodoAVLAutor izquierda) {
        this.izquierda = izquierda;
    }

    /**
     * Obtiene el nodo hijo derecho.
     * @return El NodoAVLAutor de la derecha.
     */
    public NodoAVLAutor getDerecha() {
        return derecha;
    }

    /**
     * Establece el nodo hijo derecho.
     * @param derecha El nuevo NodoAVLAutor derecho.
     */
    public void setDerecha(NodoAVLAutor derecha) {
        this.derecha = derecha;
    }
}