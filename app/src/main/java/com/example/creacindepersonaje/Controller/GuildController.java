package com.example.creacindepersonaje.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.creacindepersonaje.BDConnection.DBConnection;
import com.example.creacindepersonaje.Modelos.Personaje;
import com.example.creacindepersonaje.Modelos.modelGuild;

import java.util.ArrayList;

public class GuildController {


    public static ArrayList<Personaje> listarIntegrantes(Context context, int idGuild) throws Exception {
        ArrayList<Personaje> integrantes = new ArrayList<>();
        //int idGuild = guild.getIdGuild(); //Seleccionamos el ID de la guild que vamos a listar
        try{
            //Listar los integrantes cuyo id coincida con el id de la guild.
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getReadableDatabase();
            Cursor personajes = db.rawQuery("SELECT idPersonaje, NOMBRE_PERSONAJE, " +
                    "RAZA_PERSONAJE,SUBRAZA_PERSONAJE,CLASE_PERSONAJE, NIVEL FROM personajes where idGuild ="+idGuild, null);
            if(personajes.moveToFirst()){
                do{
                    Personaje p = new Personaje(); //Instanciamos los personajes para añadirlos a la lista.
                    p.setIdPersonaje(personajes.getInt(0));
                    p.setNombre(personajes.getString(1));
                    p.setRaza(personajes.getString(2));
                    p.setSub_Raza(personajes.getString(3));
                    p.setClase(personajes.getString(4));
                    p.setLevel(personajes.getInt(5));

                    integrantes.add(p);
                }while(personajes.moveToNext());
            }
            //Si todo sale bien, la función añade los elementos necesarios para identificar a un personaje en una list. Luego la list view puede hacer consultas en la tabla de personajes usando la id del personaje.
            return integrantes; //retorna una lista con los personajes de la guild
        }catch(Exception ex){
            throw new Exception("Error al listar los integrantes de la guild "+ex);
        }
    }

    public static ArrayList<modelGuild> listarGuilds(Context context) throws Exception {
        ArrayList<modelGuild> guilds = new ArrayList<>();
        try{
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getReadableDatabase();
            Cursor grupos = db.rawQuery("SELECT idGuild, nombre_guild FROM guild",null);
            if(grupos.moveToFirst()){
                do{
                    modelGuild mg = new modelGuild();
                    mg.setIdGuild(grupos.getInt(0));
                    mg.setGuildName(grupos.getString(1));
                    guilds.add(mg);
                }while(grupos.moveToNext());
            }
            return guilds;
        }catch (Exception ex){
            throw new Exception("Error al listar las guilds "+ ex);
        }
    }

    public static boolean createGuild (Context context, modelGuild guild) throws Exception {
        long id;
        ArrayList<Personaje> personajes = guild.getIntegrantes();
        try{
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("nombre_Guild",guild.getGuildName());//escribimos el nombre en la base de datos, la id se obtiene al finalizar el proceso.
            Toast.makeText(context, "guild creada :"+guild.getGuildName(), Toast.LENGTH_SHORT).show();
            id = db.insert("guild",null,cv); //nos retorna la id de la guild recién creada.
            con.close();
            db.close();

            DBConnection c = new DBConnection(context,"base1",null,1);
            SQLiteDatabase bd = c.getWritableDatabase();

            if(personajes.size()>0) { //Si la guild tiene al menos un personaje, se lee la lista de personajes
                for (int i = 0; i < personajes.size(); i++) {
                    int idPj;
                    idPj = personajes.get(i).getIdPersonaje(); //Se obtiene el id del personaje i
                    String query = "UPDATE personajes SET idGuild ="+id+" WHERE idPersonaje="+idPj; //Insetamos el id de la guild en el registro del personaje
                    bd.execSQL(query);
                   }
            }
            return true;
        }catch(Exception ex){
            throw new Exception("Error al crear la guild "+ex);
        }
    }

    public static boolean removeGuild(Context context, modelGuild guild) throws Exception {
        try{
            ArrayList<Personaje> personajes;
            personajes = guild.getIntegrantes();
            int idPj;
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getWritableDatabase();
            int idGuild = guild.getIdGuild();
            for(int i = 0; i<guild.getIntegrantes().size();i++){
                idPj = personajes.get(i).getIdPersonaje();
                String removeId = "UPDATE personajes SET idGuild = -1 WHERE idPersonaje="+idPj; //Quita la idGuild del personaje
                db.execSQL(removeId);
            }
            String query="DELETE FROM guild WHERE idGuild ="+idGuild; //Se quita la guild del registro
            db.execSQL(query);
            db.close();
            return true;
        }catch (Exception ex){
            throw new Exception("Error al eliminar la guild");
        }
    }

    public static boolean renameGuild(Context context, int idGuild, String name) throws Exception {
        try{
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getWritableDatabase();
            db.execSQL("UPDATE guild SET nombre_Guild ='"+name+"' WHERE idGuild="+idGuild);
            db.close();
            return true;
        }catch (Exception ex){
            throw new Exception("Error al renombrar la guild");
        }
    }

    public static boolean removePersonaje(Context context, int idPj) throws Exception {
        try {
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getWritableDatabase();
            String query = "UPDATE personajes SET idGuild = -1 WHERE idPersonaje="+idPj;
            db.execSQL(query);
            return true;
        }catch (Exception ex){
            throw new Exception("No se pudo quitar el personaje");
        }

    }

    public static boolean addPersonaje(Context context, int idPersonaje, int idGuild) throws Exception {
        try{
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getWritableDatabase();
            String query = "UPDATE personajes SET idGuild="+idGuild+" WHERE idPersonaje="+idPersonaje;
            db.execSQL(query);
            return true;
        }catch (Exception ex){
            throw new Exception("No se pudo añadir al nuevo integrante "+ex);
        }
    }


    public static int findGuildByName(Context context, String name) throws Exception {
        try{
        int idGuild=-1;
        DBConnection con = new DBConnection(context,"base1",null,1);
        SQLiteDatabase db = con.getReadableDatabase();

        Cursor query = db.rawQuery("SELECT idGuild FROM Guild WHERE nombre_Guild='"+name+"'",null);
        if(query.moveToFirst()){
            idGuild = query.getInt(0);
        }

        return idGuild;
        }catch (Exception ex){
            throw new Exception("Error al encontrar "+ex);
        }
    }
    public static String findGuildById(Context context, int ID) throws Exception {
        try{
            String nameGuild ="";
            DBConnection con = new DBConnection(context,"base1",null,1);
            SQLiteDatabase db = con.getReadableDatabase();

            Cursor query = db.rawQuery("SELECT nombre_Guild FROM Guild WHERE idGuild="+ID,null);
            if(query.moveToFirst()){
                nameGuild = query.getString(0);
            }

            return nameGuild;
        }catch (Exception ex){
            throw new Exception("Error al encontrar "+ex);
        }
    }

}



