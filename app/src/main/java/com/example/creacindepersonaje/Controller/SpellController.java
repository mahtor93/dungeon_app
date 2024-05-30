package com.example.creacindepersonaje.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.creacindepersonaje.BDConnection.DBConnection;
import com.example.creacindepersonaje.Modelos.Hechizo;

import java.util.ArrayList;

public class SpellController {

    public static ArrayList<Hechizo> listarHechizos(Context context) throws Exception {
        ArrayList<Hechizo> items = new ArrayList<>();
        try {
            DBConnection con = new DBConnection(context, "base1", null, 1);
            SQLiteDatabase db = con.getReadableDatabase();
            Cursor allSpells = db.rawQuery("SELECT idHechizo, NOMBRE_HECHIZO, NIVEL_HECHIZO, DAMAGE, HEAL, SHIELD, DESCRIPCION, TIPO_HECHIZO FROM spell", null);
            if (allSpells.moveToFirst()) {
                do {
                    Hechizo s = new Hechizo();
                    s.setIdHechizo(allSpells.getInt(0));
                    s.setNombre(allSpells.getString(1));
                    s.setNivelHechizo(allSpells.getInt(2));
                    s.setDamage(allSpells.getInt(3));
                    s.setHeal(allSpells.getInt(4));
                    s.setShield(allSpells.getInt(5));
                    s.setDescripcion(allSpells.getString(6));
                    s.setTipoHechizo(allSpells.getString(7));
                    items.add(s);
                } while (allSpells.moveToNext());
            }
            allSpells.close();
            return items;
        }catch (Exception ex){
            throw new Exception("ERROR AL LISTAR"+ex);
        }
    } //Listar todos
    public static Hechizo findHechizo(Context context, int ID_Hechizo) throws Exception {
        try{
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getReadableDatabase();
            Cursor findSpell = db.rawQuery("SELECT idHechizo,NOMBRE_HECHIZO,NIVEL_HECHIZO,DAMAGE,HEAL,SHIELD,DESCRIPCION,TIPO_HECHIZO FROM spell where idHechizo="+ID_Hechizo,null);
            Hechizo s = new Hechizo();
            if(findSpell.moveToFirst()) {
                s.setNombre(findSpell.getString(1));
                s.setNivelHechizo(findSpell.getInt(2));
                s.setDamage(findSpell.getInt(3));
                s.setHeal(findSpell.getInt(4));
                s.setShield(findSpell.getInt(5));
                s.setDescripcion(findSpell.getString(6));
                s.setTipoHechizo(findSpell.getString(7));
                s.setIdHechizo(findSpell.getInt(0));

            }
            findSpell.close();
            return s;
        }catch(Exception ex){
            throw new Exception("No se encontró el hechizo"+ex);
        }
    } //Listar uno
    public static boolean removeHechizo(Context context, int ID_HECHIZO) throws Exception {
        try{
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getWritableDatabase();
            String query ="DELETE FROM spell WHERE idHechizo="+ID_HECHIZO;
            db.execSQL(query);
            db.close();
            return true;
        }catch(Exception ex){
            throw new Exception("No se pudo eliminar "+ex);
        }
    } //Eliminar
    public static boolean createHechizo(Context context, Hechizo spell) throws Exception {
        try{
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getWritableDatabase();
            ContentValues cov = new ContentValues();
            cov.put("NOMBRE_HECHIZO",spell.getNombre());
            cov.put("NIVEL_HECHIZO",spell.getNivelHechizo());
            cov.put("DAMAGE",spell.getDamage());
            cov.put("HEAL",spell.getHeal());
            cov.put("SHIELD",spell.getShield());
            cov.put("DESCRIPCION",spell.getDescripcion());
            cov.put("TIPO_HECHIZO",spell.getTipoHechizo());
            long id = db.insert("spell",null,cov);
            if(id>0){
                Toast.makeText(context, "guardado con exito", Toast.LENGTH_SHORT).show();
                db.close();
                return true;
            }
            else {
                Toast.makeText(context, "No se guardó", Toast.LENGTH_SHORT).show();
                return false;
            }

        }catch(Exception ex){
            throw new Exception("ERROR AL CREAR "+ex);
        }
    } //Crear Hechizo
    public static boolean editHechizo(Context context, Hechizo spell) throws Exception {
        try{
            DBConnection c = new DBConnection(context, "base1", null, 1);
            SQLiteDatabase bd = c.getWritableDatabase();
            int idHechizo = spell.getIdHechizo();
            String query = "UPDATE spell SET " +
                    "NOMBRE_HECHIZO ='"+spell.getNombre()+
                    "',DESCRIPCION='"+spell.getDescripcion()+
                    "' WHERE idHechizo="+idHechizo;
            bd.execSQL(query);
            bd.close();
            return true;
        }catch (Exception ex){
            throw new Exception("ERROR AL MODIFICAR "+ex);
        }
    }

    public static int findHechizoByName(Context context, String name) throws Exception {
        try{
            int idHechizo = -1;
            DBConnection c = new DBConnection(context,"base1",null,1);
            SQLiteDatabase bd  = c.getReadableDatabase();
            Cursor findId = bd.rawQuery("SELECT idHechizo FROM spell WHERE NOMBRE_HECHIZO='"+name+"'",null);
            if(findId.moveToFirst()){
                idHechizo = findId.getInt(0);
            }
            return idHechizo;
        }catch (Exception ex){
            throw new Exception("ERROR AL ENCONTRAR HECHIZO "+ex);
        }
    }
}
