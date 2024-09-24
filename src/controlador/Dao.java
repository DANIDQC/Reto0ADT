/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Enunciado;

/**
 *
 * @author 2dam
 */
public interface Dao {

    /**
     *
     * @param enunciado
     * @return
     */
    public boolean crearEnunciadoDeExamen(Enunciado enunciado);
}
