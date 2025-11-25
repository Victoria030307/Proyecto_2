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

public class ListaPalabrasClave {
    private String clave;
    private Articulo[] articulos;
    private int agregados;
    
    public ListaPalabrasClave(String clave){
        this.clave = clave;
        this.articulos = new Articulo[10]; 
        this.agregados = 0;
    }
    
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

    public String getClave() {
        return clave;
    }

    public Articulo[] getArticulos() {
        // Devuelve solo los artículos que se han agregado (los no nulos)
        Articulo[] resultado = new Articulo[agregados];
        for (int i = 0; i < agregados; i++) {
            resultado[i] = articulos[i];
        }
        return resultado;
    }
    
    public int getAgregados(){
        return this.agregados;
    }
}
