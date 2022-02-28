package group3.assignment.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "libraryManagement";
    private static final int VERSION = 1;

    private final String CREATE_TABLE_EMPLOYEE = "CREATE TABLE " +
            "Employee (idEmployee TEXT PRIMARY KEY, " +
            "name TEXT NOT NULL, " +
            "password TEXT NOT NULL)";
    private final String CREATE_TABLE_MEMBER = "CREATE TABLE " +
            "Member (idMember INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL, " +
            "dob TEXT NOT NULL)";

    private final String CREATE_TABLE_BOOK_CATEGORY = "CREATE TABLE " +
            "BookCategory (idBookCategory INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nameBookCategory TEXT NOT NULL)";
    private final String CREATE_TABLE_BOOK = "CREATE TABLE " +
            "Book (idBook INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL, " +
            "rent INTEGER NOT NULL, " +
            "idBookCategory INTEGER REFERENCES BookCategory(idBookCategory))";
    private final String CREATE_TABLE_CARD = "CREATE TABLE " +
            "Card (idCard INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "idEmployee TEXT REFERENCES Employee(idEmployee), " +
            "idMember INTEGER REFERENCES Member(idMember), " +
            "idBook INTEGER REFERENCES Book(idBook), " +
            "price INTEGER NOT NULL, " +
            "date DATE NOT NULL, " +
            "returnBook INTEGER NOT NULL)";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_EMPLOYEE);
        database.execSQL(CREATE_TABLE_MEMBER);
        database.execSQL(CREATE_TABLE_BOOK_CATEGORY);
        database.execSQL(CREATE_TABLE_BOOK);
        database.execSQL(CREATE_TABLE_CARD);

        database.execSQL(Data.INSERT_EMPLOYEE);
        database.execSQL(Data.INSERT_MEMBER);
        database.execSQL(Data.INSERT_BOOK_CATEGORY);
        database.execSQL(Data.INSERT_BOOK);
        database.execSQL(Data.INSERT_CARD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {

        String dropEmployee = "drop table if exists Employee";
        database.execSQL(dropEmployee);

        String dropMember = "drop table if exists Member";
        database.execSQL(dropMember);

        String dropBookCategory = "drop table if exists BookCategory";
        database.execSQL(dropBookCategory);

        String dropBook = "drop table if exists Book";
        database.execSQL(dropBook);

        String dropCard = "drop table if exists Card";
        database.execSQL(dropCard);

        onCreate(database);
    }
}
