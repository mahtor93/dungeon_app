package com.example.creacindepersonaje.Jugador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.creacindepersonaje.Controller.ItemsController;
import com.example.creacindepersonaje.Controller.SpellController;
import com.example.creacindepersonaje.Modelos.Arma;
import com.example.creacindepersonaje.Modelos.Armadura;
import com.example.creacindepersonaje.Modelos.Hechizo;
import com.example.creacindepersonaje.R;

public class verItemSeleccionado extends AppCompatActivity {

    private TextView tvTitle,linea1,linea2,linea3,linea4;

    int idArma;
    int idArmadura;
    int idHechizo;
    int id_pj;
    int uno_otro;
    int idEdit;
    Arma a = new Arma();
    Armadura ar = new Armadura();
    Hechizo sp = new Hechizo();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.listado_personajes_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        try {
            switch (item.getItemId()) {
                case R.id.mn1:
                    Intent intent = new Intent(getApplicationContext(), Editor_Items.class);
                    intent.putExtra("idItem", idEdit);
                    intent.putExtra("unotro", uno_otro);
                    startActivity(intent);
                    return true;
                case R.id.mn2:
                    Intent intent1 = new Intent(getApplicationContext(),eliminar.class);
                    intent1.putExtra("idItem",idEdit);
                    intent1.putExtra("unotro",uno_otro);
                    startActivity(intent1);
                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }

        }catch (Exception ex){
            Toast.makeText(this, "error "+ex, Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_item_seleccionado);
        iniciarComponentes();
        iniciarEventos();
    }
    private void iniciarComponentes(){
        tvTitle = findViewById(R.id.tvTitle);
        linea1 = findViewById(R.id.linea1);
        linea2 = findViewById(R.id.linea2);
        linea3 = findViewById(R.id.linea3);
        linea4 = findViewById(R.id.linea4);

    }

    private void iniciarEventos(){
        try {
            Intent intent = this.getIntent();
            Bundle extra = intent.getExtras();
            id_pj = extra.getInt("ID_PJ");
            idArma = (int) extra.getInt("ID_ARMA");
            uno_otro = extra.getInt("unotro");
            idArmadura = (int) extra.getInt("idArmadura");
            idHechizo = (int) extra.getInt("spellId");


            a = ItemsController.findArma(getApplicationContext(),idArma);
            ar = ItemsController.findArmadura(getApplicationContext(),idArmadura);
            sp = SpellController.findHechizo(getApplicationContext(),idHechizo);

            if(uno_otro==0) {
                //Arma
                idEdit=a.getIdArma();
                tvTitle.setText(a.getNombre());
                linea1.setText("Daño :" + a.getDamage());
                linea2.setText("Tipo de arma : " + a.getTipoArma());
                linea3.setText("Tipo de daño :" + a.getTipoDamage());
                linea4.setText("Descripcion :\n" + a.getDescripcion());
            }
            if(uno_otro==1){
                //Armadura
                idEdit=ar.getIdArmadura();
                tvTitle.setText(ar.getNombre());
                linea1.setText("Clase de Armadura :" + ar.getClaseArmadura());
                linea2.setText("Peso : " + ar.getPeso());
                linea3.setText("Fuerza requerida :" + ar.getFuerzaReq());
                linea4.setText("Descripcion  :\n" + ar.getDescripcion());
            }
            if(uno_otro==2){
                //Hechizo
                idEdit=sp.getIdHechizo();
                tvTitle.setText(sp.getNombre());
                linea1.setText("Tipo de hechizo "+sp.getTipoHechizo());
                linea2.setText("Nivel :"+sp.getNivelHechizo()+"   Escudo :"+sp.getShield());
                linea3.setText("Daño :"+sp.getDamage()+"   Curacion :"+sp.getHeal());
                linea4.setText("Descripcion :\n"+sp.getDescripcion());
            }


        }catch (Exception ex){
            Toast.makeText(this, "error "+ex, Toast.LENGTH_SHORT).show();
        }




    }
}