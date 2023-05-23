/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: PanelOpciones.java 600 2006-11-06 06:16:53Z da-romer $ 
 * Universidad de los Andes (Bogotá - Colombia) 
 * Departamento de Ingeniería de Sistemas y Computación 
 * Todos los derechos reservados 2005 
 * 
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_partitura
 * Autor: Diana Puentes - Jul 29, 2005
 * Modificado por: Daniel Romero - 23/03/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package partitura.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * Panel que contiene los botones para realizar acciones con la partitura
 */
public class PanelOpciones extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String COMPONER = "Componer";

    private static final String PRACTICA_GUIADA = "Práctica Guiada";

    private static final String PRACTICA_LIBRE = "Práctica Libre";

    private static final String MODO = "Modo";

    private final static String OPCION_1 = "OPCION_1";

    private final static String OPCION_2 = "OPCION_2";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazPartitura principal;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * El botón para tocar la partitura
     */
    private JButton botonTocarPartitura;

    /**
     * El combo con los distintos modos en los que se puede trabajar en la partitura
     */
    private JComboBox cbbModos;

    /**
     * El combo con los distintas clases de notas
     */
    private JComboBox cbbClasesNotas;

    /**
     * El botón para la extensión 1
     */
    private JButton opcion1;

    /**
     * El botón para la extensión 2
     */
    private JButton opcion2;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea el panel con los botones para realizar las acciones en la partitura
     * @param interfaz Es una referencia a la ventana principal de la aplicación
     */
    public PanelOpciones( InterfazPartitura interfaz )
    {
        principal = interfaz;
        inicializarElementos( );

        // ubica los elementos en la interfaz
        GridBagLayout gridbag1 = new GridBagLayout( );
        setLayout( gridbag1 );
        setBorder( new CompoundBorder( new EmptyBorder( 10, 10, 10, 10 ), new TitledBorder( "Elija la acción a realizar" ) ) );

        GridBagConstraints gbc1;

        gbc1 = new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( cbbModos, gbc1 );

        gbc1 = new GridBagConstraints( 1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( botonTocarPartitura, gbc1 );

        gbc1 = new GridBagConstraints( 2, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( cbbClasesNotas, gbc1 );

        gbc1 = new GridBagConstraints( 3, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( opcion1, gbc1 );

        gbc1 = new GridBagConstraints( 4, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( opcion2, gbc1 );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicializa los elementos del panel
     */
    private void inicializarElementos( )
    {
        // Asigna valores a los botones
        opcion1 = new JButton( );
        opcion1.setText( "Opción 1" );
        opcion1.setActionCommand( OPCION_1 );
        opcion1.addActionListener( this );

        opcion2 = new JButton( );
        opcion2.setText( "Opción 2" );
        opcion2.setActionCommand( OPCION_2 );
        opcion2.addActionListener( this );

        botonTocarPartitura = new JButton( );
        botonTocarPartitura.setText( "Escuchar Partitura" );
        botonTocarPartitura.setActionCommand( PRACTICA_GUIADA );
        botonTocarPartitura.addActionListener( this );
        botonTocarPartitura.setEnabled( false );

        cbbModos = new JComboBox( );
        cbbModos.addItem( PRACTICA_LIBRE );
        cbbModos.addItem( PRACTICA_GUIADA );
        cbbModos.addItem( COMPONER );
        cbbModos.setActionCommand( MODO );
        cbbModos.addActionListener( this );

        cbbClasesNotas = new JComboBox( );
        cbbClasesNotas.addItem( "Redonda" );
        cbbClasesNotas.addItem( "Blanca" );
        cbbClasesNotas.addItem( "Negra" );
        cbbClasesNotas.addItem( "Corchea" );
        cbbClasesNotas.addItem( "Semicorchea" );
        cbbClasesNotas.setEnabled( false );

    }

    /**
     * Responde con acciones en el mundo dependiendo de las acciones en la interfaz
     * @param evento El evento realizado por el usuario en la interfaz - evento!=null
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        if( comando.equals( COMPONER ) )
        {
            principal.cambiarModo( InterfazPartitura.COMPONER );
            principal.limpiarPartitura( );
        }
        else if( comando.equals( PRACTICA_GUIADA ) )
        {
            principal.tocarMelodia( );
        }
        else if( comando.equals( PRACTICA_LIBRE ) )
        {
            principal.cambiarModo( InterfazPartitura.LIBRE );
            principal.limpiarPartitura( );
            botonTocarPartitura.setEnabled( false );
        }
        else if( comando.equals( MODO ) )
        {
            String modo = ( String )cbbModos.getSelectedItem( );

            if( modo.equals( PRACTICA_LIBRE ) )
            {
                botonTocarPartitura.setEnabled( false );
                cbbClasesNotas.setEnabled( false );
                principal.cambiarModo( InterfazPartitura.LIBRE );
                principal.reiniciarPartitura( );
            }
            else if( modo.equals( PRACTICA_GUIADA ) )
            {
                principal.cambiarModo( InterfazPartitura.GUIADA );
                botonTocarPartitura.setEnabled( true );
                cbbClasesNotas.setEnabled( false );
                principal.reiniciarPartitura( );
            }
            else if( modo.equals( COMPONER ) )
            {
                botonTocarPartitura.setEnabled( false );
                cbbClasesNotas.setEnabled( true );
                principal.cambiarModo( InterfazPartitura.COMPONER );
                principal.reiniciarPartitura( );
            }
        }
        else if( comando.equals( OPCION_1 ) )
        {
            principal.reqFuncOpcion1( );
        }
        else if( comando.equals( OPCION_2 ) )
        {
            principal.reqFuncOpcion2( );
        }
    }

    /**
     * Retorna la clase de nota seleccionada
     * @return Clase de nota seleccionada
     */
    public String darClaseNota( )
    {
        return ( String )cbbClasesNotas.getSelectedItem( );
    }
}
