package com.example.aut2_03aplicacinfinalandroid;

public class DatosListado {
    private int imagen;
    private String titulo;
    private String descripcion;

    public DatosListado(int imagen, String titulo, String descripcion){
        this.imagen = imagen;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public int getImagen() {
        return imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
