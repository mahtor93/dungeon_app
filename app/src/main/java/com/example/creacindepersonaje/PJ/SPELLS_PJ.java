package com.example.creacindepersonaje.PJ;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.creacindepersonaje.Controller.SpellController;
import com.example.creacindepersonaje.Modelos.Hechizo;
import com.example.creacindepersonaje.R;
import com.example.creacindepersonaje.Jugador.verItemSeleccionado;

import java.util.ArrayList;

public class SPELLS_PJ extends AppCompatActivity {

    private TextView tvSpells;
    private ListView lvSpells;
    private ArrayList<String> listaSpells;
    private ArrayList<Hechizo> spells;
    private ArrayList<Integer> idSpells;
    private ArrayAdapter<String> adapterSpells;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_spells_pj);
            iniciarComponentes();
            iniciarEventos();
        }catch (Exception ex){
            Toast.makeText(this, "Error "+ex, Toast.LENGTH_SHORT).show();
        }




    }
    private void iniciarComponentes(){
        try {
            tvSpells = findViewById(R.id.tvSpells);
            lvSpells = findViewById(R.id.lvSpells);
        }catch (Exception ex){
            Toast.makeText(this, "Error "+ex, Toast.LENGTH_SHORT).show();
        }
    }
    private void iniciarEventos(){
        try {
            int idHechizo;
            listaSpells = new ArrayList<>(); //nombres de hechizo
            idSpells = new ArrayList<>(); //id de hechizo

            Intent intent = this.getIntent();
            Bundle extra = intent.getExtras();
            idHechizo = extra.getInt("ID_HECHIZO");

            Toast.makeText(this, "ID "+idHechizo, Toast.LENGTH_SHORT).show();

            Hechizo h = SpellController.findHechizo(getApplicationContext(),idHechizo);
            spells = SpellController.listarHechizos(getApplicationContext()); //Lista todos los hechizos

            listaSpells.add(h.getNombre());
            idSpells.add(h.getIdHechizo());

            adapterSpells = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaSpells);
            lvSpells.setAdapter(adapterSpells);


        }catch (Exception ex){
            Toast.makeText(this, "ERROR :"+ex, Toast.LENGTH_SHORT).show();
        }

        lvSpells.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try{
                    int spellId = idSpells.get(i);
                    Intent intent = new Intent(getApplicationContext(), verItemSeleccionado.class);
                    intent.putExtra("spellId",spellId);
                    intent.putExtra("unotro",2);
                    startActivity(intent);
                }catch (Exception ex){
                    Toast.makeText(SPELLS_PJ.this, "error "+ex, Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}