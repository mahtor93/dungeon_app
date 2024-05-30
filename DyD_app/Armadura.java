public class Armadura {
    
    private String Nombre;
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    private int Coste;
    public int getCoste() {
        return Coste;
    }
    public void setCoste(int coste) {
        Coste = coste;
    }
    private int Clase_ArmaduraDA;
    public int getClase_ArmaduraDA() {
        return Clase_ArmaduraDA;
    }
    public void setClase_ArmaduraDA(int clase_ArmaduraDA) {
        Clase_ArmaduraDA = clase_ArmaduraDA;
    }
    private int Fuerza;
    public int getFuerza() {
        return Fuerza;
    }
    public void setFuerza(int fuerza) {
        Fuerza = fuerza;
    }
    private boolean Sigilo;
    public boolean isSigilo() {
        return Sigilo;
    }
    public void setSigilo(boolean sigilo) {
        Sigilo = sigilo;
    }
    private int Peso;
    
    public int getPeso() {
        return Peso;
    }
    public void setPeso(int peso) {
        Peso = peso;
    }

    public static class tipo_Armadura{
        public static String[] tipo_Armadura={"Armadura ligera","Armadura media",
        "Armadura pesada", "Escudo"};
    }

    
    
}
