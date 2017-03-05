package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_users_async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Clase encargada de actualizar Usuario
 * Created by alexi on 19/02/2017.
 */

public class ActualizarUserAsync extends AsyncTask<URL,Integer,Boolean> {

    private ProgressDialog dialog;
    private Activity activity;
    private String user_pass;


    public ActualizarUserAsync(Activity activity, String user_pass) {
        this.activity = activity;
        this.user_pass = user_pass;
        dialog = new ProgressDialog(activity);
    }

    @Override
    protected Boolean doInBackground(URL... params) {

        HttpURLConnection connection = null;

        /** Importante la contraseña a cambiar viaja como parte del cuerpo en el constructor*/

        try {
            JSONObject datos = new JSONObject();
            datos.put("user_pass", user_pass);
            connection = (HttpURLConnection) params[0].openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setDoOutput(true);
            connection.setFixedLengthStreamingMode(datos.toString().getBytes().length);

            OutputStream output = connection.getOutputStream();
            output.write(datos.toString().getBytes());
            output.flush();
            output.close();
            return true;
        }catch (IOException |JSONException e) {
            e.printStackTrace();
        }finally {
            connection.disconnect();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setMessage(activity.getString(R.string.waiting));
        dialog.show();
    }

    @Override
    protected void onPostExecute(Boolean bool) {
        super.onPostExecute(bool);

        if (bool) Toast.makeText(activity, "Usuario actualizado", Toast.LENGTH_SHORT).show();

        if (dialog.isShowing())dialog.dismiss();
    }
}
