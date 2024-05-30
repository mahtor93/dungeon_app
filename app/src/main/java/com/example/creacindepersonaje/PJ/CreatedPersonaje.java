package com.example.creacindepersonaje.PJ;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.creacindepersonaje.BDConnection.DBConnection;
import com.example.creacindepersonaje.Controller.PersonajeController;
import com.example.creacindepersonaje.Controller.SpellController;
import com.example.creacindepersonaje.Jugador.Jugador;
import com.example.creacindepersonaje.Modelos.Arma;
import com.example.creacindepersonaje.Modelos.Armadura;
import com.example.creacindepersonaje.Modelos.Dado;
import com.example.creacindepersonaje.Modelos.Hechizo;
import com.example.creacindepersonaje.Modelos.Personaje;
import com.example.creacindepersonaje.R;

import java.util.ArrayList;

public class CreatedPersonaje extends AppCompatActivity {

    private TextView tvNombre,tvRaza,tvSubRaza,tvClase,
            tvVidaShow,tvCAShow,tvVELShowCreate,tvSTR,
            tvBonoSTR,tvDES,tvBonoDES,tvCON,tvBonoCON,
            tvCAR,tvBonoCAR,tvINT,tvBonoINT,tvSAB,tvBonoSAB,tvATQShow;

    private Button btnCrearPersonaje,btnCancelCreation;

    private Spinner spArma,spArmadura, spHechizo;

    private ArrayList<Arma> armas;
    private ArrayList<String> nameArmas;
    private ArrayList<Hechizo> spells;
    private ArrayList<String> nameSpells;

    private ArrayList<Armadura> armaduras;
    private ArrayList<String> nameArmaduras;

    private ArrayAdapter<String> adapterArmas,adapterArmaduras, adapterSpells;
    private int CA;

    Personaje p = new Personaje();
    Arma arma = new Arma();
    Armadura armadura = new Armadura();
    int idArma = 0;
    int idArmadura;
    int idHechizo=-1;
    boolean spells_true = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_personaje);
        iniciarComponentes();
        iniciarEventos();
    }

    private void iniciarActividad(Class clase){
        Intent i = new Intent(CreatedPersonaje.this,clase);
        startActivity(i);
    }

    private void iniciarComponentes(){

        btnCancelCreation = findViewById(R.id.btnCancelCreation);
        btnCrearPersonaje = findViewById(R.id.btnCrearPersonaje);

        tvNombre = findViewById(R.id.tvNombre);

        tvRaza = findViewById(R.id.tvRaza);
        tvClase = findViewById(R.id.tvClase);
        tvSubRaza = findViewById(R.id.tvSubRaza);

        tvVidaShow = findViewById(R.id.tvVidaShow);
        tvCAShow = findViewById(R.id.tvCAShow);

        tvATQShow = findViewById(R.id.tvATQShow);
        tvVELShowCreate = findViewById(R.id.tvLVL);

        tvSTR = findViewById(R.id.tvSTR);
        tvBonoSTR = findViewById(R.id.tvBonoSTR);

        tvDES = findViewById(R.id.tvDES);
        tvBonoDES = findViewById(R.id.tvBonoDES);

        tvCON = findViewById(R.id.tvCON);
        tvBonoCON = findViewById(R.id.tvBonoCON);

        tvCAR = findViewById(R.id.tvCAR);
        tvBonoCAR = findViewById(R.id.tvBonoCAR);

        tvINT = findViewById(R.id.tvINT);
        tvBonoINT = findViewById(R.id.tvBonoINT);

        tvSAB = findViewById(R.id.tvSAB);
        tvBonoSAB = findViewById(R.id.tvBonoSAB);

        spArma = findViewById(R.id.spArma);
        spArmadura = findViewById(R.id.spArmadura);
        spHechizo = findViewById(R.id.spHechizo);
    }

    private void iniciarEventos(){

        Intent intent = this.getIntent();
        Bundle extra = intent.getExtras();

        String name = extra.getString("Nombre");
        p.setNombre(name);
        String race = extra.getString("Raza");
        p.setRaza(race);
        String variant = extra.getString("Variante");
        p.setSub_Raza(variant);
        String cla = extra.getString("Clase");
        p.setClase(cla);

        String STR = extra.getString("STR");
        int fuerza = Integer.parseInt(STR);
        p.setFuerza(fuerza);
        p.setModStr();
        String SAB = extra.getString("SAB");
        int sabiduria = Integer.parseInt(SAB);
        p.setSabiduria(sabiduria);
        p.setModSab();
        String DES = extra.getString("DES");
        int destreza = Integer.parseInt(DES);
        p.setDestreza(destreza);
        p.setModDes();
        String CON = extra.getString("CON");
        int constitucion = Integer.parseInt(CON);
        p.setConstitucion(constitucion);
        p.setModCon();
        String CAR = extra.getString("CAR");
        int carisma =Integer.parseInt(CAR);
        p.setCarisma(carisma);
        p.setModCar();
        String INT = extra.getString("INT");
        int inteligencia = Integer.parseInt(INT);

        p.setInteligencia(inteligencia);
        p.setModInt();
        p.setLevel(1);


        int PPG=0;
        Dado dGolpe = new Dado();

        if(p.getClase().equals(Personaje.Clases.Clases[4])||//Bardo
                Personaje.Clases.Clases[3].equals(p.getClase())||//Druida
                Personaje.Clases.Clases[0].equals(p.getClase())){//Picaro
            dGolpe.setValorDado(8);
            for (int i = 0; i <= p.getLevel(); i++) {
                 PPG = dGolpe.lanzarDado()+p.getModCon();
            }
            if(PPG<=0){
                PPG=8+p.getModCon();
            }
            p.setPG(PPG);
        }
        if(Personaje.Clases.Clases[1].equals(p.getClase())){//Guerrero
            dGolpe.setValorDado(10);
            for (int i = 0; i <= p.getLevel(); i++) {
                PPG = dGolpe.lanzarDado()+p.getModCon();
            }
            if(PPG<=0){
                PPG=5+p.getModCon();
            }
            p.setPG(PPG);
        }
        if(Personaje.Clases.Clases[2].equals(p.getClase())){//Hechicero
            dGolpe.setValorDado(6);
            for (int i = 0; i <= p.getLevel(); i++) {
                PPG = dGolpe.lanzarDado()+p.getModCon();
            }
            if(PPG<=0){
                PPG=3+p.getModCon();; //La media del dado
            }
            p.setPG(PPG);
        }
        p.setVel();
        //p.setCA(0);


        tvNombre.setText(name);
        tvRaza.setText(race);
        tvSubRaza.setText(variant);
        tvClase.setText(cla);
        tvVELShowCreate.setText(p.getVel()+"");
        tvVidaShow.setText(p.getPG()+"");
        tvCAShow.setText(p.getCA()+"");

        tvSTR.setText(p.getFuerza()+"");
        tvSAB.setText(p.getSabiduria()+"");
        tvDES.setText(p.getDestreza()+"");
        tvCON.setText(p.getConstitucion()+"");
        tvCAR.setText(p.getCarisma()+"");
        tvINT.setText(p.getInteligencia()+"");

        tvBonoINT.setText((int) p.getModInt()+"");
        tvBonoCAR.setText((int) p.getModCar()+"");
        tvBonoSTR.setText((int) p.getModStr()+"");
        tvBonoCON.setText((int) p.getModCon()+"");
        tvBonoDES.setText((int) p.getModDes()+"");
        tvBonoSAB.setText((int) p.getModSab()+"");

        try {


        DBConnection con = new DBConnection(CreatedPersonaje.this,"base1",null,1);
        SQLiteDatabase db = con.getReadableDatabase();

            armas = new ArrayList<>();
            nameArmas=new ArrayList<>();
            armaduras = new ArrayList<>();
            nameArmaduras = new ArrayList<>();
            nameSpells = new ArrayList<>();

            String nameArma;
            String nameArmadura;

            Cursor hechizos = db.rawQuery("SELECT idHechizo, NOMBRE_HECHIZO,NIVEL_HECHIZO FROM spell",null);

            if(p.getClase().equals(Personaje.Clases.Clases[0])||p.getClase().equals(Personaje.Clases.Clases[1])){
                spHechizo.setEnabled(false);
                p.setAptitudMagica(0);
                p.setEspacioConjuros(0);
                spells_true = false;
                nameSpells.add("No usa hechizos");
            }
            if(p.getClase().equals(Personaje.Clases.Clases[2])||p.getClase().equals(Personaje.Clases.Clases[3])||p.getClase().equals(Personaje.Clases.Clases[4])){
                p.setAptitudMagica(1);
                p.setEspacioConjuros(1);
                if(hechizos.moveToFirst()){
                    do{
                        Hechizo h = new Hechizo();
                        h.setIdHechizo(hechizos.getInt(0));
                        h.setNombre(hechizos.getString(1));
                        h.setNivelHechizo(hechizos.getInt(2));
                        if(h.getNivelHechizo()==1) {
                            nameSpells.add(hechizos.getString(1));
                            spells = SpellController.listarHechizos(getApplicationContext());
                        }
                    }while(hechizos.moveToNext());
                }
            }

            if(p.getClase().equals(Personaje.Clases.Clases[0]) || p.getClase().equals(Personaje.Clases.Clases[2]) ||p.getClase().equals(Personaje.Clases.Clases[4])){
                if(p.getClase().equals(Personaje.Clases.Clases[2])){
                    nameArmaduras.clear();
                    spArmadura.setEnabled(false);
                    nameArmaduras.add("No usa Armaduras");
                }

                Cursor armasPicaro = db.rawQuery("SELECT idArma, nombre,damage,tipoArma,tipoDamage FROM armas WHERE tipoArma IN('Ligera','A Distancia');",null);
                Cursor armadurasPicaro = db.rawQuery("SELECT idArmadura, nombre,claseArmadura,peso,fuerzaRequerida FROM armaduras WHERE peso BETWEEN 8 and 15;",null);

                if (armasPicaro.moveToFirst()) {
                    do {
                        Arma a = new Arma();
                        nameArma = armasPicaro.getString(1); //Nombre Arma
                        nameArmas.add(nameArma);
                        a.setIdArma(armasPicaro.getInt(0));
                        a.setNombre(nameArma);
                        a.setDamage(armasPicaro.getInt(2)); //Daño
                        a.setTipoArma(armasPicaro.getString(3));//tipo de arma
                        armas.add(a);
                    } while (armasPicaro.moveToNext());
                }
                if (armadurasPicaro.moveToFirst()){
                    do{
                        Armadura ar = new Armadura();
                        nameArmadura = armadurasPicaro.getString(1);
                        nameArmaduras.add(nameArmadura);
                        ar.setIdArmadura(armadurasPicaro.getInt(0));
                        ar.setNombre(nameArmadura);
                        ar.setClaseArmadura(armadurasPicaro.getInt(2));
                        ar.setFuerzaReq(armadurasPicaro.getInt(4));
                        armaduras.add(ar);
                    }while(armadurasPicaro.moveToNext());
                }
            }
            if(p.getClase().equals(Personaje.Clases.Clases[1])){ //Guerrero

                Cursor armasCursor = db.rawQuery("SELECT idArma, nombre,damage,tipoArma,tipoDamage FROM armas",null);
                Cursor armadurasCursor = db.rawQuery("SELECT idArmadura, nombre,claseArmadura,peso,fuerzaRequerida FROM armaduras",null);

                if(armasCursor.moveToFirst()){
                    do{
                        Arma a = new Arma();
                        nameArma = armasCursor.getString(1);
                        nameArmas.add(nameArma);
                        a.setIdArma(armasCursor.getInt(0));
                        a.setNombre(nameArma);
                        a.setDamage(armasCursor.getInt(2));
                        a.setTipoArma(armasCursor.getString(3));//tipo de arma
                        armas.add(a);
                    }while(armasCursor.moveToNext());
                }

                if(armadurasCursor.moveToFirst()){
                    do{
                        Armadura ar = new Armadura();
                        nameArmadura = armadurasCursor.getString(1);
                        nameArmaduras.add(nameArmadura);
                        ar.setIdArmadura(armadurasCursor.getInt(0));
                        ar.setNombre(nameArmadura);
                        ar.setClaseArmadura(armadurasCursor.getInt(2));
                        ar.setFuerzaReq(armadurasCursor.getInt(4));
                        armaduras.add(ar);
                    }while(armadurasCursor.moveToNext());
                }

            }
            if(p.getClase().equals(Personaje.Clases.Clases[3])){


                Cursor armasDruida = db.rawQuery("SELECT idArma,nombre,damage,tipoArma,tipoDamage FROM armas WHERE tipoArma <>'Pesada';",null);
                Cursor armadurasDruida = db.rawQuery("SELECT idArmadura,nombre,claseArmadura,peso,FuerzaRequerida FROM armaduras WHERE peso BETWEEN 8 AND 40;",null);
                if(armasDruida.moveToFirst()){
                    do{
                        Arma a = new Arma();
                        nameArma = armasDruida.getString(1);
                        nameArmas.add(nameArma);
                        a.setIdArma(armasDruida.getInt(0));
                        a.setNombre(armasDruida.getString(1));
                        a.setDamage(armasDruida.getInt(2));
                        a.setTipoArma(armasDruida.getString(2));//tipo de arma
                        armas.add(a);
                    }while(armasDruida.moveToNext());
                }
                if(armadurasDruida.moveToFirst()){
                    do{
                        Armadura ar = new Armadura();
                        nameArmadura = armadurasDruida.getString(1);
                        nameArmaduras.add(nameArmadura);
                        ar.setIdArmadura(armadurasDruida.getInt(0));
                        ar.setNombre(nameArmadura);
                        ar.setClaseArmadura(armadurasDruida.getInt(2));
                        ar.setFuerzaReq(armadurasDruida.getInt(3));
                        armaduras.add(ar);
                    }while(armadurasDruida.moveToNext());
                }
            }


            adapterSpells = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,nameSpells);
            adapterSpells.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spHechizo.setAdapter(adapterSpells);


            adapterArmas = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,nameArmas);
            adapterArmas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spArma.setAdapter(adapterArmas);

            adapterArmaduras = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,nameArmaduras);
            adapterArmaduras.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spArmadura.setAdapter(adapterArmaduras);

        }catch(Exception ex){
            Toast.makeText(this, "Error"+ex, Toast.LENGTH_SHORT).show();
        }

        btnCrearPersonaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.setCA(CA);
                p.setLevel(1);
                p.setVel();
                p.setEXP(0);
                p.setIdGuild(-1);
                try {
                    if (PersonajeController.createPersonaje(getApplicationContext(), p) > 0) {
                        Toast.makeText(CreatedPersonaje.this, "guardado con exito", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CreatedPersonaje.this, "No se guardó", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception ex){
                    Toast.makeText(CreatedPersonaje.this, "Error "+ex, Toast.LENGTH_SHORT).show();
                }
                //Añadir a la BD
                iniciarActividad(List_Personajes.class);
            }
        });



        btnCancelCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarActividad(Jugador.class);
            }
        });
        spArma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                int DMG = armas.get(i).getDamage();
                idArma = armas.get(i).getIdArma();
                p.setIdArma(idArma);
                if(armas.get(i).getTipoArma().equals(Arma.tipoArma.tipos[2])){
                    DMG = DMG + p.getModDes();
                }else{
                    DMG = DMG + p.getModStr();
                }
                tvATQShow.setText(DMG+"");

                }catch(Exception ex){
                    Toast.makeText(CreatedPersonaje.this, "ERROR"+ex, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spArmadura.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i ,long l) {
                try {

                    CA = armaduras.get(i).getClaseArmadura(); //Obtenemos el valor que va a contribuir la armadura.

                    Toast.makeText(CreatedPersonaje.this, "armaduras.get(i).getClaseArmadura() = "+CA, Toast.LENGTH_SHORT).show();

                    idArmadura = armaduras.get(i).getIdArmadura(); //Seteamos ID ARMADURA en la clase
                    p.setIdArmadura(idArmadura);

                    CA = CA + p.getModDes(); //Sumamos el modificador con el valor de la armadura

                    if(p.getClase().equals(Personaje.Clases.Clases[2])){
                        CA = p.getModDes();
                    }

                    tvCAShow.setText(CA+""); //Se muestra el valor sumado de la armadura.

                }catch(Exception ex){
                    Toast.makeText(CreatedPersonaje.this, "ERROR"+ex, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spHechizo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try{
                    if(spells_true) {
                        idHechizo = spells.get(i).getIdHechizo();
                        p.setIdHechizo(idHechizo);
                    }else{
                        idHechizo=0;
                    }
                }catch (Exception ex){
                    Toast.makeText(CreatedPersonaje.this, "ERROR "+ex, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}