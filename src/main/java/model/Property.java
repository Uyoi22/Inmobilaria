package com.inmobiliaria.model;

public class Property {
    private int id;
    private String tipo;
    private String ubicacion;
    private double valor;
    private String estado;
    private int famosoId;

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getFamosoId() {
        return famosoId;
    }

    public void setFamosoId(int famosoId) {
        this.famosoId = famosoId;
    }
}
