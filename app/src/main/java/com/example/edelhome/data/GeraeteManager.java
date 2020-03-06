package com.example.edelhome.data;


import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;


@IgnoreExtraProperties
public class GeraeteManager implements Serializable {

    private String geraeteName;
    private String ipAdress;
    private String name;

    public GeraeteManager (String geraeteName, String ipAdresse, String name){

        this.geraeteName = geraeteName;
        this.ipAdress = ipAdresse;
        this.name = name;
    }

    @Override
    public String toString(){
        String s;
        s= "["+geraeteName.toString()+" "+ipAdress.toString()+" ]";



        return s;
    }

    public String getGeraeteName() {
        return geraeteName;
    }

    public void setGeraeteName(String geraeteName) {
        this.geraeteName = geraeteName;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
