package partitura.mundo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.PrintWriter;

/**
 * Interfaz con los servicios que debe proporcionar una nota
 */
public interface INota
{
    /**
     * Retorna el nombre de la nota
     * @return Nombre de la nota (do, re, mi, fa, sol, la, si, do2)
     */
    public String darNombre( );

    /**
     * Retorna el tipo de la nota
     * @return Tipo de la nota (normal (n),sostenido(#))
     */
    public String darTipo( );

    /**
     * Sirve para pintar la nota
     * @param g La superficie donde se debe pintar
     * @param posicion La posición de la nota dentro de la partitura a la que pertenece
     */
    public void pintarNota( Graphics2D g, int posicion );

    /**
     * Sirve para pintar la nota de color rojo (o magenta si el color de la nota es rojo), indicando que está marcada
     * @param g La superficie donde se debe pintar
     * @param posicion La posición de la nota dentro de la partitura a la que pertenece
     */
    public void pintarNotaMarcada( Graphics2D g, int posicion );

    /**
     * Retorna la duración asociada con la nota
     * @return Duración de la nota
     */
    public double darDuracion( );

    /**
     * Retorna la clase de la nota (redonda, blanca, negra, corchea, semicorchea)
     * @return Clase de la nota
     */
    public String darClase( );

    /**
     * Retorna un valor numérico correspondiente a la posición de la nota en el pentagrama: <br>
     * 11 para DO, 10 para RE, 9 para MI, 8 para FA, 7 para SOL, 6 para LA, 5 para SI, 4 para DO2
     * @return Posición de la nota en el pentagrama de la nota
     */
    public int darPosicionNotaPentagrama( );

    /**
     * Sirve para saber si un punto está dentro de la nota o no.
     * @param px Es la coordenada x del punto
     * @param py Es la coordenada y del punto
     * @param posicion La posición de la nota dentro de la partitura a la que pertenece
     * @return Retorna true si el punto está dentro de la nota. Retorna false en caso contrario.
     */
    public boolean estaDentro( int px, int py, int posicion );

    /**
     * Modifica el tipo de la nota
     * @param t Nuevo tipo de la nota
     */
    public void cambiarTipo( String t );

    /**
     * Cambia el color de la nota
     * @param c Nuevo color de la nota
     */
    public void cambiarColor( Color c );

    /**
     * Cambia el nombre de la nota
     * @param nom Nuevo nombre de la nota
     */
    public void cambiarNombre( String nom );

    /**
     * Retorna el color de la nota
     * @return Color de la nota
     */
    public Color darColor( );
    
    /**
     * Este método sirve para guardar la nota en un archivo
     * @param out Es el stream donde se va a guardar la nota
     */
    public void guardar( PrintWriter out );

}
