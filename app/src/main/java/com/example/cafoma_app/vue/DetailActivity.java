package com.example.cafoma_app.vue;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cafoma_app.R;
import com.example.cafoma_app.controleur.Controleur;
import com.example.cafoma_app.controleur.ControleurBdd;
import com.example.cafoma_app.controleur.ControleurServeur;
import com.example.cafoma_app.entite.Formation;
import com.example.cafoma_app.entite.Ressource;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private static String TAG = "Formation Activity";
    private TextView titreView;
    private TextView id;
    private TextView cout;
    private TextView nom;
    private FloatingActionButton favori;
    private TextView description;
    private Controleur controleur;
    private Formation formation;
    private int mode;
    private String titre;
    private ImageView iwImage;
    private List<Ressource> ressourceList;
    private ListView liste;
    private ArrayAdapter<String> adapter;
    private ControleurBdd controleurBdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getFormationByMode();
        if(formation != null){
            titreView.setText(titre);
            controleur = ControleurServeur.getInstance();
            ressourceList = controleur.getRessourceList();
            Log.i(TAG,"ressources=" + ressourceList);
            Log.i(TAG,"mode=" + mode);
            initialisationIhm();
        }
        else {
            titreView.setText("Aucune formation sélectionné");
        }
    }
    private void getFormationByMode(){
        mode = getIntent().getIntExtra("mode", 2);
        titreView = (TextView) findViewById(R.id.titreId);
        if(mode == 1) {
            controleur = ControleurServeur.getInstance();
            titre = "Mes formations";
            formation = controleur.getFormation();
        }
        else if(mode == 3){
            controleurBdd = ControleurBdd.getInstance(this);
            titre = "Favoris";
            formation = controleurBdd.getFormation();
        }
        else {
            controleur = ControleurServeur.getInstance();
            titre = "Formation Serveur";
            formation = controleur.getFormation();
        }
        Log.i(TAG,"formation=" + formation);
    }
    private void initialisationIhm() {
        recupererComposant();
        renseignerComposant();
        if (mode == 1){
            afficherListeRessource();
        }
        if (mode ==2){
            favori.setVisibility(View.VISIBLE);
            ajouterFavoris();
        }
        if (mode == 3){
            favori.setVisibility(View.VISIBLE);
            ajouterFavoris();
        }
    }
    private void recupererComposant(){
        id = (TextView) findViewById(R.id.id);
        cout = (TextView) findViewById(R.id.coutId);
        nom = (TextView) findViewById(R.id.nomId);
        favori = findViewById(R.id.buttonFavori);
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
    private void ajouterFavoris(){

        favori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mode == 2){
                    Log.i(TAG,"ajoutfavoris");
                    //controleurBdd.persister(formation);
                }
            }
        });

    }
    private void afficherListeRessource(){
        List<String> strFormationList = formaterRessourceList(ressourceList);
        liste = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                strFormationList);
        liste.setAdapter(adapter);
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                controleur.setRessource(ressourceList.get(position));
                String urlPdf = "http://10.0.2.2/formation/public/ressources/" + controleur.getRessource().getIdFormation()+"/"+controleur.getRessource().getRessource();
                Log.i(TAG,"pdf");
                DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(urlPdf);
                Log.i(TAG,"uri="+uri);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                long reference = manager.enqueue(request);
                Log.i(TAG,"reference=" + reference);
            }
        });
    }
    private List<String> formaterRessourceList(List<Ressource> ressources){
        List<String> strRessourceList = new ArrayList<>();
        for (Ressource ressource : ressources) {
            if (ressource.getIdFormation() == controleur.getFormation().getNumFormation()){
            strRessourceList.add(ressource.getRessourceStr());
            }
        }
        Log.i(TAG, "onCreate profilList=" + strRessourceList);
        return strRessourceList;

    }
    private long downloadFile(Uri uri, String fileStorageDestinationUri, String fileName) {

        long downloadReference = 0;

        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        try {
            DownloadManager.Request request = new DownloadManager.Request(uri);

            //Setting title of request
            request.setTitle(fileName);

            //Setting description of request
            request.setDescription("Your file is downloading");

            //set notification when download completed
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

            //Set the local destination for the downloaded file to a path within the application's external files directory
            request.setDestinationInExternalPublicDir(fileStorageDestinationUri, fileName);

            request.allowScanningByMediaScanner();

            //Enqueue download and save the referenceId
            downloadReference = downloadManager.enqueue(request);
        } catch (IllegalArgumentException e) {
            Toast.makeText(this, "Download not working", Toast.LENGTH_LONG).show();

        }
        return downloadReference;
    }

}