package com.example.creacindepersonaje.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.creacindepersonaje.BDConnection.DBConnection;
import com.example.creacindepersonaje.Modelos.Arma;
import com.example.creacindepersonaje.Modelos.Armadura;

import java.util.ArrayList;

public class ItemsController {

    public static ArrayList<String> getItemsPJ(Context context, int ID1,int ID2){
        ArrayList<String> items = new ArrayList<>();
        String name;
        DBConnection con = new DBConnection(context,"base1",null,1);
        SQLiteDatabase db = con.getReadableDatabase();
        Cursor armas_pj = db.rawQuery("SELECT nombre, tipoArma from armas where idArma ="+ID1,null);
        if(armas_pj.moveToFirst()){
            do {
                name = "Arma :"+armas_pj.getString(0)+" Tipo :"+armas_pj.getString(1);
                items.add(name);
            }while(armas_pj.moveToNext());
        }
        Cursor armaduras_pj = db.rawQuery("SELECT nombre from armaduras where idArmadura="+ID2,null);
        if(armaduras_pj.moveToFirst()){
            do {
                name = "Armadura :" +armaduras_pj.getString(0);
                items.add(name);
            }while(armaduras_pj.moveToNext());
        }
        armas_pj.close();
        armaduras_pj.close();
        return items;
    }
    public static ArrayList<Arma> getArmas(Context context) throws Exception {
        try{
            ArrayList<Arma> items = new ArrayList<>();
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getReadableDatabase();
            Cursor allArmas = db.rawQuery("SELECT idArma, nombre,damage,tipoArma,tipoDamage,descripcion FROM armas",null);
            if(allArmas.moveToFirst()){
                do{
                    Arma a = new Arma();
                    a.setIdArma(allArmas.getInt(0));
                    a.setNombre(allArmas.getString(1));
                    a.setDamage(allArmas.getInt(2));
                    a.setTipoArma(allArmas.getString(3));
                    a.setTipoDamage(allArmas.getString(4));
                    a.setDescripcion(allArmas.getString(5));
                    items.add(a);
                }while(allArmas.moveToNext());
            }
            allArmas.close();
            return items;
        }catch (Exception ex){
            throw new Exception("ERROR AL OBTENER LA LISTA DE ARMAS" +ex);
        }
    }
    public static ArrayList<Armadura> getArmaduras(Context context) throws Exception {
        try{
            ArrayList<Armadura> items = new ArrayList<>();
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getReadableDatabase();
            Cursor allArmaduras = db.rawQuery("SELECT idArmadura, nombre,claseArmadura,peso,fuerzaRequerida,descripcion FROM armaduras",null);
            if(allArmaduras.moveToFirst()){
                do{
                    Armadura a = new Armadura();
                    a.setIdArmadura(allArmaduras.getInt(0));
                    a.setNombre(allArmaduras.getString(1));
                    a.setClaseArmadura(allArmaduras.getInt(2));
                    a.setPeso(allArmaduras.getInt(3));
                    a.setFuerzaReq(allArmaduras.getInt(4));
                    a.setDescripcion(allArmaduras.getString(5));
                    items.add(a);
                }while(allArmaduras.moveToNext());
            }
            allArmaduras.close();
            return items;
        }catch (Exception ex){
            throw new Exception("ERROR AL OBTENER LA LISTA DE ARMADURAS"+ex);
        }
    }
    public static boolean removeArma(Context context, int idArma) throws Exception {
        try{
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getWritableDatabase();
            String query ="DELETE FROM Armas WHERE idArma ="+idArma;
            db.execSQL(query);
            db.close();
            return true;
        }catch (Exception ex){
            throw new Exception("ERROR AL ELIMINAR ARMA"+ex);
        }
    }
    public static boolean removeArmadura(Context context, int idArmadura) throws Exception {
        try{
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getWritableDatabase();
            String query = "DELETE FROM Armaduras WHERE idArmadura ="+idArmadura;
            db.execSQL(query);
            db.close();
            return true;
        }catch (Exception ex){
            throw new Exception("ERROR AL ELIMINAR ARMADURA"+ex);
        }
    }
    public static Arma findArma(Context context, int idArma) throws Exception {
        try{
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getReadableDatabase();
            Cursor find_item = db.rawQuery("SELECT idArma, nombre,damage,tipoArma,tipoDamage,descripcion FROM armas WHERE idArma="+idArma,null);
            Arma a = new Arma();
            if(find_item.moveToFirst()) {

                a.setIdArma(find_item.getInt(0));
                a.setNombre(find_item.getString(1));
                a.setDamage(find_item.getInt(2));
                a.setTipoArma(find_item.getString(3));
                a.setTipoDamage(find_item.getString(4));
                a.setDescripcion(find_item.getString(5));
            }
            find_item.close();
            return a;

        }catch (Exception ex){
            throw new Exception("NO SE ENCONTRÓ"+ex);
        }
    }
    public static Armadura findArmadura(Context context, int idArmadura) throws Exception {
        try{
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getReadableDatabase();
            Cursor find_item = db.rawQuery("SELECT idArmadura, nombre,claseArmadura,peso,fuerzaRequerida,descripcion FROM armaduras WHERE idArmadura="+idArmadura,null);
            Armadura a = new Armadura();
            if(find_item.moveToFirst()) {

                a.setIdArmadura(find_item.getInt(0));
                a.setNombre(find_item.getString(1));
                a.setClaseArmadura(find_item.getInt(2));
                a.setPeso(find_item.getInt(3));
                a.setFuerzaReq(find_item.getInt(4));
                a.setDescripcion(find_item.getString(5));
            }
            find_item.close();
            return a;
        }catch (Exception ex){
            throw new Exception("NO SE ENCONTRÓ"+ex);
        }
    }
    public static boolean createArma(Context context,Arma arma) throws Exception {
        long id = 0;
        try {
            DBConnection c = new DBConnection(context, "base1", null, 1);
            SQLiteDatabase bd = c.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("nombre",arma.getNombre());
            cv.put("damage",arma.getDamage());
            cv.put("tipoArma",arma.getTipoArma());
            cv.put("tipoDamage",arma.getTipoDamage());
            cv.put("descripcion",arma.getDescripcion());
            id = bd.insert("armas",null,cv);
            if(id>0){
                Toast.makeText(context, "guardado con exito "+id, Toast.LENGTH_SHORT).show();
                bd.close();
                return true;
            }
            else {
                Toast.makeText(context, "No se guardó", Toast.LENGTH_SHORT).show();
                bd.close();
                return false;
            }

        }catch (Exception ex){
            throw new Exception("ERROR AL CREAR ARMA"+ex);
        }
    }
    public static boolean createArmadura(Context context,Armadura armadura) throws Exception {
        try {
            DBConnection c = new DBConnection(context, "base1", null, 1);
            SQLiteDatabase bd = c.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("nombre",armadura.getNombre());
            cv.put("claseArmadura",armadura.getClaseArmadura());
            cv.put("peso",armadura.getPeso());
            cv.put("fuerzaRequerida",armadura.getFuerzaReq());
            cv.put("descripcion",armadura.getDescripcion());
            long id = bd.insert("armaduras",null,cv);
            if(id>0){
                Toast.makeText(context, "guardado con exito", Toast.LENGTH_SHORT).show();
                bd.close();
                return true;
            }
            else {
                Toast.makeText(context, "No se guardó", Toast.LENGTH_SHORT).show();
                bd.close();
                return false;
            }

        }catch (Exception ex){
            throw new Exception("ERROR AL CREAR ARMADURA"+ex);
        }
    }
    public static boolean editArma(Context context,Arma arma) throws Exception {
        try{
            DBConnection c = new DBConnection(context, "base1", null, 1);
            SQLiteDatabase bd = c.getWritableDatabase();
            int idArma = arma.getIdArma();
            String query ="UPDATE armas SET "+
                    "nombre ="+arma.getNombre()+
                    ",damage ="+arma.getDamage()+
                    ",tipoArma ='"+ arma.getTipoArma()+
                    "',tipoDamage='"+arma.getTipoDamage()+
                    "',descripcion="+arma.getDescripcion()+
                    " WHERE idArma="+idArma;
            bd.execSQL(query);
            bd.close();
            return true;
        }catch(Exception ex){
            throw new Exception("ERROR AL MODIFICAR "+ex);
        }
    }
    public static boolean editArmadura(Context context, Armadura armadura) throws Exception {
        try{
            DBConnection c = new DBConnection(context, "base1", null, 1);
            SQLiteDatabase bd = c.getWritableDatabase();
            int idArmadura = armadura.getIdArmadura();
            String query ="UPDATE armaduras SET "+
                    "nombre="+armadura.getNombre()+
                    ",claseArmadura="+armadura.getClaseArmadura()+
                    ",peso="+armadura.getPeso()+
                    ",fuerzaRequerida="+armadura.getFuerzaReq()+
                    ",descripcion="+armadura.getDescripcion()+
                    " WHERE idArmadura="+idArmadura;
            bd.execSQL(query);
            //Cursor editArmadura = bd.execSQL(query);
            bd.close();
            return true;
        }catch(Exception ex){
            throw new Exception("NO SE PUDO EDITAR "+ex);
        }
    }

    public static int getIdArmaByName(Context context, String name) throws Exception {
        try{
            int idArma = -1;
            DBConnection c = new DBConnection(context,"base1",null,1);
            SQLiteDatabase bd = c.getReadableDatabase();
            Cursor findId = bd.rawQuery("SELECT idArma FROM armas WHERE nombre='"+name+"'",null);
            if(findId.moveToFirst()){
                idArma = findId.getInt(0);
            }
            return idArma;
        }catch (Exception ex){
            throw new Exception("NO SE ENCONTRÓ EL ARMA "+ex);
        }
    }

    public static int getIdArmaduraByName(Context context, String name) throws Exception {
        try{
            int idArmadura =-1;
            DBConnection c = new DBConnection(context,"base1",null,1);
            SQLiteDatabase bd = c.getReadableDatabase();
            Cursor findId = bd.rawQuery("SELECT idArmadura FROM armaduras WHERE nombre='"+name+"'",null);
            if(findId.moveToFirst()){
                idArmadura=findId.getInt(0);
            }
            return idArmadura;

        }catch (Exception ex){
            throw new Exception("NO SE ENCONTRÓ LA ARMADURA "+ex);
        }
    }
}
