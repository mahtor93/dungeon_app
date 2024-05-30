package com.example.creacindepersonaje.Modelos;

import java.io.Serializable;

public class Personaje implements Serializable {

     private int idPersonaje;
     private int idGuild;
     private String Nombre;
     private String Raza;
     private String Sub_Raza;
     private String Clase;
     private String Arma;
     private String Armadura;

     private Boolean SxP; //Boolean puede tomar valor true, false y 0
     private int Fuerza;
     private int Destreza;
     private int Constitucion;
     private int Inteligencia;
     private int Sabiduria;
     private int Carisma;

     private int ModStr;
     private int ModDes;
     private int ModCon;
     private int ModInt;
     private int ModSab;
     private int ModCar;

     private int Vel;
     private int CA; //Clase de armadura
     private int PG; //Puntos de golpe o la Vida, también conocido como HP
     private int PGT; //Puntos de golpe temporales

     private int Level;
     private int EXP;

     private int espacioConjuros;
     private int AptitudMagica; //true o 1: usa hechizos, false o 0: no usa hechizos

     private int Inspiracion;
     private int BonoCompetencia;
     private int Iniciativa;
     private int idHechizo;
     private int idArma;
     private  int idArmadura;

    public int getIdGuild() {
        return idGuild;
    }

    public void setIdGuild(int idGuild) {
        this.idGuild = idGuild;
    }

    public int getIdHechizo() {
        return idHechizo;
    }

    public void setIdHechizo(int idHechizo) {
        this.idHechizo = idHechizo;
    }

    public int getIdArma() {
        return idArma;
    }

    public void setIdArma(int idArma) {
        this.idArma = idArma;
    }

    public int getIdArmadura() {
        return idArmadura;
    }

    public void setIdArmadura(int idArmadura) {
        this.idArmadura = idArmadura;
    }

    public int getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(int idPersonaje) {
        this.idPersonaje = idPersonaje;
    }

    public String getArma() {
        return Arma;
    }

    public void setArma(String arma) {
        Arma = arma;
    }

    public String getArmadura() {
        return Armadura;
    }

    public void setArmadura(String armadura) {
        Armadura = armadura;
    }

     public String getRaza() {
         return Raza;
     }
     public void setRaza(String Raza) {
         this.Raza = Raza;
     }

     public int getEspacioConjuros() {
         return espacioConjuros;
     }

     public void setEspacioConjuros(int espacioConjuros) {
         this.espacioConjuros = espacioConjuros;
     }

     

     public String getNombre() {
         return Nombre;
     }

     public void setNombre(String Nombre) {
         this.Nombre = Nombre;
     }

     public String getSub_Raza() {
         return Sub_Raza;
     }

     public void setSub_Raza(String Sub_Raza) {
         this.Sub_Raza = Sub_Raza;
     }

     public String getClase() {
         return Clase;
     }

     public void setClase(String Clase) {
         this.Clase = Clase;
     }

     public Boolean getSxP() {
         return SxP;
     }

     public void setSxP(Boolean SxP) {
         this.SxP = SxP;
     }

     public int getFuerza() {
         return Fuerza;
     }

     public void setFuerza(int Fuerza) {
         if(this.getRaza().equals(Personaje.Razas.Razas[3])){//HUMANO
             this.Fuerza = Fuerza +1;
         }
         if(SubRazas.SR_enano[1].equals(this.getSub_Raza())){//Enano de las montañas
             this.Fuerza = Fuerza + 2;
         }
         else{
             this.Fuerza = Fuerza;
         }
     }

     public int getDestreza() {
         return Destreza;
     }

     public void setDestreza(int Destreza) {
         if(this.getRaza().equals(Personaje.Razas.Razas[1]) ||
                 this.getRaza().equals(Personaje.Razas.Razas[2])){ //ELFO || MEDIANO
             this.Destreza = Destreza+2;
         }
         if(this.getRaza().equals(Personaje.Razas.Razas[3])||
                 this.getSub_Raza().equals(SubRazas.SR_gnomo[0])){ //HUMANO
             this.Destreza = Destreza +1;
         }
         else{
             this.Destreza = Destreza;
         }
     }

     public int getConstitucion() {
         return Constitucion;
     }

     public void setConstitucion(int Constitucion) {
         if(this.getRaza().equals(Personaje.Razas.Razas[3])||
                 this.getSub_Raza().equals(SubRazas.SR_gnomo[0]) ||
                 this.getSub_Raza().equals(SubRazas.SR_mediano[1])){//HUMANO, MEDIANO FORNIDO
             this.Constitucion = Constitucion+1;
         }
         if(this.getRaza().equals(Personaje.Razas.Razas[0])){//ENANO
             this.Constitucion = Constitucion+2;
         }else{
             this.Constitucion = Constitucion;
         }
     }

     public int getInteligencia() {
         return Inteligencia;
     }

     public void setInteligencia(int Inteligencia) {
         if(this.getRaza().equals(Personaje.Razas.Razas[4])){//Gnomo
             this.Inteligencia = Inteligencia +2;
         }
         if(this.getRaza().equals(Personaje.Razas.Razas[3])||                 //HUMANO
                 this.getSub_Raza().equals(SubRazas.SR_elfo[1])){ //ALTO ELFO
             this.Inteligencia = Inteligencia +1;
         }else{
             this.Inteligencia = Inteligencia;
         }
     }

     public int getSabiduria() {
         return Sabiduria;
     }

     public void setSabiduria(int Sabiduria) {
         if(this.getRaza().equals(Personaje.Razas.Razas[3])||                //HUMANO
                 this.getSub_Raza().equals(SubRazas.SR_enano[0])||
                 this.getSub_Raza().equals(SubRazas.SR_enano[1])){//ENANO DE LAS COLINAS
             this.Sabiduria = Sabiduria +1;
         }else{
             this.Sabiduria = Sabiduria;
         }

     }

     public int getCarisma() {
         return Carisma;
     }

     public void setCarisma(int Carisma) {
         if(this.getRaza().equals(Personaje.Razas.Razas[3])||//HUMANO
                 this.getSub_Raza().equals(SubRazas.SR_elfo[2]) || //Elfo oscuro
                 this.getSub_Raza().equals(SubRazas.SR_mediano[0])){ //Mediano piesligeros
             this.Carisma = Carisma +1;
         }else{
             this.Carisma = Carisma;
         }
     }


     public int getModStr() {
         return ModStr;
     }

     public void setModStr() {
         this.ModStr = (int) calcModCaract(this.getFuerza());
     }

     public int getModDes() {
         return (int) ModDes;
     }

     public void setModDes() {
         this.ModDes = (int) calcModCaract(this.getDestreza());
     }

     public int getModCon() {
         return (int) ModCon;
     }

     public void setModCon() {
         this.ModCon = (int) calcModCaract(this.getConstitucion());
     }

     public int getModInt() {
         return ModInt;
     }

     public void setModInt() {
         this.ModInt = (int) calcModCaract(this.getInteligencia());
     }

     public int getModSab() {
         return ModSab;
     }

     public void setModSab() {
         this.ModSab = (int) calcModCaract(this.getSabiduria());
     }

     public int getModCar() {
         return ModCar;
     }

     public void setModCar() {
         this.ModCar = (int) calcModCaract(this.getCarisma());
     }

     public int getVel() {
         return Vel;
     }

     public void setVel() {

         if(Personaje.Razas.Razas[0].equals(this.getRaza())|| //Enano
                 Personaje.Razas.Razas[2].equals(this.getRaza())|| //Mediano
                 Personaje.Razas.Razas[4].equals(this.getRaza())){ //Gnomo
             this.Vel = 25;
         }

         if(Personaje.Razas.Razas[1].equals(this.getRaza())){
             this.Vel = 30;
             if(SubRazas.SR_elfo[1].equals(this.getRaza())){ //Elfo de los bosques
                 this.Vel = this.getVel()+5;
             }
         }
         if(Personaje.Razas.Razas[3].equals(this.getRaza())){
             this.Vel=30;
         }
     }

     public int getCA() {
         return CA;
     }

     public void setCA(int CA) {
         this.CA = CA;
     }

     public int getPG() {
         return PG;
     }

     public void setPG(int PG) {
         this.PG = PG;

     }

     public int getPGT() {
         return PGT;
     }

     public void setPGT(int PGT) {
         this.PGT = PGT;
     }

     public int getLevel() {
         return Level;
     }

     public void setLevel(int Level) {
         this.Level = Level;
     }

     public int getEXP() {
         return EXP;
     }

     public void setEXP(int EXP) {
         this.EXP = EXP;
     }

     public int getAptitudMagica() {
         return AptitudMagica;
     }

     public void setAptitudMagica(int AptitudMagica) {


         this.AptitudMagica = AptitudMagica;
     }


     public int getInspiracion() {
         return Inspiracion;
     }

     public void setInspiracion(int Inspiracion) {
         this.Inspiracion = Inspiracion;
     }

     public int getBonoCompetencia() {
         return BonoCompetencia;
     }

     public void setBonoCompetencia(int BonoCompetencia) {
         this.BonoCompetencia = BonoCompetencia;
     }

     public int getIniciativa() {
         return Iniciativa;
     }

     public void setIniciativa(int iniciativa) {
         this.Iniciativa = iniciativa;
     }

        public static class Razas{
            public static String[] Razas={"Enano","Elfo","Mediano","Humano","Gnomo"};
        }

        public static class SubRazas{
            public static String[] SR_enano={"de las Montañas","de las Colinas"};
            public static String[] SR_elfo={"Alto","de los Bosques","Oscuro"};
            public static String[] SR_gnomo={"de los Bosques","de las Rocas"};
            public static String[] SR_mediano={" Piesligeros"," Fornido"};
        }

        public static class Clases{
            public static String[] Clases={"Picaro","Guerrero","Hechicero","Druida","Bardo"};
        }

     private int calcModCaract(int val){ //calcula el modificador de característica de cada Característica en función de su valor
         double res = (double)val;
         res = Math.floor((res-10)/2);
         return (int) Math.floor(res);
     }

        public int lanzarDadosCaracteristica(){
        
            Dado dado1 = new Dado();
            dado1.setValorDado(Dado.Valor.d6);
            int resultDado=0;
            
            for(int i = 0; i<3;i++){
                resultDado = dado1.lanzarDado()+resultDado; //cada vez que se invoca la función lanzarDado() se genera un número al azar entre 1 y el valor máximo del dado
            }
            return resultDado;     
        }
    }
