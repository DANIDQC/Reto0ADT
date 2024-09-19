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
public class ConvocatoriaExamen {
    private String convocatoria = "";
    private String descripcion = "";
    private LocalDate LocalDate;
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
        return LocalDate;
    }

    public void setLocalDate(LocalDate LocalDate) {
        this.LocalDate = LocalDate;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

}
