package com.example.me;

import java.io.Serializable;

public class ClassPersona implements Serializable {

    private String identificacion, nombre, clave;

    public ClassPersona(String identificacion, String nombre, String clave) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.clave = clave;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public String toString() {
        return "ClassPersona{" +
                "identificacion='" + identificacion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", clave='" + clave + '\'' +
                '}';
    }
}
