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
public class TablaDeDispersion {

    public Articulo[] articulos;
    public int total;

    public TablaDeDispersion() {
        this.total = 1000;
        this.articulos = new Articulo[this.total];
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
        String tituloArticulo = articulo.getTitulo().toLowerCase();
        int hash = this.hash(tituloArticulo);
        int inicio = hash;

        do {
            if (this.articulos[hash] == null) {
                this.articulos[hash] = articulo;
                return;
            } else if (this.articulos[hash].getTitulo().toLowerCase().equals(tituloArticulo)) {
                return;
            }

            hash = (hash + 1) % this.total;
        } while (hash != inicio);
    }

    public Articulo buscarPorTitulo(String titulo) {
        String tituloBuscado = titulo.toLowerCase();
        int hash = this.hash(tituloBuscado);
        int inicio = hash;

        do {
            if (this.articulos[hash] == null) {
                return null;
            } else if (this.articulos[hash].getTitulo().toLowerCase().equals(tituloBuscado)) {
                return this.articulos[hash];
            }

            hash = (hash + 1) % this.total;
        } while (hash != inicio);

        return null;
    }

    // Requerimiento 2.a: Listar títulos guardados (O(n))
    public String[] listarTitulos() {
        int conta = 0;
        for (int i = 0; i < this.total; i++) {
            if (this.articulos[i] != null) {
                conta++;
            }
        }

        String[] titulos = new String[conta];
        int indice = 0;
        for (int i = 0; i < this.total; i++) {
            if (this.articulos[i] != null) {
                titulos[indice] = this.articulos[i].getTitulo();
                indice++;
            }
        }

        // Se debe ordenar alfabéticamente aquí. Se requiere un algoritmo de ordenamiento O(n^2) o O(n log n).
        // Si no se puede usar ninguna librería, se debe implementar un algoritmo de ordenamiento (ej: MergeSort o QuickSort para O(n log n)).
        // Asumiendo que esta es la lista que se presenta a la interfaz para que el usuario seleccione.
        // Implementación simple de Bubble Sort (O(n^2)) para evitar dependencias, aunque Quicksort/Mergesort serían mejores.
        for (int i = 0; i < conta - 1; i++) {
            for (int j = 0; j < conta - i - 1; j++) {
                if (titulos[j].compareTo(titulos[j + 1]) > 0) {
                    String temp = titulos[j];
                    titulos[j] = titulos[j + 1];
                    titulos[j + 1] = temp;
                }
            }
        }

        return titulos;
    }
}
