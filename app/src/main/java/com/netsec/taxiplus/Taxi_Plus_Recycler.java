package com.netsec.taxiplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Taxi_Plus_Recycler extends AppCompatActivity {

    private String id_viaje,tipo_viaje,fecha_inicio,fecha_termino,estado,costo,id_conductor;

    public Taxi_Plus_Recycler( String id, String type_travel, String date_star, String date_end,String state,String cost,String id_driver)
    {

        this.id_viaje=id;
        this.tipo_viaje=type_travel;
        this.fecha_inicio=date_star;
        this.fecha_termino=date_end;
        this.estado=state;
        this.costo=cost;
        this.id_conductor=id_driver;

    }


    public String getId_viaje() {
        return id_viaje;
    }
    public String getTipo_viaje() {
        return tipo_viaje;
    }
    public String getFecha_inicio() {
        return fecha_inicio;
    }
    public String getFecha_termino() {
        return fecha_termino;
    }
    public String getEstado() { return estado;}
    public String getCosto() {
        return costo;
    }
    public String getId_conductor() { return id_conductor;}



    public void setId_viaje(String id_viaje) {
        this.id_viaje = id_viaje;
    }
    public void setTipo_viaje(String tipo_viaje) {
        this.tipo_viaje = tipo_viaje;
    }
    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    public void setFecha_termino(String fecha_termino) { this.fecha_termino = fecha_termino; }
    public void setEstado(String estado) {this.estado = estado;}
    public void setCosto(String costo) { this.costo = costo;}
    public void setId_conductor(String id_conductor) {this.id_conductor = id_conductor;}


}