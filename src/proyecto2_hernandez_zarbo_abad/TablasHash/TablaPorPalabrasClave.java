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
    // Utilizamos un número primo grande (ej. 31) y sumamos el valor ASCII
    for (int i = 0; i < titulo.length(); i++) {
        // Multiplicación y suma para generar el hash
        indiceHash = indiceHash * 31 + titulo.toLowerCase().charAt(i);
    }
    
    // Aplicamos el módulo para obtener el índice.
    // Usamos esta expresión para garantizar que el resultado siempre sea positivo.
    int indice = (int) (indiceHash % this.total);
    
    // Si el resultado de la operación anterior fue negativo (debido a que hashValue superó los límites de long/int 
    // y se convirtió en negativo, lo cual es común en estas funciones), lo corregimos.
    if (indice < 0) {
        indice += this.total;
    }
    
    return indice;
}
    
    public void insertar(Articulo articulo) {
    // ESTE ES EL BUCLE CRÍTICO
    for (String palabraClave : articulo.getPalabrasClaves()) { 
        String claveLimpia = palabraClave.trim().toLowerCase();
        // DEBUG: Muestra qué palabra se está procesando
    System.out.println("-> Procesando palabra clave: " + claveLimpia);
        // 1. Calcular el hash para la clave limpia
        int hash = this.hash(claveLimpia);
        int inicio = hash;
        
        // 2. Sondaje Lineal para encontrar un lugar
        do {
            if (this.listas[hash] == null) {
                // Posición vacía: Insertar nueva ListaPalabrasClave
                this.listas[hash] = new ListaPalabrasClave(claveLimpia);
                this.listas[hash].insertar(articulo);
                System.out.println("-> Insertada en índice: " + hash);
            break;
            } else if (this.listas[hash].getClave().equals(claveLimpia)) {
                // Clave ya existe: Añadir el artículo
                this.listas[hash].insertar(articulo);
                System.out.println("-> Actualizada en índice: " + hash);
            break;
            }
            
            // Pasar a la siguiente posición (Sondaje Lineal)
            hash = (hash + 1) % this.total;
            
        } while (hash != inicio);
    } // <-- El bucle 'for' debería continuar aquí con la siguiente palabra
}
    
    // Requerimiento 3: Buscar Investigaciones por palabra clave (O(1))
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
            
            // Inspección crítica
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
                
                // Muestra el índice (posición real) y la palabra almacenada
                System.out.println("Índice [" + i + "]: '" + clave + "' | Artículos: " + numArticulos);
            }
        }
        System.out.println("==========================================");
    }
}