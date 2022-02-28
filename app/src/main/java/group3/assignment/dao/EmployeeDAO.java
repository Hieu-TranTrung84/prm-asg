package group3.assignment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import group3.assignment.database.DatabaseHelper;
import group3.assignment.model.Employee;

public class EmployeeDAO implements Serializable {
    private SQLiteDatabase db;

    public EmployeeDAO(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
    }

    public int update(Employee emp) {
        ContentValues values = new ContentValues();
        values.put("name", emp.getName());
        values.put("password", emp.getPassword());
        return db.update("Employee", values, "idEmployee=?", new String[]{emp.getIdEmployee()});
    }


    public List<Employee> getData(String sql, String... selectionArgs) {
        List<Employee> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            Employee emp = new Employee();
            emp.setIdEmployee(c.getString(c.getColumnIndex("idEmployee")));
            emp.setName(c.getString(c.getColumnIndex("name")));
            emp.setPassword(c.getString(c.getColumnIndex("password")));
            list.add(emp);
        }
        return list;
    }

    public Employee getUsername(String username) {
        String sql = "SELECT * FROM Employee WHERE idEmployee = ?";
        List<Employee> list = getData(sql, username);
        return list.get(0);
    }


    public int checkLogin(String username, String password) {
        String sql = "SELECT * FROM Employee WHERE idEmployee = ? AND password = ?";
        List<Employee> list = getData(sql, username, password);
        if (list.size() == 0) {
            return -1;
        }
        return 1;
    }
}
