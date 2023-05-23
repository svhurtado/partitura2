/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: FormatoInvalidoExcepcion.java 514 2006-10-23 04:50:08Z da-romer $ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Todos los derechos reservados 2005 
 *
 * Proyecto Cupi2 
 * Ejercicio: n10_partitura 
 * Autor: Mario Sánchez - 27/09/2005 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package partitura.mundo;

/**
 * Esta Excepción se lanza si hay un problema con el formato del archivo leído
 */
public class FormatoInvalidoExcepcion extends Exception
{
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la excepción
     * @param linea La línea que tiene un formato inválido
     */
    public FormatoInvalidoExcepcion( String linea )
    {
        super( "El formato de la línea es inválido:" + linea );
    }
}
