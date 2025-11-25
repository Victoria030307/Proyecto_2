/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_hernandez_zarbo_abad.EDAux;

/**
 *
 * @author gustavo 
 */
public class AnalizadorResumen {
    
    // Requerimiento 2: Analizar Resumen
    public String analizar(Articulo articulo, String[] palabrasClavesGlobales) {
        String resumen = articulo.getResumen().toLowerCase();
        
        StringBuilder analisis = new StringBuilder();
        analisis.append("Nombre del trabajo: ").append(articulo.getTitulo()).append("\n");
        analisis.append("Autores: ");
        for (String autor : articulo.getAutores()) {
            analisis.append(autor).append(", ");
        }
                analisis.append("\nPalabras clave: \n");

        for (String pc : articulo.getPalabrasClaves()) {
            analisis.append(pc).append(", ");
        }
        // Eliminar la última coma y espacio, si existe
        if (articulo.getAutores().length > 0) {
            analisis.setLength(analisis.length() - 2);
        }
        analisis.append("\n\n");
        
        for (String palabraClave : palabrasClavesGlobales) {
            int frecuencia = 0;
            String pc = palabraClave.toLowerCase();
            int longitudPC = pc.length();
            
            // Búsqueda de la palabra clave en el resumen
            for (int i = 0; i <= resumen.length() - longitudPC; i++) {
                boolean coincide = true;
                for (int j = 0; j < longitudPC; j++) {
                    if (resumen.charAt(i + j) != pc.charAt(j)) {
                        coincide = false;
                        break;
                    }
                }
                
                if (coincide) {
                    // Validar si es una palabra completa (no parte de otra)
                    // Verificar caracter anterior (debe ser espacio, inicio, o puntuación)
                    boolean antesValido = (i == 0) || !Character.isLetterOrDigit(resumen.charAt(i - 1));
                    
                    // Verificar caracter posterior (debe ser espacio, fin, o puntuación)
                    boolean despuesValido = (i + longitudPC == resumen.length()) || !Character.isLetterOrDigit(resumen.charAt(i + longitudPC));
                    
                    if (antesValido && despuesValido) {
                        frecuencia++;
                        // Saltar a la posición después de la palabra encontrada
                        i += longitudPC - 1; 
                    }
                }
            }
            if (frecuencia > 0){
            analisis.append(palabraClave)
                    .append(": frecuencia con la cual la palabra clave, almacenada en el repositorio del sistema, aparece en el resumen ")
                    .append(frecuencia)
                    .append("\n");
            }
        }
        
        return analisis.toString();
    }
}
















