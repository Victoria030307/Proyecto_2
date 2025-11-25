/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_hernandez_zarbo_abad.EDAux;

import proyecto2_hernandez_zarbo_abad.ArbolesAVL.ArbolAVLPalabrasClave;
import proyecto2_hernandez_zarbo_abad.ArbolesAVL.ArbolAVLAutores;

/**
 *
 * @author giovanni
 */  

import java.io.BufferedReader;
import java.io.FileReader;
import proyecto2_hernandez_zarbo_abad.TablasHash.TablaDeDispersion;
import proyecto2_hernandez_zarbo_abad.TablasHash.TablaPorPalabrasClave;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException; 

/**
 * Clase encargada de manejar la lectura de archivos de texto (.txt)
 * que contienen la información estructurada de un artículo.
 * Procesa el contenido y lo inserta en las estructuras de datos del sistema.
 */
public class FuncionesTXT {

    /**
     * Lee un archivo de texto en la dirección especificada, parsea su contenido
     * (título, autores, resumen, palabras clave) y lo inserta en las estructuras
     * de datos del repositorio (Tablas de Dispersión y Árboles AVL).
     *
     * @param tabladd La Tabla de Dispersión principal por título.
     * @param tablapc La Tabla de Dispersión indexada por palabras clave.
     * @param avlAutores El Árbol AVL para indexar autores.
     * @param avlPalabras El Árbol AVL para mantener el repositorio único de palabras clave.
     * @param direccion La ruta o dirección del archivo .txt a leer.
     */
    public void leerTXT(TablaDeDispersion tabladd, TablaPorPalabrasClave tablapc, ArbolAVLAutores avlAutores, ArbolAVLPalabrasClave avlPalabras, String direccion) {
        String linea;
        String titulo = "";
        String autores = "";
        String resumen = "";
        String palabras = "";
        int estado = 1; 

        try {
            FileReader fr = new FileReader(direccion);
            BufferedReader br = new BufferedReader(fr);
            while ((linea = br.readLine()) != null) {
                if (!linea.isEmpty()) {
                    if (linea.toLowerCase().equals("autores")) {
                        estado = 2;
                    } else if (linea.toLowerCase().equals("resumen")) {
                        estado = 3;
                    } else if (linea.toLowerCase().contains("palabras claves:")) {
                        palabras += linea.substring(linea.toLowerCase().indexOf("palabras claves:") + 16).trim();
                    } else if (estado == 1) {
                        titulo += linea.trim();
                    } else if (estado == 2) {
                        autores += linea.replace("-", "").trim() + "\n";
                    } else if (estado == 3) {
                        resumen += linea + " ";
                    }
                }
            }
            String autoresL[] = autores.split("\n");
            
            String[] palabrasL = palabras.split(",");
            for (int i = 0; i < palabrasL.length; i++) {
                palabrasL[i] = palabrasL[i].trim().replaceAll("\\.", "");
            }

            Articulo art = new Articulo(titulo.trim(), autoresL, resumen.trim(), palabrasL);
            
            if (tabladd.buscarPorTitulo(titulo.trim()) == null) {
                tabladd.insertar(art);
                tablapc.insertar(art);
                
                for (String autor : autoresL) {
                    avlAutores.insertar(autor.trim(), art);
                }
                
                for (String palabra : palabrasL) {
                    avlPalabras.insertar(palabra.trim());
                    // System.out.println(palabra); // Se elimina print
                }
            }
            
            br.close();

        } 
         catch (Exception ex) {
             ex.printStackTrace();
        }
    }
}