package iak.edwin.sunshine;

/**
 * Created by EDN on 2/11/2018.
 */

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


/**
 * Created by GagahIB on 2/6/2016.
 */

public class Queries {

    private SQLiteDatabase writableDatabase;
    private SQLiteDatabase readableDatabase;
    private DBHandler dbHandler;

    public Queries(DBHandler dbHandler) {
        this.dbHandler = dbHandler;
        initilizationDB();
    }

    public void initilizationDB(){
        this.writableDatabase = dbHandler.getWritableDatabase();
        this.readableDatabase = dbHandler.getReadableDatabase();
    }

    public void truncate(String table) {
        writableDatabase.execSQL("DELETE FROM " + table);
    }

//    public void closeDatabase() {
//        writableDatabase.close();
//        readableDatabase.close();
//    }


    public void insert(ListCuacaRepons list) {
        ContentValues values = new ContentValues();
        values.put(DBHandler.COLUMN_ID_CUACA, list.getWeather().get(0).getId());
        values.put(DBHandler.COLUMN_CUACA, list.getWeather().get(0).getMain());
        values.put(DBHandler.COLUMN_DERAJAT1, list.getTemp().getDay());
        values.put(DBHandler.COLUMN_DERAJAT2,list.getTemp().getMin());
        values.put(DBHandler.COLUMN_TANGGAL, list.getDt());

        writableDatabase.replace(DBHandler.TABLE_CHAT, null, values);
    }

//    public ArrayList<ListCuacaRepons> getData() {
//        ArrayList<ListCuacaRepons> list = new ArrayList<>();
//        Cursor mCursor = readableDatabase.rawQuery("SELECT * FROM " + DBHandler.TABLE_CHAT, null);
//        mCursor.moveToFirst();
//        if (!mCursor.isAfterLast()) {
//            do {
//                ListCuacaRepons entry = new ListCuacaRepons();
//                entry.setWeather(); = mCursor.getString(mCursor.getColumnIndex(DBHandler.COLUMN_ID_TUJUAN));
//                entry.nama_tujuan = mCursor.getString(mCursor.getColumnIndex(DBHandler.COLUMN_NAMA_TUJUAN));
//                entry.reg_id_tujuan = mCursor.getString(mCursor.getColumnIndex(DBHandler.COLUMN_REG_ID_TUJUAN));
//                entry.isi_chat = mCursor.getString(mCursor.getColumnIndex(DBHandler.COLUMN_ISI_CHAT));
//                entry.waktu = mCursor.getString(mCursor.getColumnIndex(DBHandler.COLUMN_WAKTU));
//                entry.status = mCursor.getInt(mCursor.getColumnIndex(DBHandler.COLUMN_STATUS));
//                entry.gambar = mCursor.getString(mCursor.getColumnIndex(DBHandler.COLUMN_GAMBAR));
//                entry.chat_from = mCursor.getInt(mCursor.getColumnIndex(DBHandler.COLUMN_CHAT_FROM));
//                list.add(entry);
//            } while (mCursor.moveToNext());
//        }
//        mCursor.close();
//        return list;
//    }
}

