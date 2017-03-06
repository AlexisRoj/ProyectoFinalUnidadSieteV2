package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_donantes_async.donantes_async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.R;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_donantes_async.Donantes;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de realizar el despliegue de los cardview en pantalla
 * Created by alexi on 05/03/2017.
 */

public class ListarDonantesAsync extends AsyncTask<URL, Integer, String> {

    private Activity activity;
    private ProgressDialog dialog;


    public interface OnListarDonantes {
        //Interface que envia
        void ListarDonantes(List<Donantes> donantes);
    }

    private OnListarDonantes listener;


    public ListarDonantesAsync(Activity activity) {

        //Constructor, se instancia el listener
        this.activity = activity;
        dialog = new ProgressDialog(activity);

        try {
            listener = (OnListarDonantes) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("La interface no ha sido implementada");
        }
    }

    @Override
    protected String doInBackground(URL... params) {

        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) params[0].openConnection();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            return reader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error en la conexion");
        } finally {
            assert connection != null;
            connection.disconnect();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setMessage(activity.getText(R.string.waiting));
        dialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if (dialog.isShowing())
            //Detiene el progressdialog
            dialog.dismiss();

        List<Donantes> donantesList = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(s);

            for (int i = 0; i<jsonArray.length();i++){
                //Se crea un nuevo donante y es almacenado en el array
                Donantes donantes = new Donantes();
                donantes.donante_ced = jsonArray.getJSONObject(i).getInt("donante_ced");
                donantes.donante_nombre = jsonArray.getJSONObject(i).getString("donante_nombre");
                donantes.donante_apellido = jsonArray.getJSONObject(i).getString("donante_apellido");
                donantes.donante_edad = jsonArray.getJSONObject(i).getInt("donante_edad");
                donantes.donante_tipo = jsonArray.getJSONObject(i).getString("donante_tipo");
                donantes.donante_factor = jsonArray.getJSONObject(i).getString("donante_factor");
                donantes.donante_peso = jsonArray.getJSONObject(i).getInt("donante_peso");
                donantes.donante_estatura = jsonArray.getJSONObject(i).getInt("donante_estatura");
                donantesList.add(donantes);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(activity, "Error al decodificar json respuesta", Toast.LENGTH_SHORT).show();
        }

        listener.ListarDonantes(donantesList);
    }
}
