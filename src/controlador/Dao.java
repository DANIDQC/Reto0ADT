
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.util.List;
import modelo.Convocatoria;
import modelo.UnidadDidactica;
import modelo.Enunciado;

/**
 *
 * @author lucia_puwj3zw
 */
public interface Dao {

    
    public boolean crearUnidadDidactica(UnidadDidactica unidad);
    public boolean crearConvocatoria(Convocatoria convocatoria, String descripcionEnunciado);
    public List<UnidadDidactica> listaUnidaades(UnidadDidactica unidades);
    public List<Convocatoria> buscarConvocatoriasPorEnunciado(String enunciado);
   
    /**
     *
     * @param enunciado
     * @param acronimo
     * @return
     */
    public boolean crearEnunciado(Enunciado enunciado, String acronimo);
    public List<Enunciado> listaEnunciados(String acronimo);
   
             }
