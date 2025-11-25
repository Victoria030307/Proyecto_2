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
 * Clase que implementa un Árbol AVL (Árbol Binario de Búsqueda Auto-Balanceado)
 * diseñado para almacenar y gestionar nodos de autores (NodoAVLAutor).
 *
 * La clave de búsqueda es el nombre del autor. Cada nodo almacena una lista
 * de artículos asociados a ese autor. El árbol asegura que las operaciones
 * de búsqueda, inserción y listado sean eficientes manteniendo el balance.
 */
public class ArbolAVLAutores {

    private NodoAVLAutor raiz;

    /**
     * Calcula la altura de un nodo.
     * @param nodo El nodo del cual se quiere obtener la altura.
     * @return La altura del nodo (0 si es nulo).
     */
    private int altura(NodoAVLAutor nodo) {
        return nodo == null ? 0 : nodo.getAltura();
    }

    /**
     * Obtiene el factor de equilibrio de un nodo (altura de la izquierda - altura de la derecha).
     * @param nodo El nodo del cual se quiere obtener el factor de equilibrio.
     * @return El factor de equilibrio del nodo (0 si es nulo).
     */
    private int obtenerFactorEquilibrio(NodoAVLAutor nodo) {
        return nodo == null ? 0 : altura(nodo.getIzquierda()) - altura(nodo.getDerecha());
    }

    /**
     * Actualiza la altura de un nodo basándose en la altura máxima de sus hijos.
     * @param nodo El nodo cuya altura debe ser actualizada.
     */
    private void actualizarAltura(NodoAVLAutor nodo) {
        nodo.setAltura(1 + Math.max(altura(nodo.getIzquierda()), altura(nodo.getDerecha())));
    }

    /**
     * Realiza una rotación simple a la derecha para reequilibrar el árbol.
     * Se usa en el caso "Izquierda-Izquierda".
     * @param y La raíz del subárbol desbalanceado (el nodo a rotar hacia abajo).
     * @return La nueva raíz del subárbol (el hijo izquierdo 'x').
     */
    private NodoAVLAutor rotacionDerecha(NodoAVLAutor y) {
        NodoAVLAutor x = y.getIzquierda();
        NodoAVLAutor T2 = x.getDerecha();

        x.setDerecha(y);
        y.setIzquierda(T2);

        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }

    /**
     * Realiza una rotación simple a la izquierda para reequilibrar el árbol.
     * Se usa en el caso "Derecha-Derecha".
     * @param x La raíz del subárbol desbalanceado (el nodo a rotar hacia abajo).
     * @return La nueva raíz del subárbol (el hijo derecho 'y').
     */
    private NodoAVLAutor rotacionIzquierda(NodoAVLAutor x) {
        NodoAVLAutor y = x.getDerecha();
        NodoAVLAutor T2 = y.getIzquierda();

        y.setIzquierda(x);
        x.setDerecha(T2);

        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }

    /**
     * Inserta un autor y un artículo asociado en el árbol. Si el autor ya existe,
     * simplemente agrega el artículo a la lista de ese autor.
     * @param autor El nombre del autor a insertar o buscar.
     * @param articulo El artículo a asociar con el autor.
     */
    public void insertar(String autor, Articulo articulo) {
        if (this.buscarNodo(autor) == null) {
            this.raiz = insertarRecursivo(this.raiz, autor, articulo);
        }
    }

    /**
     * Función recursiva que inserta un nuevo autor/artículo y reequilibra el nodo si es necesario.
     * @param nodo La raíz del subárbol actual.
     * @param autor El nombre del autor a insertar.
     * @param articulo El artículo a asociar.
     * @return El nodo raíz del subárbol, posiblemente reequilibrado.
     */
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

    /**
     * Busca un nodo de autor dentro del árbol.
     * @param autor El nombre del autor a buscar.
     * @return El NodoAVLAutor si se encuentra, o null si no existe.
     */
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

    /**
     * Busca todos los artículos escritos por un autor específico.
     * @param autor El nombre del autor a buscar.
     * @return Un array de Articulo[] que contiene todos los artículos del autor, o un array vacío si el autor no se encuentra.
     */
    public Articulo[] buscarPorAutor(String autor) {
        NodoAVLAutor nodoAutor = buscarNodo(autor);
        if (nodoAutor != null) {
            return nodoAutor.getArticulos();
        }
        return new Articulo[0];
    }

    /**
     * Lista todos los autores presentes en el árbol en orden alfabético.
     * @return Un array de String[] que contiene los nombres de todos los autores.
     */
    public String[] listarAutores() {
        String[] autores = new String[contarNodos(this.raiz)];
        listarInOrder(this.raiz, autores, new int[]{0});
        return autores;
    }

    /**
     * Cuenta de forma recursiva la cantidad total de nodos (autores) en el subárbol dado.
     * @param nodo La raíz del subárbol a contar.
     * @return El número total de nodos.
     */
    private int contarNodos(NodoAVLAutor nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodos(nodo.getIzquierda()) + contarNodos(nodo.getDerecha());
    }

    /**
     * Realiza un recorrido In-Order (izquierda, raíz, derecha) en el árbol
     * para obtener los autores en orden alfabético y almacenarlos en el array.
     * @param nodo La raíz del subárbol actual.
     * @param lista El array donde se almacenarán los nombres de los autores.
     * @param indice Array de un solo elemento usado para mantener el índice de inserción actual.
     */
    private void listarInOrder(NodoAVLAutor nodo, String[] lista, int[] indice) {
        if (nodo != null) {
            listarInOrder(nodo.getIzquierda(), lista, indice);
            lista[indice[0]++] = nodo.getAutor();
            listarInOrder(nodo.getDerecha(), lista, indice);
        }
    }
}