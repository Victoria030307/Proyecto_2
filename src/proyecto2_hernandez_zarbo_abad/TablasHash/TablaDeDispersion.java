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
