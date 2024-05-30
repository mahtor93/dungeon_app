package dyd_clase_construccion;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Metatron
 */
public class Personaje {

    
    private String Nombre;
    private String Raza; //Debe ser una clase
    private String Sub_Raza; //Debe ser una clase
    private String Clase; //Debe ser una clase

    private Boolean SxP; //Boolean puede tomar valor true, false y 0

    private int Fuerza=0;
    private int Destreza=0;
    private int Constitucion=0;
    private int Inteligencia=0;
    private int Sabiduria=0;
    private int Carisma=0;
    
    private int ModCar_Fuerza;
    private int ModCar_Destreza;
    private int ModCar_Constitucion;
    private int ModCar_Inteligencia;
    private int ModCar_Sabiduria;
    private int ModCar_Carisma;
    
    private boolean Acrobacias;
    private boolean Atletismo;
    private boolean C_Arcano;
    private boolean Enganio;
    private boolean Historia;
    private boolean Interpretacion;
    private boolean Intimidacion;
    private boolean Investigacion;
    private boolean JuegodeManos;
    private boolean Medicina;
    private boolean Naturaleza;
    private boolean Percepcion;
    private boolean Perspicacia;
    private boolean Persuacion;
    private boolean Religion;
    private boolean Sigilo;
    private boolean Supervivencia;
    private boolean TratoconAnimales;
    
    private int Vel;
    private int CA; //Clase de armadura
    private int PG; //Puntos de golpe
    private int PGT; //Puntos de golpe temporales
    
    private int Level; 
    private String Alineamiento;
    private int EXP;
    
    private int espacioConjuros;

    public int getEspacioConjuros() {
        return espacioConjuros;
    }

    public void setEspacioConjuros(int espacioConjuros) {
        this.espacioConjuros = espacioConjuros;
    }
    
    //Recursos
    private int PC;//piezas de cobre
    private int PP;//piezas de plata
    private int PE;//piezas de estaño
    private int PO;//piezas de oro
    private int PPT;//¿?
    
    //Rasgos físicos
    private int Edad;
    private double Altura;
    private double Peso;
    private String Ojos;
    private String Piel;
    private String Pelo;
    
    private String AptitudMagica; //Debe ser una característica
    private int CDSalvConjuro; //Clase dificultad salvacion de conjuro
    private int BonificadorAtaqueConjuro;
    
    private int Inspiracion;
    private int BonoCompetencia;
    private int Iniciativa;
    
    
    
    private int Exitos;//Salvaciones contra la muerte
    private int Fallos;//Salvaciones contra la muerte
    
    private String RasgosPersonalidad;
    private String Ideales;
    private String Vinculos;
    private String Defectos;
    private String HistoriaPersonaje;
    private String ClaseLanzadoraDeConjuros;

    
    public boolean isAcrobacias() {
        return Acrobacias;
    }

    public void setAcrobacias(boolean Acrobacias) {
        this.Acrobacias = Acrobacias;
    }

    public boolean isAtletismo() {
        return Atletismo;
    }

    public void setAtletismo(boolean Atletismo) {
        this.Atletismo = Atletismo;
    }

    public boolean isC_Arcano() {
        return C_Arcano;
    }

    public void setC_Arcano(boolean C_Arcano) {
        this.C_Arcano = C_Arcano;
    }

    public boolean isEnganio() {
        return Enganio;
    }

    public void setEnganio(boolean Enganio) {
        this.Enganio = Enganio;
    }

    public boolean isHistoria() {
        return Historia;
    }

    public void setHistoria(boolean Historia) {
        this.Historia = Historia;
    }

    public boolean isInterpretacion() {
        return Interpretacion;
    }

    public void setInterpretacion(boolean Interpretacion) {
        this.Interpretacion = Interpretacion;
    }

    public boolean isIntimidacion() {
        return Intimidacion;
    }

    public void setIntimidacion(boolean Intimidacion) {
        this.Intimidacion = Intimidacion;
    }

    public boolean isInvestigacion() {
        return Investigacion;
    }

    public void setInvestigacion(boolean Investigacion) {
        this.Investigacion = Investigacion;
    }

    public boolean isJuegodeManos() {
        return JuegodeManos;
    }

    public void setJuegodeManos(boolean JuegodeManos) {
        this.JuegodeManos = JuegodeManos;
    }

    public boolean isMedicina() {
        return Medicina;
    }

    public void setMedicina(boolean Medicina) {
        this.Medicina = Medicina;
    }

    public boolean isNaturaleza() {
        return Naturaleza;
    }

    public void setNaturaleza(boolean Naturaleza) {
        this.Naturaleza = Naturaleza;
    }

    public boolean isPercepcion() {
        return Percepcion;
    }

    public void setPercepcion(boolean Percepcion) {
        this.Percepcion = Percepcion;
    }

    public boolean isPerspicacia() {
        return Perspicacia;
    }

    public void setPerspicacia(boolean Perspicacia) {
        this.Perspicacia = Perspicacia;
    }

    public boolean isPersuacion() {
        return Persuacion;
    }

    public void setPersuacion(boolean Persuacion) {
        this.Persuacion = Persuacion;
    }

    public boolean isReligion() {
        return Religion;
    }

    public void setReligion(boolean Religion) {
        this.Religion = Religion;
    }

    public boolean isSigilo() {
        return Sigilo;
    }

    public void setSigilo(boolean Sigilo) {
        this.Sigilo = Sigilo;
    }

    public boolean isSupervivencia() {
        return Supervivencia;
    }

    public void setSupervivencia(boolean Supervivencia) {
        this.Supervivencia = Supervivencia;
    }

    public boolean isTratoconAnimales() {
        return TratoconAnimales;
    }

    public void setTratoconAnimales(boolean TratoconAnimales) {
        this.TratoconAnimales = TratoconAnimales;
    }
    
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getRaza() {
        return Raza;
    }

    public void setRaza(String Raza) {
        this.Raza = Raza;
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
        this.Fuerza = Fuerza;
    }

    public int getDestreza() {
        return Destreza;
    }

    public void setDestreza(int Destreza) {
        this.Destreza = Destreza;
    }

    public int getConstitucion() {
        return Constitucion;
    }

    public void setConstitucion(int Constitucion) {
        this.Constitucion = Constitucion;
    }

    public int getInteligencia() {
        return Inteligencia;
    }

    public void setInteligencia(int Inteligencia) {
        this.Inteligencia = Inteligencia;
    }

    public int getSabiduria() {
        return Sabiduria;
    }

    public void setSabiduria(int Sabiduria) {
        this.Sabiduria = Sabiduria;
    }

    public int getCarisma() {
        return Carisma;
    }

    public void setCarisma(int Carisma) {
        this.Carisma = Carisma;
    }

    public int getModCar_Fuerza() {
        return ModCar_Fuerza;
    }

    public void setModCar_Fuerza(int ModCar_Fuerza) {
        this.ModCar_Fuerza = ModCar_Fuerza;
    }

    public int getModCar_Destreza() {
        return ModCar_Destreza;
    }

    public void setModCar_Destreza(int ModCar_Destreza) {
        this.ModCar_Destreza = ModCar_Destreza;
    }

    public int getModCar_Constitucion() {
        return ModCar_Constitucion;
    }

    public void setModCar_Constitucion(int ModCar_Constitucion) {
        this.ModCar_Constitucion = ModCar_Constitucion;
    }

    public int getModCar_Inteligencia() {
        return ModCar_Inteligencia;
    }

    public void setModCar_Inteligencia(int ModCar_Inteligencia) {
        this.ModCar_Inteligencia = ModCar_Inteligencia;
    }

    public int getModCar_Sabiduria() {
        return ModCar_Sabiduria;
    }

    public void setModCar_Sabiduria(int ModCar_Sabiduria) {
        this.ModCar_Sabiduria = ModCar_Sabiduria;
    }

    public int getModCar_Carisma() {
        return ModCar_Carisma;
    }

    public void setModCar_Carisma(int ModCar_Carisma) {
        this.ModCar_Carisma = ModCar_Carisma;
    }

    public int getVel() {
        return Vel;
    }

    public void setVel(int Vel) {
        this.Vel = Vel;
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

    public String getAlineamiento() {
        return Alineamiento;
    }

    public void setAlineamiento(String Alineamiento) {
        this.Alineamiento = Alineamiento;
    }

    public int getEXP() {
        return EXP;
    }

    public void setEXP(int EXP) {
        this.EXP = EXP;
    }

    public int getPC() {
        return PC;
    }

    public void setPC(int PC) {
        this.PC = PC;
    }

    public int getPP() {
        return PP;
    }

    public void setPP(int PP) {
        this.PP = PP;
    }

    public int getPE() {
        return PE;
    }

    public void setPE(int PE) {
        this.PE = PE;
    }

    public int getPO() {
        return PO;
    }

    public void setPO(int PO) {
        this.PO = PO;
    }

    public int getPPT() {
        return PPT;
    }

    public void setPPT(int PPT) {
        this.PPT = PPT;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }

    public double getAltura() {
        return Altura;
    }

    public void setAltura(double Altura) {
        this.Altura = Altura;
    }

    public double getPeso() {
        return Peso;
    }

    public void setPeso(double Peso) {
        this.Peso = Peso;
    }

    public String getOjos() {
        return Ojos;
    }

    public void setOjos(String Ojos) {
        this.Ojos = Ojos;
    }

    public String getPiel() {
        return Piel;
    }

    public void setPiel(String Piel) {
        this.Piel = Piel;
    }

    public String getPelo() {
        return Pelo;
    }

    public void setPelo(String Pelo) {
        this.Pelo = Pelo;
    }

    public String getAptitudMagica() {
        return AptitudMagica;
    }

    public void setAptitudMagica(String AptitudMagica) {
        this.AptitudMagica = AptitudMagica;
    }

    public int getCDSalvConjuro() {
        return CDSalvConjuro;
    }

    public void setCDSalvConjuro(int CDSalvConjuro) {
        this.CDSalvConjuro = CDSalvConjuro;
    }

    public int getBonificadorAtaqueConjuro() {
        return BonificadorAtaqueConjuro;
    }

    public void setBonificadorAtaqueConjuro(int BonificadorAtaqueConjuro) {
        this.BonificadorAtaqueConjuro = BonificadorAtaqueConjuro;
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

    public void setIniciativa(int Iniciativa) {
        this.Iniciativa = Iniciativa;
    }

    public int getExitos() {
        return Exitos;
    }

    public void setExitos(int Exitos) {
        this.Exitos = Exitos;
    }

    public int getFallos() {
        return Fallos;
    }

    public void setFallos(int Fallos) {
        this.Fallos = Fallos;
    }

    public String getRasgosPersonalidad() {
        return RasgosPersonalidad;
    }

    public void setRasgosPersonalidad(String RasgosPersonalidad) {
        this.RasgosPersonalidad = RasgosPersonalidad;
    }

    public String getIdeales() {
        return Ideales;
    }

    public void setIdeales(String Ideales) {
        this.Ideales = Ideales;
    }

    public String getVinculos() {
        return Vinculos;
    }

    public void setVinculos(String Vinculos) {
        this.Vinculos = Vinculos;
    }

    public String getDefectos() {
        return Defectos;
    }

    public void setDefectos(String Defectos) {
        this.Defectos = Defectos;
    }

    public String getHistoriaPersonaje() {
        return HistoriaPersonaje;
    }

    public void setHistoriaPersonaje(String HistoriaPersonaje) {
        this.HistoriaPersonaje = HistoriaPersonaje;
    }

    public String getClaseLanzadoraDeConjuros() {
        return ClaseLanzadoraDeConjuros;
    }

    public void setClaseLanzadoraDeConjuros(String ClaseLanzadoraDeConjuros) {
        this.ClaseLanzadoraDeConjuros = ClaseLanzadoraDeConjuros;
    }
    
    
    public static class Pronouns{
        public static String[] pron_neut = {"e",};
        public static String[] pron_masc ={"o"};
        public static String[] pron_fem={"a"};
    }
    
    public static class Razas{
        public static String[] Razas={"Enan","Elf","Median","Human","Draconid","Gnom","Semielf","Semiorc","Tiefling"};
    }
    
    public static class SubRazas_Enano{
        public static String[] Sr_enano={""};
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
    
    public boolean calcCaracteristicas(){ //Método de prueba.
        
        if(this.getFuerza()==0 || 
            this.getDestreza()==0||
            this.getCarisma()==0||
            this.getConstitucion()==0||
            this.getInteligencia()==0||
            this.getSabiduria()==0
            ){
            this.setFuerza(lanzarDadosCaracteristica());
            this.setCarisma(lanzarDadosCaracteristica());
            this.setDestreza(lanzarDadosCaracteristica());
            this.setConstitucion(lanzarDadosCaracteristica());
            this.setSabiduria(lanzarDadosCaracteristica());
            this.setInteligencia(lanzarDadosCaracteristica());
            return true;
        } else{
            return false;
        }
        
    }
    
    
}
