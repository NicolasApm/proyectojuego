package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Sqlite_OpenHelper extends SQLiteOpenHelper {


    public Sqlite_OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query="create table usuario(_ID integer primary key autoincrement,Nombre text, " +
                     "Apellido text,Edad text);";
        db.execSQL(query);


    }
  public void prueba (){}
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //abrir db
    public void abrirdb(){
        this.getWritableDatabase();

    }

    //cerrar db

    public void cerrardb (){
        this.close();
    }

    /*contentValues: permite agrupar un conjunto de valores
    para que puedan ser procesados*/

    //metodo para insertar registro en tabla usuarios


    public void insertarReg(String Nom, String Ape, String edad){

        ContentValues valores = new ContentValues();
        valores.put("Nombre",Nom);
        valores.put("Apellido",Ape);
        valores.put("Edad",edad);
        this.getWritableDatabase().insert("usuario",null,valores);

    }

    public Cursor ConsultarUsuario (String usu,String Ape ) throws SQLException{
        Cursor mcursor =null;
        mcursor=this.getReadableDatabase().query("usuario",new String []{"_ID", "Nombre", "Apellido", "Edad"},
                "Nombre like '"+usu+"' and Apellido like '" +Ape+"'",null,null,null,null);
        return mcursor;
    }
}
