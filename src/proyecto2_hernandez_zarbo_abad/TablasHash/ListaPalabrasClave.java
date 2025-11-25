/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_hernandez_zarbo_abad.TablasHash;

import proyecto2_hernandez_zarbo_abad.EDAux.Articulo;

/**
 *
 * @author victoria
 */

/**
 * Estructura de datos que actúa como la "cubeta" de la tabla de dispersión
 * indexada por palabras clave. Almacena la palabra clave y una colección
 * dinámica de artículos asociados a ella.
 */
public class ListaPalabrasClave {
    private String clave;
    private Articulo[] articulos;
    private int agregados;
    
    /**
     * Constructor que inicializa la lista para una palabra clave dada.
     * Inicializa el array de artículos con un tamaño inicial.
     * @param clave La palabra clave que esta lista representa.
     */
    public ListaPalabrasClave(String clave){
        this.clave = clave;
        this.articulos = new Articulo[10];
        this.agregados = 0;
    }
    
    /**  
     * Inserta un artículo en la lista. Si el array interno está lleno,
     * lo redimensiona (duplica su tamaño) para acomodar más artículos.
     * @param articulo El artículo a agregar a la lista.
     */
    public void insertar(Articulo articulo){
        if(agregados == articulos.length){
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
     * Obtiene la palabra clave que identifica a esta lista.
     * @return La palabra clave.
     */
    public String getClave() {
        return clave;
    }

    /**
     * Obtiene un array que contiene todos los artículos asociados a esta palabra clave.
     * @return Array de Articulo[] con los artículos agregados.
     */
    public Articulo[] getArticulos() {
        Articulo[] resultado = new Articulo[agregados];
        for (int i = 0; i < agregados; i++) {
            resultado[i] = articulos[i];
        }
        return resultado;
    }
    
    /**
     * Obtiene el número total de artículos agregados a esta lista.
     * @return El número de artículos agregados.
     */
    public int getAgregados(){
        return this.agregados;
    }
}