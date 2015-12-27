package zizoujab.htl.weatherapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by CiS Info on 26/12/2015.
 */
public class WeatherDbHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "weather.db" ;
    private static final int DATABASE_VERSION = 1;

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + WeatherContract.WeatherEntry.TABLE_NAME + " (" +
                    WeatherContract.WeatherEntry._ID + " INTEGER PRIMARY KEY," +
                    WeatherContract.WeatherEntry.COLUMN_NAME_DAY + TEXT_TYPE + COMMA_SEP +
                    WeatherContract.WeatherEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    WeatherContract.WeatherEntry.COLUMN_NAME_HIGH_LOW + TEXT_TYPE+
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + WeatherContract.WeatherEntry.TABLE_NAME;
    public WeatherDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public  long  insertWeather(Weather weather  ) {
         SQLiteDatabase db= this.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(WeatherContract.WeatherEntry.COLUMN_NAME_DAY, weather.getDay());
        values.put(WeatherContract.WeatherEntry.COLUMN_NAME_DESCRIPTION, weather.getDescription());
        values.put(WeatherContract.WeatherEntry.COLUMN_NAME_HIGH_LOW, weather.getHightLow());

// Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                WeatherContract.WeatherEntry.TABLE_NAME,
                null,
                values);
        return newRowId;
    }
    public  List<Weather> getWeathers(){

        SQLiteDatabase db = this.getReadableDatabase();
        List<Weather> contactList = new ArrayList<Weather>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + WeatherContract.WeatherEntry.TABLE_NAME;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Weather weather = new Weather();
                weather.setDay(cursor.getString(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_NAME_DAY)));
                weather.setDescription((cursor.getString(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_NAME_DESCRIPTION))));
                weather.setHightLow((cursor.getString(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_NAME_HIGH_LOW))));
                // Adding weather to list
                contactList.add(weather);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
}
