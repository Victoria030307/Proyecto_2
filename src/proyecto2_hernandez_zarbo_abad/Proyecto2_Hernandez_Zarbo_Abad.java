/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2_hernandez_zarbo_abad;

import proyecto2_hernandez_zarbo_abad.TablasHash.TablaPorPalabrasClave;
import proyecto2_hernandez_zarbo_abad.TablasHash.TablaDeDispersion;
import proyecto2_hernandez_zarbo_abad.TablasHash.ListaPalabrasClave;
import proyecto2_hernandez_zarbo_abad.EDAux.Articulo;
import proyecto2_hernandez_zarbo_abad.ArbolesAVL.ArbolAVLPalabrasClave;
import proyecto2_hernandez_zarbo_abad.ArbolesAVL.ArbolAVLAutores;
import proyecto2_hernandez_zarbo_abad.EDAux.AnalizadorResumen;
import proyecto2_hernandez_zarbo_abad.Ventanas.VentanaInicio;

/**
 *
 * @author victoria
 */
public class Proyecto2_Hernandez_Zarbo_Abad {

    /**
     * @param args the command line arguments
     */
    
    public static void simularCargaArticulo(String titulo, String[] autores, String resumen, String[] palabrasClaves,
                                            TablaDeDispersion tabladd, TablaPorPalabrasClave tablapc, 
                                            ArbolAVLAutores avlAutores, ArbolAVLPalabrasClave avlPalabras) {
        
        Articulo nuevoArticulo = new Articulo(titulo, autores, resumen, palabrasClaves);
        
        if (tabladd.buscarPorTitulo(titulo) == null) {
            System.out.println("✅ Insertando: " + titulo);
            tabladd.insertar(nuevoArticulo);
            tablapc.insertar(nuevoArticulo);
            
            for (String autor : autores) {
                avlAutores.insertar(autor.trim(), nuevoArticulo);
            }
            
            for (String palabra : palabrasClaves) {
                avlPalabras.insertar(palabra.trim());
            }
        } else {
            System.out.println("❌ Resumen Duplicado: " + titulo + " (No insertado)");
        }
    }
        
    
    public static void main(String[] args) {

        // 1. Inicialización de Estructuras de Datos
        TablaDeDispersion tabladd = new TablaDeDispersion();
        TablaPorPalabrasClave tablapc = new TablaPorPalabrasClave();
        ArbolAVLAutores avlAutores = new ArbolAVLAutores();
        ArbolAVLPalabrasClave avlPalabras = new ArbolAVLPalabrasClave();
        AnalizadorResumen analizador = new AnalizadorResumen();
        
        System.out.println("====================================================");
        System.out.println("         🚀 Prueba de Requerimientos Funcionales    ");
        System.out.println("====================================================");

        // 2. Requerimiento 1: Agregar resumen (Prueba de inserción O(1) y validación de duplicados)
        System.out.println("## 1. Prueba de Agregar Resumen (O(1) y Duplicados)");
        
        String titulo1 = "Algoritmos para el manejo de Big Data";
        String[] autores1 = {"Dr. Ana López", "Ing. Carlos Ruiz"};
        String resumen1 = "Este artículo explora los algoritmos de clasificación de big data. La eficiencia de los algoritmos es crucial para el análisis de big data.";
        String[] palabras1 = {"Algoritmos", "Big Data", "Clasificación"};
        
        String titulo2 = "Optimización de árboles de búsqueda binaria";
        String[] autores2 = {"Aic. Elena Soto"};
        String resumen2 = "Investigación sobre cómo mejorar el rendimiento de los árboles AVL. La estructura de un árbol balanceado ayuda a la búsqueda.";
        String[] palabras2 = {"Árboles", "AVL", "Búsqueda"};
        
        // Inserción exitosa
        
        
        
        simularCargaArticulo(titulo1, autores1, resumen1, palabras1, tabladd, tablapc, avlAutores, avlPalabras);
        simularCargaArticulo(titulo2, autores2, resumen2, palabras2, tabladd, tablapc, avlAutores, avlPalabras);
        
        // Intento de insertar duplicado
        simularCargaArticulo(titulo1, autores1, resumen1, palabras1, tabladd, tablapc, avlAutores, avlPalabras);

        System.out.println("----------------------------------------------------");

        // 3. Requerimiento 2: Analizar Resumen (Listado O(n) y Búsqueda O(1))
        System.out.println("## 2. Prueba de Analizar Resumen (O(n) y Análisis)");
        
        // 2.a: El sistema muestra la lista de investigaciones guardadas, preferiblemente en orden alfabético (O(n)).
        String[] titulosOrdenados = tabladd.listarTitulos();
        System.out.println("Lista de Investigaciones Guardadas (O(n)):");
        for (String titulo : titulosOrdenados) {
            System.out.println("- " + titulo);
        }
        
        // 2.b: El usuario selecciona la investigación y el sistema busca el resumen (O(1)).
        String tituloBusqueda = titulo1;
        Articulo artAnalisis = tabladd.buscarPorTitulo(tituloBusqueda); // O(1)
        
        if (artAnalisis != null) {
            System.out.println("\nResumen seleccionado: " + artAnalisis.getTitulo());
            
            // 2.c: El sistema analiza el texto. Primero se obtiene la lista global de palabras clave del AVL (O(n)).
            String[] palabrasClavesGlobales = avlPalabras.listarPalabrasClaves(); // O(n)
            
            String resultadoAnalisis = analizador.analizar(artAnalisis, palabrasClavesGlobales);
            System.out.println("\nEstadísticas del Análisis:");
            System.out.println(resultadoAnalisis);
        }

        System.out.println("----------------------------------------------------");

        // 4. Requerimiento 3: Buscar Investigaciones por palabra clave (O(1))
        System.out.println("## 3. Prueba de Búsqueda por Palabra Clave (O(1))");
        
        String palabraBuscada = "Clasificación"; 
        ListaPalabrasClave listaArticulos = tablapc.buscarPalabraClave(palabraBuscada); // O(1)
        tablapc.mostrarContenidoHash();
        if (listaArticulos != null) {
            System.out.println("Investigaciones relacionadas con '" + palabraBuscada + "':");
            for (Articulo art : listaArticulos.getArticulos()) {
                System.out.println("- " + art.getTitulo());
            }
        } else {
            System.out.println("No se encontraron artículos para la palabra clave: " + palabraBuscada);
        }
        
        System.out.println("----------------------------------------------------");

        // 5. Requerimiento 4: Buscar Investigaciones por Autor (AVL O(log n))
        System.out.println("## 4. Prueba de Búsqueda por Autor (AVL O(log n) y Listado O(n))");
        
        // 4.a: Listado de autores ordenado alfabéticamente (O(n) por recorrido InOrder del AVL).
        String[] listaAutores = avlAutores.listarAutores(); // O(n)
        System.out.println("Lista de Autores (Orden Alfabético - O(n)):");
        for (String autor : listaAutores) {
            System.out.println("- " + autor);
        }
        
        // 4.b: El usuario selecciona un autor (ej: Ana López) y el sistema busca (O(log n) + O(1)).
        String autorSeleccionado = "Dr. Ana López";
        Articulo[] articulosPorAutor = avlAutores.buscarPorAutor(autorSeleccionado); // O(log n)
        
        System.out.println("\nInvestigaciones de '" + autorSeleccionado + "':");
        if (articulosPorAutor.length > 0) {
            for (Articulo art : articulosPorAutor) {
                System.out.println("- " + art.getTitulo());
            }
        } else {
             System.out.println("No se encontraron artículos.");
        }

        System.out.println("----------------------------------------------------");

        // 6. Requerimiento 5: Listar palabras claves (AVL O(n))
        System.out.println("## 5. Prueba de Listar Palabras Clave (AVL O(n))");
        
        String[] palabrasClavesOrdenadas = avlPalabras.listarPalabrasClaves(); // O(n)
        
        System.out.println("Palabras Clave en el Repositorio (Orden Alfabético - O(n)):");
        for (String pc : palabrasClavesOrdenadas) {
            System.out.println("- " + pc);
        }
        
        // Prueba de que se puede escoger para ver detalles (usando la Hash Table de PC O(1))
        String detallePC = "Búsqueda";
        ListaPalabrasClave detalleLista = tablapc.buscarPalabraClave(detallePC); // O(1)
        if (detalleLista != null) {
            System.out.println("\nDetalles para la palabra '" + detallePC + "':");
            System.out.println("  Aparece en " + detalleLista.getAgregados() + " artículo(s).");
        }
        
        System.out.println("====================================================");
        
             VentanaInicio menu = new VentanaInicio(new TablaDeDispersion(), new TablaPorPalabrasClave(), new ArbolAVLAutores(), new ArbolAVLPalabrasClave());

    }
    }
    

