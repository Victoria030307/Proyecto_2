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
public class ArbolAVLAutores {

    private NodoAVLAutor raiz;

    private int altura(NodoAVLAutor nodo) {
        return nodo == null ? 0 : nodo.getAltura();
    }

    private int obtenerFactorEquilibrio(NodoAVLAutor nodo) {
        return nodo == null ? 0 : altura(nodo.getIzquierda()) - altura(nodo.getDerecha());
    }

    private void actualizarAltura(NodoAVLAutor nodo) {
        nodo.setAltura(1 + Math.max(altura(nodo.getIzquierda()), altura(nodo.getDerecha())));
    }

    private NodoAVLAutor rotacionDerecha(NodoAVLAutor y) {
        NodoAVLAutor x = y.getIzquierda();
        NodoAVLAutor T2 = x.getDerecha();

        x.setDerecha(y);
        y.setIzquierda(T2);

        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }

    private NodoAVLAutor rotacionIzquierda(NodoAVLAutor x) {
        NodoAVLAutor y = x.getDerecha();
        NodoAVLAutor T2 = y.getIzquierda();

        y.setIzquierda(x);
        x.setDerecha(T2);

        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }

    public void insertar(String autor, Articulo articulo) {
        if (this.buscarNodo(autor) == null) {
            this.raiz = insertarRecursivo(this.raiz, autor, articulo);

        }
    }

    private NodoAVLAutor insertarRecursivo(NodoAVLAutor nodo, String autor, Articulo articulo) {
        if (nodo == null) {
            return new NodoAVLAutor(autor, articulo);
        }

        int comparacion = autor.compareTo(nodo.getAutor());

        if (comparacion < 0) {
            nodo.setIzquierda(insertarRecursivo(nodo.getIzquierda(), autor, articulo));
        } else if (comparacion > 0) {
            nodo.setDerecha(insertarRecursivo(nodo.getDerecha(), autor, articulo));
        } else {
            nodo.agregarArticulo(articulo);
            return nodo;
        }

        actualizarAltura(nodo);

        int balance = obtenerFactorEquilibrio(nodo);

        if (balance > 1 && autor.compareTo(nodo.getIzquierda().getAutor()) < 0) {
            return rotacionDerecha(nodo);
        }

        if (balance < -1 && autor.compareTo(nodo.getDerecha().getAutor()) > 0) {
            return rotacionIzquierda(nodo);
        }

        if (balance > 1 && autor.compareTo(nodo.getIzquierda().getAutor()) > 0) {
            nodo.setIzquierda(rotacionIzquierda(nodo.getIzquierda()));
            return rotacionDerecha(nodo);
        }

        if (balance < -1 && autor.compareTo(nodo.getDerecha().getAutor()) < 0) {
            nodo.setDerecha(rotacionDerecha(nodo.getDerecha()));
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    private NodoAVLAutor buscarNodo(String autor) {
        NodoAVLAutor actual = this.raiz;
        while (actual != null) {
            int comparacion = autor.trim().compareTo(actual.getAutor().trim());
            if (comparacion == 0) {
                return actual;
            } else if (comparacion < 0) {
                actual = actual.getIzquierda();
            } else {
                actual = actual.getDerecha();
            }
        }
        return null;
    }

    public Articulo[] buscarPorAutor(String autor) {
        NodoAVLAutor nodoAutor = buscarNodo(autor);
        if (nodoAutor != null) {
            return nodoAutor.getArticulos();
        }
        return new Articulo[0];
    }

    public String[] listarAutores() {
        String[] autores = new String[contarNodos(this.raiz)];
        listarInOrder(this.raiz, autores, new int[]{0});
        return autores;
    }

    private int contarNodos(NodoAVLAutor nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodos(nodo.getIzquierda()) + contarNodos(nodo.getDerecha());
    }

    private void listarInOrder(NodoAVLAutor nodo, String[] lista, int[] indice) {
        if (nodo != null) {
            listarInOrder(nodo.getIzquierda(), lista, indice);
            lista[indice[0]++] = nodo.getAutor();
            listarInOrder(nodo.getDerecha(), lista, indice);
        }
    }
}
