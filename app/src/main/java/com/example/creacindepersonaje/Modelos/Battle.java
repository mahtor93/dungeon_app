package com.example.creacindepersonaje.Modelos;

import java.util.ArrayList;

public class Battle {
    private int idBattle; //el id de la batalla.
    private String nameBattle;

    private int idGuild; //Se obtiene el id de la guild
    private modelGuild guild; //Se invoca la guild al combate
    private ArrayList<Personaje> personajesBatalla; // Una lista de los personajes provenientes de la guild
    private Personaje combatPj;//Personaje que combate en un turno.

    private ArrayList<Monstruo> monstruos; //Se invocan todos los enemigos correspondientes al combate
    private int idMonstruo;
    private Monstruo combatMonster; //Monstruo que combate en un turno.

    private ArrayList<String> logCombat; //Se almacenan Strings con las acciones de cada turno.
    private String Turno; //Se almacenan las acciones de cada turno.
    private String Descripcion;

    private boolean victory; //true si los personajes ganan la batalla, false si la pierden (sus puntos de golpe llegan a 0).

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getIdMonstruo() {
        return idMonstruo;
    }

    public void setIdMonstruo(int idMonstruo) {
        this.idMonstruo = idMonstruo;
    }

    public int getIdGuild() {
        return idGuild;
    }

    public void setIdGuild(int idGuild) {
        this.idGuild = idGuild;
    }

    public int getIdBattle() {
        return idBattle;
    }

    public void setIdBattle(int idBattle) {
        this.idBattle = idBattle;
    }

    public String getNameBattle() {
        return nameBattle;
    }

    public void setNameBattle(String nameBattle) {
        this.nameBattle = nameBattle;
    }

    public modelGuild getGuild() {
        return guild;
    }

    public void setGuild(modelGuild guild) {
        this.guild = guild;
    }

    public ArrayList<Personaje> getPersonajesBatalla() {
        return personajesBatalla;
    }

    public void setPersonajesBatalla(ArrayList<Personaje> personajesBatalla) {
        this.personajesBatalla = personajesBatalla;
    }

    public Personaje getCombatPj() {
        return combatPj;
    }

    public void setCombatPj(Personaje combatPj) {
        this.combatPj = combatPj;
    }

    public ArrayList<Monstruo> getMonstruos() {
        return monstruos;
    }

    public void setMonstruos(ArrayList<Monstruo> monstruos) {
        this.monstruos = monstruos;
    }

    public Monstruo getCombatMonster() {
        return combatMonster;
    }

    public void setCombatMonster(Monstruo combatMonster) {
        this.combatMonster = combatMonster;
    }

    public ArrayList<String> getLogCombat() {
        return logCombat;
    }

    public void setLogCombat(ArrayList<String> logCombat) {
        this.logCombat = logCombat;
    }

    public String getTurno() {
        return Turno;
    }

    public void setTurno(String turno) {
        Turno = turno;
    }

    public boolean isVictory() {
        return victory;
    }

    public void setVictory(boolean victory) {
        this.victory = victory;
    }

}
