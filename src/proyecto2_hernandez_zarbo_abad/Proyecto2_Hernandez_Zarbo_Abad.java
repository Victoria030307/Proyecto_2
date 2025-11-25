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
    public static void main(String[] args) {

        VentanaInicio menu = new VentanaInicio(new TablaDeDispersion(), new TablaPorPalabrasClave(), new ArbolAVLAutores(), new ArbolAVLPalabrasClave());
    }
}
