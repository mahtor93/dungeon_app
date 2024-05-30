//package;

public class Arma {

    private String nombre;
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    private int Coste; // hay mas de una unidad plata oro y cobre (100 platas son un oro?)
    public int getCoste() {
        return Coste;
    }

    public void setCoste(int coste) {
        Coste = coste;
    }

    private int Damage;
    public int getDamage() {
        return Damage;
    }

    public void setDamage(int damage) {
        Damage = damage;
    }

    private boolean tipo_Damage;
    public boolean isTipo_Damage() {
        return tipo_Damage;
    }

    public void setTipo_Damage(boolean tipo_Damage) {
        this.tipo_Damage = tipo_Damage;
    }


    private int Peso;
    public int getPeso() {
        return Peso;
    }

    public void setPeso(int peso) {
        Peso = peso;
    }


    public static class tipo_Arma{
        public static String[] tipo_Arma={"Armas simples cuerpo a cuerpo","Armas simples a distancia",
        "Armas marciales cuerpo a cuerpo", "Armas marciales a distancia"};
    }

    public static class Propiedades {
        public static boolean Dos_manos;
        public static boolean Alcance;
        public static boolean Arrojadiza;
        public static boolean Carga;
        public static boolean Distancia; // 
        public static boolean Especial;
        public static boolean Ligera;
        public static boolean Municion;
        public static boolean Sutil;
        public static boolean Pesada;
        public static boolean Versatil;

    }

    
}
