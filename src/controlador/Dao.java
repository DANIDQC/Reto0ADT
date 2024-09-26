
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.util.List;
import modelo.ConvocatoriaExamen;
import modelo.Enunciado;
import modelo.UnidadDidactica;

import modelo.Enunciado;

/**
 *
 * @author lucia_puwj3zw
 */
public interface Dao {
    
    public boolean crearUnidadDidactic(UnidadDidactica unidad);
    public boolean crearEnunciado(Enunciado enunciado);
    public boolean crearConvocatoria(ConvocatoriaExamen convocatoria);
    public List<UnidadDidactica> listaUnidaades(UnidadDidactica unidades);
    
}
