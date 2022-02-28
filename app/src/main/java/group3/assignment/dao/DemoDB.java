package group3.assignment.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import group3.assignment.database.DatabaseHelper;

public class DemoDB {
    private SQLiteDatabase database;

    public DemoDB(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();

    }
}
