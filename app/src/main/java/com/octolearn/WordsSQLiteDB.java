package com.octolearn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WordsSQLiteDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "words_database";
    public static final String FLASHCARDS_TABLE_NAME = "flashcards";
    public static final String FLASHCARDS_COLUMN_WORD = "word";
    public static final String FLASHCARDS_COLUMN_TRANSLATION = "translation";
    public static final String FLASHCARDS_COLUMN_SAMPLE = "sample";
    public static final String FLASHCARDS_COLUMN_TRANSCRIPTION = "transcription";
    public static final String FLASHCARDS_COLUMN_CATALOG_NAME = "catalog_name";

    public WordsSQLiteDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + FLASHCARDS_TABLE_NAME + " (" +
                FLASHCARDS_COLUMN_WORD + " TEXT NOT NULL, " +
                FLASHCARDS_COLUMN_TRANSLATION + " TEXT NOT NULL, " +
                FLASHCARDS_COLUMN_SAMPLE + " TEXT, " +
                FLASHCARDS_COLUMN_TRANSCRIPTION + " TEXT, " +
                FLASHCARDS_COLUMN_CATALOG_NAME + " TEXT NOT NULL, " +
                "PRIMARY KEY (" + FLASHCARDS_COLUMN_WORD + ", " + FLASHCARDS_COLUMN_TRANSLATION+ ")" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FLASHCARDS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    boolean addFlashcard(String word, String translation, String sample, String transcription,
                         String catalogName){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(FLASHCARDS_COLUMN_WORD, word);
        contentValues.put(FLASHCARDS_COLUMN_TRANSLATION, translation);
        contentValues.put(FLASHCARDS_COLUMN_SAMPLE, sample);
        contentValues.put(FLASHCARDS_COLUMN_TRANSCRIPTION, transcription);
        contentValues.put(FLASHCARDS_COLUMN_CATALOG_NAME, catalogName);

        return sqLiteDatabase.insert(FLASHCARDS_TABLE_NAME, null, contentValues) != -1;
    }

    Cursor getAllFlashcards() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + FLASHCARDS_TABLE_NAME, null);
    }

    boolean updateFlashcard(String word, String translation, String newWord, String newTranslation, String newSample, String newTranscription,
                           String catalogName) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FLASHCARDS_COLUMN_WORD, newWord);
        contentValues.put(FLASHCARDS_COLUMN_TRANSLATION, newTranslation);
        contentValues.put(FLASHCARDS_COLUMN_SAMPLE, newSample);
        contentValues.put(FLASHCARDS_COLUMN_TRANSCRIPTION, newTranscription);
        contentValues.put(FLASHCARDS_COLUMN_CATALOG_NAME, catalogName);

        return sqLiteDatabase.update(FLASHCARDS_TABLE_NAME, contentValues,
                FLASHCARDS_COLUMN_WORD + "=?",
                new String[] { word }) == 1;

    }

    boolean deleteFlashcard(String word, String translation) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(FLASHCARDS_TABLE_NAME,
                FLASHCARDS_COLUMN_WORD + "=?" ,
                new String[] { word }) == 1;
    }
    void deleteAllFlashcards() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FLASHCARDS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
