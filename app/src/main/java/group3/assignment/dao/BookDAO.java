package group3.assignment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import group3.assignment.database.DatabaseHelper;
import group3.assignment.model.Book;

public class BookDAO implements Serializable {
    private SQLiteDatabase db;

    public BookDAO(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
    }

    public List<Book> getData(String sql, String... selectionArgs) {
        List<Book> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            Book book = new Book();
            book.setIdBook(Integer.parseInt(c.getString(c.getColumnIndex("idBook"))));
            book.setName((c.getString(c.getColumnIndex("name"))));
            book.setRent(Integer.parseInt(c.getString(c.getColumnIndex("rent"))));
            book.setIdBookCategory(Integer.parseInt(c.getString(c.getColumnIndex("idBookCategory"))));
            list.add(book);
        }
        return list;
    }

    public List<Book> getAll() {
        String sql = "SELECT * FROM Book";
        return getData(sql);
    }

    public Book getId(String id) {
        String sql = "SELECT * FROM Book WHERE idBook=?";
        List<Book> list = getData(sql, id);
        return list.get(0);
    }

    public long insert(Book book) {
        ContentValues values = new ContentValues();
        values.put("name", book.getName());
        values.put("rent", book.getRent());
        values.put("idBookCategory", book.getIdBookCategory());
        return db.insert("Book", null, values);
    }

    public int update(Book book) {
        ContentValues values = new ContentValues();
        values.put("name", book.getName());
        values.put("rent", book.getRent());
        values.put("idBookCategory", book.getIdBookCategory());
        return db.update("Book", values, "idBook=?",
                new String[]{String.valueOf(book.getIdBook())});
    }

    public int delete(String id) {
        return db.delete("Book", "idBook=?", new String[]{id});
    }
}
