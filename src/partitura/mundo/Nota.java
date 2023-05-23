/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: Nota.java 604 2006-11-07 04:47:33Z da-romer $ 
 * Universidad de los Andes (Bogotá - Colombia) 
 * Departamento de Ingeniería de Sistemas y Computación 
 * Todos los derechos reservados 2005 
 * 
 * Proyecto Cupi2 
 * Ejercicio: n10_partitura
 * Autor: Diana Puentes - Jul 29, 2005
 * Modificado por: Daniel Francisco Romero- Marzo 21, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package partitura.mundo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Esta es una clase abstracta que representa una nota en la partitura. <br>
 * <b>inv: </b> <br>
 * (tipo= Sostenido o tipo= Normal) y (nombre= DO o nombre= RE o nombre= MI o nombre= FA o nombre= SOL o nombre= LA o nombre= SI o nombre= DO2)<br>
 */
public abstract class Nota implements INota
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    public static final String NORMAL = "Normal";

    public static final String SOSTENIDO = "Sostenido";

    public static final String DO = "DO";

    public static final String RE = "RE";

    public static final String MI = "MI";

    public static final String FA = "FA";

    public static final String SOL = "SOL";

    public static final String LA = "LA";

    public static final String SI = "SI";

    public static final String DO2 = "DO2";

    public static final String DOS = "DO#";

    public static final String RES = "RE#";

    public static final String FAS = "FA#";

    public static final String SOLS = "SOL#";

    public static final String LAS = "LA#";

    public static final int ALTO_NOTA = 12;

    public static final int ANCHO_NOTA = 12;

    public static final int ALTO_LINEA_NOTA = 30;

    public static final int ESPACIO_NOTAS_HORIZONTAL = 32;

    public static final int ESPACIO_NOTAS_VERTICAL = 6;

    public static final int LONGITUD_LINEAS_SOSTENIDO = 4;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El nombre de la nota (do, re, mi fa, sol, la, si, do2)
     */
    protected String nombre;

    /**
     * El tipo de la nota (normal, sostenido)
     */
    protected String tipo;

    /**
     * Color de fondo de la construcción
     */
    protected Color color;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nota
     * @param nom El nombre de la nota
     * @param tip El tipo de la nota
     * @param co Color de fondo de la nota
     * 
     */
    public Nota( String nom, String tip, Color co )
    {
        nombre = nom;
        tipo = tip;
        color = co;
        verificarInvariante( );
    }

    /**
     * Crea una construcción a partir de los datos contenidos en un archivo
     * @param br Es el stream que sirve para leer el archivo
     * @throws IOException Se lanza esta excepción si hay problemas leyendo el archivo
     * @throws FormatoInvalidoExcepcion Se lanza esta excepción si el formato del archivo no es el esperado
     */
    public Nota( BufferedReader br ) throws IOException, FormatoInvalidoExcepcion
    {

        String pos = null;
        String lineaColor;

        try
        {
            tipo = br.readLine( );
            nombre = br.readLine( );
            // pos= br.readLine( );
            // posicion = Integer.parseInt(pos);
            lineaColor = br.readLine( );
            inicializarColor( lineaColor );

        }
        catch( NumberFormatException fie )
        {
            br.close( );
            throw new FormatoInvalidoExcepcion( pos );
        }
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre de la nota
     * @return Nombre de la nota (do, re, mi, fa, sol, la, si, do2)
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna el tipo de la nota
     * @return Tipo de la nota (normal (n),sostenido(#))
     */
    public String darTipo( )
    {
        return tipo;
    }

    /**
     * Sirve para pintar la semicorchea
     * @param g La superficie donde se debe pintar
     * @param posicion La posición de la nota dentro de la partitura a la que pertenece
     */
    public void pintarNota( Graphics2D g, int posicion )
    {
        pintar( g, posicion, color );
    }

    /**
     * Sirve para pintar la corchea seleccionada
     * @param g La superficie donde se debe pintar
     * @param posicion La posición de la nota dentro de la partitura a la que pertenece
     */
    public void pintarNotaMarcada( Graphics2D g, int posicion )
    {

        Color c = Color.RED;

        if( color.equals( Color.RED ) )
        {
            c = Color.BLUE;

        }
        pintar( g, posicion, c );
    }

    /**
     * Sirve para pintar la nota del color dado
     * @param g La superficie donde se debe pintar
     * @param posicion La posición de la nota dentro de la partitura a la que pertenece
     * @param c El color con el que se va a pintar la nota
     */
    protected abstract void pintar( Graphics2D g, int posicion, Color c );

    /**
     * Retorna la duración asociada con la nota
     * @return duración de la nota
     */
    public abstract double darDuracion( );

    /**
     * Retorna la clase de la nota (redonda, blanca, negra, corchea, semicorchea)
     * @return Clase de la nota
     */
    public abstract String darClase( );

    /**
     * Pinta el sostenido de la nota si es de este tipo basándose en las coordenadas de la nota
     * @param g La superficie donde se debe pintar
     * @param posicion La posición de la nota dentro de la partitura a la que pertenece
     * @param c El color con el que se debe pintar
     */
    protected void pintarSostenido( Graphics2D g, int posicion, Color c )
    {
        if( tipo.equalsIgnoreCase( SOSTENIDO ) )
        {
            int coordenadaX = calcularCoordenadaXNota( posicion );
            int coordenadaY = calcularCoordenadaYNota( posicion );

            g.setColor( c );

            // Primera línea horizontal
            g.drawLine( coordenadaX + ANCHO_NOTA + LONGITUD_LINEAS_SOSTENIDO, coordenadaY + LONGITUD_LINEAS_SOSTENIDO, coordenadaX + ANCHO_NOTA + 4 + LONGITUD_LINEAS_SOSTENIDO * 2, coordenadaY + LONGITUD_LINEAS_SOSTENIDO );

            // Segunda línea horizontal
            g.drawLine( coordenadaX + ANCHO_NOTA + LONGITUD_LINEAS_SOSTENIDO, coordenadaY + LONGITUD_LINEAS_SOSTENIDO * 2, coordenadaX + ANCHO_NOTA + 4 + LONGITUD_LINEAS_SOSTENIDO * 2, coordenadaY + LONGITUD_LINEAS_SOSTENIDO * 2 );

            // Primera línea vertical
            g.drawLine( coordenadaX + ANCHO_NOTA + LONGITUD_LINEAS_SOSTENIDO + 2, coordenadaY + LONGITUD_LINEAS_SOSTENIDO - 2, coordenadaX + ANCHO_NOTA + LONGITUD_LINEAS_SOSTENIDO + 2, coordenadaY + LONGITUD_LINEAS_SOSTENIDO - 2
                    + LONGITUD_LINEAS_SOSTENIDO * 2 );

            // Segunda línea vertical
            g.drawLine( coordenadaX + ANCHO_NOTA + LONGITUD_LINEAS_SOSTENIDO + 2 + LONGITUD_LINEAS_SOSTENIDO, coordenadaY + LONGITUD_LINEAS_SOSTENIDO - 2, coordenadaX + ANCHO_NOTA + LONGITUD_LINEAS_SOSTENIDO + 2 + LONGITUD_LINEAS_SOSTENIDO,
                    coordenadaY + LONGITUD_LINEAS_SOSTENIDO - 2 + LONGITUD_LINEAS_SOSTENIDO * 2 );

        }

    }

    /**
     * Calcula la coordenada x de la nota de acuerdo a su posición dentro de la partitura <br>
     * y al espacio horizontal que se debe dejar entre las notas
     * @param posicion La posición de la nota dentro de la partitura a la que pertenece
     * @return La coordenada en x de la nota
     */
    protected int calcularCoordenadaXNota( int posicion )
    {
        int coordenadaX = 0;

        if( ( posicion + 2 ) * ESPACIO_NOTAS_HORIZONTAL > Partitura.ANCHO_PARTITURA ) // Si ya se lleno la
        // primera partitura se pasa a la siguiente
        {
            coordenadaX = ( ( ( ( ( posicion + 1 ) % ( Partitura.NUM_MAX_NOTAS / 2 + 1 ) ) + 1 ) + 1 ) * ESPACIO_NOTAS_HORIZONTAL );
        }
        else
        {
            coordenadaX = ( ( ( ( posicion + 1 ) % ( Partitura.NUM_MAX_NOTAS / 2 + 1 ) ) + 1 ) * ESPACIO_NOTAS_HORIZONTAL );
        }

        return coordenadaX;
    }

    /**
     * Calcula la coordenada y de la nota de acuerdo a su posición dentro de la partitura <br>
     * y al espacio vertical que se debe dejar entre las notas
     * @param posicion La posición de la nota dentro de la partitura a la que pertenece
     * @return La coordenada en y de la nota
     */
    protected int calcularCoordenadaYNota( int posicion )
    {
        int coordenadaY;

        coordenadaY = ( darPosicionNotaPentagrama( ) ) * ESPACIO_NOTAS_VERTICAL + Partitura.MARGEN_PARTITURA_EXTERNA + ( Partitura.MARGEN_PARTITURA_INTERNA * 3 / 4 );

        if( ( posicion + 2 ) * ESPACIO_NOTAS_HORIZONTAL > Partitura.ANCHO_PARTITURA ) // Si ya se lleno la primera partitura se pasa a la siguiente
        {
            coordenadaY = coordenadaY + Partitura.MARGEN_PARTITURA_INTERNA / 2 + Partitura.ESPACIO_LINEAS_PENTAGRAMA * 5;
        }

        return coordenadaY;
    }

    /**
     * Retorna un valor numérico correspondiente a la posición de la nota en el pentagrama: <br>
     * 11 para DO, 10 para RE, 9 para MI, 8 para FA, 7 para SOL, 6 para LA, 5 para SI, 4 para DO2
     * @return Posición de la nota en el pentagrama de la nota
     */
    public int darPosicionNotaPentagrama( )
    {
        int val;

        if( nombre.equalsIgnoreCase( DO ) )
        {
            val = 11;
        }
        else if( nombre.equalsIgnoreCase( RE ) )
        {
            val = 10;
        }
        else if( nombre.equalsIgnoreCase( MI ) )
        {
            val = 9;
        }
        else if( nombre.equalsIgnoreCase( FA ) )
        {
            val = 8;
        }
        else if( nombre.equalsIgnoreCase( SOL ) )
        {
            val = 7;
        }
        else if( nombre.equalsIgnoreCase( LA ) )
        {
            val = 6;
        }
        else if( nombre.equalsIgnoreCase( SI ) )
        {
            val = 5;
        }
        else
        {
            val = 4; // DO2
        }

        return val;

    }

    /**
     * Sirve para saber si un punto está dentro de la nota o no.
     * @param px Es la coordenada x del punto
     * @param py Es la coordenada y del punto
     * @param posicion La posición de la nota dentro de la partitura a la que pertenece
     * @return Retorna true si el punto está dentro de la nota. Retorna false en caso contrario.
     */
    public boolean estaDentro( int px, int py, int posicion )
    {
        int cX = calcularCoordenadaXNota( posicion );
        int cY = calcularCoordenadaYNota( posicion );
        int ancho = tipo == NORMAL ? ANCHO_NOTA : ESPACIO_NOTAS_HORIZONTAL;
        Rectangle2D rectangulo = new Rectangle2D.Double( cX, cY, ancho, ALTO_NOTA );

        return rectangulo.contains( px, py );
    }

    /**
     * Modifica el tipo de la nota
     * @param t nuevo tipo de la nota
     */
    public void cambiarTipo( String t )
    {
        tipo = t;
        verificarInvariante( );
    }

    /**
     * Cambia el color de la nota
     * @param c Nuevo color de la nota
     */
    public void cambiarColor( Color c )
    {
        color = c;
        verificarInvariante( );
    }

    /**
     * Cambia el nombre de la nota
     * @param nom Nuevo nombre de la nota
     */
    public void cambiarNombre( String nom )
    {
        nombre = nom;
        verificarInvariante( );

    }

    /**
     * Retorna el color de la nota
     * @return Color de la nota
     */
    public Color darColor( )
    {
        return color;
    }

    /**
     * Indica si la nota es igual al objeto o
     * @param o Nota con la que se va a ser la comparación
     */
    public boolean equals( Object o )
    {
        Nota aux = ( Nota )o;
        return aux.darClase( ).equals( darClase( ) ) && aux.color.equals( color ) && aux.nombre.equals( nombre ) && aux.tipo.equals( tipo );
    }

    /**
     * Inicializa el color de la nota a partir de la información que estaba contenida en el archivo <br>
     * <b>post: </b> color tiene nuevo color
     * @param lineaColor La línea de texto que indica el color de la nota
     * @throws FormatoInvalidoExcepcion Se lanza esta excepción si el formato de la línea no es el esperado
     */
    private void inicializarColor(String lineaColor) throws FormatoInvalidoExcepcion {
        String[] strValoresFondo = lineaColor.split( ";" );
        if( strValoresFondo.length != 3 )
            throw new FormatoInvalidoExcepcion( lineaColor );
        
        try {
            int r1 = Integer.parseInt( strValoresFondo[ 0 ] );
            int g1 = Integer.parseInt( strValoresFondo[ 1 ] );
            int b1 = Integer.parseInt( strValoresFondo[ 2 ] );

            color = new Color( r1, g1, b1 );
        } 
        catch( NumberFormatException nfe ) {
            throw new FormatoInvalidoExcepcion( lineaColor );
        }
    }

    /**
     * Este método sirve para guardar la nota en un archivo
     * @param out Es el stream donde se va a guardar la nota
     */
    public void guardar( PrintWriter out )
    {
        out.println( darClase( ) );
        out.println( darTipo( ) );
        out.println( darNombre( ) );
        out.println( color.getRed( ) + ";" + color.getGreen( ) + ";" + color.getBlue( ) );
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase. <br>
     * <b>inv: </v> <br>
     * (tipo= Sostenido o tipo= Normal) y (nombre= DO o nombre= RE o nombre= MI o nombre= FA o nombre= SOL o nombre= LA o nombre= SI o nombre= DO2)<br>
     * 
     */
    protected void verificarInvariante( )
    {
        assert ( nombre.equalsIgnoreCase( DO ) || nombre.equalsIgnoreCase( RE ) || nombre.equalsIgnoreCase( MI ) || nombre.equalsIgnoreCase( FA ) || nombre.equalsIgnoreCase( SOL ) || nombre.equalsIgnoreCase( LA ) || nombre.equalsIgnoreCase( SI ) || nombre
                .equalsIgnoreCase( DO2 ) ) : "El nombre de nota " + nombre + " no existe";
        assert ( tipo.equalsIgnoreCase( SOSTENIDO ) || tipo.equalsIgnoreCase( NORMAL ) ) : "El tipo de nota " + tipo + " no existe";
    }
}
