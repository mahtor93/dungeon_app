package com.example.creacindepersonaje.Modelos;

public class Hechizo {

    private int idHechizo;
    private String nombre;
    private int nivelHechizo;
    private int damage;
    private String descripcion;
    private String tipoHechizo;
    private int heal;
    private int shield;

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public int getIdHechizo() {
        return idHechizo;
    }

    public void setIdHechizo(int idHechizo) {
        this.idHechizo = idHechizo;
    }

    public static class tipoHechizo{
        public static String[] tipoHechizo={"Abjuracion","Alteracion",
                "Conjuracion", "Adivinacion", "Encantamiento", "Ilusion", "Evocacion", "Necromancia"};
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivelHechizo() {
        return nivelHechizo;
    }

    public void setNivelHechizo(int nivel) {
        this.nivelHechizo = nivel;
    }

    public int getDamage(){
        return damage;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public String getTipoHechizo(){
        return tipoHechizo;
    }
    public void setTipoHechizo(String tipoHechizo){
        this.tipoHechizo = tipoHechizo;
    }



}