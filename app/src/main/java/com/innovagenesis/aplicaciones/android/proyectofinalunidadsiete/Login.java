package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.preference.PreferenceConstant;

public class Login extends AppCompatActivity {

    private SharedPreferences preferences;
    private String user_name;
    private String user_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = getSharedPreferences(PreferenceConstant.PREFERENCE_LOGIN,MODE_PRIVATE);

        /** Cargar preferencias para hacer el login*/
        user_name = preferences.getString(PreferenceConstant.USER_NAME,null);
        user_pass = preferences.getString(PreferenceConstant.USER_PASS,null);



        /** Cambia el titulo del login*/
        this.setTitle(getString(R.string.titleName));
    }
}
