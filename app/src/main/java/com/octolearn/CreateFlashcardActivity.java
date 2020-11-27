package com.octolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class CreateFlashcardActivity extends AppCompatActivity {

    Button addButton;
    EditText foreignWord;
    EditText nativeWord;

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

                ListOfWordsActivity.foreign_words.add(foreignWord.getText().toString());
                ListOfWordsActivity.native_words.add(nativeWord.getText().toString());

                WordsAdapter wordsAdapter = new WordsAdapter(CreateFlashcardActivity.this, ListOfWordsActivity.foreign_words, ListOfWordsActivity.native_words);
                ListOfWordsActivity.wordsListView.setAdapter(wordsAdapter);

                //startActivity(new Intent(CreateFlashcardActivity.this, ListOfWordsActivity.class));
            }
        });
    }
}