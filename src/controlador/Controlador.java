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
    ImplementacionBd imple= new ImplementacionBd();
    
   /**
     *
     * @param unidadDidactica
     * @return
     */
    public boolean crearUnidadDidactica(UnidadDidactica unidadDidactica) {
        return imple.crearUnidadDidactica(unidadDidactica);
    }

    /**
     *
     * @param convocatoria
     * @return
     */
    public boolean crearConvocatoria(Convocatoria convocatoria, String descripcionEnunciado) {
        return imple.crearConvocatoria(convocatoria, descripcionEnunciado); 
    }

    /**
     *
     * @param enunciado
     * @return
     */
    public boolean crearEnunciado(Enunciado enunciado) {
        return imple.crearEnunciado(enunciado);
    }

    /**
     *
     * @param enunciadoDescripcion
     * @return
     */
    public List<Convocatoria> consultarConvocatoriasPorEnunciado(String enunciadoDescripcion) {
        return imple.buscarConvocatoriasPorEnunciado(enunciadoDescripcion); 
    }
}
