package com.example.sistemadeincidencias;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "incidencias_db";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "incidencias";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String LAB_COL = "lab";

    // below variable id for our course duration column.
    private static final String NAME_COL = "name";

    // below variable for our course description column.
    private static final String RUT_COL = "rut";

    // below variable is for our course tracks column.
    private static final String REASON_COL= "reason";
    private static final String DATETIME_COL= "datetime";
    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LAB_COL + " TEXT,"
                + NAME_COL + " TEXT,"
                + RUT_COL + " TEXT,"
                + DATETIME_COL + " TEXT,"
                + REASON_COL + " TEXT)";
        Log.d("table", this.getDatabaseName());
        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewIncidencia(String lab, String name, String rut, String reason, String date) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LAB_COL, lab);
        values.put(NAME_COL, name);
        values.put(RUT_COL, rut);
        values.put(REASON_COL, reason);
        values.put(DATETIME_COL, date);
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }
    public ArrayList<incidenciaModel> getIncidencias(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorIncidencias = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        ArrayList<incidenciaModel> incidenciaModelArrayList = new ArrayList<>();
        if (cursorIncidencias.moveToFirst()){
            do{
                incidenciaModelArrayList.add(new incidenciaModel(cursorIncidencias.getInt(0),
                        cursorIncidencias.getString(1),
                        cursorIncidencias.getString(2),
                        cursorIncidencias.getString(3),
                        cursorIncidencias.getString(5),
                        cursorIncidencias.getString(4)));
            } while (cursorIncidencias.moveToNext());
        }
        cursorIncidencias.close();
        return incidenciaModelArrayList;
    }
    public void deleteIncidencia(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID_COL +" = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }
    public void updateIncidencia(int id, String reason){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(REASON_COL, reason);
        db.update(TABLE_NAME, values, ID_COL + " = ? ",
                new String[]{String.valueOf(id)});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
