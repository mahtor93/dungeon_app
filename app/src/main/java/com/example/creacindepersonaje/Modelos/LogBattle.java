package com.example.creacindepersonaje.Modelos;

import com.example.creacindepersonaje.Controller.PersonajeController;

public class LogBattle {
    private int idTurno;
    private int idGuild;
    private int idBattle;
    private String descripcion;
    private int idPersonaje;
    private int damagePersonaje;
    private int idMonstruo;
    private int damageMonstruo;
    private int dadoResult;

    public int getIdGuild() {
        return idGuild;
    }

    public void setIdGuild(int idGuild) {
        this.idGuild = idGuild;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public int getIdBattle() {
        return idBattle;
    }

    public void setIdBattle(int idBattle) {
        this.idBattle = idBattle;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(int idPersonaje) {
        this.idPersonaje = idPersonaje;
    }

    public int getDamagePersonaje() {
        return damagePersonaje;
    }

    public void setDamagePersonaje(int damagePersonaje) {
        this.damagePersonaje = damagePersonaje;
    }

    public int getIdMonstruo() {
        return idMonstruo;
    }

    public void setIdMonstruo(int idMonstruo) {
        this.idMonstruo = idMonstruo;
    }

    public int getDamageMonstruo() {
        return damageMonstruo;
    }

    public void setDamageMonstruo(int damageMonstruo) {
        this.damageMonstruo = damageMonstruo;
    }

    public int getDadoResult() {
        return dadoResult;
    }

    public void setDadoResult(int dadoResult) {
        this.dadoResult = dadoResult;
    }
}
