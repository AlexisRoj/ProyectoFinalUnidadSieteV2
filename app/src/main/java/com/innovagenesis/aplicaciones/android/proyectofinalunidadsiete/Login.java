package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.preference.PreferenceConstant;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private String user_name;
    private String user_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences preferences =
                getSharedPreferences(PreferenceConstant.PREFERENCE_LOGIN, MODE_PRIVATE);

        /** Cargar preferencias para hacer el login*/
        user_name = preferences.getString(PreferenceConstant.USER_NAME, null);
        user_pass = preferences.getString(PreferenceConstant.USER_PASS, null);

        /** Castea y Activa los botones*/

        Button btnIngresar = (Button) findViewById(R.id.btnIngLogin);
        Button btnRegistrarse = (Button) findViewById(R.id.btnRegLogin);

        btnIngresar.setOnClickListener(this);
        btnRegistrarse.setOnClickListener(this);

        /** Cambia el titulo del login*/
        this.setTitle(getString(R.string.titleName));
    }

    @Override
    public void onClick(View v) {

        int identificador = v.getId();

        switch (identificador) {

            case R.id.btnIngLogin: {
                break;
            }

            case R.id.btnRegLogin: {
                break;
            }

        }

    }
}
