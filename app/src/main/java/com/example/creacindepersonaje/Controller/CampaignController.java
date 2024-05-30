package com.example.creacindepersonaje.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.creacindepersonaje.BDConnection.DBConnection;

import com.example.creacindepersonaje.Modelos.Campaing;
import com.example.creacindepersonaje.Modelos.Personaje;
import com.example.creacindepersonaje.Modelos.modelGuild;

import java.util.ArrayList;

public class CampaignController {

    public static ArrayList<Campaing> listarCampañas(Context context) throws Exception {
        ArrayList<Campaing> campaigns = new ArrayList<>();
        try {
            DBConnection con = new DBConnection(context, "base1", null, 1);
            SQLiteDatabase db = con.getReadableDatabase();
            Cursor cu = db.rawQuery("SELECT  idCampaign, nombreCampaign FROM campaign", null);
            if (cu.moveToFirst()) {
                do {
                    Campaing item = new Campaing();
                    item.setIdCampaign(cu.getInt(0));
                    item.setNombreCampaing(cu.getString(1));

                    campaigns.add(item);
                } while (cu.moveToNext());
            }
            return campaigns;
        } catch (Exception ex) {
            throw new Exception("Error al listar las Campañas " + ex);
        }
    }

    public static boolean createCampaign(Context context, Campaing campana) throws Exception {
        long id;

        try {
            DBConnection con = new DBConnection(context, "base1", null, 1);
            SQLiteDatabase db = con.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("nombreCampaign", campana.getNombreCampaing());//escribimos el nombre en la base de datos, la id se obtiene al finalizar el proceso.
            cv.put("descriptionCampaign",campana.getRelatoCampaign());
            Toast.makeText(context, "campaña creada :" + campana.getNombreCampaing(), Toast.LENGTH_SHORT).show();
            id = db.insert("campaign", null, cv);
            con.close();
            db.close();
            return true;
        } catch (Exception ex) {
            throw new Exception("Error al crear la guild " + ex);
        }
    }

    public static boolean removeCampaign(Context context, int ID) throws Exception {


        DBConnection con = new DBConnection(context, "base1", null, 1);
        SQLiteDatabase db = con.getWritableDatabase();
        String query = "DELETE FROM campaign WHERE idCampaign =" + ID; //Se quita la campaña del registro
        db.execSQL(query);
        db.close();
        return true;

    }

    public static boolean renameCampaign(Context context, Campaing c) throws Exception {
        try {
            int idCampaign = c.getIdCampaign();
            DBConnection con = new DBConnection(context, "base1", null, 1);
            SQLiteDatabase db = con.getWritableDatabase();
            if(c.getNombreCampaing().toString().length()!=0) {
                db.execSQL("UPDATE campaign SET nombreCampaign ='" + c.getNombreCampaing() + "' WHERE idCampaign=" + idCampaign);
            }
            if(c.getRelatoCampaign().length()!=0) {
                db.execSQL("UPDATE campaign SET descriptionCampaign='" + c.getRelatoCampaign() + "' WHERE idCampaign=" + idCampaign);
            }
            if(c.getIdGuild()!=0){
                db.execSQL("UPDATE campaign SET idGuild="+c.getIdGuild()+" WHERE idCampaign="+idCampaign);
            }
            else {
                db.close();
            }
            return true;
        } catch (Exception ex) {
            throw new Exception("Error al editar la campaña "+ex);
        }
    }


    public static int findCampByName(Context context, String name) throws Exception {
        try {
            int idCampaign = -1;
            DBConnection con = new DBConnection(context, "base1", null, 1);
            SQLiteDatabase db = con.getReadableDatabase();

            Cursor query = db.rawQuery("SELECT idCampaign FROM campaign WHERE nombreCampaign='" + name + "'", null);
            if (query.moveToFirst()) {
                idCampaign = query.getInt(0);
            }

            return idCampaign;
        } catch (Exception ex) {
            throw new Exception("Error al encontrar " + ex);
        }
    }

    public static Campaing findCampaignById(Context context, int ID) throws Exception {
        try {
            DBConnection con = new DBConnection(context, "base1", null, 1);
            SQLiteDatabase db = con.getReadableDatabase();
            Campaing c = new Campaing();

            Cursor query = db.rawQuery("SELECT idCampaign, nombreCampaign, descriptionCampaign FROM campaign WHERE idCampaign=" + ID, null);
            if (query.moveToFirst()) {
                do {
                    c.setIdCampaign(query.getInt(0));
                    c.setNombreCampaing(query.getString(1));
                    c.setRelatoCampaign(query.getString(2));
                } while (query.moveToNext());
            }
            con.close();
            query.close();
            return c;

        } catch (Exception ex) {
            throw new Exception("Error al encontrar " + ex);
        }
    }

}
