package com.example.android.snake.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import static android.R.attr.version;

/**
 * Created by jeanpierrebattaglia on 9/10/17.
 */

public class SnakeDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "SNAKE_DB";
    private static final int DB_VERSION = 1;

    public SnakeDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if(Build.VERSION.SDK_INT < 16) {
            db.execSQL("PRAGMA foreign_keys = ON;");
        }

        db.execSQL(Score.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != DB_VERSION) {
            recreateDb(db);
        }
    }

    private void recreateDb(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS "+ Score.CREATE_TABLE);
        onCreate(db);
    }
}
