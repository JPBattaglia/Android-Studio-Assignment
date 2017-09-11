/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.snake;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.snake.db.Score;
import com.example.android.snake.db.SnakeDbHelper;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.home;

/**
 * SnakeActivity: a simple game that everyone can enjoy.
 * <p>
 * This is an implementation of the classic Game "SnakeActivity", in which you control a
 * serpent roaming around the garden looking for apples. Be careful, though,
 * because when you catch one, not only will you become longer, but you'll move
 * faster. Running into yourself or the walls will end the game.
 */
public class SnakeActivity extends Activity {

    private static final String SCORE_DIALOG_TAG = "SCORE_DIALOG_TAG";
    private static String ICICLE_KEY = "snake-view";

    private SnakeView mSnakeView;

    Context context;
    MediaPlayer mPlayer;

    /**
     * Called when Activity is first created. Turns off the title bar, sets up
     * the content views, and fires up the SnakeView.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.snake_layout);

        context = this;

        mSnakeView = (SnakeView) findViewById(R.id.snake);
        mSnakeView.setTextView((TextView) findViewById(R.id.text));


        if (savedInstanceState == null) {
            // We were just launched -- set up a new game
            mSnakeView.setMode(SnakeView.READY);
        } else {
            // We are being restored
            Bundle map = savedInstanceState.getBundle(ICICLE_KEY);
            if (map != null) {
                mSnakeView.restoreState(map);
            } else {
                mSnakeView.setMode(SnakeView.PAUSE);
            }
        }

        new ScoreDialog().show(getFragmentManager(), SCORE_DIALOG_TAG);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mPlayer = MediaPlayer.create(context, R.raw.theme);
        mPlayer.start();
        mPlayer.setVolume((float) 0.8, (float) 0.8);
        mPlayer.setLooping(true);

    }


    @Override
    protected void onPause() {
        super.onPause();
        // Pause the game along with the activity
        mSnakeView.setMode(SnakeView.PAUSE);

        if (mPlayer.isPlaying()) {
            mPlayer.pause();
            mPlayer.release();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //Store the game state
        outState.putBundle(ICICLE_KEY, mSnakeView.saveState());
    }

}