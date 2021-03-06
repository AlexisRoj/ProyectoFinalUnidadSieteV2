package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.preference;

/**
 * Administra las preferencias de Login y el Main
 * Created by alexi on 12/02/2017.
 */

public class PreferenceConstant {

    /**
     * Acá se cambia la ip de todos los accesos al service, ojo debe mantener
     * el formato completo la ip ejemplo: 192.168.100.5 + el puerto 8080
     */


    private static final String ip = "192.168.100.4";
    private static final String puerto = "8080";


    public static final String PREFERENCE_LOGIN = "Inicio de Sección";
    public static final String USER_NAME = "Nombre de usuario";
    public static final String USER_PASS = "Clave";
    public static final String USER_PREF = "false";

    public static final String URL_TBL_USERS =
            "http://" + ip + ":" + puerto + "/WebServiceExamenSiete/webapi/Users/";

    public static final String URL_TBL_DONANTES =
            "http://" + ip + ":" + puerto + "/WebServiceExamenSiete/webapi/Donantes/";


}
