package com.example.creacindepersonaje.Modelos;

import java.util.ArrayList;

public class Campaing {
    private int idCampaign;
    private int idGuild;
    private String relatoCampaign;
    private String nombreCampaing;
    private ArrayList<Escena> sceneCampaing;

    public int getIdGuild() {
        return idGuild;
    }

    public void setIdGuild(int idGuild) {
        this.idGuild = idGuild;
    }

    public String getRelatoCampaign() {
        return relatoCampaign;
    }

    public void setRelatoCampaign(String relatoCampaign) {
        this.relatoCampaign = relatoCampaign;
    }

    public int getIdCampaign() {return idCampaign; }
    public void setIdCampaign(int idCampaign) {this.idCampaign = idCampaign; }

    public String getNombreCampaing() {
        return nombreCampaing;
    }

    public void setNombreCampaing(String nombreCampaing) {
        this.nombreCampaing = nombreCampaing;
    }

    public ArrayList<Escena> getSceneCampaing() {
        return sceneCampaing;
    }

    public void setSceneCampaing(ArrayList<Escena> sceneCampaing) {
        this.sceneCampaing = sceneCampaing;
    }

    public class Escena{
        private int idEscena;


        private  String nombreEscena;
        private String campaignAsignada;
        private ArrayList<String> Objetivos;
        private Battle batalla;

        public int getIdEscena() {return  idEscena; }

        public String getNombreEscena() {
            return nombreEscena;
        }

        public void setNombreEscena(String nombreEscena) {
            this.nombreEscena = nombreEscena;
        }

        public void setIdEscena(int idEscena) {
            this.idEscena = idEscena;
        }

        private String getCampaignAsignada() {return campaignAsignada; }
        private void setCampaignAsignada(String campaignAsignada){
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
}
