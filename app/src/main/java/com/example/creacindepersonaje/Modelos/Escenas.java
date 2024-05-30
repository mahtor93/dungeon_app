package com.example.creacindepersonaje.Modelos;

import java.util.ArrayList;

public class Escenas {

    private int idEscena;
    private  String nombreEscena;
    private int campaignAsignada;
    private ArrayList<String> Objetivos;
    private Battle batalla;

    public String getNombreEscena() {
        return nombreEscena;
    }

    public void setNombreEscena(String nombreEscena) {
        this.nombreEscena = nombreEscena;
    }

    public int getIdEscena() {return  idEscena; }

    public void setIdEscena(int idEscena) {
        this.idEscena = idEscena;
    }

    public int getCampaignAsignada() {return campaignAsignada; }
    public void setCampaignAsignada(int campaignAsignada){
        this.campaignAsignada = campaignAsignada;
    }

    public Battle getBatalla() {
        return batalla;
    }

    public void setBatalla(Battle batalla) {
        this.batalla = batalla;
    }

    public ArrayList<String> getObjetivos() {
        return Objetivos;
    }

    public void setObjetivos(ArrayList<String> objetivos) {
        Objetivos = objetivos;
    }


}

