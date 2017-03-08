package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_donantes_async;

import java.io.Serializable;

/**
 * Crea los valores a capturar en donantes
 * Clase que ayuda a agregar los donantes
 * Created by alexi on 27/02/2017.
 */

public class Donantes implements Serializable{


    public int donante_ced;
    public String donante_nombre;
    public String donante_apellido;
    public int donante_edad;
    public String donante_tipo;
    public String donante_factor;
    public int donante_peso;
    public int donante_estatura;

    public Donantes() {
    }

    public Donantes(int donante_ced,
                    String donante_nombre,
                    String donante_apellido,
                    int donante_edad,
                    String donante_tipo,
                    String donante_factor,
                    int donante_peso,
                    int donante_estatura) {
        //Constructor insertar
        this.donante_ced = donante_ced;
        this.donante_nombre = donante_nombre;
        this.donante_apellido = donante_apellido;
        this.donante_edad = donante_edad;
        this.donante_tipo = donante_tipo;
        this.donante_factor = donante_factor;
        this.donante_peso = donante_peso;
        this.donante_estatura = donante_estatura;
    }

    public Donantes(String donante_nombre,
                    String donante_apellido,
                    int donante_edad,
                    String donante_tipo,
                    String donante_factor,
                    int donante_peso,
                    int donante_estatura) {
        //Constructor editar
        this.donante_nombre = donante_nombre;
        this.donante_apellido = donante_apellido;
        this.donante_edad = donante_edad;
        this.donante_tipo = donante_tipo;
        this.donante_factor = donante_factor;
        this.donante_peso = donante_peso;
        this.donante_estatura = donante_estatura;
    }




    public int getDonante_ced() {
        return donante_ced;
    }

    public void setDonante_ced(int donante_ced) {
        this.donante_ced = donante_ced;
    }

    public String getDonante_nombre() {
        return donante_nombre;
    }

    public void setDonante_nombre(String donante_nombre) {
        this.donante_nombre = donante_nombre;
    }

    public String getDonante_apellido() {
        return donante_apellido;
    }

    public void setDonante_apellido(String donante_apellido) {
        this.donante_apellido = donante_apellido;
    }

    public int getDonante_edad() {
        return donante_edad;
    }

    public void setDonante_edad(int donante_edad) {
        this.donante_edad = donante_edad;
    }

    public String getDonante_grupo() {
        return donante_tipo;
    }

    public void setDonante_grupo(String donante_grupo) {
        this.donante_tipo = donante_grupo;
    }

    public String getDonante_factor() {
        return donante_factor;
    }

    public void setDonante_factor(String donante_factor) {
        this.donante_factor = donante_factor;
    }

    public int getDonante_peso() {
        return donante_peso;
    }

    public void setDonante_peso(int donante_peso) {
        this.donante_peso = donante_peso;
    }

    public int getDonante_estatura() {
        return donante_estatura;
    }

    public void setDonante_estatura(int donante_estatura) {
        this.donante_estatura = donante_estatura;
    }
}
