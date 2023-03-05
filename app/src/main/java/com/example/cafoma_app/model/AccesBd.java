package com.example.cafoma_app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.cafoma_app.entite.Formation;

import java.util.ArrayList;
import java.util.List;

public class AccesBd {
    private static String TAG = "AccesBd";
    private DatabaseOpenHelper mDbHelper;
    private Context context;

    public AccesBd(Context context) {
        Log.i(TAG,"AccesBd");
        mDbHelper = new DatabaseOpenHelper(context);
        this.context = context;
    }

    public void persister(Formation formation){
        SQLiteDatabase dataBase = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseOpenHelper.ID_FIELD, formation.getNumFormation());
        values.put(DatabaseOpenHelper.MONTANT_FIELD, formation.getMontant());
        values.put(DatabaseOpenHelper.IMAGE_FIELD, formation.getImage());
        values.put(DatabaseOpenHelper.NOM_FIELD, formation.getNom());
        values.put(DatabaseOpenHelper.DESCRIPTION_FIELD, formation.getDescription());
        dataBase.insert(DatabaseOpenHelper.TABLE_NAME, null, values);
        dataBase.close();
    }

    public List<Formation> getListFacture() {
        Formation formation = null;
        List<Formation> formationList = null;
        SQLiteDatabase dataBase = mDbHelper.getReadableDatabase();
        String req = "select * from " +  DatabaseOpenHelper.TABLE_NAME + " order by " + DatabaseOpenHelper.ID_FIELD;
        Cursor cursor = dataBase.rawQuery(req, null);
        if (cursor.getCount() > 0) {
            formationList = new ArrayList<>();
            if (cursor.moveToFirst()) {
                do {
                    try {
                        int id = cursor.getInt(0);
                        int cout = cursor.getInt(1);
                        String image = cursor.getString(2);
                        String nom = cursor.getString(3);
                        String description = cursor.getString(4);
                        formation = new Formation(id, cout, image, nom, description);
                        formationList.add(formation);
                    } catch (SQLException e) {
                        Toast.makeText(this.context, "Impossible de lire les étudiants en bdd", Toast.LENGTH_LONG).show();
                    }
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        Log.i(TAG, "formationList=" + formationList);
        return formationList;
    }
}
