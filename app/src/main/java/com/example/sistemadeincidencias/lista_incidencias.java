package com.example.sistemadeincidencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class lista_incidencias extends AppCompatActivity {
    private ListView listView;
    private ArrayList<incidenciaModel> incidenciaModelArrayList;
    private CustomAdapter customAdapter;
    private DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_incidencias2);

        listView = (ListView) findViewById(R.id.lv);
        dbHandler = new DBHandler(this);
        incidenciaModelArrayList = dbHandler.getIncidencias();
        customAdapter = new CustomAdapter(this,incidenciaModelArrayList);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(lista_incidencias.this,UpdateDeleteActivity.class);
                intent.putExtra("incidencia", incidenciaModelArrayList.get(position));
                startActivity(intent);
            }
        });
    }

}