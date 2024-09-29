
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
    //Esta interfaz se encarga de añadir un metodo para crear UnidadesDidacticas. 
    public boolean crearUnidadDidactica(UnidadDidactica unidad);
    //Esta interfaz se encarga de añadir un metodo para crear Enunciados. 
    public boolean crearEnunciado(Enunciado enunciado);
    //Esta interfaz se encarga de añadir un metodo para crear Convocatoria. 
    public boolean crearConvocatoria(Convocatoria convocatoria, String descripcionEnunciado);
    //Esta interfaz se encarga de añadir un metodo para listar Unidades Didacticas. 
    public List<UnidadDidactica> listaUnidaades(UnidadDidactica unidades);
    //Esta interfaz se encarga de añadir un metodo para buscar Convocatorias por Enunciado. 
    public List<Convocatoria> buscarConvocatoriasPorEnunciado(String enunciado);
    //Esta interfaz se encarga de añadir un metodo para buscar una Convocatoria en base a un nombre. 
    public Convocatoria buscarConvocatoriaPorNombre(String nombreConvocatoria);
}
