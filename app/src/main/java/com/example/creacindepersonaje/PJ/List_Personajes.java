package com.example.creacindepersonaje.PJ;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.creacindepersonaje.BDConnection.DBConnection;
import com.example.creacindepersonaje.Modelos.Personaje;
import com.example.creacindepersonaje.PJ.View_Personaje;
import com.example.creacindepersonaje.R;

import java.util.ArrayList;

public class List_Personajes extends AppCompatActivity {

    private ListView personajesList;
    private ArrayAdapter<String> adapterPersonajes;
    private ArrayList<String> nombrePersonajes;
    private ArrayList<Personaje> personajeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_personajes);
        iniciarComponentes();
        iniciarEventos();
    }
    private void iniciarComponentes(){
        personajesList = findViewById(R.id.lvItems);
    }
    private void iniciarEventos(){
        nombrePersonajes = new ArrayList<>();
        personajeID = new ArrayList<>();
        try {
            DBConnection con = new DBConnection(getApplicationContext(), "base1", null, 1);
            SQLiteDatabase db = con.getReadableDatabase();
            Cursor pj = db.rawQuery("SELECT idPersonaje,NOMBRE_PERSONAJE,RAZA_PERSONAJE,SUBRAZA_PERSONAJE,CLASE_PERSONAJE,NIVEL FROM personajes", null);
            String descPj;

            if (pj.moveToFirst()) {
                do {
                    Personaje p = new Personaje();
                    p.setIdPersonaje(pj.getInt(0));

                    p.setIdPersonaje(pj.getInt(0));
                    personajeID.add(p);

                    descPj = pj.getString(1) + "\n" + pj.getString(2)+" " + pj.getString(3)+" " + pj.getString(4)+" Lvl " +pj.getInt(5);
                    nombrePersonajes.add(descPj);
                } while (pj.moveToNext());
            }
            adapterPersonajes = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombrePersonajes);
            personajesList.setAdapter(adapterPersonajes);

        }catch(Exception ex){
            Toast.makeText(this, "Error : "+ex, Toast.LENGTH_SHORT).show();
        }

        personajesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int idPJ = personajeID.get(i).getIdPersonaje();
                Intent verPersonaje = new Intent(getApplicationContext(), View_Personaje.class);
                verPersonaje.putExtra("ID",idPJ);
                startActivity(verPersonaje);

            }
        });

    }
}