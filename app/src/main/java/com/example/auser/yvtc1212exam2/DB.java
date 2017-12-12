package com.example.auser.yvtc1212exam2;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class DB {
    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_NAME = "data";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + " (_id INTEGER PRIMARY KEY NOT NULL,cTitle TEXT,cLng TEXT,cLat TEXT)";

    private Context mCtx;
    private DataBaseOpenHelper helper;
    private SQLiteDatabase db;

    public DB(Context context) {
        mCtx = context;
    }

    public DB open() {
        helper = new DataBaseOpenHelper(mCtx);
        db = helper.getReadableDatabase();
        return this;
    }

    private class DataBaseOpenHelper extends SQLiteOpenHelper {

        public DataBaseOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public static final String KEY_ROWID = "cId";
    public static final String KEY_TITLE = "cTitle";
    public static final String KEY_LNG = "cLng";
    public static final String KEY_LAT = "cLat";

    String[] strCols = {KEY_TITLE, KEY_LNG , KEY_LAT};

    public Cursor getAll() {
//        return db.rawQuery("Select * From " + TABLE_NAME ,null);
        return db.query(TABLE_NAME,strCols,null,null,null,null,null);
    }

    public Cursor get(long rowId) throws SQLException {
        Cursor mCursor = db.query(true,
                TABLE_NAME,
                strCols,
                KEY_ROWID + "=" + rowId,
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;
    }

    public long latlng(Double x,Double y) {
        ContentValues args = new ContentValues();
        args.put(KEY_LAT, x);
        args.put(KEY_LNG, y);
        return db.insert(TABLE_NAME, null, args);
    }

    public long create(String noteName) {
        ContentValues args = new ContentValues();
        args.put(KEY_TITLE, noteName);
        args.put(KEY_LNG,noteName);
        return db.insert(TABLE_NAME, null, args);
    }

    public boolean delete(long rowId) {
        return db.delete(TABLE_NAME, KEY_ROWID + "=" + rowId, null) > 0;
    }

    public boolean update(long rowId, String noteName) {
        ContentValues args = new ContentValues();
        args.put(KEY_TITLE, noteName);
        return db.update(TABLE_NAME, args, KEY_ROWID + "=" + rowId, null) > 0;
    }

}