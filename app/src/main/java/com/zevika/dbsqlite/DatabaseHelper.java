package com.zevika.dbsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

    public class DatabaseHelper extends SQLiteOpenHelper implements com.zevika.dbsqlite.SQLiteOpenHelper {
        public static final String DATABASE_NAME = "zevika.db";
        public static final String TABLE_NAME = "mahasiswa";
        private static final int DATABASE_VERSION = 2;


        public static final String COL_1 = "NIM";
        public static final String COL_2 = "NAMA";
        public static final String COL_3 = "KELAS";
        public static final String COL_4 = "NOHP";


        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            SQLiteDatabase db = this.getWritableDatabase();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table mahasiswa(nim integer primary key," +  "nama text," +
                    "kelas text," +
                    "nohp text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
            onCreate(db);
        }

        @Override
        public boolean insertData(String nim, String nama, String kelas, String nohp) {  SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1,nim);
            contentValues.put(COL_2,nama);
            contentValues.put(COL_3,kelas);
            contentValues.put(COL_4,nohp);
            long result = db.insert(TABLE_NAME, null, contentValues);  if(result == -1)
                return false;
            else
                return true;
        }

        @Override
        public Cursor getAllData() {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("select * from mahasiswa", null);  return res;
        }

        @Override
        public boolean updateData(String nim, String nama, String kelas, String nohp) {  SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1,nim);
            contentValues.put(COL_2,nama);
            contentValues.put(COL_3,kelas);
            contentValues.put(COL_4,nohp);
            db.update(TABLE_NAME,contentValues,"NIM = ?",new String[] {nim});  return true;
        }

        @Override
        public int deleteData(String nim) {
            SQLiteDatabase db = this.getWritableDatabase();
            return db.delete(TABLE_NAME, "NIM = ?", new String[] {nim});  }
    }






