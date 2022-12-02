package com.bs.recyapp.activity.ui.Empresas;

public class DatosEmpresa {
    private String foto;
    private String nombre;
    private String descripcion;
    private String autor;
    private String id;

    public DatosEmpresa() {
    }
    public DatosEmpresa(String foto, String nombre, String descripcion, String autor, String id) {
        this.foto = foto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.autor = autor;
        this.id = id;
    }
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
