package araho.com.example.database.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static  final int DATABASE_VERSION = 2;
    private static  final String DATABASE_NOMBRE = "Fereyros";
    public  static  final String TABLE_MAQUINARIAS = "maquinarias";
    public  static  final String TABLE_USUARIOS = "usuarios";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_MAQUINARIAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "codigo INTEGER NOT NULL, " +
                "nombre TEXT NOT NULL," +
                "capacidad TEXT NOT NULL," +
                "peso TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_USUARIOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "usuario TEXT NOT NULL," +
                "password TEXT NOT NULL )");

        //sqLiteDatabase.execSQL("INSERT INTO " + TABLE_USUARIOS + " VALUES ('alan', '123.')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_MAQUINARIAS);
        onCreate(sqLiteDatabase);

    }
}
