package com.example.cafoma_app.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
	private static String TAG = "DatabaseOpenHelper";
	final public static String TABLE_NAME = "formation";
	final public static String ID_FIELD = "id";
	final public static String MONTANT_FIELD = "cout";
	final public static String IMAGE_FIELD = "image";
	final public static String NOM_FIELD = "nom";
	final public static String DESCRIPTION_FIELD = "description";
	final public static String[] columns =
			{ ID_FIELD, IMAGE_FIELD, NOM_FIELD, DESCRIPTION_FIELD};

	private String create_table="create table " + TABLE_NAME + "("
			+ ID_FIELD + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ MONTANT_FIELD + 	" INTEGER NOT NULL,"
			+ IMAGE_FIELD + " TEXT NOT NULL,"
			+ NOM_FIELD + " TEXT NOT NULL,"
			+ DESCRIPTION_FIELD + " TEXT NOT NULL);";

	final private static String NAME_DB = "etudiant_db";
	final private static Integer VERSION = 1;
	final private Context mContext;

	public DatabaseOpenHelper(Context context) {
		super(context, NAME_DB, null, VERSION);
		this.mContext = context;
		Log.i(TAG,"constructeur DatabaseOpenHelper");
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(TAG,"onCreate");
		db.execSQL(create_table);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// N/A
	}
}
