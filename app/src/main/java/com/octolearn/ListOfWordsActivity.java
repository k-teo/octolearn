package com.octolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


public class ListOfWordsActivity extends AppCompatActivity {

    ListView wordsListView;
    String[] foreign_words;
    String[] native_words;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_words);


        Resources res = getResources();
        wordsListView = (ListView) findViewById(R.id.wordsListView);
        foreign_words = res.getStringArray(R.array.foreign_words);
        native_words = res.getStringArray(R.array.native_words);

        WordsAdapter wordsAdapter = new WordsAdapter(this, foreign_words, native_words);
        wordsListView.setAdapter(wordsAdapter);

        Button button = (Button) findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListOfWordsActivity.this, CreateFlashcardActivity.class));
            }
        });
    }


}