package com.example.creacindepersonaje.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.creacindepersonaje.BDConnection.DBConnection;
import com.example.creacindepersonaje.Modelos.Battle;
import com.example.creacindepersonaje.Modelos.LogBattle;

import java.util.ArrayList;

public class BattleController {
    //Listar todas las batallas
    public static ArrayList<Battle> listarBatallas(Context context) throws Exception {
        ArrayList<Battle> getBattles = new ArrayList<>();
        try{
            DBConnection connection = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = connection.getReadableDatabase();
            Cursor batallas = db.rawQuery("SELECT idBattle,nameBattle,idGuild,idMonstruo,history FROM batallas",null);
            if(batallas.moveToFirst()){
                do{
                    Battle bat = new Battle();
                    bat.setIdBattle(batallas.getInt(0));
                    bat.setNameBattle(batallas.getString(1));
                    bat.setIdGuild(batallas.getInt(2));
                    bat.setIdMonstruo(batallas.getInt(3));
                    bat.setTurno(batallas.getString(4));
                    getBattles.add(bat);
                }while(batallas.moveToNext());
            }
            db.close();
            return getBattles;

        }catch(Exception ex){
            throw new Exception("Error al obtener Batallas "+ex);
        }
    }

    //obtener una batalla por id
    public static Battle getBattleById(Context context, int idBattle) throws Exception {
        try{
            Battle bat = new Battle();
            DBConnection connection = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = connection.getReadableDatabase();
            Cursor batallas = db.rawQuery("SELECT idBattle,nameBattle,idGuild,idMonstruo,history FROM batallas WHERE idBattle="+idBattle,null);
            if(batallas.moveToFirst()){
                    bat.setIdBattle(batallas.getInt(0));
                    bat.setNameBattle(batallas.getString(1));
                    bat.setIdGuild(batallas.getInt(2));
                    bat.setIdMonstruo(batallas.getInt(3));
                    bat.setTurno(batallas.getString(4));
            }
            db.close();
            return bat;

        }catch (Exception ex){
            throw new Exception("ERROR AL OBTENER LA BATALLA "+ ex);
        }
    }
    //obtener una batalla por nombre
    public static Battle getBattleByName(Context context, String nameBattle) throws Exception {
        try{
            Battle bat = new Battle();
            DBConnection connection = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = connection.getReadableDatabase();
            Cursor batallas = db.rawQuery("SELECT idBattle,nameBattle,idGuild,idMonstruo,history FROM batallas WHERE nameBattle='"+nameBattle+"'",null);
            if(batallas.moveToFirst()){
                bat.setIdBattle(batallas.getInt(0));
                bat.setNameBattle(batallas.getString(1));
                bat.setIdGuild(batallas.getInt(2));
                bat.setIdMonstruo(batallas.getInt(3));
                bat.setTurno(batallas.getString(4));
            }
            db.close();
            return bat;

        }catch (Exception ex){
            throw new Exception("ERROR AL OBTENER LA BATALLA "+ ex);
        }
    }

    //obtener id por nombre
    public static int getIdByName(Context context, String nameBattle) throws Exception {
        try{
            int idBattle = -1;
            DBConnection connection = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = connection.getReadableDatabase();
            Cursor batallas = db.rawQuery("SELECT idBattle FROM batallas WHERE nameBattle='"+nameBattle+"'",null);
            if(batallas.moveToFirst()){
                idBattle = batallas.getInt(0);
            }
            db.close();
            return idBattle;
        }catch (Exception ex){
            throw new Exception("ERROR AL OBTENER ID DE BATALLA "+ ex);
        }
    }

    public static boolean createBattle(Context context, Battle battle) throws Exception {
        long id;
        try{
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("nameBattle",battle.getNameBattle());
            cv.put("idGuild",battle.getIdGuild());
            cv.put("idMonstruo",battle.getIdMonstruo());
            cv.put("history",battle.getDescripcion());
            id = db.insert("batallas",null,cv);
            if(id>0){
                Toast.makeText(context, "Guardado con exito", Toast.LENGTH_SHORT).show();
                db.close();
                return true;
            }else{
                Toast.makeText(context, "No se guardó", Toast.LENGTH_SHORT).show();
                return false;
            }

        }catch (Exception ex){
            throw new Exception("ERROR AL CREAR BATALLA "+ex);
        }
    }

    public static boolean editBattle(Context context, Battle battle) throws Exception {
        try{
            DBConnection conexion = new DBConnection(context,"base1",null,1);
            SQLiteDatabase daba = conexion.getWritableDatabase();
            daba.execSQL("UPDATE batallas SET nameBattle='"+battle.getNameBattle()+"', idMonstruo="+battle.getIdMonstruo()+", history='"+battle.getDescripcion()+"' WHERE idBattle="+battle.getIdBattle());
            daba.close();
            return true;
        }catch (Exception ex){
            throw new Exception("ERROR AL EDITAR LA BATALLA "+ex);
        }
    }

    public static boolean removeBattle(Context context, int idBattle) throws Exception {
        try{
            DBConnection con = new DBConnection(context, "base1",null,1);
            SQLiteDatabase db = con.getWritableDatabase();
            String query = "DELETE FROM batallas WHERE idBattle="+idBattle;
            db.execSQL(query);
            db.close();
            return true;
        }catch (Exception ex){
            throw new Exception("ERROR AL ELIMINAR "+ex);
        }
    }

    public static boolean setGuild(Context context, int idBattle,int idGuild) throws Exception {
        try{
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getWritableDatabase();
            String query="UPDATE batallas SET idGuild="+idGuild+" WHERE idBattle="+idBattle;
            db.execSQL(query);
            db.close();
            return true;
        }catch (Exception ex){
            throw new Exception("Error al designar equipo "+ex);
        }
    }

    public static boolean writeLog(Context context, LogBattle log) throws Exception {
        try{
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("idBattle",log.getIdBattle());
            cv.put("idGuild",log.getIdGuild());
            cv.put("dadoResult",log.getDadoResult());
            cv.put("Descripcion",log.getDescripcion());
            cv.put("idPersonaje",log.getIdPersonaje());
            cv.put("DamagePersonaje",log.getDamagePersonaje());
            cv.put("idMonstruo",log.getIdMonstruo());
            cv.put("DamageMonstruo",log.getDamageMonstruo());
            long id = db.insert("LogBatalla",null,cv);
            if(id>0){
                Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                db.close();
                return true;
            }else{
                Toast.makeText(context, "No se registró el turno", Toast.LENGTH_SHORT).show();
                return false;
            }
        }catch (Exception ex){
            throw new Exception("ERROR AL INGRESAR REGISTRO "+ex);
        }
    }

    public static ArrayList<LogBattle> listLog(Context context, int idBattle) throws Exception {
        try{
            ArrayList<LogBattle> historial = new ArrayList<>();

            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT Descripcion FROM logBatalla WHERE idBattle="+idBattle,null);
            if(cursor.moveToFirst()){
                do {
                    LogBattle log = new LogBattle();
                    log.setDescripcion(cursor.getString(0));
                    historial.add(log);
                }while(cursor.moveToNext());
            }
            db.close();
            return historial;
        }catch (Exception ex){
            throw new Exception("ERROR AL LISTAR REGISTROS "+ex);
        }
    }

    public static boolean removeLog(Context context,int idBattle) throws Exception {
        try{
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getWritableDatabase();
            String query = "DELETE FROM logBatalla WHERE idBattle ="+idBattle;
            db.execSQL(query);
            db.close();
            return true;
        }catch (Exception ex){
            throw new Exception("ERROR AL BORRAR "+ex);
        }
    }

    public static boolean removeGuild(Context context, int idBattle) throws Exception {
        try{
            int idGuild = -1;
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getWritableDatabase();
            String query="UPDATE batallas SET idGuild="+idGuild+" WHERE idBattle="+idBattle;
            db.execSQL(query);
            db.close();
            return true;
        }catch (Exception ex){
            throw new Exception("Error al eliminar equipo "+ex);
        }
    }




}
