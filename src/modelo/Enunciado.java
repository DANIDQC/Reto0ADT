/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 2dam
 */
public class Enunciado {
    private String descripcion = "";
    private String dificultad;
    private boolean disponible = true;
    private String ruta;
    private List <Integer> unidades = new ArrayList<>();
    
   public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }
    public List<Integer> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<Integer> unidades) {
        this.unidades = unidades;
    }
    

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    public void introducirUnidades(){
        
    }

    @Override
    public String toString() {
        return "Enunciado{ descripcion=" + descripcion + ", dificultad=" + dificultad + ", disponible=" + disponible + ", ruta=" + ruta + ", unidades=" + unidades + '}';
    }
    
}
