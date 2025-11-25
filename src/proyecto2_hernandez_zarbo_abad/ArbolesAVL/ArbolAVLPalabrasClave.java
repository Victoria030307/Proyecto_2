/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_hernandez_zarbo_abad.ArbolesAVL;

/**
 *
 * @author gustavo 
 */
public class ArbolAVLPalabrasClave {
    private NodoAVLPalabraClave raiz;
    
    private int altura(NodoAVLPalabraClave nodo) {
        return nodo == null ? 0 : nodo.getAltura();
    }

    private int obtenerFactorEquilibrio(NodoAVLPalabraClave nodo) {
        return nodo == null ? 0 : altura(nodo.getIzquierda()) - altura(nodo.getDerecha());
    }

    private void actualizarAltura(NodoAVLPalabraClave nodo) {
        nodo.setAltura(1 + Math.max(altura(nodo.getIzquierda()), altura(nodo.getDerecha())));
    }
    
    private NodoAVLPalabraClave rotacionDerecha(NodoAVLPalabraClave y) {
        NodoAVLPalabraClave x = y.getIzquierda();
        NodoAVLPalabraClave T2 = x.getDerecha();

        x.setDerecha(y);
        y.setIzquierda(T2);

        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }

    private NodoAVLPalabraClave rotacionIzquierda(NodoAVLPalabraClave x) {
        NodoAVLPalabraClave y = x.getDerecha();
        NodoAVLPalabraClave T2 = y.getIzquierda();

        y.setIzquierda(x);
        x.setDerecha(T2);

        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }
    
    public void insertar(String palabra) {
        
        this.raiz = insertarRecursivo(this.raiz, palabra.toLowerCase());
    }
    
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

    public String[] listarPalabrasClaves() {
        int conta = contarNodos(this.raiz);
        String[] palabras = new String[conta];
        listarInOrder(this.raiz, palabras, new int[]{0});
        return palabras;
    }
    
    private int contarNodos(NodoAVLPalabraClave nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodos(nodo.getIzquierda()) + contarNodos(nodo.getDerecha());
    }

    private void listarInOrder(NodoAVLPalabraClave nodo, String[] lista, int[] indice) {
        if (nodo != null) {
            listarInOrder(nodo.getIzquierda(), lista, indice);
            lista[indice[0]++] = nodo.getPalabra();
            listarInOrder(nodo.getDerecha(), lista, indice);
        }
    }
}