package cn.edu.hznu.afinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_USER = "create table user("
            +"id integer primary key autoincrement,"
            +"phone text,"
            +"name text,"
            +"password text)";
    public static final String CREATE_CONTACT ="create table timer("
            +"id integer primary key autoincrement,"
            +"notype text,"
            +"phone text,"
            +"tname text,"
            +"tdate text,"
            +"ttime text,"
            +"tretime text)";
    private Context mContext;
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_CONTACT);
        Toast.makeText(mContext,"Create",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldv,int newv){

    }

}
