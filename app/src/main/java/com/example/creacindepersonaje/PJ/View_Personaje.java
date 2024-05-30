package com.example.creacindepersonaje.PJ;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.creacindepersonaje.Controller.PersonajeController;
import com.example.creacindepersonaje.Modelos.Dado;
import com.example.creacindepersonaje.Modelos.Personaje;
import com.example.creacindepersonaje.R;
import com.example.creacindepersonaje.Jugador.eliminar;

public class View_Personaje extends AppCompatActivity {


    private TextView tvNombre,tvRaza,tvSubRaza,tvClase,
            tvVidaShow,tvCAShow,tvVELShowCreate,tvLVL, tvSTR,
            tvBonoSTR,tvDES,tvBonoDES,tvCON,tvBonoCON,
            tvCAR,tvBonoCAR,tvINT,tvBonoINT,tvSAB,tvBonoSAB,tvATQShow,tvEXPShow,tvIniciativa,tvBonoxCompetencia,tvVELShow;
    private Button btnIniciativa,btnVerItems,btnVerHechizos;
    private EditText txtInspiracion;
    int idArma;
    int idArmadura;
    int idHechizo;
    int aptitud_magica;
    int uno_otro =3; //Personaje
    int id_pj;
    Personaje p = new Personaje();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.listado_personajes_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        try {
            switch (item.getItemId()) {
                case R.id.mn1:
                    Intent intent = new Intent(getApplicationContext(), Editor_Nombre.class);
                    intent.putExtra("ID_PJ", id_pj);
                    startActivity(intent);
                    return true;
                case R.id.mn2:
                    Intent remove = new Intent(getApplicationContext(), eliminar.class);
                    remove.putExtra("ID_PJ",id_pj);
                    remove.putExtra("unotro",3);
                    startActivity(remove);
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
        setContentView(R.layout.activity_view_personaje);
        iniciarComponentes();
        iniciarEventos();
    }
    private void iniciarComponentes(){

        txtInspiracion = findViewById(R.id.txtInspiracion);

        tvNombre = findViewById(R.id.tvNombre);
        tvVELShow = findViewById(R.id.tvVELShow);
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

        tvLVL = findViewById(R.id.tvLVL);
        tvEXPShow = findViewById(R.id.tvEXPShow);
        tvIniciativa = findViewById(R.id.tvIniciativa);
        tvBonoxCompetencia = findViewById(R.id.tvBonoxCompetencia);

        btnIniciativa = findViewById(R.id.btnIniciativa);
        btnVerHechizos = findViewById(R.id.btnVerHechizos);
        btnVerItems = findViewById(R.id.btnVerItems);
    }

    private void iniciarEventos(){


        try{

            Intent intent = this.getIntent();
            Bundle extra = intent.getExtras();
            int ID = extra.getInt("ID");
            p = PersonajeController.findPj(getApplicationContext(),ID);

                id_pj = p.getIdPersonaje();

                tvNombre.setText(p.getNombre());
                tvRaza.setText(p.getRaza());
                tvSubRaza.setText(p.getSub_Raza());
                tvClase.setText(p.getClase());
                tvSTR.setText(p.getFuerza()+"");
                tvDES.setText(p.getDestreza()+"");
                tvCON.setText(p.getConstitucion()+"");
                tvINT.setText(p.getInteligencia()+"");
                tvSAB.setText(p.getSabiduria()+"");
                tvCAR.setText(p.getCarisma()+"");
                tvBonoSTR.setText(p.getModStr()+"");
                tvBonoDES.setText(p.getModDes()+"");
                tvBonoCON.setText(p.getModCon()+"");
                tvBonoINT.setText(p.getModInt()+"");
                tvBonoSAB.setText(p.getModSab()+"");
                tvBonoCAR.setText(p.getModCar()+"");
                tvVELShow.setText(p.getVel()+"");
                tvCAShow.setText(p.getCA()+"");
                tvVidaShow.setText(p.getPG()+"");
                tvLVL.setText(p.getLevel()+"");
                tvEXPShow.setText(p.getEXP()+"");
                aptitud_magica = p.getAptitudMagica();//23 Aptitud Magica;
                txtInspiracion.setText(p.getInspiracion()+"");//24 INSP.
                tvBonoxCompetencia.setText(p.getBonoCompetencia()+"");//25 BONO_COMP
                tvIniciativa.setText(p.getIniciativa()+"");
                idArma = p.getIdArma();
                idArmadura =p.getIdArmadura();
                idHechizo=p.getIdHechizo();

                if(p.getAptitudMagica()==0){
                    btnVerHechizos.setEnabled(false);
                }

        }catch (Exception ex){
            Toast.makeText(this, "Error "+ex, Toast.LENGTH_SHORT).show();
        }

        btnIniciativa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dado d = new Dado();
                d.setValorDado(20);
                tvIniciativa.setText(d.lanzarDado()+"");
                int iniciativa = Integer.parseInt(tvIniciativa.getText().toString());
                try{
                    PersonajeController.setIniciativa(getApplicationContext(),id_pj,iniciativa);
                }catch (Exception ex){
                    Toast.makeText(View_Personaje.this, "Error "+ex, Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnVerItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(getApplicationContext(), ITEMS_PJ.class);
                    intent.putExtra("ID_ARMA", p.getIdArma());
                    intent.putExtra("ID_ARMADURA", p.getIdArmadura());
                    intent.putExtra("NAME", tvNombre.getText().toString());
                    intent.putExtra("ID_PJ", p.getIdPersonaje());
                    startActivity(intent);
                }catch (Exception ex){
                    Toast.makeText(View_Personaje.this, "Error: "+ex, Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnVerHechizos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SPELLS_PJ.class);
                intent.putExtra("ID_HECHIZO",p.getIdHechizo());
                intent.putExtra("ID_PJ",p.getIdPersonaje());
                startActivity(intent);
            }
        });




    }
}