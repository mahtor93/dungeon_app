package com.example.creacindepersonaje.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.transition.Scene;
import android.widget.Toast;

import com.example.creacindepersonaje.BDConnection.DBConnection;
import com.example.creacindepersonaje.Modelos.Campaing;
import com.example.creacindepersonaje.Modelos.Escenas;

import java.util.ArrayList;

public class SceneController {

    public static ArrayList<Escenas> listarEscenas(Context context) throws Exception {
        ArrayList<Escenas> escenas = new ArrayList<>();
        try{
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getReadableDatabase();
            Cursor cu = db.rawQuery("SELECT  idEscena, nombreEscena, campaignAsignada FROM escenas", null);
            if(cu.moveToFirst()){
                do{
                    Escenas item = new Escenas();
                    item.setIdEscena(cu.getInt(0));
                    item.setNombreEscena(cu.getString(1));


                    escenas.add(item);
                }while(cu.moveToNext());
            }
            return escenas;
        }catch (Exception ex){
            throw new Exception("Error al listar las Campañas "+ ex);
        }
    }

    public static boolean createScene (Context context, Escenas escenas) throws Exception {
        long id;

        try{
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("nombreEscena",escenas.getNombreEscena());//escribimos el nombre en la base de datos, la id se obtiene al finalizar el proceso.
            Toast.makeText(context, "escena creada :"+escenas.getNombreEscena(), Toast.LENGTH_SHORT).show();
            id = db.insert("escenas",null,cv);
            con.close();
            db.close();


            return true;
        }catch(Exception ex){
            throw new Exception("Error al crear escena "+ex);
        }
    }

    public static boolean removeScene(Context context, Escenas escenas) throws Exception {
        try{

            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getWritableDatabase();
            int idEscena = escenas.getIdEscena();
            String query="DELETE FROM escenas WHERE idEscena ="+idEscena;
            db.execSQL(query);
            db.close();
            return true;
        }catch (Exception ex){
            throw new Exception("Error al eliminar escena");
        }
    }

    public static int findSceneByName(Context context, String name) throws Exception {
        try{
            int idEscena=-1;
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getReadableDatabase();

            Cursor query = db.rawQuery("SELECT idEscena FROM escenas WHERE nombreEscena='"+name+"'",null);
            if(query.moveToFirst()){
                idEscena = query.getInt(0);
            }

            return idEscena;
        }catch (Exception ex){
            throw new Exception("Error al encontrar "+ex);
        }
    }


    public static String findSceneById(Context context, int ID) throws Exception {
        try{
            String nameScene ="";
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getReadableDatabase();

            Cursor query = db.rawQuery("SELECT nombreEscena FROM escenas WHERE idEscena="+ID,null);
            if(query.moveToFirst()){
                nameScene = query.getString(0);
            }

            return nameScene;
        }catch (Exception ex){
            throw new Exception("Error al encontrar "+ex);
        }
    }

}
