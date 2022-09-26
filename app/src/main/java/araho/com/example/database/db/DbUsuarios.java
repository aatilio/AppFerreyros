package araho.com.example.database.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbUsuarios extends  DbHelper{

    Context context;

    public DbUsuarios(@Nullable Context context) {
        super(context);

        this.context = context;
    }
/*
    public long buscarUser(String usuario, String password) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursorUsuarios;

        cursorUsuarios = db.rawQuery("SELECT usuario, password FROM " + TABLE_USUARIOS + " WHERE usuario ='" + usuario + " AND password='" +password +"'", null);

        String usuario =
    }*/

    public long insertarUsuario(String usuario, String password) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("usuario", usuario);
            values.put("password", password);

            id = db.insert(TABLE_USUARIOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }
}
