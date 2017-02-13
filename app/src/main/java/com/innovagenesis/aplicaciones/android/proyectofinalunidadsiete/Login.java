package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.login_async.ConsultarLoginAsync;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.preference.PreferenceConstant;

import java.net.MalformedURLException;
import java.net.URL;

public class Login extends AppCompatActivity implements View.OnClickListener,
        ConsultarLoginAsync.OnConsultarUsuarioGetAsync {

    private String user_name;
    private String user_pass;
    private TextInputLayout textInputUserName;
    private TextInputLayout textInputUserPass;

    private String id_comparar;
    private String username_comparar;
    private String userpass_comparar;


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

        /** Captura de datos de ingreso*/

        textInputUserName = (TextInputLayout) findViewById(R.id.user_name);
        textInputUserPass = (TextInputLayout) findViewById(R.id.user_pass);

        /** Validacion de existencia del sharepreference*/
        if (user_name != null && user_pass != null) {


        }

        /** Cambia el titulo del login*/
        this.setTitle(getString(R.string.titleName));


   /*     try {
            new ConsultarLoginAsync(this).execute(new URL("http://192.168.100.4:8080/WebServiceExamenSiete/webapi/Users/"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error en la tarea asíncrona", Toast.LENGTH_SHORT).show();
        }*/


    }

    @Override
    public void onClick(View v) {

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
                        new ConsultarLoginAsync(this).execute( new URL("http://192.168.100.4:8080/WebServiceExamenSiete/webapi/Users/"
                        + user_name) );

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

                    if (user_name == username_comparar){
                        Toast.makeText(this,"son iguales",Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(this,username_comparar,Toast.LENGTH_SHORT).show();


                } else
                    Toast.makeText(this, "No pueden haber campos vacios", Toast.LENGTH_SHORT).show();

                break;
            }

            case R.id.btnRegLogin: {
                break;
            }

        }

    }

    /** Variables que atrapan los datos que vienen del query*/


    @Override
    public void onConsultarUsuarioGetFinish(String id, String Username, String Userpass) {
        id_comparar = id;
        username_comparar = Username;
        userpass_comparar = Userpass;
    }
}
