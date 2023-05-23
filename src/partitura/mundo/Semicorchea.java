/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Semicorchea.java 600 2006-11-06 06:16:53Z da-romer $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_partitura
 * Autor: Daniel Francisco Romero Acero - 21-marzo-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package partitura.mundo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Esta es una clase que representa una semicorchea en la partitura
 */
public class Semicorchea extends Nota
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    public static final double DURACION = 0.25;

    public static final String CLASE = "Semicorchea";

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una semicorchea
     * @param nom El nombre de la corchea
     * @param tip El tipo de la corchea
     * @param co Color de la corchea
     */
    public Semicorchea( String nom, String tip, Color co )
    {
        super( nom, tip, co );

    }

    /**
     * Crea la semicorchea a partir de los datos contenidos en un archivo
     * @param br Es el stream que sirve para leer el archivo
     * @throws IOException Se lanza esta excepción si hay problemas leyendo el archivo
     * @throws FormatoInvalidoExcepcion Se lanza esta excepción si el formato del archivo no es el esperado
     */
    public Semicorchea( BufferedReader br ) throws IOException, FormatoInvalidoExcepcion
    {
        super( br );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Retorna la duración de la nota
     */
    public double darDuracion( )
    {
        return DURACION;
    }

    /**
     * Retorna la clase de la nota
     */
    public String darClase( )
    {
        return CLASE;
    }

    /**
     * Sirve para pintar la nota del color dado
     * @param g La superficie donde se debe pintar
     * @param posicion La posición de la nota dentro de la partitura a la que pertenece
     * @param c El color con el que se va a pintar la nota
     */
    protected void pintar( Graphics2D g, int posicion, Color c )
    {
        // Pintar el circulo
        g.setColor( c );
        int coordenadaX = calcularCoordenadaXNota( posicion );
        int coordenadaY = calcularCoordenadaYNota( posicion );

        g.fillOval( coordenadaX, coordenadaY, ANCHO_NOTA, ALTO_NOTA );

        // Pintar la línea
        int coordenadaXLinea = calcularCoordenadaXNota( posicion ) + ANCHO_NOTA;
        int coordenadaYLinea = calcularCoordenadaYNota( posicion ) + ALTO_NOTA / 2;

        g.drawLine( coordenadaXLinea, coordenadaYLinea, coordenadaXLinea, coordenadaYLinea - ALTO_LINEA_NOTA );

        // Pintar la primera línea de la semicorchea
        g.drawLine( coordenadaXLinea, coordenadaYLinea - ALTO_LINEA_NOTA, coordenadaXLinea + 5, coordenadaYLinea - ALTO_LINEA_NOTA + 5 );

        // Pintar la segunda línea de la semicorchea
        g.drawLine( coordenadaXLinea, coordenadaYLinea - ALTO_LINEA_NOTA + 5, coordenadaXLinea + 5, coordenadaYLinea - ALTO_LINEA_NOTA + 10 );

        // Si la nota es DO se debe pintar la línea que atraviesa la nota
        if( nombre.equalsIgnoreCase( DO ) )
        {
            g.drawLine( coordenadaX - 5, coordenadaY + ALTO_NOTA / 2, coordenadaX + ANCHO_NOTA + 5, coordenadaY + ALTO_NOTA / 2 );
        }

        pintarSostenido( g, posicion, c );
    }

}