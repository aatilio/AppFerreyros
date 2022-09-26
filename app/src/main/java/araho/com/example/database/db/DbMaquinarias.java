package araho.com.example.database.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import araho.com.example.database.entidades.Maquinarias;

public class DbMaquinarias extends  DbHelper {

    Context context;

    public DbMaquinarias(@Nullable Context context) {
        super(context);

        this.context = context;
    }


    public long insertarMaquinaria(String codigo, String nombre, String capacidad, String peso) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("codigo", codigo);
            values.put("nombre", nombre);
            values.put("capacidad", capacidad);
            values.put("peso", peso);

            id = db.insert(TABLE_MAQUINARIAS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Maquinarias> mostrarMaquinarias() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Maquinarias> listaMaquinarias = new ArrayList<>();
        Maquinarias maquinarias;
        Cursor cursorMaquinarias;

        cursorMaquinarias = db.rawQuery("SELECT * FROM " + TABLE_MAQUINARIAS + " ORDER BY nombre ASC", null);

        if (cursorMaquinarias.moveToFirst()) {
            do {
                //agregamos la informacion de cada fila y cada columna de nuestra base de datos
                maquinarias = new Maquinarias();
                maquinarias.setId(cursorMaquinarias.getInt(0));
                maquinarias.setCodigo(cursorMaquinarias.getString(1));
                maquinarias.setNombre(cursorMaquinarias.getString(2));
                maquinarias.setCapacidad(cursorMaquinarias.getString(3));
                maquinarias.setPeso(cursorMaquinarias.getString(4));
                listaMaquinarias.add(maquinarias);
            } while (cursorMaquinarias.moveToNext());//nos movemos al siguiente cliente
        }

        cursorMaquinarias.close();

        return listaMaquinarias;
    }

    public Maquinarias verMaquinaria(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Maquinarias maquinarias = null;
        Cursor cursorMaquinarias;

        cursorMaquinarias = db.rawQuery("SELECT * FROM " + TABLE_MAQUINARIAS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorMaquinarias.moveToFirst()) {
            //agregamos la informacion de cada fila y cada columna de nuestra base de datos
            maquinarias = new Maquinarias();
            maquinarias.setId(cursorMaquinarias.getInt(0));
            maquinarias.setCodigo(cursorMaquinarias.getString(1));
            maquinarias.setNombre(cursorMaquinarias.getString(2));
            maquinarias.setCapacidad(cursorMaquinarias.getString(3));
            maquinarias.setPeso(cursorMaquinarias.getString(4));

           }

        cursorMaquinarias.close();

        return maquinarias;
    }

    public boolean editarMaquinaria(int id, String codigo, String nombre, String capacidad, String peso) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_MAQUINARIAS + " SET codigo = '" + codigo + "', nombre = '" + nombre + "', capacidad = '" + capacidad + "', peso = '" + peso + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally { //siempre va aingresar a esta linea de ejecucion
            db.close();
        }

        return correcto;
    }

    public boolean eliminarMaquinaria(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_MAQUINARIAS + " WHERE id = '" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally { //siempre va aingresar a esta linea de ejecucion
            db.close();
        }

        return correcto;
    }


}
