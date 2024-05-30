package com.example.creacindepersonaje.Modelos;//package;

public class Arma {

    private int idArma;
    private String nombre;
    private int Damage;
    private String tipoArma;
    private String tipoDamage;
    private String Descripcion;

    public String getTipoDamage() {
        return tipoDamage;
    }

    public int getIdArma() {
        return idArma;
    }

    public void setIdArma(int idArma) {
        this.idArma = idArma;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDamage() {
        return Damage;
    }

    public void setDamage(int damage) {
        Damage = damage;
    }


    public String TipoDamage() {
        return tipoDamage;
    }

    public void setTipoDamage(String tipo_Damage) {
        this.tipoDamage = tipo_Damage;
    }

    public String getTipoArma() {
        return tipoArma;
    }

    public void setTipoArma(String tipoArma) {
        this.tipoArma = tipoArma;
    }

    public static class tipoDamage{
        public static String[] tiposDamage={"Contundente","Perforante","Cortante"};
    }

    public static class tipoArma{
        public static String[] tipos={"A dos manos","Una mano","A Distancia","Ligera",
        "Pesada", "Versatil"};
    }




    
}
