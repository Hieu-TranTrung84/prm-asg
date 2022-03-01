package group3.assignment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import group3.assignment.database.DatabaseHelper;
import group3.assignment.model.Member;

public class MemberDAO implements Serializable {
    private SQLiteDatabase db;

    public MemberDAO(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
    }

    public long insert(Member member) {
        ContentValues values = new ContentValues();
        values.put("name", member.getName());
        values.put("dob", member.getDob());
        return db.insert("Member", null, values);
    }

    public int update(Member member) {
        ContentValues values = new ContentValues();
        values.put("name", member.getName());
        values.put("dob", member.getDob());
        return db.update("Member", values, "idMember=?",
                new String[]{String.valueOf(member.getIdMember())});
    }

    public List<Member> getData(String sql, String... selectionArgs) {
        List<Member> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            Member member = new Member();
            member.setIdMember(Integer.parseInt(c.getString(c.getColumnIndex("idMember"))));
            member.setName(c.getString(c.getColumnIndex("name")));
            member.setDob(c.getString(c.getColumnIndex("dob")));
            list.add(member);
        }
        return list;
    }

    public List<Member> getAll() {
        String sql = "SELECT * FROM Member";
        return getData(sql);
    }

    public int delete(String id) {
        return db.delete("Member", "idMember=?", new String[]{id});
    }
}
