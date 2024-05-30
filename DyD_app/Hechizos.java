package hechizos;
/**
 *
 * @author anton
 */
public class Hechizos {

    
    private String nombre;
    private int nivel;
    private Boolean v; //Verbal. El hechizo requiere de pronunciar una palabra mágica
    private Boolean s; //Somatico. El hechizo requiere de algún movimiento
    private Boolean m; //Material. El hechizo requiere de algún material
    
    public static class tiempo_Casteo{
        public static String[] tiempo_Casteo={"1 Accion","Accion Gratis",
        "Reaccion", "1 Minuto", "10 Minutos", "1 Hora", "8 Horas", "12 Horas", "24 Horas"};
    }
    
    
    public static class tipo_hechizo{
        public static String[] tipo_Arma={"Abjuracion","Alteracion",
        "Conjuracion", "Adivinacion", "Encantamiento", "Ilusion", "Invocacion", "Necromancia"};
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Boolean getV() {
        return v;
    }

    public void setV(Boolean v) {
        this.v = v;
    }

    public Boolean getS() {
        return s;
    }

    public void setS(Boolean s) {
        this.s = s;
    }

    public Boolean getM() {
        return m;
    }

    public void setM(Boolean m) {
        this.m = m;
    }
    
    
    
}
