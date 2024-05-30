package com.example.creacindepersonaje.Modelos;

public class Monstruo {
    private int idMonstruo;
    private String nameMonstruo;
    private int CA;
    private int HP;
    private int VEL;

    private int FUE;
    private int DES;
    private int CON;
    private int INT;
    private int SAB;
    private int CAR;

    private int ExpFinal;

    private String descripcion;

    private String arma;
    private String action;
    private int idArma;




    public int getIdMonstruo() {
        return idMonstruo;
    }

    public void setIdMonstruo(int idMonstruo) {
        this.idMonstruo = idMonstruo;
    }

    public String getNameMonstruo() {
        return nameMonstruo;
    }

    public void setNameMonstruo(String nameMonstruo) {
        this.nameMonstruo = nameMonstruo;
    }

    public String getArma() {
        return arma;
    }

    public void setArma(String arma) {
        this.arma = arma;
    }

    public int getIdArma() {
        return idArma;
    }

    public void setIdArma(int idArma) {
        this.idArma = idArma;
    }



    public int getCA() {
        return CA;
    }

    public void setCA(int CA) {
        this.CA = CA;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getVEL() {
        return VEL;
    }

    public void setVEL(int VEL) {
        this.VEL = VEL;
    }

    public int getFUE() {
        return FUE;
    }

    public void setFUE(int FUE) {
        this.FUE = FUE;
    }

    public int getDES() {
        return DES;
    }

    public void setDES(int DES) {
        this.DES = DES;
    }

    public int getCON() {
        return CON;
    }

    public void setCON(int CON) {
        this.CON = CON;
    }

    public int getINT() {
        return INT;
    }

    public void setINT(int INT) {
        this.INT = INT;
    }

    public int getSAB() {
        return SAB;
    }

    public void setSAB(int SAB) {
        this.SAB = SAB;
    }

    public int getCAR() {
        return CAR;
    }

    public void setCAR(int CAR) {
        this.CAR = CAR;
    }

    public int getExpFinal() {
        return ExpFinal;
    }

    public void setExpFinal(int expFinal) {
        ExpFinal = expFinal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }





    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public static class accionesMonstruos{
        public static String[] acciones={"Atacar","Defenderse",
                "Ocultarse"};
    }

}
