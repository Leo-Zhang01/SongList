package sg.edu.rp.c346.songlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "simplenotes.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NOTE = "Song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGERS = "singers";
    private static final String COLUMN_YEARS = "years";
    private static final String COLUMN_STARS = "stars";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + TABLE_NOTE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_SINGERS + " TEXT,"
                + COLUMN_YEARS + " INTEGER,"
                + COLUMN_STARS + " INTEGER ) ";
        db.execSQL(createNoteTableSql);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        db.execSQL("ALTER TABLE " + TABLE_NOTE + " ADD COLUMN module_name TEXT ");
    }
    public long insertSong(Song song) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SINGERS, song.getSingers());
        values.put(COLUMN_TITLE, song.getTitle());
        values.put(COLUMN_STARS, song.getStars());
        values.put(COLUMN_YEARS, song.getYear());

        long result = db.insert(TABLE_NOTE, null, values);
        db.close();
        Log.d("SQL Insert ",""+ result); //id returned, shouldn’t be -1
        return result;
    }
    public ArrayList<Song> getAllSong() {
        ArrayList<Song> songs = new ArrayList<>();

        String selectQuery = "SELECT *" + " FROM " + TABLE_NOTE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                Song song = new Song(id,cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getInt(4));
                songs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }
//    public Song getOneSong(int id){
//        SQLiteDatabase db = this.getReadableDatabase();
//        String[] columns= {COLUMN_ID, COLUMN_TITLE , COLUMN_SINGERS, COLUMN_YEARS, COLUMN_STARS};
//        String condition = COLUMN_ID + "= ?";
//        String[] args = {String.valueOf(id)};
//        Cursor cursor = db.query(TABLE_NOTE, columns, condition, args,
//                null, null, null, null);
//        Song song = new Song(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getInt(4));
//        return song;
//    }
    public int updateSong(Song data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_YEARS, data.getYear());
        values.put(COLUMN_STARS, data.getStars());
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_SINGERS, data.getSingers());

        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_NOTE, values, condition, args);
        db.close();
        return result;
    }
    public int deleteSong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_NOTE, condition, args);
        db.close();
        return result;
    }
    public ArrayList<String> selectYears(){
        ArrayList<String> years = new ArrayList<>();
        years.add("0000");
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT "+ COLUMN_YEARS+" FROM " + TABLE_NOTE + " GROUP BY "+ COLUMN_YEARS;
        Cursor cursor = db.rawQuery(select, null);
        if (cursor.moveToFirst()) {
            do {
                String year = String.valueOf(cursor.getInt(0));
                years.add(year);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return years;
    }

}
