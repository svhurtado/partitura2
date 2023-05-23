/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelModificarTitulo.java 516 2006-10-23 05:06:15Z da-romer $ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Todos los derechos reservados 2005 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_partitura
 * Autor: Mario Sánchez - 27/09/2005 
 * Modificado por: Daniel Romero - 23/03/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package partitura.interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Este es el panel en el que se modifican las propiedades del titulo de la partitura
 */
public class PanelModificarTitulo extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String FUENTE = "Fuente";

    private static final String TAMANO = "Tamaño";

    private static final String ESTILO = "Estilo";

    private static final String COLOR = "Color";

    private static final String CANCELAR = "Cancelar";

    private static final String OK = "Ok";

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es una referencia al diálogo que contiene este panel
     */
    private DialogoTituloPartitura dialogo;

    /**
     * Es el comboBox para seleccionar la fuente
     */
    private JComboBox cbbFuente;

    /**
     * Es el comboBox para seleccionar el tamaño de la fuente
     */
    private JComboBox cbbTamano;

    /**
     * Es el comboBox para seleccionar el estilo de la fuente (normal, negrita, itálica)
     */
    private JComboBox cbbEstilo;

    /**
     * Es el campo de texto donde se muestra el texto que quedará en la figura
     */
    private JTextField txtTexto;

    /**
     * Es el botón Ok
     */
    private JButton botonOk;

    /**
     * Es el botón Cancelar
     */
    private JButton botonCancelar;

    /**
     * Es el botón del color del titulo
     */
    private JButton botonColorTitulo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Inicializa el panel
     * @param dt Es una referencia al diálogo en el que se encuentra el panel
     * @param fuentes Es una lista con los nombres de las fuentes instaladas en el sistema
     */
    public PanelModificarTitulo( DialogoTituloPartitura dt, ArrayList fuentes )
    {
        dialogo = dt;

        setLayout( new GridBagLayout( ) );

        // Inicializa los componentes
        inicializarComponentes( fuentes );

        // Ubica los componentes
        ubicarComponentes( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Ubica los componentes en sus posiciones. <br>
     * <b>pre: </b> Los componentes ya están inicializados
     */
    private void ubicarComponentes( )
    {
        GridBagConstraints gbc;

        gbc = new GridBagConstraints( 0, 0, 4, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtTexto, gbc );

        gbc = new GridBagConstraints( 0, 1, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( cbbFuente, gbc );

        gbc = new GridBagConstraints( 1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( cbbTamano, gbc );

        gbc = new GridBagConstraints( 2, 1, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( cbbEstilo, gbc );

        gbc = new GridBagConstraints( 3, 1, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( botonColorTitulo, gbc );

        JPanel panelBotones = new JPanel( );
        panelBotones.add( botonOk );
        panelBotones.add( botonCancelar );
        gbc = new GridBagConstraints( 0, 2, 3, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( panelBotones, gbc );
    }

    /**
     * Inicializa los componentes
     * @param fuentes Es una lista con los nombres de las fuentes instaladas en el sistema
     */
    private void inicializarComponentes( ArrayList fuentes )
    {
        cbbFuente = new JComboBox( );
        cbbFuente.setActionCommand( FUENTE );
        for( int i = 0; i < fuentes.size( ); i++ )
        {
            String nombreFuente = ( String )fuentes.get( i );
            cbbFuente.addItem( nombreFuente );
        }
        cbbFuente.addActionListener( this );

        cbbTamano = new JComboBox( );
        cbbTamano.setActionCommand( TAMANO );
        for( int i = 8; i <= 32; i += 2 )
        {
            cbbTamano.addItem( new Integer( i ) );
        }
        cbbTamano.addActionListener( this );

        cbbEstilo = new JComboBox( );
        cbbEstilo.setActionCommand( TAMANO );
        cbbEstilo.addItem( "Normal" );
        cbbEstilo.addItem( "Negrita" );
        cbbEstilo.addItem( "Itálica" );
        cbbEstilo.addActionListener( this );

        txtTexto = new JTextField( );

        botonOk = new JButton( "Ok" );
        botonOk.setActionCommand( OK );
        botonOk.addActionListener( this );

        botonCancelar = new JButton( "Cancelar" );
        botonCancelar.setActionCommand( CANCELAR );
        botonCancelar.addActionListener( this );

        botonColorTitulo = new JButton( );
        botonColorTitulo.setActionCommand( COLOR );
        botonColorTitulo.addActionListener( this );

    }

    /**
     * Cambia la fuente del texto mostrado por la seleccionada actualmente en los combo boxes.
     */
    public void actualizarFuente( )
    {
        String nombreFuente = ( String )cbbFuente.getSelectedItem( );
        int tamano = ( ( Integer )cbbTamano.getSelectedItem( ) ).intValue( );
        int numEstilo = cbbEstilo.getSelectedIndex( );

        int estilo = -1;
        switch( numEstilo )
        {
            case 0:
                estilo = Font.PLAIN;
                break;
            case 1:
                estilo = Font.BOLD;
                break;
            case 2:
                estilo = Font.ITALIC;
                break;
            default:
                estilo = Font.PLAIN;
        }

        Font fuente = new Font( nombreFuente, estilo, tamano );
        Color c = botonColorTitulo.getBackground( );
        txtTexto.setFont( fuente );
        txtTexto.setForeground( c );

    }

    /**
     * Cambia el texto mostrado y actualiza los combo box para mostrar la fuente indicada
     * @param texto El texto que se va a mostrar
     * @param fuente La fuente actual del texto
     * @param color El color que va a tener el texto
     */
    public void cambiarTexto( String texto, Font fuente, Color color )
    {
        txtTexto.setText( texto );

        String nombreFuente = fuente.getFamily( );
        int estilo = fuente.getStyle( );
        int tamano = fuente.getSize( );

        boolean encontre = false;
        for( int i = 0; i < cbbFuente.getItemCount( ) && !encontre; i++ )
        {
            String nombre = ( String )cbbFuente.getItemAt( i );
            if( nombre.equals( nombreFuente ) )
            {
                encontre = true;
                cbbFuente.setSelectedIndex( i );
            }
        }

        encontre = false;
        for( int i = 0; i < cbbTamano.getItemCount( ) && !encontre; i++ )
        {
            Integer num = ( Integer )cbbTamano.getItemAt( i );
            if( tamano == num.intValue( ) )
            {
                encontre = true;
                cbbTamano.setSelectedIndex( i );
            }
        }

        switch( estilo )
        {
            case Font.PLAIN:
                cbbEstilo.setSelectedIndex( 0 );
                break;
            case Font.BOLD:
                cbbEstilo.setSelectedIndex( 1 );
                break;
            case Font.ITALIC:
                cbbEstilo.setSelectedIndex( 2 );
                break;
            default:
                cbbEstilo.setSelectedIndex( 0 );
        }

        txtTexto.setForeground( color );
        botonColorTitulo.setBackground( color );
    }

    /**
     * Es el método que ejecuta la acción que se debe ejecutar cuando se hace click sobre un botón
     * @param evento Es el evento del click sobre un botón
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( OK.equals( comando ) )
        {
            String texto = txtTexto.getText( );
            Font fuente = txtTexto.getFont( );

            dialogo.cambiarTitulo( texto, fuente, botonColorTitulo.getBackground( ) );
        }
        else if( CANCELAR.equals( comando ) )
        {
            dialogo.dispose( );
        }
        else if( FUENTE.equals( comando ) || TAMANO.equals( comando ) || ESTILO.equals( comando ) )
        {
            actualizarFuente( );
        }
        else if( COLOR.equals( comando ) )
        {
            Color colorT = JColorChooser.showDialog( this, "Color del fondo", botonColorTitulo.getBackground( ) );
            botonColorTitulo.setBackground( colorT );
            actualizarFuente( );
        }

    }

}