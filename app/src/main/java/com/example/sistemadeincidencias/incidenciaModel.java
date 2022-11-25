package com.example.sistemadeincidencias;

import java.io.Serializable;

public class incidenciaModel implements Serializable {
    private int id;
    private String labName;
    private String name;
    private String rut;
    private String reason;
    private String datetime;


public String getLabName(){
    return labName;
}
public void setLabName(String labName){
    this.labName=labName;
}
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getRut(){
        return rut;
    }
    public void setRut(String rut){
        this.rut=rut;
    }
    public String getReason(){
        return reason;
    }
    public void setReason(String reason){
        this.reason=reason;
    }
    public int getId(){
    return id;
    }
    public void setId(int id){
    this.id=id;
    }
    public incidenciaModel(Integer id, String labName, String name, String rut, String reason, String datetime){
    this.id=id;
    this.labName=labName;
    this.name=name;
    this.rut=rut;
    this.reason=reason;
    this.datetime=datetime;
    }
}