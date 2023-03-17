package com.example.cafoma_app.vue;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cafoma_app.R;
import com.example.cafoma_app.controleur.Controleur;
import com.example.cafoma_app.controleur.ControleurBdd;
import com.example.cafoma_app.controleur.ControleurServeur;
import com.example.cafoma_app.entite.Formation;

import java.io.InputStream;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {
    private static String TAG = "Formation Activity";
    private TextView titreView;
    private TextView id;
    private TextView cout;
    private TextView nom;
    private TextView description;
    private Controleur controleur;
    private Formation formation;
    private int mode;
    private String titre;
    private ImageView iwImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getFormationByMode();
        if(formation != null){
            titreView.setText(titre);
            initialisationIhm();
        }
        else {
            titreView.setText("Aucune formation sélectionné");
        }
    }
    private void getFormationByMode(){
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
        formation = controleur.getFormation();
        Log.i(TAG,"formation=" + formation);
    }
    private void initialisationIhm() {
        recupererComposant();
        renseignerComposant();
    }
    private void recupererComposant(){
        id = (TextView) findViewById(R.id.id);
        cout = (TextView) findViewById(R.id.coutId);
        nom = (TextView) findViewById(R.id.nomId);
        description = (TextView) findViewById(R.id.descriptionId);
        iwImage = findViewById(R.id.imageIdFormation);
    }
    private void renseignerComposant(){
        id.setText(Integer.toString(formation.getNumFormation()));
        cout.setText(Integer.toString(formation.getMontant()));
        nom.setText(formation.getNom());
        description.setText(formation.getDescription());
        iwImage.setVisibility(View.VISIBLE);
        loadImageView(iwImage,"http://10.0.2.2/formation/public/images/" + formation.getImage());
    }
    private void loadImageView (ImageView img, String url) {
        Log.i("loadImageView",url);
        new Thread(new Runnable() {
            public void run(){
                try {
                    Log.i(TAG, "loadImageView thread");
                    final Drawable drawable = Drawable.createFromStream((InputStream) new URL(url).getContent(), "src");
                    Log.i(TAG, "loadImageView dawable");
                    Thread.sleep(100); // Pour serveur local
                    img.post(new Runnable() {
                        public void run() {
                            Log.i(TAG,"setImageDrawable");
                            img.setImageDrawable(drawable);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}