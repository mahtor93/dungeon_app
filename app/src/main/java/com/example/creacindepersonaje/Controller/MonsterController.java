package com.example.creacindepersonaje.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.creacindepersonaje.BDConnection.DBConnection;
import com.example.creacindepersonaje.Modelos.Monstruo;

import java.util.ArrayList;

public class MonsterController {
    public static boolean RemoveMonster(Context context, int ID) {
        DBConnection con = new DBConnection(context, "base1", null, 1);
        SQLiteDatabase db = con.getWritableDatabase();
        String query = "DELETE FROM monstruos WHERE idMonstruo=" + ID;
        db.execSQL(query);
        db.close();
        return true;
    }

    public static boolean editMonster(Context context, Monstruo m, String txtN, String txtD) throws Exception {
        try {
            DBConnection c = new DBConnection(context, "base1", null, 1);
            SQLiteDatabase bd = c.getWritableDatabase();

            bd.execSQL("UPDATE monstruos SET nameMonstruo = '" + txtN + "' , descripcion = '" + txtD + "' WHERE idMonstruo = " + m.getIdMonstruo());
            bd.close();
            return true;
        } catch (Exception ex) {
            throw new Exception("Error al modificar" + ex);
        }

    }

    public static Monstruo findMonster(Context context, int ID) throws Exception {
        try {
            DBConnection con = new DBConnection(context, "base1", null, 1);
            SQLiteDatabase db = con.getReadableDatabase();
            Monstruo m = new Monstruo();

            Cursor mnstr = db.rawQuery("SELECT idMonstruo, nameMonstruo, idArma, Actions, CA, HP, " +
                    "VEL, FUE, DES, CON, INT, SAB, CAR, " +
                    "EXPFinal, descripcion FROM monstruos WHERE idMonstruo=" + ID, null);
            if (mnstr.moveToFirst()) {
                do {
                    m.setIdMonstruo(mnstr.getInt(0));
                    m.setNameMonstruo(mnstr.getString(1));
                    m.setIdArma(mnstr.getInt(2));
                    m.setAction(mnstr.getString(3));
                    m.setCA(mnstr.getInt(4));
                    m.setHP(mnstr.getInt(5));
                    m.setVEL(mnstr.getInt(6));
                    m.setFUE(mnstr.getInt(7));
                    m.setDES(mnstr.getInt(8));
                    m.setCON(mnstr.getInt(9));
                    m.setINT(mnstr.getInt(10));
                    m.setSAB(mnstr.getInt(11));
                    m.setCAR(mnstr.getInt(12));
                    m.setExpFinal(mnstr.getInt(13));
                    m.setDescripcion(mnstr.getString(14));
                } while (mnstr.moveToNext());
            }
            con.close();
            mnstr.close();
            return m;
        } catch (Exception ex) {
            throw new Exception("Error buscando monstruo " + ex);
        }

    }

    public static int getIdPorNombre(Context context,String name) throws Exception {
        try{
            int idFinal = -1;
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getReadableDatabase();
            Cursor findId = db.rawQuery("SELECT idMonstruo FROM monstruos WHERE nameMonstruo='"+name+"'",null);
            if(findId.moveToFirst()){
                idFinal = findId.getInt(0);
            }
            return idFinal;
        }catch (Exception ex){
            throw new Exception("Error al encontrar al personaje "+ex);
        }
    }

    public static long createMonster(Context context, Monstruo monstruo) throws Exception {
        long id = 0;
        try {
            DBConnection con = new DBConnection(context, "base1", null, 1);
            SQLiteDatabase db = con.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("nameMonstruo", monstruo.getNameMonstruo());
            cv.put("idArma", monstruo.getIdArma());
            cv.put("Actions", monstruo.getAction());
            cv.put("CA", monstruo.getCA());
            cv.put("HP", monstruo.getHP());
            cv.put("VEL", monstruo.getVEL());
            cv.put("FUE", monstruo.getFUE());
            cv.put("DES", monstruo.getDES());
            cv.put("CON", monstruo.getCON());
            cv.put("INT", monstruo.getINT());
            cv.put("SAB", monstruo.getSAB());
            cv.put("CAR", monstruo.getCAR());
            cv.put("EXPFinal", monstruo.getExpFinal());
            cv.put("descripcion", monstruo.getDescripcion());
            id = db.insert("monstruos", null, cv);
            return id;
        } catch (Exception ex) {
            Toast.makeText(context, "Error " + ex, Toast.LENGTH_SHORT).show();
            return id;
        }
    }

    public static ArrayList<Monstruo> listMonsters(Context context) throws Exception {
        try {
            ArrayList<Monstruo> msList = new ArrayList<>();
            DBConnection con = new DBConnection(context, "base1", null, 1);
            SQLiteDatabase db = con.getReadableDatabase();
            Cursor mst = db.rawQuery("SELECT idMonstruo,nameMonstruo,descripcion,CA  FROM monstruos", null);
            String textoMons;
            if (mst.moveToFirst()) {
                do {
                    Monstruo m = new Monstruo();
                    m.setIdMonstruo(mst.getInt(0));
                    m.setNameMonstruo(mst.getString(1));
                    m.setDescripcion(mst.getString(2));
                    m.setCA(mst.getInt(3));
                    msList.add(m);
                } while (mst.moveToNext());
            }
            db.close();
            return msList;
        } catch (Exception ex) {
            throw new Exception("Error al listar" + ex);
        }
    }
}
