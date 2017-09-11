package com.example.android.snake.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by jeanpierrebattaglia on 9/10/17.
 */

public class Score {

    public static final String TABLE_NAME = "SCORE";
    public static final String SCORE_COL = "score";
    public static final String USERNAME_COL = "username";
    public static final String DATE_COL = "date";

    static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            " _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            " " + SCORE_COL + " INTEGER NOT NULL, " +
            " "+ USERNAME_COL + " TEXT, " +
            " " + DATE_COL + " TEXT " +
            ");";

    private final long score;
    private final String username;
    private final long date;

    public Score(long score, String username, long date) {
        this.score = score;
        this.username = username;
        this.date = date;
    }


    public long getScore() {
        return score;
    }

    public String getUsername() {
        return username;
    }

    public long getDate() {
        return date;
    }

    public void insert(SQLiteDatabase writableDatabase) {
        ContentValues values = new ContentValues();
        values.put(Score.SCORE_COL, score);
        values.put(Score.USERNAME_COL, username);
        values.put(Score.DATE_COL, date);
        writableDatabase.insert(Score.TABLE_NAME, null, values);
    }
}
