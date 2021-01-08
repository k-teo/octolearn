package com.octolearn;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CatalogsSQLiteDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "words_database";
    public static final String CATALOGS_TABLE_NAME = "catalogs";
    public static final String CATALOGS_COLUMN_ID = "id";
    public static final String CATALOGS_COLUMN_NAME = "name";

    public CatalogsSQLiteDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + CATALOGS_TABLE_NAME + " (" +
                CATALOGS_COLUMN_ID + " TEXT NOT NULL, " +
                CATALOGS_COLUMN_NAME + " TEXT NOT NULL, " +
                "PRIMARY KEY (" + CATALOGS_COLUMN_ID + ")" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CATALOGS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    boolean addCatalog(String id, String catalogName){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CATALOGS_COLUMN_ID, id);
        contentValues.put(CATALOGS_COLUMN_NAME, catalogName);

        return sqLiteDatabase.insert(CATALOGS_TABLE_NAME, null, contentValues) != -1;
    }

    Cursor getAllCatalogs() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + CATALOGS_TABLE_NAME, null);
    }

    boolean updateCatalog(String id, String catalogName) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CATALOGS_COLUMN_ID, id);
        contentValues.put(CATALOGS_COLUMN_NAME, catalogName);

        return sqLiteDatabase.update(CATALOGS_TABLE_NAME, contentValues,
                CATALOGS_COLUMN_ID + "=?",
                new String[] { id }) == 1;

    }

    boolean deleteCatalog(String id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(CATALOGS_TABLE_NAME,
                CATALOGS_COLUMN_ID + "=?" ,
                new String[] { id }) == 1;
    }
    void deleteAllCatalogs() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CATALOGS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
