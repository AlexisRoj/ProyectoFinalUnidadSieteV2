package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.adapters;

/**
 * Crea los valores a capturar en donantes
 * Clase que ayuda a agregar los donantes
 * Created by alexi on 27/02/2017.
 */

public class Donantes {


    private int donante_ced;
    private String donante_nombre;
    private String donante_apellido;
    private int donante_edad;
    private String donante_tipo_sangre;
    private int donante_peso;
    private int donante_estatura;


    public Donantes(String donante_nombre,
                    String donante_apellido,
                    int donante_edad,
                    String donante_tipo_sangre,
                    int donante_peso,
                    int donante_estatura) {
        this.donante_nombre = donante_nombre;
        this.donante_apellido = donante_apellido;
        this.donante_edad = donante_edad;
        this.donante_tipo_sangre = donante_tipo_sangre;
        this.donante_peso = donante_peso;
        this.donante_estatura = donante_estatura;
    }

    public Donantes() {
        //Constructor vacio
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

    public String getDonante_tipo_sangre() {
        return donante_tipo_sangre;
    }

    public void setDonante_tipo_sangre(String donante_tipo_sangre) {
        this.donante_tipo_sangre = donante_tipo_sangre;
    }

    public int getDonante_peso() {
        return donante_peso;
    }

    public void setDonante_peso(int donante_peso) {
        this.donante_peso = donante_peso;
    }

    public int getDonante_estatura(int i) {
        return donante_estatura;
    }

    public void setDonante_estatura(int donante_estatura) {
        this.donante_estatura = donante_estatura;
    }


}
