package com.example.cafoma_app.vue;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cafoma_app.R;
import com.example.cafoma_app.controleur.Controleur;
import com.example.cafoma_app.controleur.ControleurBdd;
import com.example.cafoma_app.controleur.ControleurServeur;
import com.example.cafoma_app.entite.Formation;

public class DetailActivity extends AppCompatActivity {
    private static String TAG = "FactureActivity";
    private TextView titreView;
    private TextView id;
    private TextView cout;
    private TextView image;
    private TextView nom;
    private TextView description;
    private Controleur controleur;
    private Formation formation;
    private int mode;
    private String titre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getFactureByMode();
        if(formation != null){
            titreView.setText(titre);
            initialisationIhm();
        }
        else {
            titreView.setText("Aucune formation sélectionné");
        }
    }
    private void getFactureByMode(){
        mode = getIntent().getIntExtra("mode", 2);
        titreView = (TextView) findViewById(R.id.titreId);
        if(mode == 0) {
            controleur = ControleurBdd.getInstance(this);
            titre = "Formation BDD";
        }
        else {
            controleur = ControleurServeur.getInstance();
            titre = "Formation Serveur";
        }
        formation = controleur.getFacture();
        Log.i(TAG,"formation=" + formation);
    }
    private void initialisationIhm() {
        recupererComposant();
        renseignerComposant();
    }
    private void recupererComposant(){
        id = (TextView) findViewById(R.id.id);
        cout = (TextView) findViewById(R.id.coutId);
        image = (TextView) findViewById(R.id.imageId);
        nom = (TextView) findViewById(R.id.nomId);
        description = (TextView) findViewById(R.id.descriptionId);
    }
    private void renseignerComposant(){
        id.setText(Integer.toString(formation.getNumFormation()));
        cout.setText(Integer.toString(formation.getMontant()));
        image.setText(formation.getImage());
        nom.setText(formation.getNom());
        description.setText(formation.getDescription());
    }



}