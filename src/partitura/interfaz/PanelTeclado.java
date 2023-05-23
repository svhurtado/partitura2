/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: PanelTeclado.java 600 2006-11-06 06:16:53Z da-romer $ 
 * Universidad de los Andes (Bogotá - Colombia) 
 * Departamento de Ingeniería de Sistemas y Computación 
 * Todos los derechos reservados 2005 
 * 
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_partitura
 * Autor: Diana Puentes - Jul 29, 2005
 * Modificado por: Daniel Francisco Romero Acero - 25-ene-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package partitura.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import partitura.mundo.Nota;

/**
 * Panel con el teclado
 */
public class PanelTeclado extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazPartitura principal;

    /**
     * Si se esta tocando en modo partitura= InterfazPartitura.PARTITURA, si se esta tocando en modo libre = InterfazPartitura.LIBRE, si se está tocando en modo composición=
     * InterfazPartitura.COMPOSICION
     */
    private int modoTeclado;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * El arreglo de botones que conforman el teclado
     */
    private JButton[] teclas;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un panel que simula un teclado de piano
     * @param interfaz La ventana principal de la aplicación
     */
    public PanelTeclado( InterfazPartitura interfaz )
    {
        // inicializa los elementos necesarios para el funcionamiento del panel
        principal = interfaz;
        // posicionActual = -1;
        setBackground( Color.WHITE );
        setPreferredSize( new Dimension( 783, 233 ) );

        modoTeclado = InterfazPartitura.LIBRE;
        inicializarElementos( );
        pintarTeclado( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Dibuja un teclado en la pantalla usando un botón para cada nota
     */
    private void pintarTeclado( )
    {
        // crea la forma en que se ubicarán las teclas
        setLayout( new GridLayout( 1, 13 ) );
        setBorder( new CompoundBorder( new EmptyBorder( 10, 10, 10, 10 ), new TitledBorder( "" ) ) );

        // se agrega en orden: DO-DOS-RE-RES-MI-FA-FAS-SOL-SOLS-LA-LAS-SI-DO2
        for( int i = 0; i < teclas.length; i++ )
        {
            add( teclas[ i ] );
        }

    }

    /**
     * Inicializa los elementos del panel de teclado: inicializa cada botón con un texto de<br>
     * acción un color de texto y de fondo
     */
    private void inicializarElementos( )
    {

        teclas = new JButton[13];
        // Se crean en orden: DO-DOS-RE-RES-MI-FA-FAS-SOL-SOLS-LA-LAS-SI-DO2
        teclas[ 0 ] = inicializarBoton( Nota.DO, Color.WHITE, Color.BLACK, Nota.DO );
        teclas[ 1 ] = inicializarBoton( Nota.DOS, Color.BLACK, Color.WHITE, Nota.DOS );
        teclas[ 2 ] = inicializarBoton( Nota.RE, Color.WHITE, Color.BLACK, Nota.RE );
        teclas[ 3 ] = inicializarBoton( Nota.RES, Color.BLACK, Color.WHITE, Nota.RES );
        teclas[ 4 ] = inicializarBoton( Nota.MI, Color.WHITE, Color.BLACK, Nota.MI );
        teclas[ 5 ] = inicializarBoton( Nota.FA, Color.WHITE, Color.BLACK, Nota.FA );
        teclas[ 6 ] = inicializarBoton( Nota.FAS, Color.BLACK, Color.WHITE, Nota.FAS );
        teclas[ 7 ] = inicializarBoton( Nota.SOL, Color.WHITE, Color.BLACK, Nota.SOL );
        teclas[ 8 ] = inicializarBoton( Nota.SOLS, Color.BLACK, Color.WHITE, Nota.SOLS );
        teclas[ 9 ] = inicializarBoton( Nota.LA, Color.WHITE, Color.BLACK, Nota.LA );
        teclas[ 10 ] = inicializarBoton( Nota.LAS, Color.BLACK, Color.WHITE, Nota.LAS );
        teclas[ 11 ] = inicializarBoton( Nota.SI, Color.WHITE, Color.BLACK, Nota.SI );
        teclas[ 12 ] = inicializarBoton( Nota.DO, Color.WHITE, Color.BLACK, Nota.DO2 );
    }

    /**
     * Crea e inicializa un botón con con los parámetros dados
     * @param texto El texto del botón
     * @param backGround Color de fondo
     * @param foreGround Color de la letra
     * @param accion Para el actionCommand
     * @return Un botón inicializado con los parámetros dados
     */
    private JButton inicializarBoton( String texto, Color backGround, Color foreGround, String accion )
    {
        JButton boton = new JButton( );
        boton.setText( texto );
        boton.setBackground( backGround );
        boton.setForeground( foreGround );
        boton.setActionCommand( accion );
        boton.addActionListener( this );
        return boton;
    }

    /**
     * Cambia el tipo de practica : InterfazPartitura.LIBRE o InterfazPartitura.PARTITURA
     * @param tipo Indica el tipo de práctica : InterfazPartitura.LIBRE, InterfazPartitura.PARTITURA <br>
     *        o InterfazPartitura.COMPOSICION
     */
    public void cambiarModo( int tipo )
    {
        modoTeclado = tipo;
    }

    /**
     * Retorna el modo del teclado
     * @return Modo del teclado
     */
    public int darModo( )
    {
        return modoTeclado;

    }

    /**
     * Bloquea el teclado para que no se pueda seguir tocando
     * 
     */
    public void bloquearTeclado( )
    {
        for( int i = 0; i < teclas.length; i++ )
        {
            teclas[ i ].setEnabled( false );
        }
    }

    /**
     * Desbloquea el teclado para que se pueda seguir tocando
     * 
     */
    public void desbloquearTeclado( )
    {
        for( int i = 0; i < teclas.length; i++ )
        {
            teclas[ i ].setEnabled( true );
        }
    }

    /**
     * Responde con acciones en el mundo dependiendo de las acciones en la interfaz
     * @param evento El evento realizado por el usuario en la interfaz
     */
    public void actionPerformed( ActionEvent evento )
    {
        // Toca la nota correspondiente según el botón en el que se haya hecho click
        String comando = evento.getActionCommand( );
        principal.tocarNota( comando );
    }

}
