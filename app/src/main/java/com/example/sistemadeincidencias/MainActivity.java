package com.example.sistemadeincidencias;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Date;
import java.util.regex.*;
import java.util.List;



public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private SensorEvent event;
    EditText name, rut, reason;

    public class Incidence {
        String lab="";
        String name="";
        String rut="";
        String reason="";
        Date now = new Date();
    }
    Spinner s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.txt_name);
        rut = (EditText) findViewById(R.id.txt_rut);
        reason = (EditText) findViewById(R.id.reason);
        s= (Spinner) findViewById(R.id.spinner_labs);
        setSpinner();

        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (sensors.size() > 0)
        {
            sm.registerListener(this, sensors.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        }

    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(Math.round(event.values[SensorManager.DATA_Y]) ==9){
            createIncidence(findViewById(R.id.view-1));

        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    public void createIncidence(View view){
        Incidence incidence = new Incidence();
        if(rut.getText().toString().matches("")|| name.getText().toString().matches("")|| reason.getText().toString().matches("")){
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context,"Existen campos sin rellenar", Toast.LENGTH_LONG );
            toast.show();
        }
        else{
            if(validateRut(rut.getText().toString())){
                incidence.lab = s.getSelectedItem().toString();
                incidence.name= name.getText().toString();
                incidence.rut= rut.getText().toString();
                incidence.reason = reason.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Registro exitoso");
                builder.setMessage("Incidencia reportada exitosamente con fecha y hora:"+incidence.now.toString());
                builder.show();
            }
            else{
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context,"El rut ingresado no es valido, este debe ser sin puntos y con guión", Toast.LENGTH_LONG );
                toast.show();

            }
        }
    }
    private boolean validateRut(String rut){
        String rutregex="^(\\d{1,2})(\\d{3})(\\d{3})-(\\w{1})$";
        Pattern p = Pattern.compile(rutregex);
        Matcher m = p.matcher(rut);
        return m.matches();
    }
    public void setSpinner(){
        String[] arraySpinner = new String[]{
                "C2","LINF","LEICA","LNET", "LTEL"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

    }
}
