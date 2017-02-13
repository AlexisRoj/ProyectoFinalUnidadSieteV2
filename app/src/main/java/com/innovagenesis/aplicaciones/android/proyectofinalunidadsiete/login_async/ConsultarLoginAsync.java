package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.login_async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Consulta el usuario para realizar el ingreso al sistema
 * Created by alexi on 13/02/2017.
 */

public class ConsultarLoginAsync extends AsyncTask<URL,Integer,String> {

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
        }finally {
            assert connection != null;
            connection.disconnect();
        }

        return null;
    }


    public interface OnConsultarUsuarioGetAsync{
        /** Interface que envia los datos del login*/
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
        }catch (ClassCastException e){
            throw new ClassCastException("La activity no implementa la interfaz consultar usuario");
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setMessage("Espere por favor...");
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        /** Parametros pasados al activity*/
        String id = "";
        String username = "";
        String userpass = "";

        try {
            JSONArray jsonArray = new JSONArray(s);

            /** Extrae los datos del json*/
            id = jsonArray.getJSONObject(0).getString("user_id");
            username = jsonArray.getJSONObject(0).getString("user_name");
            userpass = jsonArray.getJSONObject(0).getString("user_pass");

            Toast.makeText(activity,id+username+userpass,Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(activity, "Error al decodificar json respuesta", Toast.LENGTH_SHORT).show();
        }

        /** Envia los datos al activity*/
        listener.onConsultarUsuarioGetFinish(id,username,userpass);

        if (dialog.isShowing()){
            dialog.dismiss();
        }

    }
}
