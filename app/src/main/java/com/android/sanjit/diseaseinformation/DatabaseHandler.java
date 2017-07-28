package com.android.sanjit.diseaseinformation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 7;
    private static int initialized = 0;

    // Database Name
    private static final String DATABASE_NAME = "DiseaseInfo.db";

    // diseases table name
    private static final String TABLE_DISEASE = "disease";

    // diseases Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_TYPE = "type";
    private static final String KEY_SYMPTOMS = "symptoms";
    private static final String KEY_CAUSE = "cause";
    private static final String KEY_FIRSTAID = "firstaid";
    private static final String KEY_TREATMENT = "treatment";

    //Singleton instance
    private static DatabaseHandler sInstance;
    public static synchronized DatabaseHandler getInstance(Context context) {

        if (sInstance == null) {
            sInstance = new DatabaseHandler(context.getApplicationContext());
        }
        return sInstance;
    }

    private DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_diseaseS_TABLE = "CREATE TABLE " + TABLE_DISEASE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_TYPE + " TEXT, " +KEY_SYMPTOMS+" TEXT, "+KEY_CAUSE+" TEXT, "+
                KEY_FIRSTAID +" TEXT,"+KEY_TREATMENT+" TEXT "+")";
        db.execSQL(CREATE_diseaseS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISEASE);

        // Create tables again
        onCreate(db);
    }
    // Adding new disease
    public void adddisease(Disease disease) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, disease.getName());
        values.put(KEY_TYPE, disease.get_type());
        values.put(KEY_SYMPTOMS, disease.get_symptomps());
        values.put(KEY_CAUSE, disease.get_causes());
        values.put(KEY_FIRSTAID, disease.get_first_aid());
        values.put(KEY_TREATMENT, disease.get_treatment());

        // Inserting Row
        db.insert(TABLE_DISEASE, null, values);
        db.close(); // Closing database connection
    }
    // Getting single disease
    public Disease getdisease(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DISEASE, new String[] { KEY_ID,
                        KEY_NAME, KEY_TYPE,KEY_SYMPTOMS,KEY_CAUSE,KEY_FIRSTAID,KEY_TREATMENT }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Disease disease = new Disease(cursor.getString(1), cursor.getString(2),cursor.getString(3)
                ,cursor.getString(4),cursor.getString(5),cursor.getString(6));
        // return disease
        return disease;
    }
    // Getting All diseases
    public List<Disease> getAlldiseases() {
        List<Disease> diseaseList = new ArrayList<Disease>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DISEASE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Disease disease = new Disease();
                disease.setID(Integer.parseInt(cursor.getString(0)));
                disease.setName(cursor.getString(1));
                disease.set_type(cursor.getString(2));
                disease.set_symptomps(cursor.getString(3));
                disease.set_causes(cursor.getString(4));
                disease.set_first_aid(cursor.getString(5));
                disease.set_treatment(cursor.getString(6));
                // Adding disease to list
                diseaseList.add(disease);
            } while (cursor.moveToNext());
        }

        // return disease list
        return diseaseList;
    }
    // Getting diseases Count
    public int getdiseasesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_DISEASE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
    // Updating single disease
    public int updatedisease(Disease disease) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, disease.getName());
        values.put(KEY_TYPE, disease.get_type());
        values.put(KEY_SYMPTOMS, disease.get_symptomps());
        values.put(KEY_CAUSE, disease.get_causes());
        values.put(KEY_FIRSTAID, disease.get_first_aid());
        values.put(KEY_TREATMENT, disease.get_treatment());

        // updating row
        return db.update(TABLE_DISEASE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(disease.getID()) });
    }
    // Deleting single disease
    public void deletedisease(Disease disease) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DISEASE, KEY_NAME + " = ?",
                new String[] { disease.getName() });
        db.close();
    }
}