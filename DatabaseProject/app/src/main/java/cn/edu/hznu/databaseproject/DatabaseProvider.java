package cn.edu.hznu.databaseproject;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DatabaseProvider extends ContentProvider {
    public static final int PHONE_DIR = 0;
    public static final int PHONE_ITEM = 1;
    public static final String AUTHORITY = "cn.edu.hznu.databaseproject.provider";

    private static UriMatcher uriMatcher;
    private MyDatabase dbHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "contact",  PHONE_DIR);
        uriMatcher.addURI(AUTHORITY, "contact/#", PHONE_ITEM );
    }

    @Override
    public boolean onCreate() {
        dbHelper=new MyDatabase(getContext(),"phone.db",null,1);
        return true;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deletedRows = 0;
        switch (uriMatcher.match(uri)) {
            case PHONE_DIR:
                deletedRows = db.delete("contact", selection, selectionArgs);
                break;
            case PHONE_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deletedRows = db.delete("contact", "id = ?", new String[] { bookId });
                break;
            default:
                break;
        }
        return deletedRows;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case PHONE_DIR:
                return "vnd.android.cursor.dir/vnd.cn.edu.hznu.databaseproject.contact";
            case PHONE_ITEM:
                return "vnd.android.cursor.item/vnd.cn.edu.hznu.databaseproject.contact";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)) {
            case PHONE_DIR:
            case PHONE_ITEM:
                long newId = db.insert("contact", null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/contact/" + newId);
                break;
            default:
                break;
        }
        return uriReturn;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case PHONE_DIR:
                cursor = db.query("contact", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PHONE_ITEM:
                String Id = uri.getPathSegments().get(1);
                cursor = db.query("contact", projection, "id = ?", new String[] { Id }, null, null,
                        sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updatedRows = 0;
        switch (uriMatcher.match(uri)) {
            case PHONE_DIR:
                updatedRows = db.update("contact", values, selection, selectionArgs);
                break;
            case PHONE_ITEM:
                String Id = uri.getPathSegments().get(1);
                updatedRows = db.update("contact", values, "id = ?", new String[] { Id });
                break;
            default:
                break;
        }
        return updatedRows;
    }
}