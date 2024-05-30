package com.example.creacindepersonaje.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.creacindepersonaje.BDConnection.DBConnection;
import com.example.creacindepersonaje.Modelos.Personaje;

import java.util.ArrayList;

public class PersonajeController {

    public static boolean RemovePersonaje(Context context, int ID) {
        DBConnection con = new DBConnection(context,"base1",null,1);
        SQLiteDatabase db = con.getWritableDatabase();
        String query = "DELETE FROM personajes WHERE idPersonaje="+ID;
        db.execSQL(query);
        db.close();
        return true;
    }

    public static Personaje battlePersonaje(Context context, int ID) throws Exception {
        try{
            Personaje p = new Personaje();
            DBConnection c = new DBConnection(context,"base1",null,1);
            SQLiteDatabase bd = c.getReadableDatabase();
            Cursor bPj = bd.rawQuery("SELECT idPersonaje,NOMBRE_PERSONAJE," +
                    "RAZA_PERSONAJE,SUBRAZA_PERSONAJE,PG,CA,idHechizo,idArma,idArmadura," +
                    "FUERZA,DESTREZA,CONSTITUCION,INTELIGENCIA,SABIDURIA,CARISMA, " +
                    "NIVEL,EXP," +
                    "INSPIRACION,BONO_COMPETENCIA,INICIATIVA",null );
            if(bPj.moveToFirst()){
                p.setIdPersonaje(bPj.getInt(0));
                p.setNombre(bPj.getString(1));
                p.setRaza(bPj.getString(2));
                p.setSub_Raza(bPj.getString(3));
                p.setPG(bPj.getInt(4));
                p.setCA(bPj.getInt(5));
                p.setIdHechizo(bPj.getInt(6));
                p.setIdArma(bPj.getInt(7));
                p.setIdArmadura(bPj.getInt(8));
                p.setFuerza(bPj.getInt(9));
                p.setDestreza(bPj.getInt(10));
                p.setConstitucion(bPj.getInt(11));
                p.setInteligencia(bPj.getInt(12));
                p.setSabiduria(bPj.getInt(13));
                p.setCarisma(bPj.getInt(14));
                p.setLevel(bPj.getInt(15));
                p.setEXP(bPj.getInt(16));
                p.setInspiracion(bPj.getInt(17));
                p.setBonoCompetencia(bPj.getInt(18));
                p.setIniciativa(bPj.getInt(19));
                p.setModCar();
                p.setModSab();
                p.setModInt();
                p.setModDes();
                p.setModCon();
                p.setModStr();
            }
            bPj.close();
            return p;
        }catch (Exception ex){
            throw new Exception("ERROR AL ENCONTRAR PERSONAJE "+ex);
        }


    }

    public static boolean editPj(Context context, Personaje p,String txt) throws Exception {
        try{
            DBConnection c = new DBConnection(context,"base1",null,1);
            SQLiteDatabase bd = c.getWritableDatabase();

            bd.execSQL("UPDATE personajes SET NOMBRE_PERSONAJE ='"+txt+"', NIVEL = 1 WHERE idPersonaje = "+ p.getIdPersonaje());
            bd.close();
            return true;
        }catch(Exception ex){
            throw new Exception("ERROR AL MODIFICAR "+ex);
        }
    }

    public static Personaje findPj(Context context, int ID) throws Exception {

        try {
            DBConnection con = new DBConnection(context, "base1", null, 1);
            SQLiteDatabase db = con.getReadableDatabase();
            Personaje p = new Personaje();
            Cursor pj = db.rawQuery("SELECT idPersonaje, NOMBRE_PERSONAJE,"
                    + "RAZA_PERSONAJE,SUBRAZA_PERSONAJE,CLASE_PERSONAJE,FUERZA,DESTREZA,CONSTITUCION,INTELIGENCIA,SABIDURIA,CARISMA," +
                    "MOD_FUE,MOD_DES,MOD_CON,MOD_INT,MOD_SAB,MOD_CAR," +
                    "VEL,CA,PG,NIVEL,EXP,ESPACIO_CONJUROS,APTITUD_MAGICA,INSPIRACION,BONO_COMPETENCIA,INICIATIVA,idArma,idArmadura,idHechizo "
                    + "FROM personajes WHERE idPersonaje =" + ID, null);
            if (pj.moveToFirst()) {
                do {
                    p.setIdPersonaje(pj.getInt(0));
                    p.setNombre(pj.getString(1));
                    p.setRaza(pj.getString(2));
                    p.setSub_Raza(pj.getString(3));
                    p.setClase(pj.getString(4));
                    p.setFuerza(pj.getInt(5));
                    p.setDestreza(pj.getInt(6));
                    p.setConstitucion(pj.getInt(7));
                    p.setInteligencia(pj.getInt(8));
                    p.setSabiduria(pj.getInt(9));
                    p.setCarisma(pj.getInt(10));
                    p.setModStr();
                    p.setModCon();
                    p.setModCar();
                    p.setModDes();
                    p.setModInt();
                    p.setModSab();
                    p.setVel();
                    p.setCA(pj.getInt(18));
                    p.setPG(pj.getInt(19));
                    p.setLevel(pj.getInt(20));
                    p.setEXP(pj.getInt(21));
                    p.setAptitudMagica(pj.getInt(23));
                    p.setInspiracion(pj.getInt(24));
                    p.setBonoCompetencia(pj.getInt(25));
                    p.setIniciativa(pj.getInt(26));
                    p.setIdArma(pj.getInt(27));
                    p.setIdArmadura(pj.getInt(28));
                    p.setIdHechizo(pj.getInt(29));
                } while (pj.moveToNext());
            }
            con.close();
            pj.close();
            return p;
        } catch (Exception ex) {
            throw new Exception("ERROR AL ENCONTRAR" + ex);
        }
    }

    public static int getIdPorNombre(Context context,String name) throws Exception {
        try{
            int idFinal = -1;
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getReadableDatabase();
            Cursor findId = db.rawQuery("SELECT idPersonaje FROM personajes WHERE NOMBRE_PERSONAJE='"+name+"'",null);
            if(findId.moveToFirst()){
                idFinal = findId.getInt(0);
            }
            return idFinal;
        }catch (Exception ex){
            throw new Exception("Error al encontrar al personaje "+ex);
        }
    }

    public static long createPersonaje(Context context, Personaje p) throws Exception {
        long id = 0;
        try {
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("NOMBRE_PERSONAJE", p.getNombre());
            cv.put("idGuild",p.getIdGuild());
            cv.put("RAZA_PERSONAJE", p.getRaza());
            cv.put("SUBRAZA_PERSONAJE", p.getSub_Raza());
            cv.put("CLASE_PERSONAJE", p.getClase());
            cv.put("idHechizo", p.getIdHechizo());
            cv.put("idArma", p.getIdArma());
            cv.put("idArmadura", p.getIdArmadura());
            cv.put("FUERZA", p.getFuerza());
            cv.put("DESTREZA", p.getDestreza());
            cv.put("CONSTITUCION", p.getConstitucion());
            cv.put("INTELIGENCIA", p.getInteligencia());
            cv.put("SABIDURIA", p.getSabiduria());
            cv.put("CARISMA", p.getCarisma());
            cv.put("MOD_FUE", p.getModStr());
            cv.put("MOD_DES", p.getModDes());
            cv.put("MOD_CON", p.getModCon());
            cv.put("MOD_INT", p.getModInt());
            cv.put("MOD_SAB", p.getModSab());
            cv.put("MOD_CAR", p.getModCar());
            cv.put("VEL", p.getVel());
            cv.put("CA", p.getCA());
            cv.put("PG", p.getPG());
            cv.put("NIVEL", p.getLevel());
            cv.put("EXP", p.getEXP());
            cv.put("ESPACIO_CONJUROS", p.getEspacioConjuros());
            cv.put("APTITUD_MAGICA", p.getAptitudMagica());
            cv.put("INSPIRACION", 0);
            cv.put("BONO_COMPETENCIA", 2);
            cv.put("INICIATIVA", 0);
            id = db.insert("personajes", null, cv);
            return id;

        }catch (Exception ex){
            Toast.makeText(context, "Error "+ex, Toast.LENGTH_SHORT).show();
            return id;

        }
    }

    public static ArrayList<Personaje> listPersonajes(Context context) throws Exception {
        try{
            ArrayList<Personaje> pjList = new ArrayList<>();
            DBConnection con = new DBConnection(context, "base1", null, 1);
            SQLiteDatabase db = con.getReadableDatabase();
            Cursor pj = db.rawQuery("SELECT idPersonaje,idGuild,NOMBRE_PERSONAJE,RAZA_PERSONAJE,SUBRAZA_PERSONAJE,CLASE_PERSONAJE,NIVEL FROM personajes", null);
            String descPj;
            if (pj.moveToFirst()) {
                do {
                    Personaje p = new Personaje();
                    p.setIdPersonaje(pj.getInt(0));
                    p.setIdGuild(pj.getInt(1));
                    p.setNombre(pj.getString(2));
                    p.setRaza(pj.getString(3));
                    p.setSub_Raza(pj.getString(4));
                    p.setClase(pj.getString(5));
                    p.setLevel(pj.getInt(6));
                    pjList.add(p);
                } while (pj.moveToNext());
            }
            db.close();
            return pjList;
        }catch (Exception ex){
            throw new Exception("Error al listar personajes "+ex);
        }
    }

    public static boolean setIniciativa(Context context, int idPersonaje,int iniciativa) throws Exception {
        try{
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getWritableDatabase();
            String query = "UPDATE personajes SET "+
                    "INICIATIVA="+iniciativa+
                    " WHERE idPersonaje="+idPersonaje;
            db.execSQL(query);
            db.close();
            return true;
        }catch (Exception ex){
            throw new Exception("Error al modificar "+ex);
        }
    }

    public static boolean setVida(Context context, int idPersonaje,int newHP) throws Exception {
        try{
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getWritableDatabase();
            String query = "UPDATE personajes SET "+
                    "PG="+newHP+
                    " WHERE idPersonaje="+idPersonaje;
            db.execSQL(query);
            db.close();
            return true;
        }catch (Exception ex){
            throw new Exception("Error al modificar HP "+ex);
        }
    }
}


