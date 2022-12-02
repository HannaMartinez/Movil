package com.bs.recyapp.activity.ui.Inicio;

public class DatosPaso {
    private String manualidad;
    private String descripcion;
    private String foto;


    public DatosPaso() {
    }

    public DatosPaso(String manualidad, String descripcion, String foto) {
        this.manualidad = manualidad;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public String getManualidad() {
        return manualidad;
    }

    public void setManualidad(String manualidad) {
        this.manualidad = manualidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
