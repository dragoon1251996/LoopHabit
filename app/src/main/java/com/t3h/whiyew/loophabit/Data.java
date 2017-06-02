package com.t3h.whiyew.loophabit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Whiyew on 27/04/2017.
 */

public class Data extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "japan.db";
    public static final String TABLE_JAPAN = "japan";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_COLOR = "color";
    public static final String COLUMN_HOUR = "hour";
    public static final String COLUMN_MINUTE = "minute";
    public static final String COLUMN_DATE = "date";

    //    public static final String COLUMN_IMAGE = "image";
    public static final String KEY_ID = "id";

    public Data(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
    }

    public ArrayList<NameHabit> getAllContacts() {
        ArrayList<NameHabit> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_JAPAN;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                NameHabit contact = new NameHabit(cursor.getString(0), cursor.getString(1), new Time(cursor.getString(2), cursor.getString(3)));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String creat_table = "CREATE TABLE " + TABLE_JAPAN + " (" + COLUMN_COLOR + " TEXT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_HOUR + " TEXT,"
                + COLUMN_MINUTE + " TEXT)";
        db.execSQL(creat_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JAPAN);
        onCreate(db);
    }

    public Cursor getDataAll() {
        ArrayList<NameHabit> japen = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_JAPAN, new String[]{COLUMN_COLOR, COLUMN_NAME, COLUMN_HOUR, COLUMN_MINUTE
        }, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            db.close();
            return cursor;
        } else {
            db.close();
            return null;
        }

    }

    public void addJapan(NameHabit japan) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COLOR, japan.getColor());
        contentValues.put(COLUMN_NAME, japan.getTenThuocTinh());
        contentValues.put(COLUMN_HOUR, japan.getTime().getHour());
        contentValues.put(COLUMN_MINUTE, japan.getTime().getMinute());
        SQLiteDatabase db = this.getReadableDatabase();
        db.insert(TABLE_JAPAN, null, contentValues);
        db.close();
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_JAPAN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }
}
