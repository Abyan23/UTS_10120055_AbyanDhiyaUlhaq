package com.example.uts_10120055;
//10120055
//Abyan Dhiya Ulhaq
//IF-2
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class NoteDatabase extends SQLiteOpenHelper {

    public static final int DB_VERSION = 2;
    public static String DB_NAME = "NotesDB.db";
    public static String DB_TABLE = "NotesTable";

    public static String COLUMN_ID = "NotesId";
    public static String COLUMN_TITLE = "NotesTitle";
    public static String COLUMN_DETAILS = "NotesDetails";
    public static String COLUMN_DATE = "NotesDate";
    public static String COLUMN_TIME = "NotesTime";

    public NoteDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + DB_TABLE +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + "TEXT," +
                COLUMN_DETAILS + "TEXT" +
                COLUMN_DATE + "TEXT," +
                COLUMN_TIME + "TEXT" + ")";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        if (i > i1)
            return;
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(sqLiteDatabase);
    }
    public long AddNote(NoteModel noteModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, noteModel.getNoteTitle());
        contentValues.put(COLUMN_DETAILS, noteModel.getNoteDetails());
        contentValues.put(COLUMN_DATE, noteModel.getNoteDate());
        contentValues.put(COLUMN_TIME, noteModel.getNoteTime());

        long ID = db.insert(DB_TABLE, null,contentValues);
        Log.d("Inserted", "id ->" + ID);
        return ID;
    }
}
