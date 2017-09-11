package com.example.android.snake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by jeanpierrebattaglia on 8/31/17.
 */

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        Thread myThread = new Thread() {

            @Override
            public void run() {
                try {
                    sleep(2500);
                    Intent startMainScreen = new Intent(getApplicationContext(), SnakeActivity.class);
                    startActivity(startMainScreen);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
