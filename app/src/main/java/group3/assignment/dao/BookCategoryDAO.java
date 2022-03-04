package group3.assignment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import group3.assignment.database.DatabaseHelper;
import group3.assignment.model.BookCategory;

public class BookCategoryDAO implements Serializable {
    private SQLiteDatabase db;

    public BookCategoryDAO(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
    }


    public long insert(BookCategory category) {
        ContentValues values = new ContentValues();
        values.put("nameBookCategory", category.getNameBookCategory());
        return db.insert("BookCategory", null, values);
    }

    public int update(BookCategory category) {
        ContentValues values = new ContentValues();
        values.put("nameBookCategory", category.getNameBookCategory());
        return db.update("BookCategory", values, "idBookCategory=?",
                new String[]{String.valueOf(category.getIdBookCategory())});
    }

    public int delete(String id) {
        return db.delete("BookCategory", "idBookCategory=?", new String[]{id});

    }

    public List<BookCategory> getData(String sql, String... selectionArgs) {
        List<BookCategory> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            BookCategory category = new BookCategory();
            category.setIdBookCategory(Integer.parseInt(c.getString(c.getColumnIndex("idBookCategory"))));
            category.setNameBookCategory(c.getString(c.getColumnIndex("nameBookCategory")));
            list.add(category);
        }
        return list;
    }

    public List<BookCategory> getAll() {
        String sql = "SELECT * FROM BookCategory";
        return getData(sql);
    }

    public BookCategory getID(String id) {
        String sql = "SELECT * FROM BookCategory WHERE idBookCategory = ?";
        List<BookCategory> list = getData(sql, id);
        return list.get(0);
    }
}
