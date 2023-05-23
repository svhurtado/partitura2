/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: ReproductorNota.java 516 2006-10-23 05:06:15Z da-romer $ 
 * Universidad de los Andes (Bogotá - Colombia) 
 * Departamento de Ingeniería de Sistemas y Computación 
 * Todos los derechos reservados 2005 
 * 
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n5_partitura
 * Autor: Diana Puentes - Jul 29, 2005
 * Modificado por: Daniel Francisco Romero Acero - 25-ene-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package partitura.interfaz;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/**
 * Clase que permite tocar una nota
 */
public class ReproductorNota
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el canal que se usa para reproducir las notas
     */
    private MidiChannel channel;

    /**
     * Es el sintetizador con el que se reproducen las notas
     */
    private Synthesizer synth;

    private InterfazPartitura principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo reproductor de notas a partir de un sintetizador
     */
    public ReproductorNota( InterfazPartitura p )
    {
        try
        {
            // obtiene un sintetizador del sistema
            synth = MidiSystem.getSynthesizer( );
            // abre el sintetizador
            synth.open( );
            principal = p;
        }
        catch( MidiUnavailableException e )
        {
            e.printStackTrace( );
        }
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Toca la nota indicada, las notas van de 0 a 120 siendo 60 el Do central, 61 Do#, etc
     * @param nota Valor numérico de la nota a reproducir
     * @param canal El instrumento con el que se quiere tocar la nota. Van de 0 a 15. 0, es el piano
     * @param duracion La duración de la nota
     * @param posNota La posición de la nota que se está tocando
     */
    public synchronized void tocarNota( int nota, int canal, double duracion, int posNota )
    {
        final int notaT = nota;
        final int canalT = canal;
        final double dur = duracion;
        final int pNota = posNota;

        Thread t = new Thread( new Runnable( )
        {
            public void run( )
            {
                synchronized( synth )
                {

                    // define el instrumento a tocar
                    channel = synth.getChannels( )[ canalT ];
                    // prende la nota indicada en el numero dado, con un tiempo de decaimiento promedio
                    channel.noteOn( notaT, 100 );

                    // Pinta la nota como marcada
                    if( pNota != -1 )
                    {
                        principal.cambiarPosicionNotaMarcada( pNota );
                    }

                    principal.repintarPartitura( );

                    // Realiza una espera para darle duración a la nota
                    try
                    {
                        Thread.yield( );
                        Thread.sleep( ( long ) ( dur * 200 ) );
                    }
                    catch( InterruptedException e )
                    {
                        e.printStackTrace( );
                    }

                    // apaga la nota tocada
                    channel.noteOff( notaT, 100 );
                }
            }
        } );

        t.run( );
    }

}
