/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

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
     * @param enunciado
     * @return
     */
    public boolean crearEnunciado(Enunciado enunciado ){
        boolean crearEnunciado = imple.crearEnunciado(enunciado);
        return crearEnunciado;    }

    public boolean crearUnidadDidactica(UnidadDidactica unidadDidactica) {
         boolean crearUnidadDidactica = imple.crearUnidadDidactica(unidadDidactica);
        return crearUnidadDidactica;    }
    
}
