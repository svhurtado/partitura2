/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazPartitura.java 600 2006-11-06 06:16:53Z da-romer $ 
 * Universidad de los Andes (Bogotá - Colombia) 
 * Departamento de Ingeniería de Sistemas y Computación 
 * Todos los derechos reservados 2005 
 * 
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_partitura
 * Autor: Diana Puentes - Jul 29, 2005
 * Modificado por: Daniel Francisco Romero - Marzo 23, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package partitura.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import partitura.mundo.FormatoInvalidoExcepcion;
import partitura.mundo.INota;
import partitura.mundo.Nota;
import partitura.mundo.Partitura;

/**
 * Ventana principal de la aplicación
 */
public class InterfazPartitura extends JFrame
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    public final static int LIBRE = 1;

    public final static int GUIADA = 0;

    public static final int COMPONER = 2;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Representa una partitura que tiene cargada una canción
     */
    private Partitura partitura;

    /**
     * Es el archivo donde se está salvando actualmente la partitura
     */
    private String archivo;

    /**
     * reproduce notas en la interfaz
     */
    private ReproductorNota reproductor;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Representa el panel que muestra la partitura
     */
    private PanelPartitura panelPartitura;

    /**
     * Representa el panel que muestra el teclado y realiza acciones de tocar notas
     */
    private PanelTeclado panelTeclado;

    /**
     * Representa el panel de acciones
     */
    private PanelOpciones panelOpciones;

    /**
     * Es la ruta hasta el último directorio de donde se cargó o salvó un archivo
     */
    private String ultimoDirectorio;

    /**
     * Es la barra del menú
     */
    private BarraMenu barra;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la ventana principal de la aplicación y ubica los elementos que la<br>
     * componen en un orden adecuado
     */
    public InterfazPartitura( )
    {
        partitura = new Partitura( );
        panelTeclado = new PanelTeclado( this );
        panelPartitura = new PanelPartitura( this );
        panelOpciones = new PanelOpciones( this );

        getContentPane( ).setLayout( new BorderLayout( ) );
        getContentPane( ).add( panelOpciones, BorderLayout.SOUTH );
        getContentPane( ).add( panelTeclado, BorderLayout.CENTER );
        getContentPane( ).add( panelPartitura, BorderLayout.NORTH );

        setTitle( "PartituraMusical" );
        setSize( 1030, 700 );
        setResizable( true );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        barra = new BarraMenu( this );
        setJMenuBar( barra );
        ultimoDirectorio = "./data";

        reproductor = new ReproductorNota( this );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Coloca la posición de la nota marcada de la partitura en -1 y <br>
     * hace que la partitura se pinte de nuevo
     */
    public void reiniciarPartitura( )
    {
        cambiarPosicionNotaMarcada( -1 );
        panelPartitura.actualizar( );
    }

    /**
     * Borra las notas y título de la partitura y hace que se pinte de nuevo
     */
    public void limpiarPartitura( )
    {
        partitura.eliminarNotas( );
        partitura.cambiarTitulo( "" );
        partitura.cambiarNotaMarcada( -1 );
        panelPartitura.actualizar( );
        validate( );
    }

    /**
     * Cambia el tipo de práctica con el modo especificado.
     * @param modo - Indica el modo de practica (LIBRE, PARTITURA ó GUIADA)
     */
    public void cambiarModo( int modo )
    {
        panelTeclado.cambiarModo( modo );

        if( panelTeclado.darModo( ) != COMPONER )
        {
            panelTeclado.desbloquearTeclado( );
        }
        else if( partitura.darNotas( ).size( ) == Partitura.NUM_MAX_NOTAS )
        {
            panelTeclado.bloquearTeclado( );
        }
    }

    /**
     * Da una lista con las notas de la partitura cargada en el mundo
     * @return Arraylist con las notas
     */
    public ArrayList darNotas( )
    {
        return partitura.darNotas( );
    }

    /**
     * Le pide al usuario un archivo para abrir y lo carga en el panel de la partitura <br>
     * <b>post: </b>Se cargó una partitura que estaba guardada
     */
    public void abrir( )
    {
        boolean abrir = pedirConfirmacion( );
        if( abrir )
        {
            JFileChooser fc = new JFileChooser( ultimoDirectorio );
            fc.setDialogTitle( "Abrir Partirura" );
            fc.setFileSelectionMode( JFileChooser.FILES_ONLY );
            fc.setMultiSelectionEnabled( false );

            int resultado = fc.showOpenDialog( this );

            if( resultado == JFileChooser.APPROVE_OPTION )
            {
                File seleccionado = fc.getSelectedFile( );
                ultimoDirectorio = seleccionado.getParentFile( ).getAbsolutePath( );

                try
                {

                    partitura.abrir( seleccionado.getAbsolutePath( ) );
                    panelPartitura.actualizar( );
                }
                catch( FormatoInvalidoExcepcion e )
                {
                    JOptionPane.showMessageDialog( this, "Hubo problemas cargando el archivo:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
                }
                catch( IOException e )
                {
                    JOptionPane.showMessageDialog( this, "Hubo problemas cargando el archivo:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
                }
            }
        }
    }

    /**
     * Este método solicita una confirmación para realizar una acción que haría que el trabajo se perdiera. <br>
     * Presenta una ventana con las opciones "Si","No" y "Cancelar". <br>
     * Si se selecciona "Si", entonces se salva el archivo actual. <br>
     * Si se selecciona "No", el archivo no se salva y se retorna true <br>
     * Si se selecciona "Cancelar", el archivo no se salva pero se retorna false para que la acción no se realice.
     * @return Retorna true si la acción se debe realizar; retorna false en caso contrario.
     */
    private boolean pedirConfirmacion( )
    {
        boolean cerrar = true;

        int respuesta = JOptionPane.showConfirmDialog( this, "Desea guardar el archivo actual antes de continuar?", "Salvar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE );

        if( respuesta == JOptionPane.YES_OPTION )
        {
            salvar( );
        }

        else if( respuesta == JOptionPane.NO_OPTION )
        {
            // No hace nada
        }

        else if( respuesta == JOptionPane.CANCEL_OPTION )
        {
            cerrar = false;
        }

        return cerrar;
    }

    /**
     * Salva la partitura en el archivo del que se había cargado o donde se había salvado la última vez. <br>
     * Si se trata de una partitura nueva y no se ha salvado entonces se pregunta el nombre del archivo donde se salvará. <br>
     * <b>post: </b> Se salvó la partitura.
     */
    public void salvar( )
    {
        String nombreArchivo = partitura.darNombreArchivo( );
        if( nombreArchivo == null )
        {
            salvarComo( );
        }
        else
        {
            try
            {
                partitura.salvar( );
            }
            catch( IOException e )
            {
                JOptionPane.showMessageDialog( this, "Hubo problemas salvando el archivo:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     * Salva la partitura en un archivo del cual se le pregunta el nombre al usuario. <br>
     * <b>post: </b> Se salvó la partitura en un archivo que se le preguntó al usuario.
     */
    public void salvarComo( )
    {
        JFileChooser fc = new JFileChooser( ultimoDirectorio );
        fc.setDialogTitle( "Guardar como" );
        fc.setFileSelectionMode( JFileChooser.FILES_ONLY );
        fc.setMultiSelectionEnabled( false );

        boolean termine = false;

        int resultado = fc.showSaveDialog( this );

        while( !termine )
        {
            if( resultado == JFileChooser.APPROVE_OPTION )
            {
                File seleccionado = fc.getSelectedFile( );
                ultimoDirectorio = seleccionado.getParentFile( ).getAbsolutePath( );

                int respuesta = JOptionPane.YES_OPTION;

                // Si el archivo ya existe hay que pedir confirmación para
                // sobrescribirlo
                if( seleccionado.exists( ) )
                {
                    respuesta = JOptionPane.showConfirmDialog( this, "¿Desea sobrescribir el archivo seleccionado?", "Sobrescribir", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE );
                }

                // Si la respuesta fue positiva (o si no fue necesario hacer la
                // pregunta) se graba el archivo
                if( respuesta == JOptionPane.YES_OPTION )
                {
                    try
                    {
                        partitura.salvar( seleccionado.getAbsolutePath( ) );
                        termine = true;
                    }
                    catch( IOException e )
                    {
                        JOptionPane.showMessageDialog( this, "Hubo problemas guardando el archivo:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
                    }
                }
                else
                {
                    resultado = fc.showSaveDialog( this );
                }
            }
            else
            {
                termine = true;
            }
        }
    }

    /**
     * Muestra la ventana de texto utilizada para modificar el título de la partitura cuando se hace doble clic.
     */
    public void mostrarVentanaTitulo( )
    {
        String titulo = "";
        String nomDialogo;
        Font fuente = partitura.darFuenteTitulo( );

        titulo = partitura.darTitulo( );
        nomDialogo = "Titulo Partitura";
        Color colorT = partitura.darColorTitulo( );

        DialogoTituloPartitura dt = new DialogoTituloPartitura( this, titulo, fuente, nomDialogo, colorT );
        dt.setVisible( true );

    }

    /**
     * Muestra la ventana para modificar la información de la nota
     * @param pos La posición de la nota a ser modificada
     * 
     */
    public void mostrarVentanaInformacionNota( int pos )
    {
        String clase;
        String tipo;
        String nombre;
        Color color;
        String nomDialogo;
        INota nota;

        nota = partitura.darNota( pos );
        clase = nota.darClase( );
        tipo = nota.darTipo( );
        nombre = nota.darNombre( );
        color = nota.darColor( );
        nomDialogo = "Información Nota";

        DialogoInformacionNota di = new DialogoInformacionNota( this, clase, tipo, nombre, color, pos, nomDialogo );
        di.setVisible( true );

    }

    /**
     * Cambia el título de la partitura. <br>
     * <b>post: </b> Se modificó el título de la partitura.
     * @param titulo El titulo que va a tener la partitura
     * @param fuente La fuente del título de la partitura
     * @param color El color que va a tener el título de la partitura
     */
    public void cambiarTituloPartitura( String titulo, Font fuente, Color color )
    {
        if( partitura != null )
        {
            partitura.cambiarFuenteTitulo( fuente );
            partitura.cambiarTitulo( titulo );
            partitura.cambiarColorTitulo( color );

            repintarPartitura( );
        }
    }

    /**
     * Elimina la nota en la posición especificada
     * @param posNota Posición de la nota a ser eliminada
     */
    public void eliminarNota( int posNota )
    {

        partitura.eliminarNota( posNota );

        if( posNota == partitura.darPosicionNotaMarcada( ) )
        {
            cambiarPosicionNotaMarcada( posNota - 1 );
        }

        repintarPartitura( );

        if( panelTeclado.darModo( ) == COMPONER && partitura.darNotas( ).size( ) < Partitura.NUM_MAX_NOTAS )
        {
            panelTeclado.desbloquearTeclado( );
        }

    }

    /**
     * Cambia la información de la nota correspondiente a la posición especificada
     * @param clase La clase de la nota (redonda, blanca, negra, corchea, semicorchea)
     * @param tipo El tipo de la nota (normal o sostenido)
     * @param color El color de la nota
     * @param posicion La posición de la nota a ser modificada
     * @param nombre El nombre de la nota (do, re mi, fa sol, la, si, do2)
     */
    public void cambiarInformacionNota( String clase, String tipo, Color color, int posicion, String nombre )
    {
        partitura.cambiarInformacionNota( clase, tipo, color, posicion, nombre );
        repintarPartitura( );

    }

    /**
     * Cambia la nota marcada en la partitura
     * @param pos Posición de la nota a ser marcada
     */
    public void cambiarPosicionNotaMarcada( int pos )
    {

        partitura.cambiarNotaMarcada( pos );
    }

    /**
     * Pinta de nuevo la partitura
     */
    public void repintarPartitura( )
    {
        panelPartitura.actualizar( );

    }

    /**
     * Verifica que la nota tocada sea la correcta
     * @param nota La nota a ser verificada
     * @param posicionActual La posición de la nota
     * @return Indica si la nota tocada coincide con la nota que se debe tocar en la posición actual de la partitura
     */
    public boolean verificarNota( INota nota, int posicionActual )
    {

        return partitura.verificarNota( nota, posicionActual );
    }

    /**
     * Adiciona la nota tocada por el usuario a la partitura
     * @param nota Nota a ser adicionada
     */
    public void adicionarNotaPartitura( INota nota )
    {
        partitura.adicionarNota( nota );

        // Si la partitura está en modo composición y número máximo de notas de composición se ha alcanzado se bloquea el teclado
        if( panelTeclado.darModo( ) == COMPONER && partitura.darNotas( ).size( ) == Partitura.NUM_MAX_NOTAS )
        {
            panelTeclado.bloquearTeclado( );
        }
    }

    /**
     * Toca la nota dada especificada. El método realiza lo siguiente: <br>
     * 1. Verifica que la posición de la nota marcada no sea mayor o igual al número máximo de notas de la partitura. Si esto no se cumple se pone la posición de la nota
     * marcada en -1. <br>
     * 2. Si el modo es guiada, obtiene la información de la nota marcada (clase y color)e incrementa la posición de la nota marcada se incrementa en 1. <br>
     * 3. Si el modo es componer obtiene la clase de nota ingresada por el usuario. <br>
     * 4. Obtiene la nota a ser tocada. <br>
     * 5. Toca la nota 6. Si el modo es componer, adiciona la nota tocada a la partitura
     * @param nombreNota Nombre de la nota que oprimió el usuario en el teclado
     * 
     */
    public void tocarNota( String nombreNota )
    {
        INota nota = null;
        INota aux = null;
        String claseNota = "Negra";
        Color color = Color.BLACK;
        int posNota = -1;
        int posicionActual = darPosicionNotaMarcada( );

        if( posicionActual + 1 >= darNotas( ).size( ) )
        {
            posicionActual = -1;
            cambiarPosicionNotaMarcada( posicionActual );
        }

        if( panelTeclado.darModo( ) == GUIADA )
        {
            aux = ( INota )darNotas( ).get( posicionActual + 1 );
            claseNota = aux.darClase( );
            color = aux.darColor( );
            posNota = posicionActual + 1;
        }
        else if( panelTeclado.darModo( ) == COMPONER )
        {
            claseNota = darClaseNotaSeleccionada( );
        }

        nota = obtenerNota( nombreNota, claseNota, color );
        tocarSonido( nota, posNota );

        if( panelTeclado.darModo( ) == InterfazPartitura.COMPONER )
        {
            adicionarNotaPartitura( nota );
        }

    }

    /**
     * Obtiene la nota basándose en la información proporcionada
     * @param nombreNota El nombre de la nota
     * @param claseNota La clase a la que pertenece la nota
     * @param color El color de la nota
     * @return La nota construida a partir de la información dada
     */
    private INota obtenerNota( String nombreNota, String claseNota, Color color )
    {
        INota nota = null;
        if( nombreNota.endsWith( "#" ) )
        {
            nombreNota = nombreNota.substring( 0, nombreNota.length( ) - 1 );
            nota = partitura.crearNota( claseNota, nombreNota, Nota.SOSTENIDO, color );
        }
        else
        {
            nota = partitura.crearNota( claseNota, nombreNota, Nota.NORMAL, color );
        }
        return nota;
    }

    /**
     * Toca la melodía de la partitura
     */
    public void tocarMelodia( )
    {
        // se ubica en practica libre
        cambiarModo( GUIADA );
        reiniciarPartitura( );
        ArrayList notas = darNotas( );

        int contador = 1;
        // principal.repintarPartitura( principal.darNotas( ), posicionActual );
        while( contador < notas.size( ) + 1 )
        {
            INota nota1 = ( INota )notas.get( contador - 1 );

            try
            {

                tocarSonido( nota1, contador - 1 );
            }
            catch( Exception e )
            {
                JOptionPane.showMessageDialog( this, "Error al tocar la nota" + e.getMessage( ) );
                e.printStackTrace( );
            }

            contador++;
        }

        cambiarPosicionNotaMarcada( -1 );

    }

    /**
     * Toca un sonido dado el nombre, el tipo, y su número de representación en el reproductor. <br>
     * En modo de práctica el sonido de la nota sólo se reproduce si se toca la nota adecuada según <br>
     * la partitura; si la nota estaba equivocada se toca una corneta. <br>
     * Si se está en modo libre siempre se toca la nota seleccionada.
     * @param nota - La nota a ser tocada
     * @param posNota - Es la posición de la nota que se va a tocar
     */
    private void tocarSonido( INota nota, int posNota )
    {
        String nombre = nota.darNombre( );
        String tipo = nota.darTipo( );
        double duracion = nota.darDuracion( );
        int notaReproducir = darNumeroNota( nombre, tipo );
        // si se esta en modo partitura se realizan las verificaciones correspondientes

        if( panelTeclado.darModo( ) == InterfazPartitura.GUIADA )
        {
            // verificación de la nota
            boolean notaCorrecta = verificarNota( nota, darPosicionNotaMarcada( ) + 1 );

            // si la nota esta correcta se toca la nota y se avanza una posición en la partitura
            if( notaCorrecta )
            {
                // Tocar Nota

                cambiarPosicionNotaMarcada( posNota );
                reproductor.tocarNota( notaReproducir, 0, duracion, posNota );
            }
            else
            {
                // si la nota no esta correcta toca la nota 30 en el canal 15 del syntetizador (corneta)
                reproductor.tocarNota( 30, 15, 2, -1 );
            }

        }
        else
        {
            // Tocar Nota si se esta en modo libre
            reproductor.tocarNota( notaReproducir, 0, duracion, -1 );
        }

    }

    /**
     * Retorna el número que representa una nota tocada en el reproductor. <br>
     * En este caso va de 60 a 72 donde 60 es Do y 72 es el Do de la siguiente octava.
     * @param nombre El nombre de la nota que se tocó
     * @param tipo Indica si la nota fue normal o sostenida.
     * @return Retorna el número de la nota para el reproductor
     */
    private int darNumeroNota( String nombre, String tipo )
    {
        if( nombre.equals( Nota.DO ) )
        {
            if( tipo.equals( Nota.NORMAL ) )
            {
                return 60;
            }
            else if( tipo.equals( Nota.SOSTENIDO ) )
            {
                return 61;
            }
        }
        else if( nombre.equals( Nota.RE ) )
        {
            if( tipo.equals( Nota.NORMAL ) )
            {
                return 62;
            }
            else if( tipo.equals( Nota.SOSTENIDO ) )
            {
                return 63;
            }
        }
        else if( nombre.equals( Nota.MI ) )
        {
            return 64;
        }
        else if( nombre.equals( Nota.FA ) )
        {
            if( tipo.equals( Nota.NORMAL ) )
            {
                return 65;
            }
            else if( tipo.equals( Nota.SOSTENIDO ) )
            {
                return 66;
            }
        }
        else if( nombre.equals( Nota.SOL ) )
        {
            if( tipo.equals( Nota.NORMAL ) )
            {
                return 67;
            }
            else if( tipo.equals( Nota.SOSTENIDO ) )
            {
                return 68;
            }
        }
        else if( nombre.equals( Nota.LA ) )
        {
            if( tipo.equals( Nota.NORMAL ) )
            {
                return 69;
            }
            else if( tipo.equals( Nota.SOSTENIDO ) )
            {
                return 70;
            }
        }
        else if( nombre.equals( Nota.SI ) )
        {
            return 71;
        }
        else if( nombre.equals( Nota.DO2 ) )
        {
            return 72;
        }
        return 0;
    }

    /**
     * Retorna la clase de nota seleccionada en el panel de opciones
     * @return La clase de nota seleccionada
     */
    public String darClaseNotaSeleccionada( )
    {

        return panelOpciones.darClaseNota( );
    }

    /**
     * Retorna la posición de la nota que se encuentra marcada actualmente
     * @return Posición de la nota marcada actualmente
     */
    public int darPosicionNotaMarcada( )
    {

        return partitura.darPosicionNotaMarcada( );
    }

    /**
     * Manda a pintar la partitura
     * @param g El área donde se va a pintar
     */
    public void pintarPartitura( Graphics2D g )
    {
        partitura.pintarPartitura( g );
    }

    /**
     * Busca la nota donde se hizo el clic del ratón
     * @param x Coordenada x del clic del ratón
     * @param y Coordenada y del clic del ratón
     * @return La posición de la nota que "recibió" el clic. -1 si ninguna
     */
    public int buscarNota( int x, int y )
    {
        return partitura.buscarNota( x, y );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Ejecuta la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String respuesta = partitura.metodo1( );
        JOptionPane.showMessageDialog( this, respuesta, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Ejecuta la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String respuesta = partitura.metodo2( );
        JOptionPane.showMessageDialog( this, respuesta, "Respuesta", JOptionPane.INFORMATION_MESSAGE );

    }

    // -----------------------------------------------------------------
    // Ejecución
    // -----------------------------------------------------------------

    /**
     * Inicializa la aplicación
     * @param args Son los parámetros de ejecución de la aplicación. No se deben usar.
     */
    public static void main( String[] args )
    {
        InterfazPartitura i = new InterfazPartitura( );
        i.setVisible( true );
    }
}
