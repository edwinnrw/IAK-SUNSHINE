package iak.edwin.sunshine;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by GagahIB on 6/6/2016.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "CUACA";
    private static int SCHEMA_VERSION = 4;

    public static String TABLE_CHAT = "TODAY";

    public static String COLUMN_ID_CUACA = "ID_CUACA";
    public static String COLUMN_TANGGAL = "TANGGAL";
    public static String COLUMN_ICON = "ICON";
    public static String COLUMN_DERAJAT1 = "DERAJAT1";
    public static String COLUMN_DERAJAT2 = "DERAJAT2";
    public static String COLUMN_CUACA = "CUACA";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_CHAT + " ("
                + COLUMN_ID_CUACA + " VARCHAR NOT NULL, "
                + COLUMN_TANGGAL + " VARCHAR NOT NULL, "
                + COLUMN_ICON + " VARCHAR NOT NULL, "
                + COLUMN_DERAJAT1 + " VARCHAR NOT NULL, "
                + COLUMN_DERAJAT2 + " VARCHAR NOT NULL, "
                + COLUMN_CUACA + " VARCHAR NOT NULL ) ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_CHAT + " ("
                + COLUMN_ID_CUACA + " VARCHAR NOT NULL, "
                + COLUMN_TANGGAL + " VARCHAR NOT NULL, "
                + COLUMN_ICON + " VARCHAR NOT NULL, "
                + COLUMN_DERAJAT1 + " VARCHAR NOT NULL, "
                + COLUMN_DERAJAT2 + " VARCHAR NOT NULL, "
                + COLUMN_CUACA + " VARCHAR NOT NULL ) ");

    }
}
