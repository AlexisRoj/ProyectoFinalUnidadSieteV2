package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.login_async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.Login;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.MainActivity;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Consulta el usuario para realizar el ingreso al sistema
 * Created by alexi on 13/02/2017.
 */

public class ConsultarLoginAsync extends AsyncTask<URL, Integer, String> {

    @Override
    protected String doInBackground(URL... params) {
        HttpURLConnection connection = null;

        try {
            /** Conexion a la base de datos*/
            connection = (HttpURLConnection) params[0].openConnection();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            return reader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            connection.disconnect();
        }

        return null;
    }


    public interface OnConsultarUsuarioGetAsync {
        /**
         * Interface que envia los datos del login
         */
        void onConsultarUsuarioGetFinish(String id, String Username, String Userpass);
    }

    private OnConsultarUsuarioGetAsync listener;

    private Activity activity;
    private ProgressDialog dialog;

    public ConsultarLoginAsync(Activity activity) {
        /** Constructor */
        this.activity = activity;
        dialog = new ProgressDialog(activity);

        try {
            listener = (OnConsultarUsuarioGetAsync) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("La activity no implementa la interfaz consultar usuario");
        }
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setMessage("Espere por favor...");
    }


    /**
     * Parametros pasados al activity
     */
    private String id = "";
    private String username = "";
    private String userpass = "";

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);


        if (s != null) {
            /** Si s es null no consulta el json dado que la contraseña no es valida*/
            try {
                JSONObject jsonObject = new JSONObject(s);

                /** Extrae los datos del json*/

                id = jsonObject.getString("user_id");
                username = jsonObject.getString("user_name");
                userpass = jsonObject.getString("user_pass");

                //Toast.makeText(activity, id + username + userpass, Toast.LENGTH_SHORT).show();
                listener.onConsultarUsuarioGetFinish(id, username, userpass);


            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(activity, "Error al decodificar json respuesta", Toast.LENGTH_SHORT).show();
            }
        } else {
            /** Error de contraseña*/
            Toast.makeText(activity, R.string.errorLogin, Toast.LENGTH_SHORT).show();
        }

        if (dialog.isShowing()) {
            dialog.dismiss();
        }

    }

}
