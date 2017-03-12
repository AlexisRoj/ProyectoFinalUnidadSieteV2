package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_users_async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.R;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.preference.PreferenceConstant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Inserta nuevos registros en la tabla usuarios
 * Created by alexi on 17/02/2017.
 */

public class InsertarLoginAsync extends AsyncTask<URL, Integer, Boolean> {

    private ProgressDialog dialogo;
    private Activity activity;
    private String username;
    private String userpass;

    private SharedPreferences pref;


    public InsertarLoginAsync(Activity activity, String username,
                              String userpass, SharedPreferences pref) {
        /**Constructor, recibe los parametros del dialog*/
        dialogo = new ProgressDialog(activity);
        this.activity = activity;
        this.username = username;
        this.userpass = userpass;

        this.pref = pref;
    }

    @Override
    protected Boolean doInBackground(URL... params) {

        HttpURLConnection connection = null;
        try {
            JSONObject datos = new JSONObject();
            /** Trae los datos del activity*/
            /**Inserta los datos en la tabla*/
            datos.put("user_name", username).put("user_pass", userpass);
            connection = (HttpURLConnection) params[0].openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setDoOutput(true);
            connection.setFixedLengthStreamingMode(datos.toString().getBytes().length);

            OutputStream output = connection.getOutputStream();
            output.write(datos.toString().getBytes());
            output.flush();
            output.close();
            return true;

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            connection.disconnect();
        }
        return false;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialogo.setMessage(activity.getString(R.string.waiting));
        dialogo.show();
    }

    @Override
    protected void onPostExecute(Boolean bool) {
        super.onPostExecute(bool);

        if (bool) {

            SharedPreferences.Editor edit = pref.edit();

            /** Graba las preferencias */
            edit.putString(PreferenceConstant.USER_NAME, username);
            edit.putString(PreferenceConstant.USER_PASS, userpass);
            edit.apply();
            Toast.makeText(activity, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();

            activity.closeContextMenu();
        }

        if (dialogo.isShowing()) dialogo.dismiss();
    }
}
