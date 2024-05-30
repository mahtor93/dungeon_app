package com.example.creacindepersonaje.Jugador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.creacindepersonaje.Controller.ItemsController;
import com.example.creacindepersonaje.Controller.PersonajeController;
import com.example.creacindepersonaje.Controller.SpellController;
import com.example.creacindepersonaje.Modelos.Arma;
import com.example.creacindepersonaje.Modelos.Armadura;
import com.example.creacindepersonaje.Modelos.Hechizo;
import com.example.creacindepersonaje.Modelos.Personaje;
import com.example.creacindepersonaje.PJ.List_Personajes;
import com.example.creacindepersonaje.R;

public class eliminar extends AppCompatActivity {
    private Button btnEliminar, btnCancelDelete;
    private TextView tvEliminar;

    int idArma;
    int idArmadura;
    int idHechizo;
    int idPersonaje;
    int uno_otro;
    Personaje p = new Personaje();
    Arma a = new Arma();
    Armadura ar = new Armadura();
    Hechizo sp = new Hechizo();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);
        iniciarComponentes();
        iniciarEventos();
    }

    private void iniciarComponentes(){
        btnEliminar = findViewById(R.id.btnEliminar);
        btnCancelDelete = findViewById(R.id.btnCancelDelete);
        tvEliminar = findViewById(R.id.tvEliminar);
    }

    private void iniciarEventos(){

        try{
            Intent intent = this.getIntent();
            Bundle extra = intent.getExtras();
            idArma = extra.getInt("idItem");
            idArmadura = extra.getInt("idItem");
            idHechizo = extra.getInt("idItem");
            idPersonaje = extra.getInt("ID_PJ");
            uno_otro = extra.getInt("unotro");

            a = ItemsController.findArma(getApplicationContext(),idArma);
            ar = ItemsController.findArmadura(getApplicationContext(),idArmadura);
            sp = SpellController.findHechizo(getApplicationContext(),idHechizo);
            p = PersonajeController.findPj(getApplicationContext(),idPersonaje);

            if(uno_otro==0){
                tvEliminar.setText(a.getNombre());

            }
            if(uno_otro==1){
                tvEliminar.setText(ar.getNombre());
            }
            if(uno_otro==2){
                tvEliminar.setText(sp.getNombre());
            }
            if(uno_otro==3){
                tvEliminar.setText(p.getNombre());
            }

        }catch (Exception ex){
            Toast.makeText(this, "error "+ex, Toast.LENGTH_SHORT).show();
        }

        btnCancelDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    finish();
                }catch (Exception ex){
                    Toast.makeText(eliminar.this, "error "+ex, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (uno_otro == 0) {
                        //Arma

                        ItemsController.removeArma(getApplicationContext(),a.getIdArma());
                        Intent intent = new Intent(getApplicationContext(),Forja.class);
                        intent.putExtra("ID_ARMA",idArma);
                        intent.putExtra("unotro",0);
                        startActivity(intent);
                    }
                    if (uno_otro == 1) {
                        //Armadura

                        ItemsController.removeArmadura(getApplicationContext(),ar.getIdArmadura());
                        Intent intent = new Intent(getApplicationContext(),Forja.class);
                        intent.putExtra("idArmadura",idArmadura);
                        intent.putExtra("unotro",1);
                        startActivity(intent);

                    }
                    if (uno_otro == 2) {
                        //Hechizo
                        SpellController.removeHechizo(getApplicationContext(),sp.getIdHechizo());
                        Intent intent = new Intent(getApplicationContext(),Spellbook.class);
                        intent.putExtra("spellId",idHechizo);
                        intent.putExtra("unotro",2);
                        startActivity(intent);
                    }
                    if(uno_otro==3){
                        PersonajeController.RemovePersonaje(getApplicationContext(),p.getIdPersonaje());
                        Intent intent = new Intent(getApplicationContext(), List_Personajes.class);
                        startActivity(intent);
                    }

                } catch (Exception ex) {
                    Toast.makeText(eliminar.this, "error " + ex, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}