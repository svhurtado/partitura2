/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: PanelModificarInformacionNota.java 600 2006-11-06 06:16:53Z da-romer $ 
 * Universidad de los Andes (Bogotá - Colombia) 
 * Departamento de Ingeniería de Sistemas y Computación 
 * Todos los derechos reservados 2005 
 * 
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_partitura
 * Autor: Daniel Francisco Romero - Marzo 23, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package partitura.interfaz;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel donde se muestra la información para modificar una nota
 */
public class PanelModificarInformacionNota extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String CLASE = "Clase";

    private static final String TIPO = "Tipo";

    private static final String COLOR = "Color";

    private static final String NOMBRE = "Nombre";

    private static final String CANCELAR = "Cancelar";

    private static final String OK = "Ok";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Posición de la nota a ser modificada
     */
    private int posicionNota;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es una referencia al diálogo que contiene este panel
     */
    private DialogoInformacionNota dialogo;

    /**
     * Es el comboBox para seleccionar la clase
     */
    private JComboBox cbbClase;

    /**
     * Es el comboBox para seleccionar el tipo
     */
    private JComboBox cbbTipo;

    /**
     * Es el comboBox para seleccionar el nombre
     */
    private JComboBox cbbNombre;

    /**
     * Es el botón para seleccionar el color
     */
    private JButton botonColor;

    /**
     * Etiqueta con el nombre "Clase"
     */
    private JLabel etiquetaClase;

    /**
     * Etiqueta con el nombre "Tipo"
     */
    private JLabel etiquetaTipo;

    /**
     * Etiqueta con el nombre "Color"
     */
    private JLabel etiquetaColor;

    /**
     * Etiqueta con el nombre "Color"
     */
    private JLabel etiquetaNombre;

    /**
     * Es el botón Ok
     */
    private JButton botonOk;

    /**
     * Es el botón Cancelar
     */
    private JButton botonCancelar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Inicializa el panel
     * @param din Es una referencia al diálogo en el que se encuentra el panel
     * @param posNota La posición de la nota que se va a modificar
     */
    public PanelModificarInformacionNota( DialogoInformacionNota din, int posNota )
    {
        dialogo = din;

        posicionNota = posNota;

        // setLayout( new GridLayout(5,2) );
        setLayout( new GridBagLayout( ) );

        // Inicializa los componentes
        inicializarComponentes( );

        // Ubica los componentes
        ubicarComponentes( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Ubica los componentes en sus posiciones <br>
     * <b>pre: </b>Los componentes ya están inicializados
     */
    private void ubicarComponentes( )
    {

        GridBagConstraints gbc = new GridBagConstraints( 0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( etiquetaClase, gbc );
        gbc = new GridBagConstraints( 1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( cbbClase, gbc );
        gbc = new GridBagConstraints( 0, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( etiquetaTipo, gbc );
        gbc = new GridBagConstraints( 1, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( cbbTipo, gbc );

        gbc = new GridBagConstraints( 0, 3, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( etiquetaNombre, gbc );
        gbc = new GridBagConstraints( 1, 3, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( cbbNombre, gbc );
        gbc = new GridBagConstraints( 0, 4, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( etiquetaColor, gbc );
        gbc = new GridBagConstraints( 1, 4, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( botonColor, gbc );

        JPanel panelBotones = new JPanel( );
        panelBotones.setLayout( new GridLayout( 1, 2 ) );
        panelBotones.add( botonOk );
        panelBotones.add( botonCancelar );

        gbc = new GridBagConstraints( 0, 5, 3, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( panelBotones, gbc );
    }

    /**
     * Inicializa los componentes
     * @param fuentes Es una lista con los nombres de las fuentes instaladas en el sistema
     */
    private void inicializarComponentes( )
    {
        cbbClase = new JComboBox( );
        cbbClase.setActionCommand( CLASE );
        cbbClase.addItem( "Redonda" );
        cbbClase.addItem( "Blanca" );
        cbbClase.addItem( "Negra" );
        cbbClase.addItem( "Corchea" );
        cbbClase.addItem( "Semicorchea" );
        cbbClase.addActionListener( this );

        cbbTipo = new JComboBox( );
        cbbTipo.setActionCommand( TIPO );
        cbbTipo.addItem( "Normal" );
        cbbTipo.addItem( "Sostenido" );

        cbbNombre = new JComboBox( );
        cbbNombre.setActionCommand( NOMBRE );
        cbbNombre.addItem( "do" );
        cbbNombre.addItem( "re" );
        cbbNombre.addItem( "mi" );
        cbbNombre.addItem( "fa" );
        cbbNombre.addItem( "sol" );
        cbbNombre.addItem( "la" );
        cbbNombre.addItem( "si" );
        cbbNombre.addItem( "do2" );

        botonColor = new JButton( );
        botonColor.setActionCommand( COLOR );
        botonColor.addActionListener( this );

        botonCancelar = new JButton( );
        botonCancelar.setActionCommand( CANCELAR );
        botonCancelar.setText( "Cancelar" );
        botonCancelar.addActionListener( this );

        botonOk = new JButton( );
        botonOk.setActionCommand( OK );
        botonOk.setText( "OK" );
        botonOk.addActionListener( this );

        etiquetaClase = new JLabel( "Clase" );

        etiquetaTipo = new JLabel( "Tipo" );

        etiquetaColor = new JLabel( "Color" );

        etiquetaNombre = new JLabel( "Nombre" );

    }

    /**
     * Cambia el texto mostrado y actualiza los combo box para mostrar la fuente indicada
     * @param texto El texto que se va a mostrar
     * @param fuente La fuente actual del texto
     * @param nombre El nombre de la nota
     * @param color El color de la nota
     */
    public void cambiarInformacion( String clase, String tipo, String nombre, Color color )
    {
        cbbClase.setSelectedItem( clase );

        cbbTipo.setSelectedItem( tipo );

        cbbNombre.setSelectedItem( nombre.toLowerCase( ) );

        botonColor.setBackground( color );
    }

    /**
     * Es el método que ejecuta la acción que se debe ejecutar cuando se hace click sobre un botón o cuando cambia la opción seleccionada en uno de los comboBox
     * @param evento Es el evento del click sobre un botón
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( OK.equals( comando ) )
        {
            String clase = ( String )cbbClase.getSelectedItem( );
            String tipo = ( String )cbbTipo.getSelectedItem( );
            Color color = botonColor.getBackground( );
            String nombre = ( ( String )cbbNombre.getSelectedItem( ) ).toUpperCase( );

            dialogo.cambiarInformacionNota( clase, tipo, color, posicionNota, nombre );
        }
        else if( CANCELAR.equals( comando ) )
        {
            dialogo.dispose( );
        }
        else if( COLOR.equals( comando ) )
        {
            Color colorT = JColorChooser.showDialog( this, "Color del fondo", botonColor.getBackground( ) );
            botonColor.setBackground( colorT );
        }

    }
}
