package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.preference;

/**
 * Administra las preferencias de Login y el Main
 * Created by alexi on 12/02/2017.
 */

public class PreferenceConstant {

     /**
      * Acá se cambia la ip de todos los accesos al service, ojo debe mantener
      * el formato completo la ip + el puerto ejemplo: 192.168.100.5:8080
      * */

    private static final String ip = "192.168.100.3:8080";


    public static final String PREFERENCE_LOGIN = "Inicio de Sección";
    public static final String USER_NAME = "Nombre de usuario";
    public static final String USER_PASS = "Clave";
    public static final String USER_PREF = "false";

    public static final String URL_TBL_USERS =
            "http://" + ip + "/WebServiceExamenSiete/webapi/Users/";

    public static final String URL_TBL_DONANTES =
            "http://" +ip + "/WebServiceExamenSiete/webapi/Donantes/";

    public static final String NOMBRE_ARCHIVO = "donantes.txt";


}
