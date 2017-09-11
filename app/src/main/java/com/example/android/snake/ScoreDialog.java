package com.example.android.snake;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.snake.db.Score;
import com.example.android.snake.db.SnakeDbHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeanpierrebattaglia on 9/10/17.
 */

public class ScoreDialog extends DialogFragment {

    private TextView scoresView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.user_scores)
                .setView(R.layout.score_layout)
                .setCancelable(true)
                .show();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        scoresView = view.findViewById(R.id.scores_view);
        loadScores();
    }

    private void loadScores() {
        SnakeDbHelper snakeDbHelper = new SnakeDbHelper(getActivity());
        Cursor cursor = snakeDbHelper.getReadableDatabase()
                .query(Score.TABLE_NAME, null, null, null, null, null, null);

        List<Integer> scores = new ArrayList<>();
        while (cursor.moveToNext()) {
            scores.add(cursor.getInt(cursor.getColumnIndexOrThrow(Score.SCORE_COL)));
        }
        cursor.close();

        //TODO: display these in an adapter instead
        for (Integer score : scores) {
            scoresView.setText(scoresView.getText() + " - "+ score);
        }

    }

}
