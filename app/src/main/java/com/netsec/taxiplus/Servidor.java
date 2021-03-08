package com.netsec.taxiplus;

public class Servidor {

    public static String  servidor= "http://vdi.netsec.com.mx:80/controlador/usuario/";
    public  String local = "http://192.168.0.6/Taxiplus/controlador/";
    public String getIplocalhost(){
        return this.local;    }
    public String getIplocal (){
        return this.servidor;
    }
}
