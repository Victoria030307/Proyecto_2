/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_hernandez_zarbo_abad.EDAux;

/**
 *
 * @author victoria
 */
 
/**
 * Representa un artículo científico o de investigación con sus atributos
 * fundamentales: título, autores, resumen y palabras clave.
 * Es la unidad de información principal gestionada por el sistema.
 */
public class Articulo {
    private String titulo;
    private String[] autores;
    private String resumen;
    private String[] palabrasClaves;

    /**
     * Constructor para inicializar un nuevo objeto Articulo.
     * @param titulo El título del artículo.
     * @param autores Un array de Strings con los nombres de los autores.
     * @param resumen El resumen o abstract del artículo.
     * @param palabrasClaves Un array de Strings con las palabras clave asociadas.
     */
    public Articulo(String titulo, String[] autores, String resumen, String[] palabrasClaves) {
        this.titulo = titulo;
        this.autores = autores;
        this.resumen = resumen;
        this.palabrasClaves = palabrasClaves;
    }

    /**
     * Obtiene el título del artículo.
     * @return El título.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece un nuevo título para el artículo.
     * @param titulo El nuevo título.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene el array de autores del artículo.
     * @return Array de Strings con los nombres de los autores.
     */
    public String[] getAutores() {
        return autores;
    }

    /**
     * Establece un nuevo array de autores para el artículo.
     * @param autores El nuevo array de autores.
     */
    public void setAutores(String[] autores) {
        this.autores = autores;
    }

    /**
     * Obtiene el resumen del artículo.
     * @return El resumen.
     */
    public String getResumen() {
        return resumen;
    }

    /**
     * Establece un nuevo resumen para el artículo.
     * @param resumen El nuevo resumen.
     */
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    /** 
     * Obtiene el array de palabras clave del artículo.
     * @return Array de Strings con las palabras clave.
     */ 
    public String[] getPalabrasClaves() {
        return palabrasClaves;
    }

    /**
     * Establece un nuevo array de palabras clave para el artículo.
     * @param palabrasClaves El nuevo array de palabras clave.
     */
    public void setPalabrasClaves(String[] palabrasClaves) {
        this.palabrasClaves = palabrasClaves;
    }
}