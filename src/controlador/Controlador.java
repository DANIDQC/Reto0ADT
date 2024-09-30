/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import modelo.Convocatoria;
import modelo.Enunciado;
import modelo.ImplementacionBd;
import modelo.UnidadDidactica;

/**
 *
 * @author 2dam
 */
public class Controlador {
    ImplementacionBd imple = new ImplementacionBd();
    /**
     *
     * @param enunciado
     * @param acronimo
     * @return
     */

    public boolean crearEnunciado(Enunciado enunciado, String acronimo){
        boolean crearEnunciado = imple.crearEnunciado(enunciado, acronimo);
        return crearEnunciado;    }
    
    
   public List<UnidadDidactica> listaUnidades(UnidadDidactica unidades){
        List <UnidadDidactica> listaUnidades=imple.listaUnidades(unidades);
        return listaUnidades;
                
   }

    public List<Convocatoria> consultarConvocatoriasPorEnunciado(String enunciadoDescripcion) {
 List<Convocatoria> convocatorias = imple.buscarConvocatoriasPorEnunciado(enunciadoDescripcion);
    return convocatorias; // Retorna la lista de convocatorias encontradas    }
}

    public boolean crearUnidadDidactica(UnidadDidactica unidadDidactica) {
        boolean crearUnidadDidactica = imple.crearUnidadDidactica(unidadDidactica);
        return crearUnidadDidactica;    
    }
    
    /**
     *
     * @param convocatoria
     * @param descripcionEnunciado
     * @return
     */
    public boolean crearConvocatoria(Convocatoria convocatoria, String descripcionEnunciado) {
        boolean crearConvocatoria = imple.crearConvocatoria(convocatoria, descripcionEnunciado);
        return crearConvocatoria;    
    }
   
    /**
     *
     * @param enunciadoDescripcion
     * @return
     */
    public List<Convocatoria> consultarConvocatoriasPorEnunciado(String enunciadoDescripcion) {
        return imple.buscarConvocatoriasPorEnunciado(enunciadoDescripcion); 
    }

    /**
    *
    * @param nombreConvocatoria
    * @return
    */
    public Convocatoria buscarConvocatoriaPorNombre(String nombreConvocatoria) {
        return imple.buscarConvocatoriaPorNombre(nombreConvocatoria);
    }

    /**
    *
    * @param nombreConvocatoria
    * @return
    */
    public String obtenerNombreEnunciadoPorConvocatoria(String nombreConvocatoria) {
        return imple.obtenerNombreEnunciadoPorConvocatoria(nombreConvocatoria);
    }

    /**
    *
    * @param nombreConvocatoria, nuevoNombreEnunciado
    * @return
    */
    public boolean actualizarEnunciadoConvocatoria(String nombreConvocatoria, String nuevoNombreEnunciado) {
        return imple.actualizarEnunciadoConvocatoria(nombreConvocatoria, nuevoNombreEnunciado);
    }

}
