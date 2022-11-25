package com.example.sistemadeincidencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDeleteActivity extends AppCompatActivity {
    public incidenciaModel incidenciaModel;
    private DBHandler databaseHelper;
    private EditText editReason;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        Intent intent = getIntent();
        incidenciaModel = (incidenciaModel) intent.getSerializableExtra("incidencia");
        databaseHelper = new DBHandler(this);
        editReason =(EditText) findViewById(R.id.reason);
        editReason.setText(incidenciaModel.getReason());
    }
    public void updateIncidencia(View view){
        databaseHelper.updateIncidencia(incidenciaModel.getId(), editReason.getText().toString());
        Toast.makeText(UpdateDeleteActivity.this, "Actualización exitosa", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UpdateDeleteActivity.this, lista_incidencias.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void deleteIncidencia(View view){
        databaseHelper.deleteIncidencia(incidenciaModel.getId());
        Toast.makeText(UpdateDeleteActivity.this, "Borrado exitoso", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UpdateDeleteActivity.this, lista_incidencias.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}