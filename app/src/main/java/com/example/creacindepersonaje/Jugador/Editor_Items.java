package com.example.creacindepersonaje.Jugador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.creacindepersonaje.Controller.ItemsController;
import com.example.creacindepersonaje.Controller.SpellController;
import com.example.creacindepersonaje.Modelos.Arma;
import com.example.creacindepersonaje.Modelos.Armadura;
import com.example.creacindepersonaje.Modelos.Hechizo;
import com.example.creacindepersonaje.R;

public class Editor_Items extends AppCompatActivity {
    private TextView tvTitle;
    private EditText etNombre, etDescripcion;
    private Button btnEditar;
    int idArma;
    int idArmadura;
    int idHechizo;
    int uno_otro;
    Arma a = new Arma();
    Armadura ar = new Armadura();
    Hechizo sp = new Hechizo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_stuff);
        iniciarComponentes();
        iniciarEventos();
    }

    private void iniciarComponentes(){
        tvTitle = findViewById(R.id.tvTitle);
        etNombre = findViewById(R.id.etNombre);
        etDescripcion = findViewById(R.id.etDescripcion);
        btnEditar = findViewById(R.id.btnEditar);
    }
    private void iniciarEventos(){
        try{
            Intent intent = this.getIntent();
            Bundle extra = intent.getExtras();
            idArma = extra.getInt("idItem");
            idArmadura = extra.getInt("idItem");
            idHechizo = extra.getInt("idItem");
            uno_otro = extra.getInt("unotro");

            a = ItemsController.findArma(getApplicationContext(),idArma);
            ar = ItemsController.findArmadura(getApplicationContext(),idArmadura);
            sp = SpellController.findHechizo(getApplicationContext(),idHechizo);

            if(uno_otro==0){
                tvTitle.setText(a.getNombre());

            }
            if(uno_otro==1){
                tvTitle.setText(ar.getNombre());
            }
            if(uno_otro==2){
                tvTitle.setText(sp.getNombre());
            }


        }catch (Exception ex){
            Toast.makeText(this, "error "+ex, Toast.LENGTH_SHORT).show();
        }

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNombre.getText().length()>0&&etDescripcion.getText().length()>0) {
                    try {
                        if (uno_otro == 0) {
                            //Arma
                            int id = ItemsController.getIdArmaByName(getApplicationContext(),etNombre.getText().toString());
                            if(id!=-1){
                                Toast.makeText(Editor_Items.this, "El nombre ya está asignado a otra Arma", Toast.LENGTH_SHORT).show();
                            }else {
                                a.setNombre("'" + etNombre.getText().toString() + "'");
                                a.setDescripcion("'" + etDescripcion.getText().toString() + "'");
                                ItemsController.editArma(getApplicationContext(), a);
                                Intent intent = new Intent(getApplicationContext(), verItemSeleccionado.class);
                                intent.putExtra("ID_ARMA", idArma);
                                intent.putExtra("unotro", 0);
                                startActivity(intent);
                            }
                        }
                        if (uno_otro == 1) {
                            //Armadura
                            int id = ItemsController.getIdArmaduraByName(getApplicationContext(),etNombre.getText().toString());
                            if(id!=-1){
                                Toast.makeText(Editor_Items.this, "El nombre ya está asignado a otra armadura", Toast.LENGTH_SHORT).show();
                            }else {
                                ar.setNombre("'" + etNombre.getText().toString() + "'");
                                ar.setDescripcion("'" + etDescripcion.getText().toString() + "'");
                                ItemsController.editArmadura(getApplicationContext(), ar);
                                Intent intent = new Intent(getApplicationContext(), verItemSeleccionado.class);
                                intent.putExtra("idArmadura", idArmadura);
                                intent.putExtra("unotro", 1);
                                startActivity(intent);
                            }
                        }
                        if (uno_otro == 2) {
                            //Hechizo
                            int id = SpellController.findHechizoByName(getApplicationContext(),etNombre.getText().toString());
                            if(id!=-1){
                                Toast.makeText(Editor_Items.this, "El nombre ya está asignado a otro hechizo", Toast.LENGTH_SHORT).show();
                            }else {
                                sp.setNombre("'" + etNombre.getText().toString() + "'");
                                sp.setDescripcion("'" + etDescripcion.getText().toString() + "'");
                                SpellController.editHechizo(getApplicationContext(), sp);
                                Intent intent = new Intent(getApplicationContext(), verItemSeleccionado.class);
                                intent.putExtra("spellId", idHechizo);
                                intent.putExtra("unotro", 2);
                                startActivity(intent);
                            }

                        }
                    } catch (Exception ex) {
                        Toast.makeText(Editor_Items.this, "error " + ex, Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Editor_Items.this, "Rellena los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}