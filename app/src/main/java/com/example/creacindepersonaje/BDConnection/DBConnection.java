package com.example.creacindepersonaje.BDConnection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBConnection extends SQLiteOpenHelper {

    private final String createArmas = "CREATE TABLE armas ("+
            "idArma INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            "nombre TEXT NOT NULL,"+
            "damage INTEGER NOT NULL,"+
            "tipoArma STRING NOT NULL,"+
            "tipoDamage STRING NOT NULL," +
            "descripcion STRING NOT NULL);";

    private final String createArmaduras = "CREATE TABLE armaduras ("+
            "idArmadura INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            "nombre TEXT NOT NULL,"+
            "claseArmadura INTEGER NOT NULL,"+
            "peso INTEGER NOT NULL,"+
            "fuerzaRequerida INTEGER,"+
            "descripcion STRING NOT NULL);";

    private final String createHechizos = "CREATE TABLE spell("+
            "idHechizo INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            "NOMBRE_HECHIZO TEXT NOT NULL,"+
            "NIVEL_HECHIZO TEXT NOT NULL,"+
            "DAMAGE INTEGER NOT NULL,"+
            "HEAL INTEGER NOT NULL,"+
            "SHIELD INTEGER NOT NULL," +
            "DESCRIPCION TEXT,"+
            "TIPO_HECHIZO TEXT NOT NULL);";

    private final String createPersonaje = "CREATE TABLE personajes ("+
            "idPersonaje INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            "idGuild INTEGER,"+
            "NOMBRE_PERSONAJE TEXT NOT NULL,"+
            "RAZA_PERSONAJE TEXT NOT NULL,"+
            "SUBRAZA_PERSONAJE TEXT,"+
            "CLASE_PERSONAJE TEXT NOT NULL,"+
            "idHechizo INTEGER,"+
            "idArma INTEGER,"+
            "idArmadura INTEGER,"+
            "FUERZA INTEGER NOT NULL,"+
            "DESTREZA INTEGER NOT NULL,"+
            "CONSTITUCION INTEGER NOT NULL,"+
            "INTELIGENCIA INTEGER NOT NULL,"+
            "SABIDURIA INTEGER NOT NULL,"+
            "CARISMA INTEGER NOT NULL,"+
            "MOD_FUE INTEGER NOT NULL,"+
            "MOD_DES INTEGER NOT NULL,"+
            "MOD_CON INTEGER NOT NULL,"+
            "MOD_INT INTEGER NOT NULL,"+
            "MOD_SAB INTEGER NOT NULL,"+
            "MOD_CAR INTEGER NOT NULL,"+
            "VEL INTEGER NOT NULL,"+
            "CA INTEGER NOT NULL,"+
            "PG INTEGER NOT NULL,"+
            "NIVEL INTEGER NOT NULL,"+
            "EXP INTEGER NOT NULL,"+
            "ESPACIO_CONJUROS INTEGER,"+
            "APTITUD_MAGICA INTEGER NOT NULL,"+
            "INSPIRACION INTEGER,"+
            "BONO_COMPETENCIA INTEGER NOT NULL,"+
            "INICIATIVA INTEGER);";

    private final String createGuild = "CREATE TABLE guild("+
            "idGuild Integer PRIMARY KEY AUTOINCREMENT NOT NULL, "+
            "nombre_Guild TEXT);";

    private final String createCampaign = "CREATE TABLE campaign("+
            "idCampaign INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            "idGuild INTEGER,"+
            "descriptionCampaign TEXT,"+
            "nombreCampaign TEXT NOT NULL);";

    private final String createMonstruos = "CREATE TABLE monstruos (" +
            "idMonstruo INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "nameMonstruo TEXT NOT NULL," +
            "idArma INTEGER NOT NULL," +
            "Actions TEXT NOT NULL," +
            "CA INTEGER NOT NULL," +
            "HP INTEGER NOT NULL," +
            "VEL INTEGER NOT NULL," +
            "FUE INTEGER NOT NULL," +
            "DES INTEGER NOT NULL," +
            "CON INTEGER NOT NULL," +
            "INT INTEGER NOT NULL," +
            "SAB INTEGER NOT NULL," +
            "CAR INTEGER NOT NULL," +
            "EXPFinal INTEGER NOT NULL," +
            "descripcion STRING NOT NULL);";

    private final String createBattle = "CREATE TABLE batallas ("+
            "idBattle INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            "nameBattle TEXT NOT NULL,"+
            "idGuild INTEGER NOT NULL,"+
            "idMonstruo INTEGER NOT NULL,"+
            "history TEXT);";

    private final String createLogBattle ="CREATE TABLE LogBatalla("+
            "idTurno INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            "idBattle INTEGER,"+
            "idGuild INTEGER,"+
            "Descripcion TEXT,"+
            "dadoResult INTEGER,"+
            "idPersonaje INTEGER,"+
            "DamagePersonaje INTEGER,"+
            "idMonstruo INTEGER,"+
            "DamageMonstruo INTEGER);";

    public DBConnection(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createArmaduras);
        db.execSQL(createArmas);
        db.execSQL(createPersonaje);
        db.execSQL(createHechizos);
        db.execSQL(createGuild);
        db.execSQL(createCampaign);
        db.execSQL(createMonstruos);
        db.execSQL(createBattle);
        db.execSQL(createLogBattle);
        poblarItems(db);
    }

    private void poblarItems(SQLiteDatabase db){
        //Inserción de hechizos básicos
        db.execSQL("INSERT INTO Spell(nombre_hechizo,nivel_hechizo,damage,heal,shield,descripcion,tipo_hechizo) " +
                "VALUES('Descarga de fuego',1,10,0,0,'ataque a distancia, lanzas una llama que quema lo que toca.','Evocacion')");
        db.execSQL("INSERT INTO Spell(nombre_hechizo,nivel_hechizo,damage,heal,shield,descripcion,tipo_hechizo) VALUES('Armadura de mago',1,0,0,13,'Armadura mágica que protege a quien la invoca','Abjuracion')");
        db.execSQL("INSERT INTO Spell(nombre_hechizo,nivel_hechizo,damage,heal,shield,descripcion,tipo_hechizo) VALUES('Curar Heridas',1,0,8,0,'Manos sanadoras. No puedes sanar a muertos vivientes o autómatas','Evocacion')");
        db.execSQL("INSERT INTO Spell(nombre_hechizo,nivel_hechizo,damage,heal,shield,descripcion,tipo_hechizo) VALUES('Rociada Venenosa',1,12,0,0,'Esparces gas venenoso que nace de tus manos','Conjuracion')");

        //Inserción de las armas básicas.
        db.execSQL("INSERT INTO armas (nombre,damage,tipoArma,tipoDamage,descripcion) VALUES ('Daga',4,'Ligera','Perforante','Pequeña daga ligera que también puede ser arrojada');");
        db.execSQL("INSERT INTO armas (nombre,damage,tipoArma,tipoDamage,descripcion) VALUES ('Lanza',6,'A Distancia','Perforante','Lanza que puede ser utilizada a una mano, dos manos o ser arrojada');");
        db.execSQL("INSERT INTO armas (nombre,damage,tipoArma,tipoDamage,descripcion) VALUES ('Arco Corto',6,'A Distancia','Perforante','Arco de poca potencia que requiere un uso a dos manos');");
        db.execSQL("INSERT INTO armas (nombre,damage,tipoArma,tipoDamage,descripcion) VALUES ('Ballesta',8,'A Distancia','Perforante','Ballesta que puede ser utilizada con una mano');");
        db.execSQL("INSERT INTO armas (nombre,damage,tipoArma,tipoDamage,descripcion) VALUES ('Espada Corta',6,'Una mano','Perforante','Espada ligera que puede ser utilizada por una mano o ambas manos');");
        db.execSQL("INSERT INTO armas (nombre,damage,tipoArma,tipoDamage,descripcion) VALUES ('Espada Larga',8,'Versatil','Cortante','Espada larga que puede ser utilizada con una mano o ambas manos para mayor cantidad de daño');");
        db.execSQL("INSERT INTO armas (nombre,damage,tipoArma,tipoDamage,descripcion) VALUES ('Hacha de mano',6,'Ligera','Cortante','Hacha simple que puede ser utilizada con una o dos manos');");
        db.execSQL("INSERT INTO armas (nombre,damage,tipoArma,tipoDamage,descripcion) VALUES ('Hacha Guerrera',8,'Pesada','Cortante','Hacha pesada que requiere ser utilizada con dos manos');");
        db.execSQL("INSERT INTO armas (nombre,damage,tipoArma,tipoDamage,descripcion) VALUES ('Martillo de Guerra',8,'Pesada','Contundente','Martillo pesado que requiere ser utilizado con dos manos');");
        db.execSQL("INSERT INTO armas (nombre,damage,tipoArma,tipoDamage,descripcion) VALUES ('Arco Largo',8,'A dos manos','Perforante','Arco de gran potencia que requiere ser utilizado con ambas manos');");

        //Inserción de las armaduras.
        db.execSQL("INSERT INTO armaduras(nombre,claseArmadura,peso,fuerzaRequerida,descripcion) VALUES('Tela',11,8,0,'Armadura de tela ligera');");
        db.execSQL("INSERT INTO armaduras(nombre,claseArmadura,peso,fuerzaRequerida,descripcion) VALUES('Cuero',11,10,0,'Armadura de cuero ligera');");
        db.execSQL("INSERT INTO armaduras(nombre,claseArmadura,peso,fuerzaRequerida,descripcion) VALUES('Cuero tachonado',12,13,0,'Armadura de cuero tachonado. Es ruidosa');");
        db.execSQL("INSERT INTO armaduras(nombre,claseArmadura,peso,fuerzaRequerida,descripcion) VALUES('Camisa de malla',13,13,0,'Armadura de malla metálica');");
        db.execSQL("INSERT INTO armaduras(nombre,claseArmadura,peso,fuerzaRequerida,descripcion) VALUES('Cota de escamas',14,45,0,'Armadura de escamas metálicas. Es ruidosa');");
        db.execSQL("INSERT INTO armaduras(nombre,claseArmadura,peso,fuerzaRequerida,descripcion) VALUES('Coraza',14,20,0,'Coraza de metal. Es ruidosa');");
        db.execSQL("INSERT INTO armaduras(nombre,claseArmadura,peso,fuerzaRequerida,descripcion) VALUES('Armadura de Placas',18,60,15,'Armadura de metal. Dificulta el movimiento');");
        db.execSQL("INSERT INTO armaduras(nombre,claseArmadura,peso,fuerzaRequerida,descripcion) VALUES('Cota de malla',16,55,13,'Armadura de malla pesada');");
        db.execSQL("INSERT INTO armaduras(nombre,claseArmadura,peso,fuerzaRequerida,descripcion) VALUES('Media Armadura',15,40,0,'Armadura semiligera sigilosa');");
        db.execSQL("INSERT INTO armaduras(nombre,claseArmadura,peso,fuerzaRequerida,descripcion) VALUES('Pieles',12,12,0,'Cobertura de pieles para proteger del daño');");

        //Insercion Guild de Prueba
        db.execSQL("INSERT INTO guild(nombre_Guild) VALUES('Virgin Slayers');");
        db.execSQL("INSERT INTO guild(nombre_Guild) VALUES('Dioses Del Codice');");
        db.execSQL("INSERT INTO guild(nombre_Guild) VALUES('La Comunidad del Anillo');");

        //Insercion batallas de Ejemplo
        db.execSQL("INSERT INTO batallas(nameBattle,idGuild,idMonstruo,history) VALUES('Genesys',-1,1,'La noche se avecina');");
        db.execSQL("INSERT INTO batallas(nameBattle,idGuild,idMonstruo,history) VALUES('Apocalypse',-1,2,'El alba se avecina');");

        //insercion Campaña de prueba
        db.execSQL("INSERT INTO campaign(idCampaign, nombreCampaign) VALUES('1','La Mazmorra Vampirica');");

        //Insercion de monstruos
        db.execSQL("INSERT INTO monstruos(nameMonstruo,idArma,Actions,CA,HP,VEL,FUE,DES,CON,INT,SAB,CAR,EXPFinal,descripcion) VALUES('Diablillo',1,'Atacar',13,15,20,4,5,6,7,8,9,100, 'Un monstruo diablillo'  );");
        db.execSQL("INSERT INTO monstruos(nameMonstruo,idArma,Actions,CA,HP,VEL,FUE,DES,CON,INT,SAB,CAR,EXPFinal,descripcion) VALUES('Rata Gigante',2,'Atacar',10,15,20,4,5,6,7,8,9,100, 'Un monstruo raton'  );");
        db.execSQL("INSERT INTO monstruos(nameMonstruo,idArma,Actions,CA,HP,VEL,FUE,DES,CON,INT,SAB,CAR,EXPFinal,descripcion) VALUES('Orco inmundo',2,'Atacar',19,15,20,4,5,6,7,8,9,100, 'Un monstruo orco'  );");
        db.execSQL("INSERT INTO monstruos(nameMonstruo,idArma,Actions,CA,HP,VEL,FUE,DES,CON,INT,SAB,CAR,EXPFinal,descripcion) VALUES('Troll de fuego',4,'Atacar',17,15,20,4,5,6,7,8,9,100, 'Un monstruo troll'  );");


    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
