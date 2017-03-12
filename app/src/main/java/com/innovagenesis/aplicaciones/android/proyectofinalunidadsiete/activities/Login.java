package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.R;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.dialogos.DialogoCrearUsuario;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_users_async.ConsultarLoginAsync;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_users_async.ConsultarUserAsync;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_users_async.InsertarLoginAsync;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.preference.PreferenceConstant;

import java.net.MalformedURLException;
import java.net.URL;




public class Login extends AppCompatActivity implements View.OnClickListener,
        ConsultarLoginAsync.OnConsultarUsuarioGetAsync, DialogoCrearUsuario.OnInsertarUserListener
        , ConsultarUserAsync.OnIfExistUser {

    private String user_name;
    private String user_pass;
    private String user_pref;
    private TextInputLayout textInputUserName;
    private TextInputLayout textInputUserPass;

    /**
     * Preference
     */
    private SharedPreferences pref;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /** Seccion del toolbar*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        /** Crea variable de preferencias*/
        pref = getSharedPreferences(PreferenceConstant.PREFERENCE_LOGIN, MODE_PRIVATE);

        /** Cargar preferencias para hacer el login*/
        user_name = pref.getString(PreferenceConstant.USER_NAME, null);
        user_pass = pref.getString(PreferenceConstant.USER_PASS, null);
        user_pref = pref.getString(PreferenceConstant.USER_PREF, null);

        /** Castea y Activa los botones*/

        Button btnIngresar = (Button) findViewById(R.id.btnIngLogin);
        Button btnRegistrarse = (Button) findViewById(R.id.btnRegLogin);

        btnIngresar.setOnClickListener(this);
        btnRegistrarse.setOnClickListener(this);

        /** Captura de datos de ingreso*/

        textInputUserName = (TextInputLayout) findViewById(R.id.user_name);
        textInputUserPass = (TextInputLayout) findViewById(R.id.user_pass);

        /** Checkbox*/
        checkBox = (CheckBox) findViewById(R.id.checkbox);

        /** Validacion de existencia del sharepreference*/
        if (user_name != null && user_pass != null && user_pref != null) {
            /**
             *
             *   VALIDACION DE PREFERENCE
             *
             * */

            /** Valida las preferencias y si existen, envia el dato almacenado a comparar
             * para hacer login */
            try {
                new ConsultarLoginAsync(this).execute(
                        new URL(PreferenceConstant.URL_TBL_USERS
                        + user_name + "/" + user_pass));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        /** Cambia el titulo del login*/
        toolbar.setTitle(getText(R.string.titleName));
        /** Action Button*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

                if ("".equals(user_name))
                    conectar = false;

                if ("".equals(user_pass))
                    conectar = false;

                if (conectar) {
                    /** Primero crea la conexion al servicio */
                    try {
                        new ConsultarLoginAsync(this).execute(new
                                URL(PreferenceConstant.URL_TBL_USERS
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

    /**********************************************************************************************/
    /** Ingreso al sistema   */
    @Override
    public void onConsultarUsuarioGetFinish(String id, String Username, String Userpass) {

        SharedPreferences.Editor edit = pref.edit();

        if (Username != null) {
            if (checkBox.isChecked()) {
                /** Salva la preferencia*/

                edit.putString(PreferenceConstant.USER_NAME, user_name);
                edit.putString(PreferenceConstant.USER_PASS, user_pass);
                edit.putString(PreferenceConstant.USER_PREF, "Activa");
                edit.apply();
                cargarActivity();
            } else
                /** Solo carga el usuario*/
                edit.putString(PreferenceConstant.USER_NAME, user_name);
                edit.putString(PreferenceConstant.USER_PASS, user_pass);
                edit.apply();
                cargarActivity();
        }
    }

    private void cargarActivity() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    /**********************************************************************************************/
    /** Usuario nuevo    */
    private String usernameInsertar;
    private String userpassInsertar;

    @Override
    public void DialogInsertUser(String username, String userpass) {

        usernameInsertar = username;
        userpassInsertar = userpass;

        try {
            new ConsultarUserAsync(this).execute(
                    new URL(PreferenceConstant.URL_TBL_USERS
                    + username));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void OnIfExistUserGetFinish(Boolean username) {

        /** validacion del usuario para ingresarlo/registrarlo al sistema*/

        if (username) {
            Toast.makeText(this, "El nombre usuario ingresado " +
                    "ya existe, favor ingresar un nombre distinto", Toast.LENGTH_LONG).show();
        } else {

            try {
                /** Ingresa el usuario y carga la activity*/
                new InsertarLoginAsync(this, usernameInsertar, userpassInsertar, pref)
                        .execute(new
                                URL(PreferenceConstant.URL_TBL_USERS));
                cargarActivity();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Toast.makeText(Login.this,
                        "Ha ocurrido un error durante la petición", Toast.LENGTH_SHORT).show();
            }


        }

    }


}