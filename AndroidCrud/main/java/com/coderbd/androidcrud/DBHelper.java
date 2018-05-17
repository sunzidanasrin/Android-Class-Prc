package com.coderbd.androidcrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE = "contact_db";
    private static final String TABLE = "contacts";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    public DBHelper(Context context) {
        super(context, DATABASE, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists "+TABLE+"(id integer primary key, "+NAME+" text, "+EMAIL+" text, "+PHONE+" text)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    //insert into database
    public boolean insertContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        try {
            contentValues.put(NAME, contact.getName());
            contentValues.put(EMAIL, contact.getEmail());
            contentValues.put(PHONE, contact.getPhone());
            db.insert(TABLE, null, contentValues);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    //update data in database
    public boolean updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        try {
            contentValues.put(NAME, contact.getName());
            contentValues.put(EMAIL, contact.getEmail());
            contentValues.put(PHONE, contact.getPhone());
            db.update(TABLE, contentValues, "id = ?", new String[] {String.valueOf(contact.getId())});
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    //delete data from database
    public boolean deleteContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TABLE, "id = ?", new String[] {String.valueOf(contact.getId())});
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public ArrayList<Contact> getAllContacts(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Contact> contacts = new ArrayList<Contact>();

        Cursor cursor = db.rawQuery("select * from "+TABLE, null);
        cursor.moveToFirst();
        try {
            while (cursor.isAfterLast() == false) {
                Contact c = new Contact();
                c.setId(cursor.getInt(cursor.getColumnIndex("id")));
                c.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                c.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
                c.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
                contacts.add(c);
                cursor.moveToNext();
            }
            return contacts;
        }
        catch (Exception e){
            return null;
        }
    }
}





















