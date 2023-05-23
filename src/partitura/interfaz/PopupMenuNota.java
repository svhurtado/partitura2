/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: PopupMenuNota.java 463 2006-10-10 15:45:29Z da-romer $ 
 * Universidad de los Andes (Bogotá - Colombia) 
 * Departamento de Ingeniería de Sistemas y Computación 
 * Todos los derechos reservados 2005 
 * 
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_partitura
 * Autor: Daniel Francisco Romero Acero - 25-ene-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package partitura.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

/**
 * Menú con las opciones para modificar una nota
 */
public class PopupMenuNota extends JPopupMenu implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String ELIMINAR = "Eliminar";

    private static final String CAMBIAR_INFORMACION = "CambiarInformacion";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Es una referencia a la partitura en la que se despliega el menú
     */
    private PanelPartitura panel;

    /**
     * Posición de la nota asociada con el menú
     */
    private int posNota;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * La opción para eliminar una nota
     */
    JMenuItem itemEliminar;

    /**
     * la opción para cambiar los atributos de la nota
     */
    JMenuItem itemCambiarInformacionNota;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el popup menú
     * @param panel Es una referencia al panel en el que se despliega el menú
     * @param pNota Posición de la nota asociada con el menú
     */
    public PopupMenuNota( PanelPartitura pP, int pNota )
    {
        panel = pP;
        posNota = pNota;
        setInvoker( panel );

        itemCambiarInformacionNota = new JMenuItem( "Cambiar Información" );
        itemCambiarInformacionNota.setActionCommand( CAMBIAR_INFORMACION );
        itemCambiarInformacionNota.addActionListener( this );
        add( itemCambiarInformacionNota );

        itemEliminar = new JMenuItem( "Eliminar" );
        itemEliminar.setActionCommand( ELIMINAR );
        itemEliminar.addActionListener( this );
        add( itemEliminar );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Ejecuta la acción que corresponde a la opción del menú que fue seleccionada
     * @param evento Es el evento de seleccionar una opción del menú
     */
    public void actionPerformed( ActionEvent evento )
    {

        String comando = evento.getActionCommand( );

        if( CAMBIAR_INFORMACION.equals( comando ) )
        {
            panel.mostrarVentanaInformacionNota( posNota );
        }
        else if( ELIMINAR.equals( comando ) )
        {
            pedirConfirmacion( );
        }
    }

    /**
     * Este método solicita una confirmación para eliminar una nota. <br>
     * Presenta una ventana con las opciones "Si" y "No". <br>
     * Si se selecciona "Si", entonces se elimina la nota. <br>
     * Si se selecciona "No", la nota no se elimina <br>
     */
    private void pedirConfirmacion( )
    {

        int respuesta = JOptionPane.showConfirmDialog( panel, "Desea eliminar la nota?", "Eliminar Nota", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE );

        if( respuesta == JOptionPane.YES_OPTION )
        {
            panel.eliminarNota( posNota );
        }
    }

}
