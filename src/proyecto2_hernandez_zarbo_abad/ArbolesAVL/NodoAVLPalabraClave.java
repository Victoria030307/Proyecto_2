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
 * Representa un nodo dentro de un Árbol AVL de palabras clave.
 * Almacena la palabra clave (String) y la información de altura y referencias
 * necesaria para el balanceo del árbol AVL.
 */
public class NodoAVLPalabraClave {
    private String palabra;
    private int altura;
    private NodoAVLPalabraClave izquierda;
    private NodoAVLPalabraClave derecha;

    /**
     * Constructor para crear un nuevo nodo de palabra clave.
     * Inicializa la altura a 1 y las referencias a nulo.
     * @param palabra La palabra clave a almacenar.
     */
    public NodoAVLPalabraClave(String palabra) {
        this.palabra = palabra;
        this.altura = 1;
        this.izquierda = null;
        this.derecha = null;
    }

    /**
     * Obtiene la palabra clave almacenada en el nodo.
     * @return La palabra clave.
     */
    public String getPalabra() {
        return palabra;
    }

    /**
     * Obtiene la altura actual del nodo en el árbol AVL.
     * @return La altura del nodo.
     */
    public int getAltura() {
        return altura;
    }

    /**
     * Establece la altura actual del nodo.
     * @param altura La nueva altura del nodo.
     */
    public void setAltura(int altura) {
        this.altura = altura;
    }

    /**
     * Obtiene el nodo hijo izquierdo.
     * @return El NodoAVLPalabraClave de la izquierda.
     */
    public NodoAVLPalabraClave getIzquierda() {
        return izquierda;
    }

    /**
     * Establece el nodo hijo izquierdo.
     * @param izquierda El nuevo NodoAVLPalabraClave izquierdo.
     */
    public void setIzquierda(NodoAVLPalabraClave izquierda) {
        this.izquierda = izquierda;
    }

    /**
     * Obtiene el nodo hijo derecho.
     * @return El NodoAVLPalabraClave de la derecha.
     */
    public NodoAVLPalabraClave getDerecha() {
        return derecha;
    }

    /**
     * Establece el nodo hijo derecho.
     * @param derecha El nuevo NodoAVLPalabraClave derecho.
     */
    public void setDerecha(NodoAVLPalabraClave derecha) {
        this.derecha = derecha;
    }
}