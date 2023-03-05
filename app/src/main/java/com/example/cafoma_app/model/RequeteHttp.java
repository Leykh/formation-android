package com.example.cafoma_app.model;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequeteHttp extends AsyncTask<String, Void, String> {
    private final static String TAG = "RequeteHttp";

    public ReponseAsyncItf reponseAsync =null; // gestion du retour asynchrone, réponse serbeur
    private String parametres = ""; // paramètres de l'URL

    public void addParam(String nom, String valeur) {
            if (parametres.equals("")) {
                parametres = nom + "=" + valeur;
            }else{
                // paramètres suivants (séparés par &)
                parametres += "&" + nom + "=" + valeur;
            }
    }
    // . . .
    @Override
    protected String doInBackground(String... urls) {
        HttpURLConnection connexion = null;
        String data = "";
        Log.i(TAG, "doInBackground");
        try {
            // création de l'url à partir de l'adresse reçu en paramètre, dans urls[0]
            URL url = new URL(urls[0] + "?" + parametres);
            // ouverture de la connexion
            connexion = (HttpURLConnection) url.openConnection();
            connexion.setRequestMethod("GET");
            InputStream in = connexion.getInputStream();
            data = readStream(in);
            Log.i(TAG, data);
        } catch (Exception e) {
            Log.i(TAG, "doInBackground ERREUR");
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(String result) {
        reponseAsync.reponseRequete(result);
    }

    // . . .
    private String readStream(InputStream in) {
        BufferedReader reader = null;
        String data = "";
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                data += line;
            }
        } catch (IOException e) {
            Log.e(TAG, "IOException");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }
}
