package com.example.cafoma_app.model;

import android.util.Log;

import com.example.cafoma_app.controleur.ControleurServeur;
import com.example.cafoma_app.entite.Formation;
import com.example.cafoma_app.entite.Ressource;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccesDistant implements ReponseAsyncItf {
    private final static String TAG = "AccesDistant";
    private static final String SERVERADDR = "http://10.0.2.2/formation/formation.rest.php";
    private ControleurServeur controleurServeur;
    public AccesDistant(){
        controleurServeur = ControleurServeur.getInstance();
    }
    @Override
    public void reponseRequete(String reponse) {
        String[] message = reponse.split("#");
        if(message.length > 1) {
            if (message[0].equals("lister")) {
                try {
                    JSONArray jsonTabFormation = new JSONArray(message[1]);
                    List<Formation> formationList = parserFormationList(jsonTabFormation);
                    controleurServeur.setFormationList(formationList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else if (message[0].equals("erreur")) {
                Log.i(TAG,"Erreur : " + message[1]);
            }
            if (message[0].equals("ressource")) {
                try {
                    JSONArray jsonTabRessource = new JSONArray(message[1]);
                    List<Ressource> ressourceList = parserRessourceList(jsonTabRessource);
                    controleurServeur.setRessourceList(ressourceList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else if (message[0].equals("erreur")) {
                Log.i(TAG,"Erreur : " + message[1]);
            }
        }
    }
    public void envoyerRequete(String operation){
        RequeteHttp requeteHttp = new RequeteHttp();
        requeteHttp.reponseAsync = this;
        requeteHttp.addParam("operation", operation);
        requeteHttp.execute(SERVERADDR);
    }
    private List<Formation> parserFormationList(JSONArray jsonTabFormation) throws JSONException {
        List<Formation> formationList = new ArrayList<>();
        Formation formation = null;
        for (int i=0; i<jsonTabFormation.length(); i++){
            int id = jsonTabFormation.getJSONObject(i).getInt("id");
            int cout = jsonTabFormation.getJSONObject(i).getInt("cout");
            String image = jsonTabFormation.getJSONObject(i).getString("image");
            String nom = jsonTabFormation.getJSONObject(i).getString("nom");
            String description = jsonTabFormation.getJSONObject(i).getString("description");
            formation = new Formation(id,cout,image,nom,description);
            formationList.add(formation);

        }
        return formationList;
    }
    private List<Ressource> parserRessourceList(JSONArray jsonTabRessource) throws JSONException {
        List<Ressource> ressourceList = new ArrayList<>();
        Ressource ressource = null;
        for (int i=0; i<jsonTabRessource.length(); i++){
            int idRessource = jsonTabRessource.getJSONObject(i).getInt("idRessource");
            int idFormation = jsonTabRessource.getJSONObject(i).getInt("idFormation");
            String description = jsonTabRessource.getJSONObject(i).getString("description");
            String ressources = jsonTabRessource.getJSONObject(i).getString("ressource");
            ressource = new Ressource(idRessource,idFormation,description,ressources);
            Log.i(TAG, "i=" + i + " ressource=" + ressource);
            ressourceList.add(ressource);
        }
        return ressourceList;
    }
}
