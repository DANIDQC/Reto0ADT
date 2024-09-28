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
        boolean crearUnidadDidactica = imple.crearUnidadDidactica(unidadDidactica);
        return crearUnidadDidactica;    }
    
    /**
     *
     * @param convocatoria
     * @return
     */
    public boolean crearConvocatoria(Convocatoria convocatoria) {
        boolean crearConvocatoria = imple.crearConvocatoria(convocatoria);
        return crearConvocatoria;    }
    
    /**
     *
     * @param enunciado
     * @return
     */
    public boolean crearEnunciado(Enunciado enunciado ){
        boolean crearEnunciado = imple.crearEnunciado(enunciado);
        return crearEnunciado;    }
    
    /**
     *
     * @param enunciadoDescripcion
     * @return
     */
    public List<Convocatoria> consultarConvocatoriasPorEnunciado(String enunciadoDescripcion) {
    List<Convocatoria> convocatorias = imple.buscarConvocatoriasPorEnunciado(enunciadoDescripcion);
    return convocatorias; // Retorna la lista de convocatorias encontradas
}
}
