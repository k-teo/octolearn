package com.octolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NoFlashcardsLeftActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_flashcards_left);
        Button button = (Button) findViewById(R.id.button);

        button.setText("Congratulation you have learnt all of words :), \ncome back for more tomorrow!");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoFlashcardsLeftActivity.this, WayOfLearningActivity.class));
            }
        });
    }
}