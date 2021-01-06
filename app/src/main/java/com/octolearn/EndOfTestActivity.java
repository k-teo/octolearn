package com.octolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndOfTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of_test);

        Bundle b = getIntent().getExtras();
        double all = 1, good = 0;
        if(b != null)
        {
            all = b.getInt("all");
            good = b.getInt("good");
        }

        double percent = (good / all) * 100;
        TextView result = findViewById(R.id.resultTest);
        result.setText(Integer.toString((int) percent) + "%");

        Button button = (Button) findViewById(R.id.buttonTest);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EndOfTestActivity.this, WayOfLearningActivity.class));
            }
        });
    }
}