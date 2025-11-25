/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_hernandez_zarbo_abad.EDAux;

/**
 *
 * @author gustavo
 */
/**
 * Clase encargada de analizar un artículo (principalmente su resumen) para
 * generar un informe que incluye el nombre, autores, palabras clave del
 * artículo y la frecuencia de aparición de un conjunto de palabras clave
 * globales en el resumen.
 */
public class AnalizadorResumen { 

    /**
     * Analiza el artículo proporcionado y las palabras clave globales para
     * generar un informe de texto.
     *
     * @param articulo El objeto Articulo a analizar.
     * @param palabrasClavesGlobales Un array de Strings con las palabras clave
     * globales del sistema a buscar en el resumen.
     * @return Un String que contiene el informe de análisis del artículo.
     */
    public String analizar(Articulo articulo, String[] palabrasClavesGlobales) {
        String resumen = articulo.getResumen().toLowerCase();
        String analisis = "";
        analisis += "Nombre del trabajo: " + articulo.getTitulo() + "\n";
        analisis += "Autores: " + String.join(", ", articulo.getAutores()) + "\n";
        analisis += "Palabras clave: \n";
        analisis += String.join(", ", articulo.getPalabrasClaves());
        analisis += "\n\n";

        for (String palabraClave : palabrasClavesGlobales) {
            int frecuencia = 0;
            String pc = palabraClave.toLowerCase();
            int longitudPC = pc.length();

            for (int i = 0; i <= resumen.length() - longitudPC; i++) {
                boolean coincide = true;
                for (int j = 0; j < longitudPC; j++) {
                    if (resumen.charAt(i + j) != pc.charAt(j)) {
                        coincide = false;
                        break;
                    }
                }
                if (coincide) {
                    boolean antesValido = (i == 0) || !Character.isLetterOrDigit(resumen.charAt(i - 1));
                    boolean despuesValido = (i + longitudPC == resumen.length()) || !Character.isLetterOrDigit(resumen.charAt(i + longitudPC));
                    if (antesValido && despuesValido) {
                        frecuencia++;
                        i += longitudPC - 1;
                    }
                }
            }
            if (frecuencia > 0) {
                analisis += palabraClave
                        + ": frecuencia con la cual la palabra clave, almacenada en el repositorio del sistema, aparece en el resumen "
                        + frecuencia
                        + "\n";
            }
        }

        return analisis;
    }
}
