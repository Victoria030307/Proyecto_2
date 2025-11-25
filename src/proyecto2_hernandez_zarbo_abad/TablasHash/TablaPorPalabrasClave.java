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

public class TablaPorPalabrasClave {
    public ListaPalabrasClave[] listas;
    int total;
    
    public TablaPorPalabrasClave(){
        this.total = 1000;
        this.listas = new ListaPalabrasClave[this.total];
    }
    
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
    
    public void insertar(Articulo articulo) {
    for (String palabraClave : articulo.getPalabrasClaves()) { 
        String claveLimpia = palabraClave.trim().toLowerCase();
    System.out.println("-> Procesando palabra clave: " + claveLimpia);

        int hash = this.hash(claveLimpia);
        int inicio = hash;
        
        do {
            if (this.listas[hash] == null) {
                this.listas[hash] = new ListaPalabrasClave(claveLimpia);
                this.listas[hash].insertar(articulo);
                System.out.println("-> Insertada en índice: " + hash);
            break;
            } else if (this.listas[hash].getClave().equals(claveLimpia)) {
                this.listas[hash].insertar(articulo);
                System.out.println("-> Actualizada en índice: " + hash);
            break;
            }

            hash = (hash + 1) % this.total;
            
        } while (hash != inicio);
    } 
}
    
    public ListaPalabrasClave buscarPalabraClave(String palabra){
        String palabraBuscada = palabra.toLowerCase();
        int hash = this.hash(palabraBuscada);
        int inicio = hash;
        
        do {
        if (this.listas[hash] == null) {
            System.out.println("DEBUG: Posición " + hash + " es nula. Búsqueda fallida.");
            return null;
        } else {
            String claveAlmacenada = this.listas[hash].getClave();
            
            System.out.println("DEBUG: Comparando. Buscada: '" + palabraBuscada + "'. Almacenada: '" + claveAlmacenada + "'.");
            
            if (claveAlmacenada.equals(palabraBuscada)) {
                return this.listas[hash];
            }
        }
        
        hash = (hash + 1) % this.total;
    } while (hash != inicio);
        
        return null;
    }
    
    public void mostrarContenidoHash() {
        System.out.println("==========================================");
        System.out.println("  DEBUG: CONTENIDO DE TABLA POR PALABRAS CLAVE");
        System.out.println("==========================================");
        
        for (int i = 0; i < this.total; i++) {
            if (this.listas[i] != null && !this.listas[i].getClave().isEmpty()) {
                String clave = this.listas[i].getClave();
                int numArticulos = this.listas[i].getAgregados();
                
                System.out.println("Índice [" + i + "]: '" + clave + "' | Artículos: " + numArticulos);
            }
        }
        System.out.println("==========================================");
    }
}