package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.dialogos.DialogoCrearUsuario;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.login_async.ConsultarLoginAsync;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.login_async.ConsultarUserAsync;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.preference.PreferenceConstant;

import java.net.MalformedURLException;
import java.net.URL;

public class Login extends AppCompatActivity implements View.OnClickListener,
        ConsultarLoginAsync.OnConsultarUsuarioGetAsync, DialogoCrearUsuario.OnInsertarUserListener
, ConsultarUserAsync.OnIfExistUser{

    public String user_name;
    private String user_pass;
    private TextInputLayout textInputUserName;
    private TextInputLayout textInputUserPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        /** Seccion del toolbar*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);


        /** Crea variable de preferencias*/
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

        /** Captura de datos de ingreso*/

        textInputUserName = (TextInputLayout) findViewById(R.id.user_name);
        textInputUserPass = (TextInputLayout) findViewById(R.id.user_pass);

        /** Validacion de existencia del sharepreference*/
        if (user_name != null && user_pass != null) {

        }

        /** Cambia el titulo del login*/

        toolbar.setTitle(getText(R.string.titleName));

    }

    @Override
    public void onClick(View v) {
        /** Selecion de Boton*/

        int identificador = v.getId();

        switch (identificador) {

            case R.id.btnIngLogin: {
                /** captura los datos si no estan en la preference*/

                EditText editTextUserName = textInputUserName.getEditText();
                EditText editTextUserPass = textInputUserPass.getEditText();

                if (textInputUserName.getEditText() != null) {
                    assert editTextUserName != null;
                    user_name = editTextUserName.getText().toString();
                }

                if (textInputUserPass.getEditText() != null) {
                    assert editTextUserPass != null;
                    user_pass = editTextUserPass.getText().toString();
                }

                Boolean conectar = true;

/*                if ("".equals(user_name))
                    conectar = false;

                if ("".equals(user_pass))
                    conectar = false;*/

                if (conectar) {
                    /** Primero crea la conexion al servicio */
                    try {
                        new ConsultarLoginAsync(this).execute(new URL("http://192.168.100.2:8080/WebServiceExamenSiete/webapi/Users/"
                                + user_name + "/" + user_pass));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else
                    Toast.makeText(this, "No pueden haber campos vacios", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.btnRegLogin: {
                DialogoCrearUsuario dialogo = new DialogoCrearUsuario();
                dialogo.show(getSupportFragmentManager(), DialogoCrearUsuario.TAG);
                break;
            }

        }

    }

    /**
     * Variables que atrapan los datos que vienen del query
     */




    @Override
    public void onConsultarUsuarioGetFinish(String id, String Username) {


        if (user_name != null)
            cargarActivity();
            //Toast.makeText(this, "Loteria", Toast.LENGTH_SHORT).show();
    }

    private void cargarActivity() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private String usernameInsertar;
    private String userpassInsertar;

    @Override
    public void InsertarUser(String username, String userpass) {


        usernameInsertar = username;
        userpassInsertar = userpass;

        try {
            new ConsultarUserAsync(this).execute(new URL("http://192.168.100.2:8080/WebServiceExamenSiete/webapi/Users/"
                    + username));} catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void OnIfExistUserGetFinish(Boolean username) {

        if (username) {
            Toast.makeText(this, "El nombre usuario ingresado " +
                    "ya existe, favor ingresar un nombre distinto", Toast.LENGTH_LONG).show();
        } else {

            Toast.makeText(this, usernameInsertar + userpassInsertar, Toast.LENGTH_SHORT).show();
        }

    }
}