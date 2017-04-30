package com.longisland_219408hotmail.mathematicswolf.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.longisland_219408hotmail.mathematicswolf.model.Formulario;
import com.longisland_219408hotmail.mathematicswolf.model.MathematicsWolf;

import java.util.ArrayList;

/**
 * Created by edgararana on 25/04/17.
 */

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String path) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.IMAGE, path);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }



    public int update(long _id, String name, String path) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, name);
        contentValues.put(DatabaseHelper.IMAGE, path);

        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

    public ArrayList<Formulario> getFavList() {
        String selectQuery = "SELECT  * FROM " + DatabaseHelper.TABLE_NAME;
        Log.e("imagen Si entra", "siiii");
        SQLiteDatabase db = dbHelper.getContextlist();

        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<Formulario> FavList = new ArrayList<Formulario>();
        if (cursor.moveToFirst()) {
            do {

                FavList.add(new Formulario(cursor.getString(1), cursor.getString(2)));
                Log.e("imagen path", cursor.getString(2));

            } while (cursor.moveToNext());
        }
        return FavList;
    }


}