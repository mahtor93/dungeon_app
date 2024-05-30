package com.example.creacindepersonaje.Modelos;

import java.util.ArrayList;

public class modelGuild {
    private int idGuild;
    private String guildName;
    private ArrayList<Personaje> integrantes;

    public int getIdGuild() {
        return idGuild;
    }

    public void setIdGuild(int idGuild) {
        this.idGuild = idGuild;
    }

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    public ArrayList<Personaje> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(ArrayList<Personaje> integrantes) {
        this.integrantes = integrantes;
    }
}
