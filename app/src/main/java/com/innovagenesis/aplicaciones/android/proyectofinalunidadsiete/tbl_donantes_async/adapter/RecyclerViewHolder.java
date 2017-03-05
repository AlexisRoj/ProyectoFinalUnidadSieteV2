package com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.tbl_donantes_async.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadsiete.R;

/**
 * Esta es la asignacion de los elementos de template_item_donantes
 * Created by alexi on 04/03/2017.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView idCedula,nombre, apellido, edad, tipoSangre, factorSangre, peso, estatura;
    public ImageView edit,delete;

    public RecyclerViewHolder(View itemView) {
        super(itemView);

        idCedula = (TextView) itemView.findViewById(R.id.cvCedula);
        nombre = (TextView) itemView.findViewById(R.id.cvNombre);
        apellido = (TextView) itemView.findViewById(R.id.cvApellidos);
        edad = (TextView) itemView.findViewById(R.id.cvEdad);
        factorSangre = (TextView) itemView.findViewById(R.id.cvFactorSangre);
        tipoSangre = (TextView) itemView.findViewById(R.id.cvTipoSangre);
        peso = (TextView) itemView.findViewById(R.id.cvPeso);
        estatura = (TextView) itemView.findViewById(R.id.cvEstatura);
        edit = (ImageView)itemView.findViewById(R.id.imgEditDonante);
        delete = (ImageView)itemView.findViewById(R.id.imgDeleteDonante);

    }
}
