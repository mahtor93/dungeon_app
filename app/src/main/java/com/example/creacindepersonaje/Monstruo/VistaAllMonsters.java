package com.example.creacindepersonaje.Monstruo;

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
import com.example.creacindepersonaje.Modelos.Monstruo;
import com.example.creacindepersonaje.R;

import java.util.ArrayList;

public class VistaAllMonsters extends AppCompatActivity {

    private ListView listview_monster;
    private ArrayAdapter<String> adapterMonsters;
    private ArrayList<String> nombreMonstruos;
    private ArrayList<Monstruo> monstruoID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_all_monsters);
        iniciarComponentes();
        iniciarEventos();
    }

    private void iniciarComponentes() {
        listview_monster = findViewById(R.id.listview_monsters);

    }

    private void iniciarEventos() {
        nombreMonstruos = new ArrayList<>();
        monstruoID = new ArrayList<>();
        try {
            DBConnection con = new DBConnection(getApplicationContext(), "base1", null, 1);
            SQLiteDatabase db = con.getReadableDatabase();
            Cursor cur = db.rawQuery("SELECT idMonstruo,nameMonstruo,descripcion,CA  FROM monstruos", null);
            String textoMons;
            if (cur.moveToFirst()) {
                do {
                    Monstruo m = new Monstruo();
                    m.setIdMonstruo(cur.getInt(0));
                    monstruoID.add(m);

                    textoMons = cur.getString(1) + ", " + cur.getString(2);
                    nombreMonstruos.add(textoMons);
                } while (cur.moveToNext());
            }

            adapterMonsters = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreMonstruos);
            listview_monster.setAdapter(adapterMonsters);
        } catch (Exception ex) {
            Toast.makeText(this, "Error encontrado: " + ex, Toast.LENGTH_SHORT).show();
        }

        listview_monster.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int idMONS = monstruoID.get(i).getIdMonstruo();

                Intent verMonstruo = new Intent(getApplicationContext(),VistaMonsterActivity.class);
                verMonstruo.putExtra("ID",idMONS);

                startActivity(verMonstruo);

            }
        });

    }

}

