package com.octolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class CreateFlashcardActivity extends  AppCompatActivity{

    Button addButton;
    EditText foreignWord;
    EditText nativeWord;
    ListView wordsListView;
    String[] foreign_words;
    String[] native_words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_flashcard);

        foreignWord = (EditText) findViewById(R.id.foreignWordPlainText);
        nativeWord = (EditText) findViewById(R.id.nativeWordPlainText);
        addButton = (Button) findViewById(R.id.add_flashcard);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                foreign_words = new String[]{foreignWord.getText().toString()};
                native_words = new String[]{foreignWord.getText().toString()};

                WordsAdapter wordsAdapter = new WordsAdapter(CreateFlashcardActivity.this, foreign_words, native_words);
                //wordsListView.setAdapter(wordsAdapter);
            }
        });
    }
}