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
    private Integer idEnunciado = 0;

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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
     
    public Integer getIdEnunciado() {
        return idEnunciado;
    }

    public void setIdEnunciado(Int idEnunciado) {
        this.idEnunciado = idEnunciado;
    }
}
