/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_hernandez_zarbo_abad.TablasHash;

import proyecto2_hernandez_zarbo_abad.TablasHash.ListaPalabrasClave;
import proyecto2_hernandez_zarbo_abad.EDAux.Articulo;

/**
 * 
 * @author victoria 
 */

/**
 * Implementa una Tabla de Dispersión (Hash Table) diseñada para indexar
 * artículos por sus palabras clave. Cada posición de la tabla almacena una
 * lista de artículos asociados a una palabra clave específica (ListaPalabrasClave).
 */
public class TablaPorPalabrasClave {
    public ListaPalabrasClave[] listas;
    int total;
    
    /**
     * Constructor que inicializa la tabla de dispersión con un tamaño fijo (1000).
     */
    public TablaPorPalabrasClave(){
        this.total = 1000;
        this.listas = new ListaPalabrasClave[this.total];
    }
    
    /**
     * Calcula el índice hash para una palabra clave dada.
     * @param titulo La palabra clave a hashear.
     * @return El índice calculado dentro de los límites del array.
     */
    public int hash(String titulo) {
        long indiceHash = 0;
        for (int i = 0; i < titulo.length(); i++) {
            indiceHash = indiceHash * 31 + titulo.toLowerCase().charAt(i);
        }
        int indice = (int) (indiceHash % this.total);
        if (indice < 0) {
            indice += this.total;
        }
        
        return indice;
    }
    
    /**
     * Inserta un artículo indexándolo bajo todas sus palabras clave.
     * Utiliza sondaje lineal para resolver colisiones.
     * @param articulo El objeto Articulo a insertar.
     */
    public void insertar(Articulo articulo) {
        for (String palabraClave : articulo.getPalabrasClaves()) {
            String claveLimpia = palabraClave.trim().toLowerCase();
            // System.out.println("-> Procesando palabra clave: " + claveLimpia); // Se elimina print
            int hash = this.hash(claveLimpia);
            int inicio = hash;
            do {
                if (this.listas[hash] == null) {
                    this.listas[hash] = new ListaPalabrasClave(claveLimpia);
                    this.listas[hash].insertar(articulo);
                    // System.out.println("-> Insertada en índice: " + hash); // Se elimina print
                    break;
                } else if (this.listas[hash].getClave().equals(claveLimpia)) {
                    this.listas[hash].insertar(articulo);
                    // System.out.println("-> Actualizada en índice: " + hash); // Se elimina print
                    break;
                }
                hash = (hash + 1) % this.total;
                
            } while (hash != inicio);
        }
    }
    
    /**
     * Busca la lista de artículos asociada a una palabra clave específica.
     * @param palabra La palabra clave a buscar.
     * @return El objeto ListaPalabrasClave si se encuentra, o null si no existe.
     */
    public ListaPalabrasClave buscarPalabraClave(String palabra){
        String palabraBuscada = palabra.toLowerCase();
        int hash = this.hash(palabraBuscada);
        int inicio = hash;
        
        do {
            if (this.listas[hash] == null) {
                return null;
            } else {
                String claveAlmacenada = this.listas[hash].getClave();
                if (claveAlmacenada.equals(palabraBuscada)) {
                    return this.listas[hash];
                }
            }
            
            hash = (hash + 1) % this.total;
        } while (hash != inicio);
        
        return null;
    }
    
}