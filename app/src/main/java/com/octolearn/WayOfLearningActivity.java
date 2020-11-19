package com.octolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WayOfLearningActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_way_of_learning);

        Button listOfWords = findViewById(R.id.wordsListView);
        listOfWords.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wordsListView:
                startActivity(new Intent(WayOfLearningActivity.this, ListOfWordsActivity.class));
                break;
        }
    }
}