package android.hitech.com.hitechcrm.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Dbsave extends SQLiteOpenHelper {
    boolean result = false;
    Cursor cursor;
    Bitmap bmp;

    public Dbsave(Context context) {
        super(context, "Dbsave", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table autosavee (ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,ROWID BLOB)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists autosavee");
        onCreate(db);
    }

    public void insertData(int id, Bitmap img) {
        byte[] data = getBitmapAsByteArray(img);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("DATE", id);
        cv.put("ROWID", data);
         db.insert("autosave", null, cv);
        //System.out.println("TheRow :" + db.insert("autosavee", null, cv));
    }

    public Bitmap getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("select * from autosavee", null);
        int index = cursor.getColumnIndex("DATE");
        System.out.println("fetched" + cursor.getColumnCount());
        cursor.close();
        return bmp;
    }


    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}
