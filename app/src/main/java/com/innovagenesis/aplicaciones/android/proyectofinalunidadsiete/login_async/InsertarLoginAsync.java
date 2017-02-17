package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.login_async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.Login;
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

    public InsertarLoginAsync(Activity activity) {
        dialogo = new ProgressDialog(activity);
        this.activity = activity;


    }

    @Override
    protected Boolean doInBackground(URL... params) {

        HttpURLConnection connection = null;

        try {

            JSONObject datos = new JSONObject();
            /** Trae los datos del activity*/
      /*      Login login = new Login();

            String user, pass;
            user = login.getUsernameInsertar();
            pass = login.getUserpassInsertar();*/
            /**Inserta los datos en la tabla*/
            datos.put("user_name", "user").put("user_pass2", "pass2");
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
        }finally {
            assert connection != null;
            connection.disconnect();
        }
        return false;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialogo.setMessage("Por favor espere...");
        dialogo.show();
    }

    @Override
    protected void onPostExecute(Boolean bool) {
        super.onPostExecute(bool);

        if (bool) Toast.makeText(activity, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();

        if (dialogo.isShowing())dialogo.dismiss();
    }
}
