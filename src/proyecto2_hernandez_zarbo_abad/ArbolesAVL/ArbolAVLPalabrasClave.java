/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_hernandez_zarbo_abad.ArbolesAVL;

/**
 *
 * @author gustavo 
 */
/**
 * Clase que implementa un Árbol AVL (Árbol Binario de Búsqueda Auto-Balanceado)
 * diseñado para almacenar y gestionar palabras clave únicas.
 *
 * La clave de búsqueda es la propia palabra clave (String). Este árbol se utiliza
 * para mantener un repositorio de palabras clave en orden alfabético y con
 * operaciones de inserción eficientes.
 */
public class ArbolAVLPalabrasClave {
    private NodoAVLPalabraClave raiz;

    /**
     * Calcula la altura de un nodo.
     * @param nodo El nodo del cual se quiere obtener la altura.
     * @return La altura del nodo (0 si es nulo).
     */
    private int altura(NodoAVLPalabraClave nodo) {
        return nodo == null ? 0 : nodo.getAltura();
    }

    /**
     * Obtiene el factor de equilibrio de un nodo (altura de la izquierda - altura de la derecha).
     * @param nodo El nodo del cual se quiere obtener el factor de equilibrio.
     * @return El factor de equilibrio del nodo (0 si es nulo).
     */
    private int obtenerFactorEquilibrio(NodoAVLPalabraClave nodo) {
        return nodo == null ? 0 : altura(nodo.getIzquierda()) - altura(nodo.getDerecha());
    }

    /**
     * Actualiza la altura de un nodo basándose en la altura máxima de sus hijos.
     * @param nodo El nodo cuya altura debe ser actualizada.
     */
    private void actualizarAltura(NodoAVLPalabraClave nodo) {
        nodo.setAltura(1 + Math.max(altura(nodo.getIzquierda()), altura(nodo.getDerecha())));
    }

    /**
     * Realiza una rotación simple a la derecha para reequilibrar el árbol.
     * Se usa en el caso "Izquierda-Izquierda".
     * @param y La raíz del subárbol desbalanceado (el nodo a rotar hacia abajo).
     * @return La nueva raíz del subárbol (el hijo izquierdo 'x').
     */
    private NodoAVLPalabraClave rotacionDerecha(NodoAVLPalabraClave y) {
        NodoAVLPalabraClave x = y.getIzquierda();
        NodoAVLPalabraClave T2 = x.getDerecha();

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
    private NodoAVLPalabraClave rotacionIzquierda(NodoAVLPalabraClave x) {
        NodoAVLPalabraClave y = x.getDerecha();
        NodoAVLPalabraClave T2 = y.getIzquierda();

        y.setIzquierda(x);
        x.setDerecha(T2);

        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }

    /**
     * Inserta una palabra clave en el árbol, convirtiéndola a minúsculas para asegurar la unicidad.
     * Si la palabra ya existe, no se realiza ninguna acción.
     * @param palabra La palabra clave a insertar.
     */
    public void insertar(String palabra) {
        this.raiz = insertarRecursivo(this.raiz, palabra.toLowerCase());
    }

    /**
     * Función recursiva que inserta una nueva palabra clave y reequilibra el nodo si es necesario.
     * @param nodo La raíz del subárbol actual.
     * @param palabra La palabra clave a insertar (ya en minúsculas).
     * @return El nodo raíz del subárbol, posiblemente reequilibrado.
     */
    private NodoAVLPalabraClave insertarRecursivo(NodoAVLPalabraClave nodo, String palabra) {
        if (nodo == null) {
            return new NodoAVLPalabraClave(palabra);
        }

        int comparacion = palabra.compareTo(nodo.getPalabra());

        if (comparacion < 0) {
            nodo.setIzquierda(insertarRecursivo(nodo.getIzquierda(), palabra));
        } else if (comparacion > 0) {
            nodo.setDerecha(insertarRecursivo(nodo.getDerecha(), palabra));
        } else {
            return nodo;
        }

        actualizarAltura(nodo);

        int balance = obtenerFactorEquilibrio(nodo);

        if (balance > 1 && palabra.compareTo(nodo.getIzquierda().getPalabra()) < 0) {
            return rotacionDerecha(nodo);
        }

        if (balance < -1 && palabra.compareTo(nodo.getDerecha().getPalabra()) > 0) {
            return rotacionIzquierda(nodo);
        }

        if (balance > 1 && palabra.compareTo(nodo.getIzquierda().getPalabra()) > 0) {
            nodo.setIzquierda(rotacionIzquierda(nodo.getIzquierda()));
            return rotacionDerecha(nodo);
        }

        if (balance < -1 && palabra.compareTo(nodo.getDerecha().getPalabra()) < 0) {
            nodo.setDerecha(rotacionDerecha(nodo.getDerecha()));
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    /**
     * Lista todas las palabras clave presentes en el árbol en orden alfabético (In-Order).
     * @return Un array de String[] que contiene todas las palabras clave.
     */
    public String[] listarPalabrasClaves() {
        int conta = contarNodos(this.raiz);
        String[] palabras = new String[conta];
        listarInOrder(this.raiz, palabras, new int[]{0});
        return palabras;
    }

    /**
     * Cuenta de forma recursiva la cantidad total de nodos (palabras clave) en el subárbol dado.
     * @param nodo La raíz del subárbol a contar.
     * @return El número total de nodos.
     */
    private int contarNodos(NodoAVLPalabraClave nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodos(nodo.getIzquierda()) + contarNodos(nodo.getDerecha());
    }

    /**
     * Realiza un recorrido In-Order (izquierda, raíz, derecha) en el árbol
     * para obtener las palabras clave en orden alfabético y almacenarlas en el array.
     * @param nodo La raíz del subárbol actual.
     * @param lista El array donde se almacenarán las palabras clave.
     * @param indice Array de un solo elemento usado para mantener el índice de inserción actual.
     */
    private void listarInOrder(NodoAVLPalabraClave nodo, String[] lista, int[] indice) {
        if (nodo != null) {
            listarInOrder(nodo.getIzquierda(), lista, indice);
            lista[indice[0]++] = nodo.getPalabra();
            listarInOrder(nodo.getDerecha(), lista, indice);
        }
    }
}