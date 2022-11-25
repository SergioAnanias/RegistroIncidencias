package com.example.sistemadeincidencias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<incidenciaModel> incidenciaModelArrayList;

    public CustomAdapter(Context context, ArrayList<incidenciaModel> incidenciaModelArrayList) {

        this.context = context;
        this.incidenciaModelArrayList = incidenciaModelArrayList;
    }


    @Override
    public int getCount() {
        return incidenciaModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return incidenciaModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.incidencia, null, true);

            holder.lab  = (TextView) convertView.findViewById(R.id.lab);
            holder.nombreResponsable = (TextView) convertView.findViewById(R.id.NombreResponsable);
            holder.rut = (TextView) convertView.findViewById(R.id.rut);
            holder.razon=(TextView) convertView.findViewById(R.id.razon);
            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.lab.setText("Laboratorio: "+incidenciaModelArrayList.get(position).getLabName());
        holder.nombreResponsable.setText("Nombre: "+incidenciaModelArrayList.get(position).getName());
        holder.rut.setText("RUT: "+incidenciaModelArrayList.get(position).getRut());
        holder.razon.setText("Razón: "+ incidenciaModelArrayList.get(position).getReason());

        return convertView;
    }

    private class ViewHolder {

        protected TextView lab, nombreResponsable, rut, razon;
    }

}