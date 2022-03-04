package group3.assignment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import group3.assignment.database.DatabaseHelper;
import group3.assignment.model.Card;

public class CardDAO implements Serializable {
    private SQLiteDatabase db;
    SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");

    public CardDAO(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
    }

    public long insert(Card card) {
        ContentValues values = new ContentValues();
        values.put("idEmployee", card.getIdEmployee());
        values.put("idMember", card.getIdMember());
        values.put("idBook", card.getIdBook());
        values.put("price", card.getPrice());
        values.put("date", date.format(card.getDate()));
        values.put("returnBook", card.getReturnBook());
        return db.insert("Card", null, values);
    }

    public int update(Card card) {
        ContentValues values = new ContentValues();
        values.put("idEmployee", card.getIdEmployee());
        values.put("idMember", card.getIdMember());
        values.put("idBook", card.getIdBook());
        values.put("price", card.getPrice());
        values.put("date", date.format(card.getDate()));
        values.put("returnBook", card.getReturnBook());
        return db.update("Card", values, "idCard=?",
                new String[]{String.valueOf(card.getIdCard())});
    }

    public int delete(String id) {
        return db.delete("Card", "idCard=?", new String[]{id});
    }

    public List<Card> getData(String sql, String... selectionArgs) {
        List<Card> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            Card card = new Card();
            card.setIdCard(Integer.parseInt(c.getString(c.getColumnIndex("idCard"))));
            card.setIdEmployee(c.getString(c.getColumnIndex("idEmployee")));
            card.setIdMember(Integer.parseInt(c.getString(c.getColumnIndex("idMember"))));
            card.setIdBook(Integer.parseInt(c.getString(c.getColumnIndex("idBook"))));
            card.setPrice(Integer.parseInt(c.getString(c.getColumnIndex("price"))));
            try {
                card.setDate(date.parse(c.getString(c.getColumnIndex("date"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            card.setReturnBook(Integer.parseInt(c.getString(c.getColumnIndex("returnBook"))));
            list.add(card);
        }
        return list;
    }

    public List<Card> getAll() {
        String sql = "SELECT * FROM Card";
        return getData(sql);
    }
}
