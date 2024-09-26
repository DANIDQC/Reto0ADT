/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;



/**
 *
 * @author 2dam
 */
public class Convocatoria {
    private String convocatoria = "";
    private String descripcion = "";
    private LocalDate fecha;
    private String curso = "";

    public String getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(String convocatoria) {
        this.convocatoria = convocatoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getLocalDate() {
        return fecha;
    }

    public void setLocalDate(LocalDate LocalDate) {
        this.fecha = LocalDate;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

}
